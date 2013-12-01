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
package com.square.price.core.service.interfaces;

import java.util.List;


/**
 * Interface Utilitaire pour le mapping configuration via spring cf mapping-tarificateur.xml.
 * @author Goumard Stephane (stephane.goumard@scub.net).
 */
public interface TarificateurMappingService {
//    /**
//     * Retourne le mapping d'un operande.<br>
//     * Leve une exception si l'operande n'est pas presente dans la configuration.
//     * @param operande l'operande à mapper.
//     * @return le resultat du mapping.
//     */
//    String mapOperande(final String operande);
//
//    /**
//     * Retourne le mapping d'un champ_bareme_code_mapping en une propriete d'un pojo hibernate.<br>
//     * Leve une exception si le champ_bareme_code_mapping n'est pas present dans la configuration.
//     * @param champBaremeCodeMapping le champBareme à mapper.
//     * @return le resultat du mapping.
//     */
//    String mapBaremeCodeMappingHibernateProp(final String champBaremeCodeMapping);
//
//    /**
//     * Determine si le type du champ bareme et de type Integer.
//     * @param champBaremeCodeType le champBareme.
//     * @return true si Integer false sinon.
//     */
//    Boolean isMappingTypeInteger(final String champBaremeCodeType);
//
//    /**
//     * Determine si le type du champ bareme et de type String.
//     * @param champBaremeCodeType le champBareme.
//     * @return true si String false sinon.
//     */
//    Boolean isMappingTypeString(final String champBaremeCodeType);
//
//    /**
//     * Determine si le type du champ bareme et de type Long.
//     * @param champBaremeCodeType le champBareme.
//     * @return true si Long false sinon.
//     */
//    Boolean isMappingTypeLong(final String champBaremeCodeType);
//
//    /**
//     * Determine si le type du champ bareme et de type Float.
//     * @param champBaremeCodeType le champBareme.
//     * @return true si Float false sinon.
//     */
//    Boolean isMappingTypeFloat(final String champBaremeCodeType);
//
//    /**
//     * Determine si le type du champ bareme et de type Double.
//     * @param champBaremeCodeType le champBareme.
//     * @return true si Double false sinon.
//     */
//    Boolean isMappingTypeDouble(final String champBaremeCodeType);
//
//    /**
//     * Determine si le type du champ bareme et de type integer.
//     * @param champBaremeCodeType le champBareme.
//     * @return true si integer false sinon.
//     */
//    Boolean isMappingTypeDate(final String champBaremeCodeType);

    /**
     * Recuperer l'identifiant du critere ZoneTarifaire.
     * @return l'identifiant du critere zone tarifaire.
     */
    Integer getIdentifiantCritereZoneTarifaire();
//
//    /**
//     * Recuperer l'identifiant du critere age.
//     * @return l'identifiant du critere age.
//     */
//    Integer getIdentifiantCritereAge();
//
    /**
     * Recuperer l'identifiant du critere age max.
     * @return l'identifiant du critere age.
     */
    Integer getIdentifiantCritereAgeMax();

    /**
     * Recuperer l'identifiant du critere age min.
     * @return l'identifiant du critere age.
     */
    Integer getIdentifiantCritereAgeMin();

    /**
     * Recuperer l'identifiant du critere composition familial.
     * @return l'identifiant du critere composition familial.
     */
    Integer getIdentifiantCritereCompositionFamille();

    /**
     * Récuperer l'identifiant du critere prime souscrite.
     * @return l'identifiant du critere prime souscrite.
     */
    Integer getIdentifiantCriterePrimeSouscrite();

    /**
     * Récuperer l'identifiant du critere prime souscrite.
     * @return l'identifiant du critere prime souscrite.
     */
    Integer getIdentifiantCritereGarantieSouscrite();

    /**
     * Récuperer l'identifiant du critere lien famille.
     * @return l'identifiant du critere lien famille.
     */
    Integer getIdentifiantCritereLienFamille();

    /**
     * Récuperer l'identifiant du critere allocationHospi.
     * @return l'identifiant du critere capital.
     */
    Integer getIdentifiantCritereCapital();

    /**
     * Récuperer l'identifiant du critere taux.
     * @return l'identifiant du critere taux.
     */
    Integer getIdentifiantCritereTaux();

    /**
     * Récuperer l'identifiant du critere montant.
     * @return l'identifiant du critere montant.
     */
    Integer getIdentifiantCritereMontant();

    /**
     * Récuperer l'identifiant du critere montant.
     * @return l'identifiant du critere montant.
     */
    Integer getIdentifiantCritereMontantDeux();

    /**
     * Récuperer l'identifaint du critere date de naissance.
     * @return l'identifiant du critere date de naissance.
     */
    Integer getIdentifiantCritereDateNaissance();

    /**
     * Récuperer l'identifiant du critere duree du pret.
     * @return l'identifiant du critere duree du pret.
     */
    Integer getIdentifiantCritereDureePret();

    /**
     * Récuperer l'identifiant du critere capital risque.
     * @return l'identifiant du critere capital risque.
     */
    Integer getIdentifiantCritereCapitalRisque();

    /**
     * Récuperer l'identifiant de la valeur Deces Incapacite du critere capital risque.
     * @return l'identifiant de la valeur Deces Incapacite du critere capital risque.
     */
    Integer getIdentifiantDecesIncapaciteCritereCapitalRisque();
//
//    /**
//     * Récuperer l'identifiant du critere code apporteur.
//     * @return l'identifiant du critere paiement cotisation.
//     */
//    Integer getIdentifiantCritereCodeApporteur();

    /**
     * Récupérer l'identifiant du critère Taux de couverture.
     * @return l'identifiant du critère Taux de couverture
     */
    Integer getIdentifiantCritereTauxCouverture();
//
//    /**
//     * Récuperer l'identifiant du critere taux de gestion.
//     * @return le critere taux de gestion.
//     */
//    Integer getIdentifiantCritereCodeGestion();
//
    /**
     * Récuperer l'identifiant du critere Génération.
     * @return le critere Génération.
     */
    Integer getIdentifiantCritereGeneration();

    /**
     * Récuperer l'identifiant du critere Mois.
     * @return le critere Mois.
     */
    Integer getIdentifiantCritereMois();
//
//    /**
//     * Récuperer l'identifiant du produit caution.
//     * @return l'identifiant du produit caution.
//     */
//    Integer getIdentifiantProduitCaution();
//
//    /**
//     * Récuperer l'identifiant du produit caution.
//     * @return l'identifiant du produit caution.
//     */
//    Integer getIdentifiantProduitEmprunteur();
//
//    /**
//     * Récuperer l'identifiant du produit credit relais.
//     * @return l'identifiant du produit credit relais.
//     */
//    Integer getIdentifiantProduitCreditRelais();
//
//    /**
//     * Recuperer l'identifiant du produit droit associatif.
//     * @return l'identifiant du produit droit associatif.
//     */
//    Integer getIdentifiantProduitDroitAssociatif();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 16-30.
//     * @return l'identifiant du produit Santé 16-30.
//     */
//    Integer getIdentifiantProduitSante1630();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 16-30 avec chambre particulière.
//     * @return l'identifiant du produit Santé 16-30 avec chambre particulière.
//     */
//    Integer getIdentifiantProduitSante1630ChambreParticuliere();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 55-65.
//     * @return l'identifiant du produit Santé 55-65.
//     */
//    Integer getIdentifiantProduitSante5565();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 55-65 avec chambre particulière.
//     * @return l'identifiant du produit Santé 55-65.
//     */
//    Integer getIdentifiantProduitSante5565ChambreParticuliere();
//
//    /**
//     * Recuperer l'identifiant du produit Garantie Obsèques.
//     * @return l'identifiant du produit Garantie Obsèques.
//     */
//    Integer getIdentifiantProduitGarantieObseques();
//
//    /**
//     * Recuperer l'identifiant du produit Garantie Obsèques.
//     * @return l'identifiant du produit Garantie Obsèques.
//     */
//    Integer getIdentifiantProduitGarantieObsequesTempo10();
//
//    /**
//     * Recuperer l'identifiant du produit Garantie Obsèques.
//     * @return l'identifiant du produit Garantie Obsèques.
//     */
//    Integer getIdentifiantProduitGarantieObsequesTempo20();
//
//    /**
//     * Recuperer l'identifiant du produit Garantie Obsèques.
//     * @return l'identifiant du produit Garantie Obsèques.
//     */
//    Integer getIdentifiantProduitGarantieObsequesTempo30();
//
//    /**
//     * Recuperer l'identifiant du produit Garantie Obsèques.
//     * @return l'identifiant du produit Garantie Obsèques.
//     */
//    Integer getIdentifiantProduitGarantieObsequesViagere();
//
//    /**
//     * Recuperer l'identifiant du produit Garantie Obsèques Prime Unique.
//     * @return l'identifiant du produit Garantie Obsèques.
//     */
//    Integer getIdentifiantProduitGarantieObsequesPrimeUnique();
//
//    /**
//     * Recuperer l'identifiant du produit Garantie Obsèques.
//     * @return l'identifiant du produit Garantie Obsèques.
//     */
//    Integer getIdentifiantProduitGarantieObsequesMR();
//
//    /**
//     * Recuperer l'identifiant du produit Allocation hospitalière.
//     * @return l'identifiant du produit Allocation hospitalière.
//     */
//    Integer getIdentifiantProduitAllocationHospitaliere();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 70 Gamme Absolue.
//     * @return l'identifiant du produit Santé 70 Gamme Absolue.
//     */
//    Integer getIdentifiantProduitSante70GammeAbsolue();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 70 Gamme Inventive.
//     * @return l'identifiant du produit Santé 70 Gamme Inventive.
//     */
//    Integer getIdentifiantProduitSante70GammeInventive();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 70 R Gamme Absolue.
//     * @return l'identifiant du produit Santé 70 R Gamme Absolue.
//     */
//    Integer getIdentifiantProduitSante70RGammeAbsolue();
//
//    /**
//     * Recuperer l'identifiant du produit Santé 70 Gamme Inventive.
//     * @return l'identifiant du produit Santé 70 Gamme Inventive.
//     */
//    Integer getIdentifiantProduitSante70RGammeInventive();
//
//    /**
//     * Recuperer l'identifiant du produit Décès Individuel.
//     * @return l'identifiant du produit Décès Individuel.
//     */
//    Integer getIdentifiantProduitDecesIndividuel();
//
//    /**
//     * Recuperer l'identifiant du produit Décès Famille.
//     * @return l'identifiant du produit Décès Famille.
//     */
//    Integer getIdentifiantProduitDecesFamille();
//
//    /**
//     * Recuperer l'identifiant du produit Exonération.
//     * @return l'identifiant du produit Exonération.
//     */
//    Integer getIdentifiantProduitExoneration();
//
//    /**
//     * Recuperer l'identifiant du produit Exonération.
//     * @return l'identifiant du produit Exonération.
//     */
//    Integer getIdentifiantProduitExonerationNouvelleOffreDeuxMilleHuit();
//
//    /**
//     * Recuperer l'identifiant du produit Exonération.
//     * @return l'identifiant du produit Exonération.
//     */
//    Integer getIdentifiantProduitAssistanceNouvelleOffreDeuxMilleHuit();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 1.
//     * @return l'identifiant du produit Perso Evolution 1.
//     */
//    Integer getIdentifiantProduitPersoEvolution1();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 2.
//     * @return l'identifiant du produit Perso Evolution 2.
//     */
//    Integer getIdentifiantProduitPersoEvolution2();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 2 Plus.
//     * @return l'identifiant du produit Perso Evolution 2 Plus.
//     */
//    Integer getIdentifiantProduitPersoEvolution2Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 3.
//     * @return l'identifiant du produit Perso Evolution 3.
//     */
//    Integer getIdentifiantProduitPersoEvolution3();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 3 Plus.
//     * @return l'identifiant du produit Perso Evolution 3 Plus.
//     */
//    Integer getIdentifiantProduitPersoEvolution3Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 4.
//     * @return l'identifiant du produit Perso Evolution 4.
//     */
//    Integer getIdentifiantProduitPersoEvolution4();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 4 Plus.
//     * @return l'identifiant du produit Perso Evolution 4 Plus.
//     */
//    Integer getIdentifiantProduitPersoEvolution4Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 5.
//     * @return l'identifiant du produit Perso Evolution 5.
//     */
//    Integer getIdentifiantProduitPersoEvolution5();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 5 Plus.
//     * @return l'identifiant du produit Perso Evolution 5 Plus.
//     */
//    Integer getIdentifiantProduitPersoEvolution5Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 5 Banco.
//     * @return l'identifiant du produit Perso Evolution 5 Banco.
//     */
//    Integer getIdentifiantProduitPersoEvolution5Banco();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 5 Plus Banco.
//     * @return l'identifiant du produit Perso Evolution 5 Plus Banco.
//     */
//    Integer getIdentifiantProduitPersoEvolution5PlusBanco();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 5 Banco.
//     * @return l'identifiant du produit Perso Evolution 5 Banco.
//     */
//    Integer getIdentifiantProduitPersoEvolution5Banco2011();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 5 Plus Banco.
//     * @return l'identifiant du produit Perso Evolution 5 Plus Banco.
//     */
//    Integer getIdentifiantProduitPersoEvolution5PlusBanco2011();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 6.
//     * @return l'identifiant du produit Perso Evolution 6.
//     */
//    Integer getIdentifiantProduitPersoEvolution6();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 6 Plus.
//     * @return l'identifiant du produit Perso Evolution 6 Plus.
//     */
//    Integer getIdentifiantProduitPersoEvolution6Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 7.
//     * @return l'identifiant du produit Perso Evolution 7.
//     */
//    Integer getIdentifiantProduitPersoEvolution7();
//
//    /**
//     * Recuperer l'identifiant du produit Perso Evolution 7 Plus.
//     * @return l'identifiant du produit Perso Evolution 7 Plus.
//     */
//    Integer getIdentifiantProduitPersoEvolution7Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Evolution5();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Evolution5Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Evolution6();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Evolution6Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Evolution7();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Evolution7Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Famille();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1Senior();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus1SeniorPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Evolution5();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Evolution5Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Evolution6();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Evolution6Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Evolution7();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Evolution7Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Famille();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2Senior();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdentifiantProduitBonus2SeniorPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Jeune.
//     * @return l'identifiant du produit Jeune.
//     */
//    Integer getIdentifiantProduitJeune();
//
//    /**
//     * Recuperer l'identifiant du produit Jeune +.
//     * @return l'identifiant du produit Jeune +.
//     */
//    Integer getIdentifiantProduitJeunePlus();
//
//    /**
//     * Recuperer l'identifiant du produit Jeune Banco.
//     * @return l'identifiant du produit Jeune Banco.
//     */
//    Integer getIdentifiantProduitJeuneBanco();
//
//    /**
//     * Recuperer l'identifiant du produit Jeune Banco +.
//     * @return l'identifiant du produit Jeune Banco +.
//     */
//    Integer getIdentifiantProduitJeuneBancoPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Famille.
//     * @return l'identifiant du produit Famille.
//     */
//    Integer getIdentifiantProduitFamille();
//
//    /**
//     * Recuperer l'identifiant du produit Senior.
//     * @return l'identifiant du produit Senior.
//     */
//    Integer getIdentifiantProduitSenior();
//
//    /**
//     * Recuperer l'identifiant du produit Senior +.
//     * @return l'identifiant du produit Senior +.
//     */
//    Integer getIdentifiantProduitSeniorPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Senior Banco.
//     * @return l'identifiant du produit Senior Banco.
//     */
//    Integer getIdentifiantProduitSeniorBanco2011();
//
//    /**
//     * Recuperer l'identifiant du produit Senior Banco +.
//     * @return l'identifiant du produit Senior Banco +.
//     */
//    Integer getIdentifiantProduitSeniorBancoPlus2011();
//
//    /**
//     * Recuperer l'identifiant du produit Senior Banco.
//     * @return l'identifiant du produit Senior Banco.
//     */
//    Integer getIdentifiantProduitSeniorBanco();
//
//    /**
//     * Recuperer l'identifiant du produit Senior Banco +.
//     * @return l'identifiant du produit Senior Banco +.
//     */
//    Integer getIdentifiantProduitSeniorBancoPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 1 Senior Banco.
//     * @return l'identifiant du produit Bonus 1 Senior Banco.
//     */
//    Integer getIdentifiantProduitBonus1SeniorBanco();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 1 Senior Banco +.
//     * @return l'identifiant du produit Bonus 1 Senior Banco +.
//     */
//    Integer getIdentifiantProduitBonus1SeniorBancoPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 2 Senior Banco.
//     * @return l'identifiant du produit Bonus 2 Senior Banco.
//     */
//    Integer getIdentifiantProduitBonus2SeniorBanco();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 2 Senior Banco +.
//     * @return l'identifiant du produit Bonus 2 Senior Banco +.
//     */
//    Integer getIdentifiantProduitBonus2SeniorBancoPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 1 Evolution 5 Banco.
//     * @return l'identifiant du produit Bonus 1 Evolution 5 Banco.
//     */
//    Integer getIdentifiantProduitBonus1Evolution5Banco();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 2 Evolution 5 Banco.
//     * @return l'identifiant du produit Bonus 2 Evolution 5 Banco.
//     */
//    Integer getIdentifiantProduitBonus2Evolution5Banco();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 1 Evolution 5 Plus Banco.
//     * @return l'identifiant du produit Bonus 1 Evolution 5 Plus Banco.
//     */
//    Integer getIdentifiantProduitBonus1Evolution5PlusBanco();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus 2 Evolution 5 Banco.
//     * @return l'identifiant du produit Bonus 2 Evolution 5 Banco.
//     */
//    Integer getIdentifiantProduitBonus2Evolution5PlusBanco();
//
//    /**
//     * Recuperer l'identifiant du produit Assistance.
//     * @return l'identifiant du produit Assistance.
//     */
//    Integer getIdentifiantProduitAssistance();

    /**
     * Récuperer l'identifiant du reseau france.
     * @return l'identifiant du reseau france.
     */
    Integer getIdentifiantReseauFrance();
//
//    /**
//     * Récuperer l'identifiant du reseau courtage.
//     * @return l'identifiant du reseau courtage.
//     */
//    Integer getIdentifiantReseauCourtage();
//
//    /**
//     * Récuperer l'identifiant du reseau Smatis Innovation.
//     * @return l'identifiant du reseau smatis innovation.
//     */
//    Integer getIdentifiantReseauSmatisInnovation();
//
//    /**
//     * Récuperer l'identifiant du vetuste prospect.
//     * @return l'identifiant du vetuste prospect.
//     */
//    Integer getIdentifiantVetusteProspect();
//
//    /**
//     * Récuperer l'identifiant du vetuste portefeuille.
//     * @return l'identifiant du vetuste portefeuille.
//     */
//    Integer getIdentifiantVetustePortefeuille();

    /**
     * Récuperer l'identifiant du vetuste gamme ouvert a la vente.
     * @return l'identifiant du vetuste gamme ouvert a la vente
     */
    Integer getIdentifiantVetusteGammeOuvertVente();
//
//    /**
//     * Récuperer l'identifiant de la categorie gamme prevoyance.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantCategoriePrevoyance();

    /**
     * Récuperer l'identifiant de la categorie gamme sante.
     * @return l'identifiant.
     */
    Integer getIdentifiantCategorieSante();

    /**
     * Récuperer l'identifiant de la gestion individuelle.
     * @return l'identifiant.
     */
    Integer getIdentifiantGestionIndividuelle();
//
//    /**
//     * Récuperer l'identifiant de la gestion collective.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGestionCollective();
//
    /**
     * Récuperer l'identifiant de l'application gestcom.
     * @return l'identifiant de l'application gestcom.
     */
    Integer getIdentifiantApplicationGestcom();
//
//    /**
//     * Récuperer l'identifiant de l'application gestcom lite.
//     * @return l'identifiant de l'application gestcom lite.
//     */
//    Integer getIdentifiantApplicationGestcomLite();
//
//    /**
//     * Récuperer l'identifiant de l'application catalogue collectif.
//     * @return l'identifiant de l'application catalogue collectif.
//     */
//    Integer getIdentifiantApplicationCatalogueCollectif();;
//
//    /**
//     * Récuperer l'identifiant de l'application extranet courtage.
//     * @return l'identifiant de l'application gestcom.
//     */
//    Integer getIdentifiantApplicationExtranetCourtage();
//
//    /**
//     * Récuperer l'identifiant de la gamme InnovationPrevoyance.
//     * @return l'identifiant de la gamme innovation prevoyance.
//     */
//    Integer getIdentifiantGammeInnovationPrevoyance();
//
//    /**
//     * Récuperer l'identifiant de la gamme Prevoyance.
//     * @return l'identifiant de la gamme prevoyance.
//     */
//    Integer getIdentifiantGammePrevoyance();
//
//    /**
//     * Récuperer l'identifiant de la gamme Initiale.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeInitiale();
//
//    /**
//     * Récuperer l'identifiant de la gamme Inventive.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeInventive();
//
//    /**
//     * Récuperer l'identifiant de la gamme Inventive.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeIntegrale();
//
//    /**
//     * Récuperer l'identifiant de la gamme Smatis Jeune.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisJeune();
//
//    /**
//     * Récuperer l'identifiant de la gamme Smatis Famille.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisFamille();
//
//    /**
//     * Récuperer l'identifiant de la gamme Smatis Senior.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisSenior();
//
//    /**
//     * Récuperer l'identifiant de la gamme Smatis Perso.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisPerso();
//
//    /**
//     * Récuperer l'identifiant de la gamme Fidelisation.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisFidelisation();
//
//    /**
//     * Récuperer l'identifiant de la gamme Perso Fermée a la vente.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisPersoFermeeALaVente();
//
//    /**
//     * Récuperer l'identifiant de la gamme Jeune Fermée a la vente.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisJeuneFermeeALaVente();
//
//    /**
//     * Récuperer l'identifiant de la gamme Famille Fermée a la vente.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisFamilleFermeeALaVente();
//
//    /**
//     * Récuperer l'identifiant de la gamme Sénior Fermée a la vente.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSmatisSeniorFermeeALaVente();
//
//    /**
//     * Récuperer l'identifiant de la gamme Fidélisation Fermée a la vente.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeFidelisationFermeeALaVente();
//
//    /**
//     * Récuperer l'identifiant de la gamme Sociale.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeSociale();
//
//    /**
//     * Récuperer l'identifiant de la gamme InPack.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantGammeInPack();
//
    /**
     * Récuperer l'identifiant de la famille No.
     * @return l'identifiant.
     */
    Integer getIdentifiantFamilleNo();

    /**
     * Récuperer l'identifiant de la famille Bonus1.
     * @return l'identifiant.
     */
    Integer getIdentifiantFamilleBonus1();

    /**
     * Récuperer l'identifiant de la famille Bonus2.
     * @return l'identifiant.
     */
    Integer getIdentifiantFamilleBonus2();
//
//    /**
//     * Récuperer l'identifiant de la famille Allocation Hospitaliere.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantFamilleAllocationHospitaliere();
//
//    /**
//     * Récuperer l'identifiant de la famille Deces.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantFamilleDeces();
//
//    /**
//     * Récuperer l'identifiant de la famille Garantie Obseques.
//     * @return l'identifiant.
//     */
//    Integer getIdentifiantFamilleGarantieObseques();

    /**
     * Recuperer la valeur de la constate critere lien famille assure principal.
     * @return la valeur de la constante.
     */
    String getConstanteLienFamilleAssurePrincipal();

    /**
     * Recuperer la valeur de la constate critere lien famille conjoint.
     * @return la valeur de la constante.
     */
    String getConstanteLienFamilleConjoint();

    /**
     * Recuperer la valeur de la constate critere lien famille enfant.
     * @return la valeur de la constante.
     */
    String getConstanteLienFamilleEnfant();
//
//    /**
//     * Recuperer la valeur de la constante role courtier de l'application.
//     * @return le nom du role courtier de l'application.
//     */
//    String getConstanteRoleCourtierTarificateur();
//
//    /**
//     * Recuperer la valeur de la constante code tarification auto.
//     * @return la valeur de la constante.
//     */
//    @Deprecated
//    String getConstanteCodeTarificationBeneficiaire();

    /**
     * Recuperer la valeur de la constante code tarification non.
     * @return la valeur de la constante.
     */
    @Deprecated
    String getConstanteCodeTarificationAdherent();
//
//    /**
//     * Recuperer la valeur de la constante code tarification famille.
//     * @return la valeur de la constante.
//     */
//    @Deprecated
//    String getConstanteCodeTarificationFamille();
//
    /**
     * Récupère la valeur de la constante du type HTML d'une liste déroulante.
     * @return la valeur de la constante
     */
    String getConstanteTypeHTMLSelect();
//
//    /**
//     * Récupère la valeur de la constante du type HTML d'une textbox.
//     * @return la valeur de la constante
//     */
//    String getConstanteTypeHTMLText();

    /**
     * Récupère la valeur de la constante du type HTML d'un calendar.
     * @return la valeur de la constante
     */
    String getConstanteTypeHTMLCalendar();
//
//    /**
//     * Récupère la valeur de la constante "Ouverte à la vente".
//     * @return la valeur de la constante "Ouverte à la vente"
//     */
//    String getConstanteGarantieOuverteALaVente();
//
//    /**
//     * Récupère la valeur de la constante "Fermee à la vente".
//     * @return la valeur de la constante "Fermee à la vente"
//     */
//    String getConstanteGarantieFermeeALaVente();

    /**
     * Récupère l'identifiant du mode de paiement unique.
     * @return l'identifiant
     */
    Integer getIdentifiantModePaiementUnique();

    /**
     * Récupère l'identifiant du mode de paiement mensuel.
     * @return l'identifiant
     */
    Integer getIdentifiantModePaiementMensuel();
//
//    /**
//     * Récupère le code de la composition familiale "1 Adulte".
//     * @return le code de la composition familiale "1 Adulte"
//     */
//    String getCodeCompoFamUnAdulte();
//
//    /**
//     * Récupère le code de la composition familiale "2 Adultes".
//     * @return le code de la composition familiale "2 Adultes"
//     */
//    String getCodeCompoFamDeuxAdultes();
//
//    /**
//     * Récupère la valeur de la famille santé dans les baremes garanties.
//     * @return la valeur de la famille santé dans les baremes garanties
//     */
//    String getConstanteFamilleGarantieSante();
//
//    /**
//     * Récupère l'identifiant de vetuste "Fermée à la vente".
//     * @return l'identifiant.
//     */
//    Integer getIdVetusteFermeeALaVente();

    /**
     * Récupère la liste des identifiants des produits à exclure du moteur de règles.
     * @return la laiste des identifiants
     */
    List<Integer> getListeProduitsAExclureRegles();

    /**
     * Récupère la liste des identifiants des produits Bonus 1.
     * @return la liste des identifiants
     */
    List<Integer> getListeProduitsBonus1();

    /**
     * Récupère la liste des identifiants des produits Bonus 2.
     * @return la liste des identifiants
     */
    List<Integer> getListeProduitsBonus2();
//
//    /**
//     * Récupère la liste des identifiants des produits Garantie Obsèques.
//     * @return la liste des identifiants
//     */
//    List<Integer> getListeProduitsGarantieObseques();
//
//    /**
//     * Récupère la liste des identifiants des gammes "SIKI Gamme" pour les courtiers.
//     * @return la liste des identifiants
//     */
//    List<Integer> getListeGammesCourtierSiki();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution5();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution5Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution6();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution6Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution7();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution7Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution50();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution50Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution60();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution60Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution70();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierEvolution70Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierFamille();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierInnovFamille();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierSenior();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierSeniorPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierInnovSenior();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus1CourtierInnovSeniorPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution5();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution5Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution6();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution6Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution7();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution7Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution50();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution50Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution60();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution60Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution70();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierEvolution70Plus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierFamille();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierInnovFamille();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierSenior();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierSeniorPlus();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierInnovSenior();
//
//    /**
//     * Recuperer l'identifiant du produit Bonus.
//     * @return l'identifiant du produit Bonus.
//     */
//    Integer getIdProduitBonus2CourtierInnovSeniorPlus();
//
//    /**
//     * Récupère l'identifiant du produit bonus 1 fidélité 5+.
//     * @return l'identifiant du produit.
//     */
//    Integer getIdentifiantProduitBonus1Fidelite5Plus();
//
//    /**
//     * Récupère l'identifiant du produit bonus 2 fidélité 5+.
//     * @return l'identifiant du produit.
//     */
//    Integer getIdentifiantProduitBonus2Fidelite5Plus();
//
//    /**
//     * Récupère la liste des identifiants des produits Bonus 1 courtier.
//     * @return la liste des identifiants
//     */
//    List<Integer> getListeProduitsBonus1Courtier();
//
//    /**
//     * Récupère la liste des identifiants des produits Bonus 2 courtier.
//     * @return la liste des identifiants
//     */
//    List<Integer> getListeProduitsBonus2Courtier();

    /**
     * Récupère le nom de la propriété "Ordre affichage" du modèle "GammeProduit".
     * @return le nom de la propriété
     */
    String getProprieteGammeProduitOrdreAffichage();
//
//    /**
//     * Recupère l'identifiant du produit + d'un produit.
//     * @param idProduit l'identifiant du produit.
//     * @return l'identifiant du produit +.
//     */
//    Integer getProduitPlus(Integer idProduit);
//
//    /**
//     * Recupère l'identifiant du produit à partir d'un produit +.
//     * @param idProduit l'identifiant du produit +.
//     * @return l'identifiant du produit.
//     */
//    Integer getProduitByProduitPlus(Integer idProduit);
//
//    /**
//     * Récupère la liste des identifiants des produits avec chambre particulière.
//     * @return la liste des identifiants
//     */
//    List<Integer> getListeProduitsChambreParticuliere();
//
//    /**
//     * Récupère le nom de la propriété "Ordre affichage" du modèle "CategorieGamme".
//     * @return le nom de la propriété
//     */
//    String getProprieteCategorieGammeOrdreAffichage();
//
    /**
     * Récupère l'identifiant du mode de tarification pour les bénéficiaires.
     * @return l'identifiant.
     */
    Long getConstanteIdModeTarificationBeneficiaire();

    /**
     * Récupère l'identifiant du mode de tarification pour les adhérents.
     * @return l'identifiant.
     */
    Long getConstanteIdModeTarificationAdherent();

    /**
     * Récupère l'identifiant du mode de tarification pour les familles.
     * @return l'identifiant.
     */
    Integer getConstanteIdModeTarificationFamille();
//
//    /**
//     * Récupère l'identifiant du bareme PMSS.
//     * @return l'identifiant.
//     */
//    String getIdentifiantBaremePMSS();
}
