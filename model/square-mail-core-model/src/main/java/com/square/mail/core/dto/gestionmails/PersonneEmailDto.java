package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO contenant les coordonnées d'une personne (email, nom, ...).
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class PersonneEmailDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -3396610256421234652L;

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Email. */
    private String adresseEmail;

    /** Numéro adhérent. */
    private String numeroAdherent;

    /** Utilisateur (indique si la personne est un utilisateur). */
    private IdentifiantLibelleDto utilisateur;

    /**
     * Retourne la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la valeur de prenom.
     * @return la valeur de prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

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

    /**
     * Retourne la valeur de adresseEmail.
     * @return la valeur de adresseEmail
     */
    public String getAdresseEmail() {
        return adresseEmail;
    }

    /**
     * Définit la valeur de adresseEmail.
     * @param adresseEmail la nouvelle valeur de adresseEmail
     */
    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    /**
     * Retourne la valeur de utilisateur.
     * @return la valeur de utilisateur
     */
    public IdentifiantLibelleDto getUtilisateur() {
        return utilisateur;
    }

    /**
     * Définit la valeur de utilisateur.
     * @param utilisateur la nouvelle valeur de utilisateur
     */
    public void setUtilisateur(IdentifiantLibelleDto utilisateur) {
        this.utilisateur = utilisateur;
    }

}
