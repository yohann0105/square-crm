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
import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO qui encapsule les informations d'un contrat.
 *
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class ContratDto extends ContratSimpleDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 2349171794801802811L;

    /**
     * Identifiant et libellé du segment du contrat.
     */
    private IdentifiantLibelleDto segment;

    /**
     * Récapitulatif des garanties souscrites dans le contrat.
     */
    private RecapitulatifGarantiesContratDto recapitulatifGarantiesContrat;

    /**
     * L'apporteur du contrat.
     */
    private String apporteur;

    /**
     * Le gestionnaire du contrat.
     */
    private String gestionnaire;

    /**
     * Liste des garanties souscrites dans le contrat.
     */
    private List<GarantieDto> listeGaranties;

    /**
     * Informations de paiement pour les prestations associées au contrat.
     */
    private InfosPaiementDto infosPaiementPrestation;

    /**
     * Informations de paiement pour les cotisations associées au contrat.
     */
    private InfosPaiementDto infosPaiementCotisation;

    /**
     * Infos banque de cotisation.
     */
    private InfosBanqueDto infoBanqueCotisation;

    /**
     * Infos banque de prestation.
     */
    private InfosBanqueDto infoBanquePrestation;

    /** Souscripteur payeur. */
    private IdentifiantLibelleDto souscripteur;

    /** Type payeur. */
    private IdentifiantLibelleDto typePayeur;

    /**
     * Liste des ajustements appliqués à la garantie.
     */
    private List<AjustementDto> listeAjustements;

    /**
     * Getter function.
     * @return the recapitulatifGarantiesContrat
     */
    public RecapitulatifGarantiesContratDto getRecapitulatifGarantiesContrat() {
        return recapitulatifGarantiesContrat;
    }

    /**
     * Getter function.
     * @return the apporteur
     */
    public String getApporteur() {
        return apporteur;
    }

    /**
     * Getter function.
     * @return the infosPaiementPrestation
     */
    public InfosPaiementDto getInfosPaiementPrestation() {
        return infosPaiementPrestation;
    }

    /**
     * Getter function.
     * @return the infosPaiementCotisation
     */
    public InfosPaiementDto getInfosPaiementCotisation() {
        return infosPaiementCotisation;
    }

    /**
     * Setter function.
     * @param recapitulatifGarantiesContrat the recapitulatifGarantiesContrat to set
     */
    public void setRecapitulatifGarantiesContrat(RecapitulatifGarantiesContratDto recapitulatifGarantiesContrat) {
        this.recapitulatifGarantiesContrat = recapitulatifGarantiesContrat;
    }

    /**
     * Setter function.
     * @param apporteur the apporteur to set
     */
    public void setApporteur(String apporteur) {
        this.apporteur = apporteur;
    }

    /**
     * Setter function.
     * @param infosPaiementPrestation the infosPaiementPrestation to set
     */
    public void setInfosPaiementPrestation(InfosPaiementDto infosPaiementPrestation) {
        this.infosPaiementPrestation = infosPaiementPrestation;
    }

    /**
     * Setter function.
     * @param infosPaiementCotisation the infosPaiementCotisation to set
     */
    public void setInfosPaiementCotisation(InfosPaiementDto infosPaiementCotisation) {
        this.infosPaiementCotisation = infosPaiementCotisation;
    }

    /**
     * Getter function.
     * @return the listeGaranties
     */
    public List<GarantieDto> getListeGaranties() {
        if (listeGaranties == null) {
            listeGaranties = new ArrayList<GarantieDto>();
        }
        return listeGaranties;
    }

    /**
     * Setter function.
     * @param listeGaranties the listeGaranties to set
     */
    public void setListeGaranties(List<GarantieDto> listeGaranties) {
        this.listeGaranties = listeGaranties;
    }

    /**
     * Récupère la valeur de segment.
     * @return la valeur de segment
     */
    public IdentifiantLibelleDto getSegment() {
        return segment;
    }

    /**
     * Définit la valeur de segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleDto segment) {
        this.segment = segment;
    }

    /**
     * Récupère la valeur de souscripteur.
     * @return la valeur de souscripteur
     */
    public IdentifiantLibelleDto getSouscripteur() {
        return souscripteur;
    }

    /**
     * Définit la valeur de souscripteur.
     * @param souscripteur la nouvelle valeur de souscripteur
     */
    public void setSouscripteur(IdentifiantLibelleDto souscripteur) {
        this.souscripteur = souscripteur;
    }

    /**
     * Récupère la valeur de typePayeur.
     * @return la valeur de typePayeur
     */
    public IdentifiantLibelleDto getTypePayeur() {
        return typePayeur;
    }

    /**
     * Définit la valeur de typePayeur.
     * @param typePayeur la nouvelle valeur de typePayeur
     */
    public void setTypePayeur(IdentifiantLibelleDto typePayeur) {
        this.typePayeur = typePayeur;
    }

    /**
     * Getter function.
     * @return the listeAjustements
     */
    public List<AjustementDto> getListeAjustements() {
        if (listeAjustements == null) {
            listeAjustements = new ArrayList<AjustementDto>();
        }
        return listeAjustements;
    }

    /**
     * Setter function.
     * @param listeAjustements the listeAjustements to set
     */
    public void setListeAjustements(List<AjustementDto> listeAjustements) {
        this.listeAjustements = listeAjustements;
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
     * récupérer les infos banque pour cotisation.
     * @return infoBanqueCotisation the info banque cotisation
     */
	public InfosBanqueDto getInfoBanqueCotisation() {
		return infoBanqueCotisation;
	}

	/**
	 * définit les infos banques cotisation.
	 * @param infoBanqueCotisation the infos banque cotisation
	 */
	public void setInfoBanqueCotisation(InfosBanqueDto infoBanqueCotisation) {
		this.infoBanqueCotisation = infoBanqueCotisation;
	}

	/**
     * récupérer les infos banque pour prestation.
     * @return infoBanqueCotisation the info banque prestation
     */
	public InfosBanqueDto getInfoBanquePrestation() {
		return infoBanquePrestation;
	}

	/**
	 * définit les infos banques prestation.
	 * @param infoBanquePrestation the infos banque prestation
	 */
	public void setInfoBanquePrestation(InfosBanqueDto infoBanquePrestation) {
		this.infoBanquePrestation = infoBanquePrestation;
	}
}
