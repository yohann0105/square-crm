package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * DTO représentant les résultats d'une recherche de destinataires (adresses mails).
 * @author nnadeau
 */
public class RechercheDestinataireResultatsDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -5043144817051180389L;

    /** Liste de résultats. */
    private List < EmailDestinataireDto > listeResultats;

    /**
     * Get the listeResultats value.
     * @return the listeResultats
     */
    public List < EmailDestinataireDto > getListeResultats() {
        if (listeResultats == null) {
            listeResultats = new ArrayList < EmailDestinataireDto > ();
        }
        return listeResultats;
    }

    /**
     * Set the listeResultats value.
     * @param listeResultats the listeResultats to set
     */
    public void setListeResultats(List < EmailDestinataireDto > listeResultats) {
        this.listeResultats = listeResultats;
    }
}
