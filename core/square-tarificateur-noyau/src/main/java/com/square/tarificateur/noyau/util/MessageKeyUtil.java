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
package com.square.tarificateur.noyau.util;

/**
 * Classe utilitaire qui regroupe l'ensemble des clés pour les messages.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public final class MessageKeyUtil {

	/** Constructeur. */
    private MessageKeyUtil() {
        super();
    }

    /** Message d'erreur lorsqu'on essaye de créer/récupérer une opportunité sans spécifier les infos de l'opportunité. */
    public static final String ERROR_GET_CREATE_OPPORTUNITE_INFOS_OPPORTUNITE_NULL = "error.get.create.opportunite.infos.opportunite.null";

    /** Message d'erreur lorsqu'on essaye de créer/récupérer une opportunité sans spécifier l'identifiant externe de l'opportunité Square. */
    public static final String ERROR_GET_CREATE_OPPORTUNITE_EID_OPPORTUNITE_NULL = "error.get.create.opportunite.eid.opportunite.null";

    /** Message d'erreur lorsqu'on essaye de créer/récupérer une opportunité sans spécifier la personne. */
    public static final String ERROR_GET_CREATE_OPPORTUNITE_PERSONNE_NULL = "error.get.create.opportunite.personne.null";

    /** Message d'erreur lorsqu'on essaye de créer/récupérer une opportunité sans spécifier le responsable. */
    public static final String ERROR_GET_CREATE_OPPORTUNITE_EID_AGENCE_NULL = "error.get.create.opportunite.eid.agence.null";

    /** Message d'erreur lorsqu'on essaye de créer/récupérer une opportunité sans spécifier le créateur. */
    public static final String ERROR_GET_CREATE_OPPORTUNITE_EID_CREATEUR_NULL = "error.get.create.opportunite.eid.createur.null";

    /** Message d'erreur lorsqu'on essaye de créer un devis sans spécifier les informations de l'opportunité. */
    public static final String ERROR_CREATE_DEVIS_INFOS_OPPORTUNITE_NULL = "error.create.devis.infos.opportunite.null";

    /** Message d'erreur lorsqu'on essaye de créer un devis sans spécifier l'identifiant externe de l'opportunité. */
    public static final String ERROR_CREATE_DEVIS_EID_OPPORTUNITE_NULL = "error.create.devis.eid.opportunite.null";

    /** Message d'erreur lorsqu'on essaye de créer un devis associé à une opportunité qui n'existe pas. */
    public static final String ERROR_CREATE_DEVIS_OPPORTUNITE_NULL = "error.create.devis.opportunite.null";

    /** Message d'erreur lorsqu'on essaye de créer un devis sans spécifier la personne. */
    public static final String ERROR_CREATE_DEVIS_PERSONNE_NULL = "error.create.devis.personne.null";

    /** Message d'erreur lors de la récupération des produits d'un adhérent. */
    public static final String ERROR_GET_LISTE_PRODUITS_ADHERENT = "error.get.liste.produits.adherent";

    /** Message d'erreur devis inexistant pour clôture. */
    public static final String ERROR_DEMANDE_CLOTURE_IMPOSSIBLE_DEVIS_INEXISTANT = "error.demande.cloture.impossible.devis.inexistant";

    /** Message d'erreur motif devis non renseigné pour clôture. */
    public static final String ERROR_DEMANDE_CLOTURE_IMPOSSIBLE_MOTIF_DEVIS_NON_RENSEIGNE = "error.demande.cloture.impossible.motif.devis.non.renseigne";

    /** Message d'erreur ligne de devis inexistante pour clôture. */
    public static final String ERROR_DEMANDE_CLOTURE_IMPOSSIBLE_LIGNE_INEXISTANTE = "error.demande.cloture.impossible.ligne.inexistante";

    /** Message d'erreur lorsque l'opportunité possède déjà un devis accepté. */
    public static final String ERROR_OPPORTUNITE_POSSEDE_DEJA_DEVIS_ACCEPTE = "error.opportunite.possede.deja.devis.accepte";

    /** Message d'erreur. */
    public static final String ERROR_BENEFICIAIRE_DATE_NAISSANCE_NULLE = "error.beneficiaire.date.naissance.nulle";

    /** Message d'erreur. */
    public static final String ERROR_BENEFICIAIRE_INEXISTANT = "error.beneficiaire.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_IMPOSSIBLE_RECUPERER_PRODUIT = "error.impossible.recuperer.produit";

    /** Message d'erreur. */
    public static final String ERROR_AUCUN_IDENTIFIANT_PROSPECT_INDIQUE = "error.aucun.identifiant.prospect.indique";

    /** Message d'erreur. */
    public static final String ERROR_AUCUN_IDENTIFIANT_BENEFICIAIRE_INDIQUE = "error.aucun.identifiant.beneficiaire.indique";

    /** Message d'erreur. */
    public static final String ERROR_PROSPECT_INEXISTANT = "error.prospect.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_DEVIS_INEXISTANT = "error.devis.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_DATE_EFFET_PRINCIPAL_INF_DATE_EFFET_LIEE = "error_date_effet_principal_inf_date_effet_liee";

    /** Message d'erreur. */
    public static final String ERROR_RECUPERATION_TARIF_IMPOSSIBLE = "error.recuperation.tarif.impossible";

    /** Message d'erreur. */
    public static final String ERROR_CALCUL_MONTANT_LIGNE_DEVIS_IMPOSSIBLE = "error.calcul.montant.ligne.devis.impossible";

    /** Message d'erreur. */
    public static final String ERROR_BENEFICIAIRE_SANS_LIEN_FAMILIAL = "error.beneficiaire.sans.lien.familial";

    /** Message d'erreur. */
    public static final String ERROR_SUPPRESSION_LIGNE_IMPOSSIBLE_LIGNE_INEXISTANTE = "error.suppression.ligne.impossible.ligne.inexistante";

    /** Message d'erreur. */
    public static final String ERROR_SUPPRESSION_LIGNE_DEVIS_AVEC_FINALITE_RENSEIGNEE_IMPOSSIBLE =
        "error.suppression.ligne.devis.avec.finalite.renseignee.impossible";

    /** Message d'erreur. */
    public static final String ERROR_SUPPRESSION_PROPOSITION_LIEE_ADHESION_INTERNET_IMPOSSIBLE =
        "error.suppression.proposition.liee.adhesion.internet.impossible";

    /** Message d'erreur. */
    public static final String ERROR_DUREE_PRET_INCORRECTE = "error.duree.pret.incorrecte";

    /** Message d'erreur. */
    public static final String ERROR_PROPOSITION_ADHERENT_PROSPECT_DEVIS_NON_ADHERENT = "error.proposition.adherent.prospect.devis.non.adherent";

    /** Message d'erreur. */
    public static final String ERROR_PROPOSITION_ADHERENT_IMPOSSIBLE_DEVIS_CNP = "error.proposition.adherent.impossible.devis.cnp";

    /** Message d'erreur. */
    public static final String ERROR_PROPOSITION_ADHERENT_IMPOSSIBLE_AUCUN_PRODUIT_PRINCIPAL = "error.proposition.adherent.impossible.aucun.produit.principal";

    /** Message d'erreur. */
    public static final String ERROR_PROPOSITION_ADHERENT_IMPOSSIBLE_BENEFICIAIRE_INCONNU = "error.proposition.adherent.impossible.beneficiaire.inconnu";

    /** Message d'erreur. */
    public static final String ERROR_AUCUN_DEVIS_CORRESPONDANT = "error.aucun.devis.correspondant";

    /** Message d'erreur. */
    public static final String ERROR_AUCUNE_LIGNE_DEVIS_CORRESPONDANT = "error.aucune.ligne.devis.correspondant";

    /** Message d'erreur. */
    public static final String ERROR_AUCUNE_PERSONNE_PRINCIPALE_POUR_DEVIS = "error.aucune.personne.principale.pour.devis";

    /** Message d'erreur. */
    public static final String ERROR_AUCUNE_OPPORTUNITE_POUR_DEVIS = "error.aucune.opportunite.pour.devis";

    /** Message d'erreur. */
    public static final String ERROR_AUCUNE_ADHESION_POUR_OPPORTUNITE = "error.aucune.adhesion.pour.opportunite";

    /** Message d'erreur. */
    public static final String ERROR_MOYEN_PAIEMENT_INEXISTANT = "error.moyen.paiement.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_PERIODICITE_PAIEMENT_INEXISTANT = "error.periodicite.paiement.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_JOUR_PAIEMENT_INEXISTANT = "error.jour.paiement.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_PERSONNE_INEXISTANTE = "error.personne.inexistante";

    /** Message d'erreur. */
    public static final String ERROR_VALIDATION_NUMERO_SECU_PERSONNE = "error.validation.numero.secu.personne";

    /** Message d'erreur. */
    public static final String ERROR_COMPARAISON_FAMILLES = "error.comparaison.familles";

    /** Message d'erreur. */
    public static final String ERROR_EID_PERSONNE_BENEFICIAIRE_NON_RENSEIGNE = "error.eid.personne.beneficiaire.non.renseigne";

    /** Message d'erreur. */
    public static final String ERROR_GET_LISTE_CAISSES_ID_PERSONNE_NULL = "error.get.liste.caisses.id.personne.null";

    /** Message d'erreur. */
    public static final String ERROR_GET_LISTE_CAISSES_PERSONNE_NULL = "error.get.liste.caisses.personne.null";

    /** Message d'erreur. */
    public static final String ERROR_GET_LISTE_CAISSES_PERSONNE_ADRESSE_PRINCIPALE_NULL = "error.get.liste.caisses.personne.adresse.principale.null";

    /** Message d'erreur. */
    public static final String ERROR_CREATE_DEVIS_LIEN_FAMILIAL_NULL = "error.create.devis.lien.familial.null";

    /** Erreur générique lors de la transformation AIA. */
    public static final String ERREUR_TRANSFO_AIA = "erreur.transfo.aia";

    /** Message d'erreur "Finalité du devis accepté obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_FINALITE_DEVIS_ACCEPTE_OBLIGATOIRE = "erreur.transfo.aia.finalite.devis.accepte.obligatoire";

    /** Message d'erreur "Au moins une ligne de devis accepté obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_UNE_LIGNE_DEVIS_ACCEPTE_OBLIGATOIRE = "erreur.transfo.aia.une.ligne.devis.accepte.obligatoire";

    /** Message d'erreur "Fréquence de paiement obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_FREQUENCE_PAIEMENT_OBLIGATOIRE = "erreur.transfo.aia.frequence.paiement.obligatoire";

    /** Message d'erreur "Mode de paiement obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_MODE_PAIEMENT_OBLIGATOIRE = "erreur.transfo.aia.mode.paiement.obligatoire";

    /** Message d'erreur "Jour de paiement obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_JOUR_PAIEMENT_OBLIGATOIRE = "erreur.transfo.aia.jour.paiement.obligatoire";

    /** Message d'erreur "Date de signature obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_DATE_SIGNATURE_OBLIGATOIRE = "erreur.transfo.aia.date.signature.obligatoire";

    /** Message d'erreur "Numéro Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_NUMERO_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.numero.personne.obligatoire";

    /** Message d'erreur "Nom Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_NOM_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.nom.personne.obligatoire";

    /** Message d'erreur "Prénom Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_PRENOM_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.prenom.personne.obligatoire";

    /** Message d'erreur "Numéro Sécu Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_NUM_SECU_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.num.secu.personne.obligatoire";

    /** Message d'erreur "Année naissance incohérente avec le numéro de sécu". */
    public static final String ERREUR_TRANSFO_AIA_ANNEE_NUM_SECU_PERSONNE_INCOHERENTE = "erreur.transfo.aia.annee.num.secu.personne.incoherente";

    /** Message d'erreur "Mois naissance incohérent avec le numéro de sécu"". */
    public static final String ERREUR_TRANSFO_AIA_MOIS_NUM_SECU_PERSONNE_INCOHERENT = "erreur.transfo.aia.mois.num.secu.personne.incoherent";

    /** Message d'erreur "Date de naissance Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_DATE_NAISSANCE_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.date.naissance.personne.obligatoire";

    /** Message d'erreur "Civilité Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_CIVILITE_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.civilite.personne.obligatoire";

    /** Message d'erreur "Situation familiale Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_SITUATION_FAMILIALE_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.situation.familiale.personne.obligatoire";

    /** Message d'erreur "Régime Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_REGIME_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.regime.personne.obligatoire";

    /** Message d'erreur "CSP inconnue". */
    public static final String ERREUR_TRANSFO_AIA_CSP_PERSONNE_INCONNUE = "erreur.transfo.aia.csp.personne.inconnue";

    /** Message d'erreur "Caisse Personne obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_CAISSE_PERSONNE_OBLIGATOIRE = "erreur.transfo.aia.caisse.personne.obligatoire";

    /** Message d'erreur "Pays adresse obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_PAYS_ADRESSE_OBLIGATOIRE = "erreur.transfo.aia.pays.adresse.obligatoire";

    /** Message d'erreur "Pays adresse secondaire obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_PAYS_ADRESSE_SECONDAIRE_OBLIGATOIRE = "erreur.transfo.aia.pays.adresse.secondaire.obligatoire";

    /** Message d'erreur "Date d'effet pour contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_DATE_EFFET_CONTRAT_OBLIGATOIRE = "erreur.transfo.aia.date.effet.contrat.obligatoire";

    /** Message d'erreur "Date de clôture pour contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_DATE_CLOTURE_CONTRAT_OBLIGATOIRE = "erreur.transfo.aia.date.cloture.contrat.obligatoire";

    /** Message d'erreur "Date de signature pour contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_DATE_SIGNATURE_CONTRAT_OBLIGATOIRE = "erreur.transfo.aia.date.signature.contrat.obligatoire";

    /** Message d'erreur "Code profession pour contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_CODE_PROFESSION_CONTRAT_OBLIGATOIRE = "erreur.transfo.aia.code.profession.contrat.obligatoire";

    /** Message d'erreur "Statut profession pour contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_STATUT_PROFESSION_CONTRAT_OBLIGATOIRE = "erreur.transfo.aia.statut.profession.contrat.obligatoire";

    /** Message d'erreur "Numéro adhérent personne contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_NUMERO_ADHERENT_PERSONNE_CONTRAT_OBLIGATOIRE =
        "erreur.transfo.aia.numero.adherent.personne.contrat.obligatoire";

    /** Message d'erreur "Rôle personne contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_ROLE_PERSONNE_CONTRAT_OBLIGATOIRE = "erreur.transfo.aia.role.personne.contrat.obligatoire";

    /** Message d'erreur "Garantie AIA obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_GARANTIE_AIA_OBLIGATOIRE = "erreur.transfo.aia.garantie.aia.obligatoire";

    /** Message d'erreur "Libellé garantie AIA obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_LIBELLE_GARANTIE_AIA_OBLIGATOIRE = "erreur.transfo.aia.libelle.garantie.aia.obligatoire";

    /** Message d'erreur "Date de signature garantie AIA obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_DATE_SIGNATURE_GARANTIE_AIA_OBLIGATOIRE = "erreur.transfo.aia.date.signature.garantie.aia.obligatoire";

    /** Message d'erreur "Apporteur du contrat obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_APPORTEUR_OBLIGATOIRE = "erreur.transfo.aia.apporteur.obligatoire";

    /** Message d'erreur indiquant que le nombre de paramètre de la méthode de l'aop est incorrect. */
    public static final String ERREUR_NOMBRE_PARAMETRE_INCORRECT = "erreur.nombre.parametre.incorrect";

    /** Message d'erreur indiquant que l'identifiant du devis est inconnu. */
    public static final String ERREUR_IDENTIFIANT_DEVIS_INCONNU = "erreur.identifiant.devis.inconnu";

    /** Message d'erreur indiquant que l'identifiant de l'opportunité est inconnu. */
    public static final String ERREUR_IDENTIFIANT_OPPORTUNITE_INCONNU = "erreur.identifiant.opportunite.inconnu";

    /** Message d'erreur si l'opportunité est déjà transformée. */
    public static final String ERREUR_TRANSFO_AIA_OPPORTUNITE_DEJA_TRANSFORMEE = "erreur.transfo.aia.opportunite.deja.transformee";

    /** Message d'erreur "Numéro d'adhérent de la personne rattachée obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_NUMERO_ADHERENT_RATTACHE_OBLIGATOIRE = "erreur.transfo.aia.numero.adherent.rattache.obligatoire";

    /** Message d'erreur "Type de relation avec la personne rattachée obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_TYPE_RELATION_RATTACHE_OBLIGATOIRE = "erreur.transfo.aia.type.relation.rattache.obligatoire";

    /** Message d'erreur "Régime de la personne rattachée obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_REGIME_RATTACHE_OBLIGATOIRE = "erreur.transfo.aia.regime.rattache.obligatoire";

    /** Message d'erreur "Caisse de la personne rattachée obligatoire". */
    public static final String ERREUR_TRANSFO_AIA_CAISSE_RATTACHE_OBLIGATOIRE = "erreur.transfo.aia.caisse.rattache.obligatoire";

    /** Message d'erreur "Les infos d'adhésion n'existent pas". */
    public static final String ERREUR_TRANSFO_AIA_ADHESION_INEXISTANTE = "erreur.transfo.aia.adhesion.inexistante";

    /** Message d'erreur "Id devis non renseigné". */
    public static final String ERREUR_TRANSFO_AIA_ID_DEVIS_NON_RENSEIGNE = "erreur.transfo.aia.id.devis.non.renseigne";

    /** Message d'erreur "Login utilisateur connecté non renseigné". */
    public static final String ERREUR_TRANSFO_AIA_LOGIN_UTILISATEUR_NON_RENSEIGNE = "erreur.transfo.aia.login.utilisateur.connecte.non.renseigne";

    /** Message d'erreur. */
    public static final String ERREUR_TRANSFO_AIA_PERSONNE_ONUM_INEXISTANT = "erreur.transfo.aia.personne.onum.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_LIGNE_DEVIS_SELECTIONNEE_OBLIGATOIRE = "error.ligne.devis.selectionnee.obligatoire";

    /** Message d'erreur. */
    public static final String ERROR_MODELE_DEVIS_SELECTIONNE_OBLIGATOIRE = "error.modele.devis.selectionne.obligatoire";

    /** Message d'erreur. */
    public static final String ERROR_MODELE_DEVIS_INCOMPATIBLE = "error.modele.devis.incompatible";

    /** Message d'erreur. */
    public static final String GENERATION_PDF_IMPOSSIBLE = "error.generation.pdf.impossible";

    /** Message d'erreur. */
    public static final String PRODUIT_LIGNE_DEVIS_INTROUVABLE = "error.produit.ligne.devis.introuvable";

    /** Message d'erreur. */
    public static final String GENERATION_BA_PDF_IMPOSSIBLE = "error.generation.ba.pdf.impossible";

    /** Message d'erreur. */
    public static final String GENERATION_BA_PDF_IMPOSSIBLE_NB_PRODUITS_LIMITE_DEPASSEE =
        "error.generation.ba.pdf.impossible.nb.produits.limite.depassee";

    /** Message d'erreur. */
    public static final String GENERATION_BA_PDF_IMPOSSIBLE_NB_PRODUITS_LIMITE_DEPASSE_BENEFICIAIRE =
    	"error.generation.ba.pdf.impossible.nb.produits.limite.depasse.beneficiaire";

    /** Message d'erreur. */
    public static final String ERROR_AUCUN_EMAIL_PERSONNE_CIBLEE = "error.aucun.email.personne.ciblee";

    /** Message d'erreur. */
    public static final String ERROR_MOTIF_DEVIS_INEXISTANT = "error.motif.devis.inexistant";

    /** Message d'erreur. */
    public static final String ERREUR_EID_TRANSFERT_PERSONNE_SOURCE_NULL = "erreur.eid.transfert.personne.source.null";

    /** Message d'erreur. */
    public static final String ERREUR_EID_TRANSFERT_PERSONNE_CIBLE_NULL = "erreur.eid.transfert.personne.cible.null";

    /** Message d'erreur. */
    public static final String ERREUR_PERSONNE_VIDE = "erreur.personne.vide";

    /** Message d'erreur. */
    public static final String ERREUR_PERSONNE_INEXISTANTE = "erreur.personne.inexistante";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_EXISTANT = "erreur.assure.social.existant";

    /** Message d'erreur. */
    public static final String ERREUR_ID_OPPORTUNITE_NON_RENSEIGNE = "erreur.id.opportunite.non.renseigne";

    /** Message d'erreur. */
    public static final String ERREUR_OPPORTUNITE_INEXISTANTE = "erreur.opportunite.inexistante";

    /** Message d'erreur. */
    public static final String ERREUR_FINALITE_INEXISTANTE = "erreur.finalite.inexistante";

    /** Message d'erreur. */
    public static final String ERREUR_ID_MOYEN_PAIEMENT_OBLIGATOIRE = "erreur.id.moyen.paiement.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ID_PERIODICITE_PAIEMENT_OBLIGATOIRE = "erreur.id.periodicite.paiement.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ID_JOUR_PAIEMENT_OBLIGATOIRE = "erreur.id.jour.paiement.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_DATE_SIGNATURE_OBLIGATOIRE = "erreur.date.signature.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_EID_REGIME_OBLIGATOIRE = "erreur.eid.regime.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_EID_CAISSE_OBLIGATOIRE = "erreur.eid.caisse.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_CREATION_DEVIS_FAMILLE_NON_ELIGIBLE = "erreur.creation.devis.famille.non.eligible";

    /** Message d'erreur. */
    public static final String ERREUR_CREATION_DEVIS_ADRESSE_PRINCIPALE_NON_RENSEIGNEE = "erreur.creation.devis.adresse.principale.non.renseignee";

    /** Message d'erreur. */
    public static final String ERREUR_DATE_SIGNATURE_POSTERIEURE = "erreur.date.signature.posterieure";

    /** Message d'erreur. */
    public static final String ERREUR_RIB_OBLIGATOIRE = "erreur.rib.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_RIB_INVALIDE = "erreur.rib.invalide";

    /** Message d'erreur. */
    public static final String ERREUR_RECUPERATION_NATURE_PERSONNE_SQUARE = "erreur.recuperation.nature.personne.square";

    /** Message d'erreur. */
    public static final String ERREUR_ADHESION_INEXISTANTE = "erreur.adhesion.inexistante";

    /** Message d'erreur. */
    public static final String ERREUR_PERSONNE_EID_OBLIGATOIRE = "erreur.personne.eid.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_PERSONNE_CIVILITE_OBLIGATOIRE = "erreur.personne.civilite.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_PERSONNE_NOM_OBLIGATOIRE = "erreur.personne.nom.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_PERSONNE_PRENOM_OBLIGATOIRE = "erreur.personne.prenom.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_PERSONNE_DATE_NAISSANCE_OBLIGATOIRE = "erreur.personne.date.naissance.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_LIBELLE_VOIE_OBLIGATOIRE = "erreur.adresse.libelle.voie.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_LIBELLE_VOIE_NON_RENSEIGNEE = "erreur.adresse.libelle.voie.non.renseignee";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_CODE_POSTAL_OBLIGATOIRE = "erreur.adresse.code.postal.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_COMMUNE_OBLIGATOIRE = "erreur.adresse.commune.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_TELEPHONE_NUMERO_OBLIGATOIRE = "erreur.telephone.numero.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_TELEPHONE_NUMERO_TROP_LONG = "erreur.telephone.numero.trop.long";

    /** Message d'erreur. */
    public static final String ERREUR_RECUPERATION_UTILISATEUR_CONNECTE = "erreur.recuperation.utilisateur.connecte";

    /** Message d'erreur. */
    public static final String ERROR_MODIFICATION_DEVIS_IMPOSSIBLE_DEVIS_INEXISTANT = "error.modification.devis.impossible.devis.inexistant";

    /** Message d'erreur. */
    public static final String ERROR_TRANSFO_AIA_PERSONNE_SQUARE_INEXISTANTE = "error.transfo.aia.personne.square.inexistante";

    /** Message d'erreur. */
    public static final String ERREUR_TRANSFO_AIA_PRESENCE_HOMYNE_PERSONNE = "erreur.transfo.aia.presence.homonyme.personne";

    /** Message d'erreur. */
    public static final String ERREUR_RECUPERATION_OPPORTUNITE_SQUARE = "erreur.recuperation.opportunite.square";

    /** Message d'erreur. */
    public static final String ERREUR_OPPORTUNITE_SANS_RESSOURCE_ATTIBUEE = "erreur.opportunite.sans.ressource.attribuee";

    /** Message d'erreur. */
    public static final String ERREUR_EID_CREATEUR_ACTION_EDITION_NON_RENSEIGNE = "erreur.eid.createur.action.edition.non.renseigne";

    /** Message d'erreur. */
    public static final String ERREUR_RESSOURCE_CREATEUR_ACTION_EDITION_INEXISTANTE = "erreur.ressource.createur.action.edition.inexistante";

    /** Message d'erreur. */
    public static final String ERREUR_ATTACHEMENT_DOCUMENT_UTILISATEUR_INEXISTANT = "erreur.attachement.document.utilisateur.inexistant";

    /** Nom du fichier pour le bulletin d'adhésion. */
    public static final String NOM_FICHIER_BULLETIN_ADHESION = "nom.fichier.bulletin.adhesion";

    /** Nom du fichier pour l'avenant. */
    public static final String NOM_FICHIER_AVENANT = "nom.fichier.avenant";

    /** Nom du fichier pour la lettre d'annulation. */
    public static final String NOM_FICHIER_LETTRE_ANNULATION = "nom.fichier.lettre.annulation";

    /** Nom du fichier pour la lettre d'annulation. */
    public static final String NOM_FICHIER_LETTRE_RADIATION = "nom.fichier.lettre.radiation";

    /** Nom du fichier pour la lettre d'annulation. */
    public static final String NOM_FICHIER_LETTRE_RADIATION_LOI_CHATEL = "nom.fichier.lettre.radiation.loi.chatel";

    /** Nom du fichier pour le devis CNP. */
    public static final String NOM_FICHIER_DEVIS_CNP = "nom.fichier.devis.cnp";

    /** Extension d'un fichier PDF. */
    public static final String EXTENSION_FICHIER_PDF = "extension.fichier.pdf";

    /** Message d'erreur. */
    public static final String ERREUR_GENERATION_DOCUMENT_AGENCE_OBLIGATOIRE = "erreur.generation.document.agence.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_NOTIFICATION_ERREUR_GED_ACTION_INEXISTANTE = "erreur.notification.erreur.ged.action.inexistante";

    /** Commentaire de l'action suite à une erreur d'association de documents dans la GED. */
    public static final String COMMENTAIRE_ACTION_ERREUR_ASSOCIATION_DOCUMENTS_GED = "commentaire.action.erreur.association.documents.ged";

    /** Message d'erreur. */
    public static final String ERREUR_EMAIL_ADRESSE_OBLIGATOIRE = "erreur.email.adresse.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_EMAIL_ADRESSE_TROP_LONGUE = "erreur.email.adresse.trop.longue";

    /** Message d'erreur. */
    public static final String ERREUR_TRANSFO_AIA_ADRESSE_OBLIGATOIRE = "erreur.transfo.aia.adresse.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_PARAM_NUMERO_TRANSACTION_VIDE = "erreur.param.numero.transaction.vide";

    /** Message d'erreur. */
    public static final String ERREUR_GET_OPP_BY_NUM_TRANSACTION_AUCUNE_OPPORTUNITE_TROUVEE =
        "erreur.get.opportunite.by.numero.transaction.aucune.opportunite.trouvee";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_NUMERO_VOIE_TROP_LONG = "erreur.adresse.numero.voie.trop.long";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_FORMAT_NUMERO_VOIE_INCORRECT = "erreur.adresse.format.numero.voie.incorrect";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_LIBELLE_VOIE_TROP_LONG = "erreur.adresse.libelle.voie.trop.long";

    /** Message d'erreur. */
    public static final String ERREUR_ADRESSE_SOMME_CHAMPS_TROP_LONGUE = "erreur.adresse.somme.champs.trop.longue";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_NOM_OBLIGATOIRE = "erreur.assure.social.nom.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_PRENOM_OBLIGATOIRE = "erreur.assure.social.prenom.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_DATE_NAISSANCE_OBLIGATOIRE = "erreur.assure.social.date.naissance.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_CIVILITE_OBLIGATOIRE = "erreur.assure.social.civilite.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_CAISSE_OBLIGATOIRE = "erreur.assure.social.caisse.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_REGIME_OBLIGATOIRE = "erreur.assure.social.regime.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_NUMERO_RO_OBLIGATOIRE = "erreur.assure.social.numero.ro.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_MODIFICATION_DATE_CLOTURE_DEVIS_EXISTANTE_IMPOSSIBLE = "erreur.modification.date.cloture.devis.existante.impossible";

    /** Message d'erreur. */
    public static final String ERREUR_MODIFICATION_DATE_CLOTURE_OPP_EXISTANTE_IMPOSSIBLE = "erreur.modification.date.cloture.opp.existante.impossible";

    /** Message d'erreur. */
    public static final String ERREUR_ASSURE_SOCIAL_RELATION_TYPE_OBLIGATOIRE = "erreur.assure.social.relation.type.obligatoire";

    /** Message d'erreur. */
    public static final String ERREUR_SOURCE_LIGNE_DEVIS_INEXISTANTE = "erreur.source.ligne.devis.inexistante";

    /** Message d'erreur. */
    public static final String ERREUR_REOUVERTURE_DEVIS_OPPORTUNITE_NULL = "erreur.reouverture.devis.opportunite.null";

    /** Message d'erreur. */
    public static final String ERREUR_SAVE_PERSONNE_SQUARE_CSP_NULL = "erreur.save.personne.square.csp.null";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_TRANSFO_DEVIS_VERS_AIA_POSTER_FILE = "logger.info.transfo.devis.vers.aia.poster.file";

    /** Message de logger de warn.*/
	public static final String LOGGER_WARN_ERREUR_FORMAT = "logger.warn.erreur.format";

    /** Message de logger de warn.*/
	public static final String LOGGER_WARN_TRANSFO_DEVIS_VERS_AIA_NON_POSTER_ERREUR_BLOQUANTE =
		"logger.warn.transfo.devis.vers.aia.non.poster.erreur.bloquante";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_ENVOIE_DEVIS_NOUVELLE_ACTION_POSTER_FILE = "logger.info.envoie.devis.nouvelle.action.poster.file";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_MESSAGE_GENERATION_DOCUMENTS_PDF = "logger.debug.message.generation.documents.pdf";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_NOTIFICATION_ENVOI_DOCUMENT_MAIL = "logger.debug.notification.envoi.document.mail";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_DEMANDE_DEVIS_PDF = "logger.info.demande.devis.pdf";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_ABSCENCE_AGENCE_PERSONNE_OPPORTUNITE = "logger.error.abscence.agence.personne.opportunite";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_MODELE_DEVIS_NON_RENSEIGNER = "logger.error.modele.devis.non.renseigner";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_MODELE_DEVIS_IS_NOT_BULLETIN_ADHESION_OU_FICHE_TRANSFERT =
		"logger.error.modele.devis.is.not.bulletion.adhesion.ou.fiche.transfert";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_CAISSE_NULL = "logger.error.caisse.null";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_GENERATION_DOCUMENT_BA_DEVIS = "logger.debug.generation.document.ba.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_DELAIS_STAGE = "logger.debug.delais.stage";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_TAILLE_LISTE_BENEFICIAIRE = "logger.debug.taille.liste.beneficiaire";

    /** Message.*/
	public static final String MESSAGE_REMISE = "message.remise";

    /** Message.*/
	public static final String MESSAGE_ABSCENCE_DELAIS_STAGE = "message.abscence.delais.stage";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_GENERATION_PDF_IMPOSSIBLE = "logger.error.generation.pdf.impossible";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_MONTANT_CAPITAL_GARANTI_BAD_FORMAT = "logger.error.montant.capital.garanti.bad.format";

    /** Message de logger d'erreur.*/
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "date.format.yyyymmddhhmmss";

    /** Message.*/
	public static final String MESSAGE_OUI = "message.oui";

    /** Message.*/
	public static final String MESSAGE_NON = "message.non";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_TRANSFERT_EID_PERSONNE_SOURCE_TO_CIBLE = "logger.debug.transfert.eid.personne.source.to.cible";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECUP_PERSONNE = "logger.debug.recup.personne";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECUP_PERSONNE_BY_EID = "logger.debug.recup.personne.by.eid";

    /** Format de date.*/
	public static final String DATE_FORMAT_DDMMYYYY = "date.format.ddmmyyyy";

    /** Format de date.*/
	public static final String DATE_FORMAT_DDMMYYYY_SEPARATOR = "date.format.ddmmyyyy.separator";

    /** Message d'erreur.*/
	public static final String ERROR_MAJ_FAMILLE_DEVIS_IMPOSSIBLE_DEVIS_INEXISTANT = "error.maj.famille.devis.impossible.devis.inexistant";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_CLOTURE_DEVIS = "logger.debug.cloture.devis";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_CLOTURE_IMPOSSIBLE_DEVIS_INEXISTANT = "logger.error.cloture.impossible.devis.inexistant";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_MOTIF_DEVIS_INEXISTANT = "logger.error.motif.devis.inexistant";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_FINALITE_LIGNE = "logger.debug.finalite.ligne";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECUP_INFO_ADHESION_DEVIS = "logger.debug.recup.info.adhesion.devis";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_DEVIS_INEXISTANT = "logger.error.devis.inexistant";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_DEVIS_PERSONNE_PRINCIPALE_INEXISTANTE = "logger.error.devis.personne.principale.inexistante";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_DEVIS_OPPORTUNITE_INEXISTANTE = "logger.error.devis.opportunite.inexistante";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_OPPORTUNITE_ADHESION_INEXISTANTE = "logger.error.devis.opportunite.inexistante";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_MAJ_INFO_ADHESION_DEVIS = "logger.debug.maj.info.adhesion.devis";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_PERSONNE_INEXISTANTE = "logger.error.personne.inexistante";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_MOYEN_PAIEMENT_INEXISTANT = "logger.error.moyen.paiement.inexistant";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_MOYEN_PAIEMENT_PREMIER_REGLEMENT_INEXISTANT = "logger.error.moyen.paiement.premier.reglement.inexistant";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_PERIODICITE_PAIEMENT_INEXISTANTE = "logger.error.periodicite.paiement.inexistante";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_JOUR_PAIEMENT_INEXISTANT = "logger.error.jour.paiement.inexistant";

    /** Message d'erreur.*/
	public static final String ERROR_RECUP_TYPE_RELATION = "error.recup.type.relation";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECHERCHE_CAISSE_DEPARTEMENT = "logger.debug.recherche.caisse.departement";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECHERCHE_DEPARTEMENT_INEXISTANT = "logger.debug.recherche.departement.inexistant";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECUP_LISTE_PRODUIT_ADHERENT = "logger.debug.recup.liste.produit.adherent";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECUP_LISTE_PRODUIT_ADHERENT_PRINCIPAL = "logger.debug.recup.liste.produit.adherent.principal";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_NOMBRE_CONTRAT_TROUVER = "logger.debug.nombre.contrat.trouver";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_MESSAGE_CONTRAT_INDIVIDUEL = "logger.debug.message.contrat.individuel";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECUP_PRODUIT_GARANTIE_AIA = "logger.debug.recup.produit.garantie.aia";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_RECUP_PRODUIT_ADHERENT = "logger.error.recup.produit.adherent";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PRODUIT_RECUPERER = "logger.debug.produit.recuperer";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_AJOUT_PRODUIT_PRINCIPAL = "logger.debug.ajout.produit.principal";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_AJOUT_PRODUIT_LIER = "logger.debug.ajout.produit.lier";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_CONTRAT_COLL_NPE_CHARGE = "logger.debug.contrat.coll.npe.charge";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFORMATION_ADHERENT_INEXISTANTE = "logger.debug.information.adherent.inexistante";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PERF_ETUDE_GET_CREATE_OPPORTUNITE = "logger.debug.perf.etude.get.create.opportunite";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECHERCHE_DEVIS_PAR_CRITERE_NB_RESULTAT = "logger.debug.recherche.devis.par.critere.nb.resultat";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECHERCHE_DEVIS_PAR_CRITERE_IDDEVIS = "logger.debug.recherche.devis.par.critere.iddevis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PERF_ETUDE_RECUP_DEVIS_DANS_DAO = "logger.debug.perf.etude.recup.devis.dans.dao";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PERF_ETUDE_RECUP_TYPE_DEVIS = "logger.debug.perf.etude.recup.type.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PERF_ETUDE_RECUP_LIGNE_DEVIS_PRINCIPAL = "logger.debug.perf.etude.recup.ligne.devis.principal";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PERF_ETUDE_MAPPING_LIGNE_DEVIS = "logger.debug.perf.etude.mapping.ligne.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_DEMANDE_CREATION_DEVIS_POUR_OPPORTUNITE = "logger.debug.demande.creation.devis.pour.opportunite";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_FAMILLE_NON_ELIGIBLE = "logger.error.famille.non.eligible";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_MODIFICATION_OPPORTUNITE = "logger.debug.modification.opportunite";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_OPPORTUNITE_INEXISTANTE = "logger.error.opportunite.inexistante";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_OPPORTUNITE_FINALITE_INEXISTANTE = "logger.error.opportunite.finalite.inexistante";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_OPPORTUNITE_FINALITE_INEXISTANTE_IDFICHIER = "logger.error.opportunite.finalite.inexistante.idfichier";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_ADRESSE_PRINCIPALE_INEXISTANTE = "logger.error.adresse.principale.inexistante";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_MODIFICATION_DEVIS_IMPOSSIBLE_DEVIS_INEXISTANT = "logger.error.modification.devis.impossible.devis.inexistant";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_MODIFICATION_DEVIS = "logger.debug.modification.devis";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_PERSONNE_EID_NULL = "logger.error.personne.eid.null";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_DATE_NAISSANCE_NULL = "logger.error.date.naissance.null";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_CODE_POSTAL_COMMUNE_NULL = "logger.error.code.postal.commune.null";

    /** Message d'erreur.*/
	public static final String ERROR_APPLICATION_GED_INDISPONIBLE = "error.application.ged.indisponible";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_GED_TRANSFERER_DOCUMENT_PERSONNE = "logger.error.ged.transferer.document.personne";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_LISTE_BENEFICIAIRE_DIFFERE_DTO_MODEL = "logger.debug.beneficiaire.differe.dto.model";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_LIEN_FAMILIAL_INCOHERENT = "logger.debug.lien.familial.incoherent";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_CHAMPS_DIFFERENTS_PERSONNE = "logger.debug.champs.differents.personne";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFO_SANTE_DIFFERENTES_PERSONNE = "logger.debug.info.sante.differentes.personnes";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_ADRESSE_PRINCIPALE_DIFFERENTES_PERSONNE = "logger.debug.adresse.principale.differentes.personne";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_WHILE_OBJECT_PROPERTY_RECUPERATION = "logger.error.while.object.property.recuperation";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_OBJET_DIFFERENTS = "logger.debug.objet.differents";

    /** Message de logger de warning.*/
	public static final String LOGGER_WARN_PROPERTY_MISSING_DTO = "logger.warn.property.missing.dto";

    /** Message de logger de warning.*/
	public static final String LOGGER_WARN_PROPERTY_MISSING_MODEL = "logger.warn.property.missing.model";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PROPERTY_DIFFERE_DTO_MODEL = "logger.debug.property.differe.dto.model";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_PROPERTY_DIFFERE_DTO_MODEL_NULL = "logger.debug.property.differe.dto.model.null";

    /** Message.*/
	public static final String MESSAGE_MAPPING_CUSTOM_IMPOSSIBLE = "message.mapping.custom.impossible";

    /** Message.*/
	public static final String MESSAGE_LIBELLE_NON_TROUVER = "message.libelle.non.trouver";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_DEMANDE_AJOUT_LIGNE_DEVIS = "logger.info.demande.ajout.ligne.devis";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_AJOUT_IMPOSSIBLE_DEVIS_INEXISTANT = "logger.error.ajout.impossible.devis.inexistant";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_RECALCUL_FINALITE_DEVIS = "logger.debug.recalcul.finalite.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFO_DEVIS = "logger.debug.info.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFO_DEVIS_FINALITE = "logger.debug.info.devis.finalite";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_DEVIS_OUVERTURE = "logger.debug.devis.ouverture";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_DEVIS_CLOTURE = "logger.debug.devis.cloture";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFO_OPPORTUNITE_DEVIS = "logger.debug.info.opportunite.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFO_OPPORTUNITE_FINALITE = "logger.debug.info.opportunite.finalite";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_CLOTURE_OPPORTUNITE = "logger.debug.cloture.opportunite";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_OUVERTURE_OPPORTUNITE = "logger.debug.ouverture.opportunite";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_DEMANDE_MODIF_LIGNE_DEVIS = "logger.info.demande.modif.ligne.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_AFFICHAGE_CRITERE_LIB_MAPPING = "logger.debug.affichage.critere.lib.mapping";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_NON_BLOQUANTE_NO_LIBELLE_CRITERE_MAPPING = "logger.error.non.bloquante.no.Libelle.critere.mapping";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_RECUPERATION_PRODUIT = "logger.error.recuperation.produit";

    /** Message d'erreur.*/
	public static final String ERROR_RECUPERATION_PRODUIT = "error.recuperation.produit";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_CALCUL_REMISE_LIGNE_DEVIS = "logger.debug.calcul.remise.ligne.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_CALCUL_REMISE_LIGNE_DEVIS_MONTANT_REMISE = "logger.debug.calcul.remise.ligne.devis.montant.remise";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_DEMANDE_SUPPRESION_LIGNE_DEVIS = "logger.info.demande.suppresion.ligne.devis";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_ID_LIGNE_DEVIS_INEXISTANT = "logger.error.id.ligne.devis.inexistant";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_DEMANDE_CLOTURE_DEVIS = "logger.error.demande.cloture.devis";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_CLOTURE_IMPOSSIBLE_LIGNE_INEXISTANTE = "logger.error.cloture.impossible.ligne.inexistante";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_FINALITE_ID_LIBELLE = "logger.info.finalite.id.libelle";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_FORMATION_LIGNE_DEVIS_DUREEPRET_INCORRECTE = "logger.error.formation.ligne.devis.dureepret.incorrecte";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_CALCUL_MONTANT_LIGNE_DEVIS = "logger.error.calcul.montant.ligne.devis";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_DEMANDE_CALCUL_INFO_BENEFICIAIRE = "logger.debug.demande.calcul.info.beneficiaire";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFO_LIGNEDEVIS_BENEFICIAIRE_AGE = "logger.debug.info.lignedevis.beneficiaire.age";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_BENEFICIAIRE_ABSCENCE_LIEN_FAMILIAL = "logger.error.beneficiaire.abscence.lien.familial";

    /** Message de logger de debug.*/
	public static final String LOGGER_DEBUG_INFO_LIGNE_DEVIS_BENEFICIAIRE_ROLE_AGE_INDEX = "logger.debug.info.ligne.devis.beneficiaire.role.age.index";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_GENERATION_PROP_DEVIS_ADHERENT = "logger.info.generation.prop.devis.adherent";

    /** Message d'erreur.*/
	public static final String ERREUR_RECUPERATION_LIBELLE_PRODUIT = "erreur.recuperation.libelle.produit";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_BENEFICIAIRE_INEXISTANT = "logger.error.beneficiaire.inexistant";

    /** Message de logger d'erreur.*/
	public static final String LOGGER_ERROR_RECUPERATION_OPPORTUNITE_INEXISTANTE = "logger.error.recuperation.opportunite.inexistante";

    /** Message.*/
	public static final String MESSAGE_RAPPORT_VALEUR_SELECTIONNER = "message.rapport.valeur.selectionner";

    /** Message d'erreur.*/
	public static final String ERROR_AUCUN_PRODUIT_CORRESPONDANT = "error.aucun.produit.correspondant";

    /** Message d'erreur.*/
	public static final String ERROR_IMPOSSIBLE_RECUPERER_PRODUIT_BONUS = "error.impossible.recuperer.produit.bonus";

    /** Message d'erreur.*/
	public static final String ERROR_ABSCENCE_BENEFICIAIRE_PRODUIT_CONTRAT = "error.abscence.beneficiaire.produit.contrat";

    /** Message de logger d'info.*/
	public static final String LOGGER_INFO_CALCUL_COMPOSITION_FAMILIALE_PROSPECT = "logger.info.calcul.composition.familiale.prospect";

    /** Message.*/
	public static final String MESSAGE_EXISTE_PAS = "message.existe.pas";

    /** Message.*/
	public static final String MESSAGE_PERSONNE = "message.personne";

	public static final String ERREUR_PRODUIT_LIGNE_DEVIS_INEXISTANT = "erreur.produit.ligne.devis.inexistant";
}
