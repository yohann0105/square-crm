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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.magazine.MagazineDto;
import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.adherent.noyau.service.interfaces.MagazineService;

/**
 * Tests unitaires des services liés aux adhérents.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class MagazineServiceTest extends DbunitBaseTestCase {

    private MagazineService magazineService;

    private AdherentService adherentService;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        magazineService = (MagazineService) getBeanSpring("magazineService");
        adherentService = (AdherentService) getBeanSpring("adherentService");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "dataset-magazine.xml";
    }

    /** Test de la récupération d'un magazine. */
    @Test
    public void testGetMagazine() {
        final Long idMagazine1 = new Long(1);
        final Long idMagazine2 = new Long(15);

        MagazineDto magazineDto = magazineService.getMagazine(idMagazine1);
        assertNotNull(Messages.getString("MagazineServiceTest.4"), magazineDto);

        magazineDto = magazineService.getMagazine(idMagazine2);
        assertNull(Messages.getString("MagazineServiceTest.5"), magazineDto);
    }

    /** Test de la marquage d'un magazine comme envoyé. */
    @Test
    public void testMarquerMagazineCommeEnvoye() {
        final Long idMagazine = new Long(2);

        // verification
        MagazineDto magazineDto = magazineService.getMagazine(idMagazine);
        assertFalse(Messages.getString("MagazineServiceTest.6"), magazineDto.getEnvoye());

        magazineService.marquerMagazineCommeEnvoye(idMagazine);

        // verification
        magazineDto = magazineService.getMagazine(idMagazine);
        assertTrue(Messages.getString("MagazineServiceTest.7"), magazineDto.getEnvoye());
    }

    /** Test de la création d'un magazine. */
    @Test
    public void testCreateMagazine() {
        final Long idMagazine = new Long(15);
        magazineService.createMagazine(idMagazine);

        // verification
        final MagazineDto magazineDto = magazineService.getMagazine(idMagazine);
        assertNotNull(Messages.getString("MagazineServiceTest.8"), magazineDto);
    }

    /** Test d'ajout de magazine aux adherents. */
    @Test
    public void testAjouterMagazineAdherents() {
        final Long idMagazine = new Long(2);
        final List<Long> listeIds = new ArrayList<Long>();
        listeIds.add(3L);

        magazineService.ajouterMagazineAdherents(idMagazine, listeIds);

        // verification
        final List<Long> listeAdherentsRecu = adherentService.getListeAdherentsByIdMagazineEnvoye(idMagazine);
        assertEquals(Messages.getString("MagazineServiceTest.9"), 3, listeAdherentsRecu.size());
        boolean existeAdh1 = false;
        boolean existeAdh2 = false;
        boolean existeAdh3 = false;
        for (Long idAdherent : listeAdherentsRecu) {
            if (idAdherent.equals(1L)) {
                existeAdh1 = true;
            } else if (idAdherent.equals(2L)) {
                existeAdh2 = true;
            } else if (idAdherent.equals(3L)) {
                existeAdh3 = true;
            }
        }
        assertTrue(Messages.getString("MagazineServiceTest.10"), existeAdh1);
        assertTrue(Messages.getString("MagazineServiceTest.11"), existeAdh2);
        assertTrue(Messages.getString("MagazineServiceTest.12"), existeAdh3);
    }

}
