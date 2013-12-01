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
package com.square.adherent.noyau.service.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.DimensionAdherentCriteresRechercheDto;
import com.square.adherent.noyau.dto.IdentifiantEidLibelleDto;
import com.square.adherent.noyau.dto.prestation.EntetePrestationResultatRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationCriteresRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationResultatRechercheDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.PrestationService;

/**
 * Classe contenant les tests unitaires des services de RelevePrestationService.
 * @author nnadeau - SCUB
 */
public class PrestationServiceTest extends DbunitBaseTestCase {

    private static final Long CONTRAT_7149103L = 7149103L;

	private static final Long CONTRAT_7149104L = 7149104L;

	private static final Long CONTRAT_7149136L = 7149136L;

	private static final Long CONTRAT_7149122L = 7149122L;

	private static final Long CONTRAT_7149135L = 7149135L;

	private static final Long CONTRAT_7149134L = 7149134L;

	private static final Long CONTRAT_7148944L = 7148944L;

	private static final Long CONTRAT_7148946L = 7148946L;

	private static final Long CONTRAT_7148953L = 7148953L;

	private static final int YEAR_2009 = 2009;

	private static final Double MONTANT_REMBOURSEMENT_SMATIS = 48.0;

	private PrestationService prestationService;

    private AdherentMappingService adherentMappingService;

    private boolean chargerBean = true;

    /**
     * Method called before each unit testing.
     */
    @Before
    public void setUp() {
        prestationService = (PrestationService) getBeanSpring("prestationService");
        adherentMappingService = (AdherentMappingService) getBeanSpring("adherentMappingService");
    }

    @Override
    protected String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "dataset-releve-prestation.xml";
    }

    /** {@inheritDoc} */
    @Override
    public String getFichierRequetesSql() {
    	// FIXME Solution temporaire pour valider les tests unitaires
    	// Certain Dao utilise des modéles de square
    	if (chargerBean) {
    		chargerBean = false;
    		return "square-object.sql";
    	}
    	return "square-object-vide.sql";
    }
    /** Test de recherche des origines de decompte. */
    @Test
    public void testRechercherOriginesDecompteParCriteres() {

    	// FIXME Validation temporaire, utilisation de requete sql au chargement ( cf test/ressource/square-object.sql)
        DimensionAdherentCriteresRechercheDto criteres = new DimensionAdherentCriteresRechercheDto();
        List<IdentifiantLibelleDto> list = prestationService.rechercherOriginesDecompteParCriteres(criteres);
        assertEquals(Messages.getString("PrestationServiceTest.4"), 8, list.size());
        assertEquals(Messages.getString("PrestationServiceTest.5"), CONTRAT_7149103L, list.get(0).getIdentifiant());
        assertEquals(Messages.getString("PrestationServiceTest.6"), CONTRAT_7149104L, list.get(1).getIdentifiant());
        assertEquals(Messages.getString("PrestationServiceTest.7"), CONTRAT_7149136L, list.get(2).getIdentifiant());

        criteres = new DimensionAdherentCriteresRechercheDto();
        criteres.setId(new Long(CONTRAT_7149122L));
        list = prestationService.rechercherOriginesDecompteParCriteres(criteres);
        assertEquals(Messages.getString("PrestationServiceTest.8"), 1, list.size());
        assertEquals(Messages.getString("PrestationServiceTest.9"), CONTRAT_7149122L, list.get(0).getIdentifiant());

        criteres = new DimensionAdherentCriteresRechercheDto();
        criteres.setLibelle("Tiers payant");
        list = prestationService.rechercherOriginesDecompteParCriteres(criteres);
        assertEquals(Messages.getString("PrestationServiceTest.11"), 2, list.size());
        assertEquals(Messages.getString("PrestationServiceTest.12"), CONTRAT_7149135L, list.get(0).getIdentifiant());
        assertEquals(Messages.getString("PrestationServiceTest.13"), CONTRAT_7149134L, list.get(1).getIdentifiant());
    }

    /** Test de recherche des actes de decompte. */
    @Test
    public void testRechercherActesDecompteParCriteres() {

    	// FIXME Validation temporaire, utilisation de requete sql au chargement ( cf test/ressource/square-object.sql)
        DimensionAdherentCriteresRechercheDto criteres = new DimensionAdherentCriteresRechercheDto();
        List<IdentifiantEidLibelleDto> list = prestationService.rechercherActesDecompteParCriteres(criteres);
        assertEquals(Messages.getString("PrestationServiceTest.14"), 3, list.size());
        assertEquals(Messages.getString("PrestationServiceTest.15"), CONTRAT_7148953L, list.get(0).getIdentifiant());
        assertEquals(Messages.getString("PrestationServiceTest.16"), "AAUX", list.get(0).getIdentifiantExterieur());
        assertEquals(Messages.getString("PrestationServiceTest.18"), CONTRAT_7148944L, list.get(1).getIdentifiant());
        assertEquals(Messages.getString("PrestationServiceTest.19"), CONTRAT_7148946L, list.get(2).getIdentifiant());

        criteres = new DimensionAdherentCriteresRechercheDto();
        criteres.setId(new Long(CONTRAT_7148944L));
        list = prestationService.rechercherActesDecompteParCriteres(criteres);
        assertEquals(Messages.getString("PrestationServiceTest.20"), 1, list.size());
        assertEquals(Messages.getString("PrestationServiceTest.21"), CONTRAT_7148944L, list.get(0).getIdentifiant());

        criteres = new DimensionAdherentCriteresRechercheDto();
        criteres.setLibelle("COMPL");
        list = prestationService.rechercherActesDecompteParCriteres(criteres);
        assertEquals(Messages.getString("PrestationServiceTest.23"), 2, list.size());
        assertEquals(Messages.getString("PrestationServiceTest.24"), CONTRAT_7148944L, list.get(0).getIdentifiant());
        assertEquals(Messages.getString("PrestationServiceTest.25"), CONTRAT_7148946L, list.get(1).getIdentifiant());
    }

    /** Test de recherche des actes de decompte. */
    @Test
    public void testRechercherPrestationParCritreres() {
        int maxResult = 4;
    	//FIXME Validation temporaire, utilisation de requete sql au chargement ( cf test/ressource/square-object.sql)
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(new RemotePagingSort(adherentMappingService.getOrderDecompteByDateReglement(), RemotePagingSort.REMOTE_PAGING_SORT_DESC));
        listeSorts.add(new RemotePagingSort(adherentMappingService.getOrderDecompteById(), RemotePagingSort.REMOTE_PAGING_SORT_DESC));

        // recherche sans criteres
        PrestationCriteresRechercheDto criterias = new PrestationCriteresRechercheDto();
        final RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PrestationCriteresRechercheDto>(criterias, 0, maxResult);
        criteres.setListeSorts(listeSorts);
        RemotePagingResultsDto<PrestationResultatRechercheDto> resultats = prestationService.rechercherPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.26"), maxResult, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.27"), maxResult, resultats.getTotalResults());
        assertEquals(Messages.getString("PrestationServiceTest.28"), 4L, resultats.getListResults().get(0).getId());
        assertEquals(Messages.getString("PrestationServiceTest.29"), 3L, resultats.getListResults().get(1).getId());
        assertEquals(Messages.getString("PrestationServiceTest.30"), 2L, resultats.getListResults().get(2).getId());
        assertEquals(Messages.getString("PrestationServiceTest.31"), 1L, resultats.getListResults().get(3).getId());

        maxResult = 4;
        criteres.setMaxResult(maxResult);

        // recherche par assure
        criterias = new PrestationCriteresRechercheDto();
        criterias.setIdAssure(1L);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.32"), maxResult, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.33"), 4, resultats.getTotalResults());

        // recherche par origine
        criterias = new PrestationCriteresRechercheDto();
        criterias.setIdOrigine(CONTRAT_7149122L);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.34"), 2, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.35"), 2, resultats.getTotalResults());

        // recherche par actes
        criterias = new PrestationCriteresRechercheDto();
        final List<Long> idsActes = new ArrayList<Long>();
        idsActes.add(CONTRAT_7148946L);
        idsActes.add(CONTRAT_7148944L);
        criterias.setIdActes(idsActes);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.36"), 2, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.37"), 2, resultats.getTotalResults());

        // recherche par actes a exclure
        criterias = new PrestationCriteresRechercheDto();
        final List<Long> idsActesAExclure = new ArrayList<Long>();
        idsActesAExclure.add(CONTRAT_7148946L);
        criterias.setIdActesAExclure(idsActesAExclure);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.38"), 3, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.39"), 3, resultats.getTotalResults());

        // recherche par dates
        criterias = new PrestationCriteresRechercheDto();
        final Calendar dateDebutSoins = Calendar.getInstance();
        dateDebutSoins.set(YEAR_2009, 3, 1);
        final Calendar dateFinSoins = Calendar.getInstance();
        dateFinSoins.set(YEAR_2009, 4, 10);
        criterias.setDateDebutSoins(dateDebutSoins);
        criterias.setDateFinSoins(dateFinSoins);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.40"), 2, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.41"), 2, resultats.getTotalResults());
    }

    /** Test de recherche des actes de decompte. */
    @Test
    public void testRechercherEntetesPrestationParCritreres() {
        int maxResult = 4;
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("PrestationServiceTest.42"));

        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(new RemotePagingSort(adherentMappingService.getOrderDecompteByDateReglement(), RemotePagingSort.REMOTE_PAGING_SORT_DESC));
        listeSorts.add(new RemotePagingSort(adherentMappingService.getOrderDecompteByNumero(), RemotePagingSort.REMOTE_PAGING_SORT_DESC));

        // recherche sans criteres
        PrestationCriteresRechercheDto criterias = new PrestationCriteresRechercheDto();
        final RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PrestationCriteresRechercheDto>(criterias, 0, maxResult);
        criteres.setListeSorts(listeSorts);
        // FIXME le test ne peut pas passer car le dao pointe sur une table data_personne_physique non reconnu par le model.
        // FIXME il y a un probleme de conception car le noyau adherent ne devrait pas connaitre le model du noyau square

    	// Validation temporaire, utilisation de requete sql au chargement ( cf test/ressource/square-object.sql)
        RemotePagingResultsDto<EntetePrestationResultatRechercheDto> resultats = prestationService.rechercherEntetesPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.43"), 2, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.44"), 2, resultats.getTotalResults());
        assertEquals(Messages.getString("PrestationServiceTest.45"), "DecompteNo2", resultats.getListResults().get(0).getNumero());
        assertEquals(Messages.getString("PrestationServiceTest.47"), MONTANT_REMBOURSEMENT_SMATIS, resultats.getListResults().get(0).getRemboursementSmatis());
        assertEquals(Messages.getString("PrestationServiceTest.48"), "Manuel", resultats.getListResults().get(0).getOrigine().getLibelle());
        assertEquals(Messages.getString("PrestationServiceTest.50"), "DecompteNo1", resultats.getListResults().get(1).getNumero());

        maxResult = 4;
        criteres.setMaxResult(maxResult);

        // recherche par assure
        criterias = new PrestationCriteresRechercheDto();
        criterias.setIdAssure(1L);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherEntetesPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.52"), 2, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.53"), 2, resultats.getTotalResults());

        // recherche par origine
        criterias = new PrestationCriteresRechercheDto();
        criterias.setIdOrigine(CONTRAT_7149122L);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherEntetesPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.54"), 1, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.55"), 1, resultats.getTotalResults());
        assertEquals(Messages.getString("PrestationServiceTest.56"), "DecompteNo2", resultats.getListResults().get(0).getNumero());
        assertEquals(Messages.getString("PrestationServiceTest.58"), "2009-05-15", sdf.format(resultats.getListResults().get(0).getDateDebutSoins().getTime()));
        assertEquals(Messages.getString("PrestationServiceTest.60"), "2009-05-30", sdf.format(resultats.getListResults().get(0).getDateFinSoins().getTime()));

        // recherche par actes
        criterias = new PrestationCriteresRechercheDto();
        final List<Long> idsActes = new ArrayList<Long>();
        idsActes.add(CONTRAT_7148946L);
        criterias.setIdActes(idsActes);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherEntetesPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.62"), 1, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.63"), 1, resultats.getTotalResults());
        assertEquals(Messages.getString("PrestationServiceTest.64"), "DecompteNo1", resultats.getListResults().get(0).getNumero());

        // recherche par dates
        criterias = new PrestationCriteresRechercheDto();
        final Calendar dateDebutSoins = Calendar.getInstance();
        dateDebutSoins.set(YEAR_2009, 3, 1);
        final Calendar dateFinSoins = Calendar.getInstance();
        dateFinSoins.set(YEAR_2009, 4, 10);
        criterias.setDateDebutSoins(dateDebutSoins);
        criterias.setDateFinSoins(dateFinSoins);
        criteres.setCriterias(criterias);
        resultats = prestationService.rechercherEntetesPrestationParCritreres(criteres);
        // verification des resultats
        assertEquals(Messages.getString("PrestationServiceTest.66"), 1, resultats.getListResults().size());
        assertEquals(Messages.getString("PrestationServiceTest.67"), 1, resultats.getTotalResults());
        assertEquals(Messages.getString("PrestationServiceTest.68"), "DecompteNo1", resultats.getListResults().get(0).getNumero());
    }
}
