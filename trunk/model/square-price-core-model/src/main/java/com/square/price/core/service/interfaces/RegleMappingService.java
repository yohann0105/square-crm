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

/**
 * Service de distribution de constantes liées au moteur de règles.
 * 
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public interface RegleMappingService {
	// /**
	// * Récupération de l'identifiant du comparateur d'égalité.
	// * @return l'identifiant.
	// */
	// Integer getIdComparateurEgal();
	//
	// /**
	// * Récupération de l'identifiant du comparateur de supériorité stricte.
	// * @return l'identifiant.
	// */
	// Integer getIdComparateurSuperieur();
	//
	// /**
	// * Récupération de l'identifiant du comparateur d'infériorité stricte.
	// * @return l'identifiant.
	// */
	// Integer getIdComparateurInferieur();
	//
	// /**
	// * Récupération de l'identifiant du comparateur de supériorité.
	// * @return l'identifiant.
	// */
	// Integer getIdComparateurSuperieurEgal();
	//
	// /**
	// * Récupération de l'identifiant du comparateur d'infériorité.
	// * @return l'identifiant.
	// */
	// Integer getIdComparateurInferieurEgal();
	//
	// /**
	// * Test si un type de valeur du moteur de règle correspond à un entier.
	// * @param type le type à tester
	// * @return vrai ou faux
	// */
	// Boolean isTypeValeurInteger(String type);
	//
	// /**
	// * Test si un type de valeur du moteur de règle correspond à un Long.
	// * @param type le type à tester
	// * @return vrai ou faux
	// */
	// Boolean isTypeValeurLong(String type);
	//
	// /**
	// * Test si un type de valeur du moteur de règle correspond à une chaine.
	// * @param type le type à tester
	// * @return vrai ou faux
	// */
	// Boolean isTypeValeurString(String type);
	//
	// /**
	// * Récupération de l'identifiant de la règle assembliste "Tous".
	// * @return l'identifiant correspondant.
	// */
	// Integer getIdRegleAssemblisteTous();
	//
	// /**
	// * Récupération de l'identifiant de la règle assembliste "Au moins un".
	// * @return l'identifiant correspondant.
	// */
	// Integer getIdRegleAssemblisteAuMoinsUn();
	//
	// /**
	// * Récupération de l'identifiant de la règle assembliste
	// "Tous (au moins deux)".
	// * @return l'identifiant correspondant.
	// */
	// Integer getIdRegleAssemblisteTousAuMoinsDeux();

	 /**
	 * Récupération de l'identifiant de la règle "Réduction selon Produit".
	 * @return l'identifiant correspondant.
	 */
	 Integer getIdRegleReductionSelonProduit();
	//
	// /**
	// * Récupération de l'identifiant de la règle
	// "Réduction selon Composition familiale".
	// * @return l'identifiant correspondant.
	// */
	// Integer getIdRegleReductionSelonCompoFamiliale();

	 /**
	 * Récupération de l'identifiant de la règle "Stage".
	 * @return l'identifiant correspondant.
	 */
	 Integer getIdRegleStage();

	/**
	 * Récupération de l'identifiant du critère age.
	 * 
	 * @return l'identifiant correspondant.
	 */
	Integer getIdCritereAge();

	/**
	 * Récupération de l'identifiant du critère "Produit".
	 * 
	 * @return l'identifiant correspondant.
	 */
	Integer getIdCritereProduit();

	/**
	 * Récupération de l'identifiant du critère "Role".
	 * 
	 * @return l'identifiant correspondant.
	 */
	Integer getIdCritereRole();


	 /**
	 * Récupération de l'identifiant du critère "Index du role".
	 * @return l'identifiant correspondant.
	 */
	 Integer getIdCritereIndexRole();

	 /**
	 * Récupération de l'identifiant du critère génération.
	 * @return l'identifiant correspondant.
	 */
	 Integer getIdCritereGeneration();

	/**
	 * Récupération de la constante du rôle "Assuré".
	 * 
	 * @return la constante.
	 */
	String getConstanteRoleAssure();

	/**
	 * Récupération de la constante du rôle "Conjoint".
	 * 
	 * @return la constante.
	 */
	String getConstanteRoleConjoint();

	/**
	 * Récupération de la constante du rôle "Enfant".
	 * 
	 * @return la constante.
	 */
	String getConstanteRoleEnfant();
	//
	// /**
	// * Récupération de la constante de la valeur de Règle "Pas de stage".
	// * @return la constante
	// */
	// Integer getIdValeurReglePasDeStage();

	 /**
	 * Récupération de la constante de la valeur de Règle "Stage Smatis".
	 * @return la constante
	 */
	 Integer getIdValeurRegleStageSmatis();
}
