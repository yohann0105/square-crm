package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;

/**
 * Critères pour la recherche du nombre d'emails.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net)
 */
public class CritereRechercheNombreEmailDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -6145783830421388528L;

    /** Numéro d'adhérent. */
    private String numeroAdherent;

    /** Identifiant du service (utilisateurs chargés du mail). */
    private Long idService;

    /** Identifiant de l'utilisateur (chargé du mail). */
    private Long idUtilisateur;

    /**
     * Récupère la valeur de numeroAdherent.
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
     * Récupère la valeur de idService.
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
     * Récupère la valeur de idUtilisateur.
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
}
