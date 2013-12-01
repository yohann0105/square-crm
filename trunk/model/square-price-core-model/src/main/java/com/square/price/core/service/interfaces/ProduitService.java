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
package com.square.price.core.service.interfaces;

import java.util.Calendar;
import java.util.List;

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
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.dto.ProduitLieCriteresDto;
import com.square.price.core.dto.ProduitLieDto;
import com.square.price.core.dto.ReseauGammeDto;
import com.square.price.core.dto.VetusteGammeDto;
import com.square.price.core.dto.ZonageCriteresDto;
import com.square.price.core.dto.ZonageDto;

/**
 * Interface Service Tarificateur autour des produits.
 *
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public interface ProduitService {
	/**
	 * Recuperer la liste des gammes produits par critere.<br>
	 * La liste des gammes produits est ordonée par ordre d'affichage.
	 *
	 * @param critere
	 *            le critere pour filtrer la recherche.
	 * @return la liste des gammes produit une liste vide si aucune occurence.
	 */
	List<GammeProduitDto> getListeGammesProduits(
			final GammeProduitCriteresDto critere);

	/**
	 * Recuperer la liste des produits par critere.<br>
	 * La liste des produits est ordonée.
	 *
	 * @param critere
	 *            le critere pour filtrer la recherche.
	 * @return la liste des produits une liste vide si aucune occurence.
	 */
	List<ProduitDto> getListeProduits(final ProduitCriteresDto critere);

	 /**
	 * Recuperer la liste zone tarifaire par critere.<br>
	 * La liste des refZonier est ordonée par ordre d'affichage.
	 * @param critere le critere pour filtrer la recherche.
	 * @return la liste des zone tarifaire ne liste vide si aucune occurence.
	 */
	 List<ZonageDto> getListeZonages(final ZonageCriteresDto critere);

	/**
	 * Recuperer la liste des produits lies à un produit.<br>
	 * La liste des produits lies est ordonee.
	 *
	 * @param criteres
	 *            les criteres pour la selection des produits lies.
	 * @return la liste des produits lies une liste vide si aucune occurence.
	 */
	List<ProduitLieDto> getListeProduitsLies(
			final ProduitLieCriteresDto criteres);

	/**
	 * Recuperer les criteres d'un produit.
	 *
	 * @param criteres
	 *            criteres pour la recherche de critere.
	 * @return la liste des criteres du produits une liste vide si aucun
	 *         critere.
	 */
	List<CritereDto> getListeCriteresParProduit(
			final CritereCriteresDto criteres);

	 /**
	 * Recuperer la liste de tous les critères disponibles.
	 * @return la liste des criteres.
	 */
	 List<CritereDto> getListeCriteres();
	//
	// /**
	// * Recuperer la liste des gammesGestion.
	// * @return la liste.
	// */
	// List<GestionGammeDto> getListeGestionGamme();
	//
	// /**
	// * Recuperer la liste des gammeReseau.
	// * @return la liste.
	// */
	// List<ReseauGammeDto> getListeReseauGamme();
	//
	// /**
	// * Recuperer la liste des gammeVetuste.
	// * @return la liste.
	// */
	// List<VetusteGammeDto> getListeVetusteGamme();
	//
	// /**
	// * Recuperer la liste des gammeVetuste.
	// * @return la liste.
	// */
	// List<CategorieGammeDto> getListeCategorieGamme();

	 /**
	 * Recuperer la liste des gammeReseau.
	 * @param libelle le libelle
	 * @return la liste.
	 */
	 List<ReseauGammeDto> getListeReseauGammeParLibelle(String libelle);

	 /**
	 * Recuperer la liste des gammeVetuste.
	 * @param libelle le libelle
	 * @return la liste.
	 */
	 List<VetusteGammeDto> getListeVetusteGammeParLibelle(String libelle);

	 /**
	 * Recuperer la liste des gammeVetuste.
	 * @param libelle le libelle
	 * @return la liste.
	 */
	 List<CategorieGammeDto> getListeCategorieGammeParLibelle(String libelle);

	 /**
	 * Recuperer une gammeReseau.
	 * @param id l'id
	 * @return l'objet
	 */
	 ReseauGammeDto getReseauGamme(Integer id);

	 /**
	 * Recuperer une gammeVetuste.
	 * @param id l'id
	 * @return l'objet
	 */
	 VetusteGammeDto getVetusteGamme(Integer id);

	 /**
	 * Recuperer une gammeVetuste.
	 * @param id l'id
	 * @return l'objet
	 */
	 CategorieGammeDto getCategorieGamme(Integer id);

	/**
	 * Recuperer les contraintes de vente d'un produit.
	 *
	 * @param identifiantProduit
	 *            l'identifiant du produit.
	 * @return les contrainte null si aucune contrainte pour le produit ou si le
	 *         produit n'existe pas.
	 */
	ContrainteVenteDto getContrainteVenteParProduit(
			final Integer identifiantProduit);

	/**
	 * Récuperer le mode de paiement d'un produit.
	 *
	 * @param identifiantProduit
	 *            l'identifiant du produit.
	 * @return le mode de paiement.
	 */
	ModePaiementDto getModePaiementParProduit(final Integer identifiantProduit);

	/**
	 * Indique si un gamme est du type prevoyance.
	 *
	 * @param identifiantGammeProduit
	 *            l'identifiant de la gamme.
	 * @return true si oui false sinon.
	 */
	Boolean isItGammePrevoyance(final Integer identifiantGammeProduit);

	 /**
	 * Determiner si un produit possede un critere.
	 * @param identifiantProduit l'identifiant du produit.
	 * @param identifiantCritere du critere.
	 * @return true si le produit possede le critere false sinon.
	 */
	 Boolean isProduitPossedeCritere(final Integer identifiantProduit, final
	 Integer identifiantCritere);

	// /**
	// * Determine si un bareme dans la liste possède un critère.
	// * @param identifiantsBaremes identifiants des baremes
	// * @param identifiantCritere identifiant du critère
	// * @return true si le produit possède le critère false sinon
	// */
	// Boolean isBaremesPossedeCritere(final List<String> identifiantsBaremes,
	// final Integer identifiantCritere);
	//
	/**
	 * Récupere le libelle de la valeur d'un critere par l'identifiant et la
	 * valeur du critere.
	 *
	 * @param identifiantCritere
	 *            identifiant du critere.
	 * @param valeurCritere
	 *            la valeur du critere.
	 * @param identifiantProduit
	 *            du produit associé au critere.
	 * @param dateEffet
	 *            la date effet du criteres.
	 * @return la liste des libelle une liste vide si aucune occurence.
	 */
	List<String> getLibsMappingCritere(final Integer identifiantCritere,
			final String valeurCritere, final Integer identifiantProduit,
			final Calendar dateEffet);


	 /**
	 * Récupère la liste des produits d'un adhérent.
	 * @param uidAdherent uidAdherent
	 * @param produitAia le produitAia
	 * @param garantieAia la garantieAia
	 * @param idApplication l'identifiant de l'application
	 * @return un DTO contenant la liste des produits de l'adhérent
	 */
	 ListeProduitsAdherentDto getListeProduitsAdherent(Long uidAdherent,
	 String produitAia, String garantieAia, Integer idApplication);
	//
	// /**
	// * Indique si un produit peut être lié (pour l'application demandée).
	// * @param idProduit l'identifiant du produit
	// * @param idApplication l'identifiant de l'application
	// * @return true si le produit peut être lié, false sinon
	// */
	// Boolean isProduitPeutEtreLie(Integer idProduit, Integer idApplication);
//
//	/**
//	 * Récupère la liste des produits banco.
//	 *
//	 * @return la liste des produits banco.
//	 */
//	List<Integer> getListeProduitsBanco();
//
//
//	 /**
//	 * Teste si le produit est un produit banco.
//	 * @param idProduit l'identifiant du produit à tester
//	 * @return true si c'est un produit banco, false sinon
//	 */
//	 Boolean isProduitBanco(Integer idProduit);
//
//	/**
//	 * Récupère la liste des gammes nouvelles offres.
//	 *
//	 * @return la liste des gammes nouvelles offres.
//	 */
//	List<Integer> getListeGammesNouvellesOffresDeuxMilleHuit();

	//
	// /**
	// * Récupère la liste des gammes nouvelles offres.
	// * @return la liste des gammes nouvelles offres.
	// */
	// List<GammeProduitDto> getListeGammesDtoNouvellesOffresDeuxMilleHuit();
//
//	/**
//	 * Teste si la gamme est une gamme nouvelles offres.
//	 *
//	 * @param idGamme
//	 *            l'identifiant de la gamme à tester
//	 * @return true si la gamme est une gamme nouvelles offres, false sinon
//	 */
//	Boolean isGammeNouvellesOffresDeuxMilleHuit(Integer idGamme);
//
//	/**
//	 * Teste si le produit est un produit de la nouvelle offre 2008.
//	 *
//	 * @param idProduit
//	 *            l'identifiant du produit à tester.
//	 * @return true si le produit est un produit de la nouvelles offres, false
//	 *         sinon.
//	 */
//	Boolean isProduitNouvellesOffresDeuxMilleHuit(Integer idProduit);
	//
	// /**
	// * Récupère une liste de formules de prestation en fonction de critères de
	// recherche.
	// * @param criteres les critères de recherche
	// * @return la liste des formules de prestation recherchées
	// */
	// List<FormuleSimpleDto>
	// getListeFormulesPrestationByCriteres(FormulePrestationCriteresDto
	// criteres);
	//
	// /**
	// * Récupère une formule de prestation par son identifiant.
	// * @param id : l'identifiant.
	// * @return la formule trouvée.
	// */
	// FormuleSimpleDto getFormulePrestationById(String id);
	//
//	 /**
//	 * Test si un produit est une ancienne offr ou non.
//	 * @param idGamme l'identifiant de la gamme associée au produit.
//	 * @return true si le produit est une ancienne offre, false sinon.
//	 */
//	 Boolean isProduitAncienneOffre(Integer idGamme);

	 /**
	 * Récupère l'image d'un produit.
	 * @param criteres les critères de recherche de l'image
	 * @return le contenu de l'image, null si non trouvée
	 */
	 byte[] getImageProduit(CriteresImageProduitDto criteres);

	 /**
	 * Récupère l'image d'une gamme.
	 * @param criteres les critères de recherche de l'image
	 * @return le contenu de l'image, null si non trouvée
	 */
	 byte[] getImageGamme(CriteresImageGammeDto criteres);
	//
	// /**
	// * Récupère la liste des gammeVetuste en fonction des critères.
	// * @param criteres les critères de recherche.
	// * @return la liste.
	// */
	// List<CategorieGammeDto>
	// getListeCategorieGammeByCritere(CritereCategorieGammeDto criteres);
	//
	// /**
	// * Récupère la liste des produits collectifs en fonction de critères.
	// * @param criteres les critères de recherche
	// * @return la liste des produits collectifs
	// */
	// List<ProduitCollectifDto>
	// getListeProduitsCollectifsByCriteres(ProduitCollectifCriteresDto
	// criteres);
	//
	// /**
	// * Récuperer les baremes en fonction des criteres.
	// * @param criteres la liste des criteres.
	// * @return la liste des baremes qui valide les criteres.
	// */
	// List<BaremeDto> getListeBaremes(final BaremeCriteresDto criteres);
	//
	 /**
	 * Indique si un produit est ouvert à la vente.
	 * @param idProduit l'identifiant du produit.
	 * @return true si le produit est ouvert à la vente, false sinon.
	 */
	 boolean isProduitOuvertVente(Integer idProduit);
	//
	// /**
	// * Recherche de ids de bareme par produit.
	// * @param idProduit l'id du produit
	// * @param recupereBareme2 flag pour savoir si on recupere egalement le
	// bareme2 du produit.
	// * @return la liste des ids de bareme
	// */
	// List<String> getIdsBaremesParProduit(Integer idProduit, boolean
	// recupereBareme2);
}
