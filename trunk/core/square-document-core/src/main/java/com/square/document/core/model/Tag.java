package com.square.document.core.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle pour la table Tag.
 * @author Julie Jageneau
 */

@Entity
@Table(name = "TAG")
public class Tag extends BaseModel {

    private static final long serialVersionUID = 3589730940657948827L;

    @Column(name = "TAG_NOM")
    private String nom;

    @Column(name = "TAG_DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PARENT")
    private List<Tag> listeTags;

    /**
     * Récupération du nom du tag.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modification du nom du tag.
     * @param nom noueau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupération de la description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modification de la description.
     * @param description nouvelle description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Récupération du tag père (rubrique supérieure).
     * @return listeTags
     */
    public List<Tag> getListeTags() {
        return listeTags;
    }

    /**
     * Modification du tag père (rubrique supérieure).
     * @param listeTags nouvelle liste de tags fils
     */
    public void setListeTags(List<Tag> listeTags) {
        this.listeTags = listeTags;
    }

    @Override
    public boolean equals(Object other) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

}
