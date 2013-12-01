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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.CodePostalCommuneDao;
import com.square.core.model.CodePostalCommune;
import com.square.core.model.dto.DimensionCritereRechercheCodePostalCommuneDto;

/**
 * Implémentation du dao sur les codes postaux - communes.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class CodePostalCommuneDaoImplementation extends HibernateDaoBaseImplementation implements CodePostalCommuneDao {

    @Override
    public CodePostalCommune rechercheCodePostalCommuneParId(Long identifiant) {
        return load(identifiant, CodePostalCommune.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CodePostalCommune> rechercherCodesPostauxCommunesParCriteres(DimensionCritereRechercheCodePostalCommuneDto criteres) {
        final Criteria criteria = createCriteria(CodePostalCommune.class);
        criteria.createAlias("commune", "commune");
        criteria.createAlias("codePostal", "codePostal");
        // Critère sur l'identifiant
        if (criteres.getIdCodePostalCommune() != null) {
            criteria.add(Restrictions.eq("id", criteres.getIdCodePostalCommune()));
        }
        // Critère sur le libellé d'acheminement
        if (criteres.getLibelleAcheminement() != null && !criteres.getLibelleAcheminement().equals("")) {
            criteria.add(Restrictions.ilike("libelleAcheminement", criteres.getLibelleAcheminement().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getVisible() != null) {
            criteria.add(Restrictions.eq("commune.visible", criteres.getVisible()));
            criteria.add(Restrictions.eq("codePostal.visible", criteres.getVisible()));
        }
        // Critère sur l'identifiant de la commune
        if (criteres.getIdCommune() != null) {
            criteria.add(Restrictions.eq("commune.id", criteres.getIdCommune()));
        }
        // Critère sur le libellé de la commune
        if (criteres.getLibelleCommune() != null && !"".equals(criteres.getLibelleCommune())) {
            criteria.add(Restrictions.ilike("commune.libelle", criteres.getLibelleCommune().toLowerCase() + "%"));
        }
        // Critère sur l'identifiant du code postal
        if (criteres.getIdCodePostal() != null) {
            criteria.add(Restrictions.eq("codePostal.id", criteres.getIdCodePostal()));
        }
        // Critère sur le libellé du code postal
        if (criteres.getLibelleCodePostal() != null && !"".equals(criteres.getLibelleCodePostal())) {
            criteria.add(Restrictions.ilike("codePostal.codePostal", criteres.getLibelleCodePostal().toLowerCase() + "%"));
        }
        // Maxresults
        if (criteres.getMaxResults() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getMaxResults());
        }
        // Ordonner les éléments
        criteria.addOrder(Order.asc("commune.ordre"));
        criteria.addOrder(Order.asc("commune.libelle"));
        criteria.addOrder(Order.asc("codePostal.ordre"));

        return (ArrayList<CodePostalCommune>) criteria.list();
    }
}
