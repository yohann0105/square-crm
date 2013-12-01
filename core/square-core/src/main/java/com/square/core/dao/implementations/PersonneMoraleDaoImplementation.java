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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.StringValueTransformer;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.PersonneMoraleDao;
import com.square.core.model.PersonneMorale;
import com.square.core.model.PersonneMoraleNature;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoraleSimpleDto;
import com.square.core.model.util.ResultatPaginationFullText;
import com.square.core.util.lucene.AnalysingCustomQueryParser;

/**
 * Dao pour les personnes morales.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleDaoImplementation extends HibernateDaoBaseImplementation implements PersonneMoraleDao {
    /**
     * Contantes pourcent.
     */
    private static final String POURCENT = "%";

    /**
     * Constante etoile.
     */
    private static final String ETOILE = "*";

    @Override
    public PersonneMorale rechercherPersonneMoraleParId(Long identifiant) {
        return load(identifiant, PersonneMorale.class);
    }

    @Override
    public void creerPersonneMorale(PersonneMorale personne) {
        save(personne);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PersonneMorale> recherchePersonnesMoralesParRequete(String requete) {
        final org.hibernate.Query query = createQuery(requete.toString());
        return (List<PersonneMorale>) query.list();
    }

    @Override
    public PersonneMoraleSimpleDto rechercherPersonneMoraleSimpleParId(Long identifiant) {

        final Criteria criteria = createCriteria(PersonneMorale.class);
        criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("raisonSociale")).add(Projections.groupProperty("numSiret")).add(
            Projections.groupProperty("naturePersonneMorale")).add(Projections.groupProperty("num")));
        criteria.add(Restrictions.eq("id", identifiant));

        // Transformation de la PersonneMorale morale en personneMoraleSimpleDto
        final Object[] row = (Object[]) criteria.uniqueResult();
        final PersonneMoraleSimpleDto personneMoraleSimpleDto = new PersonneMoraleSimpleDto();
        personneMoraleSimpleDto.setRaisonSociale(String.valueOf(row[0]));
        personneMoraleSimpleDto.setNumeroEntreprise(String.valueOf(row[3]));

        PersonneMoraleNature personneMoraleNature = null;
        if (row[2] instanceof PersonneMoraleNature) {
            personneMoraleNature = (PersonneMoraleNature) row[2];
            personneMoraleSimpleDto.setNature(new IdentifiantLibelleDto(personneMoraleNature.getId(), personneMoraleNature.getLibelle()));
        }

        personneMoraleSimpleDto.setNum(String.valueOf(row[3]));

        return personneMoraleSimpleDto;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResultatPaginationFullText<PersonneMorale> rechercherPersonneMoraleParCriteres(RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> criteres)
        throws ParseException {

        final PersonneMoralCriteresRechercheDto criteresRecherche = criteres.getCriterias();

        final BooleanQuery bq = new BooleanQuery();

        if (criteresRecherche.getRaisonSociale() != null && !("".equals(criteresRecherche.getRaisonSociale()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), "raisonSociale", getAnalyser(PersonneMorale.class));
            parser.setAllowLeadingWildcard(true);
            final String raisonSociale = criteresRecherche.getRaisonSociale().replaceAll(POURCENT, ETOILE);
            final org.apache.lucene.search.Query requeteLucene =
                parser.parse(raisonSociale.lastIndexOf(ETOILE) == raisonSociale.length() - 1 ? raisonSociale : raisonSociale + ETOILE);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getEnseigneCommerciale() != null && !("".equals(criteresRecherche.getEnseigneCommerciale()))) {
            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "enseigneCommercial", getAnalyser(PersonneMorale.class));
            parser.setAllowLeadingWildcard(true);
            final String enseigneCommerciale = criteresRecherche.getEnseigneCommerciale().replaceAll(POURCENT, ETOILE);
            final org.apache.lucene.search.Query requeteLucene =
                parser.parse(enseigneCommerciale.lastIndexOf(ETOILE) == enseigneCommerciale.length() - 1 ? enseigneCommerciale : enseigneCommerciale + ETOILE);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        // Critère sur le numéro de l'entreprise
        String numeroEntreprise = criteresRecherche.getNumeroEntreprise();
        if (!StringUtils.isBlank(numeroEntreprise)) {
            numeroEntreprise = numeroEntreprise.replaceAll(POURCENT, ETOILE);
            final MultiFieldQueryParser parser =
                new MultiFieldQueryParser(getMatchingVersion(), new String[] {"numSiret", "num"}, getAnalyser(PersonneMorale.class));
            parser.setAllowLeadingWildcard(true);
            final org.apache.lucene.search.Query requeteLucene = parser.parse(numeroEntreprise);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        // Critere sur le complement du nom
        if (criteresRecherche.getComplementNom() != null && !("".equals(criteresRecherche.getComplementNom()))) {
            final AnalysingCustomQueryParser parser =
                new AnalysingCustomQueryParser(getMatchingVersion(), "adresses.complementNom", getAnalyser(PersonneMorale.class));
            parser.setAllowLeadingWildcard(true);
            final String complementNom = criteresRecherche.getComplementNom().replaceAll(POURCENT, ETOILE);
            final org.apache.lucene.search.Query requeteLucene =
                parser.parse(complementNom.lastIndexOf(ETOILE) == complementNom.length() - 1 ? complementNom : complementNom + ETOILE);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        // Critère sur le département
        if (criteresRecherche.getListeDepartements() != null && !criteresRecherche.getListeDepartements().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeDepartements(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("adresses.codePostalCommune.commune.departement.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la ville
        if (criteresRecherche.getListeVilles() != null && !criteresRecherche.getListeVilles().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getListeVilles(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("adresses.codePostalCommune.commune.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur le code postal
        if (criteresRecherche.getListeCodesPostaux() != null && !criteresRecherche.getListeCodesPostaux().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeCodesPostaux(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("adresses.codePostalCommune.codePostal.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la forme juridique
        if (criteresRecherche.getListeFormesJuridiques() != null && !criteresRecherche.getListeFormesJuridiques().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeFormesJuridiques(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("formeJuridique.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur le commercial
        if (criteresRecherche.getListeCommerciaux() != null && !criteresRecherche.getListeCommerciaux().isEmpty()) {
            final List<String> listeValeurs =
                (List<String>) CollectionUtils.collect(criteresRecherche.getListeCommerciaux(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("attribution.ressource.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur l'agence
        if (criteresRecherche.getListeAgences() != null && !criteresRecherche.getListeAgences().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getListeAgences(), StringValueTransformer.getInstance());
            bq.add(getInQuery("attribution.agence.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la nature
        if (criteresRecherche.getListeNatures() != null && !criteresRecherche.getListeNatures().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getListeNatures(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("naturePersonneMorale.id", listeValeurs), BooleanClause.Occur.MUST);
        }

        // Construction de la requête par defaut.
        if (criteresRecherche.getRaisonSociale() == null || "".equals(criteresRecherche.getRaisonSociale())) {
            final QueryParser parser = new QueryParser(getMatchingVersion(), "raisonSociale", getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            final org.apache.lucene.search.Query requeteLucene = parser.parse("*");
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }

        // CONSTRUCTION DE LA REQUETE
        final FullTextQuery fullTextQuery = createFullTextQuery(bq, PersonneMorale.class);

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
        fullTextQuery.setFirstResult(criteres.getFirstResult());
        fullTextQuery.setMaxResults(criteres.getMaxResult());

        // RETOURNE LA LISTE DES RESULTATS ET LE NOMBRE TOTAL DE RESULTAT (non affécté par la pagination)
        return new ResultatPaginationFullText<PersonneMorale>(fullTextQuery.list(), fullTextQuery.getResultSize());
    }
}
