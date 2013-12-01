package com.square.document.core.dao.interfaces;

import java.util.List;

import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.model.Document;

/**
 * Dao pour le document.
 * @author tlebigre
 */
public interface DocumentDao {

    /**
     * Recuperer un document par son identifiant.
     * @param id l'identifiant de du document à retourner.
     * @return le document
     */
    Document rechercherDocumentlParId(final Long id);

    /**
     * Recherche les documents correspondant aux critères de recherche.
     * @param criteres les critères de recherche.
     * @return la liste des documents trouvés.
     */
    List<Document> rechercherDocumentParCritere(CriteresRechercheDocumentDto criteres);

    /**
     * Ajout d'un document.
     * @param document document à ajouter
     */
    void ajoutDocument(Document document);

    /**
     * Récupère un unique document à partir de critères.
     * @param criteres criteres de recherche
     * @return le document
     */
    Document rechercherDocumentUniqueParCritere(CriteresRechercheDocumentDto criteres);

}
