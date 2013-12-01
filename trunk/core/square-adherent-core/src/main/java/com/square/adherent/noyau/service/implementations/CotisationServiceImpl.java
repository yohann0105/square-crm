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
package com.square.adherent.noyau.service.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.dao.interfaces.contrat.ContratMoyenPaiementDao;
import com.square.adherent.noyau.dao.interfaces.contrat.GarantieDao;
import com.square.adherent.noyau.dto.DimensionAdherentCriteresRechercheDto;
import com.square.adherent.noyau.dto.IdentifiantLibelleOrdreDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheGarantieDto;
import com.square.adherent.noyau.dto.cotisation.CodeAiaLibelleDto;
import com.square.adherent.noyau.dto.cotisation.CotisationDto;
import com.square.adherent.noyau.dto.cotisation.CotisationsCriteresRechercheDto;
import com.square.adherent.noyau.dto.cotisation.DetailCotisationDto;
import com.square.adherent.noyau.dto.cotisation.DetailEncaissementDto;
import com.square.adherent.noyau.dto.cotisation.PersonneCotisationDto;
import com.square.adherent.noyau.dto.cotisation.RetourCotisationDto;
import com.square.adherent.noyau.dto.produit.CriteresInfosProduitsDto;
import com.square.adherent.noyau.dto.produit.InfosProduitDto;
import com.square.adherent.noyau.model.data.contrat.Garantie;
import com.square.adherent.noyau.model.dimension.ContratMoyenPaiement;
import com.square.adherent.noyau.plugin.ConstantesAiaPlugin;
import com.square.adherent.noyau.plugin.CotisationsPlugin;
import com.square.adherent.noyau.plugin.dto.cotisation.CotisationPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.CotisationsCriteresPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.DetailCotisationPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.DetailEncaissementPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.ErreurRetourPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.RetourCotisationsPluginDto;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.adherent.noyau.service.interfaces.CotisationService;
import com.square.adherent.noyau.util.MessageKeyUtil;
import com.square.core.model.dto.PersonneDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.service.interfaces.ProduitService;

/**
 * Implémentation de CotisationService.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CotisationServiceImpl implements CotisationService {

    private CotisationsPlugin cotisationsPlugin;

    private MapperDozerBean mapperDozerBean;

    private ConstantesAiaPlugin constantesAiaPlugin;

    private ContratMoyenPaiementDao contratMoyenPaiementDao;

    private PersonnePhysiqueService personnePhysiqueService;

    private GarantieDao garantieDao;

    private ProduitService produitService;

    private MessageSourceUtil messageSourceUtil;

    private ContratService contratService;

    private AdherentMappingService adherentMappingService;

    private static final String LOGIN_COTISATION_AIA = "batch00";

    @Override
    public List<IdentifiantLibelleDto> rechercherModesPaiementParCriteres(String libelle) {
         //on recupere la liste de moyens de paiement AIA
        final List<String> moyensPaiementAia = new ArrayList<String>();
        //FIXME : Que doit-on insérer dans le fichier de constantes pour ces valeurs.
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementBvr());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementCarteBancaire());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementCetip());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementCheque());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementChequeAvecCoupon());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementChequeManuel());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementDomiciliation());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementDSSContributions());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementEspeces());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementInterne());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementMandat());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementOrdreMultiple());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementOrdrePermanent());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementPrecompte());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementPrelevement());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementPrelevementAccelere());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementTip());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementVCS());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementVirement());
        moyensPaiementAia.add(constantesAiaPlugin.getMoyenPaiementVirementEncaissement());

        // on recupere les modes de paiements
        final DimensionAdherentCriteresRechercheDto criteres = new DimensionAdherentCriteresRechercheDto();
        criteres.setLibelle(libelle);
        final List<ContratMoyenPaiement> listeMoyensPaiement = contratMoyenPaiementDao.getMoyensPaiementContratByCriteres(criteres);

        // on filtre suivant les valeurs disponibles dans les constantes aia
        final List<IdentifiantLibelleDto> listeFinale = new ArrayList<IdentifiantLibelleDto>();
        for (ContratMoyenPaiement moyenPaiement : listeMoyensPaiement) {
            if (moyensPaiementAia.contains(moyenPaiement.getIdentifiantExterieur())) {
                if (StringUtils.isBlank(libelle) || moyenPaiement.getLibelle().toLowerCase().startsWith(libelle.toLowerCase())) {
                    listeFinale.add((IdentifiantLibelleDto) mapperDozerBean.map(moyenPaiement, IdentifiantLibelleDto.class));
                }
            }
        }
        return new ArrayList<IdentifiantLibelleDto>();
    }

    private List<CodeAiaLibelleDto> getListeStatutsCotisation() {
        final List<CodeAiaLibelleDto> liste = new ArrayList<CodeAiaLibelleDto>();
        //FIXME open source
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getStatutPrimeAnnulante(), constantesAiaPlugin.getStatutPrimeLibelleAnnulante()));
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getStatutPrimeAnnulee(), constantesAiaPlugin.getStatutPrimeLibelleAnnulee()));
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getStatutPrimeBloquee(), constantesAiaPlugin.getStatutPrimeLibelleBloquee()));
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getStatutPrimeDemandee(), constantesAiaPlugin.getStatutPrimeLibelleDemandee()));
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getStatutPrimeEnInstance(), constantesAiaPlugin.getStatutPrimeLibelleEnInstance()));
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getStatutPrimeRemboursee(), constantesAiaPlugin.getStatutPrimeLibelleRemboursee()));
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getStatutPrimeSoldee(), constantesAiaPlugin.getStatutPrimeLibelleSoldee()));
        return liste;
    }

    private Map<String, CodeAiaLibelleDto> getMapStatutsEncaissement() {
        final List<CodeAiaLibelleDto> liste = new ArrayList<CodeAiaLibelleDto>();
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getSituationEncaissementEnInstance(), constantesAiaPlugin.getSituationEncaissementLibelleEnInstance()));
        liste.add(new CodeAiaLibelleDto(constantesAiaPlugin.getSituationEncaissementSoldee(), constantesAiaPlugin.getSituationEncaissementLibelleSoldee()));

        final Map<String, CodeAiaLibelleDto> map = new HashMap<String, CodeAiaLibelleDto>();
        for (CodeAiaLibelleDto codeAiaLibelleDto : liste) {
            map.put(codeAiaLibelleDto.getCodeAia(), codeAiaLibelleDto);
        }
        return map;
    }

    private Map<String, CodeAiaLibelleDto> getMapStatutsCotisation() {
        final List<CodeAiaLibelleDto> liste = getListeStatutsCotisation();

        final Map<String, CodeAiaLibelleDto> map = new HashMap<String, CodeAiaLibelleDto>();
        for (CodeAiaLibelleDto codeAiaLibelleDto : liste) {
            map.put(codeAiaLibelleDto.getCodeAia(), codeAiaLibelleDto);
        }
        return map;
    }

    @Override
    public List<CodeAiaLibelleDto> rechercherSituationsParCriteres(String libelle) {
        final List<CodeAiaLibelleDto> liste = new ArrayList<CodeAiaLibelleDto>();
        for (CodeAiaLibelleDto situationCotisationDto : getListeStatutsCotisation()) {
            if (StringUtils.isBlank(libelle) || situationCotisationDto.getLibelle().toLowerCase().startsWith(libelle.toLowerCase())) {
                liste.add(situationCotisationDto);
            }
        }

        // on tri par ordre alphabétique
        Collections.sort(liste, new Comparator<CodeAiaLibelleDto>() {
            @Override
            public int compare(CodeAiaLibelleDto o1, CodeAiaLibelleDto o2) {
                return o1.getLibelle().compareTo(o2.getLibelle());
            }
        });

        return liste;
    }

    @Override
    public RetourCotisationDto recupererCotisations(final RemotePagingCriteriasDto<CotisationsCriteresRechercheDto> criteresCotisations) {
        final CotisationsCriteresRechercheDto criteriasCotisations = criteresCotisations.getCriterias();
        if (criteriasCotisations.getUidPersonne() == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_OBLIGATOIRE));
        }
        // on recupere l'eid de la personne
        final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(criteriasCotisations.getUidPersonne());
        if (personne == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANT, new String[] {criteriasCotisations.getUidPersonne()
                    .toString()}));
        }

        // si aucune date, on filtre les 6 derniers mois en mode normal et 2 prochains mois en mode simulation
        Calendar dateDebut = criteriasCotisations.getDateDebut();
        Calendar dateFin = criteriasCotisations.getDateFin();
        if (criteriasCotisations.getDateDebut() == null) {
            if (criteriasCotisations.isSimulation()) {
                dateDebut = Calendar.getInstance();
            }
            else {
                dateDebut = Calendar.getInstance();
                dateDebut.add(Calendar.MONTH, -6);
            }
        }
        if (criteriasCotisations.getDateFin() == null) {
            if (criteriasCotisations.isSimulation()) {
                dateFin = Calendar.getInstance();
                dateFin.add(Calendar.MONTH, 2);
            }
            else {
                dateFin = Calendar.getInstance();
            }
        }

        // on recupere les cotisations dans AIA
        final CotisationsCriteresPluginDto criteresCotisationsAia = new CotisationsCriteresPluginDto();
        criteresCotisationsAia.setPersonne(personne.getIdext());
        criteresCotisationsAia.setDateEffet(Calendar.getInstance());
        criteresCotisationsAia.setDateDebut(dateDebut);
        criteresCotisationsAia.setDateFin(dateFin);
        criteresCotisationsAia.setContrat(criteriasCotisations.getContrat());
        if (criteriasCotisations.getIdModePaiement() != null) {
            final DimensionAdherentCriteresRechercheDto criteresMoyensPaiement = new DimensionAdherentCriteresRechercheDto();
            criteresMoyensPaiement.setId(criteriasCotisations.getIdModePaiement());
            final List<ContratMoyenPaiement> listeMoyensPaiement = contratMoyenPaiementDao.getMoyensPaiementContratByCriteres(criteresMoyensPaiement);
            if (listeMoyensPaiement.size() != 1) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOYEN_PAIEMENT_INEXISTANT, new Long[] {criteriasCotisations
                        .getIdModePaiement()}));
            }
            criteresCotisationsAia.setModePaiement(listeMoyensPaiement.get(0).getIdentifiantExterieur());
        }
        if (!criteriasCotisations.isSimulation()) {
            criteresCotisationsAia.setMontantMax(criteriasCotisations.getMontantMax());
            criteresCotisationsAia.setMontantMin(criteriasCotisations.getMontantMin());
            if (criteriasCotisations.isSimulation()) {
                criteresCotisationsAia.setOperation(CotisationsCriteresPluginDto.OPERATION_SIMULATION);
            }
            else {
                criteresCotisationsAia.setOperation(CotisationsCriteresPluginDto.OPERATION_COTISATION);
            }
            if (criteriasCotisations.getSituation() != null) {
                criteresCotisationsAia.setSituation(criteriasCotisations.getSituation().getCodeAia());
            }
        }
        //FIXME open source : validation test testRécupérerCotisations
        final RetourCotisationsPluginDto retourAia = cotisationsPlugin.recupererCotisations(LOGIN_COTISATION_AIA, criteresCotisationsAia);
        if (retourAia.getErreurs() != null && retourAia.getErreurs().size() > 0) {
            final StringBuffer message = new StringBuffer();
            for (ErreurRetourPluginDto erreur : retourAia.getErreurs()) {
                if (message.length() > 0) {
                    message.append("<br />");
                }
                message.append(erreur.getLabel());
            }
            throw new BusinessException(message.toString());
        }

        final Long idFamilleGarantieSante = adherentMappingService.getIdFamilleGarantieSante();

        final Map<String, CodeAiaLibelleDto> mapStatutsEncaissement = getMapStatutsEncaissement();
        final Map<String, CodeAiaLibelleDto> mapStatutsCotisation = getMapStatutsCotisation();

        final int nombreTotal = retourAia.getListeCotisations().size();
        int compteur = 0;

        final List<CotisationDto> listeLignes = new ArrayList<CotisationDto>();
        for (CotisationPluginDto cotisationAiaDto : retourAia.getListeCotisations()) {
            ContratMoyenPaiement moyenPaiement = null;
            if (StringUtils.isNotBlank(cotisationAiaDto.getMoyenPaiement())) {
                // on recupere le mode de paiement par son eid
                final DimensionAdherentCriteresRechercheDto criteresMoyensPaiement = new DimensionAdherentCriteresRechercheDto();
                criteresMoyensPaiement.setIdentifiantExterieur(cotisationAiaDto.getMoyenPaiement());
                final List<ContratMoyenPaiement> listeMoyensPaiement = contratMoyenPaiementDao.getMoyensPaiementContratByCriteres(criteresMoyensPaiement);
                if (listeMoyensPaiement.size() != 1) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOYEN_PAIEMENT_INEXISTANT, new String[] {cotisationAiaDto
                            .getMoyenPaiement()}));
                }
                moyenPaiement = listeMoyensPaiement.get(0);
            }

            final CotisationDto cotisationDto = new CotisationDto();
            cotisationDto.setMontant(cotisationAiaDto.getMontant());
            cotisationDto.setMontantRegle(cotisationAiaDto.getMontantRegle());
            cotisationDto.setSituation(mapStatutsCotisation.get(cotisationAiaDto.getStatut()));
            cotisationDto.setDateDebut(cotisationAiaDto.getDateDebut());
            cotisationDto.setDateFin(cotisationAiaDto.getDateFin());
            cotisationDto.setJourPaiement(cotisationAiaDto.getJourPaiement());
            if (moyenPaiement != null) {
                cotisationDto.setModePaiement(new IdentifiantLibelleDto(moyenPaiement.getId(), moyenPaiement.getLibelle()));
            }

            final Map<String, Boolean> mapTypeContrats = new HashMap<String, Boolean>();

            // on parcours les détails de cotisation
            final List<DetailCotisationDto> listeDetailsCotisation = new ArrayList<DetailCotisationDto>();
            for (DetailCotisationPluginDto detailCotisationAia : cotisationAiaDto.getListeDetailsCotisation()) {
                // on recupere le bénéficiaire
                final PersonneDto beneficiaire =
                    personnePhysiqueService.rechercherPersonneParIdentifiantExterieur(detailCotisationAia.getIdentifiantBeneficiaire());
                if (beneficiaire == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANT, new String[] {detailCotisationAia
                            .getIdentifiantBeneficiaire()}));
                }
                final PersonneCotisationDto personneCotisation = mapperDozerBean.map(beneficiaire, PersonneCotisationDto.class);
                if (personneCotisation.getNom() != null) {
                    personneCotisation.setNom(personneCotisation.getNom().toUpperCase());
                }
                if (personneCotisation.getPrenom() != null) {
                    personneCotisation.setPrenom(StringUtils.capitalize(personneCotisation.getPrenom()));
                }

                final DetailCotisationDto detailCotisationDto = new DetailCotisationDto();
                // on recupere la garantie
                final CritereRechercheGarantieDto criteresGarantie = new CritereRechercheGarantieDto();
                criteresGarantie.setEid(detailCotisationAia.getIdentifiantGarantie());
                final List<Garantie> listeGaranties = garantieDao.getListeGarantiesByCriteres(criteresGarantie);
                if (listeGaranties.size() == 1) {
                    final Garantie garantie = listeGaranties.get(0);
                    // on recupere le produit
                    final ProduitCriteresDto criteresProduit = new ProduitCriteresDto();
                    criteresProduit.setGarantieAia(garantie.getLibelleGarantieGestion());
                    criteresProduit.setProduitAia(garantie.getLibelleProduitGestion());
                    final List<ProduitDto> listeProduits = produitService.getListeProduits(criteresProduit);
                    if (listeProduits == null || listeProduits.size() != 1) {
                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_RECUPERATION_PRODUIT_IMPOSSIBLE));
                    }

                    // on recupere le type du contrat
                    Boolean isContratSante = mapTypeContrats.get(garantie.getContrat().getNumeroContrat());
                    if (isContratSante == null) {
                        // on recupere les produits du contrat
                        final CriteresInfosProduitsDto criteresInfosProduitsDto = new CriteresInfosProduitsDto();
                        criteresInfosProduitsDto.setIdContrat(garantie.getContrat().getId());
                        final List<InfosProduitDto> listeInfosProduits = contratService.getInfosProduits(criteresInfosProduitsDto);
                        isContratSante = false;
                        for (InfosProduitDto infosProduitDto : listeInfosProduits) {
                            if (infosProduitDto.getIdFamilleGarantie().equals(idFamilleGarantieSante)) {
                                isContratSante = true;
                                break;
                            }
                        }
                        mapTypeContrats.put(garantie.getContrat().getNumeroContrat(), isContratSante);
                    }
                    detailCotisationDto.setGarantieRole((IdentifiantLibelleOrdreDto) mapperDozerBean.map(garantie.getRole(), IdentifiantLibelleOrdreDto.class));
                    detailCotisationDto.setContrat(garantie.getContrat().getNumeroContrat());
                    detailCotisationDto.setGarantie(listeProduits.get(0).getLibelleCommercial());
                    detailCotisationDto.setEidGarantie(garantie.getEid());
                    if (garantie.getFamille() != null) {
                        detailCotisationDto.setFamilleGarantie((IdentifiantLibelleOrdreDto) mapperDozerBean.map(garantie.getFamille(),
                            IdentifiantLibelleOrdreDto.class));
                    }
                    detailCotisationDto.setContratSante(isContratSante);
                }
                else {
                    // on recherche le numéro de contrat grace à l'eid du contrat récupéré via AIA
                    final CritereRechercheContratDto criteres = new CritereRechercheContratDto();
                    criteres.setContratEid(detailCotisationAia.getContratEid());
                    criteres.setIdAssure(personne.getIdentifiant());
                    final List<ContratSimpleDto> contrats = contratService.getContratsSimpleByCriteres(criteres);
                    if (contrats == null || contrats.size() != 1) {
                        throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CONTRAT_INEXISTANT));
                    }
                    detailCotisationDto.setContrat(contrats.get(0).getNumeroContrat());
                    detailCotisationDto.setEidGarantie(detailCotisationAia.getIdentifiantGarantie());
                }
                detailCotisationDto.setBeneficiaire(personneCotisation);
                detailCotisationDto.setLibelle(detailCotisationAia.getLibelle());
                detailCotisationDto.setMontant(detailCotisationAia.getMontant());
                detailCotisationDto.setTypeEcheance(detailCotisationAia.getTypeEcheance());
                detailCotisationDto.setTypePrime(detailCotisationAia.getTypePrime());
                detailCotisationDto.setDateDebut(detailCotisationAia.getDateDebut());
                detailCotisationDto.setDateFin(detailCotisationAia.getDateFin());
                listeDetailsCotisation.add(detailCotisationDto);
            }
            cotisationDto.setListeDetailsCotisation(listeDetailsCotisation);
            // on trie les détails
            Collections.sort(cotisationDto.getListeDetailsCotisation(), new Comparator<DetailCotisationDto>() {
                @Override
                public int compare(DetailCotisationDto o1, DetailCotisationDto o2) {
                    // tri par famille de contrat
                    if (o1.getContratSante() == null) {
                        return 1;
                    }
                    if (o2.getContratSante() == null) {
                        return -1;
                    }
                    if (o1.getContratSante().compareTo(o2.getContratSante()) != 0) {
                        return o2.getContratSante().compareTo(o1.getContratSante());
                    }
                    // tri par libelle de contrat
                    if (o1.getContrat() == null) {
                        return 1;
                    }
                    if (o2.getContrat() == null) {
                        return -1;
                    }
                    if (o1.getContrat().compareTo(o2.getContrat()) != 0) {
                        return o2.getContrat().compareTo(o1.getContrat());
                    }
                    // tri par famille de garantie
                    if (o1.getFamilleGarantie() == null || o1.getFamilleGarantie().getOrdre() == null) {
                        return 1;
                    }
                    if (o2.getFamilleGarantie() == null || o2.getFamilleGarantie().getOrdre() == null) {
                        return -1;
                    }
                    if (o1.getFamilleGarantie().getOrdre().compareTo(o2.getFamilleGarantie().getOrdre()) != 0) {
                        return o1.getFamilleGarantie().getOrdre().compareTo(o2.getFamilleGarantie().getOrdre());
                    }
                    // tri par libelle de garantie
                    if (o1.getGarantie() == null) {
                        return 1;
                    }
                    if (o2.getGarantie() == null) {
                        return -1;
                    }
                    if (o1.getGarantie().compareTo(o2.getGarantie()) != 0) {
                        return o2.getGarantie().compareTo(o1.getGarantie());
                    }
                    // tri par role de garantie
                    if (o1.getGarantieRole() == null || o1.getGarantieRole().getOrdre() == null) {
                        return 1;
                    }
                    if (o2.getGarantieRole() == null || o2.getGarantieRole().getOrdre() == null) {
                        return -1;
                    }
                    if (o1.getGarantieRole().getOrdre().compareTo(o2.getGarantieRole().getOrdre()) != 0) {
                        return o2.getGarantieRole().getOrdre().compareTo(o1.getGarantieRole().getOrdre());
                    }
                    // tri par date de naissance
                    if (o1.getBeneficiaire() == null || o1.getBeneficiaire().getDateNaissance() == null) {
                        return 1;
                    }
                    if (o2.getBeneficiaire() == null || o2.getBeneficiaire().getDateNaissance() == null) {
                        return -1;
                    }
                    if (o1.getBeneficiaire().getDateNaissance().compareTo(o2.getBeneficiaire().getDateNaissance()) != 0) {
                        // tri desc
                        return o1.getBeneficiaire().getDateNaissance().compareTo(o2.getBeneficiaire().getDateNaissance());
                    }
                    // tri par prenom
                    if (o1.getBeneficiaire() == null || o1.getBeneficiaire().getPrenom() == null) {
                        return 1;
                    }
                    if (o2.getBeneficiaire() == null || o2.getBeneficiaire().getPrenom() == null) {
                        return -1;
                    }
                    if (o1.getBeneficiaire().getPrenom().compareTo(o2.getBeneficiaire().getPrenom()) != 0) {
                        return o1.getBeneficiaire().getPrenom().compareTo(o2.getBeneficiaire().getPrenom());
                    }
                    return 0;
                }
            });

            // on parcours les détails d'encaissements
            if (cotisationAiaDto.getListeDetailsEncaissement() != null) {
                final List<DetailEncaissementDto> listeDetailsEncaissement = new ArrayList<DetailEncaissementDto>();
                for (DetailEncaissementPluginDto detailEncaissementAia : cotisationAiaDto.getListeDetailsEncaissement()) {
                    moyenPaiement = null;
                    if (StringUtils.isNotBlank(detailEncaissementAia.getMoyenPaiement())) {
                        // on recupere le mode de paiement par son eid
                        final DimensionAdherentCriteresRechercheDto criteresMoyensPaiement = new DimensionAdherentCriteresRechercheDto();
                        criteresMoyensPaiement.setIdentifiantExterieur(detailEncaissementAia.getMoyenPaiement());
                        final List<ContratMoyenPaiement> listeMoyensPaiement =
                            contratMoyenPaiementDao.getMoyensPaiementContratByCriteres(criteresMoyensPaiement);
                        if (listeMoyensPaiement.size() != 1) {
                            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_MOYEN_PAIEMENT_INEXISTANT,
                                new String[] {detailEncaissementAia.getMoyenPaiement()}));
                        }
                        moyenPaiement = listeMoyensPaiement.get(0);
                    }

                    final DetailEncaissementDto detailEncaissementDto = new DetailEncaissementDto();
                    detailEncaissementDto.setBanque(detailEncaissementAia.getBanque());
                    detailEncaissementDto.setCompte(detailEncaissementAia.getCompte());
                    detailEncaissementDto.setDate(detailEncaissementAia.getDate());
                    detailEncaissementDto.setDateRejet(detailEncaissementAia.getDateRejet());
                    detailEncaissementDto.setJourPaiement(detailEncaissementAia.getJourPaiement());
                    detailEncaissementDto.setMontant(detailEncaissementAia.getMontant());
                    detailEncaissementDto.setMontantNonAffecte(detailEncaissementAia.getMontantNonAffecte());
                    detailEncaissementDto.setMotifRejet(detailEncaissementAia.getMotifRejet());
                    if (moyenPaiement != null) {
                        detailEncaissementDto.setMoyenPaiement(new IdentifiantLibelleDto(moyenPaiement.getId(), moyenPaiement.getLibelle()));
                    }
                    detailEncaissementDto.setNumeroCheque(detailEncaissementAia.getNumeroCheque());
                    detailEncaissementDto.setStatut(mapStatutsEncaissement.get(detailEncaissementAia.getStatut()));
                    listeDetailsEncaissement.add(detailEncaissementDto);
                }
                cotisationDto.setListeDetailsEncaissement(listeDetailsEncaissement);
                // on trie les détails
                Collections.sort(cotisationDto.getListeDetailsEncaissement(), new Comparator<DetailEncaissementDto>() {
                    @Override
                    public int compare(DetailEncaissementDto o1, DetailEncaissementDto o2) {
                        if (o1.getDate() == null) {
                            return -1;
                        }
                        if (o2.getDate() == null) {
                            return 1;
                        }
                        // tri date croissant
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });
            }

            if (criteresCotisations.getFirstResult() <= compteur && compteur < (criteresCotisations.getFirstResult() + criteresCotisations.getMaxResult())) {
                listeLignes.add(cotisationDto);
            }

            compteur++;
        }

        final String orderCotisationDateDebut = adherentMappingService.getOrderCotisationDateDebut();
        final String orderCotisationMontant = adherentMappingService.getOrderCotisationMontant();
        final String orderCotisationMontantRegle = adherentMappingService.getOrderCotisationMontantRegle();
        final String orderCotisationSituation = adherentMappingService.getOrderCotisationSituation();

        if (criteresCotisations.getListeSorts() != null && criteresCotisations.getListeSorts().size() > 0) {
            // on trie les détails
            Collections.sort(listeLignes, new Comparator<CotisationDto>() {
                @Override
                public int compare(CotisationDto o1, CotisationDto o2) {
                    for (RemotePagingSort remotePagingSort : criteresCotisations.getListeSorts()) {
                        if (remotePagingSort.getSortField().equals(orderCotisationDateDebut) && o1.getDateDebut().compareTo(o2.getDateDebut()) != 0) {
                            if (remotePagingSort.getSortAsc() == RemotePagingSort.REMOTE_PAGING_SORT_ASC) {
                                return o1.getDateDebut().compareTo(o2.getDateDebut());
                            }
                            else {
                                return o2.getDateDebut().compareTo(o1.getDateDebut());
                            }
                        }
                        if (remotePagingSort.getSortField().equals(orderCotisationMontant) && o1.getMontant().compareTo(o2.getMontant()) != 0) {
                            if (remotePagingSort.getSortAsc() == RemotePagingSort.REMOTE_PAGING_SORT_ASC) {
                                return o1.getMontant().compareTo(o2.getMontant());
                            }
                            else {
                                return o2.getMontant().compareTo(o1.getMontant());
                            }
                        }
                        if (remotePagingSort.getSortField().equals(orderCotisationMontantRegle) && o1.getMontantRegle().compareTo(o2.getMontantRegle()) != 0) {
                            if (remotePagingSort.getSortAsc() == RemotePagingSort.REMOTE_PAGING_SORT_ASC) {
                                return o1.getMontantRegle().compareTo(o2.getMontantRegle());
                            }
                            else {
                                return o2.getMontantRegle().compareTo(o1.getMontantRegle());
                            }
                        }
                        if (remotePagingSort.getSortField().equals(orderCotisationSituation)
                            && o1.getSituation().getLibelle().compareTo(o2.getSituation().getLibelle()) != 0) {
                            if (remotePagingSort.getSortAsc() == RemotePagingSort.REMOTE_PAGING_SORT_ASC) {
                                return o1.getSituation().getLibelle().compareTo(o2.getSituation().getLibelle());
                            }
                            else {
                                return o2.getSituation().getLibelle().compareTo(o1.getSituation().getLibelle());
                            }
                        }
                    }
                    return 0;
                }
            });
        }

        final RemotePagingResultsDto<CotisationDto> results = new RemotePagingResultsDto<CotisationDto>();
        results.setListResults(listeLignes);
        results.setTotalResults(nombreTotal);

        final RetourCotisationDto retour = new RetourCotisationDto();
        retour.setResultatsCotisation(results);
        retour.setSolde(retourAia.getSolde());
        return retour;
    }

    /**
     * Set the cotisationsPlugin value.
     * @param cotisationsPlugin the cotisationsPlugin to set
     */
    public void setCotisationsPlugin(CotisationsPlugin cotisationsPlugin) {
        this.cotisationsPlugin = cotisationsPlugin;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the contratMoyenPaiementDao value.
     * @param contratMoyenPaiementDao the contratMoyenPaiementDao to set
     */
    public void setContratMoyenPaiementDao(ContratMoyenPaiementDao contratMoyenPaiementDao) {
        this.contratMoyenPaiementDao = contratMoyenPaiementDao;
    }

    /**
     * Set the constantesAiaPlugin value.
     * @param constantesAiaPlugin the constantesAiaPlugin to set
     */
    public void setConstantesAiaPlugin(ConstantesAiaPlugin constantesAiaPlugin) {
        this.constantesAiaPlugin = constantesAiaPlugin;
    }

    /**
     * Set the personnePhysiqueService value.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Set the messageSourceUtil value.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Set the produitService value.
     * @param produitService the produitService to set
     */
    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Set the garantieDao value.
     * @param garantieDao the garantieDao to set
     */
    public void setGarantieDao(GarantieDao garantieDao) {
        this.garantieDao = garantieDao;
    }

    /**
     * Set the contratService value.
     * @param contratService the contratService to set
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
