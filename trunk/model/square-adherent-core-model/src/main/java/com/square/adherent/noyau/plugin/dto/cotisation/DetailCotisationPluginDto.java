package com.square.adherent.noyau.plugin.dto.cotisation;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe de retour AIA.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class DetailCotisationPluginDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -9059829423813263970L;

    /** Identifiant de la garantie. */
    private String identifiantGarantie;

    /** Libelle de la garantie. */
    private String libelleGarantie;

    /** Libelle du détail. */
    private String libelle;

    /** Montant. */
    private Float montant;

    /** Identifiant du bénéficiaire. */
    private String identifiantBeneficiaire;

    /** Echeance. */
    private String typeEcheance;

    /** Type de la prime. */
    private String typePrime;

    /** Date debut. */
    private Calendar dateDebut;

    /** Date fin. */
    private Calendar dateFin;

    /** Eid du contrat. */
    private String contratEid;

    /**
     * Get the identifiantGarantie value.
     * @return the identifiantGarantie
     */
    public String getIdentifiantGarantie() {
        return identifiantGarantie;
    }

    /**
     * Set the identifiantGarantie value.
     * @param identifiantGarantie the identifiantGarantie to set
     */
    public void setIdentifiantGarantie(String identifiantGarantie) {
        this.identifiantGarantie = identifiantGarantie;
    }

    /**
     * Get the libelleGarantie value.
     * @return the libelleGarantie
     */
    public String getLibelleGarantie() {
        return libelleGarantie;
    }

    /**
     * Set the libelleGarantie value.
     * @param libelleGarantie the libelleGarantie to set
     */
    public void setLibelleGarantie(String libelleGarantie) {
        this.libelleGarantie = libelleGarantie;
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the montant value.
     * @return the montant
     */
    public Float getMontant() {
        return montant;
    }

    /**
     * Set the montant value.
     * @param montant the montant to set
     */
    public void setMontant(Float montant) {
        this.montant = montant;
    }

    /**
     * Get the identifiantBeneficiaire value.
     * @return the identifiantBeneficiaire
     */
    public String getIdentifiantBeneficiaire() {
        return identifiantBeneficiaire;
    }

    /**
     * Set the identifiantBeneficiaire value.
     * @param identifiantBeneficiaire the identifiantBeneficiaire to set
     */
    public void setIdentifiantBeneficiaire(String identifiantBeneficiaire) {
        this.identifiantBeneficiaire = identifiantBeneficiaire;
    }

    /**
     * Get the typePrime value.
     * @return the typePrime
     */
    public String getTypePrime() {
        return typePrime;
    }

    /**
     * Set the typePrime value.
     * @param typePrime the typePrime to set
     */
    public void setTypePrime(String typePrime) {
        this.typePrime = typePrime;
    }

    /**
     * Get the dateDebut value.
     * @return the dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Set the dateDebut value.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Get the dateFin value.
     * @return the dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Set the dateFin value.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Get the typeEcheance value.
     * @return the typeEcheance
     */
    public String getTypeEcheance() {
        return typeEcheance;
    }

    /**
     * Set the typeEcheance value.
     * @param typeEcheance the typeEcheance to set
     */
    public void setTypeEcheance(String typeEcheance) {
        this.typeEcheance = typeEcheance;
    }

    /**
     * Get the contratEid value.
     * @return the contratEid
     */
    public String getContratEid() {
        return contratEid;
    }

    /**
     * Set the contratEid value.
     * @param contratEid the contratEid to set
     */
    public void setContratEid(String contratEid) {
        this.contratEid = contratEid;
    }
}
