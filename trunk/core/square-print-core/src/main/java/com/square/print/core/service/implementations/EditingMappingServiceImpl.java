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
package com.square.print.core.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.square.print.core.dto.ModeleDevisDto;
import com.square.print.core.service.interfaces.EditiqueMappingService;

/**
 * .
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class EditingMappingServiceImpl implements EditiqueMappingService {

    /** Map des modèles de devis. */
    private Map < Long, String > mapModelesDevis;

    /** Fichiers statiques en telelchargement. **/
	private Map<String, String> mapFichiersStatiques;

	/** Constante correspondant à l'id du modéle devis/bulletin d'adhésion. */
	private Long constanteIdModeleDevisBulletinAdhesion;

	/** Constante correspondant à l'id du modéle de la lettre de radiation. */
	private Long constanteIdModeleLettreRadiation;

	/** Constante correspondant à l'id du modéle de la lettre de radiation de la loi chatel. */
	private Long constanteIdModeleLettreRadiationLoiChatel;

	/**
	 * Set the constanteIdModeleLettreAnnulation.
	 * @param constanteIdModeleLettreAnnulation the constanteIdModeleLettreAnnulation to set
	 */
	public void setConstanteIdModeleLettreAnnulation(Long constanteIdModeleLettreAnnulation) {
		this.constanteIdModeleLettreAnnulation = constanteIdModeleLettreAnnulation;
	}

	/**
	 * Set the mapModelesDevis.
	 * @param mapModelesDevis the mapModelesDevis to set
	 */
	public void setMapModelesDevis(Map<Long, String> mapModelesDevis) {
		this.mapModelesDevis = mapModelesDevis;
	}

	/**
	 * Set the mapFichiersStatiques.
	 * @param mapFichiersStatiques the mapFichiersStatiques to set
	 */
	public void setMapFichiersStatiques(Map<String, String> mapFichiersStatiques) {
		this.mapFichiersStatiques = mapFichiersStatiques;
	}

	/**
	 * Set the constanteIdModeleDevisBulletinAdhesion.
	 * @param constanteIdModeleDevisBulletinAdhesion the constanteIdModeleDevisBulletinAdhesion to set
	 */
	public void setConstanteIdModeleDevisBulletinAdhesion(Long constanteIdModeleDevisBulletinAdhesion) {
		this.constanteIdModeleDevisBulletinAdhesion = constanteIdModeleDevisBulletinAdhesion;
	}

	/**
	 * Set the constanteIdModeleLettreRadiation.
	 * @param constanteIdModeleLettreRadiation the constanteIdModeleLettreRadiation to set
	 */
	public void setConstanteIdModeleLettreRadiation(Long constanteIdModeleLettreRadiation) {
		this.constanteIdModeleLettreRadiation = constanteIdModeleLettreRadiation;
	}

	/**
	 * Set constanteIdModeleLettreRadiationLoiChatel.
	 * @param constanteIdModeleLettreRadiationLoiChatel the constanteIdModeleLettreRadiationLoiChatel to set
	 */
	public void setConstanteIdModeleLettreRadiationLoiChatel(Long constanteIdModeleLettreRadiationLoiChatel) {
		this.constanteIdModeleLettreRadiationLoiChatel = constanteIdModeleLettreRadiationLoiChatel;
	}

	private Long constanteIdModeleLettreAnnulation;

	@Override
	public Long getConstanteIdModeleDevisBulletinAdhesion() {
		return constanteIdModeleDevisBulletinAdhesion;
	}

	@Override
	public Long getConstanteIdModeleDevisFicheTransfert() {
		return constanteIdModeleDevisBulletinAdhesion;
	}

	@Override
	public Long getConstanteIdModeleLettreAnnulation() {
		return constanteIdModeleLettreAnnulation;
	}

	@Override
	public Long getConstanteIdModeleLettreRadiation() {
		return constanteIdModeleLettreRadiation;
	}

	@Override
	public Long getConstanteIdModeleLettreRadiationLoiChatel() {
		return constanteIdModeleLettreRadiationLoiChatel;
	}

	@Override
	public List<ModeleDevisDto> getListeModelesDevis() {
		final List<ModeleDevisDto> listeModeles = new ArrayList<ModeleDevisDto>();
        for (Long id : mapModelesDevis.keySet()) {
            final String libelle = mapModelesDevis.get(id);
            if (libelle != null && !"".equals(libelle)) {
                final ModeleDevisDto modele = new ModeleDevisDto();
                modele.setIdentifiant(id);
                modele.setLibelle(libelle);
                listeModeles.add(modele);
            }
        }
        return listeModeles;
	}

	@Override
	public Map<String, String> getMapFichiersStatiques() {
		return mapFichiersStatiques;
	}

	@Override
	public ModeleDevisDto getModelDevisById(Long idModeleDevis) {
        final String libelle = mapModelesDevis.get(idModeleDevis);
        if (libelle == null || "".equals(libelle)) {
            return null;
        }
        else {
            final ModeleDevisDto modele = new ModeleDevisDto();
            modele.setIdentifiant(idModeleDevis);
            modele.setLibelle(libelle);
            return modele;
        }
	}

}
