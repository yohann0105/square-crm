package com.square.document.core.service.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.document.core.dao.interfaces.TagDao;
import com.square.document.core.dto.TypeDocumentDto;
import com.square.document.core.model.Tag;
import com.square.document.core.service.interfaces.DimensionService;

/**
 * Classe de test pour le service de dimension.
 * @author Julie Jageneau
 */
public class DimensionServiceTest extends DbunitBaseTestCase {

    /**
     * Services.
     */
    private DimensionService dimensionService;

    private TagDao tagDao;

    /**
     * Gestion des messages.
     */
    @SuppressWarnings("unused")
    private MessageSourceUtil messageSourceUtil;

    /**
     * Méthode appelée avant chaque test unitaire.
     * @throws Exception l'exception
     */
    @Before
    public void setUp() throws Exception {
        dimensionService = (DimensionService) getBeanSpring("dimensionService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        tagDao = (TagDao) getBeanSpring("tagDao");
    }

    @Override
    protected String[] getFichierContextSpringSup() {
        return new String[] {"gedMappingContext.xml"};
    }

    @Override
    public void tearDownBaseTestCase() throws Exception {
        final List<Tag> tagList = tagDao.getListeTag();

        for (Tag tag : tagList) {
            tag.setListeTags(null);
            tagDao.updateTag(tag);
        }
        super.tearDownBaseTestCase();
    }

    /**
     * Test pour la récupération des types et sous types.
     */
    @Test
    public void testGetListeType() {
        final List<TypeDocumentDto> listeTypes = dimensionService.getListeTypesDocuments("user1");

        assertEquals("1) Le nombre de types attendu est : ", 2, listeTypes.size());
        assertEquals("1.1) La récupération du type1 ne fonctionne pas", listeTypes.get(0).getNom(), "categorie1");
        assertEquals("1.2) La récupération du type2 ne fonctionne pas", listeTypes.get(1).getNom(), "categorie2");

        assertEquals("2) Le nombre de sous types attendu est : ", listeTypes.get(0).getListeTypesDocuments().size(), 2);
        assertEquals("2.1) La récupération du sous-type1 ne fonctionne pas", listeTypes.get(0).getListeTypesDocuments().get(0).getNom(), "cat1_rubrique1");
        assertEquals("2.2) La récupération du sous-type2 ne fonctionne pas", listeTypes.get(0).getListeTypesDocuments().get(1).getNom(), "cat1_rubrique2");

        assertEquals("3) Le nombre de sous types attendu est : ", listeTypes.get(1).getListeTypesDocuments().size(), 2);
        assertEquals("3.1) La récupération du sous-type1 ne fonctionne pas", listeTypes.get(1).getListeTypesDocuments().get(0).getNom(), "cat2_rubrique1");
        assertEquals("3.2) La récupération du sous-type2 ne fonctionne pas", listeTypes.get(1).getListeTypesDocuments().get(1).getNom(), "cat2_rubrique2");
    }
}
