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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.tarificateur.noyau.bean.CriteresRechercheLignesDevis;
import com.square.tarificateur.noyau.dao.interfaces.LigneDevisDao;
import com.square.tarificateur.noyau.model.opportunite.LigneDevis;

/**
 * Implémentation de LigneDevisDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class LigneDevisDaoImpl extends HibernateDaoBaseImplementation implements LigneDevisDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public LigneDevis getLigneDevis(Long idLigneDevis) {
        return load(idLigneDevis, LigneDevis.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveLigneDevis(LigneDevis ligneDevis) {
        save(ligneDevis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteLigneDevis(LigneDevis ligneDevis) {
        delete(ligneDevis);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LigneDevis> getLigneDevisPrincipalParIdDevis(Long idDevis) {
        final StringBuffer sql =
            new StringBuffer("select distinct ligneDevis from LigneDevis ligneDevis left join fetch ligneDevis.listeReglesValeurs ")
                .append("left join fetch ligneDevis.listeValeurCritereLigneDevis ")
                .append("where ligneDevis.devis.id = :idDevis ")
                    .append(" and ligneDevis.id not in (select ligneDevisLiee.id from LigneDevisLiee where ligneDevisLiee.devis.id = :idDevis)");
        final Query query = createQuery(sql.toString());
        query.setLong("idDevis", idDevis);
        return (ArrayList<LigneDevis>) query.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getIdLigneDevisPrincipalParIdDevis(Long idDevis) {
        final StringBuffer sql =
            new StringBuffer(" select ligneDevis.id from LigneDevis ligneDevis where ligneDevis.devis.id = :idDevis")
                    .append(" and ligneDevis.id not in (select ligneDevisLiee.id from LigneDevisLiee where ligneDevisLiee.devis.id = :idDevis)");
        final Query query = createQuery(sql.toString());
        query.setLong("idDevis", idDevis);
        return (ArrayList<Long>) query.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<LigneDevis> getLigneDevisPrincipalParCriteres(CriteresRechercheLignesDevis criteres) {
        final StringBuffer sql = new StringBuffer("from LigneDevis ligneDevis where 1 = 1");
        final Map<String, Object> properties = new HashMap<String, Object>();

        if (criteres.getIdDevis() != null) {
            sql.append(" and ligneDevis.devis.id = :idDevis").append(
                " and ligneDevis.id not in (select ligneDevisLiee.id from LigneDevisLiee where ligneDevisLiee.devis.id = :idDevis)");
            properties.put("idDevis", criteres.getIdDevis());
        }
        if (criteres.getIdFinalite() != null) {
            sql.append(" and ligneDevis.finalite.id = :idFinalite");
            properties.put("idFinalite", criteres.getIdFinalite());
        }
        if (criteres.getIdSource() != null) {
            sql.append(" and ligneDevis.sourceLigneDevis.id = :idSource");
            properties.put("idSource", criteres.getIdSource());
        }
        if (criteres.getSelectionnePourAdhesion() != null) {
            sql.append(" and ligneDevis.selectionnePourAdhesion = :selectionnePourAdhesion");
            properties.put("selectionnePourAdhesion", criteres.getSelectionnePourAdhesion());
        }
        if (criteres.getIdBeneficiaire() != null) {
            sql.append(" and ligneDevis.beneficiaire.id = :idBeneficiaire");
            properties.put("idBeneficiaire", criteres.getIdBeneficiaire());
        }
        final Query query = createQuery(sql.toString()).setProperties(properties);
        return (ArrayList<LigneDevis>) query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean estUneLigneLiee(Long idLigneDevis) {
        final StringBuffer sql = new StringBuffer("select ligneDevisLiee.id from LigneDevisLiee where ligneDevisLiee.id = :idLigneDevis");
        final Query query = createQuery(sql.toString());
        query.setLong("idLigneDevis", idLigneDevis);
        return query.uniqueResult() != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LigneDevis> getLignesLieesLignePrincipale(Long idLignePrincipale) {
        final StringBuffer sql = new StringBuffer("select ldl.ligneDevisLiee from LigneDevisLiee ldl where ldl.ligneDevisSource.id = :idLignePrincipale");
        final Query query = createQuery(sql.toString());
        query.setLong("idLignePrincipale", idLignePrincipale);
        return (ArrayList<LigneDevis>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getIdsFinaliteLigneDevis(Long idDevis) {
        final StringBuffer sql = new StringBuffer("select distinct ligneDevis.finalite.id from LigneDevis ligneDevis where ligneDevis.devis.id = :idDevis");
        final Query query = createQuery(sql.toString());
        query.setLong("idDevis", idDevis);
        return (ArrayList<Long>) query.list();
    }

    @Override
    public void deleteLigneDevisFromDevis(LigneDevis ligneDevis) {
        delete(ligneDevis);
    }

}
