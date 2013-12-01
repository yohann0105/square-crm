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
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.PersonneDao;
import com.square.core.model.AdresseNature;
import com.square.core.model.Personne;
import com.square.core.model.dto.AdresseSimpleDto;

/**
 * Classe pour les requêtes Polymorphique sur les personne.
 * @author Goumard Stephane (Scub) - SCUB
 */
public class PersonneDaoImplementation extends HibernateDaoBaseImplementation implements PersonneDao {

    @Override
    public Personne rechercherPersonneParId(final Long id) {
        return load(id, Personne.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AdresseSimpleDto> rechercherAdressesSimplesParIdPersonne(Long idPersonne) {
        // Recherche des adresses de la personne
        final Criteria criteria = createCriteria(Personne.class);
        criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("adr.dateDebut")).add(Projections.groupProperty("adr.dateFin")).add(
            Projections.groupProperty("adr.nature")).add(Projections.groupProperty("nature.ordre")));

        criteria.add(Restrictions.eq("id", idPersonne));
        criteria.createAlias("adresses", "adr");
        criteria.createAlias("adr.nature", "nature");
        criteria.addOrder(Order.asc("nature.ordre"));
        criteria.addOrder(Order.asc("adr.dateDebut"));
        criteria.addOrder(Order.asc("adr.dateFin"));

        // Transformation

        final List<Object[]> resultatRequete = criteria.list();
        final List<AdresseSimpleDto> resultat = new ArrayList<AdresseSimpleDto>();

        if (resultatRequete != null) {
            for (Object[] row : resultatRequete) {
                final AdresseSimpleDto adresseSimpleDto = new AdresseSimpleDto();
                adresseSimpleDto.setDateDebut((Calendar) row[0]);
                adresseSimpleDto.setDateFin((Calendar) row[1]);
                final AdresseNature adresseNature = (AdresseNature) row[2];
                if (adresseNature != null) {
                    adresseSimpleDto.setTypeAdresse(new IdentifiantLibelleDto(adresseNature.getId(), adresseNature.getLibelle()));
                }

                resultat.add(adresseSimpleDto);
            }

        }
        else {
            return null;
        }

        return resultat;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Personne> rechercherPersonnesParAdresse(Long adresseId) {
    	final Criteria criteria = createCriteria(Personne.class);
        criteria.createAlias("adresses", "a");
        criteria.add(Restrictions.eq("a.id", adresseId));
        criteria.add(Restrictions.eq("a.supprime", false));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Personne> rechercherPersonnesParEmail(Long emailId) {
    	final Criteria criteria = createCriteria(Personne.class);
        criteria.createAlias("emails", "e");
        criteria.add(Restrictions.eq("e.id", emailId));
        criteria.add(Restrictions.eq("e.supprime", false));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Personne> rechercherPersonnesParTelephone(Long telephoneId) {
    	final Criteria criteria = createCriteria(Personne.class);
        criteria.createAlias("telephones", "t");
        criteria.add(Restrictions.eq("t.id", telephoneId));
        criteria.add(Restrictions.eq("t.supprime", false));
        return criteria.list();
    }
}
