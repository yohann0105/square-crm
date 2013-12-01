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
package com.square.composant.tarificateur.square.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model permettant de centraliser les constantes nécessaires à l'application.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ConstantesModel implements IsSerializable {

    /** Identifiant du critère Mois. */
    private Integer idCritereMois;

    /** Identifiant du critère Generation. */
    private Integer idCritereGeneration;

    /** Identifiant du critère Age Min. */
    private Integer idCritereAgeMin;

    /** Identifiant du critère Age Max. */
    private Integer idCritereAgeMax;

    /** Identifiant mode paiement mensuel. */
    private Integer idModePaiementMensuel;

    /** Identifiant mode paiement unique. */
    private Integer idModePaiementUnique;

    /** Identifiant de la finalité non renseignée. */
    private Long idFinaliteNonRenseignee;

    /** Identifiant de la finalité en cours. */
    private Long idFinaliteEnCours;

    /** Identifiant de la finalité refusée. */
    private Long idFinaliteRefusee;

    /** Identifiant de la finalité acceptée. */
    private Long idFinaliteAcceptee;

    /** Identifiant de la finalité corbeille. */
    private Long idFinaliteCorbeille;

    /** Identifiant de la finalité transformée. */
    private Long idFinaliteTransformee;

    /** Identifiant du critère "Montant min". */
    private Integer idCritereMontantMin;

    /** Identifiant du critère "Montant max". */
    private Integer idCritereMontantMax;

    /** Identifiant de la gamme Smatis Innovation. */
    private String constanteTypeSantePrevoyance;

    /** Constante du type select. */
    private String constanteTypeHTMLSelect;

    /** Type Calendar. */
    private String constanteTypeCalendar;

    /** Constante code tarification. */
    private String codeTarificationAdherent;

    /** Identifiant de la famille bonus 1. */
    private Integer identifiantFamilleBonus1;

    /** Identifiant de la famille bonus 2. */
    private Integer identifiantFamilleBonus2;

    /** Nb d'années pour le bonus 1. */
    private Integer nbAnneesBonus1;

    /** Nb d'années pour le bonus 2. */
    private Integer nbAnneesBonus2;

    /** Moyen de paiement par prélevement. */
    private Long idMoyenPaiementPrelevement;

    /** Moyen de paiement par CB. */
    private Long idMoyenPaiementCarteBancaire;

    /** Type Neutre du devis. */
    private String typeNeutre;

    /** Gamme ouvert à la vente. */
    private Integer identifiantVetusteGammeOuvertVente;

    /** Categorie sante. */
    private Integer identifiantCategorieSante;

    /** Id lien familial assuré principal. */
    private Long idLienFamilialAssurePrincipal;

    /** Id lien familial conjoint. */
    private Long idLienFamilialConjoint;

    /** Id lien familial enfant. */
    private Long idLienFamilialEnfant;

    /** Identifiant du modèle de devis "BA adhesion en ligne". */
    private Long idModeleDevisBulletinAdhesion;

    /** Identifiant du modèle de devis "BA individuel". */
    private Long idModeleDevisFicheTransfert;

    /** Identifiant du modèle de devis "". */
    private Long idModeleLettreAnnulation;

    /** Identifiant du modèle de lettre de radiation. */
    private Long idModeleLettreRadiation;

    /** Identifiant du modèle de lettre de radiation par loi Chatel. */
    private Long idModeleLettreRadiationLoiChatel;

    /** Reseau par défaut. */
    private IntegerLibelleModel reseauGammeDefaut;

    /** Type de gamme par défaut. */
    private IntegerLibelleModel typeGammeDefaut;

    /** Catégorie par défaut. */
    private IntegerLibelleModel categorieGammeDefaut;

    /** Motif devis par défaut. */
    private IdentifiantLibelleGwt motifDevisDefaut;

    /** Constante groupement famille. */
    private Long idGroupementFamille;

    /** Identifiant du type de relation conjoint. */
    private Long idTypeRelationConjoint;

    /** Identifiant du type de relation enfant. */
    private Long idTypeRelationEnfant;

    /**
     * Get the identifiantFamilleBonus1 value.
     * @return the identifiantFamilleBonus1
     */
    public Integer getIdentifiantFamilleBonus1() {
        return identifiantFamilleBonus1;
    }

    /**
     * Set the identifiantFamilleBonus1 value.
     * @param identifiantFamilleBonus1 the identifiantFamilleBonus1 to set
     */
    public void setIdentifiantFamilleBonus1(Integer identifiantFamilleBonus1) {
        this.identifiantFamilleBonus1 = identifiantFamilleBonus1;
    }

    /**
     * Get the identifiantFamilleBonus2 value.
     * @return the identifiantFamilleBonus2
     */
    public Integer getIdentifiantFamilleBonus2() {
        return identifiantFamilleBonus2;
    }

    /**
     * Set the identifiantFamilleBonus2 value.
     * @param identifiantFamilleBonus2 the identifiantFamilleBonus2 to set
     */
    public void setIdentifiantFamilleBonus2(Integer identifiantFamilleBonus2) {
        this.identifiantFamilleBonus2 = identifiantFamilleBonus2;
    }

    /**
     * Get the idFinaliteRefusee value.
     * @return the idFinaliteRefusee
     */
    public Long getIdFinaliteRefusee() {
        return idFinaliteRefusee;
    }

    /**
     * Set the idFinaliteRefusee value.
     * @param idFinaliteRefusee the idFinaliteRefusee to set
     */
    public void setIdFinaliteRefusee(Long idFinaliteRefusee) {
        this.idFinaliteRefusee = idFinaliteRefusee;
    }

    /**
     * Get the idFinaliteAcceptee value.
     * @return the idFinaliteAcceptee
     */
    public Long getIdFinaliteAcceptee() {
        return idFinaliteAcceptee;
    }

    /**
     * Set the idFinaliteAcceptee value.
     * @param idFinaliteAcceptee the idFinaliteAcceptee to set
     */
    public void setIdFinaliteAcceptee(Long idFinaliteAcceptee) {
        this.idFinaliteAcceptee = idFinaliteAcceptee;
    }

    /**
     * Get the idCritereAgeMin value.
     * @return the idCritereAgeMin
     */
    public Integer getIdCritereAgeMin() {
        return idCritereAgeMin;
    }

    /**
     * Set the idCritereAgeMin value.
     * @param idCritereAgeMin the idCritereAgeMin to set
     */
    public void setIdCritereAgeMin(Integer idCritereAgeMin) {
        this.idCritereAgeMin = idCritereAgeMin;
    }

    /**
     * Get the idFinaliteNonRenseignee value.
     * @return the idFinaliteNonRenseignee
     */
    public Long getIdFinaliteNonRenseignee() {
        return idFinaliteNonRenseignee;
    }

    /**
     * Set the idFinaliteNonRenseignee value.
     * @param idFinaliteNonRenseignee the idFinaliteNonRenseignee to set
     */
    public void setIdFinaliteNonRenseignee(Long idFinaliteNonRenseignee) {
        this.idFinaliteNonRenseignee = idFinaliteNonRenseignee;
    }

    /**
     * Get the idCritereMontantMin value.
     * @return the idCritereMontantMin
     */
    public Integer getIdCritereMontantMin() {
        return idCritereMontantMin;
    }

    /**
     * Set the idCritereMontantMin value.
     * @param idCritereMontantMin the idCritereMontantMin to set
     */
    public void setIdCritereMontantMin(Integer idCritereMontantMin) {
        this.idCritereMontantMin = idCritereMontantMin;
    }

    /**
     * Get the idCritereMontantMax value.
     * @return the idCritereMontantMax
     */
    public Integer getIdCritereMontantMax() {
        return idCritereMontantMax;
    }

    /**
     * Set the idCritereMontantMax value.
     * @param idCritereMontantMax the idCritereMontantMax to set
     */
    public void setIdCritereMontantMax(Integer idCritereMontantMax) {
        this.idCritereMontantMax = idCritereMontantMax;
    }

    /**
     * Get the idCritereAgeMax value.
     * @return the idCritereAgeMax
     */
    public Integer getIdCritereAgeMax() {
        return idCritereAgeMax;
    }

    /**
     * Set the idCritereAgeMax value.
     * @param idCritereAgeMax the idCritereAgeMax to set
     */
    public void setIdCritereAgeMax(Integer idCritereAgeMax) {
        this.idCritereAgeMax = idCritereAgeMax;
    }

    /**
     * Get the idModePaiementMensuel value.
     * @return the idModePaiementMensuel
     */
    public Integer getIdModePaiementMensuel() {
        return idModePaiementMensuel;
    }

    /**
     * Set the idModePaiementMensuel value.
     * @param idModePaiementMensuel the idModePaiementMensuel to set
     */
    public void setIdModePaiementMensuel(Integer idModePaiementMensuel) {
        this.idModePaiementMensuel = idModePaiementMensuel;
    }

    /**
     * Get the idModePaiementUnique value.
     * @return the idModePaiementUnique
     */
    public Integer getIdModePaiementUnique() {
        return idModePaiementUnique;
    }

    /**
     * Set the idModePaiementUnique value.
     * @param idModePaiementUnique the idModePaiementUnique to set
     */
    public void setIdModePaiementUnique(Integer idModePaiementUnique) {
        this.idModePaiementUnique = idModePaiementUnique;
    }

    /**
     * Get the constanteTypeSantePrevoyance value.
     * @return the constanteTypeSantePrevoyance
     */
    public String getConstanteTypeSantePrevoyance() {
        return constanteTypeSantePrevoyance;
    }

    /**
     * Set the constanteTypeSantePrevoyance value.
     * @param constanteTypeSantePrevoyance the constanteTypeSantePrevoyance to set
     */
    public void setConstanteTypeSantePrevoyance(String constanteTypeSantePrevoyance) {
        this.constanteTypeSantePrevoyance = constanteTypeSantePrevoyance;
    }

    /**
     * Get the constanteTypeHTMLSelect value.
     * @return the constanteTypeHTMLSelect
     */
    public String getConstanteTypeHTMLSelect() {
        return constanteTypeHTMLSelect;
    }

    /**
     * Set the constanteTypeHTMLSelect value.
     * @param constanteTypeHTMLSelect the constanteTypeHTMLSelect to set
     */
    public void setConstanteTypeHTMLSelect(String constanteTypeHTMLSelect) {
        this.constanteTypeHTMLSelect = constanteTypeHTMLSelect;
    }

    /**
     * Get the constanteTypeCalendar value.
     * @return the constanteTypeCalendar
     */
    public String getConstanteTypeCalendar() {
        return constanteTypeCalendar;
    }

    /**
     * Set the constanteTypeCalendar value.
     * @param constanteTypeCalendar the constanteTypeCalendar to set
     */
    public void setConstanteTypeCalendar(String constanteTypeCalendar) {
        this.constanteTypeCalendar = constanteTypeCalendar;
    }

    /**
     * Get the codeTarificationAdherent value.
     * @return the codeTarificationAdherent
     */
    public String getCodeTarificationAdherent() {
        return codeTarificationAdherent;
    }

    /**
     * Set the codeTarificationAdherent value.
     * @param codeTarificationAdherent the codeTarificationAdherent to set
     */
    public void setCodeTarificationAdherent(String codeTarificationAdherent) {
        this.codeTarificationAdherent = codeTarificationAdherent;
    }

    /**
     * Get the idCritereMois value.
     * @return the idCritereMois
     */
    public Integer getIdCritereMois() {
        return idCritereMois;
    }

    /**
     * Set the idCritereMois value.
     * @param idCritereMois the idCritereMois to set
     */
    public void setIdCritereMois(Integer idCritereMois) {
        this.idCritereMois = idCritereMois;
    }

    /**
     * Get the idCritereGeneration value.
     * @return the idCritereGeneration
     */
    public Integer getIdCritereGeneration() {
        return idCritereGeneration;
    }

    /**
     * Set the idCritereGeneration value.
     * @param idCritereGeneration the idCritereGeneration to set
     */
    public void setIdCritereGeneration(Integer idCritereGeneration) {
        this.idCritereGeneration = idCritereGeneration;
    }

    /**
     * Get the nbAnneesBonus1 value.
     * @return the nbAnneesBonus1
     */
    public Integer getNbAnneesBonus1() {
        return nbAnneesBonus1;
    }

    /**
     * Set the nbAnneesBonus1 value.
     * @param nbAnneesBonus1 the nbAnneesBonus1 to set
     */
    public void setNbAnneesBonus1(Integer nbAnneesBonus1) {
        this.nbAnneesBonus1 = nbAnneesBonus1;
    }

    /**
     * Get the nbAnneesBonus2 value.
     * @return the nbAnneesBonus2
     */
    public Integer getNbAnneesBonus2() {
        return nbAnneesBonus2;
    }

    /**
     * Set the nbAnneesBonus2 value.
     * @param nbAnneesBonus2 the nbAnneesBonus2 to set
     */
    public void setNbAnneesBonus2(Integer nbAnneesBonus2) {
        this.nbAnneesBonus2 = nbAnneesBonus2;
    }

    /**
     * Get the idFinaliteCorbeille value.
     * @return the idFinaliteCorbeille
     */
    public Long getIdFinaliteCorbeille() {
        return idFinaliteCorbeille;
    }

    /**
     * Set the idFinaliteCorbeille value.
     * @param idFinaliteCorbeille the idFinaliteCorbeille to set
     */
    public void setIdFinaliteCorbeille(Long idFinaliteCorbeille) {
        this.idFinaliteCorbeille = idFinaliteCorbeille;
    }

    /**
     * Get the idMoyenPaiementPrelevement value.
     * @return the idMoyenPaiementPrelevement
     */
    public Long getIdMoyenPaiementPrelevement() {
        return idMoyenPaiementPrelevement;
    }

    /**
     * Set the idMoyenPaiementPrelevement value.
     * @param idMoyenPaiementPrelevement the idMoyenPaiementPrelevement to set
     */
    public void setIdMoyenPaiementPrelevement(Long idMoyenPaiementPrelevement) {
        this.idMoyenPaiementPrelevement = idMoyenPaiementPrelevement;
    }

    /**
     * Get the idMoyenPaiementCarteBancaire value.
     * @return the idMoyenPaiementCarteBancaire
     */
    public Long getIdMoyenPaiementCarteBancaire() {
        return idMoyenPaiementCarteBancaire;
    }

    /**
     * Set the idMoyenPaiementCarteBancaire value.
     * @param idMoyenPaiementCarteBancaire the idMoyenPaiementCarteBancaire to set
     */
    public void setIdMoyenPaiementCarteBancaire(Long idMoyenPaiementCarteBancaire) {
        this.idMoyenPaiementCarteBancaire = idMoyenPaiementCarteBancaire;
    }

    /**
     * Get the idFinaliteTransformee value.
     * @return the idFinaliteTransformee
     */
    public Long getIdFinaliteTransformee() {
        return idFinaliteTransformee;
    }

    /**
     * Set the idFinaliteTransformee value.
     * @param idFinaliteTransformee the idFinaliteTransformee to set
     */
    public void setIdFinaliteTransformee(Long idFinaliteTransformee) {
        this.idFinaliteTransformee = idFinaliteTransformee;
    }

    /**
     * Get the typeNeutre value.
     * @return the typeNeutre
     */
    public String getTypeNeutre() {
        return typeNeutre;
    }

    /**
     * Set the typeNeutre value.
     * @param typeNeutre the typeNeutre to set
     */
    public void setTypeNeutre(String typeNeutre) {
        this.typeNeutre = typeNeutre;
    }

    /**
     * Get the identifiantVetusteGammeOuvertVente value.
     * @return the identifiantVetusteGammeOuvertVente
     */
    public Integer getIdentifiantVetusteGammeOuvertVente() {
        return identifiantVetusteGammeOuvertVente;
    }

    /**
     * Set the identifiantVetusteGammeOuvertVente value.
     * @param identifiantVetusteGammeOuvertVente the identifiantVetusteGammeOuvertVente to set
     */
    public void setIdentifiantVetusteGammeOuvertVente(Integer identifiantVetusteGammeOuvertVente) {
        this.identifiantVetusteGammeOuvertVente = identifiantVetusteGammeOuvertVente;
    }

    /**
     * Get the identifiantCategorieSante value.
     * @return the identifiantCategorieSante
     */
    public Integer getIdentifiantCategorieSante() {
        return identifiantCategorieSante;
    }

    /**
     * Set the identifiantCategorieSante value.
     * @param identifiantCategorieSante the identifiantCategorieSante to set
     */
    public void setIdentifiantCategorieSante(Integer identifiantCategorieSante) {
        this.identifiantCategorieSante = identifiantCategorieSante;
    }

    /**
     * Getter function.
     * @return the idLienFamilialAssurePrincipal
     */
    public Long getIdLienFamilialAssurePrincipal() {
        return idLienFamilialAssurePrincipal;
    }

    /**
     * Getter function.
     * @return the idLienFamilialConjoint
     */
    public Long getIdLienFamilialConjoint() {
        return idLienFamilialConjoint;
    }

    /**
     * Getter function.
     * @return the idLienFamilialEnfant
     */
    public Long getIdLienFamilialEnfant() {
        return idLienFamilialEnfant;
    }

    /**
     * Setter function.
     * @param idLienFamilialAssurePrincipal the idLienFamilialAssurePrincipal to set
     */
    public void setIdLienFamilialAssurePrincipal(Long idLienFamilialAssurePrincipal) {
        this.idLienFamilialAssurePrincipal = idLienFamilialAssurePrincipal;
    }

    /**
     * Setter function.
     * @param idLienFamilialConjoint the idLienFamilialConjoint to set
     */
    public void setIdLienFamilialConjoint(Long idLienFamilialConjoint) {
        this.idLienFamilialConjoint = idLienFamilialConjoint;
    }

    /**
     * Setter function.
     * @param idLienFamilialEnfant the idLienFamilialEnfant to set
     */
    public void setIdLienFamilialEnfant(Long idLienFamilialEnfant) {
        this.idLienFamilialEnfant = idLienFamilialEnfant;
    }

    /**
     * Récupère la valeur de idModeleDevisBulletinAdhesion.
     * @return la valeur de idModeleDevisBulletinAdhesion
     */
    public Long getIdModeleDevisBulletinAdhesion() {
        return idModeleDevisBulletinAdhesion;
    }

    /**
     * Définit la valeur de idModeleDevisBulletinAdhesion.
     * @param idModeleDevisBulletinAdhesion la nouvelle valeur de idModeleDevisBulletinAdhesion
     */
    public void setIdModeleDevisBulletinAdhesion(Long idModeleDevisBulletinAdhesion) {
        this.idModeleDevisBulletinAdhesion = idModeleDevisBulletinAdhesion;
    }

    /**
     * Récupère la valeur de idModeleDevisFicheTransfert.
     * @return la valeur de idModeleDevisFicheTransfert
     */
    public Long getIdModeleDevisFicheTransfert() {
        return idModeleDevisFicheTransfert;
    }

    /**
     * Définit la valeur de idModeleDevisFicheTransfert.
     * @param idModeleDevisFicheTransfert la nouvelle valeur de idModeleDevisFicheTransfert
     */
    public void setIdModeleDevisFicheTransfert(Long idModeleDevisFicheTransfert) {
        this.idModeleDevisFicheTransfert = idModeleDevisFicheTransfert;
    }

    /**
     * Récupère la valeur de idModeleLettreAnnulation.
     * @return la valeur de idModeleLettreAnnulation
     */
    public Long getIdModeleLettreAnnulation() {
        return idModeleLettreAnnulation;
    }

    /**
     * Définit la valeur de idModeleLettreAnnulation.
     * @param idModeleLettreAnnulation la nouvelle valeur de idModeleLettreAnnulation
     */
    public void setIdModeleLettreAnnulation(Long idModeleLettreAnnulation) {
        this.idModeleLettreAnnulation = idModeleLettreAnnulation;
    }

    /**
     * Get the reseauGammeDefaut value.
     * @return the reseauGammeDefaut
     */
    public IntegerLibelleModel getReseauGammeDefaut() {
        return reseauGammeDefaut;
    }

    /**
     * Set the reseauGammeDefaut value.
     * @param reseauGammeDefaut the reseauGammeDefaut to set
     */
    public void setReseauGammeDefaut(IntegerLibelleModel reseauGammeDefaut) {
        this.reseauGammeDefaut = reseauGammeDefaut;
    }

    /**
     * Get the typeGammeDefaut value.
     * @return the typeGammeDefaut
     */
    public IntegerLibelleModel getTypeGammeDefaut() {
        return typeGammeDefaut;
    }

    /**
     * Set the typeGammeDefaut value.
     * @param typeGammeDefaut the typeGammeDefaut to set
     */
    public void setTypeGammeDefaut(IntegerLibelleModel typeGammeDefaut) {
        this.typeGammeDefaut = typeGammeDefaut;
    }

    /**
     * Get the categorieGammeDefaut value.
     * @return the categorieGammeDefaut
     */
    public IntegerLibelleModel getCategorieGammeDefaut() {
        return categorieGammeDefaut;
    }

    /**
     * Set the categorieGammeDefaut value.
     * @param categorieGammeDefaut the categorieGammeDefaut to set
     */
    public void setCategorieGammeDefaut(IntegerLibelleModel categorieGammeDefaut) {
        this.categorieGammeDefaut = categorieGammeDefaut;
    }

    /**
     * Get the motifDevisDefaut value.
     * @return the motifDevisDefaut
     */
    public IdentifiantLibelleGwt getMotifDevisDefaut() {
        return motifDevisDefaut;
    }

    /**
     * Set the motifDevisDefaut value.
     * @param motifDevisDefaut the motifDevisDefaut to set
     */
    public void setMotifDevisDefaut(IdentifiantLibelleGwt motifDevisDefaut) {
        this.motifDevisDefaut = motifDevisDefaut;
    }

    /**
     * Set the idGroupementFamille value.
     * @param idGroupementFamille the idGroupementFamille to set
     */
	public void setIdGroupementFamille(Long idGroupementFamille) {
		this.idGroupementFamille = idGroupementFamille;
	}

    /**
     * Get the idGroupementFamille value.
     * @return the idGroupementFamille
     */
	public Long getIdGroupementFamille() {
		return idGroupementFamille;
	}

    /**
     * Renvoi la valeur de idTypeRelationConjoint.
     * @return idTypeRelationConjoint
     */
    public Long getIdTypeRelationConjoint() {
        return idTypeRelationConjoint;
    }

    /**
     * Modifie idTypeRelationConjoint.
     * @param idTypeRelationConjoint la nouvelle valeur de idTypeRelationConjoint
     */
    public void setIdTypeRelationConjoint(Long idTypeRelationConjoint) {
        this.idTypeRelationConjoint = idTypeRelationConjoint;
    }

    /**
     * Renvoi la valeur de idTypeRelationEnfant.
     * @return idTypeRelationEnfant
     */
    public Long getIdTypeRelationEnfant() {
        return idTypeRelationEnfant;
    }

    /**
     * Modifie idTypeRelationEnfant.
     * @param idTypeRelationEnfant la nouvelle valeur de idTypeRelationEnfant
     */
    public void setIdTypeRelationEnfant(Long idTypeRelationEnfant) {
        this.idTypeRelationEnfant = idTypeRelationEnfant;
    }

    /**
     * Get the value of idFinaliteEnCours.
     * @return the idFinaliteEnCours
     */
    public Long getIdFinaliteEnCours() {
        return idFinaliteEnCours;
    }

    /**
     * Set the value of idFinaliteEnCours.
     * @param idFinaliteEnCours the idFinaliteEnCours to set
     */
    public void setIdFinaliteEnCours(Long idFinaliteEnCours) {
        this.idFinaliteEnCours = idFinaliteEnCours;
    }

	/**
	 * Renvoie la valeur de idModeleLettreRadiation.
	 * @return idModeleLettreRadiation
	 */
	public Long getIdModeleLettreRadiation() {
		return idModeleLettreRadiation;
	}

	/**
	 * Modifie idModeleLettreRadiation.
	 * @param idModeleLettreRadiation la nouvelle valeur de idModeleLettreRadiation
	 */
	public void setIdModeleLettreRadiation(Long idModeleLettreRadiation) {
		this.idModeleLettreRadiation = idModeleLettreRadiation;
	}

	/**
	 * Renvoie la valeur de idModeleLettreRadiationLoiChatel.
	 * @return idModeleLettreRadiationLoiChatel
	 */
	public Long getIdModeleLettreRadiationLoiChatel() {
		return idModeleLettreRadiationLoiChatel;
	}

	/**
	 * Modifie idModeleLettreRadiationLoiChatel.
	 * @param idModeleLettreRadiationLoiChatel la nouvelle valeur de idModeleLettreRadiationLoiChatel
	 */
	public void setIdModeleLettreRadiationLoiChatel(
			Long idModeleLettreRadiationLoiChatel) {
		this.idModeleLettreRadiationLoiChatel = idModeleLettreRadiationLoiChatel;
	}

}
