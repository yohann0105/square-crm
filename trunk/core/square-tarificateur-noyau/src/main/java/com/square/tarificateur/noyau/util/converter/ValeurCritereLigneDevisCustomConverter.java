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
package com.square.tarificateur.noyau.util.converter;

import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.converters.CustomConverter;

import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.spring.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;

import com.square.price.core.dto.CritereDto;
import com.square.price.core.service.interfaces.TarifService;
import com.square.price.core.service.interfaces.TarificateurMappingService;
import com.square.tarificateur.noyau.dto.devis.ValeurCritereLigneDevisDto;
import com.square.tarificateur.noyau.model.opportunite.ValeurCritereLigneDevis;
import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Convertion specifique des valeur de criteres de ligne de devis vers un objet ValeurCritereLigneDevisDto.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ValeurCritereLigneDevisCustomConverter implements CustomConverter {

    /**
     * Classe utilitaire contenant les chaines de caractéres.
     */
    private MessageSourceUtil messageSourceUtil;

    private final String mappingCustomImpossible = messageSourceUtil.get(MessageKeyUtil.MESSAGE_MAPPING_CUSTOM_IMPOSSIBLE);

    private TarifService tarifService;

    private TarificateurMappingService tarificateurMappingService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
        final ApplicationContext context = ApplicationContextHolder.getContext();
        tarifService = (TarifService) context.getBean("tarifService");
        tarificateurMappingService = (TarificateurMappingService) context.getBean("tarificateurMappingService");
        try {
            ValeurCritereLigneDevis valeurCritereLigneDevis = null;
            ValeurCritereLigneDevisDto valeurCritereLigneDevisDto = null;
            int cas = 0;

            if (destination == null && destClass == ValeurCritereLigneDevis.class) {
                valeurCritereLigneDevis = (ValeurCritereLigneDevis) destClass.newInstance();
                cas = 1;
            }
            else if (destination == null && destClass == ValeurCritereLigneDevisDto.class) {
                valeurCritereLigneDevisDto = (ValeurCritereLigneDevisDto) destClass.newInstance();
                cas = 2;
            }
            else if (destination instanceof ValeurCritereLigneDevis) {
                valeurCritereLigneDevis = (ValeurCritereLigneDevis) destination;
                cas = 1;
            }
            else if (destination instanceof ValeurCritereLigneDevisDto) {
                valeurCritereLigneDevisDto = (ValeurCritereLigneDevisDto) destination;
                cas = 2;
            }
            else {
                throw new MappingException(mappingCustomImpossible);
            }

            if (source == null) {
                return null;
            }

            if (source instanceof ValeurCritereLigneDevis) {
                valeurCritereLigneDevis = (ValeurCritereLigneDevis) source;
            }
            else if (source instanceof ValeurCritereLigneDevisDto) {
                valeurCritereLigneDevisDto = (ValeurCritereLigneDevisDto) source;
            }
            else {
                throw new MappingException(mappingCustomImpossible);
            }

            switch (cas) {
            case 1:
                valeurCritereLigneDevis.setEidCritere(valeurCritereLigneDevisDto.getIdentifiantCritere());
                valeurCritereLigneDevis.setAffichageValeur(valeurCritereLigneDevisDto.getAffichageValeur());
                valeurCritereLigneDevis.setValeur(valeurCritereLigneDevisDto.getValeur());
                return valeurCritereLigneDevis;

            case 2:
                // on copie les proprietes
                valeurCritereLigneDevisDto.setIdentifiantCritere(valeurCritereLigneDevis.getEidCritere());
                valeurCritereLigneDevisDto.setAffichageValeur(valeurCritereLigneDevis.getAffichageValeur());
                valeurCritereLigneDevisDto.setValeur(valeurCritereLigneDevis.getValeur());
                // recuperer le libelle du critere depuis le noyau tarificateur
                final CritereDto critere =
                    tarifService.getCritereParIdentifiant(valeurCritereLigneDevis.getEidCritere(), tarificateurMappingService
                        .getIdentifiantApplicationGestcom());
                valeurCritereLigneDevisDto.setLibelleCritere(critere == null ? messageSourceUtil.get(MessageKeyUtil.MESSAGE_LIBELLE_NON_TROUVER)
                    + valeurCritereLigneDevisDto.getIdentifiantCritere() : critere.getLibelle());
                return valeurCritereLigneDevisDto;

            default:
                throw new MappingException(mappingCustomImpossible);
            }
        }
        catch (InstantiationException e) {
            throw new MappingException(e);
        }
        catch (IllegalAccessException e) {
            throw new MappingException(e);
        }
    }

	/**Set messageSourceUtil.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}
}
