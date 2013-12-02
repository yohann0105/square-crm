package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO représentant un groupe d'emails (fil de discussion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class GroupeEmailDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 5357430787262345700L;

    /** Identifiant. */
    private Long id;

    /** GroupMessageId. */
    private String groupeMessageId;

    /** Liste des emails. */
    private List < EmailDto > listeEmails;

    /**
     * Retourne la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne la valeur de groupeMessageId.
     * @return la valeur de groupeMessageId
     */
    public String getGroupeMessageId() {
        return groupeMessageId;
    }

    /**
     * Définit la valeur de groupeMessageId.
     * @param groupeMessageId la nouvelle valeur de groupeMessageId
     */
    public void setGroupeMessageId(String groupeMessageId) {
        this.groupeMessageId = groupeMessageId;
    }

    /**
     * Retourne la valeur de listeEmails.
     * @return la valeur de listeEmails
     */
    public List < EmailDto > getListeEmails() {
        if (listeEmails == null) {
            listeEmails = new ArrayList < EmailDto > ();
        }
        return listeEmails;
    }

    /**
     * Définit la valeur de listeEmails.
     * @param listeEmails la nouvelle valeur de listeEmails
     */
    public void setListeEmails(List < EmailDto > listeEmails) {
        this.listeEmails = listeEmails;
    }

}
