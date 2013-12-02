package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant une requête pour la recherche de mails.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class RechercheEmailRequeteDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6661944751275672781L;

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Numéro d'adhérent (recherche exacte). */
    private String numeroAdherent;

    /** Sujet du mail (recherche de type like). */
    private String sujetMail;

    /** Date de début d'envoi. */
    private Calendar dateDebutEnvoi;

    /** Date de fin d'envoi. */
    private Calendar dateFinEnvoi;

    /** Recherche Full Text. */
    private String rechercheFullText;

    /** Identifiant de l'état du mail. */
    private Long idEtat;

    /** Identifiant du service (utilisateurs chargés du mail). */
    private Long idService;

    /** Identifiant de l'utilisateur (chargé du mail). */
    private Long idUtilisateur;

    /** Login de l'utilisateur connecté. */
    private String loginUtilisateurConnecte;

    /** Ordre de tri. */
    private Boolean ordreAscendant = Boolean.TRUE;

    /** Colonne sur laquelle trier. */
    private String ordreColonne;

    /** Booléen pour la recherche FullText pour savoir si c'est une archive ou non. */
    private Boolean isArchive;

    /** Booléen pour indiquer si on est dans le cas d'une recherche fullText. */
    private Boolean isRechercheFullText;

    /**
     * Retourne la valeur de sujetMail.
     * @return la valeur de sujetMail
     */
    public String getSujetMail() {
        return sujetMail;
    }

    /**
     * Définit la valeur de sujetMail.
     * @param sujetMail la nouvelle valeur de sujetMail
     */
    public void setSujetMail(String sujetMail) {
        this.sujetMail = sujetMail;
    }

    /**
     * Retourne la valeur de rechercheFullText.
     * @return la valeur de rechercheFullText
     */
    public String getRechercheFullText() {
        return rechercheFullText;
    }

    /**
     * Définit la valeur de rechercheFullText.
     * @param rechercheFullText la nouvelle valeur de rechercheFullText
     */
    public void setRechercheFullText(String rechercheFullText) {
        this.rechercheFullText = rechercheFullText;
    }

    /**
     * Retourne la valeur de idEtat.
     * @return la valeur de idEtat
     */
    public Long getIdEtat() {
        return idEtat;
    }

    /**
     * Définit la valeur de idEtat.
     * @param idEtat la nouvelle valeur de idEtat
     */
    public void setIdEtat(Long idEtat) {
        this.idEtat = idEtat;
    }

    /**
     * Retourne la valeur de idService.
     * @return la valeur de idService
     */
    public Long getIdService() {
        return idService;
    }

    /**
     * Définit la valeur de idService.
     * @param idService la nouvelle valeur de idService
     */
    public void setIdService(Long idService) {
        this.idService = idService;
    }

    /**
     * Retourne la valeur de idUtilisateur.
     * @return la valeur de idUtilisateur
     */
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit la valeur de idUtilisateur.
     * @param idUtilisateur la nouvelle valeur de idUtilisateur
     */
    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Retourne la valeur de ordreColonne.
     * @return la valeur de ordreColonne
     */
    public String getOrdreColonne() {
        return ordreColonne;
    }

    /**
     * Définit la valeur de ordreColonne.
     * @param ordreColonne la nouvelle valeur de ordreColonne
     */
    public void setOrdreColonne(String ordreColonne) {
        this.ordreColonne = ordreColonne;
    }

    /**
     * Retourne la valeur de dateDebutEnvoi.
     * @return la valeur de dateDebutEnvoi
     */
    public Calendar getDateDebutEnvoi() {
        return dateDebutEnvoi;
    }

    /**
     * Définit la valeur de dateDebutEnvoi.
     * @param dateDebutEnvoi la nouvelle valeur de dateDebutEnvoi
     */
    public void setDateDebutEnvoi(Calendar dateDebutEnvoi) {
        this.dateDebutEnvoi = dateDebutEnvoi;
    }

    /**
     * Retourne la valeur de dateFinEnvoi.
     * @return la valeur de dateFinEnvoi
     */
    public Calendar getDateFinEnvoi() {
        return dateFinEnvoi;
    }

    /**
     * Définit la valeur de dateFinEnvoi.
     * @param dateFinEnvoi la nouvelle valeur de dateFinEnvoi
     */
    public void setDateFinEnvoi(Calendar dateFinEnvoi) {
        this.dateFinEnvoi = dateFinEnvoi;
    }

    /**
     * Retourne la valeur de ordreAscendant.
     * @return la valeur de ordreAscendant
     */
    public Boolean getOrdreAscendant() {
        return ordreAscendant;
    }

    /**
     * Définit la valeur de ordreAscendant.
     * @param ordreAscendant la nouvelle valeur de ordreAscendant
     */
    public void setOrdreAscendant(Boolean ordreAscendant) {
        this.ordreAscendant = ordreAscendant;
    }

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
     * Get the isArchive value.
     * @return the isArchive
     */
    public Boolean getIsArchive() {
        return isArchive;
    }

    /**
     * Set the isArchive value.
     * @param isArchive the isArchive to set
     */
    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    /**
     * Get the isRechercheFullText value.
     * @return the isRechercheFullText
     */
    public Boolean getIsRechercheFullText() {
        return isRechercheFullText;
    }

    /**
     * Set the isRechercheFullText value.
     * @param isRechercheFullText the isRechercheFullText to set
     */
    public void setIsRechercheFullText(Boolean isRechercheFullText) {
        this.isRechercheFullText = isRechercheFullText;
    }

    /**
     * Getter function.
     * @return the loginUtilisateurConnecte
     */
    public String getLoginUtilisateurConnecte() {
        return loginUtilisateurConnecte;
    }

    /**
     * Setter function.
     * @param loginUtilisateurConnecte the loginUtilisateurConnecte to set
     */
    public void setLoginUtilisateurConnecte(String loginUtilisateurConnecte) {
        this.loginUtilisateurConnecte = loginUtilisateurConnecte;
    }

}
