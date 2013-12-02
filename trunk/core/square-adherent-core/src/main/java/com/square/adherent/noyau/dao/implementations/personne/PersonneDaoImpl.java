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
package com.square.adherent.noyau.dao.implementations.personne;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.personne.PersonneDao;
import com.square.adherent.noyau.dto.prestation.CriteresPersonnesNotificationSmsDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;

/**
 * Implémentation de PersonneDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonneDaoImpl extends HibernateDaoBaseImplementation implements PersonneDao {

    /** Service de mapping. */
    private AdherentMappingService adherentMappingService;

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> getListeBeneficiairesPersonne(Long idPersonne) {
        final StringBuffer requete = new StringBuffer();
        requete.append("SELECT distinct garantie.uidBeneficiaire FROM Garantie garantie ");
        requete.append("WHERE garantie.uidAssure = :idPersonne ");
        requete.append("AND garantie.statut.id = :idStatutGarantieEnCours and garantie.role.id <> :idRoleGarantieAssure ");
        final Query query = createQuery(requete.toString());
        query.setLong("idPersonne", idPersonne);
        query.setLong("idStatutGarantieEnCours", adherentMappingService.getIdStatutGarantieEnCours());
        query.setLong("idRoleGarantieAssure", adherentMappingService.getIdRoleGarantieAssure());
        return (ArrayList<Long>) query.list();
    }

    @Override
    public Long getAdherentPrincipalPersonne(Long idPersonne) {
        final StringBuffer requete = new StringBuffer();
        requete.append("SELECT distinct garantie.uidAssure FROM Garantie garantie ");
        requete.append("WHERE garantie.uidBeneficiaire = :idPersonne ");
        requete.append("AND garantie.statut.id = :idStatutGarantieEnCours and garantie.role.id <> :idRoleGarantieAssure ");
        final Query query = createQuery(requete.toString());
        query.setLong("idPersonne", idPersonne);
        query.setLong("idStatutGarantieEnCours", adherentMappingService.getIdStatutGarantieEnCours());
        query.setLong("idRoleGarantieAssure", adherentMappingService.getIdRoleGarantieAssure());
        return (Long) query.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    public List<Long> getListePersonnesNotificationSmsByCriteres(CriteresPersonnesNotificationSmsDto criteres) {
        // Construction de la requête
        final StringBuffer requete =
            new StringBuffer("SELECT distinct l.decompte_assure_uid ")
                    .append("FROM data_decompte l ")
                    .append("INNER JOIN data_option o ON (l.decompte_assure_uid = o.option_personne_uid and o.option_type_uid = :idTypeOptionSms"
                             + " and o.option_top_actif = true) ")
                    .append("WHERE ( ").append("SELECT sum(l2.decompte_lig_remb_compl) as montant_total FROM data_decompte l2 ").append(
                        "WHERE l2.decompte_origine_uid not in (:listeOriginesDesactivees) ").append(
                        "AND l2.decompte_date_reglement = :datePaiement ").append("AND l2.decompte_assure_uid = l.decompte_assure_uid ").append(
                        ") >= :montantMinimal ").append("AND decompte_origine_uid not in (:listeOriginesDesactivees) ");

        final Query query = createSqlQuery(requete.toString());
        query.setCalendar("datePaiement", criteres.getDateReglement());
        query.setFloat("montantMinimal", criteres.getMontantMinimal());
        query.setLong("idTypeOptionSms", adherentMappingService.getIdTypeOptionEnvoiSms());
        query.setParameterList("listeOriginesDesactivees", adherentMappingService.getListeOriginesDecomptesExcluesEnvoiSms());

        // on mappe les resultats dans des dtos
        final List<Long> liste = new ArrayList<Long>();
        for (Object item : query.list()) {
            if (item instanceof Long) {
                liste.add((Long) item);
            } else if (item instanceof BigInteger) {
                liste.add(((BigInteger) item).longValue());
            }
        }
        return liste;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
