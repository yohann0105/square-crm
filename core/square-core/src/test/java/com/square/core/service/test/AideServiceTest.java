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
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.dto.AideDto;
import com.square.core.service.interfaces.AideService;
import com.square.core.util.AideKeyUtil;

/**
 * Tests unitaires pour les services d'aide.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AideServiceTest extends DbunitBaseTestCase {

    private AideService aideService;

    private MessageSourceUtil messageSourceUtil;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        aideService = (AideService) getBeanSpring("aideService");
        createSecureContext("user", "user");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "datasetAide.xml";
    }

    /** Test de la recherche d'une aide par son identifiant. */
    @Test
    public void testRechercherAide() {
        // Identifiant null
        Long id = null;
        try {
            aideService.rechercherAide(id);
            fail(Messages.getString("AideServiceTest.6"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("AideServiceTest.7"), messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SEARCH_AIDE_ID_REQUIRED), e.getMessage());
        }

        // Identifiant inexistant
        id = 10L;
        AideDto aide = aideService.rechercherAide(id);
        assertNull(Messages.getString("AideServiceTest.8"), aide);

        // Identifiant existant
        id = 1L;
        aide = aideService.rechercherAide(id);
        assertEquals(Messages.getString("AideServiceTest.9"), id, aide.getId());
        assertEquals(Messages.getString("AideServiceTest.10"), 1L, aide.getIdComposant());
        assertEquals(Messages.getString("AideServiceTest.11"), "champ de saisie pour nom", aide.getText());
    }

    /** Test de la recherche d'une aide par son identifiant de composant. */
    @Test
    public void testRechercherAideParidComposant() {
        // Identifiant null
        Long idComposant = null;
        try {
            aideService.rechercherAideParidComposant(idComposant);
            fail(Messages.getString("AideServiceTest.13"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("AideServiceTest.14"), messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SEARCH_AIDE_IDCOMPOSANT_REQUIRED),
                e.getMessage());
        }

        // Identifiant inexistant
        idComposant = 10L;
        AideDto aide = aideService.rechercherAideParidComposant(idComposant);
        assertNull(Messages.getString("AideServiceTest.15"), aide);

        // Identifiant existant
        idComposant = 2L;
        aide = aideService.rechercherAideParidComposant(idComposant);
        assertEquals(Messages.getString("AideServiceTest.16"), 2L, aide.getId());
        assertEquals(Messages.getString("AideServiceTest.17"), idComposant, aide.getIdComposant());
        assertEquals(Messages.getString("AideServiceTest.18"), "information à propos de champ date", aide.getText());
    }

    /** Test de la création de la modification d'une aide. */
    @Test
    public void testCreerOuModifierAide() {
        // Dto null
        AideDto aide = null;
        try {
            aideService.creerOuModifierAide(aide);
            fail(Messages.getString("AideServiceTest.20"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("AideServiceTest.21"), messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SAVE_AIDE_DTO_REQUIRED),
                e.getMessage());
        }

        // Dto sans identifiant de composant
        aide = new AideDto();
        try {
            aideService.creerOuModifierAide(aide);
            fail(Messages.getString("AideServiceTest.22"));
        }
        catch (BusinessException e) {
            assertEquals(Messages.getString("AideServiceTest.23"), messageSourceUtil.get(AideKeyUtil.MESSAGE_ERROR_SEARCH_AIDE_ID_REQUIRED),
                e.getMessage());
        }

        // Modification
        aide.setId(1L);
        aide.setIdComposant(1L);
        final String texteAide = "Texte aide modifiée";
        aide.setText(texteAide);
        final AideDto aideModifiee = aideService.creerOuModifierAide(aide);
        assertEquals(Messages.getString("AideServiceTest.25"), 1L, aideModifiee.getId());
        assertEquals(Messages.getString("AideServiceTest.26"), 1L, aideModifiee.getIdComposant());
        assertEquals(Messages.getString("AideServiceTest.27"), texteAide, aideModifiee.getText());

        // Création
        aide = new AideDto();
        aide.setIdComposant(5L);
        final String texteAideCreee = "Texte aide créée";
        aide.setText(texteAideCreee);
        final AideDto aideCreee = aideService.creerOuModifierAide(aide);
        assertNotNull(Messages.getString("AideServiceTest.29"), aideCreee.getId());
        assertEquals(Messages.getString("AideServiceTest.30"), 5L, aideCreee.getIdComposant());
        assertEquals(Messages.getString("AideServiceTest.31"), texteAideCreee, aideCreee.getText());
    }

    /** Test de la recherche d'identifiants de composants d'aide. */
    @Test
    public void testRechercherIdsComposantsAides() {
        final List<Long> listeIdsComposantsAide = new ArrayList<Long>();
        List<Long> listeResultats = aideService.rechercherIdsComposantsAides(listeIdsComposantsAide);
        assertEquals(Messages.getString("AideServiceTest.32"), 3, listeResultats.size());
        boolean aide1Trouvee = false;
        boolean aide2Trouvee = false;
        boolean aide3Trouvee = false;
        for (Long idComposantAide : listeResultats) {
            if (idComposantAide.equals(1L)) {
                aide1Trouvee = true;
            }
            else if (idComposantAide.equals(2L)) {
                aide2Trouvee = true;
            }
            else if (idComposantAide.equals(3L)) {
                aide3Trouvee = true;
            }
        }
        assertTrue(Messages.getString("AideServiceTest.33"), aide1Trouvee && aide2Trouvee && aide3Trouvee);

        listeIdsComposantsAide.add(1L);
        listeResultats = aideService.rechercherIdsComposantsAides(listeIdsComposantsAide);
        assertEquals(Messages.getString("AideServiceTest.34"), 1, listeResultats.size());

        listeIdsComposantsAide.add(3L);
        listeResultats = aideService.rechercherIdsComposantsAides(listeIdsComposantsAide);
        assertEquals(Messages.getString("AideServiceTest.35"), 2, listeResultats.size());
        aide1Trouvee = false;
        aide2Trouvee = false;
        aide3Trouvee = false;
        for (Long idComposantAide : listeResultats) {
            if (idComposantAide.equals(1L)) {
                aide1Trouvee = true;
            }
            else if (idComposantAide.equals(2L)) {
                aide2Trouvee = true;
            }
            else if (idComposantAide.equals(3L)) {
                aide3Trouvee = true;
            }
        }
        assertTrue(Messages.getString("AideServiceTest.36"), aide1Trouvee && !aide2Trouvee && aide3Trouvee);
    }
}
