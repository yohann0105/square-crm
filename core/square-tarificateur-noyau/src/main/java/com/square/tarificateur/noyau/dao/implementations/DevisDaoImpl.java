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
package com.square.tarificateur.noyau.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.tarificateur.noyau.dao.interfaces.DevisDao;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheDevisDto;
import com.square.tarificateur.noyau.model.opportunite.Devis;

/**
 * Implémentation de DevisDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class DevisDaoImpl extends HibernateDaoBaseImplementation implements DevisDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public Devis getDevis(Long idDevis) {
        return load(idDevis, Devis.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDevis(Devis devis) {
        save(devis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Devis> getListeDevisByOpportunite(Long idOpportunite) {
        final Criteria crit = createCriteria(Devis.class);
        crit.createAlias("finalite", "finalite");
        crit.add(Restrictions.eq("opportunite.id", idOpportunite));
        crit.addOrder(Order.asc("finalite.ordre"));
        crit.addOrder(Order.desc("dateCreation"));
        return (ArrayList<Devis>) crit.list();
    }

    @Override
    public void deleteDevis(Long idDevis) {
        delete(load(idDevis, Devis.class));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Devis> getListeDevisByCriteres(CriteresRechercheDevisDto criteres) {
        final Criteria crit = createCriteria(Devis.class);
        if (criteres.getCloturer() != null) {
            if (criteres.getCloturer()) {
                crit.add(Restrictions.isNotNull("dateCloture"));
            } else {
                crit.add(Restrictions.isNull("dateCloture"));
            }
        }
        if (criteres.getOrigineSiteWeb() != null) {
            if (criteres.getOrigineSiteWeb()) {
                crit.add(Restrictions.eq("origineSiteWeb", true));
            } else {
                crit.add(Restrictions.or(Restrictions.isNull("origineSiteWeb"), Restrictions.eq("origineSiteWeb", false)));
            }
        }
        if (criteres.getIdFinalite() != null) {
            crit.add(Restrictions.eq("finalite.id", criteres.getIdFinalite()));
        }
        if (criteres.getIdsFinalite() != null && criteres.getIdsFinalite().size() > 0) {
            crit.add(Restrictions.in("finalite.id", criteres.getIdsFinalite()));
        }
        if (criteres.getIdPersonne() != null || criteres.getEidPersonne() != null) {
            final Criteria personne = crit.createAlias("personnePrincipale", "personne");
            if (criteres.getIdPersonne() != null) {
                personne.add(Restrictions.eq("personne.id", criteres.getIdPersonne()));
            }
            if (criteres.getEidPersonne() != null) {
                personne.add(Restrictions.eq("personne.eidPersonne", criteres.getEidPersonne()));
            }
        }
        if (criteres.getIdDevis() != null) {
            crit.add(Restrictions.eq("id", criteres.getIdDevis()));
        }
        return (ArrayList<Devis>) crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> getEidsProduitDevis(final Long idDevis) {
        final Query query = createQuery("select distinct ld.eidProduit from Devis devis inner join devis.listeLigneDevis ld where devis.id = :idDevis");
        query.setLong("idDevis", idDevis);
        return query.list();
    }

}
