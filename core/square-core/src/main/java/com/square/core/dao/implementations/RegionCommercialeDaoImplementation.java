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
package com.square.core.dao.implementations;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.RegionCommercialeDao;
import com.square.core.model.RegionCommerciale;

/**
 * Implémentation de l'interface RegionCommercialeDao.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class RegionCommercialeDaoImplementation extends HibernateDaoBaseImplementation implements RegionCommercialeDao {

    @Override
    public RegionCommerciale rechercherRegionCommercialeParEid(String eidRegionCommerciale) {
        final Criteria criteria = createCriteria(RegionCommerciale.class);
        if (eidRegionCommerciale != null && !"".equals(eidRegionCommerciale)) {
            criteria.add(Restrictions.eq("identifiantExterieur", eidRegionCommerciale));
        }
        return (RegionCommerciale) criteria.uniqueResult();
    }

    @Override
    public RegionCommerciale rechercherRegionCommercialeParId(Long idRegionCommerciale) {
        return load(idRegionCommerciale, RegionCommerciale.class);
    }

}
