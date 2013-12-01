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

import com.square.print.core.dto.AdresseDto;

/**
 * DTO représentant un prospect pour les devis PDF.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class ProspectBADto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -5628590778809968733L;

    /** Identifiant. */
    private Long id;

    /** Numéro d'adhérent. */
    private String numeroAdherent;

    /** Civilité. */
    private String civilite;

    /** Nom. */
    private String nom;

    /** Nom de jeune fille. */
    private String nomJeuneFille;

    /** Prénom. */
    private String prenom;

    /** Adresse. */
    private AdresseDto adresse;

    /** Numero de téléphone. */
    private String numeroTelephone;

    /** E-mail. */
    private String email;

    /** Numéro de sécurité sociale. */
    private String numeroSecuriteSociale;

    /** Clé du numéro de sécurité sociale. */
    private String cleNumeroSecuriteSociale;

    /** Libellé de la caisse. */
    private String libelleCaisse;

    /** Libellé du régime. */
    private String libelleRegime;

    /** Indique si le prospect est un travailleur non salarié. */
    private Boolean travailleurNonSalarie;

    /** Indique si le prospect souhaite bénéficier de la loi Madelin. */
    private Boolean souhaiteBeneficierLoiMadelin;

    /** Délai d'attente. */
    private String delaiAttente;

    /** Numéro d'adhérent du parrain. */
    private String numeroAdherentParrain;

    /** Constructeur. */
    public ProspectBADto() { }

    /**
     * Définit la valeur de id.
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
     * Get the numeroAdherent value.
     * @return the numeroAdherent
     */
    public String getNumeroAdherent() {
        return numeroAdherent;
    }

    /**
     * Set the numeroAdherent value.
     * @param numeroAdherent the numeroAdherent to set
     */
    public void setNumeroAdherent(String numeroAdherent) {
        this.numeroAdherent = numeroAdherent;
    }

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
     * Définit la valeur de adresse.
     * @return la valeur de adresse
     */
    public AdresseDto getAdresse() {
        if (adresse == null) {
            adresse = new AdresseDto();
        }
        return adresse;
    }

    /**
     * Définit la valeur de adresse.
     * @param adresse la nouvelle valeur de adresse
     */
    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne la valeur de travailleurNonSalarie.
     * @return la valeur de travailleurNonSalarie
     */
    public Boolean getTravailleurNonSalarie() {
        return travailleurNonSalarie;
    }

    /**
     * Définit la valeur de travailleurNonSalarie.
     * @param travailleurNonSalarie la nouvelle valeur de travailleurNonSalarie
     */
    public void setTravailleurNonSalarie(Boolean travailleurNonSalarie) {
        this.travailleurNonSalarie = travailleurNonSalarie;
    }

    /**
     * Retourne la valeur de souhaiteBeneficierLoiMadelin.
     * @return la valeur de souhaiteBeneficierLoiMadelin
     */
    public Boolean getSouhaiteBeneficierLoiMadelin() {
        return souhaiteBeneficierLoiMadelin;
    }

    /**
     * Définit la valeur de souhaiteBeneficierLoiMadelin.
     * @param souhaiteBeneficierLoiMadelin la nouvelle valeur de souhaiteBeneficierLoiMadelin
     */
    public void setSouhaiteBeneficierLoiMadelin(Boolean souhaiteBeneficierLoiMadelin) {
        this.souhaiteBeneficierLoiMadelin = souhaiteBeneficierLoiMadelin;
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
     * Retourne la valeur de numeroTelephone.
     * @return la valeur de numeroTelephone
     */
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    /**
     * Définit la valeur de numeroTelephone.
     * @param numeroTelephone la nouvelle valeur de numeroTelephone
     */
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    /**
     * Retourne la valeur de email.
     * @return la valeur de email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit la valeur de email.
     * @param email la nouvelle valeur de email
     */
    public void setEmail(String email) {
        this.email = email;
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
	 * Getter function.
	 * @return the numeroAdherentParrain
	 */
	public String getNumeroAdherentParrain() {
		return numeroAdherentParrain;
	}

	/**
	 * Setter function.
	 * @param numeroAdherentParrain the numeroAdherentParrain to set
	 */
	public void setNumeroAdherentParrain(String numeroAdherentParrain) {
		this.numeroAdherentParrain = numeroAdherentParrain;
	}

}
