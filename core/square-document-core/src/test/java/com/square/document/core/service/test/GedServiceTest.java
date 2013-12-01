package com.square.document.core.service.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.document.core.dao.interfaces.TagDao;
import com.square.document.core.dto.CodeLibelleDto;
import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.dto.DocumentDto;
import com.square.document.core.model.Tag;
import com.square.document.core.service.interfaces.GedMappingService;
import com.square.document.core.service.interfaces.GedService;
import com.square.document.core.util.GedKeyUtil;

/**
 * Classe des tests unitaires pour le service GedServiceImpl.
 * @author Julie Jageneau
 */
public class GedServiceTest extends DbunitBaseTestCase {

    /**
     * Services liés aux déclaration.
     */
    private GedService gedService;

    private GedMappingService gedMappingService;

    private TagDao tagDao;

    private String nomDocument1;

    private String nomDocument2;

    private String nomDocument3;

    private String leNomDuDocument;

    /**
     * Gestion des messages.
     */
    private MessageSourceUtil messageSourceUtil;

    @Override
    protected String[] getFichierContextSpringSup() {
        return new String[] {"gedMappingContext.xml"};
    }

    /**
     * Méthode appelée avant chaque test unitaire.
     * @throws Exception
     */
    @Before
    public void setUp() {
        gedService = (GedService) getBeanSpring("gedService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        gedMappingService = (GedMappingService) getBeanSpring("gedMappingService");
        tagDao = (TagDao) getBeanSpring("tagDao");
        this.nomDocument1 = "123456-document1";
        this.nomDocument2 = "234565-document2";
        this.nomDocument3 = "112233-document3";
        this.leNomDuDocument = "Nom document :";
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
     * Test de l'ajout d'un document.
     */
    @Test
    public void testAjoutDocument() {
        final DocumentDto documentDto = new DocumentDto();

        // Sans numéro d'utilisateur
        try {
            documentDto.setNumeroClient(null);
            documentDto.setNom("Document1");
            gedService.ajouterDocument(documentDto, "test1");
            fail("Une erreur aurait du survenir car il n'y a pas d'utilisateur");
        } catch (Exception e) {
            assertEquals("Le message d'erreur est incorrect", messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED), e.getMessage());
        }

        // Sans nom de fichier
        try {
            documentDto.setNumeroClient("Client2");
            documentDto.setNom(null);
            gedService.ajouterDocument(documentDto, "test1");
            fail("Une erreur aurait du survenir car il n'y a pas de nom du fichier");
        } catch (Exception e) {
            assertEquals("Le message d'erreur est incorrect", messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NAME_REQUIRED), e.getMessage());
        }

        documentDto.setNom("Document1");
        final String contenu = "test";
        documentDto.setContenu(contenu.getBytes());
        documentDto.setNumeroClient("Client1");

        final CodeLibelleDto type1 = new CodeLibelleDto("3", "cat1_rubrique1");
        final List<CodeLibelleDto> list = new ArrayList<CodeLibelleDto>();
        list.add(type1);

        documentDto.setTypes(list);

        gedService.ajouterDocument(documentDto, "");
    }

    /**
     * Test de la recherche d'un document par critère.
     */
    @Test
    public void testGetListeDocumentsByCriteres() {
        final CriteresRechercheDocumentDto criteresRechercheDocumentDto1 = new CriteresRechercheDocumentDto();
        criteresRechercheDocumentDto1.setNumeroClient("1234");

        final List<DocumentDto> liste1 = gedService.getListeDocumentsByCriteres(criteresRechercheDocumentDto1, "");

        assertEquals("Taille :", liste1.size(), 3);
        assertEquals("Identifiant document :", liste1.get(0).getIdentifiant(), "1");
        assertEquals(this.leNomDuDocument, liste1.get(0).getNom(), this.nomDocument1);
        assertEquals(this.leNomDuDocument, liste1.get(1).getNom(), this.nomDocument2);

        assertEquals("Numero client :", liste1.get(0).getNumeroClient(), "1234");

        assertEquals("Taille tags :", liste1.get(0).getTags().size(), 1);
        assertEquals("Tags 1:", liste1.get(0).getTags().get(0), "categorie1");
        assertEquals("Tags 2:", liste1.get(1).getTags().get(0), "categorie2");

        final CriteresRechercheDocumentDto criteresRechercheDocumentDto2 = new CriteresRechercheDocumentDto();
        criteresRechercheDocumentDto2.setNumeroClient("4444");

        final List<DocumentDto> liste2 = gedService.getListeDocumentsByCriteres(criteresRechercheDocumentDto2, "");

        assertEquals("Taille :", liste2.size(), 1);
        assertEquals("Identifiant document :", liste2.get(0).getIdentifiant(), "3");
        assertEquals(this.leNomDuDocument, liste2.get(0).getNom(), this.nomDocument3);
        assertEquals("Numero client :", liste2.get(0).getNumeroClient(), "4444");

        assertEquals("Taille tags :", liste2.get(0).getTags().size(), 1);
        assertEquals("Tags 1:", liste2.get(0).getTags().get(0), "cat1_rubrique1");

        final List<String> idsDocuments = new ArrayList<String>();
        idsDocuments.add("1");
        idsDocuments.add("2");

        final CriteresRechercheDocumentDto criteresRechercheDocumentDto3 = new CriteresRechercheDocumentDto();
        criteresRechercheDocumentDto3.setIds(idsDocuments);

        final List<DocumentDto> liste3 = gedService.getListeDocumentsByCriteres(criteresRechercheDocumentDto3, "");

        assertEquals("Taille :", liste3.size(), 2);
        assertEquals(this.leNomDuDocument, liste3.get(0).getNom(), this.nomDocument1);
        assertEquals(this.leNomDuDocument, liste3.get(1).getNom(), this.nomDocument2);

        assertEquals("Taille tags :", liste1.get(0).getTags().size(), 1);
        assertEquals("Taille tags :", liste1.get(1).getTags().size(), 1);
        assertEquals("Tags 1:", liste1.get(0).getTags().get(0), "categorie1");
        assertEquals("Tags 2:", liste1.get(1).getTags().get(0), "categorie2");

    }

    /**
     * Test de la recherche d'un document par critère.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetDocumentByCriteres() throws UnsupportedEncodingException {
        final CriteresRechercheDocumentDto criteresRechercheDocumentDto1 = new CriteresRechercheDocumentDto();
        // Sans critere :
        try {
            gedService.getDocumentByCriteres(criteresRechercheDocumentDto1, "");
            fail("Une erreur aurait du survenir car il n'y a pas d'utilisateur et pas d'identifiant");
        } catch (Exception e) {
            assertEquals("Critere : ", messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED), e.getMessage());
        }

        // Sans ids :
        try {
            criteresRechercheDocumentDto1.setNumeroClient("1234");
            criteresRechercheDocumentDto1.setIds(null);
            gedService.getDocumentByCriteres(criteresRechercheDocumentDto1, "");
            fail("Une erreur aurait du survenir car il n'y a pas d'utilisateur");
        } catch (Exception e) {
            assertEquals("Liste ids : ", messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED), e.getMessage());
        }

        // Sans numero d'utilisateur :
        try {
            final List<String> listIds = new ArrayList<String>();
            listIds.add("4");
            criteresRechercheDocumentDto1.setIds(listIds);
            criteresRechercheDocumentDto1.setNumeroClient(null);
            gedService.getDocumentByCriteres(criteresRechercheDocumentDto1, "");

            fail("Une erreur aurait du survenir car il n'y a pas d'identifiant");
        } catch (Exception e) {
            assertEquals("Numero utilisateur : ", messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED), e.getMessage());
        }

        // Cas normal
        criteresRechercheDocumentDto1.setNumeroClient("1234");
        final List<String> listIds = new ArrayList<String>();
        listIds.add("4");
        criteresRechercheDocumentDto1.setIds(listIds);
        gedService.getDocumentByCriteres(criteresRechercheDocumentDto1, "");

        final DocumentDto docDto = gedService.getDocumentByCriteres(criteresRechercheDocumentDto1, "");

        final String contenu = "test";
        assertEquals("Nom : ", docDto.getNom(), "test");
        if (docDto.getContenu() != null) {
            final String s = new String(docDto.getContenu(), "UTF8");
            assertEquals("Contenu :", s.trim(), contenu);
        }

        assertEquals("Proprietaire : ", docDto.getNumeroClient(), criteresRechercheDocumentDto1.getNumeroClient());

    }

    /**
     * Test du transfert des documents de personnes.
     */
    @Test
    public void testTransfererDocumentPersonne() {

        // Création des fichiers pour le test -----
        final String[] tabTagDoc1 = {"RIB", "autre", "Facture"};
        final String[] tabTagDoc2 = {"Comptabilité", "Financement"};
        final String[] tabTagDoc3 = {"Document"};

        final String idPers1 = "1234";
        final String idPers2 = "4444";
        final String backSlash = "/";

        final DocumentDto documentDto1 = new DocumentDto();
        documentDto1.setNom(this.nomDocument1);
        documentDto1.setNumeroClient(idPers1);
        documentDto1.setTags(Arrays.asList(tabTagDoc1));

        new File(gedMappingService.getCheminRepertoire() + backSlash + idPers1).mkdir();

        try {
            new File(gedMappingService.getCheminRepertoire() + backSlash + idPers1 + backSlash + this.nomDocument1).createNewFile();
        } catch (IOException e) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.ERROR_CREATE_FILE));
        }

        final DocumentDto documentDto2 = new DocumentDto();
        documentDto2.setNom(this.nomDocument2);
        documentDto2.setNumeroClient(idPers1);
        documentDto2.setTags(Arrays.asList(tabTagDoc2));
        try {
            new File(gedMappingService.getCheminRepertoire() + backSlash + idPers1 + backSlash + this.nomDocument2).createNewFile();
        } catch (IOException e) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.ERROR_CREATE_FILE));
        }

        final DocumentDto documentDto3 = new DocumentDto();
        documentDto3.setNom(this.nomDocument3);
        documentDto3.setNumeroClient(idPers2);
        documentDto3.setTags(Arrays.asList(tabTagDoc3));

        new File(gedMappingService.getCheminRepertoire() + backSlash + idPers2).mkdir();
        try {
            new File(gedMappingService.getCheminRepertoire() + backSlash + idPers2 + backSlash + this.nomDocument3).createNewFile();
        } catch (IOException e) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.ERROR_CREATE_FILE));
        }
        // Fin de création des fichiers pour le test
        // On à alors (D=dossier F=fichier) :
        // 1234(D)-> document1(F) et document2(F)
        // 4444(D)-> document3(F)

        // 4444 à déjà un repertoire avec un fichier
        gedService.transfererDocumentPersonne(idPers1, idPers2, "");
        // On obtient : 4444(D)-> document1(F), document2(F) et document3(F)

        final File file0 = new File(gedMappingService.getCheminRepertoire() + backSlash + idPers2 + backSlash + this.nomDocument1);
        final File file1 = new File(gedMappingService.getCheminRepertoire() + backSlash + idPers2 + backSlash + this.nomDocument2);
        final File file2 = new File(gedMappingService.getCheminRepertoire() + backSlash + idPers2 + backSlash + this.nomDocument3);

        final File file3 = new File(gedMappingService.getCheminRepertoire() + backSlash + idPers1 + backSlash + this.nomDocument1);

        assertTrue("Le fichier existe ", file0.exists());
        assertTrue("Le fichier existe ", file1.exists());
        assertTrue("Le fichier existe ", file2.exists());

        assertTrue("Le fichier n'existe plus ", !file3.exists());

        // 5555 n'a pas encore de repertoire
        gedService.transfererDocumentPersonne(idPers2, "5555", "");
        // On obtient : 5555(D)-> document1(F), document2(F) et document3(F)

        final CriteresRechercheDocumentDto criteresRechercheDocumentDto1 = new CriteresRechercheDocumentDto();
        criteresRechercheDocumentDto1.setNumeroClient("5555");
        final List<DocumentDto> liste1 = gedService.getListeDocumentsByCriteres(criteresRechercheDocumentDto1, "");

        assertEquals("Taille :", liste1.size(), 4);
        assertEquals(this.leNomDuDocument, liste1.get(0).getNom(), this.nomDocument3);
        assertEquals(this.leNomDuDocument, liste1.get(1).getNom(), this.nomDocument1);
        assertEquals(this.leNomDuDocument, liste1.get(2).getNom(), this.nomDocument2);

    }
}
