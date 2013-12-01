package com.square.document.core.model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle pour la table document.
 * @author Julie Jageneau
 */

@Entity
@Table(name = "DOCUMENT")
public class Document extends BaseModel {


    private static final long serialVersionUID = 6870607012332836096L;

    /**
     * Nom.
     */
    @Column(name = "DOCUMENT_NOM")
    private String nom;

    /**
     * Url de téléchargement.
     */
    @Column(name = "DOCUMENT_URL")
    private String url;

    @Column(name = "DOCUMENT_DATE_NUM")
    private Calendar dateNum;

    @Column(name = "DOCUMENT_DATE_REC")
    private Calendar dateRec;

    @Column(name = "DOCUMENT_SENS")
    private String sens;

    @Column(name = "DOCUMENT_NUMERO_CLIENT")
    private String numClient;

    /**
     * Tags d'un document.
     */
    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TYPER", joinColumns = @JoinColumn(name = "typer_id_document", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "typer_id_tag", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {
        "typer_id_document", "typer_id_tag" }))
         @ForeignKey(name = "FK_TYPER_DOCUMENT", inverseName = "FK_TYPER_TAG")
    private Set<Tag> tags;

    /* GETTER & SETTER */

    /**
     * Récupération du nom.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modification du nom.
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupération de l'url de téléchargement du document.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Modification de l'url de téléchargement du document.
     * @param url nouvel url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Récupération de la date de numérisation.
     * @return date_num
     */
    public Calendar getDateNum() {
        return dateNum;
    }

    /**
     * Modification de la date de numérisation.
     * @param dateNum nouvelle date de numérisation
     */
    public void setDateNum(Calendar dateNum) {
        this.dateNum = dateNum;
    }

    /**
     * Récupération de la date de réception.
     * @return date_rec
     */
    public Calendar getDateRec() {
        return dateRec;
    }

    /**
     * Modification de la date de réception.
     * @param dateRec nouvelle date de réception
     */
    public void setDateRec(Calendar dateRec) {
        this.dateRec = dateRec;
    }

    /**
     * Récupération du sens du document (entrant ou sortant).
     * @return sens
     */
    public String getSens() {
        return sens;
    }

    /**
     * Modification du sens du document.
     * @param sens nouveau sens
     */
    public void setSens(String sens) {
        this.sens = sens;
    }

    /**
     * Récupération du numéro de client.
     * @return numclient
     */
    public String getNumClient() {
        return numClient;
    }

    /**
     * Modification du numéro de client.
     * @param numClient nouveau numéro
     */
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    /**
     * Récupération des tags.
     * @return tags
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * Modification des tags.
     * @param tags nouveaux tags
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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
