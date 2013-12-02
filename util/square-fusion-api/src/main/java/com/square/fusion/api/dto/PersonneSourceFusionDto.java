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
package com.square.fusion.api.dto;

import java.util.List;

/**
 * DTO représentant une personne source à fusionner.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonneSourceFusionDto extends PersonneFusionDto {

    /** Identificateur de sérilaisation. */
    private static final long serialVersionUID = -8254920186178407869L;

    /**
     * Numéro de sécurité social + clé.
     */
    private ProprieteFusionnableStringDto nro;

    /**
     * Civilité de la personne.
     */
    private ProprieteFusionnableIdLibelleDto civilite;

    /**
     * Prénom de la personne.
     */
    private ProprieteFusionnableStringDto prenom;

    /**
     * Nom de la personne.
     */
    private ProprieteFusionnableStringDto nom;

    /**
     * Nom de jeune fille.
     */
    private ProprieteFusionnableStringDto nomJeuneFille;

    /**
     * Date de naissance de la personne.
     */
    private ProprieteFusionnableCalendarDto dateNaissance;

    /**
     * Date de décès de la personne.
     */
    private ProprieteFusionnableCalendarDto dateDeces;

    /**
     * Nature de la personne.
     */
    private ProprieteFusionnableIdLibelleDto naturePersonne;

    /**
     * Indique si la personne est décédé.
     */
    private ProprieteFusionnableBooleanDto decede;

    /**
     * La caisse de la personne.
     */
    private ProprieteFusionnableIdLibelleDto caisse;

    /**
     * Le regime de la personne.
     */
    private ProprieteFusionnableIdLibelleDto caisseRegime;

    /**
     * Le segment de la personne.
     */
    private ProprieteFusionnableIdLibelleDto segment;

    /**
     * Le réseau de vente.
     */
    private ProprieteFusionnableIdLibelleDto reseauVente;

    /**
     * Csp de la personne.
     */
    private ProprieteFusionnableIdLibelleDto csp;

    /**
     * Situation familiale.
     */
    private ProprieteFusionnableIdLibelleDto sitFam;

    /**
     * Profession de la personne.
     */
    private ProprieteFusionnableIdLibelleDto profession;

    /**
     * Statut de la personne.
     */
    private ProprieteFusionnableIdLibelleDto statut;

    /**
     * Commercial de la personne.
     */
    private ProprieteFusionnableIdLibelleDto commercial;

    /**
     * L'agence.
     */
    private ProprieteFusionnableIdLibelleDto agence;

    /** Adresse principal. */
    private ProprieteFusionnableAdresseDto adressePrincipale;

    /** Liste des téléphones. */
    private List<ProprieteFusionnableTelephoneDto> listeTelephones;

    /** Liste des e-mails. */
    private List<ProprieteFusionnableEmailDto> listeEmails;

    /**
     * Récupère la valeur de nro.
     * @return la valeur de nro
     */
    public ProprieteFusionnableStringDto getNro() {
        return nro;
    }

    /**
     * Définit la valeur de nro.
     * @param nro la nouvelle valeur de nro
     */
    public void setNro(ProprieteFusionnableStringDto nro) {
        this.nro = nro;
    }

    /**
     * Récupère la valeur de civilite.
     * @return la valeur de civilite
     */
    public ProprieteFusionnableIdLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Définit la valeur de civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(ProprieteFusionnableIdLibelleDto civilite) {
        this.civilite = civilite;
    }

    /**
     * Récupère la valeur de prenom.
     * @return la valeur de prenom
     */
    public ProprieteFusionnableStringDto getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(ProprieteFusionnableStringDto prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public ProprieteFusionnableStringDto getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(ProprieteFusionnableStringDto nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de nomJeuneFille.
     * @return la valeur de nomJeuneFille
     */
    public ProprieteFusionnableStringDto getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Définit la valeur de nomJeuneFille.
     * @param nomJeuneFille la nouvelle valeur de nomJeuneFille
     */
    public void setNomJeuneFille(ProprieteFusionnableStringDto nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Récupère la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public ProprieteFusionnableCalendarDto getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(ProprieteFusionnableCalendarDto dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupère la valeur de dateDeces.
     * @return la valeur de dateDeces
     */
    public ProprieteFusionnableCalendarDto getDateDeces() {
        return dateDeces;
    }

    /**
     * Définit la valeur de dateDeces.
     * @param dateDeces la nouvelle valeur de dateDeces
     */
    public void setDateDeces(ProprieteFusionnableCalendarDto dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Récupère la valeur de naturePersonne.
     * @return la valeur de naturePersonne
     */
    public ProprieteFusionnableIdLibelleDto getNaturePersonne() {
        return naturePersonne;
    }

    /**
     * Définit la valeur de naturePersonne.
     * @param naturePersonne la nouvelle valeur de naturePersonne
     */
    public void setNaturePersonne(ProprieteFusionnableIdLibelleDto naturePersonne) {
        this.naturePersonne = naturePersonne;
    }

    /**
     * Récupère la valeur de decede.
     * @return la valeur de decede
     */
    public ProprieteFusionnableBooleanDto getDecede() {
        return decede;
    }

    /**
     * Définit la valeur de decede.
     * @param decede la nouvelle valeur de decede
     */
    public void setDecede(ProprieteFusionnableBooleanDto decede) {
        this.decede = decede;
    }

    /**
     * Récupère la valeur de caisse.
     * @return la valeur de caisse
     */
    public ProprieteFusionnableIdLibelleDto getCaisse() {
        return caisse;
    }

    /**
     * Définit la valeur de caisse.
     * @param caisse la nouvelle valeur de caisse
     */
    public void setCaisse(ProprieteFusionnableIdLibelleDto caisse) {
        this.caisse = caisse;
    }

    /**
     * Récupère la valeur de caisseRegime.
     * @return la valeur de caisseRegime
     */
    public ProprieteFusionnableIdLibelleDto getCaisseRegime() {
        return caisseRegime;
    }

    /**
     * Définit la valeur de caisseRegime.
     * @param caisseRegime la nouvelle valeur de caisseRegime
     */
    public void setCaisseRegime(ProprieteFusionnableIdLibelleDto caisseRegime) {
        this.caisseRegime = caisseRegime;
    }

    /**
     * Récupère la valeur de segment.
     * @return la valeur de segment
     */
    public ProprieteFusionnableIdLibelleDto getSegment() {
        return segment;
    }

    /**
     * Définit la valeur de segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(ProprieteFusionnableIdLibelleDto segment) {
        this.segment = segment;
    }

    /**
     * Récupère la valeur de reseauVente.
     * @return la valeur de reseauVente
     */
    public ProprieteFusionnableIdLibelleDto getReseauVente() {
        return reseauVente;
    }

    /**
     * Définit la valeur de reseauVente.
     * @param reseauVente la nouvelle valeur de reseauVente
     */
    public void setReseauVente(ProprieteFusionnableIdLibelleDto reseauVente) {
        this.reseauVente = reseauVente;
    }

    /**
     * Récupère la valeur de csp.
     * @return la valeur de csp
     */
    public ProprieteFusionnableIdLibelleDto getCsp() {
        return csp;
    }

    /**
     * Définit la valeur de csp.
     * @param csp la nouvelle valeur de csp
     */
    public void setCsp(ProprieteFusionnableIdLibelleDto csp) {
        this.csp = csp;
    }

    /**
     * Récupère la valeur de sitFam.
     * @return la valeur de sitFam
     */
    public ProprieteFusionnableIdLibelleDto getSitFam() {
        return sitFam;
    }

    /**
     * Définit la valeur de sitFam.
     * @param sitFam la nouvelle valeur de sitFam
     */
    public void setSitFam(ProprieteFusionnableIdLibelleDto sitFam) {
        this.sitFam = sitFam;
    }

    /**
     * Récupère la valeur de profession.
     * @return la valeur de profession
     */
    public ProprieteFusionnableIdLibelleDto getProfession() {
        return profession;
    }

    /**
     * Définit la valeur de profession.
     * @param profession la nouvelle valeur de profession
     */
    public void setProfession(ProprieteFusionnableIdLibelleDto profession) {
        this.profession = profession;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public ProprieteFusionnableIdLibelleDto getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(ProprieteFusionnableIdLibelleDto statut) {
        this.statut = statut;
    }

    /**
     * Récupère la valeur de commercial.
     * @return la valeur de commercial
     */
    public ProprieteFusionnableIdLibelleDto getCommercial() {
        return commercial;
    }

    /**
     * Définit la valeur de commercial.
     * @param commercial la nouvelle valeur de commercial
     */
    public void setCommercial(ProprieteFusionnableIdLibelleDto commercial) {
        this.commercial = commercial;
    }

    /**
     * Récupère la valeur de agence.
     * @return la valeur de agence
     */
    public ProprieteFusionnableIdLibelleDto getAgence() {
        return agence;
    }

    /**
     * Définit la valeur de agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(ProprieteFusionnableIdLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Récupère la valeur de adressePrincipale.
     * @return la valeur de adressePrincipale
     */
    public ProprieteFusionnableAdresseDto getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Définit la valeur de adressePrincipale.
     * @param adressePrincipale la nouvelle valeur de adressePrincipale
     */
    public void setAdressePrincipale(ProprieteFusionnableAdresseDto adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }

    /**
     * Récupère la valeur de listeTelephones.
     * @return la valeur de listeTelephones
     */
    public List<ProprieteFusionnableTelephoneDto> getListeTelephones() {
        return listeTelephones;
    }

    /**
     * Définit la valeur de listeTelephones.
     * @param listeTelephones la nouvelle valeur de listeTelephones
     */
    public void setListeTelephones(List<ProprieteFusionnableTelephoneDto> listeTelephones) {
        this.listeTelephones = listeTelephones;
    }

    /**
     * Récupère la valeur de listeEmails.
     * @return la valeur de listeEmails
     */
    public List<ProprieteFusionnableEmailDto> getListeEmails() {
        return listeEmails;
    }

    /**
     * Définit la valeur de listeEmails.
     * @param listeEmails la nouvelle valeur de listeEmails
     */
    public void setListeEmails(List<ProprieteFusionnableEmailDto> listeEmails) {
        this.listeEmails = listeEmails;
    }

}
