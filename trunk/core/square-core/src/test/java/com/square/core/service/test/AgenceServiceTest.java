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
package com.square.core.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.dto.AgenceDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.service.interfaces.AgenceService;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.util.AgenceKeyUtil;

/**
 * Tests unitaires sur les services des agences.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AgenceServiceTest extends DbunitBaseTestCase {

   private static final String UN = "1";

   /** Service des agences. */
   private AgenceService agenceService;

   /** Service des dimensions. */
   private DimensionService dimensionService;

   /** Gestion des messages. */
   private MessageSourceUtil messageSourceUtil;

   /**
    * Méthode appellée avant chaque test unitaire.
    */
   @Before
   public void setUp() {
       messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
       agenceService = (AgenceService) getBeanSpring("agenceService");
       dimensionService = (DimensionService) getBeanSpring("dimensionService");
       createSecureContext("user", "user");
   }

   /** {@inheritDoc} */
   public String[] getFichierContextSpringSup() {
       return new String[] {"squareMappingContext.xml"};
   }

   @Override
   protected String getXmlDateSet() {
       return "datasetAgence.xml";
   }

   /** Test de la création d'une agence. */
   @Test
   public void testCreerAgence() {
       final AgenceDto agenceDto = new AgenceDto();

       // Agence sans libellé
       try {
           agenceService.creerAgence(agenceDto);
           fail(Messages.getString("AgenceServiceTest.8"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.9"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_LIBELLE_OBLIGATOIRE), e.getMessage());
       }

       // Agence sans région commerciale
       final String libelleAgence = "AgenceTest";
       agenceDto.setLibelle(libelleAgence);
       try {
           agenceService.creerAgence(agenceDto);
           fail(Messages.getString("AgenceServiceTest.11"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.12"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_REG_COM_OBLIGATOIRE), e.getMessage());
       }

       // Agence avec région commerciale inexistante
       final Long idRegComInexistante = 100L;
       final IdentifiantEidLibelleDto regComInexistante = new IdentifiantEidLibelleDto();
       regComInexistante.setIdentifiant(idRegComInexistante);
       agenceDto.setRegion(regComInexistante);
       try {
           agenceService.creerAgence(agenceDto);
           fail(Messages.getString("AgenceServiceTest.13"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.14"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_REG_COM_INEXISTANTE), e.getMessage());
       }

       // Agence correcte
       final Long idRegComExistante = 1L;
       final IdentifiantEidLibelleDto regComExistante = new IdentifiantEidLibelleDto();
       regComExistante.setIdentifiant(idRegComExistante);
       agenceDto.setRegion(regComExistante);
       final String idExt = "test1";
       agenceDto.setIdExt(idExt);
       final AgenceDto agenceCreee = agenceService.creerAgence(agenceDto);
       assertNotNull(Messages.getString("AgenceServiceTest.16"), agenceCreee.getId());
       assertEquals(Messages.getString("AgenceServiceTest.17"), libelleAgence, agenceCreee.getLibelle());
       assertEquals(Messages.getString("AgenceServiceTest.18"), 1L, agenceCreee.getRegion().getIdentifiant());
       assertEquals(Messages.getString("AgenceServiceTest.19"), idExt, agenceCreee.getIdExt());
   }

   /** Test de la modification d'une agence. */
   @Test
   public void testModifierAgence() {
       // Modification d'une agence inexistante
       final AgenceDto agenceDto = new AgenceDto();
       final Long idAgenceInexistante = 100L;
       agenceDto.setId(idAgenceInexistante);
       try {
           agenceService.modifierAgence(agenceDto);
           fail(Messages.getString("AgenceServiceTest.20"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.21"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_INEXISTANTE), e.getMessage());
       }

       // Modification d'une agence supprimée
       final Long idAgenceSupprimee = 2L;
       agenceDto.setId(idAgenceSupprimee);
       try {
           agenceService.modifierAgence(agenceDto);
           fail(Messages.getString("AgenceServiceTest.22"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.23"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_UPDATE_AGENCE_SUPPRIMEE), e.getMessage());
       }

       // Modification d'une agence sans renseigner le libellé
       final AgenceDto agenceModifDto = agenceService.rechercherAgenceParEid(UN);
       agenceModifDto.setLibelle("");
       try {
           agenceService.modifierAgence(agenceModifDto);
           fail(Messages.getString("AgenceServiceTest.25"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.26"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_LIBELLE_OBLIGATOIRE), e.getMessage());
       }

       // Modification d'une agence sans renseigner la région commerciale
       final String libelleAgence = "Agence modifiée";
       agenceModifDto.setLibelle(libelleAgence);
       agenceModifDto.setRegion(null);
       try {
           agenceService.modifierAgence(agenceModifDto);
           fail(Messages.getString("AgenceServiceTest.28"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.29"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_REG_COM_OBLIGATOIRE), e.getMessage());
       }

       // Modification d'une agence en renseignant une région commerciale inexistante
       final IdentifiantEidLibelleDto regionDto = new IdentifiantEidLibelleDto();
       final Long idRegionInexistant = 100L;
       regionDto.setIdentifiant(idRegionInexistant);
       agenceModifDto.setRegion(regionDto);
       try {
           agenceService.modifierAgence(agenceModifDto);
           fail(Messages.getString("AgenceServiceTest.30"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.31"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_REG_COM_INEXISTANTE), e.getMessage());
       }

       // Modification d'une agence correcte
       regionDto.setLibelle("Secteur 2");
       final Long idRegionExistant = 2L;
       regionDto.setIdentifiant(idRegionExistant);
       agenceModifDto.setRegion(regionDto);
       final AgenceDto agenceModifiee = agenceService.modifierAgence(agenceModifDto);
       assertEquals(Messages.getString("AgenceServiceTest.33"), libelleAgence, agenceModifiee.getLibelle());
       assertEquals(Messages.getString("AgenceServiceTest.34"), 2L,
           agenceModifiee.getRegion().getIdentifiant());
   }

   /** Test de la recherche d'une agence par son identifiant externe. */
   @Test
   public void testRechercherAgenceParEid() {
       final String eidAgence = UN;
       final AgenceDto agence = agenceService.rechercherAgenceParEid(eidAgence);
       assertEquals(Messages.getString("AgenceServiceTest.35"), 1L, agence.getId());
       assertEquals(Messages.getString("AgenceServiceTest.36"), eidAgence, agence.getIdExt());
       assertEquals(Messages.getString("AgenceServiceTest.37"), "Agence 1", agence.getLibelle());
       assertEquals(Messages.getString("AgenceServiceTest.39"), 1L, agence.getRegion().getIdentifiant());
   }

   /** Test de la suppression (logique) d'une agence. */
   @Test
   public void testSupprimerAgence() {
       // Suppression d'une agence inexistante
       final String eidAgenceInexistante = "100";
       try {
           agenceService.supprimerAgence(eidAgenceInexistante);
           fail(Messages.getString("AgenceServiceTest.41"));
       }
       catch (BusinessException e) {
           assertEquals(Messages.getString("AgenceServiceTest.42"),
               messageSourceUtil.get(AgenceKeyUtil.MESSAGE_ERREUR_AGENCE_INEXISTANTE), e.getMessage());
       }

       // Suppression d'une agence existante
       final String eidAgenceExistante = UN;
       agenceService.supprimerAgence(eidAgenceExistante);

       // Vérification de la suppression
       final DimensionCriteresRechercheDto criteres = new DimensionCriteresRechercheDto();
       criteres.setIdentifiantExterieur(eidAgenceExistante);
       criteres.setVisible(true);
       final List<IdentifiantLibelleDto> listeAgences = dimensionService.rechercherAgenceParCriteres(criteres);
       assertEquals(Messages.getString("AgenceServiceTest.43"), 0, listeAgences.size());
   }

   /**
    * Test du service rechercherRegionCommercialeParEid.
    */
   @Test
   public void testRechercherRegionCommercialeParEid() {
       // Région commerciale inexistante
       final String eidRegionInexistante = "100";
       assertNull(Messages.getString("AgenceServiceTest.45"), agenceService.rechercherRegionCommercialeParEid(eidRegionInexistante));

       // Région commerciale inexistante
       final String eidRegionExistante = UN;
       final IdentifiantEidLibelleDto regionDto = agenceService.rechercherRegionCommercialeParEid(eidRegionExistante);
       assertNotNull(Messages.getString("AgenceServiceTest.46"), regionDto);
       assertEquals(Messages.getString("AgenceServiceTest.47"), 1L, regionDto.getIdentifiant());
       assertEquals(Messages.getString("AgenceServiceTest.48"), "Secteur 1", regionDto.getLibelle());
   }

   /**
    * Test du service rechercherAgencesParIds.
    */
   @Test
   public void testRechercherAgencesParIds() {
       final List<Long> listeIds = new ArrayList<Long>();
       List<AgenceDto> listeAgences = agenceService.rechercherAgencesParIds(listeIds);
       assertNull(Messages.getString("AgenceServiceTest.50"), listeAgences);

       listeIds.add(1L);
       listeIds.add(2L);
       listeAgences = agenceService.rechercherAgencesParIds(listeIds);
       assertEquals(Messages.getString("AgenceServiceTest.51"), 2, listeAgences.size());
       boolean isAgence1Correcte = false;
       boolean isAgence2Correcte = false;
       for (AgenceDto agence : listeAgences) {
           if (agence.getId().equals(1L)) {
               assertEquals(Messages.getString("AgenceServiceTest.52"), UN, agence.getIdExt());
               assertEquals(Messages.getString("AgenceServiceTest.53"), "Agence 1", agence.getLibelle());
               isAgence1Correcte = true;

           }
           else if (agence.getId().equals(2L)) {
               assertEquals(Messages.getString("AgenceServiceTest.55"), "2", agence.getIdExt());
               assertEquals(Messages.getString("AgenceServiceTest.57"), "Agence 2", agence.getLibelle());
               isAgence2Correcte = true;
           }
           else {
               fail(Messages.getString("AgenceServiceTest.59"));
           }
       }
       assertTrue(Messages.getString("AgenceServiceTest.60"), isAgence1Correcte && isAgence2Correcte);
   }
}
