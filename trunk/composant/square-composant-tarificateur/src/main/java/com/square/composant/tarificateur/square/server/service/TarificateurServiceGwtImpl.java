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
package com.square.composant.tarificateur.square.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;

import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.CritereModeleDevisModel;
import com.square.composant.tarificateur.square.client.model.ListeProduitsAdherentModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosGlobalesAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneModel;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.OpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModificationModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.EnregistrementFinaliteDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.TransfertDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.LigneDevisModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwt;
import com.square.composant.tarificateur.square.server.servlet.ImprimerDevisServlet;
import com.square.core.model.dto.CaisseDto;
import com.square.core.model.dto.RessourceDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.core.service.interfaces.RessourceService;
import com.square.tarificateur.noyau.dto.InfosAdhesionDto;
import com.square.tarificateur.noyau.dto.InfosOpportuniteDto;
import com.square.tarificateur.noyau.dto.InfosPersonneDto;
import com.square.tarificateur.noyau.dto.devis.CritereModeleDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheDevisDto;
import com.square.tarificateur.noyau.dto.devis.DevisDto;
import com.square.tarificateur.noyau.dto.devis.DevisModificationDto;
import com.square.tarificateur.noyau.dto.devis.EnregistrementFinaliteDevisDto;
import com.square.tarificateur.noyau.dto.devis.MotifDevisDto;
import com.square.tarificateur.noyau.dto.devis.OpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.TransfertDevisDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;
import com.square.user.core.dto.UtilisateurDto;
import com.square.user.core.service.interfaces.UtilisateurMappingService;
import com.square.user.core.service.interfaces.UtilisateurService;

/**
 * Implémentation serveur des services GWT pour les services du tarficateur.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class TarificateurServiceGwtImpl implements TarificateurServiceGwt {

    /** Service tarificateur. */
    private TarificateurService tarificateurService;

    /** Service tarificateur Personne. */
    private TarificateurPersonneService tarificateurPersonneService;

    private UtilisateurMappingService utilisateurMappingService;

    /** Service Utilisateur. */
    private UtilisateurService utilisateurService;

    private DimensionService dimensionService;

    private RessourceService ressourceService;

    private MapperDozerBean mapperDozerBean;

    @Override
    public OpportuniteModel getOrCreateOpportunite(InfosOpportuniteModel infosOpportunite) {
        final InfosOpportuniteDto infosOpportuniteDto = mapperDozerBean.map(infosOpportunite, InfosOpportuniteDto.class);
        final OpportuniteDto opportuniteDto = tarificateurService.getOrCreateOpportunite(infosOpportuniteDto);
        final OpportuniteModel opportuniteModel = mapperDozerBean.map(opportuniteDto, OpportuniteModel.class);
        remplirCreateurLigneDevis(opportuniteModel);
        return opportuniteModel;
    }

    @Override
    public void enregistrerFinaliteDevis(EnregistrementFinaliteDevisModel enregistrementFinaliteDevis) {
        tarificateurService.enregistrerFinaliteDevis((EnregistrementFinaliteDevisDto) mapperDozerBean.map(enregistrementFinaliteDevis,
            EnregistrementFinaliteDevisDto.class));
    }

    @Override
    public List<IdentifiantLibelleGwt> getMotifsDevisByCriteres(String suggest, Long idMotifDevisOriginel) {
        final List<MotifDevisDto> listeMotifs = tarificateurService.getMotifsDevisByCriteres(suggest);
        final List<IdentifiantLibelleGwt> listeMotifsGwt = new ArrayList<IdentifiantLibelleGwt>();
        for (MotifDevisDto motifDevis : listeMotifs) {
            // On affiche l'offre si elle n'est pas obsolète ou si elle est sélectionnée
            if (Boolean.FALSE.equals(motifDevis.getObsolete()) || (idMotifDevisOriginel != null && motifDevis.getIdentifiant().equals(idMotifDevisOriginel))) {
                listeMotifsGwt.add((IdentifiantLibelleGwt) mapperDozerBean.map(motifDevis, IdentifiantLibelleGwt.class));
            }
        }
        return listeMotifsGwt;
    }

    @Override
    public void transfererDevis(TransfertDevisModel transfertDevis, InfosOpportuniteModel infosOpp) {
        // Récupération des informations de l'utilisateur
        final UtilisateurDto utilisateur = utilisateurService.getUtilisateurByLogin(infosOpp.getLoginUtilisateurConnecte());
        final RessourceDto ressource = ressourceService.rechercherRessourceParEid(utilisateur.getId().toString());
        final TransfertDevisDto demande = mapperDozerBean.map(transfertDevis, TransfertDevisDto.class);
        demande.setEidRessource(ressource.getId());
        tarificateurService.transfererDevis(demande);
    }

    @Override
    public List<IdentifiantLibelleGwt> getListeModelesDevisByCritere(CritereModeleDevisModel critere) {
        final CritereModeleDevisDto critereDto = mapperDozerBean.map(critere, CritereModeleDevisDto.class);
        final List<IdentifiantLibelleDto> listeModelesDto = tarificateurService.getListeModelesDevisByCriteres(critereDto);
        return mapperDozerBean.mapList(listeModelesDto, IdentifiantLibelleGwt.class);
    }

    /**
     * Set the tarificateurService value.
     * @param tarificateurService the tarificateurService to set
     */
    public void setTarificateurService(TarificateurService tarificateurService) {
        this.tarificateurService = tarificateurService;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentifiantLibelleGwt> getListeCaisses(Long idPersonne, Long idRegime) {
        return mapperDozerBean.mapList(tarificateurService.getListeCaisses(idPersonne, idRegime), IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InfosGlobalesAdhesionModel getInfosAdhesion(Long idDevis) {
        final InfosAdhesionDto infosAdhesion = tarificateurService.getInfosAdhesion(idDevis);
        final InfosAdhesionModel infosAdhesionModel = mapperDozerBean.map(infosAdhesion, InfosAdhesionModel.class);
        for (InfosPersonneDto infosPersonneDto : infosAdhesion.getInfosPersonnes()) {
            // on complete le regime et caisse
            for (InfosPersonneModel infosPersonnes : infosAdhesionModel.getInfosPersonnes()) {
                if (infosPersonneDto.getId().equals(infosPersonnes.getId()) && infosPersonneDto.getInfoSante() != null) {
                    final CaisseDto caisse = dimensionService.rechercherCaisseParId(infosPersonneDto.getInfoSante().getEidCaisse());
                    if (caisse != null) {
                        infosPersonnes.getInfoSante().setCaisse((CaisseSimpleModel) mapperDozerBean.map(caisse, CaisseSimpleModel.class));
                        infosPersonnes.getInfoSante().setRegime((IdentifiantLibelleGwt) mapperDozerBean.map(caisse.getRegime(), IdentifiantLibelleGwt.class));
                    }
                    break;
                }
            }
        }
        infosAdhesionModel.getInfosPaiement().setJourPaiement(
            (IdentifiantLibelleGwt) mapperDozerBean.map(tarificateurService.rechercherJourPaiementParId(infosAdhesion.getInfosPaiement().getIdJourPaiement()),
                IdentifiantLibelleGwt.class));
        infosAdhesionModel.getInfosPaiement().setPeriodicitePaiement(
            (IdentifiantLibelleGwt) mapperDozerBean.map(tarificateurService.rechercherPeriodicitePaiementParId(infosAdhesion.getInfosPaiement()
                    .getIdPeriodicitePaiement()), IdentifiantLibelleGwt.class));
        infosAdhesionModel.getInfosPaiement().setMoyenPaiement(
            (IdentifiantLibelleGwt) mapperDozerBean.map(
                tarificateurService.rechercherMoyenPaiementParId(infosAdhesion.getInfosPaiement().getIdMoyenPaiement()), IdentifiantLibelleGwt.class));

        final InfosGlobalesAdhesionModel infosGlobales = new InfosGlobalesAdhesionModel();
        infosGlobales.setInfosAdhesionModel(infosAdhesionModel);
        infosGlobales.setListeAssuresSociaux(getListeAssuresSociaux(idDevis));
        return infosGlobales;
    }

    private List<AssureSocialModel> getListeAssuresSociaux(Long idDevis) {
        final List<PersonneDto> listeDto = tarificateurPersonneService.getAssuresSociauxByIdDevis(idDevis);
        final List<AssureSocialModel> liste = mapperDozerBean.mapList(listeDto, AssureSocialModel.class);
        for (AssureSocialModel assureSocialModel : liste) {
            // on complete le regime et caisse
            for (PersonneDto assureSocialDto : listeDto) {
                if (assureSocialDto.getId().equals(assureSocialModel.getId()) && assureSocialDto.getInfoSante() != null) {
                    final CaisseDto caisse = dimensionService.rechercherCaisseParId(assureSocialDto.getInfoSante().getEidCaisse());
                    if (caisse != null) {
                        assureSocialModel.getInfoSante().setCaisse((CaisseSimpleModel) mapperDozerBean.map(caisse, CaisseSimpleModel.class));
                        assureSocialModel.getInfoSante()
                                .setRegime((IdentifiantLibelleGwt) mapperDozerBean.map(caisse.getRegime(), IdentifiantLibelleGwt.class));
                    }
                    break;
                }
            }
        }
        return liste;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInfosAdhesion(InfosAdhesionModel infosAdhesion) {
        tarificateurService.updateInfosAdhesion((InfosAdhesionDto) mapperDozerBean.map(infosAdhesion, InfosAdhesionDto.class));
    }

    @Override
    public List<IdentifiantLibelleGwt> getBeneficiairesFromProspectByDevis(DevisModel devis, List<Long> listeIdsLignesSelectionnees) {
        final DevisDto devisDto = mapperDozerBean.map(devis, DevisDto.class);
        final List<IdentifiantLibelleDto> listeBeneficiaireDto = tarificateurService.getBeneficiairesFromProspectByDevis(devisDto, listeIdsLignesSelectionnees);
    	return mapperDozerBean.mapList(listeBeneficiaireDto, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<ListeProduitsAdherentModel> getListeProduitsAdherent(Long idPersonne) {
        return mapperDozerBean.mapList(tarificateurService.getListeProduitsAdherent(idPersonne), ListeProduitsAdherentModel.class);
    }

    /**
     * Remplit le créateur des lignes de devis.
     * @param opportuniteModel l'opportunité
     */
    private void remplirCreateurLigneDevis(OpportuniteModel opportuniteModel) {
        final Map<String, String> mapNomPrenomUtilisateur = new HashMap<String, String>();
        if (opportuniteModel.getListeDevis() != null) {
            for (DevisModel devisModel : opportuniteModel.getListeDevis()) {
                if (devisModel.getListeLigneDevis() != null) {
                    for (LigneDevisModel ligneDevisModel : devisModel.getListeLigneDevis()) {
                        if (ligneDevisModel.getGestionnaire() != null && ligneDevisModel.getGestionnaire().getIdentifiant() != null) {
                            final String nomPrenom =
                                recupererNomPrenomUtilisateur(ligneDevisModel.getGestionnaire().getIdentifiant().toString(), mapNomPrenomUtilisateur);
                            ligneDevisModel.getGestionnaire().setLibelle(nomPrenom);
                        }
                    }
                }
            }
        }
    }

    /**
     * Récupère le nom et le prénom de l'utilisateur.
     * @param mapNomPrenomUtilisateur la map contenant les utilisateurs déjà traités
     * @return le nom et prénom de l'utilisateur
     */
    private String recupererNomPrenomUtilisateur(String eidUtilisateur, Map<String, String> mapNomPrenomUtilisateur) {
        final String nomPrenom = mapNomPrenomUtilisateur.get(eidUtilisateur);
        if (nomPrenom != null) {
            return nomPrenom;
        }
        else {
            // on recupere la ressource
            final RessourceDto ressource = ressourceService.rechercherRessourceParEid(eidUtilisateur);
            final StringBuffer nomPrenomUtilisateur = new StringBuffer("");
            if (ressource.getNom() != null) {
                nomPrenomUtilisateur.append(ressource.getNom());
            }
            if (ressource.getPrenom() != null) {
                if (nomPrenomUtilisateur.length() > 0) {
                    nomPrenomUtilisateur.append(" ");
                }
                nomPrenomUtilisateur.append(ressource.getPrenom());
            }
            mapNomPrenomUtilisateur.put(eidUtilisateur, nomPrenomUtilisateur.toString());
            return nomPrenomUtilisateur.toString();
        }
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherModesPaiementParCriteres(IdentifiantLibelleGwt criteres) {
        final List<IdentifiantLibelleDto> liste =
            tarificateurService.rechercherMoyensPaiementParCriteres((IdentifiantLibelleDto) mapperDozerBean.map(criteres, IdentifiantLibelleDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherJoursPaiementParCriteres(IdentifiantLibelleGwt criteres) {
        final List<IdentifiantLibelleDto> liste =
            tarificateurService.rechercherJoursPaiementParCriteres((IdentifiantLibelleDto) mapperDozerBean.map(criteres, IdentifiantLibelleDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherPeriodicitesPaiementParCriteres(IdentifiantLibelleGwt criteres) {
        final List<IdentifiantLibelleDto> liste =
            tarificateurService.rechercherPeriodicitesPaiementParCriteres((IdentifiantLibelleDto) mapperDozerBean.map(criteres, IdentifiantLibelleDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public void modifierDevis(DevisModificationModel devisModificationModel) {
        final DevisModificationDto devisModificationDto = mapperDozerBean.map(devisModificationModel, DevisModificationDto.class);
        tarificateurService.modifierDevis(devisModificationDto);
    }

    /**
     * Définit la valeur de utilisateurService.
     * @param utilisateurService la nouvelle valeur de utilisateurService
     */
    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Set the dimensionSquareService value.
     * @param dimensionService the dimensionSquareService to set
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * Définition de ressourceService.
     * @param ressourceService the ressourceService to set
     */
    public void setRessourceService(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpportuniteModel getOpportuniteByNumTransaction(String numeroTransaction) {
        final OpportuniteDto opportuniteDto = tarificateurService.getOpportuniteByNumTransaction(numeroTransaction);
        return mapperDozerBean.map(opportuniteDto, OpportuniteModel.class);
    }

    /**
     * Setter function.
     * @param utilisateurMappingService the utilisateurMappingService to set
     */
    public void setUtilisateurMappingService(UtilisateurMappingService utilisateurMappingService) {
        this.utilisateurMappingService = utilisateurMappingService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean peutRechercherOppParTransaction() {
        Boolean peutRechercherOppParTransaction = false;
        // On récupère les rôles à partir des habilitations
        final String roleSquareCompta = utilisateurMappingService.getConstanteRoleSquareCompta();

        // L'utilisateur connecté peut rechercher une opportunité par numéro de transaction
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            final GrantedAuthority[] authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(roleSquareCompta)) {
                    // Si il a le rôle comptable
                    peutRechercherOppParTransaction = true;
                    break;
                }
            }
        }
        return peutRechercherOppParTransaction;
    }

    /**
     * Set the tarificateurPersonneService value.
     * @param tarificateurPersonneService the tarificateurPersonneService to set
     */
    public void setTarificateurPersonneService(TarificateurPersonneService tarificateurPersonneService) {
        this.tarificateurPersonneService = tarificateurPersonneService;
    }

    @Override
    public Long construireFichiersDevisAImprimer(Long idDevis, String lignesDevis, String modeledDevis, String beneficiaires, String referenceOpportunite) {
        return ImprimerDevisServlet.getInstance().construireFichiers(idDevis, lignesDevis, modeledDevis, beneficiaires, referenceOpportunite);
    }

    @Override
    public DevisModel getDevis(Long id) {
        final CriteresRechercheDevisDto criteres = new CriteresRechercheDevisDto();
        criteres.setIdDevis(id);
        criteres.setOrigineSiteWeb(null);
        final List<DevisModel> listeDevis = mapperDozerBean.mapList(tarificateurService.getListeDevisByCriteres(criteres), DevisModel.class);
        if (listeDevis.isEmpty()) {
            return null;
        }
        else {
            return listeDevis.get(0);
        }
    }
}
