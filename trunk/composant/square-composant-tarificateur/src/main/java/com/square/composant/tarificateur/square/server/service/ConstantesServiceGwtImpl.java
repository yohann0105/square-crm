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

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.IntegerLibelleModel;
import com.square.composant.tarificateur.square.client.service.ConstantesServiceGwt;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.price.core.service.interfaces.ProduitService;
import com.square.price.core.service.interfaces.TarificateurMappingService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurService;
import com.square.tarificateur.noyau.service.interfaces.TarificateurSquareMappingService;

/**
 * Implémentation serveur des services GWT pour les constantes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class ConstantesServiceGwtImpl implements ConstantesServiceGwt {

    private TarificateurMappingService tarificateurMappingService;

    private TarificateurSquareMappingService tarificateurSquareMappingService;

    private ProduitService produitService;

    private MapperDozerBean mapperDozerBean;

    private TarificateurService tarificateurService;

    private SquareMappingService squareMappingService;

    @SuppressWarnings("deprecation")
	@Override
    public ConstantesModel getConstantes() {
        final ConstantesModel constantes = new ConstantesModel();
        constantes.setIdentifiantVetusteGammeOuvertVente(tarificateurMappingService.getIdentifiantVetusteGammeOuvertVente());
        constantes.setIdentifiantCategorieSante(tarificateurMappingService.getIdentifiantCategorieSante());
        constantes.setConstanteTypeHTMLSelect(tarificateurMappingService.getConstanteTypeHTMLSelect());
        constantes.setIdCritereAgeMin(tarificateurMappingService.getIdentifiantCritereAgeMin());
        constantes.setIdCritereAgeMax(tarificateurMappingService.getIdentifiantCritereAgeMax());
        constantes.setIdCritereMontantMin(tarificateurMappingService.getIdentifiantCritereMontant());
        constantes.setIdCritereMontantMax(tarificateurMappingService.getIdentifiantCritereMontantDeux());
        constantes.setIdCritereGeneration(tarificateurMappingService.getIdentifiantCritereGeneration());
        constantes.setIdCritereMois(tarificateurMappingService.getIdentifiantCritereMois());
        constantes.setCodeTarificationAdherent(tarificateurMappingService.getConstanteCodeTarificationAdherent());
        constantes.setConstanteTypeCalendar(tarificateurMappingService.getConstanteTypeHTMLCalendar());
        constantes.setIdentifiantFamilleBonus1(tarificateurMappingService.getIdentifiantFamilleBonus1());
        constantes.setIdentifiantFamilleBonus2(tarificateurMappingService.getIdentifiantFamilleBonus2());
        constantes.setIdFinaliteNonRenseignee(tarificateurSquareMappingService.getIdFinaliteNonRenseignee());
        constantes.setIdFinaliteEnCours(tarificateurSquareMappingService.getIdFinaliteEnCours());
        constantes.setIdFinaliteAcceptee(tarificateurSquareMappingService.getIdFinaliteAcceptee());
        constantes.setIdFinaliteRefusee(tarificateurSquareMappingService.getIdFinaliteRefusee());
        constantes.setIdFinaliteCorbeille(tarificateurSquareMappingService.getIdFinaliteCorbeille());
        constantes.setIdFinaliteTransformee(tarificateurSquareMappingService.getIdFinaliteTransformee());
        constantes.setIdModePaiementMensuel(tarificateurMappingService.getIdentifiantModePaiementMensuel());
        constantes.setIdModePaiementUnique(tarificateurMappingService.getIdentifiantModePaiementUnique());
        constantes.setConstanteTypeSantePrevoyance(tarificateurSquareMappingService.getTypeSantePrevoyance());
        constantes.setNbAnneesBonus1(tarificateurSquareMappingService.getNbAnneesBonus1());
        constantes.setNbAnneesBonus2(tarificateurSquareMappingService.getNbAnneesBonus2());
        constantes.setIdMoyenPaiementPrelevement(tarificateurSquareMappingService.getIdMoyenPaiementPrelevement());
        constantes.setIdMoyenPaiementCarteBancaire(tarificateurSquareMappingService.getIdMoyenPaiementCarteBancaire());
        constantes.setTypeNeutre(tarificateurSquareMappingService.getTypeNeutre());
        constantes.setIdLienFamilialAssurePrincipal(tarificateurSquareMappingService.getIdLienFamilialAssurePrincipal());
        constantes.setIdLienFamilialConjoint(tarificateurSquareMappingService.getIdLienFamilialConjoint());
        constantes.setIdLienFamilialEnfant(tarificateurSquareMappingService.getIdLienFamilialEnfant());
        constantes.setIdModeleDevisBulletinAdhesion(tarificateurSquareMappingService.getIdModeleDevisBulletinAdhesion());
        constantes.setIdModeleDevisFicheTransfert(tarificateurSquareMappingService.getIdModeleDevisFicheTransfert());
        constantes.setIdModeleLettreAnnulation(tarificateurSquareMappingService.getIdModeleLettreAnnulation());
        constantes.setIdModeleLettreRadiation(tarificateurSquareMappingService.getIdModeleLettreRadiation());
        constantes.setIdModeleLettreRadiationLoiChatel(tarificateurSquareMappingService.getIdModeleLettreRadiationLoiChatel());
        constantes.setIdGroupementFamille(squareMappingService.getIdGroupementFamille());
        constantes.setIdTypeRelationConjoint(squareMappingService.getIdTypeRelationConjoint());
        constantes.setIdTypeRelationEnfant(squareMappingService.getIdTypeRelationEnfant());

        constantes.setCategorieGammeDefaut((IntegerLibelleModel) mapperDozerBean.map(produitService
                .getCategorieGamme(constantes.getIdentifiantCategorieSante()), IntegerLibelleModel.class));
        constantes.setTypeGammeDefaut((IntegerLibelleModel) mapperDozerBean.map(produitService
            .getVetusteGamme(constantes.getIdentifiantVetusteGammeOuvertVente()), IntegerLibelleModel.class));
        constantes.setReseauGammeDefaut((IntegerLibelleModel) mapperDozerBean.map(produitService
            .getReseauGamme(tarificateurMappingService.getIdentifiantReseauFrance()), IntegerLibelleModel.class));
        constantes.setMotifDevisDefaut((IdentifiantLibelleGwt) mapperDozerBean.map(tarificateurService
            .getMotifDevis(tarificateurSquareMappingService.getIdMotifDevisStandard()), IdentifiantLibelleGwt.class));

        return constantes;
    }

    /**
     * Set the tarificateurMappingService value.
     * @param tarificateurMappingService the tarificateurMappingService to set
     */
    public void setTarificateurMappingService(TarificateurMappingService tarificateurMappingService) {
        this.tarificateurMappingService = tarificateurMappingService;
    }

    /**
     * Set the tarificateurSquareMappingService value.
     * @param tarificateurSquareMappingService the tarificateurSquareMappingService to set
     */
    public void setTarificateurSquareMappingService(TarificateurSquareMappingService tarificateurSquareMappingService) {
        this.tarificateurSquareMappingService = tarificateurSquareMappingService;
    }

    /**
     * Set the produitService value.
     * @param produitService the produitService to set
     */
    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the tarificateurService value.
     * @param tarificateurService the tarificateurService to set
     */
    public void setTarificateurService(TarificateurService tarificateurService) {
        this.tarificateurService = tarificateurService;
    }

    /**
     * Set the squareMappingService value.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

}
