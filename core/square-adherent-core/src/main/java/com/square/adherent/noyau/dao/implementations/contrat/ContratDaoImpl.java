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
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.bean.IdsBeneficiaireContrat;
import com.square.adherent.noyau.dao.interfaces.contrat.ContratDao;
import com.square.adherent.noyau.dto.adherent.contrat.ContratCollectifCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.EffectifStatutDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentDto;
import com.square.adherent.noyau.dto.adherent.contrat.RatioPrestationCotisationDto;
import com.square.adherent.noyau.dto.adherent.contrat.RecapitulatifPopulationDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurDto;
import com.square.adherent.noyau.dto.adherent.contrat.ValeursStatutsPopulationDto;
import com.square.adherent.noyau.model.data.contrat.AjustementTarif;
import com.square.adherent.noyau.model.data.contrat.Contrat;
import com.square.adherent.noyau.model.data.contrat.Ratio;
import com.square.adherent.noyau.model.data.contrat.SyntheseContrat;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;

/**
 * Implémentation de ContratDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratDaoImpl extends HibernateDaoBaseImplementation implements ContratDao {

    /** Service de mapping. */
    private AdherentMappingService adherentMappingService;

    /** Nombre de caractère pour extraire un identifiant de contrat. */
    private static final int NB_CARACTERE_ID_CONTRAT = 13;

    private static final String POURCENT = "%";

    @Override
    @SuppressWarnings("unchecked")
    // TODO manque la colonne contrat_adhesion_onum
    public Contrat getContratSantePersonneEnCours(Long idPersonne) {
        final StringBuffer requete = new StringBuffer("select contrat_numero from data_contrat ");
        requete.append("where data_contrat.contrat_assure_uid = :idPersonne ");
        requete.append("and (data_contrat.contrat_statut_uid = :idStatutEnCours ");
        requete.append("or (data_contrat.contrat_statut_uid = :idStatutResilie and data_contrat.contrat_date_resiliation >= now())) ");
        requete.append("and data_contrat.contrat_nature_uid = :idNatureSante ");
        // requete.append("and data_contrat.contrat_adhesion_onum <> 'groupe' ");
        requete.append("and data_contrat.contrat_date_adhesion <= now() ");
        requete.append("and data_contrat.contrat_visible = true ");
        requete.append("group by contrat_numero");
        final Query crit = createSqlQuery(requete.toString());
        crit.setLong("idPersonne", idPersonne);
        crit.setLong("idStatutEnCours", adherentMappingService.getIdStatutContratEnCours());
        crit.setLong("idStatutResilie", adherentMappingService.getIdStatutContratResilie());
        crit.setLong("idNatureSante", adherentMappingService.getIdNatureContratSante());
        final List<String> resultats = (ArrayList<String>) crit.list();
        return resultats.size() == 1 ? ((Contrat) getContratByNumero(resultats.get(0))) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Contrat getContratSantePersonneInactifXMois(Long idPersonne, Calendar dateInactiviteGarantieSante) {
        final StringBuffer requete = new StringBuffer("select contrat_numero from data_contrat ");
        requete.append("where data_contrat.contrat_assure_uid = :idPersonne ");
        requete.append("and data_contrat.contrat_statut_uid <> :idStatutEnCours and data_contrat.contrat_nature_uid = :idNatureSante ");
        // requete.append("and data_contrat.contrat_adhesion_onum <> 'groupe' ");
        requete.append("and data_contrat.contrat_date_resiliation < now() and data_contrat.contrat_date_resiliation >= :dateInactiviteGarantieSante ");
        requete.append("and data_contrat.contrat_visible = true ");
        requete.append("group by contrat_numero ");
        final Query crit = createSqlQuery(requete.toString());
        crit.setLong("idPersonne", idPersonne);
        crit.setLong("idStatutEnCours", adherentMappingService.getIdStatutContratEnCours());
        crit.setLong("idNatureSante", adherentMappingService.getIdNatureContratSante());
        crit.setCalendar("dateInactiviteGarantieSante", dateInactiviteGarantieSante);
        final List<String> resultats = (ArrayList<String>) crit.list();
        return resultats.size() == 1 ? ((Contrat) getContratByNumero(resultats.get(0))) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Contrat getContratSantePersonneAVenir(Long idPersonne) {
        final StringBuffer requete = new StringBuffer("select contrat_numero from data_contrat ");
        requete.append("where data_contrat.contrat_assure_uid = :idPersonne ");
        requete.append("and data_contrat.contrat_statut_uid = :idStatutFutur ");
        requete.append("and data_contrat.contrat_nature_uid = :idNatureSante ");
        // requete.append("and data_contrat.contrat_adhesion_onum <> 'groupe' ");
        requete.append("and data_contrat.contrat_date_adhesion > now() ");
        requete.append("and data_contrat.contrat_visible = true ");
        requete.append("group by contrat_numero");
        final Query crit = createSqlQuery(requete.toString());
        crit.setLong("idPersonne", idPersonne);
        crit.setLong("idStatutFutur", adherentMappingService.getIdStatutContratFutur());
        crit.setLong("idNatureSante", adherentMappingService.getIdNatureContratSante());
        final List<String> resultats = (ArrayList<String>) crit.list();
        return resultats.size() == 1 ? ((Contrat) getContratByNumero(resultats.get(0))) : null;
    }

    @Override
    public Contrat getContratById(Long uidContrat) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.add(Restrictions.eq("id", uidContrat));
        crit.add(Restrictions.eq("isVisible", true));
        return (Contrat) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GarantieBeneficiaireDto> getListeBeneficiairesFromContrats(List<Long> listeUidContrats, Long uidAssure, boolean filtrerContratEnCOurs) {
        final StringBuffer requete = new StringBuffer("select uidBeneficiaire, contrat.id, role.id, role.libelle ");
        requete.append("from Garantie where ");
        if (listeUidContrats != null && listeUidContrats.size() > 0) {
            requete.append("contrat.id in (:listeUidContrats) and ");
        }
        if (filtrerContratEnCOurs) {
            requete.append("statut.id = :idStatutGarantieEnCours and ");
        }
        requete.append("contrat.isVisible = true and isVisible = true and ");
        requete.append("uidAssure = :uidAssure order by statut.ordre, role.ordre");
        final Query query = createQuery(requete.toString());
        if (listeUidContrats != null && listeUidContrats.size() > 0) {
            query.setParameterList("listeUidContrats", listeUidContrats);
        }
        if (filtrerContratEnCOurs) {
            query.setLong("idStatutGarantieEnCours", adherentMappingService.getIdStatutGarantieEnCours());
        }

        query.setLong("uidAssure", uidAssure);
        final List<Object[]> listResult = query.list();
        final List<GarantieBeneficiaireDto> listeBeneficiaires = new ArrayList<GarantieBeneficiaireDto>();
        final List<IdsBeneficiaireContrat> listeIdsBenefsContrats = new ArrayList<IdsBeneficiaireContrat>();
        if (listResult != null && listResult.size() > 0) {
            for (Object[] result : listResult) {
                final Long idBenef = (Long) result[0];
                final Long idContrat = (Long) result[1];
                final IdsBeneficiaireContrat idBenefContrat = new IdsBeneficiaireContrat();
                idBenefContrat.setIdBenef(idBenef);
                idBenefContrat.setIdContrat(idContrat);
                if (!listeIdsBenefsContrats.contains(idBenefContrat)) {
                    final GarantieBeneficiaireDto benef = new GarantieBeneficiaireDto();
                    benef.setIdBenef(idBenef);
                    benef.setIdContrat(idContrat);
                    benef.setRole(new IdentifiantLibelleDto((Long) result[2], String.valueOf(result[3])));
                    listeBeneficiaires.add(benef);
                    listeIdsBenefsContrats.add(idBenefContrat);
                }
            }
        }
        return listeBeneficiaires;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contrat> getContratsByCriteres(CritereRechercheContratDto criteres) {
        final Criteria crit = createCriteriaByCriteres(criteres);
        crit.addOrder(Order.asc("statut.ordre"));
        return crit.list();
    }

    private Criteria createCriteriaByCriteres(CritereRechercheContratDto criteres) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.createAlias("statut", "statut");
        if (criteres != null && criteres.getIdAssure() != null) {
            crit.add(Restrictions.eq("uidAssure", criteres.getIdAssure()));
        }
        if (criteres != null && criteres.getIdSouscripteur() != null) {
            crit.add(Restrictions.eq("uidSouscripteur", criteres.getIdSouscripteur()));
        }
        if (criteres != null && criteres.isIdAssureNull()) {
            crit.add(Restrictions.isNull("uidAssure"));
        }
        if (criteres != null && StringUtils.isNotBlank(criteres.getNumeroContrat())) {
            crit.add(Restrictions.ilike("numeroContrat", criteres.getNumeroContrat() + "%"));
        }
        if (criteres != null && criteres.getHasContratEnCours() != null && criteres.getHasContratEnCours()) {
            crit.add(Restrictions.eq("statut.id", adherentMappingService.getIdStatutContratEnCours()));
        }
        if (criteres != null && criteres.getContratEid() != null && StringUtils.isNotBlank(criteres.getContratEid())) {
            crit.add(Restrictions.like("identifiantExterieur", "%" + criteres.getContratEid() + "%"));
        }
        crit.add(Restrictions.eq("isVisible", true));
        return crit;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Contrat getDernierContratPersonneByNatureContrat(Long uidPersonne, Long uidNatureContrat, Long uidStatutContrat) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.add(Restrictions.eq("uidAssure", uidPersonne));
        crit.createAlias("nature", "nature");
        crit.add(Restrictions.eq("nature.id", uidNatureContrat));
        if (uidStatutContrat != null) {
            crit.add(Restrictions.eq("statut.id", uidStatutContrat));
        }
        crit.add(Restrictions.eq("isVisible", true));
        crit.addOrder(Order.desc("dateEffet"));
        final List<Contrat> listeConstrats = crit.list();
        if (listeConstrats != null && listeConstrats.size() > 0) {
            return listeConstrats.get(0);
        } else {
            return null;
        }
    }

    @Override
    public SyntheseContrat getSyntheseContrat(Long uidPersonne) {
        final Criteria crit = createCriteria(SyntheseContrat.class);
        crit.add(Restrictions.eq("uidPersonne", uidPersonne));
        return (SyntheseContrat) crit.uniqueResult();
    }

    @Override
    public List<RatioPrestationCotisationDto> getRatioPrestationCotisationPersonne(Long uidPersonne) {
        final Criteria crit = createCriteria(Ratio.class);
        final ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("ratioPrestaSurCotis"));
        projectionList.add(Projections.property("annee"));
        crit.setProjection(projectionList);
        crit.add(Restrictions.eq("uidPersonne", uidPersonne));
        final List result = crit.list();

        final Iterator iterator = result.iterator();

        if (result == null) {
            return null;
        } else {
            double ratio = -1;
            int annee = -1;

            final List<RatioPrestationCotisationDto> listRatioDto = new ArrayList<RatioPrestationCotisationDto>();

            while (iterator.hasNext()) {
                final Object[] objects = (Object[]) iterator.next();

                ratio = ((Double) objects[0]).doubleValue();
                annee = ((Integer) objects[1]).intValue();

                final RatioPrestationCotisationDto ratioDto = new RatioPrestationCotisationDto();
                ratioDto.setAnnee(annee);
                ratioDto.setRatioPrestationCotisation(ratio);
                final IdentifiantLibelleDto personneDto = new IdentifiantLibelleDto(uidPersonne);
                ratioDto.setPersonne(personneDto);

                listRatioDto.add(ratioDto);
            }

            return listRatioDto;
        }
    }

    /**
     * Récupère un contrat par son numéro.
     * @param numeroContrat le numéro du contrat
     * @return le contrat demandé
     */
    private Contrat getContratByNumero(String numeroContrat) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.add(Restrictions.eq("numeroContrat", numeroContrat));
        crit.add(Restrictions.eq("isVisible", true));
        return (Contrat) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AjustementTarif> getListeAjustementsFromContrat(Long uidContrat) {
        final Criteria crit = createCriteria(AjustementTarif.class);
        crit.createAlias("contrat", "contrat");
        crit.add(Restrictions.eq("contrat.id", uidContrat));
        crit.add(Restrictions.eq("contrat.isVisible", true));
        crit.add(Restrictions.or(Restrictions.ltProperty("dateDebut", "dateFin"), Restrictions.isNull("dateFin")));
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Contrat getDernierContratRadiePersonne(Long uidPersonne) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.add(Restrictions.eq("uidAssure", uidPersonne));
        crit.add(Restrictions.isNotNull("dateResiliation"));
        crit.add(Restrictions.eq("isVisible", true));
        crit.addOrder(Order.desc("dateResiliation"));
        final List<Contrat> listeConstrats = crit.list();
        if (listeConstrats != null && listeConstrats.size() > 0) {
            return listeConstrats.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getGestionnaireContratCollectif(Long uidPersonne) {
        final StringBuffer requete =
            new StringBuffer("select a.contrat_gestionnaire from data_contrat a ").append(
                "join dim_contrat_nature b on a.contrat_nature_uid = b.contrat_nature_uid ").append(
                "join data_personne_physique c on c.personne_uid = a.contrat_assure_uid and c.personne_segment_uid = 3 ").append("inner join (").append(
                "select contrat_assure_uid,max(contrat_nature_eid || ").append(
                "cast(contrat_date_adhesion as text)||cast(coalesce(contrat_date_resiliation,'2222/01/01') as text)) ").append(
                "ID from data_contrat, dim_contrat_nature ").append("where data_contrat.contrat_nature_uid = dim_contrat_nature.contrat_nature_uid ").append(
                "and contrat_segment_uid = 3 ").append("group by contrat_assure_uid) contrat_coll_unique_assure ").append(
                "on a.contrat_assure_uid = contrat_coll_unique_assure.contrat_assure_uid ").append(
                "and b.contrat_nature_eid ||cast(a.contrat_date_adhesion as text) ").append(
                " || cast(coalesce(a.contrat_date_resiliation,'2222/01/01') as text) = contrat_coll_unique_assure.ID ")
                    .append("and a.contrat_segment_uid = 3 ").append("and a.contrat_assure_uid= :uidPersonne");
        final Query crit = createSqlQuery(requete.toString());
        crit.setLong("uidPersonne", uidPersonne);
        final List<String> resultats = (ArrayList<String>) crit.list();
        return resultats.size() >= 1 ? resultats.get(0) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PopulationDto> getListePopulations(Long uidPersonneMorale) {
        final StringBuffer requete =
            new StringBuffer("select population, count(distinct uidAssure) from Contrat ").append("where uidSouscripteur = :uidPersonneMorale ").append(
                "and uidAssure is not null ").append("and statut.id = :idStatutEnCours ").append("and nature.id = :idNatureSante ").append(
                "and isVisible = true ").append("group by population");
        final Query query = createQuery(requete.toString());
        query.setLong("uidPersonneMorale", uidPersonneMorale);
        query.setLong("idStatutEnCours", adherentMappingService.getIdStatutContratEnCours());
        query.setLong("idNatureSante", adherentMappingService.getIdNatureContratSante());

        final List<Object[]> listResult = query.list();
        final List<PopulationDto> listePopulations = new ArrayList<PopulationDto>();
        if (listResult != null && listResult.size() > 0) {
            for (Object[] result : listResult) {
                final String libellePopulation = (String) result[0];
                final Integer effectif = Integer.valueOf((String) result[1].toString());
                final PopulationDto population = new PopulationDto();
                population.setLibelle(libellePopulation);
                population.setEffectif(effectif);
                listePopulations.add(population);
            }
        }
        return listePopulations;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Contrat> getListeContratsPersonneMorale(Long uidPersonneMorale) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.createAlias("statut", "statut");
        crit.createAlias("nature", "nature");
        crit.add(Restrictions.eq("uidSouscripteur", uidPersonneMorale));
        crit.add(Restrictions.isNull("uidAssure"));
        crit.add(Restrictions.eq("isVisible", true));
        crit.addOrder(Order.asc("statut.ordre"));
        crit.addOrder(Order.asc("nature.ordre"));
        crit.addOrder(Order.desc("dateSignature"));
        return crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getListeProduitsGestionFromContrat(String eidContrat) {
        final StringBuffer requete =
            new StringBuffer("select distinct libelleProduitGestion from Garantie ").append("where contrat.identifiantExterieur like :idContrat ").append(
                "and contrat.uidAssure is not null ").append("and isVisible = true");
        final Query query = createQuery(requete.toString());
        query.setString("idContrat", eidContrat.substring(0, NB_CARACTERE_ID_CONTRAT) + POURCENT);
        return query.list();
    }

    @Override
    public Integer getNombreAdherentsContrat(String eidContrat) {
        final StringBuffer requete =
            new StringBuffer("select count(distinct uidAssure) from Garantie ").append("where contrat.identifiantExterieur like :idContrat ").append(
                "and contrat.uidAssure is not null ").append("and contrat.statut.id = :idStatutContratEnCours ").append(
                "and statut.id = :idStatutGarantieEnCours ").append("and isVisible = true");
        final Query query = createQuery(requete.toString());
        query.setString("idContrat", eidContrat.substring(0, NB_CARACTERE_ID_CONTRAT) + POURCENT);
        query.setLong("idStatutContratEnCours", adherentMappingService.getIdStatutContratEnCours());
        query.setLong("idStatutGarantieEnCours", adherentMappingService.getIdStatutGarantieEnCours());
        final Integer nbAdherents = Integer.valueOf(query.uniqueResult().toString());
        return nbAdherents;
    }

    @Override
    public Integer getNombreBeneficiairesContrat(String eidContrat) {
        final StringBuffer requete =
            new StringBuffer("select count(distinct uidBeneficiaire) from Garantie ").append("where contrat.identifiantExterieur like :idContrat ").append(
                "and contrat.uidAssure is not null ").append("and uidAssure != uidBeneficiaire ").append("and contrat.statut.id = :idStatutContratEnCours ")
                    .append("and statut.id = :idStatutGarantieEnCours ").append("and isVisible = true ");
        final Query query = createQuery(requete.toString());
        query.setString("idContrat", eidContrat.substring(0, NB_CARACTERE_ID_CONTRAT) + POURCENT);
        query.setLong("idStatutContratEnCours", adherentMappingService.getIdStatutContratEnCours());
        query.setLong("idStatutGarantieEnCours", adherentMappingService.getIdStatutGarantieEnCours());
        final Integer nbAdherents = Integer.valueOf(query.uniqueResult().toString());
        return nbAdherents;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RecapitulatifPopulationDto getRecapitulatifPopulationContrat(String eidContrat) {
        final RecapitulatifPopulationDto recapitulatif = new RecapitulatifPopulationDto();

        // Récupération de la liste des statuts
        final StringBuffer requeteStatuts =
            new StringBuffer("select distinct statut.id, statut.libelle, statut.ordre from Contrat ").append("where identifiantExterieur like :idContrat ")
                    .append("and uidAssure is not null ").append("and isVisible = true ").append("group by statut.id, statut.libelle, statut.ordre ").append(
                        "order by statut.ordre asc ");
        final Query queryStatuts = createQuery(requeteStatuts.toString());
        queryStatuts.setString("idContrat", eidContrat.substring(0, NB_CARACTERE_ID_CONTRAT) + POURCENT);
        final List<Object[]> listResultStatuts = queryStatuts.list();
        final List<IdentifiantLibelleDto> listeStatuts = new ArrayList<IdentifiantLibelleDto>();
        if (listResultStatuts != null && listResultStatuts.size() > 0) {
            for (Object[] result : listResultStatuts) {
                final Long idStatut = (Long) result[0];
                final String libelleStatut = (String) result[1];
                listeStatuts.add(new IdentifiantLibelleDto(idStatut, libelleStatut));
            }
        }
        recapitulatif.setListeStatuts(listeStatuts);

        // Récupération des effectifs pour chaque population et chaque statut
        final StringBuffer requete =
            new StringBuffer("select population, statut.id, count(distinct uidAssure) from Contrat ").append("where identifiantExterieur like :idContrat ")
                    .append("and uidAssure is not null ").append("and isVisible = true ").append("group by population, statut.id, statut.ordre ").append(
                        "order by statut.ordre asc");
        final Query query = createQuery(requete.toString());
        query.setString("idContrat", eidContrat.substring(0, NB_CARACTERE_ID_CONTRAT) + POURCENT);

        final List<Object[]> listResult = query.list();
        final List<ValeursStatutsPopulationDto> listeValeursPopulation = new ArrayList<ValeursStatutsPopulationDto>();
        if (listResult != null && listResult.size() > 0) {
            for (Object[] result : listResult) {
                final String libellePopulation = (String) result[0];
                final Long idStatut = (Long) result[1];
                final Integer effectif = Integer.valueOf((String) result[2].toString());
                ValeursStatutsPopulationDto valeurStatutpopulationTrouve = isPopulationPresenteDansListe(listeValeursPopulation, libellePopulation);
                if (valeurStatutpopulationTrouve != null) {
                    for (EffectifStatutDto effectifStatutDto : valeurStatutpopulationTrouve.getListeEffectifsParStatut()) {
                        if (idStatut.equals(effectifStatutDto.getIdStatut())) {
                            effectifStatutDto.setEffectif(effectif);
                            break;
                        }
                    }
                } else {
                    valeurStatutpopulationTrouve = new ValeursStatutsPopulationDto();
                    valeurStatutpopulationTrouve.setLibellePopulation(libellePopulation);
                    final List<EffectifStatutDto> listeEffectifsStatutsDto = new ArrayList<EffectifStatutDto>();
                    for (IdentifiantLibelleDto statutDto : listeStatuts) {
                        final EffectifStatutDto effectifStatutDto = new EffectifStatutDto();
                        effectifStatutDto.setIdStatut(statutDto.getIdentifiant());
                        if (idStatut.equals(statutDto.getIdentifiant())) {
                            effectifStatutDto.setEffectif(effectif);
                        }
                        listeEffectifsStatutsDto.add(effectifStatutDto);
                    }
                    valeurStatutpopulationTrouve.setListeEffectifsParStatut(listeEffectifsStatutsDto);
                    listeValeursPopulation.add(valeurStatutpopulationTrouve);
                }
            }
        }
        recapitulatif.setListeValeursPopulation(listeValeursPopulation);

        return recapitulatif;
    }

    /**
     * Détecte si un libellé de population est déjà présent dans la liste. Si c'est le cas, on retourne l'objet de cette population.
     * @param listeValeursPopulation la liste.
     * @param libellePopulation le libellé de la population.
     * @return l'objet de la population si il existe.
     */
    private ValeursStatutsPopulationDto isPopulationPresenteDansListe(List<ValeursStatutsPopulationDto> listeValeursPopulation, String libellePopulation) {
        if (libellePopulation != null && listeValeursPopulation != null && listeValeursPopulation.size() > 0) {
            for (ValeursStatutsPopulationDto valeurPopulation : listeValeursPopulation) {
                if (libellePopulation.equals(valeurPopulation.getLibellePopulation())) {
                    return valeurPopulation;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Contrat getDernierContratRadiePersonneMorale(Long uidPersonneMorale) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.add(Restrictions.eq("uidSouscripteur", uidPersonneMorale));
        crit.add(Restrictions.isNull("uidAssure"));
        crit.add(Restrictions.isNotNull("dateResiliation"));
        crit.add(Restrictions.eq("isVisible", true));
        crit.addOrder(Order.desc("dateResiliation"));
        final List<Contrat> listeConstrats = crit.list();
        if (listeConstrats != null && listeConstrats.size() > 0) {
            return listeConstrats.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Contrat getDernierContratPersonneMoraleByCriteres(Long uidPersonneMorale, Long uidNatureContrat, Long uidStatutContrat) {
        final Criteria crit = createCriteria(Contrat.class);
        crit.add(Restrictions.eq("uidSouscripteur", uidPersonneMorale));
        crit.add(Restrictions.isNull("uidAssure"));
        crit.createAlias("nature", "nature");
        crit.createAlias("statut", "statut");
        crit.add(Restrictions.eq("nature.id", uidNatureContrat));
        crit.add(Restrictions.eq("statut.id", uidStatutContrat));
        crit.add(Restrictions.eq("isVisible", true));
        crit.addOrder(Order.desc("dateEffet"));
        final List<Contrat> listeConstrats = crit.list();
        if (listeConstrats != null && listeConstrats.size() > 0) {
            return listeConstrats.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Boolean hasPersonneContrats(Long idPersonne) {
        final StringBuffer requete = new StringBuffer("select count(id) from Contrat ").append("where uidAssure = :idPersonne ").append("and isVisible = true");
        final Query query = createQuery(requete.toString());
        query.setLong("idPersonne", idPersonne);
        final Integer nbContrats = Integer.valueOf(query.uniqueResult().toString());
        return nbContrats > 0;
    }

    @Override
    public int countContratsByCriteres(CritereRechercheContratDto criteres) {
        final Criteria crit = createCriteriaByCriteres(criteres);
        crit.setProjection(Projections.countDistinct("id"));
        return Integer.valueOf(((Long) crit.uniqueResult()).toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getListeContratsCollectifsByCriteres(ContratCollectifCriteresDto criteres) {
        // Création du critère
        final Criteria crit = createCriteria(Contrat.class);
        // Projection sur la colonne contrat
        crit.setProjection(Projections.groupProperty("numeroContrat"));
        // Critères de recherche
        if (criteres.getUidEntreprise() != null) {
            crit.add(Restrictions.eq("uidSouscripteur", criteres.getUidEntreprise()));
        }
        crit.add(Restrictions.ilike("identifiantExterieur", "%groupe%"));
        // Tri sur le contrat
        crit.addOrder(Order.asc("numeroContrat"));
        return (ArrayList<String>) crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getListePopulationsByCriteres(PopulationCriteresDto criteres) {
        final StringBuffer requete = new StringBuffer("select distinct g.libellePopulation from Contrat cg, Garantie g ");
        requete.append("where cg.id = g.contrat ");
        if (criteres.getContrat() != null) {
            requete.append("and cg.numeroContrat = :numeroContrat ");
        }
        requete.append("order by g.libellePopulation ");
        final Query query = createQuery(requete.toString());
        if (criteres.getContrat() != null) {
            query.setString("numeroContrat", criteres.getContrat());
        }
        return (ArrayList<String>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProduitCollectifAdherentDto> getListeProduitsCollectifsAdherent(ProduitCollectifAdherentCriteresDto criteres, Long idRoleGarantieAssure,
        Long idStatutGarantieEnCours, Long idStatutGarantieFutur) {
        final StringBuffer requete = new StringBuffer("select g.libelleProduitGestion, g.libelleGarantieGestion, g.codeTarif, g.montantSouscrit ");
        requete.append(", CAST(sum(CASE g.statut.id WHEN :idStatutGarantieEnCours THEN 1 ELSE 0 END) as integer) ");
        requete.append("from Contrat cg, Garantie g, Contrat cp ");
        requete.append("where substring(cg.identifiantExterieur,1,13) = substring(cp.identifiantExterieur,1,13) ");
        requete.append("and g.contrat.id = cp.id ");
        requete.append("and g.role.id = :idRoleGarantieAssure ");
        requete.append("and g.statut.id in (:idStatutGarantieEnCours, :idStatutGarantieFutur) ");
        if (criteres.getPopulation() != null) {
            requete.append("and g.libellePopulation = :population ");
        }
        if (criteres.getContrat() != null) {
            requete.append("and cg.numeroContrat = :numeroContrat ");
        }
        if (criteres.getUidEntreprise() != null) {
            requete.append("and cg.uidSouscripteur = :uidSouscripteur ");
        }
        if (criteres.getDateEffet() != null) {
            requete.append(" and g.dateAdhesion <= :dateEffet");
            requete.append(" and (g.dateResiliation is null or g.dateResiliation >= :dateEffet)");
        }
        requete.append("group by g.libelleProduitGestion, g.libelleGarantieGestion, g.codeTarif, g.montantSouscrit ");

        final Query query = createQuery(requete.toString());
        query.setLong("idRoleGarantieAssure", idRoleGarantieAssure);
        query.setLong("idStatutGarantieEnCours", idStatutGarantieEnCours);
        query.setLong("idStatutGarantieFutur", idStatutGarantieFutur);
        if (criteres.getUidEntreprise() != null) {
            query.setLong("uidSouscripteur", criteres.getUidEntreprise());
        }
        if (criteres.getContrat() != null) {
            query.setString("numeroContrat", criteres.getContrat());
        }
        if (criteres.getPopulation() != null) {
            query.setString("population", criteres.getPopulation());
        }
        if (criteres.getDateEffet() != null) {
            query.setCalendar("dateEffet", criteres.getDateEffet());
        }

        final List<Object[]> rows = query.list();
        final List<ProduitCollectifAdherentDto> liste = new ArrayList<ProduitCollectifAdherentDto>();
        for (Object[] row : rows) {
            liste.add(new ProduitCollectifAdherentDto((String) row[0], (String) row[1], (String) row[2], (Double) row[3], (Integer) row[4]));
        }
        return liste;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TypePayeurDto> getListeTypesPayeurs(TypePayeurCriteresDto criteres, Long idRoleGarantieAssure) {
        final StringBuffer requete = new StringBuffer("select p.id, p.eid, p.libelle, b.bareme1Eid, b.bareme1Zone, b.bareme2Eid, b.bareme2Zone ");
        requete.append("from Contrat cg, Contrat cp, Garantie g, GarantieBareme b, TypePayeur p ");
        requete.append("where substring(cg.identifiantExterieur,1,13) = substring(cp.identifiantExterieur,1,13) ");
        requete.append("and g.contrat.id = cp.id ");
        requete.append("and g.id = b.garantie.id ");
        requete.append("and p.id = b.typePayeur.id ");
        requete.append("and g.role.id = :idRoleGarantieAssure ");
        if (criteres.getGarantieGestion() != null) {
            requete.append("and g.libelleGarantieGestion = :garantieGestion ");
        }
        if (criteres.getProduitGestion() != null) {
            requete.append("and g.libelleProduitGestion = :produitGestion ");
        }
        if (criteres.getContrat() != null) {
            requete.append("and cg.numeroContrat = :numeroContrat ");
        }
        if (criteres.getCodeTarif() != null) {
            requete.append("and g.codeTarif = :codeTarif ");
        }
        if (criteres.getMontantSouscrit() != null) {
            requete.append("and g.montantSouscrit = :montantSouscrit ");
        }
        if (criteres.getLibPopulation() != null) {
            requete.append("and g.libellePopulation = :libPopulation ");
        }
        requete.append("group by p.id, p.eid, p.libelle, b.bareme1Eid, b.bareme1Zone, b.bareme2Eid, b.bareme2Zone ");

        final Query query = createQuery(requete.toString());
        query.setLong("idRoleGarantieAssure", idRoleGarantieAssure);
        if (criteres.getGarantieGestion() != null) {
            query.setString("garantieGestion", criteres.getGarantieGestion());
        }
        if (criteres.getProduitGestion() != null) {
            query.setString("produitGestion", criteres.getProduitGestion());
        }
        if (criteres.getContrat() != null) {
            query.setString("numeroContrat", criteres.getContrat());
        }
        if (criteres.getCodeTarif() != null) {
            query.setString("codeTarif", criteres.getCodeTarif());
        }
        if (criteres.getMontantSouscrit() != null) {
            query.setDouble("montantSouscrit", criteres.getMontantSouscrit());
        }
        if (criteres.getLibPopulation() != null) {
            query.setString("libPopulation", criteres.getLibPopulation());
        }

        final List<Object[]> rows = query.list();
        final List<TypePayeurDto> liste = new ArrayList<TypePayeurDto>();
        for (Object[] row : rows) {
            liste.add(new TypePayeurDto((Long) row[0], (String) row[1], (String) row[2], (String) row[3], (Integer) row[4], (String) row[5], (Integer) row[6]));
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
