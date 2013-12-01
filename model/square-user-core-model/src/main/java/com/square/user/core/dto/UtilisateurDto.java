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
package com.square.user.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO d'un utilisateur.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class UtilisateurDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -1452369207892000770L;

    /** Identifiant. */
    private Long id;

    /** Login. */
    private String login;

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Date de création. */
    private Calendar dateCreation;

    /** Mot de passe. */
    private String password;

    /** Actif ou pas. */
    private Boolean actif;

    /** Email. */
    private String email;

    /** Raccourci télephone. */
    private String raccourciTelephone;

    /** Numéro de téléphone fixe. */
    private String numeroTelephoneFixe;

    /** Numéro de téléphone portable. */
    private String numeroTelephonePortable;

    /** Numéro de fax. */
    private String numeroFax;

    /** Agence. */
    private IdentifiantLibelleDto agence;

    /** Réseau associé. */
    private IdentifiantLibelleDto reseau;

    /** Fonction. */
    private IdentifiantLibelleDto fonction;

    /** Service. */
    private IdentifiantLibelleDto service;

    /** Liste des rôles. */
    private List < IdentifiantLibelleDto > listeRoles;

    /** Numéro de téléphone fixe personnel. */
    private String numeroTelephoneFixePerso;

    /** Numéro de téléphone portable. */
    private String numeroTelephonePortablePerso;

    /** Date de fin de validité du compte. */
    private Calendar dateFinValidite;

    /** Validité permanente. */
    private Boolean validitePermanente;

    /** Date de réactivation du compte. */
    private Calendar dateReactivation;

    /** Commentaires. */
    private String commentaires;

    /** Commentaires. */
    private Boolean valide = Boolean.FALSE;

    /** Numéro de voie. */
    private String numeroVoie;

    /** Code Bis. */
    private String codeBis;

    /** Nature de la voie. */
    private String natureVoie;

    /** Libellé de la voie. */
    private String libelleVoie;

    /** Complément de l'adresse. */
    private String complementAdresse;

    /** Localité. */
    private String localite;

    /** Département. */
    private String departement;

    /** Code postal. */
    private String codePostal;

    /** Bureau distributeur. */
    private String bureauDistributeur;

    /** Code AIA de l'utilisateur. */
    private String codeAIAUtilisateur;

    /** Code AIA de l'agence. */
    private String codeAIAAgence;

    /** Date de dernière connexion. */
    private Calendar dateDerniereConnexion;

    /** Code ACP. */
    private String codeAcp;

    /** Premiere connexion. */
    private Boolean premiereConnexion;

    /** Droit de modiifer son password. */
    private Boolean droitModifPassword;

    /**Get premiereConnexion.
	 * @return the premiereConnexion
	 */
	public Boolean getPremiereConnexion() {
		return premiereConnexion;
	}

	/**Set premiereConnexion.
	 * @param premiereConnexion the premiereConnexion to set
	 */
	public void setPremiereConnexion(Boolean premiereConnexion) {
		this.premiereConnexion = premiereConnexion;
	}

	/** Get listeResponsable.
	 * @return the listeResponsable
	 */
	public List<IdentifiantLibelleDto> getListeResponsable() {
		return listeResponsable;
	}

	/**Set listeResponsable.
	 * @param listeResponsable the listeResponsable to set
	 */
	public void setListeResponsable(List<IdentifiantLibelleDto> listeResponsable) {
		this.listeResponsable = listeResponsable;
	}

	/** Liste des responsables utilisateurs. */
    private List < IdentifiantLibelleDto > listeResponsable;

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
     * Retourne la valeur de login.
     * @return la valeur de login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit la valeur de login.
     * @param login la nouvelle valeur de login
     */
    public void setLogin(String login) {
        this.login = login;
    }

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
     * Retourne la valeur de prenom.
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
     * Retourne la valeur de dateCreation.
     * @return la valeur de dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la valeur de dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Retourne la valeur de password.
     * @return la valeur de password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit la valeur de password.
     * @param password la nouvelle valeur de password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retourne la valeur de actif.
     * @return la valeur de actif
     */
    public Boolean getActif() {
        return actif;
    }

    /**
     * Définit la valeur de actif.
     * @param actif la nouvelle valeur de actif
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
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
     * Retourne la valeur de raccourciTelephone.
     * @return la valeur de raccourciTelephone
     */
    public String getRaccourciTelephone() {
        return raccourciTelephone;
    }

    /**
     * Définit la valeur de raccourciTelephone.
     * @param raccourciTelephone la nouvelle valeur de raccourciTelephone
     */
    public void setRaccourciTelephone(String raccourciTelephone) {
        this.raccourciTelephone = raccourciTelephone;
    }

    /**
     * Retourne la valeur de numeroTelephoneFixe.
     * @return la valeur de numeroTelephoneFixe
     */
    public String getNumeroTelephoneFixe() {
        return numeroTelephoneFixe;
    }

    /**
     * Définit la valeur de numeroTelephoneFixe.
     * @param numeroTelephoneFixe la nouvelle valeur de numeroTelephoneFixe
     */
    public void setNumeroTelephoneFixe(String numeroTelephoneFixe) {
        this.numeroTelephoneFixe = numeroTelephoneFixe;
    }

    /**
     * Retourne la valeur de numeroTelephonePortable.
     * @return la valeur de numeroTelephonePortable
     */
    public String getNumeroTelephonePortable() {
        return numeroTelephonePortable;
    }

    /**
     * Définit la valeur de numeroTelephonePortable.
     * @param numeroTelephonePortable la nouvelle valeur de numeroTelephonePortable
     */
    public void setNumeroTelephonePortable(String numeroTelephonePortable) {
        this.numeroTelephonePortable = numeroTelephonePortable;
    }

    /**
     * Retourne la valeur de numeroFax.
     * @return la valeur de numeroFax
     */
    public String getNumeroFax() {
        return numeroFax;
    }

    /**
     * Définit la valeur de numeroFax.
     * @param numeroFax la nouvelle valeur de numeroFax
     */
    public void setNumeroFax(String numeroFax) {
        this.numeroFax = numeroFax;
    }

    /**
     * Retourne la valeur de agence.
     * @return la valeur de agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Définit la valeur de agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Retourne la valeur de fonction.
     * @return la valeur de fonction
     */
    public IdentifiantLibelleDto getFonction() {
        return fonction;
    }

    /**
     * Définit la valeur de fonction.
     * @param fonction la nouvelle valeur de fonction
     */
    public void setFonction(IdentifiantLibelleDto fonction) {
        this.fonction = fonction;
    }

    /**
     * Retourne la valeur de listeRoles.
     * @return la valeur de listeRoles
     */
    public List < IdentifiantLibelleDto > getListeRoles() {
        if (listeRoles == null) {
            listeRoles = new ArrayList < IdentifiantLibelleDto > ();
        }
        return listeRoles;
    }

    /**
     * Définit la valeur de listeRoles.
     * @param listeRoles la nouvelle valeur de listeRoles
     */
    public void setListeRoles(List < IdentifiantLibelleDto > listeRoles) {
        this.listeRoles = listeRoles;
    }

    /**
     * Recupere la valeur de service.
     * @return la valeur de service
     */
    public IdentifiantLibelleDto getService() {
        return service;
    }

    /**
     * Definit la valeur de service.
     * @param service la nouvelle valeur de service
     */
    public void setService(IdentifiantLibelleDto service) {
        this.service = service;
    }

    /**
     * Recupere la valeur de numeroTelephoneFixePerso.
     * @return la valeur de numeroTelephoneFixePerso
     */
    public String getNumeroTelephoneFixePerso() {
        return numeroTelephoneFixePerso;
    }

    /**
     * Definit la valeur de numeroTelephoneFixePerso.
     * @param numeroTelephoneFixePerso la nouvelle valeur de numeroTelephoneFixePerso
     */
    public void setNumeroTelephoneFixePerso(String numeroTelephoneFixePerso) {
        this.numeroTelephoneFixePerso = numeroTelephoneFixePerso;
    }

    /**
     * Recupere la valeur de numeroTelephonePortablePerso.
     * @return la valeur de numeroTelephonePortablePerso
     */
    public String getNumeroTelephonePortablePerso() {
        return numeroTelephonePortablePerso;
    }

    /**
     * Definit la valeur de numeroTelephonePortablePerso.
     * @param numeroTelephonePortablePerso la nouvelle valeur de numeroTelephonePortablePerso
     */
    public void setNumeroTelephonePortablePerso(String numeroTelephonePortablePerso) {
        this.numeroTelephonePortablePerso = numeroTelephonePortablePerso;
    }

    /**
     * Recupere la valeur de dateFinValidite.
     * @return la valeur de dateFinValidite
     */
    public Calendar getDateFinValidite() {
        return dateFinValidite;
    }

    /**
     * Definit la valeur de dateFinValidite.
     * @param dateFinValidite la nouvelle valeur de dateFinValidite
     */
    public void setDateFinValidite(Calendar dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    /**
     * Recupere la valeur de commentaires.
     * @return la valeur de commentaires
     */
    public String getCommentaires() {
        return commentaires;
    }

    /**
     * Definit la valeur de commentaires.
     * @param commentaires la nouvelle valeur de commentaires
     */
    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * Recupere la valeur de numeroVoie.
     * @return la valeur de numeroVoie
     */
    public String getNumeroVoie() {
        return numeroVoie;
    }

    /**
     * Definit la valeur de numeroVoie.
     * @param numeroVoie la nouvelle valeur de numeroVoie
     */
    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    /**
     * Recupere la valeur de codeBis.
     * @return la valeur de codeBis
     */
    public String getCodeBis() {
        return codeBis;
    }

    /**
     * Definit la valeur de codeBis.
     * @param codeBis la nouvelle valeur de codeBis
     */
    public void setCodeBis(String codeBis) {
        this.codeBis = codeBis;
    }

    /**
     * Recupere la valeur de natureVoie.
     * @return la valeur de natureVoie
     */
    public String getNatureVoie() {
        return natureVoie;
    }

    /**
     * Definit la valeur de natureVoie.
     * @param natureVoie la nouvelle valeur de natureVoie
     */
    public void setNatureVoie(String natureVoie) {
        this.natureVoie = natureVoie;
    }

    /**
     * Recupere la valeur de libelleVoie.
     * @return la valeur de libelleVoie
     */
    public String getLibelleVoie() {
        return libelleVoie;
    }

    /**
     * Definit la valeur de libelleVoie.
     * @param libelleVoie la nouvelle valeur de libelleVoie
     */
    public void setLibelleVoie(String libelleVoie) {
        this.libelleVoie = libelleVoie;
    }

    /**
     * Recupere la valeur de complementAdresse.
     * @return la valeur de complementAdresse
     */
    public String getComplementAdresse() {
        return complementAdresse;
    }

    /**
     * Definit la valeur de complementAdresse.
     * @param complementAdresse la nouvelle valeur de complementAdresse
     */
    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }

    /**
     * Recupere la valeur de localite.
     * @return la valeur de localite
     */
    public String getLocalite() {
        return localite;
    }

    /**
     * Definit la valeur de localite.
     * @param localite la nouvelle valeur de localite
     */
    public void setLocalite(String localite) {
        this.localite = localite;
    }

    /**
     * Recupere la valeur de departement.
     * @return la valeur de departement
     */
    public String getDepartement() {
        return departement;
    }

    /**
     * Definit la valeur de departement.
     * @param departement la nouvelle valeur de departement
     */
    public void setDepartement(String departement) {
        this.departement = departement;
    }

    /**
     * Recupere la valeur de codePostal.
     * @return la valeur de codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Definit la valeur de codePostal.
     * @param codePostal la nouvelle valeur de codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Recupere la valeur de bureauDistributeur.
     * @return la valeur de bureauDistributeur
     */
    public String getBureauDistributeur() {
        return bureauDistributeur;
    }

    /**
     * Definit la valeur de bureauDistributeur.
     * @param bureauDistributeur la nouvelle valeur de bureauDistributeur
     */
    public void setBureauDistributeur(String bureauDistributeur) {
        this.bureauDistributeur = bureauDistributeur;
    }

    /**
     * Recupere la valeur de valide.
     * @return la valeur de valide
     */
    public Boolean getValide() {
        return valide;
    }

    /**
     * Definit la valeur de valide.
     * @param valide la nouvelle valeur de valide
     */
    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    /**
     * Recupere la valeur de dateReactivation.
     * @return la valeur de dateReactivation
     */
    public Calendar getDateReactivation() {
        return dateReactivation;
    }

    /**
     * Definit la valeur de dateReactivation.
     * @param dateReactivation la nouvelle valeur de dateReactivation
     */
    public void setDateReactivation(Calendar dateReactivation) {
        this.dateReactivation = dateReactivation;
    }

    /**
     * Retourne la valeur de reseau.
     * @return la valeur de reseau
     */
    public IdentifiantLibelleDto getReseau() {
        return reseau;
    }

    /**
     * Définit la valeur de reseau.
     * @param reseau la nouvelle valeur de reseau
     */
    public void setReseau(IdentifiantLibelleDto reseau) {
        this.reseau = reseau;
    }

    /**
     * Recupere la valeur de validitePermanente.
     * @return la valeur de validitePermanente
     */
    public Boolean getValiditePermanente() {
        return validitePermanente;
    }

    /**
     * Definit la valeur de validitePermanente.
     * @param validitePermanente la nouvelle valeur de validitePermanente
     */
    public void setValiditePermanente(Boolean validitePermanente) {
        this.validitePermanente = validitePermanente;
    }

    /**
     * Get the codeAIAUtilisateur value.
     * @return the codeAIAUtilisateur
     */
    public String getCodeAIAUtilisateur() {
        return codeAIAUtilisateur;
    }

    /**
     * Set the codeAIAUtilisateur value.
     * @param codeAIAUtilisateur the codeAIAUtilisateur to set
     */
    public void setCodeAIAUtilisateur(String codeAIAUtilisateur) {
        this.codeAIAUtilisateur = codeAIAUtilisateur;
    }

    /**
     * Get the codeAIAAgence value.
     * @return the codeAIAAgence
     */
    public String getCodeAIAAgence() {
        return codeAIAAgence;
    }

    /**
     * Set the codeAIAAgence value.
     * @param codeAIAAgence the codeAIAAgence to set
     */
    public void setCodeAIAAgence(String codeAIAAgence) {
        this.codeAIAAgence = codeAIAAgence;
    }

    /**
     * Get the dateDerniereConnexion value.
     * @return the dateDerniereConnexion
     */
    public Calendar getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }

    /**
     * Set the dateDerniereConnexion value.
     * @param dateDerniereConnexion the dateDerniereConnexion to set
     */
    public void setDateDerniereConnexion(Calendar dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

	/**
	 * Récupère la valeur de codeAcp.
	 * @return the codeAcp
	 */
	public String getCodeAcp() {
		return codeAcp;
	}

	/**
	 * Définit la valeur de codeAcp.
	 * @param codeAcp the codeAcp to set
	 */
	public void setCodeAcp(String codeAcp) {
		this.codeAcp = codeAcp;
	}

    /**
     * Renvoie la valeur de droitModifPassword.
     * @return la valeur de droitModifPassword
     */
    public Boolean getDroitModifPassword() {
        return droitModifPassword;
    }

    /**
     * Définit la valeur de droitModifPassword.
     * @param droitModifPassword definie la nouvelle valeur de droitModifPassword to set
     */
    public void setDroitModifPassword(Boolean droitModifPassword) {
        this.droitModifPassword = droitModifPassword;
    }

}
