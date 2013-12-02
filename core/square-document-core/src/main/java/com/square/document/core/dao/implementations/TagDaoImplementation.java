package com.square.document.core.dao.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.document.core.dao.interfaces.TagDao;
import com.square.document.core.model.Tag;

/**
 * Implementation du dao des tags.
 * @author tlebigre
 */
public class TagDaoImplementation extends HibernateDaoBaseImplementation implements TagDao {

    @Override
    public Tag rechercherTagParId(Long id) {
        return load(id, Tag.class);
    }

    @Override
    public List<Tag> getListeTag() {
        return loadAll(Tag.class);
    }

    @Override
    public void updateTag(Tag tag) {
        saveOrUpdate(tag);
    }

    @Override
    public Tag getTagByCriteria(String code) {
        final Criteria criteria = createCriteria(Tag.class);
        if (code != null && !code.trim().equals("")) {
        	criteria.add(Restrictions.eq("nom", code));
        }

        return (Tag) criteria.uniqueResult();
    }

}
