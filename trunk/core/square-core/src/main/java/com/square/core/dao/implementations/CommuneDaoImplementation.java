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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.CommuneDao;
import com.square.core.model.CodePostalCommune;
import com.square.core.model.Commune;
import com.square.core.model.dto.CommuneCriteresRechercherDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.IdentifiantLibelleCodePostalCommuneDto;

/**
 * Implémentation du Dao sur les communes.
 * @author cblanchard - SCUB
 */
public class CommuneDaoImplementation extends HibernateDaoBaseImplementation implements CommuneDao {

    @Override
    public Commune rechercheCommuneParId(Long identifiant) {
        return load(identifiant, Commune.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Commune> rechercherCommuneParCriteres(CommuneCriteresRechercherDto criteres) {

        if (criteres.getDimensionCriteres() == null) {
            criteres.setDimensionCriteres(new DimensionCriteresRechercheDto());
        }

        final Criteria criteria = createCriteria(Commune.class);

        // Critère sur l'identifiant
        if (criteres.getDimensionCriteres().getId() != null) {
            criteria.add(Restrictions.eq("id", criteres.getDimensionCriteres().getId()));
        }
        // Critère sur le libelle
        if (criteres.getDimensionCriteres().getLibelle() != null && !criteres.getDimensionCriteres().getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("libelle", criteres.getDimensionCriteres().getLibelle().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getDimensionCriteres().getVisible() != null) {
            criteria.add(Restrictions.eq("visible", criteres.getDimensionCriteres().getVisible()));
        }
        // Critère sur les codes postaux
        if (criteres.getIdCodePostal() != null) {
            criteria.createAlias("codesPostaux", "codePostauxAlias");
            criteria.add(Restrictions.eq("codePostauxAlias.codePostal.id", criteres.getIdCodePostal()));
        }
        // Maxresults
        if (criteres.getDimensionCriteres().getMaxResults() != null) {
            criteria.setFirstResult(0);
            criteria.setMaxResults(criteres.getDimensionCriteres().getMaxResults());
        }
        // Ordonner les éléments
        criteria.addOrder(Order.asc("ordre"));
        criteria.addOrder(Order.asc("libelle"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<IdentifiantLibelleCodePostalCommuneDto> rechercherCodesPostauxCommunes(DimensionCriteresRechercheDto criteres) {
        final Criteria criteria = createCriteria(CodePostalCommune.class);
        criteria.createAlias("commune", "commune");
        criteria.createAlias("codePostal", "codePostal");
        criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("commune.id")).add(Projections.groupProperty("commune.libelle")).add(
            Projections.groupProperty("codePostal.id")).add(Projections.groupProperty("codePostal.codePostal")).add(Projections.groupProperty("commune.ordre"))
                .add(Projections.groupProperty("codePostal.ordre")));

        // Critère sur l'identifiant
        if (criteres.getId() != null) {
            criteria.add(Restrictions.eq("commune.id", criteres.getId()));
        }
        // Critère sur le libelle
        if (criteres.getLibelle() != null && !criteres.getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("codePostal.codePostal", criteres.getLibelle().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getVisible() != null) {
            criteria.add(Restrictions.eq("commune.visible", criteres.getVisible()));
            criteria.add(Restrictions.eq("codePostal.visible", criteres.getVisible()));
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

        final List<Object[]> resultat = criteria.list();
        final List<IdentifiantLibelleCodePostalCommuneDto> list = new ArrayList<IdentifiantLibelleCodePostalCommuneDto>();
        for (Object[] row : resultat) {
            final IdentifiantLibelleCodePostalCommuneDto identifiantLibelleCodePostalCommuneDto = new IdentifiantLibelleCodePostalCommuneDto();
            identifiantLibelleCodePostalCommuneDto.setIdCodePostal((Long) row[2]);
            identifiantLibelleCodePostalCommuneDto.setIdCommune((Long) row[0]);
            identifiantLibelleCodePostalCommuneDto.setLibelleCodePostal(String.valueOf(row[3]));
            identifiantLibelleCodePostalCommuneDto.setLibelleCommune(String.valueOf(row[1]));
            list.add(identifiantLibelleCodePostalCommuneDto);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<IdentifiantLibelleCodePostalCommuneDto> rechercherCommunesCodesPostaux(DimensionCriteresRechercheDto criteres) {
        final Criteria criteria = createCriteria(CodePostalCommune.class);
        criteria.createAlias("commune", "commune");
        criteria.createAlias("codePostal", "codePostal");
        criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("commune.id")).add(Projections.groupProperty("commune.libelle"))
                .add(Projections.groupProperty("codePostal.id")).add(Projections.groupProperty("codePostal.codePostal")).add(
                    Projections.groupProperty("commune.ordre")).add(Projections.groupProperty("codePostal.ordre")));

        // Critère sur l'identifiant
        if (criteres.getId() != null) {
            criteria.add(Restrictions.eq("commune.id", criteres.getId()));
        }
        // Critère sur le libelle
        if (criteres.getLibelle() != null && !criteres.getLibelle().equals("")) {
            criteria.add(Restrictions.ilike("commune.libelle", criteres.getLibelle().toLowerCase() + "%"));
        }
        // Critère sur la visibilité
        if (criteres.getVisible() != null) {
            criteria.add(Restrictions.eq("commune.visible", criteres.getVisible()));
            criteria.add(Restrictions.eq("codePostal.visible", criteres.getVisible()));
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

        final List<Object[]> resultat = criteria.list();
        final List<IdentifiantLibelleCodePostalCommuneDto> list = new ArrayList<IdentifiantLibelleCodePostalCommuneDto>();
        for (Object[] row : resultat) {
            final IdentifiantLibelleCodePostalCommuneDto identifiantLibelleCodePostalCommuneDto = new IdentifiantLibelleCodePostalCommuneDto();
            identifiantLibelleCodePostalCommuneDto.setIdCodePostal((Long) row[2]);
            identifiantLibelleCodePostalCommuneDto.setIdCommune((Long) row[0]);
            identifiantLibelleCodePostalCommuneDto.setLibelleCodePostal(String.valueOf(row[3]));
            identifiantLibelleCodePostalCommuneDto.setLibelleCommune(String.valueOf(row[1]));
            list.add(identifiantLibelleCodePostalCommuneDto);
        }
        return list;
    }
}
