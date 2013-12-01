package com.square.mail.core.dto.emails;

import java.io.Serializable;
import java.util.Map;

/**
 * Informations à injecter dans un modèle.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net)
 */
public class InfosModeleEmailDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -5478469557126004717L;

    /**
     * Identifiant du modèle d'email.
     */
    private Long idModeleEmail;

    /**
     * Civilité du destinataire.
     */
    private String civiliteDestinataire;

    /**
     * Nom du destinataire.
     */
    private String nomDestinataire;

    /**
     * Email du destinataire.
     */
    private String emailDestinataire;

    /**
     * Informations spécifiques au modèle de l'email.
     */
    private Map<String, ? extends Serializable> mapInfos;

    /**
     * Get the idModeleEmail value.
     * @return the idModeleEmail
     */
    public Long getIdModeleEmail() {
        return idModeleEmail;
    }

    /**
     * Set the idModeleEmail value.
     * @param idModeleEmail the idModeleEmail to set
     */
    public void setIdModeleEmail(Long idModeleEmail) {
        this.idModeleEmail = idModeleEmail;
    }

    /**
     * Get the civiliteDestinataire value.
     * @return the civiliteDestinataire
     */
    public String getCiviliteDestinataire() {
        return civiliteDestinataire;
    }

    /**
     * Set the civiliteDestinataire value.
     * @param civiliteDestinataire the civiliteDestinataire to set
     */
    public void setCiviliteDestinataire(String civiliteDestinataire) {
        this.civiliteDestinataire = civiliteDestinataire;
    }

    /**
     * Get the nomDestinataire value.
     * @return the nomDestinataire
     */
    public String getNomDestinataire() {
        return nomDestinataire;
    }

    /**
     * Set the nomDestinataire value.
     * @param nomDestinataire the nomDestinataire to set
     */
    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }

    /**
     * Get the emailDestinataire value.
     * @return the emailDestinataire
     */
    public String getEmailDestinataire() {
        return emailDestinataire;
    }

    /**
     * Set the emailDestinataire value.
     * @param emailDestinataire the emailDestinataire to set
     */
    public void setEmailDestinataire(String emailDestinataire) {
        this.emailDestinataire = emailDestinataire;
    }

    /**
     * Get the mapInfos value.
     * @return the mapInfos
     */
    public Map<String, ? extends Serializable> getMapInfos() {
        return mapInfos;
    }

    /**
     * Set the mapInfos value.
     * @param mapInfos the mapInfos to set
     */
    public void setMapInfos(Map<String, ? extends Serializable> mapInfos) {
        this.mapInfos = mapInfos;
    }

}
