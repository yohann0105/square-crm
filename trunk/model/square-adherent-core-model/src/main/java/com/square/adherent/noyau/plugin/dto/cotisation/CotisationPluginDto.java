package com.square.adherent.noyau.plugin.dto.cotisation;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Classe de retour AIA.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class CotisationPluginDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -6793840484138422616L;

    /** Date de debut. */
    private Calendar dateDebut;

    /** Date de fin. */
    private Calendar dateFin;

    /** Montant. */
    private Float montant;

    /** Montant reglé. */
    private Float montantRegle;

    /** Jour de paiement. */
    private Calendar jourPaiement;

    /** Moyen de paiement. */
    private String moyenPaiement;

    /** Statut. */
    private String statut;

    /** Détail des encaissements. */
    private List<DetailEncaissementPluginDto> listeDetailsEncaissement;

    /** Détail des cotisations. */
    private List<DetailCotisationPluginDto> listeDetailsCotisation;

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
     * Get the montantRegle value.
     * @return the montantRegle
     */
    public Float getMontantRegle() {
        return montantRegle;
    }

    /**
     * Set the montantRegle value.
     * @param montantRegle the montantRegle to set
     */
    public void setMontantRegle(Float montantRegle) {
        this.montantRegle = montantRegle;
    }

    /**
     * Get the jourPaiement value.
     * @return the jourPaiement
     */
    public Calendar getJourPaiement() {
        return jourPaiement;
    }

    /**
     * Set the jourPaiement value.
     * @param jourPaiement the jourPaiement to set
     */
    public void setJourPaiement(Calendar jourPaiement) {
        this.jourPaiement = jourPaiement;
    }

    /**
     * Get the moyenPaiement value.
     * @return the moyenPaiement
     */
    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Set the moyenPaiement value.
     * @param moyenPaiement the moyenPaiement to set
     */
    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * Get the statut value.
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Set the statut value.
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Get the listeDetailsEncaissement value.
     * @return the listeDetailsEncaissement
     */
    public List<DetailEncaissementPluginDto> getListeDetailsEncaissement() {
        return listeDetailsEncaissement;
    }

    /**
     * Set the listeDetailsEncaissement value.
     * @param listeDetailsEncaissement the listeDetailsEncaissement to set
     */
    public void setListeDetailsEncaissement(List<DetailEncaissementPluginDto> listeDetailsEncaissement) {
        this.listeDetailsEncaissement = listeDetailsEncaissement;
    }

    /**
     * Get the listeDetailsCotisation value.
     * @return the listeDetailsCotisation
     */
    public List<DetailCotisationPluginDto> getListeDetailsCotisation() {
        return listeDetailsCotisation;
    }

    /**
     * Set the listeDetailsCotisation value.
     * @param listeDetailsCotisation the listeDetailsCotisation to set
     */
    public void setListeDetailsCotisation(List<DetailCotisationPluginDto> listeDetailsCotisation) {
        this.listeDetailsCotisation = listeDetailsCotisation;
    }

}
