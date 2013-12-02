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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Dto de criteres garantie bareme.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class GarantieBaremeCriteresDto implements Serializable {

    private static final long serialVersionUID = -2483904391813196547L;

    private String garantie;

    private String contrat;

    /** Flag pour savoir si il s'agit d'un contrat collectif. */
    private Boolean contratCollectif = Boolean.FALSE;

    private Long uidAssure;

    private String eidTypePayeur;

    private String eidTarifType;

    private String garantieGestion;

    private String produitGestion;

    private Long idRoleGarantie;

    private Calendar dateDebut;

    private Calendar dateFin;

    private Calendar dateEffet;

    private List<String> listeIdsBareme;

    /**
     * Get the value of garantie.
     * @return the garantie
     */
    public String getGarantie() {
        return garantie;
    }

    /**
     * Set the value of garantie.
     * @param garantie the garantie to set
     */
    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }

    /**
     * Get the value of contrat.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the value of contrat.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Get the value of uidAssure.
     * @return the uidAssure
     */
    public Long getUidAssure() {
        return uidAssure;
    }

    /**
     * Set the value of uidAssure.
     * @param uidAssure the uidAssure to set
     */
    public void setUidAssure(Long uidAssure) {
        this.uidAssure = uidAssure;
    }

    /**
     * Get the value of garantieGestion.
     * @return the garantieGestion
     */
    public String getGarantieGestion() {
        return garantieGestion;
    }

    /**
     * Set the value of garantieGestion.
     * @param garantieGestion the garantieGestion to set
     */
    public void setGarantieGestion(String garantieGestion) {
        this.garantieGestion = garantieGestion;
    }

    /**
     * Get the value of produitGestion.
     * @return the produitGestion
     */
    public String getProduitGestion() {
        return produitGestion;
    }

    /**
     * Set the value of produitGestion.
     * @param produitGestion the produitGestion to set
     */
    public void setProduitGestion(String produitGestion) {
        this.produitGestion = produitGestion;
    }

    /**
     * Get the value of idRoleGarantie.
     * @return the idRoleGarantie
     */
    public Long getIdRoleGarantie() {
        return idRoleGarantie;
    }

    /**
     * Set the value of idRoleGarantie.
     * @param idRoleGarantie the idRoleGarantie to set
     */
    public void setIdRoleGarantie(Long idRoleGarantie) {
        this.idRoleGarantie = idRoleGarantie;
    }

    /**
     * Get the value of dateDebut.
     * @return the dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Set the value of dateDebut.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Get the value of dateFin.
     * @return the dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Set the value of dateFin.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Get the value of dateEffet.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Set the value of dateEffet.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Get the value of contratCollectif.
     * @return the contratCollectif
     */
    public Boolean getContratCollectif() {
        return contratCollectif;
    }

    /**
     * Set the value of contratCollectif.
     * @param contratCollectif the contratCollectif to set
     */
    public void setContratCollectif(Boolean contratCollectif) {
        this.contratCollectif = contratCollectif;
    }

    /**
     * Get the value of eidTypePayeur.
     * @return the eidTypePayeur
     */
    public String getEidTypePayeur() {
        return eidTypePayeur;
    }

    /**
     * Set the value of eidTypePayeur.
     * @param eidTypePayeur the eidTypePayeur to set
     */
    public void setEidTypePayeur(String eidTypePayeur) {
        this.eidTypePayeur = eidTypePayeur;
    }

    /**
     * Get the value of eidTarifType.
     * @return the eidTarifType
     */
    public String getEidTarifType() {
        return eidTarifType;
    }

    /**
     * Set the value of eidTarifType.
     * @param eidTarifType the eidTarifType to set
     */
    public void setEidTarifType(String eidTarifType) {
        this.eidTarifType = eidTarifType;
    }

    @Override
    public String toString() {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final StringBuffer buffer = new StringBuffer();
        if (this.garantie != null) {
            buffer.append(" garantie=").append(this.garantie);
        }
        if (this.contrat != null) {
            buffer.append(" contrat=").append(this.contrat);
        }
        if (this.contratCollectif != null) {
            buffer.append(" contratCollectif=").append(this.contratCollectif);
        }
        if (this.uidAssure != null) {
            buffer.append(" uidAssure=").append(this.uidAssure);
        }
        if (this.eidTypePayeur != null) {
            buffer.append(" eidTypePayeur=").append(this.eidTypePayeur);
        }
        if (this.eidTarifType != null) {
            buffer.append(" eidTarifType=").append(this.eidTarifType);
        }
        if (this.garantieGestion != null) {
            buffer.append(" garantieGestion=").append(this.garantieGestion);
        }
        if (this.produitGestion != null) {
            buffer.append(" produitGestion=").append(this.produitGestion);
        }
        if (this.idRoleGarantie != null) {
            buffer.append(" idRoleGarantie=").append(this.idRoleGarantie);
        }
        if (this.dateDebut != null) {
            buffer.append(" dateDebut=").append(sdf.format(this.dateDebut.getTime()));
        }
        if (this.dateFin != null) {
            buffer.append(" dateFin=").append(sdf.format(this.dateFin.getTime()));
        }
        if (this.dateEffet != null) {
            buffer.append(" dateEffet=").append(sdf.format(this.dateEffet.getTime()));
        }
        return buffer.toString();
    }

    /**
     * Get the value of listeIdsBareme.
     * @return the listeIdsBareme
     */
    public List<String> getListeIdsBareme() {
        return listeIdsBareme;
    }

    /**
     * Set the value of listeIdsBareme.
     * @param listeIdsBareme the listeIdsBareme to set
     */
    public void setListeIdsBareme(List<String> listeIdsBareme) {
        this.listeIdsBareme = listeIdsBareme;
    }

}
