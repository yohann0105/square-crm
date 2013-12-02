package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;

/**
 * DTO représentant les informations d'une adresse mail d'un destinataire.
 * @author nnadeau
 */
public class EmailDestinataireDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -5761574524008019324L;

    /** Libellé du destinataire. */
    private String libelle;

    /** Email du destinatire. */
    private String email;

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
     * Get the email value.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email value.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
