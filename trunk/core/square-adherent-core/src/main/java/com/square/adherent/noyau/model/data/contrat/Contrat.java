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
import com.square.adherent.noyau.model.dimension.ContratCompoFamiliale;
import com.square.adherent.noyau.model.dimension.ContratFrequencePaiement;
import com.square.adherent.noyau.model.dimension.ContratMoyenPaiement;
import com.square.adherent.noyau.model.dimension.ContratNature;
import com.square.adherent.noyau.model.dimension.ContratStatut;
import com.square.adherent.noyau.model.dimension.ContratTermePaiement;
import com.square.adherent.noyau.model.dimension.MotifResiliation;
import com.square.adherent.noyau.model.dimension.Segment;
import com.square.adherent.noyau.model.dimension.TypePayeur;

/**
 * Modèle d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_CONTRAT")
public class Contrat implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 3891890792154034620L;

    /** Identifiant. */
    @Id
    @Column(name = "CONTRAT_UID")
    private Long id;

    /** Identifiant extérieur. */
    @Column(name = "CONTRAT_EID")
    private String identifiantExterieur;

    /** UID de l'assuré associé. */
    @Column(name = "CONTRAT_ASSURE_UID")
    private Long uidAssure;

    /** Numéro du contrat. */
    @Column(name = "CONTRAT_NUMERO")
    private String numeroContrat;

    /** Date de création du contrat. */
    @Column(name = "CONTRAT_DATE_SAISIE")
    private Calendar dateCreation;

    /** Date de signature du contrat. */
    @Column(name = "CONTRAT_DATE_SIGNATURE")
    private Calendar dateSignature;

    /** Date d'effet du contrat. */
    @Column(name = "CONTRAT_DATE_ADHESION")
    private Calendar dateEffet;

    /** Date de résiliation du contrat. */
    @Column(name = "CONTRAT_DATE_RESILIATION")
    private Calendar dateResiliation;

    /** Code Apporteur du contrat. */
    @Column(name = "CONTRAT_APPORTEUR_CODE")
    private String codeApporteur;

    /** Apporteur du contrat. */
    @Column(name = "CONTRAT_APPORTEUR_LIBELLE")
    private String apporteur;

    /** Le gestionnaire du contrat. */
    @Column(name = "CONTRAT_GESTIONNAIRE")
    private String gestionnaire;

    /** Statut du contrat. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_STATUT_UID")
    @ForeignKey(name = "fk_contrat_statut")
    private ContratStatut statut;

    /** Segment du contrat. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_SEGMENT_UID")
    @ForeignKey(name = "fk_contrat_segment")
    private Segment segment;

    /** Nature du contrat. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_NATURE_UID")
    @ForeignKey(name = "fk_contrat_nature")
    private ContratNature nature;

    /** Moyen de paiement de la cotisation. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_MOYEN_PAIEMENT_COTIS_UID")
    @ForeignKey(name = "fk_contrat_moyen_paiement_cotis")
    private ContratMoyenPaiement moyenPaiementCotisation;

    /** Fréquence de paiement de la cotisation. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_FREQUENCE_PAIEMENT_COTIS_UID")
    @ForeignKey(name = "fk_contrat_frequence_paiement_cotis")
    private ContratFrequencePaiement frequencePaiementCotisation;

    /** Composition familiale. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_COMPO_FAMILIALE_UID")
    @ForeignKey(name = "fk_contrat_compo_familiale")
    private ContratCompoFamiliale compoFamiliale;

    /** Jour de paiement de la cotisation. */
    @Column(name = "CONTRAT_JOUR_PAIEMENT_COTIS")
    private Integer jourPaiementCotisation;

    /** Moyen de paiement de la prestation. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_MOYEN_PAIEMENT_PRESTA_UID")
    @ForeignKey(name = "fk_contrat_moyen_paiement_presta")
    private ContratMoyenPaiement moyenPaiementPrestation;

    /** UID du souscripteur. */
    @Column(name = "CONTRAT_SOUSCRIPTEUR_UID")
    private Long uidSouscripteur;

    /** Type payeur du contrat. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_TYPE_PAYEUR_UID")
    @ForeignKey(name = "fk_contrat_type_payeur")
    private TypePayeur typePayeur;

    /** Popuplation. */
    @Column(name = "CONTRAT_POPULATION_LIBELLE")
    private String population;

    /** Motif de résiliation du contrat. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_MOTIF_RESILIATION_UID")
    @ForeignKey(name = "fk_contrat_motif_resiliation")
    private MotifResiliation motifResiliation;

    /** Indique si le contrat est visible ou non. */
    @Column(name = "CONTRAT_VISIBLE")
    private Boolean isVisible;

    /** Type de souscription. */
    @Column(name = "CONTRAT_SOUSCRIPTION_TYPE")
    private String typeSouscription;

    /** Banque pour la cotisation. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_BANQUE_PAIEMENT_COTIS_UID")
    @ForeignKey(name = "fk_banque_cotis")
    private Banque banqueCotisation;

    /** Terme de paiement. */
    @ManyToOne
    @JoinColumn(name = "CONTRAT_TERME_PAIEMENT_COTIS_UID")
    @ForeignKey(name = "fk_contrat_terme_paiement_cotis")
    private ContratTermePaiement termePaiement;

    /** Banque pour la prestation. **/
    @ManyToOne
    @JoinColumn(name = "CONTRAT_BANQUE_PAIEMENT_PRESTA_UID")
    @ForeignKey(name = "fk_banque_presta")
    private Banque banquePrestation;

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
     * Récupère la valeur de numeroContrat.
     * @return la valeur de numeroContrat
     */
    public String getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * Définit la valeur de numeroContrat.
     * @param numeroContrat la nouvelle valeur de numeroContrat
     */
    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    /**
     * Récupère la valeur de dateCreation.
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
     * Récupère la valeur de dateEffet.
     * @return la valeur de dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Définit la valeur de dateEffet.
     * @param dateEffet la nouvelle valeur de dateEffet
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
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
     * Récupère la valeur de apporteur.
     * @return la valeur de apporteur
     */
    public String getApporteur() {
        return apporteur;
    }

    /**
     * Définit la valeur de apporteur.
     * @param apporteur la nouvelle valeur de apporteur
     */
    public void setApporteur(String apporteur) {
        this.apporteur = apporteur;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public ContratStatut getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(ContratStatut statut) {
        this.statut = statut;
    }

    /**
     * Récupère la valeur de moyenPaiementCotisation.
     * @return la valeur de moyenPaiementCotisation
     */
    public ContratMoyenPaiement getMoyenPaiementCotisation() {
        return moyenPaiementCotisation;
    }

    /**
     * Définit la valeur de moyenPaiementCotisation.
     * @param moyenPaiementCotisation la nouvelle valeur de moyenPaiementCotisation
     */
    public void setMoyenPaiementCotisation(ContratMoyenPaiement moyenPaiementCotisation) {
        this.moyenPaiementCotisation = moyenPaiementCotisation;
    }

    /**
     * Récupère la valeur de frequencePaiementCotisation.
     * @return la valeur de frequencePaiementCotisation
     */
    public ContratFrequencePaiement getFrequencePaiementCotisation() {
        return frequencePaiementCotisation;
    }

    /**
     * Définit la valeur de frequencePaiementCotisation.
     * @param frequencePaiementCotisation la nouvelle valeur de frequencePaiementCotisation
     */
    public void setFrequencePaiementCotisation(ContratFrequencePaiement frequencePaiementCotisation) {
        this.frequencePaiementCotisation = frequencePaiementCotisation;
    }

    /**
     * Récupère la valeur de moyenPaiementPrestation.
     * @return la valeur de moyenPaiementPrestation
     */
    public ContratMoyenPaiement getMoyenPaiementPrestation() {
        return moyenPaiementPrestation;
    }

    /**
     * Définit la valeur de moyenPaiementPrestation.
     * @param moyenPaiementPrestation la nouvelle valeur de moyenPaiementPrestation
     */
    public void setMoyenPaiementPrestation(ContratMoyenPaiement moyenPaiementPrestation) {
        this.moyenPaiementPrestation = moyenPaiementPrestation;
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
     * Récupère la valeur de codeApporteur.
     * @return la valeur de codeApporteur
     */
    public String getCodeApporteur() {
        return codeApporteur;
    }

    /**
     * Définit la valeur de codeApporteur.
     * @param codeApporteur la nouvelle valeur de codeApporteur
     */
    public void setCodeApporteur(String codeApporteur) {
        this.codeApporteur = codeApporteur;
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
     * Récupère la valeur de uidSouscripteur.
     * @return la valeur de uidSouscripteur
     */
    public Long getUidSouscripteur() {
        return uidSouscripteur;
    }

    /**
     * Définit la valeur de uidSouscripteur.
     * @param uidSouscripteur la nouvelle valeur de uidSouscripteur
     */
    public void setUidSouscripteur(Long uidSouscripteur) {
        this.uidSouscripteur = uidSouscripteur;
    }

    /**
     * Récupère la valeur de typePayeur.
     * @return la valeur de typePayeur
     */
    public TypePayeur getTypePayeur() {
        return typePayeur;
    }

    /**
     * Définit la valeur de typePayeur.
     * @param typePayeur la nouvelle valeur de typePayeur
     */
    public void setTypePayeur(TypePayeur typePayeur) {
        this.typePayeur = typePayeur;
    }

    /**
     * Récupère la valeur de jourPaiementCotisation.
     * @return la valeur de jourPaiementCotisation
     */
    public Integer getJourPaiementCotisation() {
        return jourPaiementCotisation;
    }

    /**
     * Définit la valeur de jourPaiementCotisation.
     * @param jourPaiementCotisation la nouvelle valeur de jourPaiementCotisation
     */
    public void setJourPaiementCotisation(Integer jourPaiementCotisation) {
        this.jourPaiementCotisation = jourPaiementCotisation;
    }

    /**
     * Récupère la valeur de nature.
     * @return la valeur de nature
     */
    public ContratNature getNature() {
        return nature;
    }

    /**
     * Définit la valeur de nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(ContratNature nature) {
        this.nature = nature;
    }

    /**
     * Récupère la valeur de population.
     * @return la valeur de population
     */
    public String getPopulation() {
        return population;
    }

    /**
     * Définit la valeur de population.
     * @param population la nouvelle valeur de population
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * Récupère la valeur de motifResiliation.
     * @return la valeur de motifResiliation
     */
    public MotifResiliation getMotifResiliation() {
        return motifResiliation;
    }

    /**
     * Définit la valeur de motifResiliation.
     * @param motifResiliation la nouvelle valeur de motifResiliation
     */
    public void setMotifResiliation(MotifResiliation motifResiliation) {
        this.motifResiliation = motifResiliation;
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
     * Getter function.
     * @return the gestionnaire
     */
    public String getGestionnaire() {
        return gestionnaire;
    }

    /**
     * Setter function.
     * @param gestionnaire the gestionnaire to set
     */
    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
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
     * Récupère la valeur de typeSouscription.
     * @return la valeur de typeSouscription
     */
    public String getTypeSouscription() {
        return typeSouscription;
    }

    /**
     * Définit la valeur de typeSouscription.
     * @param typeSouscription la nouvelle valeur de typeSouscription
     */
    public void setTypeSouscription(String typeSouscription) {
        this.typeSouscription = typeSouscription;
    }

    /**
     * Récupère la valeur de identifiantExterieur.
     * @return la valeur de identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définit la valeur de identifiantExterieur.
     * @param identifiantExterieur la nouvelle valeur de identifiantExterieur
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

    /**
     * Récupère la valeur de banqueCotisation.
     * @return la valeur de banqueCotisation
     */
    public Banque getBanqueCotisation() {
        return banqueCotisation;
    }

    /**
     * Définit la valeur de banqueCotisation.
     * @param banqueCotisation la nouvelle valeur de banqueCotisation
     */
    public void setBanqueCotisation(Banque banqueCotisation) {
        this.banqueCotisation = banqueCotisation;
    }

    /**
     * Récupère la valeur de termePaiement.
     * @return la valeur de termePaiement
     */
    public ContratTermePaiement getTermePaiement() {
        return termePaiement;
    }

    /**
     * Définit la valeur de termePaiement.
     * @param termePaiement la nouvelle valeur de termePaiement
     */
    public void setTermePaiement(ContratTermePaiement termePaiement) {
        this.termePaiement = termePaiement;
    }

    /**
     * Get the value of compoFamiliale.
     * @return the compoFamiliale
     */
    public ContratCompoFamiliale getCompoFamiliale() {
        return compoFamiliale;
    }

    /**
     * Set the value of compoFamiliale.
     * @param compoFamiliale the compoFamiliale to set
     */
    public void setCompoFamiliale(ContratCompoFamiliale compoFamiliale) {
        this.compoFamiliale = compoFamiliale;
    }

    /**
     * Récupérer la valeur de banqueprestation.
     * @return banquePrestation
     */
	public Banque getBanquePrestation() {
		return banquePrestation;
	}

	/**
	 * Définir la valeur de banqueprestation.
	 * @param banquePrestation banque prestation
	 */
	public void setBanquePrestation(Banque banquePrestation) {
		this.banquePrestation = banquePrestation;
	}
}
