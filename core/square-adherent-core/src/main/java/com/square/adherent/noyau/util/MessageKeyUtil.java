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
package com.square.adherent.noyau.util;

/**
 * Classe utilitaire regroupant les clés des messages de l'application.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public final class MessageKeyUtil {

    /** Message d'erreur. */
    public static final String ERROR_GET_INFOS_ESPACE_ADHERENT_UID_PERSONNE_NULL = "error.get.infos.espace.adherent.uid.personne.null";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_UID_PERSONNE_NULL = "error.envoyer.mot.de.passe.uid.personne.null";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_AUCUN_MODE_ENVOI_SPECIFIE = "error.envoyer.mot.de.passe.aucun.mode.envoi.specifie";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_ESPACE_CLIENT_INTERNET_NULL = "error.envoyer.mot.de.passe.connexion.null";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_ESPACE_CLIENT_INTERNET_DESACTIVEE = "error.envoyer.mot.de.passe.connexion.desactivee";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_PERSONNE_NULL = "error.envoyer.mot.de.passe.personne.null";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_COORDONNEES_PERSONNE_NULL = "error.envoyer.mot.de.passe.coordonnees.personne.null";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_AUCUN_EMAIL = "error.envoyer.mot.de.passe.aucun.email";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_ECHEC_ENVOI_EMAIL = "error.envoyer.mot.de.passe.echec.envoi.email";

    /** Message d'erreur. */
    public static final String ERROR_ENVOYER_MOT_DE_PASSE_AUCUN_TELEPHONE = "error.envoyer.mot.de.passe.aucun.telephone";

    /** Message d'erreur. */
    public static final String ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_NULL = "error.maj.options.adherent.uid.personne.null";

    /** Message d'erreur. */
    public static final String ERROR_MAJ_OPTIONS_ADHERENT_TYPE_OPTION_INCONNU = "error.maj.options.adherent.type.option.inconnu";

    /** Message d'erreur. */
    public static final String ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_EMAIL_REQUIS = "error.maj.options.adherent.uid.personne.email.requis";

    /** Message d'erreur. */
    public static final String ERROR_MAJ_OPTIONS_ADHERENT_UID_PERSONNE_TELEPHONE_MOBILE_REQUIS =
    	"error.maj.options.adherent.uid.personne.telephone.mobile.requis";

    /** Message d'erreur lors de l'identification. */
    public static final String ERROR_IDENTIFICATION_LOGIN_MDP_INCORRECTS = "error.identification.login.mdp.incorrects";

    /** Message d'erreur d'un compte inexistant pour un adhérent. */
    public static final String ERROR_COMPTE_INEXITANT = "error.compte.inexistant";

    /** Message d'erreur lorsqu'un adhérent n'a pas d'identifiants de connexion. */
    public static final String ERROR_MESSAGE_ESPACE_CLIENT_INTERNET_ADHERENT_INEXISTANT = "error.message.connexion.adherent.inexistant";

    /** Message d'erreur lorsque le numéro d'adhérent n'est pas renseigné. */
    public static final String ERROR_IDENTIFICATION_LOGIN_INCORRECT = "error.identification.login.incorrect";

    /** Message d'erreur d'un souscripteur de contrat inexistant. */
    public static final String ERROR_SOUSCRIPTEUR_CONTRAT_INEXISTANT = "error.souscripteur.contrat.inexistant";

    /** Message d'erreur lorsque la recherche d'un produit ne retourne pas un seul produit. */
    public static final String ERROR_RECUPERATION_PRODUIT_IMPOSSIBLE = "error.recuperation.produit.impossible";

    /** Message d'erreur lorsque la recherche d'un bénéficiaire ne retourne rien. */
    public static final String ERROR_BENEFICIAIRE_CONTRAT_INEXISTANT = "error.beneficiaire.contrat.inexistant";

    /** Message d'erreur lorsque la sunthèse d'un contrat n'est pas trouvée. */
    public static final String ERROR_SYNTHESE_CONTRAT_INEXISTANTE = "error.synthese.contrat.inexistante";

    /** Message d'erreur lorsqu'un adhérent est inexistant. */
    public static final String ERROR_MESSAGE_ADHERENT_INEXISTANT = "erreur.message.adherent.inexistant";

    /** Message d'erreur lorsqu'un moyen de paiement est inexistant. */
    public static final String ERROR_MOYEN_PAIEMENT_INEXISTANT = "error.moyen.paiement.inexistant";

    /** Message d'erreur lorsqu'une garantie est inexistante. */
    public static final String ERROR_GARANTIE_INEXISTANTE = "error.garantie.inexistante";

    /** Message d'erreur lorsqu'une personne est inexistante. */
    public static final String ERROR_PERSONNE_INEXISTANT = "error.personne.inexistante";

    /** Message d'erreur lorsqu'il manque le critere personne. */
    public static final String ERROR_PERSONNE_OBLIGATOIRE = "error.personne.obligatoire";

    /** Message d'erreur lors de l'appel au service pour créer un espace client mais la personne ne possède pas d'adresse email personnelle. */
    public static final String ERROR_CREER_ESPACE_CLIENT_EMAIL_PERSONNE_REQUIS = "error.creer.espace.client.email.personne.requis";

    /** Message d'erreur lors de l'appel au service pour maj un espace client mais l'espace client n'existe pas. */
    public static final String ERROR_MAJ_ESPACE_CLIENT_ESPACE_CLIENT_INTROUVABLE = "error.maj.espace.client.espace.client.introuvable";

    /** Message d'erreur lors de l'appel au service pour maj un espace client mais le nouveau login est déjà utilisé. */
    public static final String ERROR_MAJ_ESPACE_CLIENT_LOGIN_DEJA_UTILISE = "error.maj.espace.client.login.deja.utilise";

    /** Message d'erreur. */
    public static final String ERROR_FUSION_PERSONNE_SOURCE_INEXISTANTE = "error.fusion.personne.source.inexistante";

    /** Message d'erreur. */
    public static final String ERROR_FUSION_PERSONNE_CIBLE_INEXISTANTE = "error.fusion.personne.cible.inexistante";

    /** Message d'erreur lors d'une demande de prise en charge sans que l'identifiant de l'adhérent à l'origine de la demande ne soit spécifié. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_ID_ADHERENT = "error.demande.prise.en.charge.prerequis.id.adherent";

    /** Message d'erreur lors d'une demande de prise en charge mais que l'adhérent à l'origine de la demande est introuvable. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_ADHERENT_INTROUVABLE = "error.demande.prise.en.charge.adherent.introuvable";

    /** Message d'erreur lors d'une demande de prise en charge sans que l'identifiant de la personne à prendre en charge ne soit spécifié. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_ID_PERSONNE_A_PRENDRE_EN_CHARGE =
        "error.demande.prise.en.charge.prerequis.id.personne.a.prendre.en.charge";

    /** Message d'erreur lors d'une demande de prise en charge mais que la personne à prendre en charge est introuvable. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PERSONNE_A_PRENDRE_EN_CHARGE_INTROUVABLE =
        "error.demande.prise.en.charge.personne.a.prendre.en.charge.introuvable";

    /** Message d'erreur lors d'une demande de prise en charge sans préciser le nom de l'établissement. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_NOM_ETABLISSEMENT = "error.demande.prise.en.charge.prerequis.nom.etablissement";

    /** Message d'erreur lors d'une demande de prise en charge sans préciser l'adresse de l'établissement. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_ADRESSE_ETABLISSEMENT = "error.demande.prise.en.charge.prerequis.adresse.etablissement";

    /** Message d'erreur lors d'une demande de prise en charge sans préciser la ville de l'établissement. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_VILLE_ETABLISSEMENT = "error.demande.prise.en.charge.prerequis.ville.etablissement";

    /** Message d'erreur lors d'une demande de prise en charge sans préciser la date d'éntrée dans l'établissement. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_DATE_ENTREE = "error.demande.prise.en.charge.prerequis.date.entree";

    /** Message d'erreur lors d'une demande de prise en charge sans préciser la nature des soins. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_NATURE_SOINS = "error.demande.prise.en.charge.prerequis.nature.soins";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser l'identifiant de la ressource ayant permis la demande de prise en charge. */
    public static final String ERROR_DEMANDE_PRISE_EN_CHARGE_PREREQUIS_ID_CREATEUR =
        "error.demande.prise.en.charge.prerequis.id.createur";

    /** Message d'erreur lors d'une demande de prise en charge sans préciser l'identifiant de l'adhérent auquel le bénéficiaire doit être rattaché. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_ID_ADHERENT = "error.ajouter.beneficiaire.id.adherent.prerequis";

    /** Message d'erreur lors d'un ajout de bénéficiaire mais que l'adhérent auquel le bénéficiaire doit être rattaché est introuvable. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_ADHERENT_INTROUVABLE = "error.ajouter.beneficiaire.adherent.introuvable";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser les infos du bénéficiaire. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_BENEFICIAIRE_DTO = "error.ajouter.beneficiaire.prerequis.beneficiaire.dto";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser la civilité du bénéficiaire. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_CIVILITE_BENEFICIAIRE =
        "error.ajouter.beneficiaire.prerequis.civilite.beneficiaire";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser le nom du bénéficiaire. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_NOM_BENEFICIAIRE = "error.ajouter.beneficiaire.prerequis.nom.beneficiaire";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser le prénom du bénéficiaire. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_PRENOM_BENEFICIAIRE = "error.ajouter.beneficiaire.prerequis.prenom.beneficiaire";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser la date de naissance du bénéficiaire. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_DATE_NAISSANCE_BENEFICIAIRE =
        "error.ajouter.beneficiaire.prerequis.date.naissance.beneficiaire";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser le type de relation entre l'adhérent et le bénéficiaire. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_ID_TYPE_RELATION =
        "error.ajouter.beneficiaire.prerequis.type.relation";

    /** Message d'erreur lors d'un ajout de bénéficiaire sans préciser l'identifiant de la ressource responsable de la création du bénéficiaire. */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_ID_CREATEUR =
        "error.ajouter.beneficiaire.prerequis.id.createur";

    /** Message d'erreur lors d'un ajout de bénéficiaire avec une relation de type conjoint alors que l'adhérent a déjà un(e) conjoint(e). */
    public static final String ERROR_AJOUTER_BENEFICIAIRE_PREREQUIS_RELATION_CONJOINT_EXISTANTE =
        "error.ajouter.beneficiaire.prerequis.relation.conjoint.existante";

    /** Message d'erreur lors de la récupération des coordonnées bancaires de remboursement sans spécifier l'identifiant de l'adhérent. */
    public static final String ERROR_GET_COORDONNEES_BANCAIRES_REMBOURSEMENT_PREREQUIS_UID_ADHERENT =
        "error.get.coordonnees.bancaires.remboursement.prerequis.uid.adherent";

    /** Message d'erreur. */
    public static final String ERROR_PERSONNE_NUM_CLIENT_INEXISTANTE = "error.personne.num.client.inexistantes";

    /** Message Logger d'erreur. */
    public static final String LOGGER_ERROR_TYPE_OPTION_INVALIDE = "logger.error.type.option.invalide";

    /** Message Logger de debug. */
    public static final String LOGGER_DEBUG_RECUP_BENEFICIAIRE_PERSONNE = "logger.debug.recup.beneficiaire.personne";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_ADHERENT_PRINCIPAL_PERSONNE = "logger.debug.recup.adherent.principal.personne";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_INFO_RESERVE_BANCO_ADHERENT = "logger.debug.recup.info.reserve.banco.adherent";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_MAJ_MDP_PERSONNE = "logger.debug.maj.mdp.personne";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_OPTIONS_SOUSCRITES_PERSONNE = "logger.debug.recup.options.souscrites.personne";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_MODIF_INFO_COMPTE_PERSONNE = "logger.debug.modif.info.compte.personne";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_DESACTIVATION_OPTION_ENVOIE_EMAIL_PERSONNE = "logger.debug.desactivation.option.envoie.email.personne";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_DESACTIVATION_OPTION_ENVOIE_TEL_PERSONNE = "logger.debug.desactivation.option.envoie.tel.personne";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_LISTE_ADHERENT_SOUSCRIT_OPTION = "logger.debug.recup.liste.adherent.souscrit.option";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_LISTE_ADHERENT_RECU_MAGAZINE = "logger.debug.recup.liste.adherent.recu.magazine";

    /** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_PERSONNE_A_NOTIFIER_PAR_SMS_PAR_CRITERE = "logger.debug.recup.personne.a.notifier.par.sms.par.critere";

    /** Message Logger d'erreur. */
	public static final String LOGGER_ERROR_ADHERENT_INTROUVABLE = "logger.error.adherent.introuvable";

    /** Message Logger d'erreur. */
	public static final String LOGGER_ERROR_PERSONNE_PRENDRE_CHARGE_INTROUVABLE = "logger.error.personne.prendre.charge.introuvable";

    /** Message Logger d'info. */
	public static final String LOGGER_INFO_DEMANDE_PRISE_CHARGE_ADHERENT = "logger.info.demande.prise.charge.adherent";

    /** Message descriptif. */
	public static final String MESSAGE_DESCRIPTIF_ADHERENT = "message.descriptif.adherent";

	/** Message descriptif. */
	public static final String MESSAGE_DESCRIPTIF_BENEFICIAIRE = "message.descriptif.beneficiaire";

    /** Format de date. */
	public static final String FORMAT_DATE_DD_MM_YYYY = "format.date.dd.mm.yyyy";

    /** Format de date. */
	public static final String FORMAT_DATE_MM = "format.date.mm";

	 /** Format de date. */
	public static final String FORMAT_DATE_DD_MM_YYYY_HH_MM_SS = "format.date.dd.mm.yyyy.hh.mm.ss";

	/** Format de date. */
	public static final String FORMAT_DATE_YYYYMMDD = "format.date.yyyymmdd";

    /** Message Logger d'erreur. */
	public static final String LOGGER_INFO_NOTIFICATION_DEMANDE_PECHARGE_ENVOYER_ADRESSE = "logger.info.notification.demande.pecharge.envoyer.adresse";

    /** Message Logger d'erreur. */
	public static final String LOGGER_ERROR_ENVOI_MAIL_NOTIFICATION_DEMANDE_PECHARGE = "logger.error.envoi.mail.notification.demande.pecharge";

    /** Message Logger de warning. */
	public static final String LOGGER_WARN_AUCUN_MAIL_NOTIFICATION_DEMANDE_PECHARGE_ENVOYER_ADHERENT_NO_ADRESSE =
		"logger.warn.aucun.mail.notification.demande.pecharge.envoyer.adherent.no.adresse";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_CONTRAT_SANTE_PERSONNE = "logger.debug.recup.contrat.sante.personne";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_INFO_PRODUIT_PAR_CRITERES = "logger.debug.recup.info.produit.par.criteres";

	/** Message Logger de erreur. */
	public static final String LOGGER_ERROR_RECUP_PRODUIT = "logger.error.recup.produit";

	/** Message Logger de info. */
	public static final String LOGGER_INFO_RECUP_LISTE_CONTRAT_ENTREPRISE = "logger.info.recup.liste.contrat.entreprise";

	/** Message Logger de info. */
	public static final String LOGGER_INFO_RECUP_LISTE_POPULATION_CONTRAT_ENTREPRISE = "logger.info.recup.liste.population.contrat.entreprise";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_DEMANDE_AUTHENTIFICATION_LOGIN = "logger.debug.demande.authentification.login";

	/** Message Logger de error. */
	public static final String LOGGER_ERROR_AUTHENTIFICATION = "logger.error.authentification";

	/** Message Logger de error. */
	public static final String LOGGER_ERROR_AUTHENTIFICATION_AUCUN_CONTRAT = "logger.error.authentification.aucun.contrat";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_PERSONNE_AUTHENTIFIE = "logger.debug.personne.authentifie";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_MAJ_STATS_ESPACE_CLIENT = "logger.debug.maj.stats.espace.client";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_INFO_CONNECTION_SIMPLE_CLIENT = "logger.debug.recup.info.connection.simple.client";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_INFO_CLIENT_CONNECTER = "logger.debug.recup.info.client.connecter";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_FUSION_ESPACE_CLIENT_PERSONNE_SOURCE = "logger.debug.fusion.espace.client.personne.source";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_ABSCENCE_CONNEXION_CIBLE = "logger.debug.abscence.connexion.cible";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_ABSCENCE_CONNEXION_SOURCE = "logger.debug.abscence.connexion.source";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_DEUX_PROSPECT_ESPACE_CLIENT_CONSERVE_SOURCE = "logger.debug.deux.prospect.espace.client.conserve.source";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_DEUX_PROSPECT_ESPACE_CLIENT_CONSERVE_CIBLE = "logger.debug.deux.prospect.espace.client.conserve.cible";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_CONNECTION_SOURCE_PROSPECT_CIBLE_ADHERENT_CONSERVE_CIBLE =
		"logger.debug.connection.source.prospect.cible.adherent.conserve.cible";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_CONNECTION_SOURCE_ADHERENT_CIBLE_PROSPECT_CONSERVE_SOURCE =
		"logger.debug.connection.source.adherent.cible.prospect.conserve.cible";

	/** Message Logger de info. */
	public static final String LOGGER_INFO_IDENTIFIANT_CONNECTION_ESPACE_CLIENT_PERSONNE_ENVOYE_MAIL =
		"logger.info.connection.espace.client.personne.envoye.mail";

	/** Message Logger de error. */
	public static final String LOGGER_ERROR_ECHEC_ENVOIE_MAIL_MDP_ADHERENT = "logger.error.echec.envoie.mail.mdp.adherent";

	/** Message. */
	public static final String MESSAGE_SMS_ADHERENT = "message.sms.adherent";

	/** Message Logger de info. */
	public static final String LOGGER_INFO_IDENTIFIANT_CONNECTION_ESPACE_CLIENT_PERSONNE_ENVOYE_SMS =
		"logger.info.connection.espace.client.personne.envoye.sms";

	/** Message Logger de info. */
	public static final String LOGGER_INFO_NOMBRE_PRESTATION_RECUPERER = "logger.info.nombre.prestation.recuperer";

	/** Message Logger de info. */
	public static final String LOGGER_INFO_NOMBRE_ENTETE_PRESTATION_RECUPERER = "logger.info.nombre.entete.prestation.recuperer";

	/** Message d'erreur. */
	public static final String ERROR_RECUPERATION_FICHIER = "error.recuperation.fichier";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUP_RELEVE_PRESTATION_CRITERES = "logger.debug.recup.releve.prestation.criteres";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_ENVOIE_RELEVES_PRESTATIONS_EMAIL = "logger.debug.envoie.releves.prestations.email";

	/** Message d'erreur. */
	public static final String ERROR_ADHERENT_NON_TROUVE = "error.adherent.non.trouve";

	/** Message d'erreur. */
	public static final String ERROR_OPTION_ENVOIE_MAIL_DESACTIVER = "error.option.envoie.mail.desactiver";

	/** Message d'erreur. */
	public static final String ERROR_ABSCENCE_RELEVE_A_ENVOYER = "error.abscence.releve.a.envoyer";

	/** Message d'erreur. */
	public static final String ERROR_ABSCENCE_ADRESSE_EMAIL_ADHERENT = "error.abscence.adresse.email.adherent";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_EMAIL_ADHERENT = "logger.debug.email.adherent";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_MAJ_TEMOIN_ENVOI_RELEVE_PRESTATION = "logger.debug.maj.temoin.releve.prestation";

	/** Message Logger d'erreur. */
	public static final String LOGGER_ERROR_RECUPERATION_RELEVE_PRESTATION = "logger.error.recuperation.releve.prestation";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_CONVERSION_RELEVE_PRESTATION = "logger.debug.conversion.releve.prestation";

	/** Message d'erreur. */
	public static final String ERROR_IMPOSSIBLE_AJOUTER_MENTION_DUPLICATA = "error.impossible.ajouter.mention.duplicata";

	/** Message d'erreur. */
	public static final String ERROR_ABSCENCE_RELEVE_PRESTATION_PERSONNE = "error.abscence.releve.prestation.personne";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_COMPTE_NOMBRE_CANDIDAT_ENVOIE_MAIL = "logger.debug.compte.nombre.candidat.envoie.mail";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_RECUPERATION_CANDIDAT_ENVOIE_RELEVE_PRESTATION_MAIL =
		"logger.debug.recuperation.candidat.envoie.releve.prestation.mail";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_AJOUT_RELEVE_PRESTATION = "logger.debug.ajout.releve.prestation";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_IMPOSSIBLE_AJOUTER_RELEVE_PRESTATION_RESULTAT_NULL =
		"logger.debug.impossible.ajouter.releve.prestation.resultat.null";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_IMPOSSIBLE_AJOUTER_RELEVE_PRESTATION_NB_RESULTAT =
		"logger.debug.impossible.ajouter.releve.prestation.nb.resultat";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_IMPOSSIBLE_AJOUTER_RELEVE_PRESTATION_ABSCENCE_PERSONNE =
		"logger.debug.impossible.ajouter.releve.prestation.abscence.personne";

	/** Message . */
	public static final String MESSAGE_NOM_FICHIER = "message.nom.fichier";

	/** Message d'erreur. */
	public static final String ERROR_MOYEN_PAIEMENT_INCONNU = "error.moyen.paiement.inconnu";

	/** Message d'erreur. */
	public static final String ERROR_CREATION_FICHIER_EXISTE_BASE = "error.creation.fichier.existe.base";

	/** Message Logger de debug. */
	public static final String LOGGER_DEBUG_ENVOIE_RELEVE_PRESTATION_MAIL = "logger.debug.envoie.releve.prestation.mail";

	/** Message d'erreur. */
	public static final String ERROR_AUCUN_ADHERENT_TROUVE = "error.aucun.adherent.trouve";

	/** Message Logger de info. */
	public static final String LOGGER_INFO_DEMANDE_INFO_ADHERENT_AVEC_LOGIN = "logger.info.demande.info.adherent.avec.login";

	/** Message. */
	public static final String MESSAGE_LIBELLE_PRODUIT_NON_TROUVE = "message.libelle.produit.non.trouve";

	/** Message. */
	public static final String ERROR_RECUPERATION_GARANTIES_BAREMES_CRITERES_NULL = "error.recuperation.garanties.baremes.criteres.null";

	/** Message. */
	public static final String ERROR_CONTRAT_INEXISTANT = "error.contrat.inexistant";

	/** Message. */
	public static final String ERROR_CREER_ESPACE_CLIENT_PARAM_ID_PERSONNE_REQUIS = "error.creer.espace.client.param.uid.personne.requis";

	/** Message. */
	public static final String ERROR_CREER_ESPACE_CLIENT_ESPACE_CLIENT_DEJA_EXISTANT = "error.creer.espace.client.espace.client.deja.existant";
	/**
     * Constructeur privé.
     */
    private MessageKeyUtil() {
        super();
    }

}
