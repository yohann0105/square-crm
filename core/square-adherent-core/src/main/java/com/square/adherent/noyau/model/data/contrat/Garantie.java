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
package com.square.adherent.noyau.model.data.contrat;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.square.adherent.noyau.model.data.prestation.Banque;
import com.square.adherent.noyau.model.dimension.ContratMoyenPaiement;
import com.square.adherent.noyau.model.dimension.GarantieFamille;
import com.square.adherent.noyau.model.dimension.GarantieRole;
import com.square.adherent.noyau.model.dimension.GarantieStatut;
import com.square.adherent.noyau.model.dimension.Segment;

/**
 * Modèle d'une garantie.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_GARANTIE")
public class Garantie implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6166941992986044743L;

    /** Identifiant. */
    @Id
    @Column(name = "GARANTIE_UID")
    private Long id;

    /** Identifiant externe. */
    @Column(name = "GARANTIE_EID")
    private String eid;

    /** Contrat associé. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_CONTRAT_UID")
    @ForeignKey(name = "fk_garantie_contrat")
    private Contrat contrat;

    /** UID de l'assuré associé. */
    @Column(name = "GARANTIE_ASSURE_UID")
    private Long uidAssure;

    /** UID du bénéficiaire associé. */
    @Column(name = "GARANTIE_BENEFICIAIRE_UID")
    private Long uidBeneficiaire;

    /** Libellé de gestion de la garantie. */
    @Column(name = "GARANTIE_LIBELLE_GESTION")
    private String libelleGarantieGestion;

    /** Libellé de gestion du produit. */
    @Column(name = "GARANTIE_PRODUIT_GESTION")
    private String libelleProduitGestion;

    /** Code du tarif. */
    @Column(name = "GARANTIE_CODE_TARIF")
    private String codeTarif;

    /** Code de la génération. */
    @Column(name = "GARANTIE_CODE_GENERATION")
    private String codeGeneration;

    /** Code de l'apporteur. */
    @Column(name = "GARANTIE_APPORTEUR_CODE")
    private String codeApporteur;

    /** Date d'adhésion. */
    @Column(name = "GARANTIE_DATE_ADHESION")
    private Calendar dateAdhesion;

    /** Date de résiliation. */
    @Column(name = "GARANTIE_DATE_RESILIATION")
    private Calendar dateResiliation;

    /** Date de signature. */
    @Column(name = "GARANTIE_DATE_SIGNATURE")
    private Calendar dateSignature;

    /** Rôle. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_ROLE_UID")
    @ForeignKey(name = "fk_garantie_role")
    private GarantieRole role;

    /** Statut. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_STATUT_UID")
    @ForeignKey(name = "fk_garantie_statut")
    private GarantieStatut statut;

    /** Statut. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_FAMILLE_UID")
    @ForeignKey(name = "fk_garantie_famille")
    private GarantieFamille famille;

    /** Montant souscrit. */
    @Column(name = "GARANTIE_MONTANT_SOUSCRIT")
    private Double montantSouscrit;

    /** Indique si lgarantie est visible ou non. */
    @Column(name = "GARANTIE_VISIBLE")
    private Boolean isVisible;

    /** Segment. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_SEGMENT_UID")
    @ForeignKey(name = "fk_garantie_segment")
    private Segment segment;

    /** Popuplation. */
    @Column(name = "GARANTIE_POPULATION_LIBELLE")
    private String libellePopulation;

    /** Banque utilisée pour les remboursements. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_BANQUE_PRESTA_UID")
    @ForeignKey(name = "FK_GARANTIE_BANQUE_PRESTA")
    private Banque banque;

    /** Moyen de paiement utilisé pour les remboursements. */
    @ManyToOne
    @JoinColumn(name = "GARANTIE_MOYEN_PAIEMENT_PRESTA_UID")
    @ForeignKey(name = "FK_GARANTIE_MOYEN_PAIEMENT_PRESTA")
    private ContratMoyenPaiement moyenPaiement;

    /** Identifiant de la personne destinataire des remboursements. */
    @Column(name = "GARANTIE_DEST_RMBSMT_PRESTA_UID")
    private Long uidDestinataireRemboursements;

    /** Numero Gestion. */
    @Column(name = "GARANTIE_NUMERO_GESTION")
    private String numeroGestion;

    /** Loi Madelin. */
    @Column(name = "GARANTIE_LOI_MADELIN")
    private Boolean loiMadelin;

    /**
     * Récupère la valeur de id.
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
     * Récupère la valeur de contrat.
     * @return la valeur de contrat
     */
    public Contrat getContrat() {
        return contrat;
    }

    /**
     * Définit la valeur de contrat.
     * @param contrat la nouvelle valeur de contrat
     */
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    /**
     * Récupère la valeur de uidAssure.
     * @return la valeur de uidAssure
     */
    public Long getUidAssure() {
        return uidAssure;
    }

    /**
     * Définit la valeur de uidAssure.
     * @param uidAssure la nouvelle valeur de uidAssure
     */
    public void setUidAssure(Long uidAssure) {
        this.uidAssure = uidAssure;
    }

    /**
     * Récupère la valeur de uidBeneficiaire.
     * @return la valeur de uidBeneficiaire
     */
    public Long getUidBeneficiaire() {
        return uidBeneficiaire;
    }

    /**
     * Définit la valeur de uidBeneficiaire.
     * @param uidBeneficiaire la nouvelle valeur de uidBeneficiaire
     */
    public void setUidBeneficiaire(Long uidBeneficiaire) {
        this.uidBeneficiaire = uidBeneficiaire;
    }

    /**
     * Récupère la valeur de libelleGarantieGestion.
     * @return la valeur de libelleGarantieGestion
     */
    public String getLibelleGarantieGestion() {
        return libelleGarantieGestion;
    }

    /**
     * Définit la valeur de libelleGarantieGestion.
     * @param libelleGarantieGestion la nouvelle valeur de libelleGarantieGestion
     */
    public void setLibelleGarantieGestion(String libelleGarantieGestion) {
        this.libelleGarantieGestion = libelleGarantieGestion;
    }

    /**
     * Récupère la valeur de libelleProduitGestion.
     * @return la valeur de libelleProduitGestion
     */
    public String getLibelleProduitGestion() {
        return libelleProduitGestion;
    }

    /**
     * Définit la valeur de libelleProduitGestion.
     * @param libelleProduitGestion la nouvelle valeur de libelleProduitGestion
     */
    public void setLibelleProduitGestion(String libelleProduitGestion) {
        this.libelleProduitGestion = libelleProduitGestion;
    }

    /**
     * Récupère la valeur de codeTarif.
     * @return la valeur de codeTarif
     */
    public String getCodeTarif() {
        return codeTarif;
    }

    /**
     * Définit la valeur de codeTarif.
     * @param codeTarif la nouvelle valeur de codeTarif
     */
    public void setCodeTarif(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    /**
     * Récupère la valeur de codeGeneration.
     * @return la valeur de codeGeneration
     */
    public String getCodeGeneration() {
        return codeGeneration;
    }

    /**
     * Définit la valeur de codeGeneration.
     * @param codeGeneration la nouvelle valeur de codeGeneration
     */
    public void setCodeGeneration(String codeGeneration) {
        this.codeGeneration = codeGeneration;
    }

    /**
     * Récupère la valeur de dateAdhesion.
     * @return la valeur de dateAdhesion
     */
    public Calendar getDateAdhesion() {
        return dateAdhesion;
    }

    /**
     * Définit la valeur de dateAdhesion.
     * @param dateAdhesion la nouvelle valeur de dateAdhesion
     */
    public void setDateAdhesion(Calendar dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    /**
     * Récupère la valeur de role.
     * @return la valeur de role
     */
    public GarantieRole getRole() {
        return role;
    }

    /**
     * Définit la valeur de role.
     * @param role la nouvelle valeur de role
     */
    public void setRole(GarantieRole role) {
        this.role = role;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public GarantieStatut getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(GarantieStatut statut) {
        this.statut = statut;
    }

    /**
     * Récupère la valeur de famille.
     * @return la valeur de famille
     */
    public GarantieFamille getFamille() {
        return famille;
    }

    /**
     * Définit la valeur de famille.
     * @param famille la nouvelle valeur de famille
     */
    public void setFamille(GarantieFamille famille) {
        this.famille = famille;
    }

    /**
     * Récupère la valeur de dateResiliation.
     * @return la valeur de dateResiliation
     */
    public Calendar getDateResiliation() {
        return dateResiliation;
    }

    /**
     * Définit la valeur de dateResiliation.
     * @param dateResiliation la nouvelle valeur de dateResiliation
     */
    public void setDateResiliation(Calendar dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

    /**
     * Récupère la valeur de montantSouscrit.
     * @return la valeur de montantSouscrit
     */
    public Double getMontantSouscrit() {
        return montantSouscrit;
    }

    /**
     * Définit la valeur de montantSouscrit.
     * @param montantSouscrit la nouvelle valeur de montantSouscrit
     */
    public void setMontantSouscrit(Double montantSouscrit) {
        this.montantSouscrit = montantSouscrit;
    }

    /**
     * Récupère la valeur de isVisible.
     * @return la valeur de isVisible
     */
    public Boolean getIsVisible() {
        return isVisible;
    }

    /**
     * Définit la valeur de isVisible.
     * @param isVisible la nouvelle valeur de isVisible
     */
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Récupère la valeur de segment.
     * @return la valeur de segment
     */
    public Segment getSegment() {
        return segment;
    }

    /**
     * Définit la valeur de segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    /**
     * Récupère la valeur de libellePopulation.
     * @return la valeur de libellePopulation
     */
    public String getLibellePopulation() {
        return libellePopulation;
    }

    /**
     * Définit la valeur de libellePopulation.
     * @param libellePopulation la nouvelle valeur de libellePopulation
     */
    public void setLibellePopulation(String libellePopulation) {
        this.libellePopulation = libellePopulation;
    }

    /**
     * Récupère la valeur de dateSignature.
     * @return la valeur de dateSignature
     */
    public Calendar getDateSignature() {
        return dateSignature;
    }

    /**
     * Définit la valeur de dateSignature.
     * @param dateSignature la nouvelle valeur de dateSignature
     */
    public void setDateSignature(Calendar dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Get the eid value.
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * Set the eid value.
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     * Récupère la valeur de loiMadelin.
     * @return la valeur de loiMadelin
     */
    public Boolean getLoiMadelin() {
        return loiMadelin;
    }

    /**
     * Définit la valeur de loiMadelin.
     * @param loiMadelin la nouvelle valeur de loiMadelin
     */
    public void setLoiMadelin(Boolean loiMadelin) {
        this.loiMadelin = loiMadelin;
    }

    /**
     * Get the value of codeApporteur.
     * @return the codeApporteur
     */
    public String getCodeApporteur() {
        return codeApporteur;
    }

    /**
     * Set the value of codeApporteur.
     * @param codeApporteur the codeApporteur to set
     */
    public void setCodeApporteur(String codeApporteur) {
        this.codeApporteur = codeApporteur;
    }

    /**
     * Get the value of banque.
     * @return the banque
     */
    public Banque getBanque() {
        return banque;
    }

    /**
     * Set the value of banque.
     * @param banque the banque to set
     */
    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    /**
     * Get the value of moyenPaiement.
     * @return the moyenPaiement
     */
    public ContratMoyenPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Set the value of moyenPaiement.
     * @param moyenPaiement the moyenPaiement to set
     */
    public void setMoyenPaiement(ContratMoyenPaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * Get the value of uidDestinataireRemboursements.
     * @return the uidDestinataireRemboursements
     */
    public Long getUidDestinataireRemboursements() {
        return uidDestinataireRemboursements;
    }

    /**
     * Set the value of uidDestinataireRemboursements.
     * @param uidDestinataireRemboursements the uidDestinataireRemboursements to set
     */
    public void setUidDestinataireRemboursements(Long uidDestinataireRemboursements) {
        this.uidDestinataireRemboursements = uidDestinataireRemboursements;
    }

    /**
     * Get the value of numeroGestion.
     * @return the numeroGestion
     */
    public String getNumeroGestion() {
        return numeroGestion;
    }

    /**
     * Set the value of numeroGestion.
     * @param numeroGestion the numeroGestion to set
     */
    public void setNumeroGestion(String numeroGestion) {
        this.numeroGestion = numeroGestion;
    }

}
