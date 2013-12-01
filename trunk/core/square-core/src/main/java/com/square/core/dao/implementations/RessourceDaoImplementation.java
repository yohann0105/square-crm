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
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.core.dao.interfaces.RessourceDao;
import com.square.core.model.PersonnePhysique;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.DimensionCriteresRechercheRessourceDto;
import com.square.core.model.dto.RessourceCriteresRechercheDto;
import com.square.core.model.util.ResultatPaginationFullText;
import com.square.core.util.lucene.AnalysingCustomQueryParser;

/**
 * Implémentation du dao pour les ressources.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class RessourceDaoImplementation extends HibernateDaoBaseImplementation implements RessourceDao {

    /**
     * Nom de l'attibut "nom".
     */
    private static final String FIELD_NOM = "nom";

    /**
     * Contantes pourcent.
     */
    private static final String POURCENT = "%";

    /**
     * Constante etoile.
     */
    private static final String ETOILE = "*";

    @Override
    public Ressource rechercherRessourceParId(Long identifiant) {
        return load(identifiant, Ressource.class);
    }

    @Override
    public Ressource rechercherRessourceParEid(String eid) {
        final Criteria criteria = createCriteria(Ressource.class);
        if (eid != null && !"".equals(eid)) {
            criteria.add(Restrictions.eq("identifiantExterieur", eid));
        }
        return (Ressource) criteria.uniqueResult();
    }

    @Override
    public List<Ressource> rechercherRessourceParCriteres(DimensionCriteresRechercheRessourceDto criteres) {
    	// On recherche d'abord par Nom
    	final List<Ressource> list = rechercherRessourceParNomOuPrenom(criteres, true);
    	// Puis par Prénom sauf si nom et prenom vide (car c'est la même recherche sinon)
    	if (!StringUtils.isBlank(criteres.getNom()) || !StringUtils.isBlank(criteres.getPrenom())) {
    		list.addAll(rechercherRessourceParNomOuPrenom(criteres, false));
    	}
        return list;
    }

    /**
     * Rechercher ressource par critères, soit en recherchant que par nom ou que par prénom.
     * @param criteres les critères de recherche.
     * @param chercherParNom true pour chercher par nom, false pour chercher par prénom.
     */
    @SuppressWarnings("unchecked")
	private List<Ressource> rechercherRessourceParNomOuPrenom(DimensionCriteresRechercheRessourceDto criteres, boolean chercherParNom) {
        final StringBuffer requete = new StringBuffer("from Ressource r where 1=1 ");
        final boolean hasCritereNom = !StringUtils.isBlank(criteres.getNom());
        final boolean hasCriterePrenom = !StringUtils.isBlank(criteres.getPrenom());
        if (hasCritereNom && chercherParNom) {
            requete.append(" and lower(r.nom) like :nomRessource");
        }
        // Critère sur le prénom.
        if (hasCriterePrenom && !chercherParNom) {
            requete.append(" and lower(r.prenom) like :prenomRessource");
        }
        // Critère sur l'identifiant de l'agence.
        if (criteres.getIdAgence() != null) {
            requete.append(" and r.agence.id = :idAgence");
        }
        // Critères sur la liste des agences
        if (criteres.getIdAgences() != null && !criteres.getIdAgences().isEmpty()) {
            requete.append(" and r.agence.id in (:listeIdsAgences)");
        }
        // Critères sur la liste des regions
        if (criteres.getIdRegions() != null && !criteres.getIdRegions().isEmpty()) {
            requete.append(" and r.agence.region.id in (:listeIdsRegions)");
        }
        // Critères sur la liste des fonctions
        if (criteres.getIdFonctions() != null && !criteres.getIdFonctions().isEmpty()) {
            requete.append(" and r.fonction.id in (:listeIdsFonctions)");
        }
        if (criteres.getIdEtats() != null && !criteres.getIdEtats().isEmpty()) {
            requete.append(" and r.etat.id in (:listeIdsEtats)");
        }
        // Critère sur le nom et prénom concaténés
        if (!StringUtils.isBlank(criteres.getNomPrenom())) {
            requete.append(" and (lower(r.nom) || ' ' || lower(r.prenom)) like :nomPrenom");
        }
        // Critère sur le prénom et nom concaténés
        if (!StringUtils.isBlank(criteres.getPrenomNom())) {
            requete.append(" and (lower(r.prenom) || ' ' || lower(r.nom)) like :prenomNom");
        }
        // Les ressources ne doivent pas être supprimées
        if (criteres.getIsSupprime() != null) {
        	requete.append(" and r.supprime = :isSupprime");
        }

        // Tri
        requete.append(" order by");
        if (chercherParNom) {
        	requete.append(" r.nom, r.prenom");
        }
        else {
        	requete.append(" r.prenom, r.nom");
        }

        // Création du query
        final org.hibernate.Query query = createQuery(requete.toString());

        // Maxresults
        if (criteres.getMaxResults() != null) {
            query.setFirstResult(0);
            query.setMaxResults(criteres.getMaxResults());
        }

        // Ajout des paramètres
        if (hasCritereNom && chercherParNom) {
            query.setString("nomRessource", criteres.getNom().toLowerCase() + POURCENT);
        }
        if (hasCriterePrenom && !chercherParNom) {
            query.setString("prenomRessource", criteres.getPrenom().toLowerCase() + POURCENT);
        }
        if (criteres.getIdAgence() != null) {
            query.setLong("idAgence", criteres.getIdAgence());
        }
        if (criteres.getIdAgences() != null && !criteres.getIdAgences().isEmpty()) {
            query.setParameterList("listeIdsAgences", criteres.getIdAgences());
        }
        if (criteres.getIdRegions() != null && !criteres.getIdRegions().isEmpty()) {
            query.setParameterList("listeIdsRegions", criteres.getIdRegions());
        }
        if (criteres.getIdEtats() != null && !criteres.getIdEtats().isEmpty()) {
            query.setParameterList("listeIdsEtats", criteres.getIdEtats());
        }
        if (criteres.getIdFonctions() != null && !criteres.getIdFonctions().isEmpty()) {
            query.setParameterList("listeIdsFonctions", criteres.getIdFonctions());
        }
        if (!StringUtils.isBlank(criteres.getNomPrenom())) {
            query.setString("nomPrenom", criteres.getNomPrenom().toLowerCase() + POURCENT);
        }
        if (!StringUtils.isBlank(criteres.getPrenomNom())) {
            query.setString("prenomNom", criteres.getPrenomNom().toLowerCase() + POURCENT);
        }
        if (criteres.getIsSupprime() != null) {
            query.setBoolean("isSupprime", criteres.getIsSupprime());
        }

        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ResultatPaginationFullText<Ressource> rechercherFullTextRessource(RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteres)
        throws ParseException {

        final RessourceCriteresRechercheDto criteresRecherche = criteres.getCriterias();

        // Si des critères ont été fournis on traite ces critères
        final BooleanQuery bq = new BooleanQuery();

        if (criteresRecherche.getNom() != null && !("".equals(criteresRecherche.getNom()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), FIELD_NOM, getAnalyser(Ressource.class));
            parser.setAllowLeadingWildcard(true);
            final String nom = criteresRecherche.getNom().replaceAll(POURCENT, ETOILE);
            final org.apache.lucene.search.Query requeteLucene = parser.parse(nom.lastIndexOf(ETOILE) == nom.length() - 1 ? nom : nom + ETOILE);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteresRecherche.getPrenom() != null && !("".equals(criteresRecherche.getPrenom()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), "prenom", getAnalyser(Ressource.class));
            parser.setAllowLeadingWildcard(true);
            final String prenom = criteresRecherche.getPrenom().replaceAll(POURCENT, ETOILE);
            final org.apache.lucene.search.Query requeteLucene = parser.parse(prenom.lastIndexOf(ETOILE) == prenom.length() - 1 ? prenom : prenom + ETOILE);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des services
        if (criteresRecherche.getIdServices() != null && !criteresRecherche.getIdServices().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getIdServices(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("service.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des fonctions
        if (criteresRecherche.getIdFonctions() != null && !criteresRecherche.getIdFonctions().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getIdFonctions(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("fonction.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des etats
        if (criteresRecherche.getIdEtats() != null && !criteresRecherche.getIdEtats().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getIdEtats(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("etat.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des agences
        if (criteresRecherche.getIdAgences() != null && !criteresRecherche.getIdAgences().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getIdAgences(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("agence.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des identifiants extérieurs des fonctions
        if (criteresRecherche.getListeIdsExtsFonctions() != null && !criteresRecherche.getListeIdsExtsFonctions().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteresRecherche.getListeIdsExtsFonctions(),
                StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("fonction.identifiantExterieur", listeValeurs), BooleanClause.Occur.SHOULD);
        }

        // PARAMETRE TOUJOURS VRAI POUR RECHERCHER SANS CRITERE
        if (criteresRecherche.getNom() == null || "".equals(criteresRecherche.getNom())) {
            final QueryParser parser = new QueryParser(getMatchingVersion(), FIELD_NOM, getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            final org.apache.lucene.search.Query requeteLucene = parser.parse("*");
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }

        // CONSTRUCTION DE LA REQUETE
        final FullTextQuery fullTextQuery = createFullTextQuery(bq, Ressource.class);

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
        return new ResultatPaginationFullText<Ressource>(fullTextQuery.list(), fullTextQuery.getResultSize());
    }

    @Override
    public void creerRessource(Ressource ressource) {
        save(ressource);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> rechercherFullTextIdsRessources(RessourceCriteresRechercheDto criteres) throws ParseException {

        // Si des critères ont été fournis on traite ces critères
        final BooleanQuery bq = new BooleanQuery();

        if (criteres.getNom() != null && !("".equals(criteres.getNom()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), FIELD_NOM, getAnalyser(Ressource.class));
            parser.setAllowLeadingWildcard(true);
            final String nom = criteres.getNom().replaceAll(POURCENT, ETOILE);
            final org.apache.lucene.search.Query requeteLucene = parser.parse(nom.lastIndexOf(ETOILE) == nom.length() - 1 ? nom : nom + ETOILE);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        if (criteres.getPrenom() != null && !("".equals(criteres.getPrenom()))) {
            final AnalysingCustomQueryParser parser = new AnalysingCustomQueryParser(getMatchingVersion(), "prenom", getAnalyser(Ressource.class));
            parser.setAllowLeadingWildcard(true);
            final String prenom = criteres.getPrenom().replaceAll(POURCENT, ETOILE);
            final org.apache.lucene.search.Query requeteLucene = parser.parse(prenom.lastIndexOf(ETOILE) == prenom.length() - 1 ? prenom : prenom + ETOILE);
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des services
        if (criteres.getIdServices() != null && !criteres.getIdServices().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteres.getIdServices(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("service.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des fonctions
        if (criteres.getIdFonctions() != null && !criteres.getIdFonctions().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteres.getIdFonctions(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("fonction.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des etats
        if (criteres.getIdEtats() != null && !criteres.getIdEtats().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteres.getIdEtats(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("etat.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des agences
        if (criteres.getIdAgences() != null && !criteres.getIdAgences().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteres.getIdAgences(), StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("agence.id", listeValeurs), BooleanClause.Occur.MUST);
        }
        // Critère sur la liste des identifiants extérieurs des fonctions
        if (criteres.getListeIdsExtsFonctions() != null && !criteres.getListeIdsExtsFonctions().isEmpty()) {
            final List<String> listeValeurs = (List<String>) CollectionUtils.collect(criteres.getListeIdsExtsFonctions(),
                StringValueTransformer.getInstance());
            bq.add((Query) getInQuery("fonction.identifiantExterieur", listeValeurs), BooleanClause.Occur.SHOULD);
        }

        // PARAMETRE TOUJOURS VRAI POUR RECHERCHER SANS CRITERE
        if (criteres.getNom() == null || "".equals(criteres.getNom())) {
            final QueryParser parser = new QueryParser(getMatchingVersion(), FIELD_NOM, getAnalyser(PersonnePhysique.class));
            parser.setAllowLeadingWildcard(true);
            final org.apache.lucene.search.Query requeteLucene = parser.parse("*");
            bq.add(requeteLucene, BooleanClause.Occur.MUST);
        }

        // CONSTRUCTION DE LA REQUETE
        final FullTextQuery fullTextQuery = createFullTextQuery(bq, Ressource.class);
        fullTextQuery.setProjection("id");

        final List<Object[]> listeResultats = (List<Object[]>) fullTextQuery.list();
        // Mapping des résultats
        final List<Long> resultats = new ArrayList<Long>();
        for (Object[] resultat : listeResultats) {
            resultats.add((Long) resultat[0]);
        }

        return resultats;
    }

}
