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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * DTO représentant le bénéficiaire d'un produit et sa cotisation.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class BeneficiaireMontantBADto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 568999122112446943L;

    /** Identifiant du prospect (si le bénéficiaire est le prospect). */
    private Long idProspect;

    /** Identifiant du bénéficiaire (si le bénéficiaire est un bénéficiaire du prospect). */
    private Long idBeneficiaire;

    /** Montant sans la remise. */
    private Float montantSansRemise;

    /** Montant de la remise. */
    private Float montantRemise;

    /** Date d'effet. */
    private Calendar dateEffet;

    /** Liste des critères. */
    private List < CritereProduitBADto > listeCriteres;

    /** Pourcentage du bonus Eco. */
    private Integer pourcentageBonusEco;

    /** Indique si le montant est offert pour ce bénéficiaire. */
    private Boolean offert;

    /** Constructeur. */
    public BeneficiaireMontantBADto() { }

    /**
     * Constructeur.
     * @param idProspect l'identifiant du prospect (si le bénéficiaire est le prospect)
     * @param idBeneficiaire l'identifiant du bénéficiaire (si le bénéficiaire est un bénéficiaire du prospect)
     * @param montantSansRemise le montant sans la remise pour ce bénéficiaire
     * @param montantRemise le montant de la remise pour ce bénéficiaire
     * @param dateEffet la date d'effet du montant
     * @param listeCriteres la liste des critères
     * @param pourcentageBonusEco le pourcentage du bonus Eco
     * @param offert flag indiquant que le montant est offert pour ce bénéficiaire
     */
    public BeneficiaireMontantBADto(Long idProspect, Long idBeneficiaire, Float montantSansRemise, Float montantRemise, Calendar dateEffet,
            List < CritereProduitBADto > listeCriteres, Integer pourcentageBonusEco, Boolean offert) {
        this.idProspect = idProspect;
        this.idBeneficiaire = idBeneficiaire;
        this.montantSansRemise = montantSansRemise;
        this.montantRemise = montantRemise;
        this.dateEffet = dateEffet;
        this.listeCriteres = listeCriteres;
        this.pourcentageBonusEco = pourcentageBonusEco;
        this.offert = offert;
    }

    /**
     * Définit la valeur de idBeneficiaire.
     * @return la valeur de idBeneficiaire
     */
    public Long getIdBeneficiaire() {
        return idBeneficiaire;
    }

    /**
     * Définit la valeur de idBeneficiaire.
     * @param idBeneficiaire la nouvelle valeur de idBeneficiaire
     */
    public void setIdBeneficiaire(Long idBeneficiaire) {
        this.idBeneficiaire = idBeneficiaire;
    }

    /**
     * Définit la valeur de listeCriteres.
     * @return la valeur de listeCriteres
     */
    public List < CritereProduitBADto > getListeCriteres() {
        if (listeCriteres == null) {
            listeCriteres = new ArrayList < CritereProduitBADto > ();
        }
        return listeCriteres;
    }

    /**
     * Définit la valeur de listeCriteres.
     * @param listeCriteres la nouvelle valeur de listeCriteres
     */
    public void setListeCriteres(List < CritereProduitBADto > listeCriteres) {
        this.listeCriteres = listeCriteres;
    }

    /**
     * Définit la valeur de idProspect.
     * @return la valeur de idProspect
     */
    public Long getIdProspect() {
        return idProspect;
    }

    /**
     * Définit la valeur de idProspect.
     * @param idProspect la nouvelle valeur de idProspect
     */
    public void setIdProspect(Long idProspect) {
        this.idProspect = idProspect;
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
     * Récupère la valeur de pourcentageBonusEco.
     * @return la valeur de pourcentageBonusEco
     */
    public Integer getPourcentageBonusEco() {
        return pourcentageBonusEco;
    }

    /**
     * Définit la valeur de pourcentageBonusEco.
     * @param pourcentageBonusEco la nouvelle valeur de pourcentageBonusEco
     */
    public void setPourcentageBonusEco(Integer pourcentageBonusEco) {
        this.pourcentageBonusEco = pourcentageBonusEco;
    }

    /**
     * Récupère la valeur de montantSansRemise.
     * @return la valeur de montantSansRemise
     */
    public Float getMontantSansRemise() {
        return montantSansRemise;
    }

    /**
     * Définit la valeur de montantSansRemise.
     * @param montantSansRemise la nouvelle valeur de montantSansRemise
     */
    public void setMontantSansRemise(Float montantSansRemise) {
        this.montantSansRemise = montantSansRemise;
    }

    /**
     * Récupère la valeur de montantRemise.
     * @return la valeur de montantRemise
     */
    public Float getMontantRemise() {
        return montantRemise;
    }

    /**
     * Définit la valeur de montantRemise.
     * @param montantRemise la nouvelle valeur de montantRemise
     */
    public void setMontantRemise(Float montantRemise) {
        this.montantRemise = montantRemise;
    }

    /**
     * Récupère la valeur de offert.
     * @return la valeur de offert
     */
    public Boolean getOffert() {
        return offert;
    }

    /**
     * Définit la valeur de offert.
     * @param offert la nouvelle valeur de offert
     */
    public void setOffert(Boolean offert) {
        this.offert = offert;
    }

    /**
     * Calcule le montant avec la remise.
     * @return le montant avec la remise
     */
    public Float getMontantAvecRemise() {
        if (montantSansRemise != null) {
            float montantAvecRemise = 0f;
            montantAvecRemise += montantSansRemise;
            if (montantRemise != null) {
                montantAvecRemise -= montantRemise;
            }
            return montantAvecRemise;
        }  else {
            return null;
        }
    }
}
