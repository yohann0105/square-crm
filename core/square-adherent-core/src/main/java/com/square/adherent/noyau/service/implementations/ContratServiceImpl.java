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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.dao.interfaces.contrat.ContratDao;
import com.square.adherent.noyau.dao.interfaces.contrat.GarantieDao;
import com.square.adherent.noyau.dao.interfaces.prestation.ReserveBancoDao;
import com.square.adherent.noyau.dto.adherent.contrat.AjustementDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratCollectifCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.CoordonneesBancairesDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheGarantieDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantiePersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosBanqueDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosGarantieBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosGarantiePersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosPaiementDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosPaiementPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ListeContratsDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentDto;
import com.square.adherent.noyau.dto.adherent.contrat.RatioPrestationCotisationDto;
import com.square.adherent.noyau.dto.adherent.contrat.RecapitulatifGarantiesContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.RecapitulatifPopulationDto;
import com.square.adherent.noyau.dto.adherent.contrat.ReserveBancoDto;
import com.square.adherent.noyau.dto.adherent.contrat.SyntheseContratPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurDto;
import com.square.adherent.noyau.dto.adherent.contrat.ValeursStatutsPopulationDto;
import com.square.adherent.noyau.dto.produit.CriteresInfosProduitsDto;
import com.square.adherent.noyau.dto.produit.InfosProduitDto;
import com.square.adherent.noyau.dto.produit.ReserveProduitBancoDto;
import com.square.adherent.noyau.model.data.contrat.AjustementTarif;
import com.square.adherent.noyau.model.data.contrat.Contrat;
import com.square.adherent.noyau.model.data.contrat.Garantie;
import com.square.adherent.noyau.model.data.contrat.SyntheseContrat;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.adherent.noyau.util.MessageKeyUtil;
import com.square.core.model.dto.PersonneBaseDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.price.core.dto.ProduitCriteresDto;
import com.square.price.core.dto.ProduitDto;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.TarificateurMappingService;

/**
 * Implémentation de ContratService.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratServiceImpl implements ContratService {

    private static final char[] DELIMITERS = {' ', '-', '_'};

    /** DAO Contrat. */
    private ContratDao contratDao;

    /** DAO Garantie. */
    private GarantieDao garantieDao;

    /** DAO ReserveBanco. */
    private ReserveBancoDao reserveBancoDao;

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    /** Service des constantes des adhérents. */
    private AdherentMappingService adherentMappingService;

    /** Service des personnes physiques. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service des personnes. */
    private PersonneService personneService;

    /** Service des produits. */
    private ProduitService produitService;

    /** Service des constantes de mapping du tarificateur. */
    private TarificateurMappingService tarificateurMappingService;

    /** Gestionnaire des messages. */
    private MessageSourceUtil messageSourceUtil;

    /** Délai pour considérer une garantie santé comme inactive. */
    private int delaiMoisInactiviteGarantieSante;

    /** Service de mapping Square. */
    private SquareMappingService squareMappingService;

    /** Logger. */
    private Logger logger = Logger.getLogger(ContratServiceImpl.class);

    @Override
    public ContratSimpleDto getContratSantePersonne(Long idPersonne) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_CONTRAT_SANTE_PERSONNE, new String[] {String.valueOf(idPersonne)}));
        // On recherche le contrat en cours de la personne
        Contrat contrat = contratDao.getContratSantePersonneEnCours(idPersonne);
        // Si la personne n'a aucun contrat en cours, on recherche un contrat résilié dans un délai de X mois
        if (contrat == null) {
            final Calendar dateInactivite = Calendar.getInstance();
            final int jour = dateInactivite.get(Calendar.DAY_OF_MONTH);
            final int mois = dateInactivite.get(Calendar.MONTH);
            final int annee = dateInactivite.get(Calendar.YEAR);
            dateInactivite.clear();
            dateInactivite.set(annee, mois, jour);
            dateInactivite.add(Calendar.MONTH, -delaiMoisInactiviteGarantieSante);
            contrat = contratDao.getContratSantePersonneInactifXMois(idPersonne, dateInactivite);
        }
        // Si la personne n'a aucun contrat en cours et aucun contrat résilié
        if (contrat == null) {
            // On recherche un contrat à venir pour cette personne
            contrat = contratDao.getContratSantePersonneAVenir(idPersonne);
        }
        return mapperDozerBean.map(contrat, ContratSimpleDto.class);
    }

    @Override
    public List<InfosProduitDto> getInfosProduits(CriteresInfosProduitsDto criteresInfosProduitsDto) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_RECUP_INFO_PRODUIT_PAR_CRITERES));
        if (criteresInfosProduitsDto.getNumeroClient() != null && !"".equals(criteresInfosProduitsDto.getNumeroClient())) {
            // Récupération de l'indentifiant de la personne
            final PersonneCriteresRechercheDto criteriasPersonne = new PersonneCriteresRechercheDto();
            criteriasPersonne.setNumeroClient(criteresInfosProduitsDto.getNumeroClient());
            final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteresPersonne =
                new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criteriasPersonne, 0, 1);
            final RemotePagingResultsDto<PersonneSimpleDto> listePersonnes = personnePhysiqueService.rechercherPersonneFullTextParCriteres(criteresPersonne);
            if (listePersonnes == null || listePersonnes.getListResults() == null || listePersonnes.getListResults().size() != 1) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_NUM_CLIENT_INEXISTANTE, new String[] {criteresInfosProduitsDto
                        .getNumeroClient()}));
            }
            criteresInfosProduitsDto.setIdAssure(listePersonnes.getListResults().get(0).getId());
        }
        final List<InfosProduitDto> listeInfosProduit = garantieDao.getInfosProduits(criteresInfosProduitsDto);
        if (listeInfosProduit != null && listeInfosProduit.size() > 0) {
            // Récupération des infos de la personne rattachée aux produits.
            for (InfosProduitDto infosProduit : listeInfosProduit) {
                final PersonneDto personne = personnePhysiqueService.rechercherPersonneParIdentifiant(infosProduit.getIdPersonne());
                if (personne == null) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_PERSONNE_INEXISTANT, new String[] {infosProduit.getIdPersonne()
                            .toString()}));
                }
                infosProduit.setNomPersonne(personne.getNom());
                infosProduit.setPrenomPersonne(personne.getPrenom());
                infosProduit.setDateNaissancePersonne(personne.getDateNaissance());
            }
        }
        return listeInfosProduit;
    }

    @Override
    public ContratDto getContrat(Long uidContrat) {
        final Contrat contrat = contratDao.getContratById(uidContrat);
        if (contrat == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CONTRAT_INEXISTANT));
        }
        final ContratDto contratDto = mapperDozerBean.map(contrat, ContratDto.class);

        // Récupération des ajustements du contrat
        final List<AjustementTarif> listeAjustements = contratDao.getListeAjustementsFromContrat(uidContrat);
        final List<AjustementDto> listeAjustementsDto = mapperDozerBean.mapList(listeAjustements, AjustementDto.class);
        if (listeAjustementsDto != null && listeAjustementsDto.size() > 0) {
            for (AjustementDto ajustementDto : listeAjustementsDto) {
                final String[] decoupeNumContrat = contrat.getNumeroContrat().split("/");
                if (decoupeNumContrat.length > 1) {
                    final String reference = decoupeNumContrat[decoupeNumContrat.length - 2] + "/" + decoupeNumContrat[decoupeNumContrat.length - 1];
                    ajustementDto.setReference(reference);
                }
            }
        }
        contratDto.setListeAjustements(listeAjustementsDto);

        // Récupération de la liste des bénéficiaires
        final List<Long> listUidContrat = new ArrayList<Long>();
        listUidContrat.add(uidContrat);
        final List<GarantieBeneficiaireDto> listeBeneficiaires = contratDao.getListeBeneficiairesFromContrats(listUidContrat, contrat.getUidAssure(), false);

        // Récupération de la liste des garanties
        final CritereRechercheGarantieDto criteres = new CritereRechercheGarantieDto();
        criteres.setListeIdsContrat(listUidContrat);
        // si contrat en cours, on recupere les garanties en cours
        if (contrat.getStatut().getId().equals(adherentMappingService.getIdStatutContratEnCours())) {
            criteres.setIdStatutGarantie(adherentMappingService.getIdStatutGarantieEnCours());
        }
        // si contrat futur, on recupere les garanties futur
        else if (contrat.getStatut().getId().equals(adherentMappingService.getIdStatutContratFutur())) {
            criteres.setIdStatutGarantie(adherentMappingService.getIdStatutGarantieFutur());
        }
        // si contrat résilié, on recupere les garanties résilié
        else if (contrat.getStatut().getId().equals(adherentMappingService.getIdStatutContratResilie())) {
            criteres.setIdStatutGarantie(adherentMappingService.getIdStatutGarantieResiliee());
        }
        final List<Garantie> listeGaranties = garantieDao.getListeGarantiesByCriteres(criteres);

        // Récupération du récapitulatif des garanties du contrat
        final RecapitulatifGarantiesContratDto recapitulatif = construireRecapitulatif(listeBeneficiaires, listeGaranties);
        contratDto.setRecapitulatifGarantiesContrat(recapitulatif);

        // Récupération de la liste des garanties
        final List<GarantieDto> listeGarantieDtos = new ArrayList<GarantieDto>();
        final List<Integer> listeProduitsDejaPresents = new ArrayList<Integer>();
        if (listeGaranties != null && listeGaranties.size() > 0) {
            final Integer idCritereMontantSouscrit = tarificateurMappingService.getIdentifiantCriterePrimeSouscrite();
            for (Garantie garantie : listeGaranties) {
                final GarantieDto garantieDto = new GarantieDto();
                garantieDto.setId(garantie.getId());
                final ProduitCriteresDto critereProduit = new ProduitCriteresDto();
                critereProduit.setProduitAia(garantie.getLibelleProduitGestion());
                critereProduit.setGarantieAia(garantie.getLibelleGarantieGestion());
                final List<ProduitDto> listeProduits = produitService.getListeProduits(critereProduit);
                if (listeProduits == null || listeProduits.size() != 1) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_RECUPERATION_PRODUIT_IMPOSSIBLE));
                }
                final ProduitDto produit = listeProduits.get(0);
                if (!listeProduitsDejaPresents.contains(produit.getIdentifiant())) {
                    listeProduitsDejaPresents.add(produit.getIdentifiant());
                    garantieDto.setIdProduit(produit.getIdentifiant());
                    garantieDto.setLibelle(produit.getLibelleCommercial());
                    garantieDto.setCodeGeneration(garantie.getCodeGeneration());
                    garantieDto.setCodeTarif(garantie.getCodeTarif());
                    garantieDto.setLibelleGarantieGestion(garantie.getLibelleGarantieGestion());
                    garantieDto.setLibelleProduitGestion(garantie.getLibelleProduitGestion());
                    final IdentifiantLibelleDto statutDto = mapperDozerBean.map(garantie.getStatut(), IdentifiantLibelleDto.class);
                    garantieDto.setStatut(statutDto);
                    garantieDto.setLoiMadelin(garantie.getLoiMadelin());

                    // On définit le montant souscrit si le produit est issu de la gamme prévoyance et si il a le critère montant soucrit
                    final Boolean isProduitPrevoyance = produitService.isItGammePrevoyance(produit.getGamme().getIdentifiant());
                    final Boolean hasCritereMontantSouscrit = produitService.isProduitPossedeCritere(produit.getIdentifiant(), idCritereMontantSouscrit);
                    if (isProduitPrevoyance && hasCritereMontantSouscrit) {
                        garantieDto.setMontantSouscrit(garantie.getMontantSouscrit());
                    }

                    listeGarantieDtos.add(garantieDto);
                }
            }
        }
        contratDto.setListeGaranties(listeGarantieDtos);

        // Récupération des infos de paiement de cotisation
        final InfosPaiementDto infosPaiementCotisation = new InfosPaiementDto();
        contratDto.setInfosPaiementCotisation(infosPaiementCotisation);
        final IdentifiantLibelleDto moyenPaiementCotisation = mapperDozerBean.map(contrat.getMoyenPaiementCotisation(), IdentifiantLibelleDto.class);
        infosPaiementCotisation.setMoyenPaiement(moyenPaiementCotisation);
        final IdentifiantLibelleDto frequencePaiementCotisation = mapperDozerBean.map(contrat.getFrequencePaiementCotisation(), IdentifiantLibelleDto.class);
        infosPaiementCotisation.setFrequencePaiement(frequencePaiementCotisation);
        infosPaiementCotisation.setJourPaiement(contrat.getJourPaiementCotisation());

        // Récupération des infos de paiement de prestation
        final InfosPaiementDto infosPaiementPrestation = new InfosPaiementDto();
        final IdentifiantLibelleDto moyenPaiementPrestation = mapperDozerBean.map(contrat.getMoyenPaiementPrestation(), IdentifiantLibelleDto.class);
        infosPaiementPrestation.setMoyenPaiement(moyenPaiementPrestation);
        contratDto.setInfosPaiementPrestation(infosPaiementPrestation);

        // Récupération des informations du souscripteur
        final IdentifiantLibelleDto souscripteurDto = new IdentifiantLibelleDto();
        final PersonneBaseDto souscripteur = personneService.rechercherPersonneParId(contrat.getUidSouscripteur());
        // Si aucune personne n'est trouvée
        if (souscripteur == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SOUSCRIPTEUR_CONTRAT_INEXISTANT, new String[] {
                contrat.getUidSouscripteur().toString(), contrat.getNumeroContrat()}));
        }
        if (souscripteur instanceof PersonneDto) {
            final PersonneDto souscripteurPPDto = (PersonneDto) souscripteur;
            souscripteurDto.setIdentifiant(souscripteurPPDto.getIdentifiant());
            souscripteurDto.setLibelle(souscripteurPPDto.getPrenom() + " " + souscripteurPPDto.getNom());
        } else {
            final PersonneMoraleDto souscripteurPMDto = (PersonneMoraleDto) souscripteur;
            souscripteurDto.setIdentifiant(souscripteurPMDto.getId());
            souscripteurDto.setLibelle(souscripteurPMDto.getRaisonSociale());
        }
        contratDto.setSouscripteur(souscripteurDto);
        if (contrat.getBanqueCotisation() != null) {
            final InfosBanqueDto infosBanqueCotisation = new InfosBanqueDto();
            infosBanqueCotisation.setCle(contrat.getBanqueCotisation().getCle());
            infosBanqueCotisation.setCodeBanque(contrat.getBanqueCotisation().getCodeBanque());
            infosBanqueCotisation.setCodeGuichet(contrat.getBanqueCotisation().getCodeGuichet());
            infosBanqueCotisation.setCodeCompte(contrat.getBanqueCotisation().getCompte());
            contratDto.setInfoBanqueCotisation(infosBanqueCotisation);
        }
        if (contrat.getBanquePrestation() != null) {
            final InfosBanqueDto infosBanquePrestation = new InfosBanqueDto();
            infosBanquePrestation.setCle(contrat.getBanquePrestation().getCle());
            infosBanquePrestation.setCodeBanque(contrat.getBanquePrestation().getCodeBanque());
            infosBanquePrestation.setCodeGuichet(contrat.getBanquePrestation().getCodeGuichet());
            infosBanquePrestation.setCodeCompte(contrat.getBanquePrestation().getCompte());
            contratDto.setInfoBanquePrestation(infosBanquePrestation);
        }
        // Récupération des informations du type de payeur
        final IdentifiantLibelleDto typePayeurDto = mapperDozerBean.map(contrat.getTypePayeur(), IdentifiantLibelleDto.class);
        contratDto.setTypePayeur(typePayeurDto);

        return contratDto;
    }

    @Override
    public ListeContratsDto getListeContrats(Long uidPersonne) {
        final ListeContratsDto listeContratsDto = new ListeContratsDto();

        // Récupération du dernier contrat santé en cours ou, à défaut de contrat santé, du dernier contrat prévoyance en cours,
        // ou à défaut de contrat prévoyance, du dernier contrat santé futur ou, à défaut du contrat santé futur, du dernier contrat prévoyance futur
        // ou à défaut du dernier contrat résilié
        final Long idStatutContratEnCours = adherentMappingService.getIdStatutContratEnCours();
        final Long idStatutContratFutur = adherentMappingService.getIdStatutContratFutur();
        Contrat contratPrinc =
            contratDao.getDernierContratPersonneByNatureContrat(uidPersonne, adherentMappingService.getIdNatureContratSante(), idStatutContratEnCours);
        if (contratPrinc == null) {
            contratPrinc =
                contratDao.getDernierContratPersonneByNatureContrat(uidPersonne, adherentMappingService.getIdNatureContratPrevoyance(), idStatutContratEnCours);
        }
        if (contratPrinc == null) {
            contratPrinc =
                contratDao.getDernierContratPersonneByNatureContrat(uidPersonne, adherentMappingService.getIdNatureContratSante(), idStatutContratFutur);
        }
        if (contratPrinc == null) {
            contratPrinc =
                contratDao.getDernierContratPersonneByNatureContrat(uidPersonne, adherentMappingService.getIdNatureContratPrevoyance(), idStatutContratFutur);
        }
        if (contratPrinc == null) {
            contratPrinc = contratDao.getDernierContratRadiePersonne(uidPersonne);
        }

        if (contratPrinc != null) {

            // Récupération des informations de synthèse du contrat
            final SyntheseContrat syntheseContrat = contratDao.getSyntheseContrat(uidPersonne);
            if (syntheseContrat == null) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SYNTHESE_CONTRAT_INEXISTANTE));
            }

            final InfosContratsDto infosContrats = new InfosContratsDto();
            infosContrats.setDatePremiereMutualisation(syntheseContrat.getDatePremiereMutualisation());
            infosContrats.setNbAnneesFidelite(syntheseContrat.getNbAnneesFidelite());
            infosContrats.setNbMoisFidelite(syntheseContrat.getNbMoisFidelite());
            infosContrats.setTeletransmission(syntheseContrat.getTeletransmission());
            final List<ReserveProduitBancoDto> listeReservesProduitBanco = reserveBancoDao.getListeReservesBancoByAdherent(uidPersonne);
            final List<ReserveBancoDto> listeReservesBanco = mapperDozerBean.mapList(listeReservesProduitBanco, ReserveBancoDto.class);
            infosContrats.setListeReservesBanco(listeReservesBanco);

            final IdentifiantLibelleDto segmentDto = mapperDozerBean.map(contratPrinc.getSegment(), IdentifiantLibelleDto.class);
            infosContrats.setSegment(segmentDto);
            final IdentifiantLibelleDto statutDto = mapperDozerBean.map(contratPrinc.getStatut(), IdentifiantLibelleDto.class);
            infosContrats.setStatut(statutDto);
            if (contratPrinc.getTypePayeur() != null) {
                infosContrats.setGestionDuContrat(contratPrinc.getTypePayeur().getLibelle());
            }
            infosContrats.setGestionnaire(contratPrinc.getGestionnaire());
            infosContrats.setPopulation(contratPrinc.getPopulation());

            // Récupération du dernier contrat radié
            final Contrat dernierContratRadie = contratDao.getDernierContratRadiePersonne(uidPersonne);
            if (dernierContratRadie != null
                && !(dernierContratRadie.getMotifResiliation() != null && (dernierContratRadie.getMotifResiliation().getId().equals(
                    adherentMappingService.getIdMotifResiliationSansEffet()) || dernierContratRadie.getMotifResiliation().getId().equals(
                    adherentMappingService.getIdMotifResiliationErreurDeSaisie())))) {
                infosContrats.setDateDerniereRadiation(dernierContratRadie.getDateResiliation());
                if (dernierContratRadie.getMotifResiliation() != null) {
                    infosContrats.setMotifDerniereRadiation(dernierContratRadie.getMotifResiliation().getLibelle());
                }
            }

            // Récupération des ratios sur les 3 dernières années
            // Récupération de la liste des bénéficiaires du contrat principal
            final List<Long> listeIdContratPrinc = new ArrayList<Long>();
            listeIdContratPrinc.add(contratPrinc.getId());
            final List<GarantieBeneficiaireDto> listeBeneficiairesContratPrinc =
                contratDao.getListeBeneficiairesFromContrats(listeIdContratPrinc, uidPersonne, true);
            final List<RatioPrestationCotisationDto> listeRatios = new ArrayList<RatioPrestationCotisationDto>();
            if (listeBeneficiairesContratPrinc != null && listeBeneficiairesContratPrinc.size() > 0) {
                for (GarantieBeneficiaireDto beneficiaire : listeBeneficiairesContratPrinc) {
                    final List<RatioPrestationCotisationDto> listRatioDto = contratDao.getRatioPrestationCotisationPersonne(beneficiaire.getIdBenef());
                    if (listRatioDto != null) {
                        for (RatioPrestationCotisationDto ratioDto : listRatioDto) {
                            final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(ratioDto.getPersonne().getIdentifiant());
                            ratioDto.getPersonne().setLibelle(personneDto.getNom() + " " + personneDto.getPrenom());
                            listeRatios.add(ratioDto);
                        }
                    }
                }
            }
            infosContrats.setListeRatiosPrestationCotisation(listeRatios);

            // Récupération des contrats en cours
            final CritereRechercheContratDto criteresContrat = new CritereRechercheContratDto();
            criteresContrat.setIdAssure(uidPersonne);
            criteresContrat.setHasContratEnCours(true);
            final List<Contrat> listeContrats = contratDao.getContratsByCriteres(criteresContrat);
            final List<Long> listeIdsContrats = new ArrayList<Long>();
            if (listeContrats != null && listeContrats.size() > 0) {
                for (Contrat contrat : listeContrats) {
                    listeIdsContrats.add(contrat.getId());
                }
            }
            // Récupération de la liste des bénéficiaires
            final List<GarantieBeneficiaireDto> listeBeneficiaires = contratDao.getListeBeneficiairesFromContrats(listeIdsContrats, uidPersonne, true);
            // Récupération de la liste des garanties des contrats
            final List<Garantie> listeGaranties = new ArrayList<Garantie>();
            if (listeContrats != null && listeContrats.size() > 0) {
                final CritereRechercheGarantieDto criteresGarantie = new CritereRechercheGarantieDto();
                criteresGarantie.setListeIdsContrat(listeIdsContrats);
                criteresGarantie.setHasGarantieEnCours(true);
                listeGaranties.addAll(garantieDao.getListeGarantiesByCriteres(criteresGarantie));
            }
            final RecapitulatifGarantiesContratDto listeGarantiesContrat = construireRecapitulatif(listeBeneficiaires, listeGaranties);
            infosContrats.setListeGarantiesContrat(listeGarantiesContrat);

            listeContratsDto.setInfosContrats(infosContrats);
        }

        // Récupération de la liste des contrats
        final CritereRechercheContratDto criteresContrat = new CritereRechercheContratDto();
        criteresContrat.setIdAssure(uidPersonne);
        final List<Contrat> listeContrats = contratDao.getContratsByCriteres(criteresContrat);
        if (listeContrats != null && listeContrats.size() > 0) {
            final List<ContratSimpleDto> listeContratsSimples = mapperDozerBean.mapList(listeContrats, ContratSimpleDto.class);
            listeContratsDto.setListeContrats(listeContratsSimples);
        }
        return listeContratsDto;
    }

    @Override
    public InfosContratsBeneficiaireDto hasContratAssureBeneficiaire(Long idAssure, Long idBeneficiaire) {
        final InfosContratsBeneficiaireDto infosContratsBenef = new InfosContratsBeneficiaireDto();
        final List<Garantie> listeGaranties = garantieDao.getListeGarantiesBeneficiaire(idAssure, idBeneficiaire);
        infosContratsBenef.setHasContrats(!listeGaranties.isEmpty());
        boolean hasContratsActifs = false;
        for (Garantie garantie : listeGaranties) {
            final Long idStatutGarantie = garantie.getStatut().getId();
            // Si la garantie a le statut en cours
            // ou le statut résilé mais avec une date de résiliation future
            if (adherentMappingService.getIdStatutGarantieEnCours().equals(idStatutGarantie)
                || (adherentMappingService.getIdStatutGarantieResiliee().equals(idStatutGarantie) && garantie.getDateResiliation()
                        .after(Calendar.getInstance()))) {
                hasContratsActifs = true;
            }
        }
        infosContratsBenef.setHasContratsActifs(hasContratsActifs);
        return infosContratsBenef;
    }

    /**
     * Construit le récapitulatif des garanties d'un contrat.
     * @param listeBeneficiaires la liste des bénéficiaires.
     * @param listeGaranties la liste des garanties.
     * @return le récapitulatif.
     */
    private RecapitulatifGarantiesContratDto construireRecapitulatif(List<GarantieBeneficiaireDto> listeBeneficiaires, List<Garantie> listeGaranties) {
        final RecapitulatifGarantiesContratDto recapitulatif = new RecapitulatifGarantiesContratDto();
        final List<GarantieBeneficiaireDto> listeBenefsRecap = new ArrayList<GarantieBeneficiaireDto>();
        for (GarantieBeneficiaireDto benef : listeBeneficiaires) {
            // Récupération du nom et du prénom du bénéficiaire
            final PersonneDto personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(benef.getIdBenef());
            if (personneDto == null) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_BENEFICIAIRE_CONTRAT_INEXISTANT));
            }
            benef.setNom(personneDto.getNom().toUpperCase());
            benef.setPrenom(WordUtils.capitalizeFully(personneDto.getPrenom(), DELIMITERS));
            listeBenefsRecap.add(benef);
        }
        recapitulatif.setListeBeneficiaires(listeBenefsRecap);
        final List<GarantieSimpleDto> listeGarantiesSimples = new ArrayList<GarantieSimpleDto>();
        final List<Integer> listeProduitsDejaPresents = new ArrayList<Integer>();
        if (listeGaranties != null && listeGaranties.size() > 0) {
            for (Garantie garantie : listeGaranties) {
                final GarantieSimpleDto garantieSimpleDto = new GarantieSimpleDto();
                garantieSimpleDto.setId(garantie.getId());
                garantieSimpleDto.setIdContrat(garantie.getContrat().getId());
                final ProduitCriteresDto critereProduit = new ProduitCriteresDto();
                critereProduit.setProduitAia(garantie.getLibelleProduitGestion());
                critereProduit.setGarantieAia(garantie.getLibelleGarantieGestion());
                final List<ProduitDto> listeProduits = produitService.getListeProduits(critereProduit);
                if (listeProduits == null || listeProduits.size() != 1) {
                    throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_RECUPERATION_PRODUIT_IMPOSSIBLE));
                }
                final ProduitDto produit = listeProduits.get(0);
                if (!listeProduitsDejaPresents.contains(produit.getIdentifiant())) {
                    listeProduitsDejaPresents.add(produit.getIdentifiant());
                    garantieSimpleDto.setIdGamme(Long.valueOf(produit.getGamme().getIdentifiant()));
                    garantieSimpleDto.setIdProduit(produit.getIdentifiant());
                    garantieSimpleDto.setLibelle(produit.getLibelleCommercial());
                    if (produit.getFormulePresta() != null) {
                        garantieSimpleDto.setIdFormulePresta(produit.getFormulePresta().getIdentifiant());
                        garantieSimpleDto.setLibelleFormulePresta(produit.getFormulePresta().getLibelle());
                    }
                    final IdentifiantLibelleDto segmentDto = mapperDozerBean.map(garantie.getSegment(), IdentifiantLibelleDto.class);
                    garantieSimpleDto.setSegment(segmentDto);
                    final List<InfosGarantieBeneficiaireDto> listeInfosGarantiesBeneficiaires = new ArrayList<InfosGarantieBeneficiaireDto>();
                    final InfosGarantieBeneficiaireDto infosGarantieBeneficiaireDto = new InfosGarantieBeneficiaireDto();
                    infosGarantieBeneficiaireDto.setIdBeneficiaire(garantie.getUidBeneficiaire());
                    infosGarantieBeneficiaireDto.setDateAdhesion(garantie.getDateAdhesion());
                    infosGarantieBeneficiaireDto.setDateResiliation(garantie.getDateResiliation());
                    final IdentifiantLibelleDto statutDto = mapperDozerBean.map(garantie.getStatut(), IdentifiantLibelleDto.class);
                    infosGarantieBeneficiaireDto.setStatut(statutDto);
                    listeInfosGarantiesBeneficiaires.add(infosGarantieBeneficiaireDto);
                    garantieSimpleDto.setListeInfosGarantiesBeneficiaires(listeInfosGarantiesBeneficiaires);
                    listeGarantiesSimples.add(garantieSimpleDto);
                } else {
                    for (GarantieSimpleDto garantieDto : listeGarantiesSimples) {
                        if (garantieDto.getIdProduit().equals(produit.getIdentifiant())) {
                            Integer indexBenefExistant = null;
                            for (int i = 0; i < garantieDto.getListeInfosGarantiesBeneficiaires().size(); i++) {
                                if (garantieDto.getListeInfosGarantiesBeneficiaires().get(i).getIdBeneficiaire().equals(garantie.getUidBeneficiaire())) {
                                    indexBenefExistant = i;
                                    break;
                                }
                            }
                            final IdentifiantLibelleDto statutDto = mapperDozerBean.map(garantie.getStatut(), IdentifiantLibelleDto.class);
                            if (indexBenefExistant != null) {
                                final InfosGarantieBeneficiaireDto infoGarantieBenefDto =
                                    garantieDto.getListeInfosGarantiesBeneficiaires().get(indexBenefExistant);
                                if (infoGarantieBenefDto.getDateAdhesion().before(garantie.getDateAdhesion())) {
                                    infoGarantieBenefDto.setDateAdhesion(garantie.getDateAdhesion());
                                    infoGarantieBenefDto.setDateResiliation(garantie.getDateResiliation());
                                    infoGarantieBenefDto.setStatut(statutDto);
                                }
                            } else {
                                final InfosGarantieBeneficiaireDto infosGarantieBeneficiaireDto = new InfosGarantieBeneficiaireDto();
                                infosGarantieBeneficiaireDto.setIdBeneficiaire(garantie.getUidBeneficiaire());
                                infosGarantieBeneficiaireDto.setDateAdhesion(garantie.getDateAdhesion());
                                infosGarantieBeneficiaireDto.setDateResiliation(garantie.getDateResiliation());
                                infosGarantieBeneficiaireDto.setStatut(statutDto);
                                garantieDto.getListeInfosGarantiesBeneficiaires().add(infosGarantieBeneficiaireDto);
                            }
                        }
                    }
                }
            }
        }
        recapitulatif.setListeGaranties(listeGarantiesSimples);
        return recapitulatif;
    }

    @Override
    public String getGestionnaireContratCollectif(Long uidPersonne) {
        return contratDao.getGestionnaireContratCollectif(uidPersonne);
    }

    @Override
    public ContratPersonneMoraleDto getContratPersonneMorale(Long uidContrat) {
        final Contrat contrat = contratDao.getContratById(uidContrat);
        if (contrat == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_CONTRAT_INEXISTANT));
        }
        final ContratPersonneMoraleDto contratDto = mapperDozerBean.map(contrat, ContratPersonneMoraleDto.class);
        final IdentifiantLibelleDto typeGestionDto = mapperDozerBean.map(contrat.getTypePayeur(), IdentifiantLibelleDto.class);
        contratDto.setTypeGestion(typeGestionDto);
        final List<String> listeProduitsGestion = contratDao.getListeProduitsGestionFromContrat(contrat.getIdentifiantExterieur());
        if (listeProduitsGestion != null && listeProduitsGestion.size() > 0) {
            String produitGestion = listeProduitsGestion.get(0);
            if (listeProduitsGestion.size() > 1) {
                for (int i = 1; i < listeProduitsGestion.size(); i++) {
                    produitGestion = produitGestion + ", " + listeProduitsGestion.get(i);
                }
            }
            contratDto.setProduitGestion(produitGestion);
        }
        contratDto.setNbAdherents(contratDao.getNombreAdherentsContrat(contrat.getIdentifiantExterieur()));
        contratDto.setNbBeneficiaires(contratDao.getNombreBeneficiairesContrat(contrat.getIdentifiantExterieur()));
        InfosPaiementPersonneMoraleDto infosPaiement = new InfosPaiementPersonneMoraleDto();
        if (contrat.getBanqueCotisation() != null) {
            infosPaiement = mapperDozerBean.map(contrat.getBanqueCotisation(), InfosPaiementPersonneMoraleDto.class);
        }
        if (contrat.getTermePaiement() != null) {
            infosPaiement.setTypeEcheance(contrat.getTermePaiement().getLibelle());
        }
        if (contrat.getFrequencePaiementCotisation() != null) {
            final IdentifiantLibelleDto frequencePaiementDto = mapperDozerBean.map(contrat.getFrequencePaiementCotisation(), IdentifiantLibelleDto.class);
            infosPaiement.setFrequencePaiement(frequencePaiementDto);
        }
        if (contrat.getMoyenPaiementCotisation() != null) {
            final IdentifiantLibelleDto moyenPaiementDto = mapperDozerBean.map(contrat.getMoyenPaiementCotisation(), IdentifiantLibelleDto.class);
            infosPaiement.setMoyenPaiement(moyenPaiementDto);
        }
        infosPaiement.setJourPaiement(contrat.getJourPaiementCotisation());
        contratDto.setInfosPaiement(infosPaiement);

        // Construction du récapitulatif des populations
        final RecapitulatifPopulationDto recapitulatifPopulation = contratDao.getRecapitulatifPopulationContrat(contrat.getIdentifiantExterieur());
        // Tri en fonction des populations
        if (recapitulatifPopulation.getListeValeursPopulation() != null && recapitulatifPopulation.getListeValeursPopulation().size() > 1) {
            // Récupération de l'ordre de la population
            for (ValeursStatutsPopulationDto valeurStatutPopulation : recapitulatifPopulation.getListeValeursPopulation()) {
                final Integer ordre = adherentMappingService.getOrdrePopulation(valeurStatutPopulation.getLibellePopulation());
                valeurStatutPopulation.setOrdrePopulation(ordre);
            }
            // Tri
            final Comparator<ValeursStatutsPopulationDto> comparatorPopulation = new Comparator<ValeursStatutsPopulationDto>() {
                @Override
                public int compare(ValeursStatutsPopulationDto o1, ValeursStatutsPopulationDto o2) {
                    if (o1 == null || o1.getOrdrePopulation() == null) {
                        return 1;
                    } else if (o2 == null || o2.getOrdrePopulation() == null) {
                        return -1;
                    } else {
                        return o1.getOrdrePopulation().compareTo(o2.getOrdrePopulation());
                    }
                }
            };
            Collections.sort(recapitulatifPopulation.getListeValeursPopulation(), comparatorPopulation);
        }
        contratDto.setRecapitulatifPopulation(recapitulatifPopulation);

        // Récupération de la liste des garanties
        final List<Garantie> listeGaranties = garantieDao.getListeGarantiesContratPersonneMorale(contrat.getIdentifiantExterieur());
        final List<GarantiePersonneMoraleDto> listeGarantiesDto = new ArrayList<GarantiePersonneMoraleDto>();
        final List<String> listeProduitsDejaPresents = new ArrayList<String>();
        if (listeGaranties != null && listeGaranties.size() > 0) {
            for (Garantie garantie : listeGaranties) {
                final ProduitCriteresDto critereProduit = new ProduitCriteresDto();
                critereProduit.setProduitAia(garantie.getLibelleProduitGestion());
                critereProduit.setGarantieAia(garantie.getLibelleGarantieGestion());
                final List<ProduitDto> listeProduits = produitService.getListeProduits(critereProduit);
                if (listeProduits == null || listeProduits.size() != 1) {
                    logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_RECUP_PRODUIT,
                    		new String[] {garantie.getLibelleProduitGestion(), garantie.getLibelleGarantieGestion()}));
                    // On ne traite pas cette garantie car le produit n'a pas été trouvé
                    contratDto.getListeProduitsNonTrouves().add(messageSourceUtil.get(MessageKeyUtil.MESSAGE_LIBELLE_PRODUIT_NON_TROUVE,
                    		new String[] {garantie.getLibelleProduitGestion(), garantie.getLibelleGarantieGestion()}));
                    continue;
                }
                final ProduitDto produit = listeProduits.get(0);
                if (!listeProduitsDejaPresents.contains(produit.getLibelleCommercial())) {
                    listeProduitsDejaPresents.add(produit.getLibelleCommercial());
                    final GarantiePersonneMoraleDto garantieDto = new GarantiePersonneMoraleDto();
                    garantieDto.setId(garantie.getId());
                    garantieDto.setIdProduit(produit.getIdentifiant());
                    garantieDto.setLibelle(produit.getLibelleCommercial());
                    if (produit.getFormulePresta() != null) {
                        garantieDto.setIdFormulePresta(produit.getFormulePresta().getIdentifiant());
                        garantieDto.setLibelleFormulePresta(produit.getFormulePresta().getLibelle());
                    }
                    final IdentifiantLibelleDto statut = (IdentifiantLibelleDto) mapperDozerBean.map(garantie.getStatut(), IdentifiantLibelleDto.class);
                    garantieDto.setStatut(statut);
                    garantieDto.setIdNatureProduit(produit.getGamme().getIdCategorie());
                    garantieDto.setDateSignature(garantie.getDateSignature());

                    final List<InfosGarantiePersonneMoraleDto> listeInfosGarantie = new ArrayList<InfosGarantiePersonneMoraleDto>();
                    final InfosGarantiePersonneMoraleDto infosGarantie = mapperDozerBean.map(garantie, InfosGarantiePersonneMoraleDto.class);
                    // On initialise le statut de la garantie au statut de la première garantie trouvée
                    infosGarantie.setStatut(statut);
                    listeInfosGarantie.add(infosGarantie);
                    garantieDto.setListeInfosGarantie(listeInfosGarantie);
                    listeGarantiesDto.add(garantieDto);
                } else {
                    if (listeGarantiesDto != null && listeGarantiesDto.size() > 0) {
                        for (GarantiePersonneMoraleDto garantieDto : listeGarantiesDto) {
                            if (produit.getLibelleCommercial().equals(garantieDto.getLibelle())) {
                                // Mis à jour des champs pour le tri si nécessaire
                                final Long idStatutGarantieExistant = garantieDto.getStatut().getIdentifiant();
                                final Long idNouveauStatutGarantie = garantie.getStatut().getId();
                                if (adherentMappingService.getNiveauImportanceStatutGarantie(idStatutGarantieExistant) < adherentMappingService
                                        .getNiveauImportanceStatutGarantie(idNouveauStatutGarantie)) {
                                    final IdentifiantLibelleDto statut = mapperDozerBean.map(garantie.getStatut(), IdentifiantLibelleDto.class);
                                    garantieDto.setStatut(statut);
                                }
                                if (produit.getGamme().getIdCategorie() < garantieDto.getIdNatureProduit()) {
                                    garantieDto.setIdNatureProduit(produit.getGamme().getIdCategorie());
                                }
                                if (garantie.getDateSignature().before(garantieDto.getDateSignature())) {
                                    garantieDto.setDateSignature(garantie.getDateSignature());
                                }
                                // Gestion des infos de garantie
                                if (garantieDto.getListeInfosGarantie() != null && garantieDto.getListeInfosGarantie().size() > 0) {
                                    final InfosGarantiePersonneMoraleDto infosGarantie = mapperDozerBean.map(garantie, InfosGarantiePersonneMoraleDto.class);
                                    infosGarantie.setStatut((IdentifiantLibelleDto) mapperDozerBean.map(garantie.getStatut(), IdentifiantLibelleDto.class));
                                    boolean isExiste = false;
                                    for (InfosGarantiePersonneMoraleDto infos : garantieDto.getListeInfosGarantie()) {
                                        // On ajoute si ça n'est pas un "doublon"
                                        if (infosGarantie.getCodeTarif().equals(infos.getCodeTarif())
                                            && infosGarantie.getLibelleGarantieGestion().equals(infos.getLibelleGarantieGestion())
                                            && infosGarantie.getLibellePopulation().equals(infos.getLibellePopulation())
                                            && (infosGarantie.getMontantSouscrit() == null && infos.getMontantSouscrit() == null || (infosGarantie
                                                    .getMontantSouscrit() != null
                                                && infos.getMontantSouscrit() != null && infosGarantie.getMontantSouscrit().equals(infos.getMontantSouscrit())))) {
                                            isExiste = true;
                                            // On met à jour le statut d'une garantie si le nouveau statut est plus important que l'ancien
                                            final Long idStatutExistant = infos.getStatut().getIdentifiant();
                                            final Long idNouveauStatut = infosGarantie.getStatut().getIdentifiant();
                                            if (adherentMappingService.getNiveauImportanceStatutGarantie(idStatutExistant) < adherentMappingService
                                                    .getNiveauImportanceStatutGarantie(idNouveauStatut)) {
                                                infos.setStatut(infosGarantie.getStatut());
                                            }
                                            break;
                                        }
                                    }
                                    if (!isExiste) {
                                        garantieDto.getListeInfosGarantie().add(infosGarantie);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (listeGarantiesDto != null && listeGarantiesDto.size() > 1) {
            // Tri des infos de garantie dans chaque garantie
            final Comparator<InfosGarantiePersonneMoraleDto> comparatorListeInfosGarantie = new Comparator<InfosGarantiePersonneMoraleDto>() {
                @Override
                public int compare(InfosGarantiePersonneMoraleDto o1, InfosGarantiePersonneMoraleDto o2) {
                    if (o1 == null) {
                        return -1;
                    } else if (o2 == null) {
                        return 1;
                    } else {
                        final IdentifiantLibelleDto statut1 = o1.getStatut();
                        final IdentifiantLibelleDto statut2 = o2.getStatut();
                        if (statut1 == null || statut1.getIdentifiant() == null) {
                            return -1;
                        } else if (statut2 == null || statut2.getIdentifiant() == null) {
                            return 1;
                        } else {
                            // On récupère le niveau d'importance des statuts de chaque garantie
                            final Integer niveauImportanceStatut1 = adherentMappingService.getNiveauImportanceStatutGarantie(statut1.getIdentifiant());
                            final Integer niveauImportanceStatut2 = adherentMappingService.getNiveauImportanceStatutGarantie(statut2.getIdentifiant());
                            int compareStatut = niveauImportanceStatut1.compareTo(niveauImportanceStatut2);
                            // On inverse le résultat de comparaison
                            // Niveau d'importance du statut 1 > au niveau d'importance du statut 2
                            if (compareStatut == 1) {
                                // La garantie 1 doit donc apparaitre avant la garantie 2
                                compareStatut = -1;
                            } else if (compareStatut == -1) { // Niveau d'importance du statut 1 < au niveau d'importance du statut 2
                                // La garantie 2 doit donc apparaitre avant la garantie 1
                                compareStatut = 1;
                            }
                            if (compareStatut == 0) {
                                final String libellePopulation1 = o1.getLibellePopulation();
                                final String libellePopulation2 = o2.getLibellePopulation();
                                if (StringUtils.isBlank(libellePopulation1)) {
                                    return -1;
                                } else if (StringUtils.isBlank(libellePopulation2)) {
                                    return 1;
                                } else {
                                    // On compare par rapport au libellé population de la garantie
                                    final Integer ordreO1 = adherentMappingService.getOrdrePopulation(libellePopulation1);
                                    final Integer ordreO2 = adherentMappingService.getOrdrePopulation(o2.getLibellePopulation());
                                    if (ordreO1 == null) {
                                        return -1;
                                    } else if (ordreO2 == null) {
                                        return 1;
                                    } else {
                                        return ordreO1.compareTo(ordreO2);
                                    }
                                }
                            } else {
                                return compareStatut;
                            }
                        }
                    }
                }
            };
            for (GarantiePersonneMoraleDto garantie : listeGarantiesDto) {
                Collections.sort(garantie.getListeInfosGarantie(), comparatorListeInfosGarantie);
            }

            // Tri des garanties
            final Comparator<GarantiePersonneMoraleDto> comparator = new Comparator<GarantiePersonneMoraleDto>() {
                @Override
                public int compare(GarantiePersonneMoraleDto o1, GarantiePersonneMoraleDto o2) {
                    if (o1 == null) {
                        return 1;
                    } else if (o2 == null) {
                        return -1;
                    } else {
                        if (o1.getStatut() == null && o2.getStatut() != null) {
                            return 1;
                        } else if (o1.getStatut() != null && o2.getStatut() == null) {
                            return -1;
                        } else {
                            if ((o1.getStatut() == null && o2.getStatut() == null)
                                || (o1.getStatut().getIdentifiant() == null && o2.getStatut().getIdentifiant() == null)
                                || (o1.getStatut().getIdentifiant().equals(o2.getStatut().getIdentifiant()))) {
                                if (o1.getIdNatureProduit() == null && o2.getIdNatureProduit() != null) {
                                    return 1;
                                } else if (o1.getIdNatureProduit() != null && o2.getIdNatureProduit() == null) {
                                    return -1;
                                } else if ((o1.getIdNatureProduit() == null && o2.getIdNatureProduit() == null)
                                    || (o1.getIdNatureProduit().equals(o2.getIdNatureProduit()))) {
                                    if (o1.getDateSignature() == null) {
                                        return 1;
                                    } else if (o2.getDateSignature() == null) {
                                        return -1;
                                    } else {
                                        return o2.getDateSignature().compareTo(o1.getDateSignature());
                                    }
                                } else {
                                    return o1.getIdNatureProduit().compareTo(o2.getIdNatureProduit());
                                }
                            } else if (o1.getStatut().getIdentifiant() == null) {
                                return 1;
                            } else if (o2.getStatut().getIdentifiant() == null) {
                                return -1;
                            } else {
                                final Integer niveauImportanceStatut1 =
                                    adherentMappingService.getNiveauImportanceStatutGarantie(o1.getStatut().getIdentifiant());
                                final Integer niveauImportanceStatut2 =
                                    adherentMappingService.getNiveauImportanceStatutGarantie(o2.getStatut().getIdentifiant());
                                return niveauImportanceStatut2.compareTo(niveauImportanceStatut1);
                            }
                        }
                    }
                }
            };
            Collections.sort(listeGarantiesDto, comparator);
        }
        contratDto.setListeGaranties(listeGarantiesDto);
        return contratDto;
    }

    @Override
    public InfosContratsPersonneMoraleDto getInfosContratPersonneMorale(Long uidPersonneMorale) {
        final InfosContratsPersonneMoraleDto infosContrat = new InfosContratsPersonneMoraleDto();

        // Récupération du dernier contrat santé ou, à défaut de contrat santé, dernier contrat prévoyance
        Contrat contratPrinc =
            contratDao.getDernierContratPersonneMoraleByCriteres(uidPersonneMorale, adherentMappingService.getIdNatureContratSante(), adherentMappingService
                    .getIdStatutContratEnCours());
        if (contratPrinc == null) {
            contratPrinc =
                contratDao.getDernierContratPersonneMoraleByCriteres(uidPersonneMorale, adherentMappingService.getIdNatureContratPrevoyance(),
                    adherentMappingService.getIdStatutContratEnCours());
        }

        if (contratPrinc != null) {
            // Récupération des informations de synthèse du contrat
            final SyntheseContrat syntheseContrat = contratDao.getSyntheseContrat(uidPersonneMorale);
            if (syntheseContrat == null) {
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_SYNTHESE_CONTRAT_INEXISTANTE));
            }

            final SyntheseContratPersonneMoraleDto syntheseContrats = new SyntheseContratPersonneMoraleDto();
            syntheseContrats.setDatePremiereMutualisation(syntheseContrat.getDatePremiereMutualisation());
            syntheseContrats.setNbAnneesFidelite(syntheseContrat.getNbAnneesFidelite());
            syntheseContrats.setNbMoisFidelite(syntheseContrat.getNbMoisFidelite());
            final IdentifiantLibelleDto statutDto = mapperDozerBean.map(contratPrinc.getStatut(), IdentifiantLibelleDto.class);
            syntheseContrats.setStatut(statutDto);
            if (contratPrinc.getTypePayeur() != null) {
                syntheseContrats.setGestionDuContrat(contratPrinc.getTypePayeur().getLibelle());
            }
            syntheseContrats.setGestionnaire(contratPrinc.getGestionnaire());

            // Récupération du dernier contrat radié
            final Contrat dernierContratRadie = contratDao.getDernierContratRadiePersonneMorale(uidPersonneMorale);
            if (dernierContratRadie != null) {
                syntheseContrats.setDateDerniereRadiation(dernierContratRadie.getDateResiliation());
                if (dernierContratRadie.getMotifResiliation() != null) {
                    syntheseContrats.setMotifDerniereRadiation(dernierContratRadie.getMotifResiliation().getLibelle());
                }
            }

            // Récupération de la population
            final List<PopulationDto> listePopulations = contratDao.getListePopulations(uidPersonneMorale);
            // Tri de la population
            if (listePopulations != null && listePopulations.size() > 1) {
                // Récupération de l'ordre
                for (PopulationDto populationDto : listePopulations) {
                    final Integer ordre = adherentMappingService.getOrdrePopulation(populationDto.getLibelle());
                    populationDto.setOrdre(ordre);
                }

                // Tri
                final Comparator<PopulationDto> comparator = new Comparator<PopulationDto>() {
                    @Override
                    public int compare(PopulationDto o1, PopulationDto o2) {
                        if (o1 == null || o1.getOrdre() == null) {
                            return 1;
                        } else if (o2 == null || o2.getOrdre() == null) {
                            return -1;
                        } else {
                            return o1.getOrdre().compareTo(o2.getOrdre());
                        }
                    }
                };
                Collections.sort(listePopulations, comparator);
            }
            syntheseContrats.setPopulation(listePopulations);
            infosContrat.setSyntheseContrat(syntheseContrats);

        }
        // Récupération des contrats de la personne morale
        final List<Contrat> listeContrats = contratDao.getListeContratsPersonneMorale(uidPersonneMorale);
        if (listeContrats != null && listeContrats.size() > 0) {
            final List<ContratSimpleDto> listeContratsSimples = mapperDozerBean.mapList(listeContrats, ContratSimpleDto.class);
            infosContrat.setListeContrats(listeContratsSimples);
        }
        return infosContrat;
    }

    @Override
    public Boolean hasPersonneContrats(Long idPersonne) {
        return contratDao.hasPersonneContrats(idPersonne);
    }

    @Override
    public List<ContratSimpleDto> getContratsSimpleByCriteres(CritereRechercheContratDto criteres) {
        final List<Contrat> listeContrats = contratDao.getContratsByCriteres(criteres);
        return mapperDozerBean.mapList(listeContrats, ContratSimpleDto.class);
    }

    @Override
    public List<CoordonneesBancairesDto> getListeCoordonneesBancaires(Long idPersonne) {
        final Long idMoyenPaiementCheque = adherentMappingService.getIdMoyenPaiementCheque();
        final Long idMoyenPaiementEspece = adherentMappingService.getIdMoyenPaiementEspece();

        final CritereRechercheContratDto criteres = new CritereRechercheContratDto();
        criteres.setIdAssure(idPersonne);
        criteres.setHasContratEnCours(true);
        final List<Contrat> listeContrat = contratDao.getContratsByCriteres(criteres);

        final List<CoordonneesBancairesDto> listeCoordonneesBancaires = new ArrayList<CoordonneesBancairesDto>();
        for (Contrat contrat : listeContrat) {
            final CoordonneesBancairesDto coordonneesBancaires = new CoordonneesBancairesDto();
            coordonneesBancaires.setNumeroContrat(contrat.getNumeroContrat());
            if (contrat.getMoyenPaiementCotisation() != null && !idMoyenPaiementCheque.equals(contrat.getMoyenPaiementCotisation().getId())
                && !idMoyenPaiementEspece.equals(contrat.getMoyenPaiementCotisation().getId())) {
                coordonneesBancaires.setInfosBanque((InfosBanqueDto) mapperDozerBean.map(contrat.getBanqueCotisation(), InfosBanqueDto.class));
            }
            final InfosPaiementDto infosPaiement = new InfosPaiementDto();
            infosPaiement.setFrequencePaiement((IdentifiantLibelleDto) mapperDozerBean.map(contrat.getFrequencePaiementCotisation(),
                IdentifiantLibelleDto.class));
            infosPaiement.setMoyenPaiement((IdentifiantLibelleDto) mapperDozerBean.map(contrat.getMoyenPaiementCotisation(), IdentifiantLibelleDto.class));
            infosPaiement.setJourPaiement(contrat.getJourPaiementCotisation());
            coordonneesBancaires.setInfosPaiement(infosPaiement);
            listeCoordonneesBancaires.add(coordonneesBancaires);
        }
        return listeCoordonneesBancaires;
    }

    @Override
    public Boolean hasDroitAffichageCotisation(Long idPersonne) {
        final PersonneSimpleDto personne = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(idPersonne);
        if (personne != null && personne.getSegment() != null && personne.getSegment().getIdentifiant() != null) {
            if (personne.getSegment().getIdentifiant().equals(squareMappingService.getIdSegmentIndividuel())) {
                return true;
            } else {
                final ListeContratsDto listeContrats = getListeContrats(idPersonne);
                for (ContratSimpleDto contratSimpleDto : listeContrats.getListeContrats()) {
                    final Contrat contrat = contratDao.getContratById(contratSimpleDto.getIdentifiant());
                    if (contrat.getTypePayeur().getId().equals(adherentMappingService.getIdTypePayeurAssure())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> getListeContratsCollectifsByCriteres(ContratCollectifCriteresDto criteres) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_RECUP_LISTE_CONTRAT_ENTREPRISE));
        return contratDao.getListeContratsCollectifsByCriteres(criteres);
    }

    @Override
    public List<String> getListePopulationsByCriteres(PopulationCriteresDto criteres) {
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_RECUP_LISTE_POPULATION_CONTRAT_ENTREPRISE));
        return contratDao.getListePopulationsByCriteres(criteres);
    }

    @Override
    public List<ProduitCollectifAdherentDto> getListeProduitsCollectifsAdherent(ProduitCollectifAdherentCriteresDto criteres) {
        return contratDao.getListeProduitsCollectifsAdherent(criteres, adherentMappingService.getIdRoleGarantieAssure(), adherentMappingService
                .getIdStatutGarantieEnCours(), adherentMappingService.getIdStatutGarantieFutur());
    }

    @Override
    public List<TypePayeurDto> getListeTypesPayeurs(TypePayeurCriteresDto criteres) {
        return contratDao.getListeTypesPayeurs(criteres, adherentMappingService.getIdRoleGarantieAssure());
    }

    @Override
    public int countContrats(Long idPersonne, boolean personneMorale) {
        final CritereRechercheContratDto criteresContrat = new CritereRechercheContratDto();
        if (personneMorale) {
            criteresContrat.setIdAssureNull(true);
            criteresContrat.setIdSouscripteur(idPersonne);
        } else {
            criteresContrat.setIdAssure(idPersonne);
        }
        return contratDao.countContratsByCriteres(criteresContrat);
    }

    /**
     * Définit la valeur de contratDao.
     * @param contratDao la nouvelle valeur de contratDao
     */
    public void setContratDao(ContratDao contratDao) {
        this.contratDao = contratDao;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de delaiMoisInactiviteGarantieSante.
     * @param delaiMoisInactiviteGarantieSante la nouvelle valeur de delaiMoisInactiviteGarantieSante
     */
    public void setDelaiMoisInactiviteGarantieSante(int delaiMoisInactiviteGarantieSante) {
        this.delaiMoisInactiviteGarantieSante = delaiMoisInactiviteGarantieSante;
    }

    /**
     * Définit la valeur de garantieDao.
     * @param garantieDao la nouvelle valeur de garantieDao
     */
    public void setGarantieDao(GarantieDao garantieDao) {
        this.garantieDao = garantieDao;
    }

    /**
     * Définit la valeur de personnePhysiqueService.
     * @param personnePhysiqueService la nouvelle valeur de personnePhysiqueService
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Définit la valeur de produitService.
     * @param produitService la nouvelle valeur de produitService
     */
    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /**
     * Définit la valeur de logger.
     * @param logger la nouvelle valeur de logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Définit la valeur de reserveBancoDao.
     * @param reserveBancoDao la nouvelle valeur de reserveBancoDao
     */
    public void setReserveBancoDao(ReserveBancoDao reserveBancoDao) {
        this.reserveBancoDao = reserveBancoDao;
    }

    /**
     * Définit la valeur de personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Définit la valeur de tarificateurMappingService.
     * @param tarificateurMappingService la nouvelle valeur de tarificateurMappingService
     */
    public void setTarificateurMappingService(TarificateurMappingService tarificateurMappingService) {
        this.tarificateurMappingService = tarificateurMappingService;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }
}
