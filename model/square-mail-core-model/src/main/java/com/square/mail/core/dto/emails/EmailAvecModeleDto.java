package com.square.mail.core.dto.emails;

import java.io.Serializable;

/**
 * Superclasse DTO qui encapsule les informations communes nécessaires à la génération d'un email au format HTML à partir d'un modèle d'email.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net)
 */
public class EmailAvecModeleDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 8334039261697089709L;

    /**
     * Informations de l'email.
     */
    private MailDto email;

    /**
     * Informations du modèle de l'email.
     */
    private InfosModeleEmailDto infosModele;

    /**
     * Get the email value.
     * @return the email
     */
    public MailDto getEmail() {
        return email;
    }

    /**
     * Set the email value.
     * @param email the email to set
     */
    public void setEmail(MailDto email) {
        this.email = email;
    }

    /**
     * Get the infosModele value.
     * @return the infosModele
     */
    public InfosModeleEmailDto getInfosModele() {
        return infosModele;
    }

    /**
     * Set the infosModele value.
     * @param infosModele the infosModele to set
     */
    public void setInfosModele(InfosModeleEmailDto infosModele) {
        this.infosModele = infosModele;
    }

}
