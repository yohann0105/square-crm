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
package com.square.core.util.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.time.DateUtils;
import org.scub.foundation.framework.base.exception.TechnicalException;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.util.FormatUtil;

/**
 * Classe utilitaire pour la gestion des expressions régulières.
 * @author cblanchard - SCUB
 */
public final class ValidationExpressionUtil {

    private FormatUtil formatUtil;

    /** Nombre de caractères maximum pour le libellé d'une adresse (numéro voie + nature de voie + nom voie). */
    private static final int NB_CARACTERE_ADRESSE = 38;

    /**
     * Permet de vérifier le format du téléphone.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param telephone le téléphone
     * @param formatTelephone le format du numéro de téléphone
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierFormatTelephone(final RapportDto rapport, final String message, final String erreurMessage, final String telephone,
        final String formatTelephone, final String propNom) {
        verifierSiVide(rapport, message, erreurMessage, telephone, propNom);

        if (formatTelephone != null)
        {
            final boolean erreur = rapport.getRapports().get(propNom).getErreur() || (!telephone.toUpperCase().matches(formatTelephone));
            rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
            rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        }
        return this;
    }

    /**
     * Permet de vérifier la nature du téléphone.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param telephone le téléphone
     * @param isPaysTelFrance indique si le pays du téléphone est la france
     * @param formatTelephone le format du numéro de téléphone
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierTelephoneMobile(final RapportDto rapport, final String message, final String erreurMessage, final String telephone,
        final boolean isPaysTelFrance, final String formatTelephone, final String propNom) {
        final String numeroTelephone = formatUtil.supprimerFormatNumTel(telephone);
        verifierFormatTelephone(rapport, message, erreurMessage, numeroTelephone, formatTelephone, propNom);
        boolean erreur = rapport.getRapports().get(propNom).getErreur();
        if (isPaysTelFrance) {
            erreur = erreur || (!numeroTelephone.startsWith("06") && !numeroTelephone.startsWith("07"));
        }
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
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierNumeroSecuriteSociale(final RapportDto rapport, final String message, final String erreurMessage,
        final String nomProp, final String numero, final String cle) {

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
            }
            else {

                // Cas particulier de la Corse
                // Si le numéro contient un A ou un B, on le remplace par un zéro
                final boolean contientA = numSecu.contains("A");
                final boolean contientB = numSecu.contains("B");

                // On remplace le A ou le B par un zéro
                if (contientA || contientB) {
                    if (contientA) {
                        numSecu = numSecu.replace("A", "0");
                    }
                    else if (contientB) {
                        numSecu = numSecu.replace("B", "0");
                    }
                }

                try {

                    // On tente de transformer le numéro de sécu en nombre
                    long numSecuLong = Long.parseLong(numSecu);
                    final long cleLong = Long.parseLong(cleSecu);

                    // Cas particulier de la Corse
                    // Si le numéro de sécu contient un A, on soustrait 1000000 du numéro de sécu (nombre)
                    if (contientA) {
                        final long nbMagigueA = 1000000;
                        numSecuLong = numSecuLong - nbMagigueA;
                    }
                    else if (contientB) {
                        // S'il contient un B, on soustrait 2000000 du numéro de sécu (nombre)
                        final long nbMagigueB = 2000000;
                        numSecuLong = numSecuLong - nbMagigueB;
                    }

                    // Cle = 97 - (num_secu % 97)
                    final long modulo = 97;
                    erreur = (cleLong != (modulo - (numSecuLong % modulo)));
                }
                catch (NumberFormatException e) {
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
            }
            catch (IllegalAccessException e) {
                throw new TechnicalException(e.getMessage(), e);
            }
            catch (InvocationTargetException e) {
                throw new TechnicalException(e.getMessage(), e);
            }
            catch (NoSuchMethodException e) {
                throw new TechnicalException(e.getMessage(), e);
            }
            catch (NestedNullException e) {
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
            }
            catch (IllegalAccessException e) {
                throw new TechnicalException(e.getMessage(), e);
            }
            catch (InvocationTargetException e) {
                throw new TechnicalException(e.getMessage(), e);
            }
            catch (NoSuchMethodException e) {
                throw new TechnicalException(e.getMessage(), e);
            }
            catch (NestedNullException e) {
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
            }
            else {

                // Cas particulier de la Corse
                // Si le numéro contient un A ou un B, on le remplace par un zéro
                final boolean contientA = numSecu.contains("A");
                final boolean contientB = numSecu.contains("B");

                // On remplace le A ou le B par un zéro
                if (contientA || contientB) {
                    if (contientA) {
                        numSecu = numSecu.replace("A", "0");
                    }
                    else if (contientB) {
                        numSecu = numSecu.replace("B", "0");
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
                    }
                    else if (contientB) {
                        // S'il contient un B, on soustrait 2000000 du numéro de sécu (nombre)
                        final long nbMagigueB = 2000000;
                        numSecuLong = numSecuLong - nbMagigueB;
                    }

                    // Cle = 97 - (num_secu % 97)
                    final long modulo = 97;
                    return cleLong == (modulo - (numSecuLong % modulo));
                }
                catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }

    /**
     * Détermine si la date de naissance passée en paramètre est valide ou pas.
     * @param dateDeNaissance la date de naissance à vérifier
     * @return true si la date est valide, false sinon
     */
    public boolean isDateDeNaissanceValide(Calendar dateDeNaissance) {
        Calendar dateLimiteValidite = Calendar.getInstance();
        dateLimiteValidite.add(Calendar.MONTH, 9);
        dateLimiteValidite = DateUtils.truncate(dateLimiteValidite, Calendar.DAY_OF_MONTH);
        final Calendar dateNaissanceMois = DateUtils.truncate(dateDeNaissance, Calendar.DAY_OF_MONTH);
        boolean isDateDeNaissanceValide = false;
        // On vérifie que la date de naissance est antérieure à la date courante + 9 mois pour autoriser la date de naissance d'un futur nouveau né
        if (dateDeNaissance != null && (dateNaissanceMois.before(dateLimiteValidite) || dateNaissanceMois.equals(dateLimiteValidite))) {
            isDateDeNaissanceValide = true;
        }
        return isDateDeNaissanceValide;
    }

    /**
     * Permet de vérifier la nature du téléphone.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param telephone le téléphone
     * @param isPaysTelFrance indique si le pays du téléphone est la france
     * @param formatTelephone le format du numéro de téléphone
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierTelephoneFixe(final RapportDto rapport, final String message, final String erreurMessage, final String telephone,
        final boolean isPaysTelFrance, final String formatTelephone, final String propNom) {
        final String numeroTelephone = formatUtil.supprimerFormatNumTel(telephone);
        verifierFormatTelephone(rapport, message, erreurMessage, numeroTelephone, formatTelephone, propNom);
        boolean erreur = rapport.getRapports().get(propNom).getErreur();
        if (isPaysTelFrance) {
            erreur = erreur || numeroTelephone.startsWith("06") || numeroTelephone.startsWith("07");
        }
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Permet de vérifier un email.
     * @param rapport le rapport
     * @param message le message si pas d'erreur
     * @param erreurMessage le message d'erreur
     * @param adresse l'adresse
     * @param propNom le nom de la propriété
     * @return this pour chainage des appels
     */
    public ValidationExpressionUtil verifierAdresse(final RapportDto rapport, final String message, final String erreurMessage, final AdresseDto adresse,
        final String propNom) {
        verifierSiVide(rapport, message, erreurMessage, adresse.getNomVoie(), propNom);
        String libelleAdresse = adresse.getNumVoie();
        if (adresse.getTypeVoie() != null && adresse.getTypeVoie().getIdentifiant() != null) {
            if (libelleAdresse != null && !"".equals(libelleAdresse)) {
                libelleAdresse = libelleAdresse + " " + adresse.getTypeVoie().getLibelle();
            }
            else {
                libelleAdresse = adresse.getTypeVoie().getLibelle();
            }
        }
        if (libelleAdresse != null && !"".equals(libelleAdresse)) {
            libelleAdresse = libelleAdresse + " " + adresse.getNomVoie();
        }
        else {
            libelleAdresse = adresse.getNomVoie();
        }

        final boolean erreur = rapport.getRapports().get(propNom).getErreur() || libelleAdresse.length() > NB_CARACTERE_ADRESSE;
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Permet de vérifier le format d'un numéro de voie.
     * @param rapport le rapport le rapport.
     * @param message le message si pas d'erreur.
     * @param erreurMessage le message d'erreur.
     * @param numVoie le numéro de voie à analyser.
     * @param propNom le nom de la propriété.
     * @return this pour chainage des appels.
     */
    public ValidationExpressionUtil verifierFormatNumVoie(final RapportDto rapport, final String message, final String erreurMessage, final String numVoie,
        final String propNom) {
        verifierSiVide(rapport, message, erreurMessage, numVoie, propNom);
        final boolean erreur = rapport.getRapports().get(propNom).getErreur() || (!numVoie.toUpperCase().matches("^[\\d]{1,4}[A-Z]{0,1}$"));
        rapport.ajoutRapport(propNom, erreur ? erreurMessage : message, erreur);
        return this;
    }

    /**
     * Setter function.
     * @param formatUtil the formatUtil to set
     */
    public void setFormatUtil(FormatUtil formatUtil) {
        this.formatUtil = formatUtil;
    }

}
