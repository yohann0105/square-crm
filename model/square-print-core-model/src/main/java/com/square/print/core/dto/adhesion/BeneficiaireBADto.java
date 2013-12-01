/*
 * This file is a part of Square, Customer Relationship Management Software for insurance's companies
 * Copyright (C) 2010-2012  SCUB <square@scub.net> - Mutuelle SMATIS FRANCE  <square@smatis.fr >
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.square.print.core.dto.adhesion;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant un bénéficiaire d'un devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class BeneficiaireBADto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -4507516412797829337L;

    /** Identifiant du prospect (si le bénéficiaire est le prospect). */
    private Long idProspect;

    /** Identifiant du bénéficiaire (si le bénéficiaire est un bénéficiaire du prospect). */
    private Long idBeneficiaire;

    /** Civilite. */
    private String civilite;

    /** Nom. */
    private String nom;

    /** Nom de jeune fille. */
    private String nomJeuneFille;

    /** Prénom. */
    private String prenom;

    /** Date de naissance. */
    private Calendar dateDeNaissance;

    /** Lien familial. */
    private String lienFamilial;

    /** Numéro de sécurité sociale. */
    private String numeroSecuriteSociale;

    /** Clé du numéro de sécurité sociale. */
    private String cleNumeroSecuriteSociale;

    /** Libellé de la caisse. */
    private String libelleCaisse;

    /** Libellé du régime. */
    private String libelleRegime;

    /** Délai d'attente. */
    private String delaiAttente;

    /** Indique si c'est un conjoint. */
    private Boolean isConjoint = Boolean.FALSE;

    /** Indique si c'est un enfant. */
    private Boolean isEnfant = Boolean.FALSE;

    /**
     * Définit la valeur de nom.
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
     * Définit la valeur de prenom.
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
     * Définit la valeur de idProspect.
     * @return la valeur de idProspect
     */
    public Long getIdProspect() {
        return idProspect;
    }

    /**
     * Définit la valeur de idProspect.
     * @param idProspect la nouvelle valeur de idProspect
     */
    public void setIdProspect(Long idProspect) {
        this.idProspect = idProspect;
    }

    /**
     * Définit la valeur de idBeneficiaire.
     * @return la valeur de idBeneficiaire
     */
    public Long getIdBeneficiaire() {
        return idBeneficiaire;
    }

    /**
     * Définit la valeur de idBeneficiaire.
     * @param idBeneficiaire la nouvelle valeur de idBeneficiaire
     */
    public void setIdBeneficiaire(Long idBeneficiaire) {
        this.idBeneficiaire = idBeneficiaire;
    }

    /**
     * Retourne la valeur de numeroSecuriteSociale.
     * @return la valeur de numeroSecuriteSociale
     */
    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    /**
     * Définit la valeur de numeroSecuriteSociale.
     * @param numeroSecuriteSociale la nouvelle valeur de numeroSecuriteSociale
     */
    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    /**
     * Retourne la valeur de cleNumeroSecuriteSociale.
     * @return la valeur de cleNumeroSecuriteSociale
     */
    public String getCleNumeroSecuriteSociale() {
        return cleNumeroSecuriteSociale;
    }

    /**
     * Définit la valeur de cleNumeroSecuriteSociale.
     * @param cleNumeroSecuriteSociale la nouvelle valeur de cleNumeroSecuriteSociale
     */
    public void setCleNumeroSecuriteSociale(String cleNumeroSecuriteSociale) {
        this.cleNumeroSecuriteSociale = cleNumeroSecuriteSociale;
    }

    /**
     * Retourne la valeur de libelleCaisse.
     * @return la valeur de libelleCaisse
     */
    public String getLibelleCaisse() {
        return libelleCaisse;
    }

    /**
     * Définit la valeur de libelleCaisse.
     * @param libelleCaisse la nouvelle valeur de libelleCaisse
     */
    public void setLibelleCaisse(String libelleCaisse) {
        this.libelleCaisse = libelleCaisse;
    }

    /**
     * Retourne la valeur de libelleRegime.
     * @return la valeur de libelleRegime
     */
    public String getLibelleRegime() {
        return libelleRegime;
    }

    /**
     * Définit la valeur de libelleRegime.
     * @param libelleRegime la nouvelle valeur de libelleRegime
     */
    public void setLibelleRegime(String libelleRegime) {
        this.libelleRegime = libelleRegime;
    }

    /**
     * Retourne la valeur de delaiAttente.
     * @return la valeur de delaiAttente
     */
    public String getDelaiAttente() {
        return delaiAttente;
    }

    /**
     * Définit la valeur de delaiAttente.
     * @param delaiAttente la nouvelle valeur de delaiAttente
     */
    public void setDelaiAttente(String delaiAttente) {
        this.delaiAttente = delaiAttente;
    }

    /**
     * Retourne la valeur de dateDeNaissance.
     * @return la valeur de dateDeNaissance
     */
    public Calendar getDateDeNaissance() {
        return dateDeNaissance;
    }

    /**
     * Définit la valeur de dateDeNaissance.
     * @param dateDeNaissance la nouvelle valeur de dateDeNaissance
     */
    public void setDateDeNaissance(Calendar dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /**
     * Retourne la valeur de lienFamilial.
     * @return la valeur de lienFamilial
     */
    public String getLienFamilial() {
        return lienFamilial;
    }

    /**
     * Définit la valeur de lienFamilial.
     * @param lienFamilial la nouvelle valeur de lienFamilial
     */
    public void setLienFamilial(String lienFamilial) {
        this.lienFamilial = lienFamilial;
    }

    /**
     * Récupère la valeur de civilite.
     * @return la valeur de civilite
     */
    public String getCivilite() {
        return civilite;
    }

    /**
     * Définit la valeur de civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    /**
     * Récupère la valeur de nomJeuneFille.
     * @return la valeur de nomJeuneFille
     */
    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Définit la valeur de nomJeuneFille.
     * @param nomJeuneFille la nouvelle valeur de nomJeuneFille
     */
    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Récupère la valeur de isConjoint.
     * @return la valeur de isConjoint
     */
    public Boolean getIsConjoint() {
        return isConjoint;
    }

    /**
     * Définit la valeur de isConjoint.
     * @param isConjoint la nouvelle valeur de isConjoint
     */
    public void setIsConjoint(Boolean isConjoint) {
        this.isConjoint = isConjoint;
    }

    /**
     * Récupère la valeur de isEnfant.
     * @return la valeur de isEnfant
     */
    public Boolean getIsEnfant() {
        return isEnfant;
    }

    /**
     * Définit la valeur de isEnfant.
     * @param isEnfant la nouvelle valeur de isEnfant
     */
    public void setIsEnfant(Boolean isEnfant) {
        this.isEnfant = isEnfant;
    }

}
