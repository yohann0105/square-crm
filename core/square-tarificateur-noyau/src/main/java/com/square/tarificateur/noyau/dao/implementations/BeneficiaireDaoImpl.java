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

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.tarificateur.noyau.bean.CriteresBeneficiaire;
import com.square.tarificateur.noyau.dao.interfaces.BeneficiaireDao;
import com.square.tarificateur.noyau.model.personne.Beneficiaire;

/**
 * Implémentation de BeneficiaireDao.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class BeneficiaireDaoImpl extends HibernateDaoBaseImplementation implements BeneficiaireDao {

    @Override
    public void createBeneficiaire(Beneficiaire beneficiaire) {
        save(beneficiaire);
    }

    @Override
    public Beneficiaire getBeneficiaireByCible(Long idBeneficiaire) {
        final Criteria crit = createCriteria(Beneficiaire.class);
        crit.add(Restrictions.eq("personneCible.id", idBeneficiaire));
        return (Beneficiaire) crit.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Beneficiaire> getBeneficiairesParCriteres(CriteresBeneficiaire criteres) {
        final Criteria crit = createCriteria(Beneficiaire.class);
        crit.createAlias("personneCible", "pc");
        crit.createAlias("personneSource", "ps");
        if (StringUtils.isNotBlank(criteres.getNom())) {
            crit.add(Restrictions.eq("pc.nom", criteres.getNom()));
        }
        if (StringUtils.isNotBlank(criteres.getPrenom())) {
            crit.add(Restrictions.eq("pc.prenom", criteres.getPrenom()));
        }
        if (criteres.getDateNaissance() != null) {
            crit.add(Restrictions.eq("pc.dateNaissance", criteres.getDateNaissance()));
        }
        if (criteres.getIdPersonneSource() != null) {
            crit.add(Restrictions.eq("ps.id", criteres.getIdPersonneSource()));
        }
        return (ArrayList<Beneficiaire>) crit.list();
    }

    @Override
    public void deleteBeneficiaire(Long idBeneficiaire) {
    	delete(load(idBeneficiaire, Beneficiaire.class));
    }
}
