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
/**
 * 
 */
package com.square.adherent.noyau.dao.implementations.prestation;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.prestation.DecompteDao;
import com.square.adherent.noyau.dto.prestation.EntetePrestationResultatRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationCriteresRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationResultatRechercheDto;
import com.square.adherent.noyau.model.data.prestation.Decompte;

/**
 * Moteur de recherche des decomptes.
 * @author Goumard Stephane (scub) - SCUB
 */
public class DecompteDaoImpl extends HibernateDaoBaseImplementation implements DecompteDao {

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Integer getNombreTotalPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres) {
        final String select = "select count(*)";
        final Query query = creerQuery(select, criteres, false, false);
        return Integer.valueOf(query.uniqueResult().toString());
    }

    @Override
    public Integer getNombreTotalEntetesPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres) {
        final String select = "select distinct d.decompte_numero";
        final Query query = creerQuery(select, criteres, false, true);
        return query.list().size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PrestationResultatRechercheDto> rechercherPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres) {
        final StringBuffer select = new StringBuffer("select d.decompte_uid, d.decompte_numero, d.decompte_assure_uid, d.decompte_lig_patient_uid, ");
        select.append(" d.decompte_lig_date_debut_soin, d.decompte_lig_date_fin_soin, d.decompte_date_reglement, ");
        select.append(" acte.decompte_acte_uid, acte.decompte_acte_lib, ");
        select.append(" origine.decompte_origine_uid, origine.decompte_origine_lib, ");
        select.append(" banque.banque_compte, banque.banque_domiciliation, ");
        select.append(" d.decompte_destinataire_nom, d.decompte_reglement_professionnel_sante, ");
        select.append(" d.decompte_reglement_numero_cheque, ");
        select.append(" d.decompte_lig_montant, d.decompte_lig_remb_secu, d.decompte_lig_remb_compl, ");
        select.append(" abs(d.decompte_lig_montant - (d.decompte_lig_remb_secu + d.decompte_lig_remb_compl)) as resteACharge, ");
        select.append(" d.decompte_lig_mt_baseremb_ro, d.decompte_lig_tx_remb_ro, ");
        select.append(" d.decompte_statut_paiement, d.decompte_benef_reglmt_nature_id");
        final Query query = creerQuery(select.toString(), criteres, true, false);
        final List<Object[]> resultSet = query.list();
        final List<PrestationResultatRechercheDto> results = new ArrayList<PrestationResultatRechercheDto>();
        for (Object[] row : resultSet) {
            final PrestationResultatRechercheDto prestation = new PrestationResultatRechercheDto();
            int i = 0;
            prestation.setId(((BigInteger) row[i++]).longValue());
            prestation.setNumero((String) row[i++]);
            prestation.setUidAssure(((BigInteger) row[i++]).longValue());
            prestation.setUidLigPatient(((BigInteger) row[i++]).longValue());
            prestation.setDateDebutSoins(convertTimeStamp((Timestamp) row[i++]));
            prestation.setDateFinSoins(convertTimeStamp((Timestamp) row[i++]));
            prestation.setDateReglement(convertTimeStamp((Timestamp) row[i++]));
            prestation.setActe(new IdentifiantLibelleDto(((BigInteger) row[i++]).longValue(), (String) row[i++]));
            prestation.setOrigine(new IdentifiantLibelleDto(((BigInteger) row[i++]).longValue(), (String) row[i++]));
            prestation.setCompte((String) row[i++]);
            prestation.setDomiciliation((String) row[i++]);
            prestation.setNomDestinataire((String) row[i++]);
            prestation.setProfessionnelSante((String) row[i++]);
            prestation.setNumeroCheque((String) row[i++]);
            prestation.setMontant((Double) row[i++]);
            prestation.setRemboursementSecu((Double) row[i++]);
            prestation.setRemboursementCompl((Double) row[i++]);
            prestation.setResteACharge((Double) row[i++]);
            prestation.setBaseRemboursementRO((Double) row[i++]);
            prestation.setTauxRemboursementRO((Double) row[i++]);
            prestation.setStatutPaiement((String) row[i++]);
            prestation.setIdNatureReglement((String) row[i++]);
            results.add(prestation);
        }
        return results;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EntetePrestationResultatRechercheDto> rechercherEntetesPrestationParCritreres(
        RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres) {
        final StringBuffer select = new StringBuffer("select d.decompte_numero, d.decompte_date_reglement, ");
        select.append(" cast (min(d.decompte_lig_date_debut_soin) as timestamp), cast (max(d.decompte_lig_date_fin_soin) as timestamp), ");
        select.append(" origine.decompte_origine_uid, origine.decompte_origine_lib, sum(d.decompte_lig_remb_compl), ");
        select.append(" banque.banque_compte, banque.banque_domiciliation, ");
        select.append(" d.decompte_destinataire_nom, d.decompte_benef_reglement_nom, ");
        select.append(" d.decompte_reglement_professionnel_sante, d.decompte_reglement_numero_cheque, d.decompte_statut_paiement, ");
        select.append(" bs.personne_uid, upper(bs.personne_nom)||' '||bs.personne_prenom");
        final Query query = creerQuery(select.toString(), criteres, true, true);
        final List<Object[]> resultSet = query.list();
        final List<EntetePrestationResultatRechercheDto> results = new ArrayList<EntetePrestationResultatRechercheDto>();
        for (Object[] row : resultSet) {
            final EntetePrestationResultatRechercheDto prestation = new EntetePrestationResultatRechercheDto();
            int i = 0;
            prestation.setNumero((String) row[i++]);
            prestation.setDateReglement(convertTimeStamp((Timestamp) row[i++]));
            prestation.setDateDebutSoins(convertTimeStamp((Timestamp) row[i++]));
            prestation.setDateFinSoins(convertTimeStamp((Timestamp) row[i++]));
            prestation.setOrigine(new IdentifiantLibelleDto(((BigInteger) row[i++]).longValue(), (String) row[i++]));
            prestation.setRemboursementSmatis((Double) row[i++]);
            prestation.setCompte((String) row[i++]);
            prestation.setDomiciliation((String) row[i++]);
            prestation.setNomDestinataire((String) row[i++]);
            prestation.setNomDestinataireReglement((String) row[i++]);
            prestation.setProfessionnelSante((String) row[i++]);
            prestation.setNumeroCheque((String) row[i++]);
            prestation.setStatutPaiement((String) row[i++]);
            final Long idBeneficiaireSoins = ((BigInteger) row[i++]).longValue();
            final String libelleBeneficiaireSoins = (String) row[i++];
            prestation.setBeneficiaireSoins(new IdentifiantLibelleDto(idBeneficiaireSoins, libelleBeneficiaireSoins));
            results.add(prestation);
        }
        return results;
    }

    /**
     * Créer la requête HQL et ajoute les critères.
     * @param select clause select de la requête
     * @param criteresPagines les critères de recherche
     * @param orderBy true pour ajouter la clause order by à la requête.
     * @return requête HQL
     */
    private Query creerQuery(String select, RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteresPagines, boolean orderBy, boolean groupBy) {
        final SQLQuery query;
        Map<String, Object> params = null;
        final StringBuffer requete = new StringBuffer(select);
        requete.append(" from data_decompte d");
        requete.append(" left join dim_decompte_acte acte on (acte.decompte_acte_uid = d.decompte_lig_acte_uid) ");
        requete.append(" left join dim_decompte_origine origine on (origine.decompte_origine_uid = d.decompte_origine_uid) ");
        requete.append(" left join data_banque banque on (banque.banque_uid = d.decompte_reglement_banque_uid) ");
        if (groupBy) {
            requete.append(" left join data_personne_physique bs on (bs.personne_uid = d.decompte_lig_patient_uid) ");
        }
        requete.append(" where 1 = 1");

        final PrestationCriteresRechercheDto criteres = criteresPagines.getCriterias();
        if (criteres != null) {
            params = new HashMap<String, Object>();
            if (criteres.getIdAssure() != null) {
                final String param = "uidAssure";
                requete.append(" and d.decompte_assure_uid = :" + param);
                params.put(param, criteres.getIdAssure());
            }
            if (criteres.getIdBeneficiaire() != null) {
                final String param = "uidLigPatient";
                requete.append(" and d.decompte_lig_patient_uid = :" + param);
                params.put(param, criteres.getIdBeneficiaire());
            }
            if (criteres.getNumero() != null) {
                final String param = "numero";
                requete.append(" and d.decompte_numero like :" + param);
                params.put(param, criteres.getNumero() + "%");
            }
            if (criteres.getNumeroDecompteExact() != null) {
                final String param = "numero";
                requete.append(" and d.decompte_numero = :" + param);
                params.put(param, criteres.getNumeroDecompteExact());
            }
            if (criteres.getDateDebutSoins() != null) {
                final String param = "dateDebut";
                requete.append(" and d.decompte_lig_date_debut_soin >= :" + param);
                params.put(param, criteres.getDateDebutSoins());
            }
            if (criteres.getDateFinSoins() != null) {
                final String param = "dateFin";
                requete.append(" and d.decompte_lig_date_debut_soin <= :" + param);
                params.put(param, criteres.getDateFinSoins());
            }
            if (criteres.getIdOrigine() != null) {
                final String param = "uidOrigine";
                requete.append(" and origine.decompte_origine_uid = :" + param);
                params.put(param, criteres.getIdOrigine());
            }
            if (criteres.getIdActes() != null && criteres.getIdActes().size() > 0) {
                final String param = "listeUidActe";
                requete.append(" and acte.decompte_acte_uid in (:" + param + ")");
                params.put(param, criteres.getIdActes());
            }
            if (criteres.getIdActesAExclure() != null && criteres.getIdActesAExclure().size() > 0) {
                final String param = "listeUidActe";
                requete.append(" and acte.decompte_acte_uid not in (:" + param + ")");
                params.put(param, criteres.getIdActesAExclure());
            }
        }
        if (groupBy) {
            requete.append(" group by d.decompte_numero, d.decompte_date_reglement, origine.decompte_origine_uid, origine.decompte_origine_lib, ");
            requete.append(" banque.banque_compte, banque.banque_domiciliation, d.decompte_destinataire_nom, d.decompte_benef_reglement_nom, ");
            requete.append(" d.decompte_reglement_professionnel_sante, d.decompte_reglement_numero_cheque, d.decompte_statut_paiement,");
            requete.append(" bs.personne_uid, bs.personne_nom, bs.personne_prenom");
        }
        if (orderBy) {
            query = createSqlQuery(requete.toString(), criteresPagines);
        } else {
            query = createSqlQuery(requete.toString());
        }
        if (criteres != null) {
            query.setProperties(params);
        }
        query.addSynchronizedEntityClass(Decompte.class);
        logger.debug(query.toString());
        return query;
    }

    /**
     * Convertit un TimeStamp en Calendar.
     * @param timestamp le timestamp
     * @return le calendar
     */
    private Calendar convertTimeStamp(Timestamp timestamp) {
    	if (timestamp == null) {
    		return null;
    	}
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        return calendar;
    }
}
