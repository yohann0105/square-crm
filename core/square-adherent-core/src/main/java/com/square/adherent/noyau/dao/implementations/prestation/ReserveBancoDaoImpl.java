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
package com.square.adherent.noyau.dao.implementations.prestation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.prestation.ReserveBancoDao;
import com.square.adherent.noyau.dto.produit.ReserveProduitBancoDto;

/**
 * Implémentation de l'interface ReserveBancoDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ReserveBancoDaoImpl extends HibernateDaoBaseImplementation implements ReserveBancoDao {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<ReserveProduitBancoDto> getListeReservesBancoByAdherent(Long idAdherent) {
        final StringBuffer requete = new StringBuffer("select franchise_cotisation_annuelle, franchise_versee_reserve, franchise_debut_periode, ");
        requete.append("franchise_fin_periode, garantie_date_adhesion ");
        requete.append("from data_franchise inner join data_garantie on franchise_garantie_uid = garantie_uid ");
        requete.append("where franchise_personne_uid = :idAdherent ");
        requete.append("and franchise_fin_periode >= :dateAnneePrecedente ");
        requete.append("and franchise_debut_periode <= :dateDuJour ");
        requete.append("order by franchise_debut_periode, franchise_fin_periode");
        final Query crit = createSqlQuery(requete.toString());
        crit.setLong("idAdherent", idAdherent);
        // on recupere les franchises des annees N et N-1
        final Calendar dateAnneePrecedente = Calendar.getInstance();
        final int anneePrecedente = dateAnneePrecedente.get(Calendar.YEAR) - 1;
        dateAnneePrecedente.clear();
        dateAnneePrecedente.set(Calendar.DAY_OF_MONTH, 1);
        dateAnneePrecedente.set(Calendar.MONTH, 0);
        dateAnneePrecedente.set(Calendar.YEAR, anneePrecedente);
        final Calendar dateDuJour = Calendar.getInstance();
        dateDuJour.clear(Calendar.MILLISECOND);
        dateDuJour.clear(Calendar.SECOND);
        dateDuJour.clear(Calendar.MINUTE);
        dateDuJour.clear(Calendar.HOUR_OF_DAY);
        crit.setCalendar("dateAnneePrecedente", dateAnneePrecedente);
        crit.setCalendar("dateDuJour", dateDuJour);
        final List<Object[]> resultSet = crit.list();
        final List<ReserveProduitBancoDto> results = new ArrayList<ReserveProduitBancoDto>();
        if (resultSet == null || resultSet.isEmpty()) {
            return null;
        } else {
            for (Object[] row : resultSet) {
                final ReserveProduitBancoDto reserveDto = new ReserveProduitBancoDto();
                reserveDto.setReserveAnnuelle(Double.parseDouble(row[0].toString()));
                reserveDto.setReserveConsommee(Double.parseDouble(row[1].toString()));
                final Calendar dateDebutReserve = Calendar.getInstance();
                dateDebutReserve.setTime((Timestamp) row[2]);
                reserveDto.setDateDebutReserve(dateDebutReserve);
                final Calendar dateFinReserve = Calendar.getInstance();
                dateFinReserve.setTime((Timestamp) row[3]);
                reserveDto.setDateFinReserve(dateFinReserve);
                final Calendar dateAnniversaire = Calendar.getInstance();
                dateAnniversaire.setTime((Timestamp) row[4]);
                reserveDto.setDateAnniversaire(dateAnniversaire);
                results.add(reserveDto);
            }
            return results;
        }
    }
}
