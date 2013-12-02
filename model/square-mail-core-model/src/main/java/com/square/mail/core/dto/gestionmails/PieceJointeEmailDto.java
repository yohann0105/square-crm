package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;

/**
 * DTO représentant une pièce jointe d'un mail.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class PieceJointeEmailDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 8700348638428524529L;

    /** Identifiant. */
    private Long id;

    /** Nom du fichier. */
    private String nom;

    /** Nom du fichier timestamp (sur le serveur). */
    private String nomFichierTimeStamp;


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
     * Retourne la valeur de nomFichierTimeStamp.
     * @return la valeur de nomFichierTimeStamp
     */
    public String getNomFichierTimeStamp() {
        return nomFichierTimeStamp;
    }

    /**
     * Définit la valeur de nomFichierTimeStamp.
     * @param nomFichierTimeStamp la nouvelle valeur de nomFichierTimeStamp
     */
    public void setNomFichierTimeStamp(String nomFichierTimeStamp) {
        this.nomFichierTimeStamp = nomFichierTimeStamp;
    }
}
