package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;
/**
 * Dto pour les informations de pagination.
 * @author Goumard stephane (stephane.goumard@scub.net)
 */
public class PagingDto implements Serializable
{

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7839106626956173375L;

    /**
     * Debut pagination.
     */
    private Integer firstResult = 0;

    /**
     * Max pagination.
     */
    private Integer maxResult = 0;

    /**
     * Nombre de resultat de la requete hors pagination.
     */
    private Integer nombreTotalResultat;

    /**
     * Get the firstResult value.
     * @return the firstResult
     */
    public Integer getFirstResult() {
        return firstResult;
    }

    /**
     * Set the firstResult value.
     * @param firstResult the firstResult to set
     */
    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    /**
     * Get the maxResult value.
     * @return the maxResult
     */
    public Integer getMaxResult() {
        return maxResult;
    }

    /**
     * Set the maxResult value.
     * @param maxResult the maxResult to set
     */
    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    /**
     * Get the nombreTotalResultat value.
     * @return the nombreTotalResultat
     */
    public Integer getNombreTotalResultat() {
        return nombreTotalResultat;
    }

    /**
     * Set the nombreTotalResultat value.
     * @param nombreTotalResultat the nombreTotalResultat to set
     */
    public void setNombreTotalResultat(Integer nombreTotalResultat) {
        this.nombreTotalResultat = nombreTotalResultat;
    }
}
