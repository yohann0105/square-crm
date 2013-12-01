/*
 * This file is a part of Square, Customer Relationship Management Software for insurance's companies
 * Copyright (C) 2010-2012  SCUB <square@scub.net> - Mutuelle SMATIS FRANCE  <square@smatis.fr >
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.square.tarificateur.noyau.util.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.scub.foundation.framework.base.exception.TechnicalException;

import com.square.core.service.interfaces.SquareMappingService;
import com.square.tarificateur.noyau.dto.RapportDto;

/**
 * Classe utilitaire pour la gestion des expressions régulières.
 * @author cblanchard - SCUB
 */
public final class ValidationExpressionUtil {

    /** Service de mapping de square. */
    private SquareMappingService squareMappingService;

    private static final String ZERO_STRING = "0";

    /**
     * Permet de vérifier le format du téléphone.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param telephone le téléphone
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierFormatTelephone(final RapportDto rapport, final String message, final String erreurMessage, final String telephone,
        final String propNom) {
        verifierSiVide(rapport, message, erreurMessage, telephone, propNom);

        final boolean erreur = rapport.getRapports().get(propNom).getErreur() || (!telephone.toUpperCase().matches("[0-9]{10,15}"));
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Permet de vérifier la nature du téléphone.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param telephone le téléphone
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierTelephoneMobile(final RapportDto rapport, final String message, final String erreurMessage, final String telephone,
        final String propNom) {
        verifierFormatTelephone(rapport, message, erreurMessage, telephone, propNom);
        final boolean erreur = rapport.getRapports().get(propNom).getErreur() || (!telephone.startsWith("06") && !telephone.startsWith("07"));
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Permet de vérifier un email.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param email l'email
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierEmail(final RapportDto rapport, final String message, final String erreurMessage, final String email,
        final String propNom) {

        verifierSiVide(rapport, message, erreurMessage, email, propNom);

        final boolean erreur = rapport.getRapports().get(propNom).getErreur() || !email.toUpperCase().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Teste si le numéro de sécurité sociale est valide.
     * @param rapport le rapport a compléter
     * @param message le message si ok
     * @param erreurMessage le message d'erreur si ko
     * @param nomProp le nom de la propriété pour le rapport
     * @param numero le numéro de sécurité sociale
     * @param cle la clé
     * @param dateNaissance la date de naissance de la personne
     * @param idCivilite l'identifiant de la civilité de la personne
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierNumeroSecuriteSociale(final RapportDto rapport, final String message, final String erreurMessage,
        final String nomProp, final String numero, final String cle, final Calendar dateNaissance, final Long idCivilite) {

        verifierSiVide(rapport, message, erreurMessage, numero, nomProp);
        verifierSiVide(rapport, message, erreurMessage, cle, nomProp);

        boolean erreur = rapport.getRapports().get(nomProp).getErreur();

        if (!erreur) {
            String numSecu = numero.trim();
            final String cleSecu = cle.trim();
            final int tailleNumSecu = 13;
            final int tailleCle = 2;

            // Le numéro doit contenir 13 caractères et la clé 2
            if (numSecu.length() != tailleNumSecu || cleSecu.length() != tailleCle) {
                erreur = true;
            } else {

                // Cas particulier de la Corse
                // Si le numéro contient un A ou un B, on le remplace par un zéro
                final boolean contientA = numSecu.contains("A");
                final boolean contienta = numSecu.contains("a");
                final boolean contientB = numSecu.contains("B");
                final boolean contientb = numSecu.contains("b");

                // On remplace le A ou le B par un zéro
                if (contientA || contientB) {
                    if (contientA) {
                        numSecu = numSecu.replace("A", ZERO_STRING);
                    } else if (contienta) {
                        numSecu = numSecu.replace("a", ZERO_STRING);
                    } else if (contientB) {
                        numSecu = numSecu.replace("B", ZERO_STRING);
                    } else if (contientb) {
                        numSecu = numSecu.replace("b", ZERO_STRING);
                    }
                }

                try {

                    // On tente de transformer le numéro de sécu en nombre
                    long numSecuLong = Long.parseLong(numSecu);
                    final long cleLong = Long.parseLong(cleSecu);

                    // Cas particulier de la Corse
                    // Si le numéro de sécu contient un A, on soustrait 1000000 du numéro de sécu (nombre)
                    if (contientA || contienta) {
                        final long nbMagigueA = 1000000;
                        numSecuLong = numSecuLong - nbMagigueA;
                    } else if (contientB || contientb) {
                        // S'il contient un B, on soustrait 2000000 du numéro de sécu (nombre)
                        final long nbMagigueB = 2000000;
                        numSecuLong = numSecuLong - nbMagigueB;
                    }

                    // Cle = 97 - (num_secu % 97)
                    final long modulo = 97;
                    erreur = cleLong != modulo - (numSecuLong % modulo);
                } catch (NumberFormatException e) {
                    erreur = true;
                }

                // Vérification du sexe/civilité
                final String civiliteNumSS = numSecu.substring(0, 1);
                if (("2".equals(civiliteNumSS) && !squareMappingService.getIdCiviliteMadame().equals(idCivilite) && !squareMappingService
                        .getIdCiviliteMademoiselle().equals(idCivilite))
                    || ("1".equals(civiliteNumSS) && !squareMappingService.getIdCiviliteMonsieur().equals(idCivilite))) {
                    erreur = true;
                }

                // Vérification de l'année de naissance
                final String anneeNaissanceNumSS = numSecu.substring(1, 3);
                if (dateNaissance == null) {
                    erreur = true;
                } else {
                    final int anneeDateNaissance = dateNaissance.get(Calendar.YEAR);
                    if (String.valueOf(anneeDateNaissance).length() != 4) {
                        erreur = true;
                    } else {
                        final String anneeNaissance = String.valueOf(anneeDateNaissance).substring(2, 4);
                        if (!anneeNaissance.equals(anneeNaissanceNumSS)) {
                            erreur = true;
                        }
                    }
                }

                // Vérification du mois de naissance
                final String moisNaissanceNumSS = numSecu.substring(3, 5);
            	final Integer moisNaissanceInteger = Integer.valueOf(moisNaissanceNumSS);
            	if ((1 <= moisNaissanceInteger && moisNaissanceInteger <= 12)
            			|| (31 <= moisNaissanceInteger && moisNaissanceInteger <= 42)) {
            		if (dateNaissance != null) {
            			final int moisDateNaissance = dateNaissance.get(Calendar.MONTH) + 1;
                		String moisNaissance;
                		String moisNaissanceFictif;
                		if (moisDateNaissance < 10) {
                            moisNaissance = ZERO_STRING + moisDateNaissance;
                        } else {
                            moisNaissance = String.valueOf(moisDateNaissance);
                        }
                		moisNaissanceFictif = String.valueOf(moisDateNaissance + 30);
                        if (!(moisNaissance.equals(moisNaissanceNumSS) || moisNaissanceFictif.equals(moisNaissanceNumSS))) {
                            erreur = true;
                        }
            		}
            	} else if (!((20 <= moisNaissanceInteger && moisNaissanceInteger <= 30)
            			|| (50 <= moisNaissanceInteger && moisNaissanceInteger <= 99))) {
            		erreur = true;
                }
            }
        }
        rapport.ajoutRapport(nomProp, erreur ? erreurMessage : message, erreur);

        return this;
    }

    /**
     * Méthode qui teste si un champ n'est pas null.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param propValeur la valeur de la propriété
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierSiNull(final RapportDto rapport, final String message, final String erreurMessage, final Object propValeur,
        final String propNom) {
        final boolean erreur = propValeur == null;
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Méthode qui teste si un champ n'est pas vide.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param propValeur la valeur de la propriété
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierSiVide(final RapportDto rapport, final String message, final String erreurMessage, final String propValeur,
        final String propNom) {
        verifierSiNull(rapport, message, erreurMessage, propValeur, propNom);
        final boolean erreur = rapport.getRapports().get(propNom).getErreur() || "".equals(propValeur.trim());
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Méthode qui teste si une condition est vraie.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param test le test a réaliser
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierSiVrai(final RapportDto rapport, final String message, final String erreurMessage, final boolean test,
        final String propNom) {
        final boolean erreur = rapport.getRapports().get(propNom).getErreur() || !test;
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Méthode qui prend une liste de propriété pour vérifier leur nullité sur un objet cible.
     * @param rapport le rapport.
     * @param bean le bean à vérifier
     * @param props tableau des expressions.
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierSiNull(final RapportDto rapport, final Object bean, final ValidationExpressionProp[] props) {
        for (ValidationExpressionProp prop : props) {
            try {
                final Object valeurProp = PropertyUtils.getProperty(bean, prop.getNomPropriete());
                verifierSiNull(rapport, prop.getMessage(), prop.getMessageErreur(), valeurProp, formerPropNom(prop, bean));
            } catch (IllegalAccessException e) {
                throw new TechnicalException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new TechnicalException(e.getMessage(), e);
            } catch (NoSuchMethodException e) {
                throw new TechnicalException(e.getMessage(), e);
            } catch (NestedNullException e) {
                rapport.ajoutRapport(formerPropNom(prop, bean), prop.getMessage(), true);
            }
        }
        return this;

    }

    private String formerPropNom(ValidationExpressionProp prop, Object bean) {
        return new StringBuffer(bean.getClass().getSimpleName()).append(".").append(prop.getNomPropriete()).append(
            prop.getIndex() >= 0 ? "." + prop.getIndex() : "").toString();
    }

    /**
     * Méthode qui prend une liste de propriété pour vérifier si remplis sur un objet cible.
     * @param rapport le rapport.
     * @param bean le bean à vérifier
     * @param props tableau des expressions.
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierSiVide(final RapportDto rapport, final Object bean, final ValidationExpressionProp[] props) {
        for (ValidationExpressionProp prop : props) {
            try {
                final Object valeurProp = PropertyUtils.getProperty(bean, prop.getNomPropriete());
                verifierSiVide(rapport, prop.getMessage(), prop.getMessageErreur(), valeurProp == null ? null : String.valueOf(valeurProp), formerPropNom(prop,
                    bean));
            } catch (IllegalAccessException e) {
                throw new TechnicalException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new TechnicalException(e.getMessage(), e);
            } catch (NoSuchMethodException e) {
                throw new TechnicalException(e.getMessage(), e);
            } catch (NestedNullException e) {
                rapport.ajoutRapport(formerPropNom(prop, bean), prop.getMessage(), true);
            }
        }
        return this;
    }

    // TODO : Supprimer les méthodes ci-dessous après refactoring
    /**
     * Permet de vérifier un numéro de téléphone.
     * @param telephone chaine de caractère représentant le téléphone à tester
     * @return renvoi true si le téléphone correspond au pattern false sinon
     */
    public boolean verifTelephone(String telephone) {
        if (telephone == null) {
            return false;
        }
        return telephone.matches("[0-9]{10,15}");

    }

    /**
     * Permet de vérifier un email.
     * @param email chaine de caractère représentant l'email à tester.
     * @return renvoi true si l'email correspond au pattern false sinon
     */
    public boolean verifEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        final String emailUpper = email.toUpperCase();
        return emailUpper.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
    }

    /**
     * Teste si le numéro de sécurité sociale est valide.
     * @param numeroSecuriteSociale le numéro à tester
     * @param cleNumSecu la clé du numéro de sécurité sociale
     * @return true si le numéro est valide, false sinon
     */
    public boolean isNumeroSecuValide(String numeroSecuriteSociale, String cleNumSecu) {
        if (!numeroSecuriteSociale.equals("") && !cleNumSecu.equals("")) {
            String numSecu = numeroSecuriteSociale.trim();
            final String cle = cleNumSecu.trim();
            final int tailleNumSecu = 13;
            final int tailleCle = 2;

            // Le numéro doit contenir 13 caractères et la clé 2
            if (numSecu.length() != tailleNumSecu || cle.length() != tailleCle) {
                return false;
            } else {

                // Cas particulier de la Corse
                // Si le numéro contient un A ou un B, on le remplace par un zéro
                final boolean contientA = numSecu.contains("A");
                final boolean contientB = numSecu.contains("B");

                // On remplace le A ou le B par un zéro
                if (contientA || contientB) {
                    if (contientA) {
                        numSecu = numSecu.replace("A", ZERO_STRING);
                    } else if (contientB) {
                        numSecu = numSecu.replace("B", ZERO_STRING);
                    }
                }

                try {

                    // On tente de transformer le numéro de sécu en nombre
                    long numSecuLong = Long.parseLong(numSecu);
                    final long cleLong = Long.parseLong(cle);

                    // Cas particulier de la Corse
                    // Si le numéro de sécu contient un A, on soustrait 1000000 du numéro de sécu (nombre)
                    if (contientA) {
                        final long nbMagigueA = 1000000;
                        numSecuLong = numSecuLong - nbMagigueA;
                    } else if (contientB) {
                        // S'il contient un B, on soustrait 2000000 du numéro de sécu (nombre)
                        final long nbMagigueB = 2000000;
                        numSecuLong = numSecuLong - nbMagigueB;
                    }

                    // Cle = 97 - (num_secu % 97)
                    final long modulo = 97;
                    return cleLong == (modulo - (numSecuLong % modulo));
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

}
