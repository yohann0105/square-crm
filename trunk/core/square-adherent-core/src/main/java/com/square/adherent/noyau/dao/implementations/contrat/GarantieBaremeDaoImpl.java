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
package com.square.adherent.noyau.dao.implementations.contrat;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.contrat.GarantieBaremeDao;
import com.square.adherent.noyau.dto.adherent.contrat.CoupleBaremeDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeCriteresDto;
import com.square.adherent.noyau.model.data.contrat.GarantieBareme;

/**
 * Implémentation de GarantieBaremeDao.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class GarantieBaremeDaoImpl extends HibernateDaoBaseImplementation implements GarantieBaremeDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<GarantieBareme> getListeGarantiesBaremesByCriteres(GarantieBaremeCriteresDto criteres) {
        final StringBuffer requete = new StringBuffer("select distinct b1 from GarantieBareme b1 where b1 in (select b ");
        requete.append(createQuery(criteres));
        if (criteres.getContratCollectif().booleanValue()) {
            requete.append(" order by cg.id, cp.id, g.id, b.id )");
        } else {
            requete.append("order by c.id, g.id, b.id )");
        }
        final Query query = createQuery(requete.toString());
        setParamQuery(query, criteres);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CoupleBaremeDto> getListeCouplesBaremesByCriteres(GarantieBaremeCriteresDto criteres) {
        final StringBuffer requete = new StringBuffer("select distinct b.bareme1Eid, b.bareme2Eid ");
        requete.append(createQuery(criteres));
        final Query query = createQuery(requete.toString());
        setParamQuery(query, criteres);
        final List<Object[]> listResult = query.list();
        final List<CoupleBaremeDto> liste = new ArrayList<CoupleBaremeDto>();
        if (listResult != null && listResult.size() > 0) {
            for (Object[] result : listResult) {
                final CoupleBaremeDto couple = new CoupleBaremeDto();
                if (result[0] != null) {
                    couple.setBareme1Eid(String.valueOf(result[0]));
                }
                if (result[1] != null) {
                    couple.setBareme2Eid(String.valueOf(result[1]));
                }
                liste.add(couple);
            }
        }
        return liste;
    }

    private String createQuery(GarantieBaremeCriteresDto criteres) {
        final StringBuffer requete = new StringBuffer("from Garantie g, GarantieBareme b, TarifType tt, TypePayeur tp, GarantieStatut gs, ");
        if (criteres.getContratCollectif().booleanValue()) {
            requete.append("Contrat cg, Contrat cp ");
            requete.append("where substring(cg.identifiantExterieur,1,13) = substring(cp.identifiantExterieur,1,13) ");
            requete.append("and g.contrat.id = cp.id ");
        } else {
            requete.append("Contrat c ");
            requete.append("where g.contrat.id = c.id ");
        }

        requete.append("and g.statut.id = gs.id ");
        requete.append("and gs.eid = '->' ");

        requete.append("and g.id = b.garantie.id ");
        requete.append("and tt.id = b.tarifType.id ");
        requete.append("and tp.id = b.typePayeur.id ");
        if (criteres.getGarantie() != null) {
            requete.append("and g.eid = :garantie ");
        }
        if (criteres.getGarantieGestion() != null) {
            requete.append("and g.libelleGarantieGestion = :garantieGestion ");
        }
        if (criteres.getProduitGestion() != null) {
            requete.append("and g.libelleProduitGestion = :produitGestion ");
        }
        if (criteres.getContrat() != null && criteres.getContratCollectif().booleanValue()) {
            requete.append("and cg.numeroContrat = :numeroContrat ");
        } else if (criteres.getContrat() != null && !criteres.getContratCollectif().booleanValue()) {
            requete.append("and c.numeroContrat = :numeroContrat ");
        }
        if (criteres.getEidTarifType() != null) {
            requete.append("and tt.eid = :eidTarifType ");
        }
        if (criteres.getEidTypePayeur() != null) {
            requete.append("and tp.eid = :eidTypePayeur ");
        }
        if (criteres.getUidAssure() != null && criteres.getContratCollectif().booleanValue()) {
            requete.append("and cp.uidAssure = :uidAssure ");
        } else if (criteres.getUidAssure() != null && !criteres.getContratCollectif().booleanValue()) {
            requete.append("and c.uidAssure = :uidAssure ");
        }
        if (criteres.getIdRoleGarantie() != null) {
            requete.append("and g.role.id = :idRole ");
        }
        if (criteres.getDateDebut() != null) {
            requete.append("and g.dateAdhesion <= :dateDebut ");
        }
        if (criteres.getDateFin() != null) {
            requete.append("and (g.dateResiliation is null or g.dateResiliation >= :dateFin) ");
        }
        if (criteres.getListeIdsBareme() != null && criteres.getListeIdsBareme().size() > 0) {
            requete.append("and b.bareme1Eid in (:listeIdsBareme) ");
        }
        return requete.toString();
    }

    private void setParamQuery(Query query, GarantieBaremeCriteresDto criteres) {
        if (criteres.getGarantie() != null) {
            query.setString("garantie", criteres.getGarantie());
        }
        if (criteres.getGarantieGestion() != null) {
            query.setString("garantieGestion", criteres.getGarantieGestion());
        }
        if (criteres.getProduitGestion() != null) {
            query.setString("produitGestion", criteres.getProduitGestion());
        }
        if (criteres.getContrat() != null) {
            query.setString("numeroContrat", criteres.getContrat());
        }
        if (criteres.getEidTarifType() != null) {
            query.setString("eidTarifType", criteres.getEidTarifType());
        }
        if (criteres.getEidTypePayeur() != null) {
            query.setString("eidTypePayeur", criteres.getEidTypePayeur());
        }
        if (criteres.getUidAssure() != null) {
            query.setLong("uidAssure", criteres.getUidAssure());
        }
        if (criteres.getIdRoleGarantie() != null) {
            query.setLong("idRole", criteres.getIdRoleGarantie());
        }
//        if (criteres.getDateEffet() != null) {
//            query.setCalendar("dateEffet", criteres.getDateEffet());
//        }
        if (criteres.getDateDebut() != null) {
            query.setCalendar("dateDebut", criteres.getDateDebut());
        }
        if (criteres.getDateFin() != null) {
            query.setCalendar("dateFin", criteres.getDateFin());
        }
        if (criteres.getListeIdsBareme() != null && criteres.getListeIdsBareme().size() > 0) {
            query.setParameterList("listeIdsBareme", criteres.getListeIdsBareme());
        }
    }

}
