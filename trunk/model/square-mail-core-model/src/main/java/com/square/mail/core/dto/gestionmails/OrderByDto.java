package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;

/**
 * Dto pour les demandes de tri sur les moteurs de recherche.
 * @author Goumard stephane (stephane.goumard@scub.net)
 */
public class OrderByDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2537008722074859616L;

    /**
     * Nom de la colonne.
     */
    private String nomColonne;

    /**
     * Demande le tri croissant.
     */
    private Boolean ascending;


    /**
     * Constructeur.
     */
    public OrderByDto() {
        this.nomColonne = "";
        this.ascending = true;
    }

    /**
     * Constructeur.
     * @param nomColonne nom de la colonne.
     * @param ascending ascendant ou pas.
     */
    public OrderByDto(String nomColonne, Boolean ascending) {
        this.nomColonne = nomColonne;
        this.ascending = ascending;
    }

    /**
     * Get the ascending value.
     * @return the ascending
     */
    public Boolean getAscending() {
        return ascending;
    }

    /**
     * Set the ascending value.
     * @param ascending the ascending to set
     */
    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * Recupere la valeur de nomColonne.
     * @return la valeur de nomColonne
     */
    public String getNomColonne() {
        return nomColonne;
    }

    /**
     * Definit la valeur de nomColonne.
     * @param nomColonne la nouvelle valeur de nomColonne
     */
    public void setNomColonne(String nomColonne) {
        this.nomColonne = nomColonne;
    }

}
