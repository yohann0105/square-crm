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
package com.square.fusion.api.service.test.mock;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.tarificateur.noyau.dto.InfosAdhesionDto;
import com.square.tarificateur.noyau.dto.InfosBaDto;
import com.square.tarificateur.noyau.dto.InfosOpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.ClotureDevisQueryDto;
import com.square.tarificateur.noyau.dto.devis.CritereModeleDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheLigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheOpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.DevisDto;
import com.square.tarificateur.noyau.dto.devis.DevisModificationDto;
import com.square.tarificateur.noyau.dto.devis.EnregistrementFinaliteDevisDto;
import com.square.tarificateur.noyau.dto.devis.LigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.MotifDevisDto;
import com.square.tarificateur.noyau.dto.devis.OpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.OpportuniteModificationDto;
import com.square.tarificateur.noyau.dto.devis.TransfertDevisDto;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.dto.produit.adherent.ListeProduitsAdherentDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.SelecteurProduitDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;

/**
 * Mock du service TarificateurService.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class TarificateurServiceImpl implements TarificateurService {

    @Override
    public LigneDevisDto addLigneDevis(Long idDevis, LigneDevisDto ligneDevis) {
        return null;
    }

    @Override
    public LigneDevisDto addLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit) {
        return null;
    }

    @Override
    public LigneDevisDto addLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit, InfosOpportuniteDto infosOpportunite) {
        return null;
    }

    @Override
    public void cloturerDevisQuery(ClotureDevisQueryDto query) {
    }

    @Override
    public boolean comparerFamille(PersonneTarificateurDto personneAComparer, Long idPersonneReference) {
        return false;
    }

    @Override
    public DevisDto createDevis(InfosOpportuniteDto infosOpportunite) {
        return null;
    }

    @Override
    public void deleteLigneDevis(Long idLigneDevis) {
    }

    @Override
    public void deleteLigneDevisfromDevis(Long idDevis) {
    }

    @Override
    public void enregistrerFinaliteDevis(EnregistrementFinaliteDevisDto enregistrementFinaliteDevisDto) {
    }

    @Override
    public BeneficiaireDto getBeneficiaireByCible(Long idBeneficiaire) {
        return null;
    }

    @Override
    public List<String> getIdsBaAdhesionNonTelecharges() {
        return null;
    }

    @Override
    public InfosAdhesionDto getInfosAdhesion(Long idDevis) {
        return null;
    }

    @Override
    public LigneDevisDto getLigneDevisParIdentifiant(Long idLigneDevis) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> getListeCaisses(Long idPersonne, Long idRegime) {
        return null;
    }

    @Override
    public List<DevisDto> getListeDevisByCriteres(CriteresRechercheDevisDto criteres) {
        return null;
    }

    @Override
    public List<Integer> getListeIdsProduitsParIdDevis(Long idDevis) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> getListeJoursPaiement() {
        return null;
    }

    @Override
    public List<LigneDevisDto> getListeLignesDevisParCriteres(CriteresRechercheLigneDevisDto criteres) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> getListeModelesDevisByCriteres(CritereModeleDevisDto criteres) {
        return null;
    }

    @Override
    public List<MotifDevisDto> getListeMotifsDevis() {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> getListeMoyensPaiement() {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> getListePeriodicitesPaiement() {
        return null;
    }

    @Override
    public List<ListeProduitsAdherentDto> getListeProduitsAdherent(Long idPersonne) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> getListeRegimes() {
        return null;
    }

    @Override
    public MotifDevisDto getMotifDevis(Long id) {
        return null;
    }

    @Override
    public List<MotifDevisDto> getMotifsDevisByCriteres(String libelle) {
        return null;
    }

    @Override
    public OpportuniteDto getOpportuniteById(Long idOpportunite) {
        return null;
    }

    @Override
    public OpportuniteDto getOpportuniteByNumTransaction(String numeroTransaction) {
        return null;
    }

    @Override
    public OpportuniteDto getOrCreateOpportunite(InfosOpportuniteDto infosOpportunite) {
        return null;
    }

    @Override
    public SelecteurProduitDto getSelecteurParProduit(Integer idProduit, Long idDevis, PersonneTarificateurDto nouvellePersonnePrincipale) {
        return null;
    }

    @Override
    public SelecteurProduitDto getSelecteurProduitParLigneDevis(Long idLigneDevis, Long idDevis) {
        return null;
    }

    @Override
    public SelecteurProduitDto getSelecteurProduitParProduitsAdherent(Long idDevis, String produitAia, String garantieAia,
        PersonneTarificateurDto nouvellePersonnePrincipale) {
        return null;
    }

    @Override
    public String getValeurCritereCodeCompoFamille(Long idLigneDevis) {
        return null;
    }

    @Override
    public String getValeurCritereCodeCompoFamille(List<BeneficiaireDto> beneficiaires) {
        return null;
    }

    @Override
    public void majFamilleDevis(Long idDevis, PersonneTarificateurDto personne) {
    }

    @Override
    public Long mettreAJourInfosFichier(InfosBaDto infos) {
        return null;
    }

    @Override
    public void modifierDevis(DevisModificationDto devisModificationDto) {
    }

    @Override
    public void modifierOpportunite(OpportuniteModificationDto opportuniteModificationDto) {
    }

    @Override
    public void modifierSelectionPourImpression(Long idDevis, boolean isSelectionne) {
    }

    @Override
    public void modifierSourceLigneDevis(Long idLigne, Long idSource) {
    }

    @Override
    public Boolean personneIsBeneficiaireSurOppTransforme(Long eidPersonne) {
        return false;
    }

    @Override
    public LigneDevisDto recalculerLigneDevis(Long idDevis, LigneDevisDto ligneDevis) {
        return null;
    }

    @Override
    public List<Long> rechercherIdsOpportunitesByCritere(CriteresRechercheOpportuniteDto criteres) {
        return null;
    }

    @Override
    public IdentifiantLibelleDto rechercherJourPaiementParId(Long id) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherJoursPaiementParCriteres(IdentifiantLibelleDto criteres) {
        return null;
    }

    @Override
    public IdentifiantLibelleDto rechercherMoyenPaiementParId(Long id) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherMoyensPaiementParCriteres(IdentifiantLibelleDto criteres) {
        return null;
    }

    @Override
    public IdentifiantLibelleDto rechercherPeriodicitePaiementParId(Long id) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherPeriodicitesPaiementParCriteres(IdentifiantLibelleDto criteres) {
        return null;
    }

    @Override
    public DevisDto transfererDevis(TransfertDevisDto transfertDevisDto) {
        return null;
    }

    @Override
    public void updateInfosAdhesion(InfosAdhesionDto infosAdhesion) {
    }

    @Override
    public LigneDevisDto updateLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit) {
        return null;
    }

	@Override
	public List<IdentifiantLibelleDto> getBeneficiairesFromProspectByDevis(
			DevisDto devis, List<Long> listeIdsLignesSelectionnees) {
		return null;
	}

}
