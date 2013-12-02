package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;

/**
 * DTO contenant les informations d'un prospect.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class InformationsProspectDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -4056643183421059981L;

    /** Numéro de l'adhérent. */
    private String numeroAdherent;

    /**
     * Retourne la valeur de numeroAdherent.
     * @return la valeur de numeroAdherent
     */
    public String getNumeroAdherent() {
        return numeroAdherent;
    }

    /**
     * Définit la valeur de numeroAdherent.
     * @param numeroAdherent la nouvelle valeur de numeroAdherent
     */
    public void setNumeroAdherent(String numeroAdherent) {
        this.numeroAdherent = numeroAdherent;
    }

}
