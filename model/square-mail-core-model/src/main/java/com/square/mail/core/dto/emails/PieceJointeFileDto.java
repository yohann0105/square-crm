package com.square.mail.core.dto.emails;

import java.io.Serializable;

/**
 * DTO représentant les informations du fichier d'une pièce jointe.
 * @author nnadeau
 */
public class PieceJointeFileDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 1343511624659762074L;

    /** Nom du fichier. */
    private String nom;

    /**  Type mime du fichier. */
    private String typeMime;

    /** Données. */
    private byte[] data;

    /** Constructeur. */
    public PieceJointeFileDto() {
    }

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Constructeur.
     * @param nom : le nom du fichier.
     * @param typeMime : le type mime du fichier.
     * @param data : les données du fichier.
     */
    public PieceJointeFileDto(String nom, String typeMime, byte[] data) {
        super();
        this.nom = nom;
        this.typeMime = typeMime;
        this.data = data;
    }

    /**
     * Set the nom value.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the typeMime value.
     * @return the typeMime
     */
    public String getTypeMime() {
        return typeMime;
    }

    /**
     * Set the typeMime value.
     * @param typeMime the typeMime to set
     */
    public void setTypeMime(String typeMime) {
        this.typeMime = typeMime;
    }

    /**
     * Get the data value.
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Set the data value.
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}
