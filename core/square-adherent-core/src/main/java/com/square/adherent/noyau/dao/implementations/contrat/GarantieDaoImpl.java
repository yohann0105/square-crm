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

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.adherent.noyau.dao.interfaces.contrat.GarantieDao;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheGarantieDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosBanqueDto;
import com.square.adherent.noyau.dto.adherent.prestations.CoordonneesBancairesRemboursementDto;
import com.square.adherent.noyau.dto.produit.CriteresInfosProduitsDto;
import com.square.adherent.noyau.dto.produit.InfosProduitDto;
import com.square.adherent.noyau.model.data.contrat.Garantie;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;

/**
 * Implémentation de GarantieDao.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class GarantieDaoImpl extends HibernateDaoBaseImplementation implements GarantieDao {

    /** Service de mapping. */
    private AdherentMappingService adherentMappingService;

    @Override
    @SuppressWarnings("unchecked")
    // TODO manque la table data_provenance
    public List<InfosProduitDto> getInfosProduits(CriteresInfosProduitsDto criteres) {
        final StringBuffer requete =
            new StringBuffer("select garantie_beneficiaire_uid, ")
                    .append("data_garantie.garantie_role_uid, garantie_role_lib, garantie_produit_gestion, garantie_libelle_gestion, ")
                    .append("contrat_frequence_paiement_lib, contrat_moyen_paiement_lib, ")
                    .append("garantie_date_adhesion, garantie_date_resiliation, data_garantie.garantie_famille_uid, data_garantie.garantie_segment_uid, ")
                    .append("garantie_famille_ordre ")
                    .append("from data_garantie inner join data_contrat on contrat_uid = garantie_contrat_uid ")
                    .append("left join dim_garantie_role on data_garantie.garantie_role_uid = dim_garantie_role.garantie_role_uid ")
                    .append("left join dim_garantie_famille on data_garantie.garantie_famille_uid = dim_garantie_famille.garantie_famille_uid ")
                    .append("left join dim_contrat_moyen_paiement on data_contrat.contrat_moyen_paiement_cotis_uid=")
                    .append("dim_contrat_moyen_paiement.contrat_moyen_paiement_uid ")
                    .append("left join dim_contrat_frequence_paiement on ")
                    .append("data_contrat.contrat_frequence_paiement_cotis_uid=dim_contrat_frequence_paiement.contrat_frequence_paiement_uid ")
                    .append("where data_garantie.garantie_statut_uid = :idStatutGarantieEnCours and data_garantie.garantie_visible = true ");

        if (criteres.getIdContrat() != null) {
            requete.append(" and contrat_uid = :paramContratUid");
        }
        if (criteres.getIdAssure() != null) {
            requete.append(" and contrat_assure_uid = :paramAssureUid");
        }
        if (criteres.getIdPersonne() != null) {
            requete.append(" and garantie_beneficiaire_uid = :paramPersonneUid");
        }
        if (criteres.getIdFamille() != null) {
            requete.append(" and garantie_famille_uid >= :paramFamilleUid");
        }
        if (criteres.getDateDebutDeGarantie() != null) {
            requete.append(" and garantie_date_adhesion >= :paramDateDebut");
        }
        if (criteres.isHorsProduitsBonus()) {
            requete.append(" and data_garantie.garantie_famille_uid <> :idFamilleGarantieBonus");
        }
        if (criteres.isFiltreNullSurDateDeFin()) {
            requete.append(" and (garantie_date_resiliation is null or garantie_date_resiliation >= now())");
            requete.append(" and garantie_date_adhesion <= now()");
        }
        else if (criteres.getDateDeFinDeGarantie() != null) {
            requete.append(" and garantie_date_adhesion <= :paramDateFin");
        }
        requete.append(" order by garantie_role_lib, garantie_famille_ordre");

        final Query query = createSqlQuery(requete.toString());
        if (criteres.getIdContrat() != null) {
            query.setLong("paramContratUid", criteres.getIdContrat());
        }
        if (criteres.getIdAssure() != null) {
            query.setLong("paramAssureUid", criteres.getIdAssure());
        }
        if (criteres.getIdPersonne() != null) {
            query.setLong("paramPersonneUid", criteres.getIdPersonne());
        }
        if (criteres.getIdFamille() != null) {
            query.setLong("paramFamilleUid", criteres.getIdFamille());
        }
        if (criteres.getDateDebutDeGarantie() != null) {
            query.setCalendar("paramDateDebut", criteres.getDateDebutDeGarantie());
        }
        if (!criteres.isFiltreNullSurDateDeFin() && criteres.getDateDeFinDeGarantie() != null) {
            query.setCalendar("paramDateFin", criteres.getDateDeFinDeGarantie());
        }
        query.setLong("idStatutGarantieEnCours", adherentMappingService.getIdStatutGarantieEnCours());
        if (criteres.isHorsProduitsBonus()) {
            query.setLong("idFamilleGarantieBonus", adherentMappingService.getIdFamilleGarantieBonus());
        }

        final List<Object[]> resultats = (ArrayList<Object[]>) query.list();
        final List<InfosProduitDto> listeInfosProduit = new ArrayList<InfosProduitDto>();
        for (int i = 0; i < resultats.size(); i++) {
            final Object[] res = (Object[]) resultats.get(i);
            // Mapping d'un résultat dans le dto associé.
            int j = 0;
            final InfosProduitDto infoProduit = new InfosProduitDto();
            final Object idPersonne = res[j++];
            infoProduit.setIdPersonne(idPersonne != null ? Long.valueOf(idPersonne.toString()) : null);
            final Object idRole = res[j++];
            infoProduit.setIdRole(idRole != null ? Long.valueOf(idRole.toString()) : null);
            // On saute le libellé de rôle
            j++;
            infoProduit.setProduitAia((String) res[j++]);
            infoProduit.setGarantieAia((String) res[j++]);
            infoProduit.setFrequenceReglement((String) res[j++]);
            infoProduit.setModeReglement((String) res[j++]);
            final Calendar dateEffet = Calendar.getInstance();
            dateEffet.setTimeInMillis(((Timestamp) res[j++]).getTime());
            infoProduit.setDateEffet(dateEffet);
            final Calendar dateResiliation = Calendar.getInstance();
            final Object objDateResiliation = res[j++];
            if (objDateResiliation != null) {
                dateResiliation.setTimeInMillis(((Timestamp) objDateResiliation).getTime());
                infoProduit.setDateResiliation(dateResiliation);
            }
            final Object idFamilleGarantie = res[j++];
            infoProduit.setIdFamilleGarantie(idFamilleGarantie != null ? Long.valueOf(idFamilleGarantie.toString()) : null);
            final Object idSegmentGarantieObj = res[j++];
            final Long idSegmentGarantie = idSegmentGarantieObj != null ? Long.valueOf(idSegmentGarantieObj.toString()) : null;
            final boolean isFromContratCollectif = adherentMappingService.getIdSegmentCollectif().equals(idSegmentGarantie)
                                                || adherentMappingService.getIdSegmentCollectifIndividualise().equals(idSegmentGarantie);
            infoProduit.setIsFromContratCollectif(isFromContratCollectif);
            final Object ordre = res[j++];
            infoProduit.setOrdre(ordre != null ? Integer.valueOf(ordre.toString()) : null);
            listeInfosProduit.add(infoProduit);
        }
        return listeInfosProduit;
    }

    @Override
    public boolean isPersonneAssocieeAContrat(Long idPersonne) {
        final Criteria crit = createCriteria(Garantie.class);
        crit.setProjection(Projections.rowCount());
        crit.createAlias("statut", "statut");
        crit.add(Restrictions.or(Restrictions.eq("uidAssure", idPersonne), Restrictions.eq("uidBeneficiaire", idPersonne)));
        crit.add(Restrictions.eq("isVisible", true));
        crit.add(Restrictions.or(Restrictions.eq("statut.id", adherentMappingService.getIdStatutGarantieEnCours()), Restrictions.eq("statut.id",
            adherentMappingService.getIdStatutGarantieFutur())));
        final Integer nbGaranties = Integer.valueOf(crit.uniqueResult().toString());
        return nbGaranties > 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Garantie> getListeGarantiesByCriteres(CritereRechercheGarantieDto criteres) {
        final Criteria crit = createCriteria(Garantie.class);
        crit.createAlias("contrat", "contrat");
        crit.createAlias("famille", "famille");
        crit.createAlias("statut", "statut");
        if (criteres.getListeIdsContrat() != null && criteres.getListeIdsContrat().size() > 0) {
            crit.add(Restrictions.in("contrat.id", criteres.getListeIdsContrat()));
        }
        if (criteres.getHasGarantieEnCours() != null && criteres.getHasGarantieEnCours()) {
            crit.add(Restrictions.eq("statut.id", adherentMappingService.getIdStatutGarantieEnCours()));
        }
        if (criteres.getIdStatutGarantie() != null) {
            crit.add(Restrictions.eq("statut.id", criteres.getIdStatutGarantie()));
        }
        if (criteres.getEid() != null) {
            crit.add(Restrictions.eq("eid", criteres.getEid()));
        }
        crit.add(Restrictions.eq("isVisible", true));
        crit.addOrder(Order.asc("statut.ordre"));
        crit.addOrder(Order.asc("famille.ordre"));
        return crit.list();
    }

    @Override
    public boolean hasGarantieAssureBeneficiaire(Long idAssure, Long idBeneficiaire) {
        final StringBuffer requete = new StringBuffer("select count(garantie_uid) from data_garantie ");
        requete.append("where garantie_assure_uid = :idAssure ");
        requete.append("and garantie_beneficiaire_uid = :idBeneficiaire ");
        requete.append("and (garantie_statut_uid = :idStatutEnCours ");
        requete.append("or (garantie_statut_uid = :idStatutResilie and garantie_date_resiliation >= now())) ");
        requete.append("and garantie_date_adhesion <= now() ");
        requete.append("and garantie_visible = true");
        final Query crit = createSqlQuery(requete.toString());
        crit.setLong("idAssure", idAssure);
        crit.setLong("idBeneficiaire", idBeneficiaire);
        crit.setLong("idStatutEnCours", adherentMappingService.getIdStatutGarantieEnCours());
        crit.setLong("idStatutResilie", adherentMappingService.getIdStatutGarantieResiliee());
        return Integer.valueOf(crit.uniqueResult().toString()) > 0;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Garantie> getListeGarantiesBeneficiaire(Long idAssure, Long idBeneficiaire) {
        final Criteria crit = createCriteria(Garantie.class);
        crit.createAlias("famille", "famille");
        crit.createAlias("statut", "statut");
        crit.add(Restrictions.eq("uidAssure", idAssure));
        crit.add(Restrictions.eq("uidBeneficiaire", idBeneficiaire));
        crit.add(Restrictions.le("dateAdhesion", Calendar.getInstance()));
        crit.add(Restrictions.or(Restrictions.eq("statut.id", adherentMappingService.getIdStatutGarantieEnCours()),
            Restrictions.eq("statut.id", adherentMappingService.getIdStatutGarantieResiliee())));
        crit.add(Restrictions.eq("isVisible", true));
        crit.addOrder(Order.asc("famille.ordre"));
        crit.addOrder(Order.asc("statut.ordre"));
        return crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Garantie> getListeGarantiesContratPersonneMorale(String eidContrat) {
        final int nbCaractere = 13;
        final Criteria crit = createCriteria(Garantie.class);
        crit.createAlias("contrat", "contrat");
        crit.add(Restrictions.isNotNull("uidAssure"));
        crit.add(Restrictions.ilike("contrat.identifiantExterieur", eidContrat.substring(0, nbCaractere) + "%"));
        return crit.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CoordonneesBancairesRemboursementDto> getCoordonneesBancairesRemboursement(Long uidAdherent) {
        final StringBuffer sql = new StringBuffer("select distinct c.contrat_numero, ");
        sql.append("ad.personne_uid, ");
        sql.append("upper(ad.personne_nom)||' '||ad.personne_prenom ayant_droit, ");
        sql.append("case when g.garantie_moyen_paiement_presta_uid = 4714377 ");
        sql.append("then b.banque_nom_titulaire else upper(br.personne_nom)||' '||br.personne_prenom end benef_reglmt,");
        sql.append("mp.contrat_moyen_paiement_uid, ");
        sql.append("mp.contrat_moyen_paiement_lib, ");
        sql.append("b.banque_domiciliation, ");
        sql.append("b.banque_compte ");
        sql.append("from data_garantie g ");
        sql.append("inner join data_personne p on p.personne_uid = g.garantie_assure_uid ");
        sql.append("inner join data_personne_physique ad on g.garantie_beneficiaire_uid = ad.personne_uid ");
        sql.append("inner join data_personne_physique br on g.garantie_dest_rmbsmt_presta_uid = br.personne_uid ");
        sql.append("inner join data_contrat c on g.garantie_contrat_uid = c.contrat_uid ");
        sql.append("left outer join data_banque b on g.garantie_banque_presta_uid = b.banque_uid ");
        sql.append("inner join dim_contrat_moyen_paiement mp on g.garantie_moyen_paiement_presta_uid = mp.contrat_moyen_paiement_uid ");
        sql.append("where p.personne_uid = " + uidAdherent);
        sql.append(" and (g.garantie_date_resiliation is null or g.garantie_date_resiliation >= date_trunc('day', NOW() - interval '2 years'))");
        sql.append(" order by c.contrat_numero");
        final Query query = createSqlQuery(sql.toString());
        final List<Object[]> resultSet = query.list();
        final List<CoordonneesBancairesRemboursementDto> listeCoordonneesBancairesRemboursement = new ArrayList<CoordonneesBancairesRemboursementDto>();
        for (Object[] row : resultSet) {
            final CoordonneesBancairesRemboursementDto coordonneesBancairesRemboursement = new CoordonneesBancairesRemboursementDto();
            int i = 0;
            coordonneesBancairesRemboursement.setNumeroContrat((String) row[i++]);
            coordonneesBancairesRemboursement.setBeneficiaireSoins(new IdentifiantLibelleDto(((BigInteger) row[i++]).longValue(), (String) row[i++]));
            coordonneesBancairesRemboursement.setDestinataireRemboursements(new IdentifiantLibelleDto(null, (String) row[i++]));
            coordonneesBancairesRemboursement.setMoyenPaiement(new IdentifiantLibelleDto(((BigInteger) row[i++]).longValue(), (String) row[i++]));
            final InfosBanqueDto infosBancaires = new InfosBanqueDto();
            infosBancaires.setDomiciliation((String) row[i++]);
            infosBancaires.setCodeCompte((String) row[i++]);
            coordonneesBancairesRemboursement.setInfosBancaires(infosBancaires);
            listeCoordonneesBancairesRemboursement.add(coordonneesBancairesRemboursement);
        }
        return listeCoordonneesBancairesRemboursement;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
