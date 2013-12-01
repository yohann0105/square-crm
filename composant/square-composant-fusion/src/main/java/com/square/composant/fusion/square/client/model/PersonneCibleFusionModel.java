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
package com.square.composant.fusion.square.client.model;


import java.util.List;

/**
 * Modèle pour la personne cible de la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PersonneCibleFusionModel extends PersonneFusionModel {

    /** Numéro de sécurité social + clé. */
    private String nro;

    /** Civilité de la personne. */
    private IdentifiantLibelleModel civilite;

    /** Prénom de la personne. */
    private String prenom;

    /** Nom de la personne. */
    private String nom;

    /** Nom de jeune fille. */
    private String nomJeuneFille;

    /** Date de naissance de la personne. */
    private String dateNaissance;

    /** Date de décès de la personne. */
    private String dateDeces;

    /** Nature de la personne. */
    private IdentifiantLibelleModel naturePersonne;

    /** Indique si la personne est décédé. */
    private Boolean decede;

    /** La caisse de la personne. */
    private IdentifiantLibelleModel caisse;

    /** Le régime de la personne. */
    private IdentifiantLibelleModel caisseRegime;

    /** Le segment de la personne. */
    private IdentifiantLibelleModel segment;

    /** Le réseau de vente. */
    private IdentifiantLibelleModel reseauVente;

    /** Csp de la personne. */
    private IdentifiantLibelleModel csp;

    /** Situation familiale. */
    private IdentifiantLibelleModel sitFam;

    /** Profession de la personne. */
    private IdentifiantLibelleModel profession;

    /** Statut de la personne. */
    private IdentifiantLibelleModel statut;

    /** Commercial de la personne. */
    private IdentifiantLibelleModel commercial;

    /** L'agence. */
    private IdentifiantLibelleModel agence;

    /** Adresse principal. */
    private AdresseFusionModel adressePrincipale;

    /** Liste des téléphones. */
    private List<TelephoneFusionModel> listeTelephones;

    /** Liste des e-mails. */
    private List<EmailFusionModel> listeEmails;

    /**
     * Récupère la valeur de nro.
     * @return la valeur de nro
     */
    public String getNro() {
        return nro;
    }

    /**
     * Définit la valeur de nro.
     * @param nro la nouvelle valeur de nro
     */
    public void setNro(String nro) {
        this.nro = nro;
    }

    /**
     * Récupère la valeur de civilite.
     * @return la valeur de civilite
     */
    public IdentifiantLibelleModel getCivilite() {
        return civilite;
    }

    /**
     * Définit la valeur de civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(IdentifiantLibelleModel civilite) {
        this.civilite = civilite;
    }

    /**
     * Récupère la valeur de prenom.
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
     * Récupère la valeur de nom.
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
     * Récupère la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupère la valeur de dateDeces.
     * @return la valeur de dateDeces
     */
    public String getDateDeces() {
        return dateDeces;
    }

    /**
     * Définit la valeur de dateDeces.
     * @param dateDeces la nouvelle valeur de dateDeces
     */
    public void setDateDeces(String dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Récupère la valeur de naturePersonne.
     * @return la valeur de naturePersonne
     */
    public IdentifiantLibelleModel getNaturePersonne() {
        return naturePersonne;
    }

    /**
     * Définit la valeur de naturePersonne.
     * @param naturePersonne la nouvelle valeur de naturePersonne
     */
    public void setNaturePersonne(IdentifiantLibelleModel naturePersonne) {
        this.naturePersonne = naturePersonne;
    }

    /**
     * Récupère la valeur de decede.
     * @return la valeur de decede
     */
    public Boolean getDecede() {
        return decede;
    }

    /**
     * Définit la valeur de decede.
     * @param decede la nouvelle valeur de decede
     */
    public void setDecede(Boolean decede) {
        this.decede = decede;
    }

    /**
     * Récupère la valeur de caisse.
     * @return la valeur de caisse
     */
    public IdentifiantLibelleModel getCaisse() {
        return caisse;
    }

    /**
     * Définit la valeur de caisse.
     * @param caisse la nouvelle valeur de caisse
     */
    public void setCaisse(IdentifiantLibelleModel caisse) {
        this.caisse = caisse;
    }

    /**
     * Récupère la valeur de caisseRegime.
     * @return la valeur de caisseRegime
     */
    public IdentifiantLibelleModel getCaisseRegime() {
        return caisseRegime;
    }

    /**
     * Définit la valeur de caisseRegime.
     * @param caisseRegime la nouvelle valeur de caisseRegime
     */
    public void setCaisseRegime(IdentifiantLibelleModel caisseRegime) {
        this.caisseRegime = caisseRegime;
    }

    /**
     * Récupère la valeur de segment.
     * @return la valeur de segment
     */
    public IdentifiantLibelleModel getSegment() {
        return segment;
    }

    /**
     * Définit la valeur de segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleModel segment) {
        this.segment = segment;
    }

    /**
     * Récupère la valeur de reseauVente.
     * @return la valeur de reseauVente
     */
    public IdentifiantLibelleModel getReseauVente() {
        return reseauVente;
    }

    /**
     * Définit la valeur de reseauVente.
     * @param reseauVente la nouvelle valeur de reseauVente
     */
    public void setReseauVente(IdentifiantLibelleModel reseauVente) {
        this.reseauVente = reseauVente;
    }

    /**
     * Récupère la valeur de csp.
     * @return la valeur de csp
     */
    public IdentifiantLibelleModel getCsp() {
        return csp;
    }

    /**
     * Définit la valeur de csp.
     * @param csp la nouvelle valeur de csp
     */
    public void setCsp(IdentifiantLibelleModel csp) {
        this.csp = csp;
    }

    /**
     * Récupère la valeur de sitFam.
     * @return la valeur de sitFam
     */
    public IdentifiantLibelleModel getSitFam() {
        return sitFam;
    }

    /**
     * Définit la valeur de sitFam.
     * @param sitFam la nouvelle valeur de sitFam
     */
    public void setSitFam(IdentifiantLibelleModel sitFam) {
        this.sitFam = sitFam;
    }

    /**
     * Récupère la valeur de profession.
     * @return la valeur de profession
     */
    public IdentifiantLibelleModel getProfession() {
        return profession;
    }

    /**
     * Définit la valeur de profession.
     * @param profession la nouvelle valeur de profession
     */
    public void setProfession(IdentifiantLibelleModel profession) {
        this.profession = profession;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public IdentifiantLibelleModel getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleModel statut) {
        this.statut = statut;
    }

    /**
     * Récupère la valeur de commercial.
     * @return la valeur de commercial
     */
    public IdentifiantLibelleModel getCommercial() {
        return commercial;
    }

    /**
     * Définit la valeur de commercial.
     * @param commercial la nouvelle valeur de commercial
     */
    public void setCommercial(IdentifiantLibelleModel commercial) {
        this.commercial = commercial;
    }

    /**
     * Récupère la valeur de agence.
     * @return la valeur de agence
     */
    public IdentifiantLibelleModel getAgence() {
        return agence;
    }

    /**
     * Définit la valeur de agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(IdentifiantLibelleModel agence) {
        this.agence = agence;
    }

    /**
     * Récupère la valeur de adressePrincipale.
     * @return la valeur de adressePrincipale
     */
    public AdresseFusionModel getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Définit la valeur de adressePrincipale.
     * @param adressePrincipale la nouvelle valeur de adressePrincipale
     */
    public void setAdressePrincipale(AdresseFusionModel adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    /**
     * Récupère la valeur de listeTelephones.
     * @return la valeur de listeTelephones
     */
    public List<TelephoneFusionModel> getListeTelephones() {
        return listeTelephones;
    }

    /**
     * Définit la valeur de listeTelephones.
     * @param listeTelephones la nouvelle valeur de listeTelephones
     */
    public void setListeTelephones(List<TelephoneFusionModel> listeTelephones) {
        this.listeTelephones = listeTelephones;
    }

    /**
     * Récupère la valeur de listeEmails.
     * @return la valeur de listeEmails
     */
    public List<EmailFusionModel> getListeEmails() {
        return listeEmails;
    }

    /**
     * Définit la valeur de listeEmails.
     * @param listeEmails la nouvelle valeur de listeEmails
     */
    public void setListeEmails(List<EmailFusionModel> listeEmails) {
        this.listeEmails = listeEmails;
    }
}
