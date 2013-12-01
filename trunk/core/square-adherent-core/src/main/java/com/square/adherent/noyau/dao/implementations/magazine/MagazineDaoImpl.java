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
package com.square.adherent.noyau.dao.implementations.magazine;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.MagazineDao;
import com.square.adherent.noyau.model.data.magazine.AdherentMagazine;
import com.square.adherent.noyau.model.data.magazine.Magazine;

/**
 * Implémentation de l'interface MagazineDao.
 * @author nnadeau - SCUB
 */
public class MagazineDaoImpl extends HibernateDaoBaseImplementation implements MagazineDao {

    /**
     * {@inheritDoc}
     */
    public Magazine getMagazine(Long idMagazine) {
        return load(idMagazine, Magazine.class);
    }

    /**
     * {@inheritDoc}
     */
    public void saveAdherentMagazine(AdherentMagazine adherentMagazine) {
        save(adherentMagazine);
    }

    /**
     * {@inheritDoc}
     */
    public void saveMagazine(Magazine magazine) {
        save(magazine);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Long> getListeAdherentsByIdMagazineEnvoye(Long idMagazine) {
        final Criteria crit = createCriteria(AdherentMagazine.class);
        crit.add(Restrictions.eq("magazine.id", idMagazine));
        crit.setProjection(Projections.property("uidPersonne"));
        return (ArrayList<Long>) crit.list();
    }
}
