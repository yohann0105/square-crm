package com.square.adherent.noyau.plugin.dto.cotisation;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Critere de recherche de cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class CotisationsCriteresPluginDto implements Serializable {

    /** Opération de type cotisation. */
    public static final String OPERATION_COTISATION = "cotisation";

    /** Opération de type simulation. */
    public static final String OPERATION_SIMULATION = "simulation";

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 9204377053470779650L;

    /** Le type de relation. */
    private String type;

    /** La personne ciblée. */
    private String personne;

    /** La date d'effet. */
    private Calendar dateEffet;

    /** La date de début. */
    private Calendar dateDebut;

    /** La date de fin. */
    private Calendar dateFin;

    /** Le contrat. */
    private String contrat;

    /** Le mode de paiement. */
    private String modePaiement;

    /** La situation. */
    private String situation;

    /** Le montant min. */
    private Float montantMin;

    /** Le montant max. */
    private Float montantMax;

    /** Operation (simulation ou cotisation). */
    private String operation;

    /**
     * Consctructeur.
     */
    public CotisationsCriteresPluginDto() {
        super();
    }

    /**
     * Get the personne value.
     * @return the personne
     */
    public String getPersonne() {
        return personne;
    }

    /**
     * Set the personne value.
     * @param personne the personne to set
     */
    public void setPersonne(String personne) {
        this.personne = personne;
    }

    /**
     * Get the dateEffet value.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Set the dateEffet value.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
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
     * Get the type value.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type value.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the contrat value.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the contrat value.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Get the modePaiement value.
     * @return the modePaiement
     */
    public String getModePaiement() {
        return modePaiement;
    }

    /**
     * Set the modePaiement value.
     * @param modePaiement the modePaiement to set
     */
    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    /**
     * Get the situation value.
     * @return the situation
     */
    public String getSituation() {
        return situation;
    }

    /**
     * Set the situation value.
     * @param situation the situation to set
     */
    public void setSituation(String situation) {
        this.situation = situation;
    }

    /**
     * Get the montantMin value.
     * @return the montantMin
     */
    public Float getMontantMin() {
        return montantMin;
    }

    /**
     * Set the montantMin value.
     * @param montantMin the montantMin to set
     */
    public void setMontantMin(Float montantMin) {
        this.montantMin = montantMin;
    }

    /**
     * Get the montantMax value.
     * @return the montantMax
     */
    public Float getMontantMax() {
        return montantMax;
    }

    /**
     * Set the montantMax value.
     * @param montantMax the montantMax to set
     */
    public void setMontantMax(Float montantMax) {
        this.montantMax = montantMax;
    }

    /**
     * Get the operation value.
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Set the operation value.
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
}
