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
package com.square.core.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.StringValueTransformer;
import org.apache.log4j.Logger;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.PersonnePhysiqueDao;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.dto.NumeroRoDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonnePhysiqueIdCriteresRechercheDto;
import com.square.core.model.util.ResultatPaginationFullText;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.lucene.AnalysingCustomQueryParser;
import com.square.core.util.lucene.DateNaissanceBridge;

/**
 * Implémentation du Dao des personnes physiques.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonnePhysiqueDaoImplementation extends HibernateDaoBaseImplementation implements PersonnePhysiqueDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    private SquareMappingService squareMappingService;

    private static final String POURCENT = "%";

    private static final String ETOILE = "*";

    /**
     * Créer une personne physique.
     * @param personne personne à créer.
     */
    public void creerPersonnePhysique(PersonnePhysique personne) {
        save(personne);
    }

    @Override
    public PersonnePhysique rechercherPersonneParId(Long id) {
        return load(id, PersonnePhysique.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PersonnePhysique> recherchePersonneParRequete(String requete) {
        final org.hibernate.Query query = createQuery(requete.toString());
        return (List<PersonnePhysique>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ResultatPaginationFullText<PersonnePhysique> rechercheFullTextPersonne(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres)
        throws ParseException {

        final PersonneCriteresRechercheDto criteresRecherche = criteres.getCriterias();

        // Si des critères ont été fournis on traite ces critères
        final BooleanQuery bq = new BooleanQuery();

        if (criteresRecherche.getNumeroClient() != null && !("".equals(criteresRecherche.getNumeroClient()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), "num", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String numeroClient = criteresRecherche.getNumeroClient().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                numeroClient = numeroClient.lastIndexOf(ETOILE) == numeroClient.length() - 1 ? numeroClient : numeroClient + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(numeroClient);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getNom() != null && !("".equals(criteresRecherche.getNom()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), "nom", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String nom = criteresRecherche.getNom().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                nom = nom.lastIndexOf(ETOILE) == nom.length() - 1 ? nom : nom + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(nom);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getNomJeuneFille() != null && !("".equals(criteresRecherche.getNomJeuneFille()))) {
            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "nomJeuneFille", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String nomJeuneFille = criteresRecherche.getNomJeuneFille().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                nomJeuneFille = nomJeuneFille.lastIndexOf(ETOILE) == nomJeuneFille.length() - 1 ? nomJeuneFille : nomJeuneFille + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(nomJeuneFille);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getPrenom() != null && !("".equals(criteresRecherche.getPrenom()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), "prenom", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String prenom = criteresRecherche.getPrenom().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                prenom = prenom.lastIndexOf(ETOILE) == prenom.length() - 1 ? prenom : prenom + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(prenom);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getNro() != null && !("".equals(criteresRecherche.getNro()))) {
            if (criteresRecherche.getNro().length() == 15) {
                final NumeroRoDto numeroRo = squareMappingService.convertirNroVersNss(criteresRecherche.getNro());

                AnalysingCustomQueryParser parser =
                    new AnalysingCustomQueryParser(getMatchingVersion(), "infoSante.numSecuriteSocial", getAnalyser(PersonnePhysique.class));
                parser.setAllowLeadingWildcard(true);
                String numeroSs = numeroRo.getNumeroSS().replaceAll(POURCENT, ETOILE);
                if (!criteres.getCriterias().isRechercheStricte()) {
                    numeroSs = numeroSs.lastIndexOf(ETOILE) == numeroSs.length() - 1 ? numeroSs : numeroSs + ETOILE;
                }
                org.apache.lucene.search.Query requeteLucene = parser.parse(numeroSs);
                bq.add(requeteLucene, BooleanClause.Occur.MUST);

                parser = new AnalysingCustomQueryParser(getMatchingVersion(), "infoSante.cleSecuriteSocial", getAnalyser(PersonnePhysique.class));
                parser.setAllowLeadingWildcard(true);
                String cleSs = numeroRo.getCleSS().replaceAll(POURCENT, ETOILE);
                if (!criteres.getCriterias().isRechercheStricte()) {
                    cleSs = cleSs.lastIndexOf(ETOILE) == cleSs.length() - 1 ? cleSs : cleSs + ETOILE;
                }
                requeteLucene = parser.parse(cleSs);
                bq.add(requeteLucene, BooleanClause.Occur.MUST);
            } else {
                final AnalysingCustomQueryParser parser =
                    new AnalysingCustomQueryParser(getMatchingVersion(), "infoSante.numSecuriteSocial", getAnalyser(PersonnePhysique.class));
                parser.setAllowLeadingWildcard(true);
                String numeroSs = criteresRecherche.getNro().replaceAll(POURCENT, ETOILE);
                if (!criteres.getCriterias().isRechercheStricte()) {
                    numeroSs = numeroSs.lastIndexOf(ETOILE) == numeroSs.length() - 1 ? numeroSs : numeroSs + ETOILE;
                }
                final org.apache.lucene.search.Query requeteLucene = parser.parse(numeroSs);
                bq.add(requeteLucene, BooleanClause.Occur.MUST);
            }
        }

        if (criteresRecherche.getDateNaissance() != null) {
            final DateNaissanceBridge dateBridge = new DateNaissanceBridge();

            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "dateNaissance", getAnalyser(PersonnePhysique.class));
            final org.apache.lucene.search.Query requeteLucene = parser.parse(dateBridge.objectToString(criteresRecherche.getDateNaissance()));
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getListeNatures() != null && !criteresRecherche.getListeNatures().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getListeNatures(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("nature.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getTelephone() != null && !("".equals(criteresRecherche.getTelephone()))) {
            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "telephones.numTelephone", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String telephone = criteresRecherche.getTelephone().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                telephone = telephone.lastIndexOf(ETOILE) == telephone.length() - 1 ? telephone : telephone + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(telephone);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getEmail() != null && !("".equals(criteresRecherche.getEmail()))) {
            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "emails.adresse", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String email = criteresRecherche.getEmail().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                email = email.lastIndexOf(ETOILE) == email.length() - 1 ? email : email + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(email);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getNumVoie() != null && !("".equals(criteresRecherche.getNumVoie()))) {
            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "adresses.numeroVoie", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String numVoie = criteresRecherche.getNumVoie().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                numVoie = numVoie.lastIndexOf(ETOILE) == numVoie.length() - 1 ? numVoie : numVoie + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(numVoie);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getListeNaturesVoie() != null && !criteresRecherche.getListeNaturesVoie().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeNaturesVoie(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("adresses.typeVoie.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getListeNaturesTelephones() != null && !criteresRecherche.getListeNaturesTelephones().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeNaturesTelephones(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("telephones.natureTelephone.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getAdresse() != null && !("".equals(criteresRecherche.getAdresse()))) {
            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "adresses.nomVoie", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            String adresse = criteresRecherche.getAdresse().replaceAll(POURCENT, ETOILE);
            if (!criteres.getCriterias().isRechercheStricte()) {
                adresse = adresse.lastIndexOf(ETOILE) == adresse.length() - 1 ? adresse : adresse + ETOILE;
            }
            final org.apache.lucene.search.Query requeteLucene = parser.parse(adresse);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getListeCodesPostaux() != null && !criteresRecherche.getListeCodesPostaux().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeCodesPostaux(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("adresses.codePostalCommune.codePostal.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getListeVilles() != null && !criteresRecherche.getListeVilles().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getListeVilles(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("adresses.codePostalCommune.commune.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getIdCommerciaux() != null && !criteresRecherche.getIdCommerciaux().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getIdCommerciaux(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("attribution.ressource.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getIdAgences() != null && !criteresRecherche.getIdAgences().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getIdAgences(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("attribution.agence.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getIdPersonnesAIgnorer() != null && !criteresRecherche.getIdPersonnesAIgnorer().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getIdPersonnesAIgnorer(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("id", listeValeurs), BooleanClause.Occur.MUST_NOT);
        }
        if (criteresRecherche.getListeReseaux() != null && !criteresRecherche.getListeReseaux().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeReseaux(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("reseau.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        final List<Long> idPersonnes = criteresRecherche.getIdPersonnes();
        if (idPersonnes != null && !idPersonnes.isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(idPersonnes, StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("id", listeValeurs), BooleanClause.Occur.MUST);
        }

        // On ne recherche que les personnes non supprimées
        if (criteresRecherche.getSupprime() != null) {
            bq.add(new TermQuery(new Term("supprime", criteresRecherche.getSupprime().toString())), BooleanClause.Occur.MUST);
        }

        // CONSTRUCTION DE LA REQUETE
        final FullTextQuery fullTextQuery = createFullTextQuery(bq, PersonnePhysique.class);
        logger.debug("Requête full text : " + fullTextQuery.getQueryString());

        // AJOUT DES INFORMATION DE TRI
        if (criteres.getListeSorts().size() > 0) {
            final SortField[] listeChamp = new SortField[criteres.getListeSorts().size()];
            for (int index = 0; index < criteres.getListeSorts().size(); index++) {
                listeChamp[index] =
                    new SortField(criteres.getListeSorts().get(index).getSortField(), SortField.STRING,
                        criteres.getListeSorts().get(index).getSortAsc() == RemotePagingSort.REMOTE_PAGING_SORT_DESC);
            }
            final Sort sort = new Sort(listeChamp);
            fullTextQuery.setSort(sort);

        }

        // AJOUT DES INFORMATIONS DE PAGINATION
        final int nbMaxDocs = fullTextQuery.getResultSize();
        fullTextQuery.setFirstResult(criteres.getFirstResult());
        fullTextQuery.setMaxResults((criteres.getFirstResult() + criteres.getMaxResult()) > nbMaxDocs ? (nbMaxDocs == 0 ? nbMaxDocs + 1 : nbMaxDocs) : criteres
                .getMaxResult());

        // RETOURNE LA LISTE DES RESULTATS ET LE NOMBRE TOTAL DE RESULTAT (non affécté par la pagination)
        return new ResultatPaginationFullText<PersonnePhysique>(fullTextQuery.list(), nbMaxDocs);
    }

    /**
     * Modifie squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    @Override
    public PersonnePhysique rechercherPersonneParIdExt(String id) {
        return (PersonnePhysique) createCriteria(PersonnePhysique.class).add(Restrictions.eq("identifiantExterieur", id)).uniqueResult();
    }

    private String getOperator(String operator, int count) {
        return count > 0 ? operator : "";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> rechercherIdPersonneParCriteres(PersonnePhysiqueIdCriteresRechercheDto criteres) {
        final List<Long> listeIds = new ArrayList<Long>();
        if (criteres == null) {
            return listeIds;
        }
        final Criteria criteria = createCriteria(PersonnePhysique.class);
        if (criteres.getListeIdsPersonnes() != null) {
            criteria.add(Restrictions.in("id", criteres.getListeIdsPersonnes()));
        }
        if (criteres.getListeIdsNaturesPersonnes() != null) {
            criteria.add(Restrictions.in("nature.id", criteres.getListeIdsNaturesPersonnes()));
        }
        final List<PersonnePhysique> result = criteria.list();
        if (result != null && result.size() > 0) {
            for (PersonnePhysique personnePhysique : result) {
                listeIds.add(personnePhysique.getId());
            }
        }
        return listeIds;
    }
}
