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

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.banque.BanqueDto;
import com.square.adherent.noyau.dto.banque.CritereRechercheBanqueDto;
import com.square.adherent.noyau.service.interfaces.BanqueService;

/**
 * Tests unitaires des services sur les banques.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class BanqueServiceTest extends DbunitBaseTestCase {

    private BanqueService banqueService;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        banqueService = (BanqueService) getBeanSpring("banqueService");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "dataset-banque.xml";
    }

    /** Test du service getListeBanquesByCriteres. */
    @Test
    public void testGetListeBanquesByCriteres() {
        final CritereRechercheBanqueDto criteres = new CritereRechercheBanqueDto();
        List<BanqueDto> listeBanques = banqueService.getListeBanquesByCriteres(criteres);
        assertEquals(Messages.getString("BanqueServiceTest.3"), 3, listeBanques.size());
        assertEquals(Messages.getString("BanqueServiceTest.4"), "BANQUE DE FRANCE", listeBanques.get(0).getLibelleBanque());
        assertEquals(Messages.getString("BanqueServiceTest.6"), "BDF ANGOULEME", listeBanques.get(0).getLibelleAgence());
        assertEquals(Messages.getString("BanqueServiceTest.8"), "BANQUE TARNEAUD", listeBanques.get(1).getLibelleBanque());
        assertEquals(Messages.getString("BanqueServiceTest.10"), "TARNEAUD ANGOULEME", listeBanques.get(1).getLibelleAgence());
        assertEquals(Messages.getString("BanqueServiceTest.12"), "CREDIT LYONNAIS", listeBanques.get(2).getLibelleBanque());
        assertEquals(Messages.getString("BanqueServiceTest.14"), "CL ANGOULEME", listeBanques.get(2).getLibelleAgence());

        criteres.setCodeBanque("30001");
        criteres.setCodeGuichet("00129");
        listeBanques = banqueService.getListeBanquesByCriteres(criteres);
        assertEquals(Messages.getString("BanqueServiceTest.18"), 1, listeBanques.size());
        assertEquals(Messages.getString("BanqueServiceTest.19"), "BANQUE DE FRANCE", listeBanques.get(0).getLibelleBanque());
        assertEquals(Messages.getString("BanqueServiceTest.21"), "BDF ANGOULEME", listeBanques.get(0).getLibelleAgence());
    }
}
