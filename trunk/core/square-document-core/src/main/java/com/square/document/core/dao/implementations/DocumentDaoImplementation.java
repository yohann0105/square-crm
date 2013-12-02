package com.square.document.core.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.document.core.dao.interfaces.DocumentDao;
import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.model.Document;

/**
 * Implementation du dao des documents.
 * @author tlebigre
 */
public class DocumentDaoImplementation extends HibernateDaoBaseImplementation implements DocumentDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Document> rechercherDocumentParCritere(CriteresRechercheDocumentDto criteres) {
        final Criteria criteria = createCriteria(Document.class);
        final List<Long> lesIds;

        if ((criteres == null) || (criteres.getIds() == null && criteres.getNumeroClient() == null)) {
            return new ArrayList<Document>();
        }

        if (criteres.getIds() != null && criteres.getNumeroClient() != null) {
            if (criteres.getIds().isEmpty() && criteres.getNumeroClient().isEmpty()) {
                return new ArrayList<Document>();
            }
        }

        // Ids des documents
        if (criteres.getIds() != null && !criteres.getIds().isEmpty()) {
            lesIds = new ArrayList<Long>();
            for (String str : criteres.getIds()) {
                lesIds.add(Long.valueOf(str));
            }
            criteria.add(Restrictions.in("id", lesIds));
        }

        // numeroClient
        if (criteres.getNumeroClient() != null && !criteres.getNumeroClient().isEmpty()) {
            criteria.add(Restrictions.eq("numClient", criteres.getNumeroClient()));
        }

        // Ordonner les éléments
        criteria.addOrder(Order.asc("nom"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Document rechercherDocumentUniqueParCritere(CriteresRechercheDocumentDto criteres) {
        final Criteria criteria = createCriteria(Document.class);
        if ((criteres == null) || (criteres.getIds() == null && criteres.getNumeroClient() == null)) {
            return new Document();
        }

        if (criteres.getIds() != null && criteres.getNumeroClient() != null) {
            if (criteres.getIds().isEmpty() && criteres.getNumeroClient().isEmpty()) {
                return new Document();
            }
        }

        // Id du document
        final String id = (criteres.getIds()).get(0);
        if (!id.isEmpty() && !id.trim().isEmpty()) {
            criteria.add(Restrictions.eq("id", Long.valueOf(id)));
        }

        return (Document) criteria.uniqueResult();
    }

    @Override
    public Document rechercherDocumentlParId(Long id) {
        return load(id, Document.class);
    }

    @Override
    public void ajoutDocument(Document document) {
        saveOrUpdate(document);

    }

}
