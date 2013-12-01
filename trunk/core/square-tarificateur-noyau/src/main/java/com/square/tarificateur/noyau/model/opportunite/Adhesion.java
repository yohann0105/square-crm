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
package com.square.tarificateur.noyau.model.opportunite;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

import com.square.tarificateur.noyau.model.paiement.JourPaiement;
import com.square.tarificateur.noyau.model.paiement.MoyenPaiement;
import com.square.tarificateur.noyau.model.paiement.PeriodicitePaiement;

/**
 * Entité persistante qui représente les informations supplémentaires à renseigner pour une adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_ADHESION")
public class Adhesion extends BaseModel {

    /** SerialVersionUID. */
    private static final long serialVersionUID = 5887294874974937338L;

    /** Montant du paiement. */
    @Column(name = "MONTANT_PAIEMENT")
    private Float montantPaiement;

    /** Moyen de paiement choisi pour le premier règlement lors de l'adhésion. */
    @ManyToOne
    @JoinColumn(name = "MOYEN_PAIEMENT_PREMIER_REGLEMENT_UID")
    @ForeignKey(name = "FK_DATA_ADHESION_DIM_MOYEN_PAIEMENT_PREMIER_REGLEMENT_UID")
    private MoyenPaiement moyenPaiementPremierReglement;

    /** Numéro de la transaction renseigné si le premier règlement est fait par carte bancaire. */
    @Column(name = "NUMERO_TRANSACTION")
    private String numeroTransaction;

    /** Moyen de paiement choisi. */
    @ManyToOne
    @JoinColumn(name = "MOYEN_PAIEMENT_UID")
    @ForeignKey(name = "FK_DATA_ADHESION_DIM_MOYEN_PAIEMENT_UID")
    private MoyenPaiement moyenPaiement;

    /** Periodicité pour le paiement. */
    @ManyToOne
    @JoinColumn(name = "PERIODICITE_PAIEMENT_UID")
    @ForeignKey(name = "FK_DATA_ADHESION_DIM_PERIODICITE_PAIEMENT_UID")
    private PeriodicitePaiement periodicitePaiement;

    /** Jour du mois pour le paiement. */
    @ManyToOne
    @JoinColumn(name = "JOUR_PAIEMENT_UID")
    @ForeignKey(name = "FK_DATA_ADHESION_DIM_JOUR_PAIEMENT_UID")
    private JourPaiement jourPaiement;

    /** Date de dernière édition du bulletin d'adhésion. */
    @Column(name = "DATE_EDITION_BA")
    private Calendar dateEditionBA;

    /** Date de signature de l'adhésion. */
    @Column(name = "DATE_SIGNATURE")
    private Calendar dateSignature;

    /** Date du courrier. */
    @Column(name = "DATE_COURRIER")
    private Calendar dateCourrier;

    /** Infos du rib. */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_INFOS_RIB")
    private InfosRib infosRib;

    /** Identifiant du fichier sur la plateforme CNS (depot de fichier). */
    @Column(name = "ID_FICHIER_CNS")
    private String idFichierCNS;

    /** Date de clic sur le mail du BA signé. */
    @Column(name = "DATE_CLIC_BA")
    private Calendar dateClicBA;

    /** Date de telechargement du BA signé. */
    @Column(name = "DATE_TELECHARGEMENT_BA")
    private Calendar dateTelechargementBA;

    /** Indique si l'adhérent souhaite la télétransmission. */
    @Column(name = "TELETRANSMISSION")
    private Boolean teletransmission = Boolean.TRUE;

    /** Libellé de l'ancienne complémentaire de l'adhérent. */
    @Column(name = "ANCIENNE_COMPLEMENTAIRE_ADHERENT")
    private String ancienneComplemetaireAdherent;

    /** Date de fin du contrat de l'adhérent. */
    @Column(name = "DATE_FIN_CONTRAT_ADHERENT")
    private Calendar dateFinContratAdherent;

    /** Libellé de l'ancienne complémentaire du conjoint. */
    @Column(name = "ANCIENNE_COMPLEMENTAIRE_CONJOINT")
    private String ancienneComplemetaireConjoint;

    /** Date de fin du contrat du conjoint. */
    @Column(name = "DATE_FIN_CONTRAT_CONJOINT")
    private Calendar dateFinContratConjoint;

    /**
     * Getter function.
     * @return the montantPaiement
     */
    public Float getMontantPaiement() {
        return montantPaiement;
    }

    /**
     * Getter function.
     * @return the numeroTransaction
     */
    public String getNumeroTransaction() {
        return numeroTransaction;
    }

    /**
     * Getter function.
     * @return the moyenPaiement
     */
    public MoyenPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Getter function.
     * @return the periodicitePaiement
     */
    public PeriodicitePaiement getPeriodicitePaiement() {
        return periodicitePaiement;
    }

    /**
     * Getter function.
     * @return the jourPaiement
     */
    public JourPaiement getJourPaiement() {
        return jourPaiement;
    }

    /**
     * Getter function.
     * @return the dateEditionBA
     */
    public Calendar getDateEditionBA() {
        return dateEditionBA;
    }

    /**
     * Getter function.
     * @return the dateSignature
     */
    public Calendar getDateSignature() {
        return dateSignature;
    }

    /**
     * Setter function.
     * @param montantPaiement the montantPaiement to set
     */
    public void setMontantPaiement(Float montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    /**
     * Setter function.
     * @param numeroTransaction the numeroTransaction to set
     */
    public void setNumeroTransaction(String numeroTransaction) {
        this.numeroTransaction = numeroTransaction;
    }

    /**
     * Setter function.
     * @param moyenPaiement the moyenPaiement to set
     */
    public void setMoyenPaiement(MoyenPaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * Setter function.
     * @param periodicitePaiement the periodicitePaiement to set
     */
    public void setPeriodicitePaiement(PeriodicitePaiement periodicitePaiement) {
        this.periodicitePaiement = periodicitePaiement;
    }

    /**
     * Setter function.
     * @param jourPaiement the jourPaiement to set
     */
    public void setJourPaiement(JourPaiement jourPaiement) {
        this.jourPaiement = jourPaiement;
    }

    /**
     * Setter function.
     * @param dateEditionBA the dateEditionBA to set
     */
    public void setDateEditionBA(Calendar dateEditionBA) {
        this.dateEditionBA = dateEditionBA;
    }

    /**
     * Setter function.
     * @param dateSignature the dateSignature to set
     */
    public void setDateSignature(Calendar dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Getter function.
     * @return the dateCourrier
     */
    public Calendar getDateCourrier() {
        return dateCourrier;
    }

    /**
     * Setter function.
     * @param dateCourrier the dateCourrier to set
     */
    public void setDateCourrier(Calendar dateCourrier) {
        this.dateCourrier = dateCourrier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dateCourrier == null) ? 0 : dateCourrier.hashCode());
        result = prime * result + ((dateEditionBA == null) ? 0 : dateEditionBA.hashCode());
        result = prime * result + ((dateSignature == null) ? 0 : dateSignature.hashCode());
        result = prime * result + ((jourPaiement == null) ? 0 : jourPaiement.hashCode());
        result = prime * result + ((montantPaiement == null) ? 0 : montantPaiement.hashCode());
        result = prime * result + ((moyenPaiement == null) ? 0 : moyenPaiement.hashCode());
        result = prime * result + ((numeroTransaction == null) ? 0 : numeroTransaction.hashCode());
        result = prime * result + ((periodicitePaiement == null) ? 0 : periodicitePaiement.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equalsUtil(obj)) {
            return false;
        }
        if (!(obj instanceof Adhesion)) {
            return false;
        }
        final Adhesion other = (Adhesion) obj;
        if (dateCourrier == null) {
            if (other.dateCourrier != null) {
                return false;
            }
        } else if (!dateCourrier.equals(other.dateCourrier)) {
            return false;
        }
        if (dateEditionBA == null) {
            if (other.dateEditionBA != null) {
                return false;
            }
        } else if (!dateEditionBA.equals(other.dateEditionBA)) {
            return false;
        }
        if (dateSignature == null) {
            if (other.dateSignature != null) {
                return false;
            }
        } else if (!dateSignature.equals(other.dateSignature)) {
            return false;
        }
        if (jourPaiement == null) {
            if (other.jourPaiement != null) {
                return false;
            }
        } else if (!jourPaiement.equals(other.jourPaiement)) {
            return false;
        }
        if (montantPaiement == null) {
            if (other.montantPaiement != null) {
                return false;
            }
        } else if (!montantPaiement.equals(other.montantPaiement)) {
            return false;
        }
        if (moyenPaiement == null) {
            if (other.moyenPaiement != null) {
                return false;
            }
        } else if (!moyenPaiement.equals(other.moyenPaiement)) {
            return false;
        }
        if (numeroTransaction == null) {
            if (other.numeroTransaction != null) {
                return false;
            }
        } else if (!numeroTransaction.equals(other.numeroTransaction)) {
            return false;
        }
        if (periodicitePaiement == null) {
            if (other.periodicitePaiement != null) {
                return false;
            }
        } else if (!periodicitePaiement.equals(other.periodicitePaiement)) {
            return false;
        }
        return true;
    }

    /**
     * Get the infosRib value.
     * @return the infosRib
     */
    public InfosRib getInfosRib() {
        return infosRib;
    }

    /**
     * Set the infosRib value.
     * @param infosRib the infosRib to set
     */
    public void setInfosRib(InfosRib infosRib) {
        this.infosRib = infosRib;
    }

    /**
     * Get the idFichierCNS value.
     * @return the idFichierCNS
     */
    public String getIdFichierCNS() {
        return idFichierCNS;
    }

    /**
     * Set the idFichierCNS value.
     * @param idFichierCNS the idFichierCNS to set
     */
    public void setIdFichierCNS(String idFichierCNS) {
        this.idFichierCNS = idFichierCNS;
    }

    /**
     * Get the dateClicBA value.
     * @return the dateClicBA
     */
    public Calendar getDateClicBA() {
        return dateClicBA;
    }

    /**
     * Set the dateClicBA value.
     * @param dateClicBA the dateClicBA to set
     */
    public void setDateClicBA(Calendar dateClicBA) {
        this.dateClicBA = dateClicBA;
    }

    /**
     * Get the dateTelechargementBA value.
     * @return the dateTelechargementBA
     */
    public Calendar getDateTelechargementBA() {
        return dateTelechargementBA;
    }

    /**
     * Set the dateTelechargementBA value.
     * @param dateTelechargementBA the dateTelechargementBA to set
     */
    public void setDateTelechargementBA(Calendar dateTelechargementBA) {
        this.dateTelechargementBA = dateTelechargementBA;
    }

    /**
     * Get the teletransmission value.
     * @return the teletransmission
     */
    public Boolean getTeletransmission() {
        return teletransmission;
    }

    /**
     * Set the teletransmission value.
     * @param teletransmission the teletransmission to set
     */
    public void setTeletransmission(Boolean teletransmission) {
        this.teletransmission = teletransmission;
    }

    /**
     * Getter function.
     * @return the moyenPaiementPremierReglement
     */
    public MoyenPaiement getMoyenPaiementPremierReglement() {
        return moyenPaiementPremierReglement;
    }

    /**
     * Setter function.
     * @param moyenPaiementPremierReglement the moyenPaiementPremierReglement to set
     */
    public void setMoyenPaiementPremierReglement(MoyenPaiement moyenPaiementPremierReglement) {
        this.moyenPaiementPremierReglement = moyenPaiementPremierReglement;
    }

    /**
     * Récupère la valeur de ancienneComplemetaireAdherent.
     * @return la valeur de ancienneComplemetaireAdherent
     */
    public String getAncienneComplemetaireAdherent() {
        return ancienneComplemetaireAdherent;
    }

    /**
     * Définit la valeur de ancienneComplemetaireAdherent.
     * @param ancienneComplemetaireAdherent la nouvelle valeur de ancienneComplemetaireAdherent
     */
    public void setAncienneComplemetaireAdherent(String ancienneComplemetaireAdherent) {
        this.ancienneComplemetaireAdherent = ancienneComplemetaireAdherent;
    }

    /**
     * Récupère la valeur de dateFinContratAdherent.
     * @return la valeur de dateFinContratAdherent
     */
    public Calendar getDateFinContratAdherent() {
        return dateFinContratAdherent;
    }

    /**
     * Définit la valeur de dateFinContratAdherent.
     * @param dateFinContratAdherent la nouvelle valeur de dateFinContratAdherent
     */
    public void setDateFinContratAdherent(Calendar dateFinContratAdherent) {
        this.dateFinContratAdherent = dateFinContratAdherent;
    }

    /**
     * Récupère la valeur de ancienneComplemetaireConjoint.
     * @return la valeur de ancienneComplemetaireConjoint
     */
    public String getAncienneComplemetaireConjoint() {
        return ancienneComplemetaireConjoint;
    }

    /**
     * Définit la valeur de ancienneComplemetaireConjoint.
     * @param ancienneComplemetaireConjoint la nouvelle valeur de ancienneComplemetaireConjoint
     */
    public void setAncienneComplemetaireConjoint(String ancienneComplemetaireConjoint) {
        this.ancienneComplemetaireConjoint = ancienneComplemetaireConjoint;
    }

    /**
     * Récupère la valeur de dateFinContratConjoint.
     * @return la valeur de dateFinContratConjoint
     */
    public Calendar getDateFinContratConjoint() {
        return dateFinContratConjoint;
    }

    /**
     * Définit la valeur de dateFinContratConjoint.
     * @param dateFinContratConjoint la nouvelle valeur de dateFinContratConjoint
     */
    public void setDateFinContratConjoint(Calendar dateFinContratConjoint) {
        this.dateFinContratConjoint = dateFinContratConjoint;
    }

}
