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
package com.square.adherent.noyau.dao.implementations.banque;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.banque.BanqueDao;
import com.square.adherent.noyau.dto.banque.BanqueDto;
import com.square.adherent.noyau.dto.banque.CritereRechercheBanqueDto;

/**
 * Implémentation de l'interface BanqueDao.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class BanqueDaoImpl extends HibernateDaoBaseImplementation implements BanqueDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<BanqueDto> getListeBanquesByCriteres(CritereRechercheBanqueDto criteres) {
        final StringBuffer requete = new StringBuffer("select bc.libelle, bg.domiciliation from BanqueCode bc, BanqueGuichet bg where bc.id = bg.idCodeBanque");
        if (criteres.getCodeBanque() != null && !"".equals(criteres.getCodeBanque())) {
            requete.append(" and bc.eid = :codeBanque");
        }
        if (criteres.getCodeGuichet() != null && !"".equals(criteres.getCodeGuichet())) {
            requete.append(" and bg.eid = :codeGuichet");
        }
        requete.append(" order by bc.libelle");
        final Query query = createQuery(requete.toString());
        if (criteres.getCodeBanque() != null && !"".equals(criteres.getCodeBanque())) {
            query.setString("codeBanque", criteres.getCodeBanque());
        }
        if (criteres.getCodeGuichet() != null && !"".equals(criteres.getCodeGuichet())) {
            query.setString("codeGuichet", criteres.getCodeGuichet());
        }
        final List<Object[]> listResult = query.list();
        final List<BanqueDto> listeBanques = new ArrayList<BanqueDto>();
        if (listResult != null && listResult.size() > 0) {
            for (Object[] result : listResult) {
                final String libelleBanque = String.valueOf(result[0]);
                final String libelleAgence = String.valueOf(result[1]);
                final BanqueDto banque = new BanqueDto();
                banque.setLibelleBanque(libelleBanque);
                banque.setLibelleAgence(libelleAgence);
                listeBanques.add(banque);
            }
        }
        return listeBanques;
    }

}
