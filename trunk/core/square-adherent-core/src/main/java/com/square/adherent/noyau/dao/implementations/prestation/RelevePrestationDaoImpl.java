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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.prestation.RelevePrestationDao;
import com.square.adherent.noyau.dto.prestation.CritereSelectionRelevePrestationDto;
import com.square.adherent.noyau.model.data.prestation.RelevePrestation;
import com.square.adherent.noyau.model.dimension.RelevePrestationMoyenPaiement;

/**
 * Implémentation de RelevePrestationDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class RelevePrestationDaoImpl extends HibernateDaoBaseImplementation implements RelevePrestationDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<RelevePrestation> getListeReleveParCriteres(CritereSelectionRelevePrestationDto critereSelectionRelevePrestationDto, Boolean triDesc) {
        final StringBuffer requete = new StringBuffer("select distinct(relevePresta) from RelevePrestation relevePresta ");
        boolean existCritere = false;
        if (critereSelectionRelevePrestationDto.getIdPersonne() != null) {
            requete.append("where relevePresta.uidPersonne =:idPersonne ");
            existCritere = true;
        }
        if (critereSelectionRelevePrestationDto.getRelevePrestationId() != null) {
            requete.append(getConditionSeparator(existCritere)).append("relevePresta.id =:idRelevePresta ");
            existCritere = true;
        }
        if (critereSelectionRelevePrestationDto.getEnvoyeMail() != null) {
            requete.append(getConditionSeparator(existCritere)).append("relevePresta.envoyeMail =:envoyeMailParam ");
            existCritere = true;
        }
        if (critereSelectionRelevePrestationDto.getAutorisationEnvoiMail() != null) {
            requete.append(getConditionSeparator(existCritere)).append("relevePresta.moyenPaiement.autorisationEnvoiMail = :autorisationEnvoiMailParam ");
            existCritere = true;
        }
        if (critereSelectionRelevePrestationDto.getDateMaxImpression() != null) {
            requete.append(getConditionSeparator(existCritere)).append("relevePresta.dateImpression <= :dateMaxImpressionParam ");
            existCritere = true;
        }
        if (critereSelectionRelevePrestationDto.getDateMinImpression() != null) {
            requete.append(getConditionSeparator(existCritere)).append("relevePresta.dateImpression >= :dateMinImpressionParam ");
            existCritere = true;
        }
        if (triDesc != null && triDesc) {
            requete.append("order by relevePresta.dateImpression desc");
        } else if (triDesc != null && !triDesc) {
            requete.append("order by relevePresta.dateImpression asc");
        }

        final Query query = createQuery(requete.toString());
        query.setFirstResult(0);
        if (critereSelectionRelevePrestationDto.getNombreMax() != null) {
            query.setMaxResults(critereSelectionRelevePrestationDto.getNombreMax().intValue());
        }

        if (critereSelectionRelevePrestationDto.getIdPersonne() != null) {
            query.setLong("idPersonne", critereSelectionRelevePrestationDto.getIdPersonne());
        }
        if (critereSelectionRelevePrestationDto.getRelevePrestationId() != null) {
            query.setLong("idRelevePresta", critereSelectionRelevePrestationDto.getRelevePrestationId());
        }
        if (critereSelectionRelevePrestationDto.getEnvoyeMail() != null) {
            query.setBoolean("envoyeMailParam", critereSelectionRelevePrestationDto.getEnvoyeMail().booleanValue());
        }
        if (critereSelectionRelevePrestationDto.getAutorisationEnvoiMail() != null) {
            query.setBoolean("autorisationEnvoiMailParam", critereSelectionRelevePrestationDto.getAutorisationEnvoiMail().booleanValue());
        }
        if (critereSelectionRelevePrestationDto.getDateMaxImpression() != null) {
            query.setCalendar("dateMaxImpressionParam", critereSelectionRelevePrestationDto.getDateMaxImpression());
        }
        if (critereSelectionRelevePrestationDto.getDateMinImpression() != null) {
            query.setCalendar("dateMinImpressionParam", critereSelectionRelevePrestationDto.getDateMinImpression());
        }
        return (List<RelevePrestation>) query.list();
    }

    /**
     * {@inheritDoc}
     */
    public Integer getNombreCandidatsEnvoiRelevesPrestationEmail(int nbJoursDifferesEnvoiMail, Long idNatureConnexionEspaceAdherent,
        Long idOptionRelevePresta) {
        final StringBuffer requete = new StringBuffer("select count (distinct (releve_personne_uid)) ");
        completerRequeteEnvoiRelevesPrestationEmail(requete, nbJoursDifferesEnvoiMail, idNatureConnexionEspaceAdherent, idOptionRelevePresta);
        final Query query = createSqlQuery(requete.toString());
        final Calendar today = Calendar.getInstance();
        final int jour = today.get(Calendar.DAY_OF_MONTH) - nbJoursDifferesEnvoiMail;
        today.set(Calendar.DAY_OF_MONTH, jour);
        query.setCalendar("dateMaxImpressionParam", today);
        query.setLong("nature", idNatureConnexionEspaceAdherent);
        query.setLong("option", idOptionRelevePresta);
        return Integer.valueOf(query.uniqueResult().toString());
    }

    /**
     * Retourne le séparateur de condition adéquat suivant si il existe deja un critere ou pas.
     * @param existCritere flag
     * @return le séparateur
     */
    private String getConditionSeparator(boolean existCritere) {
        return existCritere ? "and " : "where ";
    }

    @Override
    public List<Long> getCandidatsEnvoiRelevesPrestationEmail(int firstResult, int maxResult, List<Long> listeIdsExclus, int nbJoursDifferesEnvoiMail,
        Long idNatureConnexionEspaceAdherent, Long idOptionRelevePresta) {
        final StringBuffer requete = new StringBuffer("select distinct (releve_personne_uid) ");
        completerRequeteEnvoiRelevesPrestationEmail(requete, nbJoursDifferesEnvoiMail, idNatureConnexionEspaceAdherent, idOptionRelevePresta);
        if (listeIdsExclus != null && listeIdsExclus.size() > 0) {
            requete.append(" and releve_personne_uid not in (:listeIdsExclus)");
        }
        final Query query = createSqlQuery(requete.toString());
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        final Calendar today = Calendar.getInstance();
        final int jour = today.get(Calendar.DAY_OF_MONTH) - nbJoursDifferesEnvoiMail;
        today.set(Calendar.DAY_OF_MONTH, jour);
        query.setCalendar("dateMaxImpressionParam", today);
        query.setLong("nature", idNatureConnexionEspaceAdherent);
        query.setLong("option", idOptionRelevePresta);
        if (listeIdsExclus != null && listeIdsExclus.size() > 0) {
            query.setParameterList("listeIdsExclus", listeIdsExclus);
        }
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

    private void completerRequeteEnvoiRelevesPrestationEmail(StringBuffer requete, int nbJoursDifferesEnvoiMail, Long idNatureConnexionEspaceAdherent,
        Long idOptionRelevePresta) {
        requete.append("from data_releve_prestation ")
        .append("inner join data_espace_client_internet on espace_client_internet_personne_uid = releve_personne_uid ")
        .append("and espace_client_internet_nature_uid = :nature and espace_client_internet_active = 'O' ")
        .append("inner join data_option on option_personne_uid = releve_personne_uid and option_type_uid = :option ")
        .append(" and option_top_actif = 'true' and option_date_debut <= releve_date_impression ")
        .append("left join dim_releve_prestation_moyen_paiement ")
        .append("on data_releve_prestation.releve_moyen_paiement_uid=dim_releve_prestation_moyen_paiement.releve_moyen_paiement_uid ")
        .append("join data_personnes_emails pe on espace_client_internet_personne_uid = pe.personne_uid ")
        .append("where releve_envoi_mail = 'false' and  releve_moyen_paiement_autorise_envoie_mail = 'true' ")
        .append("and releve_date_impression <= :dateMaxImpressionParam");
    }

    @Override
    public void createRelevePresta(RelevePrestation relevePrestation) {
        save(relevePrestation);
    }

    @Override
    public void deleteRelevePresta(RelevePrestation relevePrestation) {
        delete(relevePrestation);
    }

    @Override
    public boolean existe(String nomFichier) {
        final Criteria criteria = createCriteria(RelevePrestation.class);
        criteria.add(Restrictions.eq("nomFichier", nomFichier));
        return criteria.uniqueResult() != null;
    }

    @Override
    public RelevePrestationMoyenPaiement getMoyenPaiementByEid(String eid) {
        final Criteria criteria = createCriteria(RelevePrestationMoyenPaiement.class);
        criteria.add(Restrictions.ilike("identifiantExterieur", eid));
        return (RelevePrestationMoyenPaiement) criteria.uniqueResult();
    }
}
