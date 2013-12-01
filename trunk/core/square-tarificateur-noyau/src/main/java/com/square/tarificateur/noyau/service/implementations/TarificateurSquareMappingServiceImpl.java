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
package com.square.tarificateur.noyau.service.implementations;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;

/**
 * Implémentation de TarificateurSquareMappingService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class TarificateurSquareMappingServiceImpl implements TarificateurSquareMappingService {

    /** Identifiant de la finalité "non renseignée". */
    private Long idFinaliteNonRenseignee;

    /** Identifiant de la finalité "acceptée". */
    private Long idFinaliteAcceptee;

    /** Identifiant de la finalité "refusée". */
    private Long idFinaliteRefusee;

    /** Identifiant de la finalité "corbeille". */
    private Long idFinaliteCorbeille;

    /** Identifiant de la finalité "transformée". */
    private Long idFinaliteTransformee;

    /** Identifiant de la finalité "en cours". */
    private Long idFinaliteEnCours;

    /** Identifiant du moyen de paiement par prélèvement. */
    private Long idMoyenPaiementPrelevement;

    /** Identifiant du moyen de paiement par carte bancaire. */
    private Long idMoyenPaiementCarteBancaire;

    /** Identifiant du moyen de paiement par chèque. */
    private Long idMoyenPaiementCheque;

    /** Identifiant du moyen de paiement par espèces. */
    private Long idMoyenPaiementEspeces;

    /** Identifiant du moyen de paiement par mandat. */
    private Long idMoyenPaiementMandat;

    /** Identifiant du moyen de paiement par virement. */
    private Long idMoyenPaiementVirement;

    /** Identifiant du moyen de paiement par chèque. */
    private Long idMoyenPaiementVirementEncaissement;

    /** Identifiant de la périodicité de paiement "Mensuelle". */
    private Long idPeriodicitePaiementMensuelle;

    /** Identifiant de la périodicité de paiement "Trimestrielle". */
    private Long idPeriodicitePaiementTrimestrielle;

    /** Identifiant de la périodicité de paiement "Semestrielle". */
    private Long idPeriodicitePaiementSemestrielle;

    /** Identifiant de la périodicité de paiement "Annuelle". */
    private Long idPeriodicitePaiementAnnuelle;

    /** Identifiant du jour de paiement "5 du mois". */
    private Long idJourPaiement5DuMois;

    /** Identifiant du jour de paiement "10 du mois". */
    private Long idJourPaiement10DuMois;

    /** Identifiant du jour de paiement "15 du mois". */
    private Long idJourPaiement15DuMois;

    /** Type CNP. */
    private String typeCnp;

    /** Type Santé/Prévoyance. */
    private String typeSantePrevoyance;

    /** Type Neutre. */
    private String typeNeutre;

    /** Type de devis santé Nouvelle offre. */
    private String typeSanteNouvelleOffre;

    /** Type de devis santé Ancienne offre. */
    private String typeSanteAncienneOffre;

    /** Identifiant simulacre GO. */
    private Integer idSimulacreProduitGo;

    /** Identifiant critère simulacre type GO. */
    private Integer idCritereSimulacreTypeGo;

    /** Nombre d'années d'adhésion nécessaires pour obtenir le bonus 1. */
    private Integer nbAnneesBonus1;

    /** Nombre d'années d'adhésion nécessaires pour obtenir le bonus 1. */
    private Integer nbAnneesBonus2;

    /** HashMap mapping composition famille. */
    private Map<Long, String> mapCompoFamTCodCompoTarif;

    /** HashMap mapping composition famille. */
    private Map<String, String> mapCompoFamToCodRoleTarif;

    /** Constante lien famille conjoint. */
    private Long idLienFamilialAssurePrincipal;

    /** Constante lien famille conjoint. */
    private Long idLienFamilialConjoint;

    /** Constante lien famille conjoint. */
    private Long idLienFamilialEnfant;

    /** Liste des propriétés de personne à comparer. */
    private List<String> listeProprietesPersonneAComparer;

    /** Liste des propriétés d'adresses à comparer. */
    private List<String> listeProprietesAdresseAComparer;

    /** Liste des propriétés d'info santé à comparer. */
    private List<String> listeProprietesInfoSanteAComparer;

    /** Identifiant du modèle de devis "Avenant individuel". */
    private Long idModeleDevisCnp;

    /** Identifiant du modèle de devis "BA adhesion en ligne". */
    private Long idModeleDevisBulletinAdhesion;

    /** Identifiant du modèle de devis "BA individuel". */
    private Long idModeleDevisFicheTransfert;

    /** Identifiant du modèle de devis "". */
    private Long idModeleLettreAnnulation;

    /** Identifiant du modèle de lettre de radiation. */
    private Long idModeleLettreRadiation;

    /** Identifiant du modèle de lettre de radiation par loi Chatel. */
    private Long idModeleLettreRadiationLoiChatel;

    /** Identifiant de la source de ligne de devis "Non renseignée". */
    private Long idSourceLigneDevisNonRenseignee;

    /** Identifiant de la source de ligne de devis "Consultation Tarifs". */
    private Long idSourceLigneDevisConsultationTarifs;

    /** Identifiant de la source de ligne de devis "Demande de dossier". */
    private Long idSourceLigneDevisDemandeDossier;

    /** Identifiant de la source de ligne de devis "Adhésion". */
    private Long idSourceLigneDevisAdhesion;

    /** Constante motif ligne devis non reseignee. */
    private Long constanteIdMotifNonRenseigne;

    /** Constante motif ligne devis spontanee. */
    private Long constanteIdMotifSpontanee;

    /** Constante motif ligne devis vide. */
    private Long constanteIdMotifVide;

    /** Constante motif ligne devis vide. */
    private Long constanteIdMotifAutre;

    /**
     * Identifiant du motif de devis standard.
     */
    private Long idMotifDevisStandard;
    /**
     * Identifiant du motif de devis "parrainage".
     */
    private Long idMotifDevisParrainage;

    /**
     * Identifiant du motif de devis "parrainage bénéficiaire".
     */
    private Long idMotifDevisParrainageBeneficiaire;

    /**
     * Identifiant du motif de devis "parrainage entreprise".
     */
    private Long idMotifDevisParrainageEntreprise;

    /**
     * Identifiant du motif de devis "Le mois responsable".
     */
    private Long idMotifDevisMoisResponsable;

    /**
     * Identifiant du motif de devis "1er mois gratuit crédit".
     */
    private Long idMotifDevisPremierMoisGratuit;

    /**
     * Identifiant du motif de devis "3 mois gratuits".
     */
    private Long idMotifDevisTroisMoisGratuits;

    /**
     * Identifiant du motif de devis "Rentree Recompense 2011".
     */
    private Long idMotifDevisRentreeRecompense2011;

    /**
     * Identifiant du motif de devis "Campagne Tv avril 2012".
     */
    private Long idMotifDevisCampagneTv2012;


    /** Age minimum pour déterminer le délai de stage. */
    private Integer constanteAdhesionAgeMiniDelaiStage;

    /**
     * {@inheritDoc}
     */
    public Long getIdFinaliteNonRenseignee() {
        return idFinaliteNonRenseignee;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdFinaliteAcceptee() {
        return idFinaliteAcceptee;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdFinaliteRefusee() {
        return idFinaliteRefusee;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdFinaliteCorbeille() {
        return idFinaliteCorbeille;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdFinaliteTransformee() {
        return idFinaliteTransformee;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdMoyenPaiementPrelevement() {
        return idMoyenPaiementPrelevement;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdMoyenPaiementCarteBancaire() {
        return idMoyenPaiementCarteBancaire;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdMoyenPaiementCheque() {
        return idMoyenPaiementCheque;
    }

    /**
     * {@inheritDoc}
     */
    public String getTypeCnp() {
        return typeCnp;
    }

    /**
     * {@inheritDoc}
     */
    public String getTypeSantePrevoyance() {
        return typeSantePrevoyance;
    }

    /**
     * Setter function.
     * @param idFinaliteNonRenseignee the idFinaliteNonRenseignee to set
     */
    public void setIdFinaliteNonRenseignee(Long idFinaliteNonRenseignee) {
        this.idFinaliteNonRenseignee = idFinaliteNonRenseignee;
    }

    /**
     * Setter function.
     * @param idFinaliteAcceptee the idFinaliteAcceptee to set
     */
    public void setIdFinaliteAcceptee(Long idFinaliteAcceptee) {
        this.idFinaliteAcceptee = idFinaliteAcceptee;
    }

    /**
     * Setter function.
     * @param idFinaliteRefusee the idFinaliteRefusee to set
     */
    public void setIdFinaliteRefusee(Long idFinaliteRefusee) {
        this.idFinaliteRefusee = idFinaliteRefusee;
    }

    /**
     * Setter function.
     * @param idFinaliteCorbeille the idFinaliteCorbeille to set
     */
    public void setIdFinaliteCorbeille(Long idFinaliteCorbeille) {
        this.idFinaliteCorbeille = idFinaliteCorbeille;
    }

    /**
     * Setter function.
     * @param idFinaliteTransformee the idFinaliteTransformee to set
     */
    public void setIdFinaliteTransformee(Long idFinaliteTransformee) {
        this.idFinaliteTransformee = idFinaliteTransformee;
    }

    /**
     * Setter function.
     * @param idMoyenPaiementPrelevement the idMoyenPaiementPrelevement to set
     */
    public void setIdMoyenPaiementPrelevement(Long idMoyenPaiementPrelevement) {
        this.idMoyenPaiementPrelevement = idMoyenPaiementPrelevement;
    }

    /**
     * Setter function.
     * @param idMoyenPaiementCarteBancaire the idMoyenPaiementCarteBancaire to set
     */
    public void setIdMoyenPaiementCarteBancaire(Long idMoyenPaiementCarteBancaire) {
        this.idMoyenPaiementCarteBancaire = idMoyenPaiementCarteBancaire;
    }

    /**
     * Setter function.
     * @param idMoyenPaiementCheque the idMoyenPaiementCheque to set
     */
    public void setIdMoyenPaiementCheque(Long idMoyenPaiementCheque) {
        this.idMoyenPaiementCheque = idMoyenPaiementCheque;
    }

    /**
     * Setter function.
     * @param typeCnp the typeCnp to set
     */
    public void setTypeCnp(String typeCnp) {
        this.typeCnp = typeCnp;
    }

    /**
     * Setter function.
     * @param typeSantePrevoyance the typeSantePrevoyance to set
     */
    public void setTypeSantePrevoyance(String typeSantePrevoyance) {
        this.typeSantePrevoyance = typeSantePrevoyance;
    }

    /**
     * {@inheritDoc}
     */
    public String getCodeCompoParCompoFam(final Long idLiensFamiliaux) {
        return mapCompoFamTCodCompoTarif.get(idLiensFamiliaux);
    }

    /**
     * Set the mapCompoFamTCodCompoTarif value.
     * @param mapCompoFamTCodCompoTarif the mapCompoFamTCodCompoTarif to set
     */
    public void setMapCompoFamTCodCompoTarif(Map<Long, String> mapCompoFamTCodCompoTarif) {
        this.mapCompoFamTCodCompoTarif = mapCompoFamTCodCompoTarif;
    }

    /**
     * Get the idLienFamilialAssurePrincipal value.
     * @return the idLienFamilialAssurePrincipal
     */
    public Long getIdLienFamilialAssurePrincipal() {
        return idLienFamilialAssurePrincipal;
    }

    /**
     * Set the idLienFamilialAssurePrincipal value.
     * @param idLienFamilialAssurePrincipal the idLienFamilialAssurePrincipal to set
     */
    public void setIdLienFamilialAssurePrincipal(Long idLienFamilialAssurePrincipal) {
        this.idLienFamilialAssurePrincipal = idLienFamilialAssurePrincipal;
    }

    /**
     * Get the idLienFamilialConjoint value.
     * @return the idLienFamilialConjoint
     */
    public Long getIdLienFamilialConjoint() {
        return idLienFamilialConjoint;
    }

    /**
     * Set the idLienFamilialConjoint value.
     * @param idLienFamilialConjoint the idLienFamilialConjoint to set
     */
    public void setIdLienFamilialConjoint(Long idLienFamilialConjoint) {
        this.idLienFamilialConjoint = idLienFamilialConjoint;
    }

    /**
     * Get the idLienFamilialEnfant value.
     * @return the idLienFamilialEnfant
     */
    public Long getIdLienFamilialEnfant() {
        return idLienFamilialEnfant;
    }

    /**
     * Set the idLienFamilialEnfant value.
     * @param idLienFamilialEnfant the idLienFamilialEnfant to set
     */
    public void setIdLienFamilialEnfant(Long idLienFamilialEnfant) {
        this.idLienFamilialEnfant = idLienFamilialEnfant;
    }

    /**
     * Get the idSimulacreProduitGo value.
     * @return the idSimulacreProduitGo
     */
    public Integer getIdSimulacreProduitGo() {
        return idSimulacreProduitGo;
    }

    /**
     * Set the idSimulacreProduitGo value.
     * @param idSimulacreProduitGo the idSimulacreProduitGo to set
     */
    public void setIdSimulacreProduitGo(Integer idSimulacreProduitGo) {
        this.idSimulacreProduitGo = idSimulacreProduitGo;
    }

    /**
     * Get the idCritereSimulacreTypeGo value.
     * @return the idCritereSimulacreTypeGo
     */
    public Integer getIdCritereSimulacreTypeGo() {
        return idCritereSimulacreTypeGo;
    }

    /**
     * Set the idCritereSimulacreTypeGo value.
     * @param idCritereSimulacreTypeGo the idCritereSimulacreTypeGo to set
     */
    public void setIdCritereSimulacreTypeGo(Integer idCritereSimulacreTypeGo) {
        this.idCritereSimulacreTypeGo = idCritereSimulacreTypeGo;
    }

    /**
     * Get the nbAnneesBonus1 value.
     * @return the nbAnneesBonus1
     */
    public Integer getNbAnneesBonus1() {
        return nbAnneesBonus1;
    }

    /**
     * Set the nbAnneesBonus1 value.
     * @param nbAnneesBonus1 the nbAnneesBonus1 to set
     */
    public void setNbAnneesBonus1(Integer nbAnneesBonus1) {
        this.nbAnneesBonus1 = nbAnneesBonus1;
    }

    /**
     * Get the nbAnneesBonus2 value.
     * @return the nbAnneesBonus2
     */
    public Integer getNbAnneesBonus2() {
        return nbAnneesBonus2;
    }

    /**
     * Set the nbAnneesBonus2 value.
     * @param nbAnneesBonus2 the nbAnneesBonus2 to set
     */
    public void setNbAnneesBonus2(Integer nbAnneesBonus2) {
        this.nbAnneesBonus2 = nbAnneesBonus2;
    }

    /**
     * {@inheritDoc}
     */
    public String getCodeRoleParCompoFam(final String compoFamille) {
        return mapCompoFamToCodRoleTarif.get(compoFamille);
    }

    /**
     * Set the mapCompoFamToCodRoleTarif value.
     * @param mapCompoFamToCodRoleTarif the mapCompoFamToCodRoleTarif to set
     */
    public void setMapCompoFamToCodRoleTarif(Map<String, String> mapCompoFamToCodRoleTarif) {
        this.mapCompoFamToCodRoleTarif = mapCompoFamToCodRoleTarif;
    }

    /**
     * Get the typeNeutre value.
     * @return the typeNeutre
     */
    public String getTypeNeutre() {
        return typeNeutre;
    }

    /**
     * Set the typeNeutre value.
     * @param typeNeutre the typeNeutre to set
     */
    public void setTypeNeutre(String typeNeutre) {
        this.typeNeutre = typeNeutre;
    }

    /**
     * Get the typeSanteNouvelleOffre value.
     * @return the typeSanteNouvelleOffre
     */
    public String getTypeSanteNouvelleOffre() {
        return typeSanteNouvelleOffre;
    }

    /**
     * Set the typeSanteNouvelleOffre value.
     * @param typeSanteNouvelleOffre the typeSanteNouvelleOffre to set
     */
    public void setTypeSanteNouvelleOffre(String typeSanteNouvelleOffre) {
        this.typeSanteNouvelleOffre = typeSanteNouvelleOffre;
    }

    /**
     * Get the typeSanteAncienneOffre value.
     * @return the typeSanteAncienneOffre
     */
    public String getTypeSanteAncienneOffre() {
        return typeSanteAncienneOffre;
    }

    /**
     * Set the typeSanteAncienneOffre value.
     * @param typeSanteAncienneOffre the typeSanteAncienneOffre to set
     */
    public void setTypeSanteAncienneOffre(String typeSanteAncienneOffre) {
        this.typeSanteAncienneOffre = typeSanteAncienneOffre;
    }

    /**
     * Récupère la valeur de listeProprietesPersonneAComparer.
     * @return la valeur de listeProprietesPersonneAComparer
     */
    public List<String> getListeProprietesPersonneAComparer() {
        return listeProprietesPersonneAComparer;
    }

    /**
     * Définit la valeur de listeProprietesPersonneAComparer.
     * @param listeProprietesPersonneAComparer la nouvelle valeur de listeProprietesPersonneAComparer
     */
    public void setListeProprietesPersonneAComparer(List<String> listeProprietesPersonneAComparer) {
        this.listeProprietesPersonneAComparer = listeProprietesPersonneAComparer;
    }

    /**
     * Récupère la valeur de listeProprietesAdresseAComparer.
     * @return la valeur de listeProprietesAdresseAComparer
     */
    public List<String> getListeProprietesAdresseAComparer() {
        return listeProprietesAdresseAComparer;
    }

    /**
     * Définit la valeur de listeProprietesAdresseAComparer.
     * @param listeProprietesAdresseAComparer la nouvelle valeur de listeProprietesAdresseAComparer
     */
    public void setListeProprietesAdresseAComparer(List<String> listeProprietesAdresseAComparer) {
        this.listeProprietesAdresseAComparer = listeProprietesAdresseAComparer;
    }

    /**
     * Getter function.
     * @return the idPeriodicitePaiementMensuelle
     */
    public Long getIdPeriodicitePaiementMensuelle() {
        return idPeriodicitePaiementMensuelle;
    }

    /**
     * Getter function.
     * @return the idPeriodicitePaiementTrimestrielle
     */
    public Long getIdPeriodicitePaiementTrimestrielle() {
        return idPeriodicitePaiementTrimestrielle;
    }

    /**
     * Getter function.
     * @return the idPeriodicitePaiementSemestrielle
     */
    public Long getIdPeriodicitePaiementSemestrielle() {
        return idPeriodicitePaiementSemestrielle;
    }

    /**
     * Getter function.
     * @return the idPeriodicitePaiementAnnuelle
     */
    public Long getIdPeriodicitePaiementAnnuelle() {
        return idPeriodicitePaiementAnnuelle;
    }

    /**
     * Getter function.
     * @return the idJourPaiement5DuMois
     */
    public Long getIdJourPaiement5DuMois() {
        return idJourPaiement5DuMois;
    }

    /**
     * Getter function.
     * @return the idJourPaiement10DuMois
     */
    public Long getIdJourPaiement10DuMois() {
        return idJourPaiement10DuMois;
    }

    /**
     * Getter function.
     * @return the idJourPaiement15DuMois
     */
    public Long getIdJourPaiement15DuMois() {
        return idJourPaiement15DuMois;
    }

    /**
     * Setter function.
     * @param idPeriodicitePaiementMensuelle the idPeriodicitePaiementMensuelle to set
     */
    public void setIdPeriodicitePaiementMensuelle(Long idPeriodicitePaiementMensuelle) {
        this.idPeriodicitePaiementMensuelle = idPeriodicitePaiementMensuelle;
    }

    /**
     * Setter function.
     * @param idPeriodicitePaiementTrimestrielle the idPeriodicitePaiementTrimestrielle to set
     */
    public void setIdPeriodicitePaiementTrimestrielle(Long idPeriodicitePaiementTrimestrielle) {
        this.idPeriodicitePaiementTrimestrielle = idPeriodicitePaiementTrimestrielle;
    }

    /**
     * Setter function.
     * @param idPeriodicitePaiementSemestrielle the idPeriodicitePaiementSemestrielle to set
     */
    public void setIdPeriodicitePaiementSemestrielle(Long idPeriodicitePaiementSemestrielle) {
        this.idPeriodicitePaiementSemestrielle = idPeriodicitePaiementSemestrielle;
    }

    /**
     * Setter function.
     * @param idPeriodicitePaiementAnnuelle the idPeriodicitePaiementAnnuelle to set
     */
    public void setIdPeriodicitePaiementAnnuelle(Long idPeriodicitePaiementAnnuelle) {
        this.idPeriodicitePaiementAnnuelle = idPeriodicitePaiementAnnuelle;
    }

    /**
     * Setter function.
     * @param idJourPaiement5DuMois the idJourPaiement5DuMois to set
     */
    public void setIdJourPaiement5DuMois(Long idJourPaiement5DuMois) {
        this.idJourPaiement5DuMois = idJourPaiement5DuMois;
    }

    /**
     * Setter function.
     * @param idJourPaiement10DuMois the idJourPaiement10DuMois to set
     */
    public void setIdJourPaiement10DuMois(Long idJourPaiement10DuMois) {
        this.idJourPaiement10DuMois = idJourPaiement10DuMois;
    }

    /**
     * Setter function.
     * @param idJourPaiement15DuMois the idJourPaiement15DuMois to set
     */
    public void setIdJourPaiement15DuMois(Long idJourPaiement15DuMois) {
        this.idJourPaiement15DuMois = idJourPaiement15DuMois;
    }

    /**
     * Récupère la valeur de idMoyenPaiementEspeces.
     * @return la valeur de idMoyenPaiementEspeces
     */
    public Long getIdMoyenPaiementEspeces() {
        return idMoyenPaiementEspeces;
    }

    /**
     * Définit la valeur de idMoyenPaiementEspeces.
     * @param idMoyenPaiementEspeces la nouvelle valeur de idMoyenPaiementEspeces
     */
    public void setIdMoyenPaiementEspeces(Long idMoyenPaiementEspeces) {
        this.idMoyenPaiementEspeces = idMoyenPaiementEspeces;
    }

    /**
     * Récupère la valeur de idMoyenPaiementMandat.
     * @return la valeur de idMoyenPaiementMandat
     */
    public Long getIdMoyenPaiementMandat() {
        return idMoyenPaiementMandat;
    }

    /**
     * Définit la valeur de idMoyenPaiementMandat.
     * @param idMoyenPaiementMandat la nouvelle valeur de idMoyenPaiementMandat
     */
    public void setIdMoyenPaiementMandat(Long idMoyenPaiementMandat) {
        this.idMoyenPaiementMandat = idMoyenPaiementMandat;
    }

    /**
     * Récupère la valeur de idMoyenPaiementVirement.
     * @return la valeur de idMoyenPaiementVirement
     */
    public Long getIdMoyenPaiementVirement() {
        return idMoyenPaiementVirement;
    }

    /**
     * Définit la valeur de idMoyenPaiementVirement.
     * @param idMoyenPaiementVirement la nouvelle valeur de idMoyenPaiementVirement
     */
    public void setIdMoyenPaiementVirement(Long idMoyenPaiementVirement) {
        this.idMoyenPaiementVirement = idMoyenPaiementVirement;
    }

    /**
     * Récupère la valeur de idMoyenPaiementVirementEncaissement.
     * @return la valeur de idMoyenPaiementVirementEncaissement
     */
    public Long getIdMoyenPaiementVirementEncaissement() {
        return idMoyenPaiementVirementEncaissement;
    }

    /**
     * Définit la valeur de idMoyenPaiementVirementEncaissement.
     * @param idMoyenPaiementVirementEncaissement la nouvelle valeur de idMoyenPaiementVirementEncaissement
     */
    public void setIdMoyenPaiementVirementEncaissement(Long idMoyenPaiementVirementEncaissement) {
        this.idMoyenPaiementVirementEncaissement = idMoyenPaiementVirementEncaissement;
    }

    /**
     * Récupère la valeur de idModeleDevisCnp.
     * @return la valeur de idModeleDevisCnp
     */
    public Long getIdModeleDevisCnp() {
        return idModeleDevisCnp;
    }

    /**
     * Définit la valeur de idModeleDevisCnp.
     * @param idModeleDevisCnp la nouvelle valeur de idModeleDevisCnp
     */
    public void setIdModeleDevisCnp(Long idModeleDevisCnp) {
        this.idModeleDevisCnp = idModeleDevisCnp;
    }

    /**
     * Récupère la valeur de idModeleDevisBulletinAdhesion.
     * @return la valeur de idModeleDevisBulletinAdhesion
     */
    public Long getIdModeleDevisBulletinAdhesion() {
        return idModeleDevisBulletinAdhesion;
    }

    /**
     * Définit la valeur de idModeleDevisBulletinAdhesion.
     * @param idModeleDevisBulletinAdhesion la nouvelle valeur de idModeleDevisBulletinAdhesion
     */
    public void setIdModeleDevisBulletinAdhesion(Long idModeleDevisBulletinAdhesion) {
        this.idModeleDevisBulletinAdhesion = idModeleDevisBulletinAdhesion;
    }

    /**
     * Récupère la valeur de idModeleDevisFicheTransfert.
     * @return la valeur de idModeleDevisFicheTransfert
     */
    public Long getIdModeleDevisFicheTransfert() {
        return idModeleDevisFicheTransfert;
    }

    /**
     * Définit la valeur de idModeleDevisFicheTransfert.
     * @param idModeleDevisFicheTransfert la nouvelle valeur de idModeleDevisFicheTransfert
     */
    public void setIdModeleDevisFicheTransfert(Long idModeleDevisFicheTransfert) {
        this.idModeleDevisFicheTransfert = idModeleDevisFicheTransfert;
    }

    /**
     * Récupère la valeur de idModeleLettreAnnulation.
     * @return la valeur de idModeleLettreAnnulation
     */
    public Long getIdModeleLettreAnnulation() {
        return idModeleLettreAnnulation;
    }

    /**
     * Définit la valeur de idModeleLettreAnnulation.
     * @param idModeleLettreAnnulation la nouvelle valeur de idModeleLettreAnnulation
     */
    public void setIdModeleLettreAnnulation(Long idModeleLettreAnnulation) {
        this.idModeleLettreAnnulation = idModeleLettreAnnulation;
    }

    /**
     * Récupère la valeur de idSourceLigneDevisNonRenseignee.
     * @return la valeur de idSourceLigneDevisNonRenseignee
     */
    public Long getIdSourceLigneDevisNonRenseignee() {
        return idSourceLigneDevisNonRenseignee;
    }

    /**
     * Définit la valeur de idSourceLigneDevisNonRenseignee.
     * @param idSourceLigneDevisNonRenseignee la nouvelle valeur de idSourceLigneDevisNonRenseignee
     */
    public void setIdSourceLigneDevisNonRenseignee(Long idSourceLigneDevisNonRenseignee) {
        this.idSourceLigneDevisNonRenseignee = idSourceLigneDevisNonRenseignee;
    }

    /**
     * Récupère la valeur de idSourceLigneDevisDemandeDossier.
     * @return la valeur de idSourceLigneDevisDemandeDossier
     */
    public Long getIdSourceLigneDevisDemandeDossier() {
        return idSourceLigneDevisDemandeDossier;
    }

    /**
     * Définit la valeur de idSourceLigneDevisDemandeDossier.
     * @param idSourceLigneDevisDemandeDossier la nouvelle valeur de idSourceLigneDevisDemandeDossier
     */
    public void setIdSourceLigneDevisDemandeDossier(Long idSourceLigneDevisDemandeDossier) {
        this.idSourceLigneDevisDemandeDossier = idSourceLigneDevisDemandeDossier;
    }

    /**
     * Récupère la valeur de idSourceLigneDevisAdhesion.
     * @return la valeur de idSourceLigneDevisAdhesion
     */
    public Long getIdSourceLigneDevisAdhesion() {
        return idSourceLigneDevisAdhesion;
    }

    /**
     * Définit la valeur de idSourceLigneDevisAdhesion.
     * @param idSourceLigneDevisAdhesion la nouvelle valeur de idSourceLigneDevisAdhesion
     */
    public void setIdSourceLigneDevisAdhesion(Long idSourceLigneDevisAdhesion) {
        this.idSourceLigneDevisAdhesion = idSourceLigneDevisAdhesion;
    }

    /**
     * Get the idSourceLigneDevisConsultationTarifs value.
     * @return the idSourceLigneDevisConsultationTarifs
     */
    public Long getIdSourceLigneDevisConsultationTarifs() {
        return idSourceLigneDevisConsultationTarifs;
    }

    /**
     * Set the idSourceLigneDevisConsultationTarifs value.
     * @param idSourceLigneDevisConsultationTarifs the idSourceLigneDevisConsultationTarifs to set
     */
    public void setIdSourceLigneDevisConsultationTarifs(Long idSourceLigneDevisConsultationTarifs) {
        this.idSourceLigneDevisConsultationTarifs = idSourceLigneDevisConsultationTarifs;
    }

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifNonRenseigne
     */
    public Long getConstanteIdMotifNonRenseigne() {
        return constanteIdMotifNonRenseigne;
    }

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifSpontanee
     */
    public Long getConstanteIdMotifSpontanee() {
        return constanteIdMotifSpontanee;
    }

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifVide
     */
    public Long getConstanteIdMotifVide() {
        return constanteIdMotifVide;
    }

    /**
     * Recuperer la valeur.
     * @return the constanteIdMotifAutre
     */
    public Long getConstanteIdMotifAutre() {
        return constanteIdMotifAutre;
    }

    /**
     * Fixer la valeur.
     * @param constanteIdMotifNonRenseigne the constanteIdMotifNonRenseigne to set
     */
    public void setConstanteIdMotifNonRenseigne(Long constanteIdMotifNonRenseigne) {
        this.constanteIdMotifNonRenseigne = constanteIdMotifNonRenseigne;
    }

    /**
     * Fixer la valeur.
     * @param constanteIdMotifSpontanee the constanteIdMotifSpontanee to set
     */
    public void setConstanteIdMotifSpontanee(Long constanteIdMotifSpontanee) {
        this.constanteIdMotifSpontanee = constanteIdMotifSpontanee;
    }

    /**
     * Fixer la valeur.
     * @param constanteIdMotifVide the constanteIdMotifVide to set
     */
    public void setConstanteIdMotifVide(Long constanteIdMotifVide) {
        this.constanteIdMotifVide = constanteIdMotifVide;
    }

    /**
     * Fixer la valeur.
     * @param constanteIdMotifAutre the constanteIdMotifAutre to set
     */
    public void setConstanteIdMotifAutre(Long constanteIdMotifAutre) {
        this.constanteIdMotifAutre = constanteIdMotifAutre;
    }

    @Override
    public Long getIdFinaliteEnCours() {
        return idFinaliteEnCours;
    }

    /**
     * Définit la valeur de idFinaliteEnCours.
     * @param idFinaliteEnCours la nouvelle valeur de idFinaliteEnCours
     */
    public void setIdFinaliteEnCours(Long idFinaliteEnCours) {
        this.idFinaliteEnCours = idFinaliteEnCours;
    }

    /**
     * Accesseur pour l'attribut constanteAdhesionAgeMiniDelaiStage.
     * @return l'attribut constanteAdhesionAgeMiniDelaiStage
     */
    public Integer getConstanteAdhesionAgeMiniDelaiStage() {
        return constanteAdhesionAgeMiniDelaiStage;
    }

    /**
     * Définit la valeur de constanteAdhesionAgeMiniDelaiStage.
     * @param constanteAdhesionAgeMiniDelaiStage la nouvelle valeur de constanteAdhesionAgeMiniDelaiStage
     */
    public void setConstanteAdhesionAgeMiniDelaiStage(Integer constanteAdhesionAgeMiniDelaiStage) {
        this.constanteAdhesionAgeMiniDelaiStage = constanteAdhesionAgeMiniDelaiStage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdMotifDevisParrainage() {
        return this.idMotifDevisParrainage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdMotifDevisParrainageBeneficiaire() {
        return this.idMotifDevisParrainageBeneficiaire;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdMotifDevisParrainageEntreprise() {
        return this.idMotifDevisParrainageEntreprise;
    }

    /**
     * Définit la valeur de idMotifDevisParrainage.
     * @param idMotifDevisParrainage la nouvelle valeur de idMotifDevisParrainage
     */
    public void setIdMotifDevisParrainage(Long idMotifDevisParrainage) {
        this.idMotifDevisParrainage = idMotifDevisParrainage;
    }

    /**
     * Définit la valeur de idMotifDevisParrainageBeneficiaire.
     * @param idMotifDevisParrainageBeneficiaire la nouvelle valeur de idMotifDevisParrainageBeneficiaire
     */
    public void setIdMotifDevisParrainageBeneficiaire(Long idMotifDevisParrainageBeneficiaire) {
        this.idMotifDevisParrainageBeneficiaire = idMotifDevisParrainageBeneficiaire;
    }

    /**
     * Définit la valeur de idMotifDevisParrainageEntreprise.
     * @param idMotifDevisParrainageEntreprise la nouvelle valeur de idMotifDevisParrainageEntreprise
     */
    public void setIdMotifDevisParrainageEntreprise(Long idMotifDevisParrainageEntreprise) {
        this.idMotifDevisParrainageEntreprise = idMotifDevisParrainageEntreprise;
    }

    /**
     * Définit la valeur de idMotifDevisMoisResponsable.
     * @param idMotifDevisMoisResponsable la nouvelle valeur de idMotifDevisMoisResponsable
     */
    public void setIdMotifDevisMoisResponsable(Long idMotifDevisMoisResponsable) {
        this.idMotifDevisMoisResponsable = idMotifDevisMoisResponsable;
    }

    /**
     * Définit la valeur de idMotifDevisPremierMoisGratuit.
     * @param idMotifDevisPremierMoisGratuit la nouvelle valeur de idMotifDevisPremierMoisGratuit
     */
    public void setIdMotifDevisPremierMoisGratuit(Long idMotifDevisPremierMoisGratuit) {
        this.idMotifDevisPremierMoisGratuit = idMotifDevisPremierMoisGratuit;
    }

    /**
     * Définit la valeur de idMotifDevisTroisMoisGratuits.
     * @param idMotifDevisTroisMoisGratuits la nouvelle valeur de idMotifDevisTroisMoisGratuits
     */
    public void setIdMotifDevisTroisMoisGratuits(Long idMotifDevisTroisMoisGratuits) {
        this.idMotifDevisTroisMoisGratuits = idMotifDevisTroisMoisGratuits;
    }

    /**
     * Get the listeProprietesInfoSanteAComparer value.
     * @return the listeProprietesInfoSanteAComparer
     */
    public List<String> getListeProprietesInfoSanteAComparer() {
        return listeProprietesInfoSanteAComparer;
    }

    /**
     * Set the listeProprietesInfoSanteAComparer value.
     * @param listeProprietesInfoSanteAComparer the listeProprietesInfoSanteAComparer to set
     */
    public void setListeProprietesInfoSanteAComparer(List<String> listeProprietesInfoSanteAComparer) {
        this.listeProprietesInfoSanteAComparer = listeProprietesInfoSanteAComparer;
    }

    /**
     * Renvoie la valeur de idModeleLettreRadiation.
     * @return idModeleLettreRadiation
     */
    public Long getIdModeleLettreRadiation() {
        return idModeleLettreRadiation;
    }

    /**
     * Modifie idModeleLettreRadiation.
     * @param idModeleLettreRadiation la nouvelle valeur de idModeleLettreRadiation
     */
    public void setIdModeleLettreRadiation(Long idModeleLettreRadiation) {
        this.idModeleLettreRadiation = idModeleLettreRadiation;
    }

    /**
     * Renvoie la valeur de idModeleLettreRadiationLoiChatel.
     * @return idModeleLettreRadiationLoiChatel
     */
    public Long getIdModeleLettreRadiationLoiChatel() {
        return idModeleLettreRadiationLoiChatel;
    }

    /**
     * Modifie idModeleLettreRadiationLoiChatel.
     * @param idModeleLettreRadiationLoiChatel la nouvelle valeur de idModeleLettreRadiationLoiChatel
     */
    public void setIdModeleLettreRadiationLoiChatel(
        Long idModeleLettreRadiationLoiChatel) {
        this.idModeleLettreRadiationLoiChatel = idModeleLettreRadiationLoiChatel;
    }

    /**
     * Renvoie la valeur de idMotifDevisRentreeRecompense2011.
     * @return idMotifDevisRentreeRecompense2011
     */
    public Long getIdMotifDevisRentreeRecompense2011() {

        return idMotifDevisRentreeRecompense2011;
    }

    /**
     * Définit la valeur de idMotifDevisRentreeRecompense2011.
     * @param idMotifDevisRentreeRecompense2011 la nouvelle valeur de idMotifDevisRentreeRecompense2011
     */
    public void setIdMotifDevisRentreeRecompense2011(Long idMotifDevisRentreeRecompense2011) {
        this.idMotifDevisRentreeRecompense2011 = idMotifDevisRentreeRecompense2011;
    }

    /**
     * Définit la valeur de idMotifDevisCampagneTv2012.
     * @param idMotifDevisCampagneTv2012 la nouvelle valeur de idMotifDevisCampagneTv2012
     */
    public void setIdMotifDevisCampagneTv2012(Long idMotifDevisCampagneTv2012) {
        this.idMotifDevisCampagneTv2012 = idMotifDevisCampagneTv2012;
    }

	@Override
	public Long getIdMotifDevisStandard() {
		return this.idMotifDevisStandard;
	}

	/**Get mapCompoFamTCodCompoTarif.
	 * @return the mapCompoFamTCodCompoTarif
	 */
	public Map<Long, String> getMapCompoFamTCodCompoTarif() {
		return mapCompoFamTCodCompoTarif;
	}

	/**Get mapCompoFamToCodRoleTarif.
	 * @return the mapCompoFamToCodRoleTarif
	 */
	public Map<String, String> getMapCompoFamToCodRoleTarif() {
		return mapCompoFamToCodRoleTarif;
	}

	/**Get idMotifDevisMoisResponsable.
	 * @return the idMotifDevisMoisResponsable
	 */
	public Long getIdMotifDevisMoisResponsable() {
		return idMotifDevisMoisResponsable;
	}

	/**Get idMotifDevisPremierMoisGratuit.
	 * @return the idMotifDevisPremierMoisGratuit
	 */
	public Long getIdMotifDevisPremierMoisGratuit() {
		return idMotifDevisPremierMoisGratuit;
	}

	/**Get idMotifDevisTroisMoisGratuits.
	 * @return the idMotifDevisTroisMoisGratuits
	 */
	public Long getIdMotifDevisTroisMoisGratuits() {
		return idMotifDevisTroisMoisGratuits;
	}

	/**Get idMotifDevisCampagneTv2012.
	 * @return the idMotifDevisCampagneTv2012
	 */
	public Long getIdMotifDevisCampagneTv2012() {
		return idMotifDevisCampagneTv2012;
	}

	/**Set idMotifDevisStandard.
	 * @param idMotifDevisStandard the idMotifDevisStandard to set
	 */
	public void setIdMotifDevisStandard(Long idMotifDevisStandard) {
		this.idMotifDevisStandard = idMotifDevisStandard;
	}

}
