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
package com.square.price.core.service.implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.price.core.dto.CategorieGammeDto;
import com.square.price.core.dto.ContrainteVenteDto;
import com.square.price.core.dto.CritereCriteresDto;
import com.square.price.core.dto.CritereDto;
import com.square.price.core.dto.CriteresImageGammeDto;
import com.square.price.core.dto.CriteresImageProduitDto;
import com.square.price.core.dto.GammeProduitCriteresDto;
import com.square.price.core.dto.GammeProduitDto;
import com.square.price.core.dto.ListeProduitsAdherentDto;
import com.square.price.core.dto.ModePaiementDto;
import com.square.price.core.dto.ProduitAdherentDto;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.dto.ProduitLieCriteresDto;
import com.square.price.core.dto.ProduitLieDto;
import com.square.price.core.dto.ReseauGammeDto;
import com.square.price.core.dto.VetusteGammeDto;
import com.square.price.core.dto.ZonageCriteresDto;
import com.square.price.core.dto.ZonageDto;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.TarificateurMappingService;

/**
 * Implémentation de ProduitService.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 *
 */
public class ProduitServiceImpl implements ProduitService {

	private TarificateurMappingService tarificateurMappingService;

	@Override
	public CategorieGammeDto getCategorieGamme(Integer id) {
		final CategorieGammeDto categorie = new CategorieGammeDto();
		categorie.setIdentifiant(1);
		categorie.setLibelle("Catégorie");
		return categorie;
	}

	@Override
	public ContrainteVenteDto getContrainteVenteParProduit(
			Integer identifiantProduit) {
		return new ContrainteVenteDto();
	}

	@Override
	public byte[] getImageGamme(CriteresImageGammeDto criteres) {
		return null;
	}

	@Override
	public byte[] getImageProduit(CriteresImageProduitDto criteres) {
		return null;
	}

	@Override
	public List<String> getLibsMappingCritere(Integer identifiantCritere,
			String valeurCritere, Integer identifiantProduit, Calendar dateEffet) {
		return new ArrayList<String>();
	}

	@Override
	public List<CategorieGammeDto> getListeCategorieGammeParLibelle(
			String libelle) {
		final CategorieGammeDto categorie = new CategorieGammeDto();
		categorie.setIdentifiant(1);
		categorie.setLibelle("Catégorie 1");
		return Arrays.asList(new CategorieGammeDto[] {categorie});
	}

	@Override
	public List<CritereDto> getListeCriteres() {
		return new ArrayList<CritereDto>();
	}

	@Override
	public List<CritereDto> getListeCriteresParProduit(
			CritereCriteresDto criteres) {
		return new ArrayList<CritereDto>();
	}

	@Override
	public List<GammeProduitDto> getListeGammesProduits(
			GammeProduitCriteresDto critere) {
		final GammeProduitDto gamme = new GammeProduitDto();
		gamme.setIdentifiant(1);
		gamme.setLibelle("Gamme 1");
		return Arrays.asList(new GammeProduitDto[] {gamme});
	}

	@Override
	public List<ProduitDto> getListeProduits(ProduitCriteresDto critere) {
		final ProduitDto produit = new ProduitDto();
		produit.setIdentifiant(1);
		produit.setLibelleCommercial("Produit 1");
		produit.setLibelle("Produit 1");
		produit.setIdentifiantFamille(tarificateurMappingService.getIdentifiantFamilleNo());
		final GammeProduitDto gamme = new GammeProduitDto();
		gamme.setIdentifiant(1);
		gamme.setLibelle("Gamme 1");
		produit.setGamme(gamme);
		final IdentifiantLibelleDto modeTarification = new IdentifiantLibelleDto();
		modeTarification.setIdentifiant(1l);
		produit.setModeTarification(modeTarification);
		return Arrays.asList(new ProduitDto[] {produit});
	}

	@Override
	public ListeProduitsAdherentDto getListeProduitsAdherent(Long uidAdherent,
			String produitAia, String garantieAia, Integer idApplication) {
		final ProduitAdherentDto produit = new ProduitAdherentDto();
		produit.setIdentifiant(1);
		final ListeProduitsAdherentDto listeProduits = new ListeProduitsAdherentDto();
		listeProduits.setUidAdherent(uidAdherent);
		listeProduits.setListeProduitsLies(Arrays.asList(new ProduitAdherentDto[] {}));
		return listeProduits;
	}


	@Override
	public List<ProduitLieDto> getListeProduitsLies(
			ProduitLieCriteresDto criteres) {
		final ProduitDto produit = new ProduitDto();
		produit.setIdentifiant(2);
		produit.setLibelleCommercial("Produit Lié 1");
		produit.setIdentifiantFamille(tarificateurMappingService.getIdentifiantFamilleBonus1());
		final GammeProduitDto gamme = new GammeProduitDto();
		gamme.setIdentifiant(1);
		gamme.setLibelle("Gamme 1");
		produit.setGamme(gamme);
		final IdentifiantLibelleDto modeTarification = new IdentifiantLibelleDto();
		modeTarification.setIdentifiant(1l);
		produit.setModeTarification(modeTarification);
		final ProduitLieDto produitLie = new ProduitLieDto();
		produitLie.setProduitLie(produit);
		produitLie.setActif(Boolean.TRUE);
		produitLie.setOptionnel(Boolean.TRUE);
		return Arrays.asList(new ProduitLieDto[] {produitLie});
	}

	@Override
	public List<ReseauGammeDto> getListeReseauGammeParLibelle(String libelle) {
		return Arrays.asList(new ReseauGammeDto[] {getReseauGamme(1)});
	}

	@Override
	public List<VetusteGammeDto> getListeVetusteGammeParLibelle(String libelle) {
		return Arrays.asList(new VetusteGammeDto[] {getVetusteGamme(1)});
	}

	@Override
	public List<ZonageDto> getListeZonages(ZonageCriteresDto critere) {
		final ZonageDto zonage = new ZonageDto();
		zonage.setCodeDepartement("1");
		zonage.setCodeZone(1);
		zonage.setCodeZonier("1");
		zonage.setLibelleDepartement("Département 1");
		zonage.setLibelleZone("Zone 1");
		return Arrays.asList(new ZonageDto[] {});
	}

	@Override
	public ModePaiementDto getModePaiementParProduit(Integer identifiantProduit) {
		final ModePaiementDto modePaiementDto = new ModePaiementDto();
		modePaiementDto.setIdentifiant(1);
		modePaiementDto.setLibelle("Prélèvement");
		return modePaiementDto;
	}

	@Override
	public ReseauGammeDto getReseauGamme(Integer id) {
		final ReseauGammeDto reseau = new ReseauGammeDto();
		reseau.setIdentifiant(1);
		reseau.setLibelle("Réseau 1");
		return reseau;
	}

	@Override
	public VetusteGammeDto getVetusteGamme(Integer id) {
		final VetusteGammeDto vetusteGammeDto = new VetusteGammeDto();
		vetusteGammeDto.setIdentifiant(1);
		vetusteGammeDto.setLibelle("Ouvert à la vente");
		return vetusteGammeDto;
	}

	@Override
	public Boolean isItGammePrevoyance(Integer identifiantGammeProduit) {
		return false;
	}

	@Override
	public boolean isProduitOuvertVente(Integer idProduit) {
		return true;
	}

	@Override
	public Boolean isProduitPossedeCritere(Integer identifiantProduit,
			Integer identifiantCritere) {
		return true;
	}

	/**
	 * Set tarificateur MappingService.
	 * @param tarificateurMappingService le service de mapping
	 */
	public void setTarificateurMappingService(TarificateurMappingService tarificateurMappingService) {
		this.tarificateurMappingService = tarificateurMappingService;
	}
}
