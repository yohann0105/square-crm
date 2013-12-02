package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant une ligne de résultat pour la recherche de mails.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class RechercheEmailResultatDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -7985860715053540305L;

    /** Identifiant du mail. */
    private Long identifiantMail;

    /** Numéro de l'adhérent. */
    private String numeroAdherent;

    /** Nom de l'expéditeur. */
    private String nomExpediteur;

    /** Prénom de l'expéditeur. */
    private String prenomExpediteur;

    /** Adresse mail de l'expéditeur. */
    private String adresseMailExpediteur;

    /** Id de l'utilisateur en charge du mail. */
    private Long idUtilisateurCharge;

    /** Libellé de l'utilisateur en charge du mail. */
    private String libelleUtilisateurCharge;

    /** L'identifiant du service. */
    private Long idService;

    /** Libellé du service. */
    private String libelleService;

    /** Libellé de l'état. */
    private String libelleEtat;

    /** Sujet du mail. */
    private String sujetMail;

    /** Date d'envoi. */
    private Calendar dateEnvoi;

    /** Identifiant du groupe. */
    private Long idGroupe;

    /**
     * Retourne la valeur de identifiantMail.
     * @return la valeur de identifiantMail
     */
    public Long getIdentifiantMail() {
        return identifiantMail;
    }

    /**
     * Définit la valeur de identifiantMail.
     * @param identifiantMail la nouvelle valeur de identifiantMail
     */
    public void setIdentifiantMail(Long identifiantMail) {
        this.identifiantMail = identifiantMail;
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
     * Retourne la valeur de nomExpediteur.
     * @return la valeur de nomExpediteur
     */
    public String getNomExpediteur() {
        return nomExpediteur;
    }

    /**
     * Définit la valeur de nomExpediteur.
     * @param nomExpediteur la nouvelle valeur de nomExpediteur
     */
    public void setNomExpediteur(String nomExpediteur) {
        this.nomExpediteur = nomExpediteur;
    }

    /**
     * Retourne la valeur de prenomExpediteur.
     * @return la valeur de prenomExpediteur
     */
    public String getPrenomExpediteur() {
        return prenomExpediteur;
    }

    /**
     * Définit la valeur de prenomExpediteur.
     * @param prenomExpediteur la nouvelle valeur de prenomExpediteur
     */
    public void setPrenomExpediteur(String prenomExpediteur) {
        this.prenomExpediteur = prenomExpediteur;
    }

    /**
     * Retourne la valeur de libelleUtilisateurCharge.
     * @return la valeur de libelleUtilisateurCharge
     */
    public String getLibelleUtilisateurCharge() {
        return libelleUtilisateurCharge;
    }

    /**
     * Définit la valeur de libelleUtilisateurCharge.
     * @param libelleUtilisateurCharge la nouvelle valeur de libelleUtilisateurCharge
     */
    public void setLibelleUtilisateurCharge(String libelleUtilisateurCharge) {
        this.libelleUtilisateurCharge = libelleUtilisateurCharge;
    }

    /**
     * Retourne la valeur de libelleService.
     * @return la valeur de libelleService
     */
    public String getLibelleService() {
        return libelleService;
    }

    /**
     * Définit la valeur de libelleService.
     * @param libelleService la nouvelle valeur de libelleService
     */
    public void setLibelleService(String libelleService) {
        this.libelleService = libelleService;
    }

    /**
     * Retourne la valeur de libelleEtat.
     * @return la valeur de libelleEtat
     */
    public String getLibelleEtat() {
        return libelleEtat;
    }

    /**
     * Définit la valeur de libelleEtat.
     * @param libelleEtat la nouvelle valeur de libelleEtat
     */
    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
    }

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
     * Retourne la valeur de dateEnvoi.
     * @return la valeur de dateEnvoi
     */
    public Calendar getDateEnvoi() {
        return dateEnvoi;
    }

    /**
     * Définit la valeur de dateEnvoi.
     * @param dateEnvoi la nouvelle valeur de dateEnvoi
     */
    public void setDateEnvoi(Calendar dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    /**
     * Get the idUtilisateurCharge value.
     * @return the idUtilisateurCharge
     */
    public Long getIdUtilisateurCharge() {
        return idUtilisateurCharge;
    }

    /**
     * Set the idUtilisateurCharge value.
     * @param idUtilisateurCharge the idUtilisateurCharge to set
     */
    public void setIdUtilisateurCharge(Long idUtilisateurCharge) {
        this.idUtilisateurCharge = idUtilisateurCharge;
    }

    /**
     * Get the idService value.
     * @return the idService
     */
    public Long getIdService() {
        return idService;
    }

    /**
     * Set the idService value.
     * @param idService the idService to set
     */
    public void setIdService(Long idService) {
        this.idService = idService;
    }

    /**
     * Retourne la valeur de idGroupe.
     * @return la valeur de idGroupe
     */
    public Long getIdGroupe() {
        return idGroupe;
    }

    /**
     * Définit la valeur de idGroupe.
     * @param idGroupe la nouvelle valeur de idGroupe
     */
    public void setIdGroupe(Long idGroupe) {
        this.idGroupe = idGroupe;
    }

    /**
     * Récupère la valeur de adresseMailExpediteur.
     * @return la valeur de adresseMailExpediteur
     */
    public String getAdresseMailExpediteur() {
        return adresseMailExpediteur;
    }

    /**
     * Définit la valeur de adresseMailExpediteur.
     * @param adresseMailExpediteur la nouvelle valeur de adresseMailExpediteur
     */
    public void setAdresseMailExpediteur(String adresseMailExpediteur) {
        this.adresseMailExpediteur = adresseMailExpediteur;
    }

}
