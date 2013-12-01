package com.square.adherent.noyau.plugin.dto.cotisation;

import java.io.Serializable;
import java.util.List;

/**
 * Classe de retour AIA.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class RetourCotisationsPluginDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -785082119313224495L;

    /** Statut. */
    private String statut;

    /** Statut. */
    private String personne;

    /** Solde. */
    private Float solde;

    /** Liste des lignes de cotisations. */
    private List<CotisationPluginDto> listeCotisations;

    /** Liste des erreurs. */
    private List<ErreurRetourPluginDto> erreurs;

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
     * Get the solde value.
     * @return the solde
     */
    public Float getSolde() {
        return solde;
    }

    /**
     * Set the solde value.
     * @param solde the solde to set
     */
    public void setSolde(Float solde) {
        this.solde = solde;
    }

    /**
     * Get the erreurs value.
     * @return the erreurs
     */
    public List<ErreurRetourPluginDto> getErreurs() {
        return erreurs;
    }

    /**
     * Set the erreurs value.
     * @param erreurs the erreurs to set
     */
    public void setErreurs(List<ErreurRetourPluginDto> erreurs) {
        this.erreurs = erreurs;
    }

    /**
     * Get the listeCotisations value.
     * @return the listeCotisations
     */
    public List<CotisationPluginDto> getListeCotisations() {
        return listeCotisations;
    }

    /**
     * Set the listeCotisations value.
     * @param listeCotisations the listeCotisations to set
     */
    public void setListeCotisations(List<CotisationPluginDto> listeCotisations) {
        this.listeCotisations = listeCotisations;
    }

}
