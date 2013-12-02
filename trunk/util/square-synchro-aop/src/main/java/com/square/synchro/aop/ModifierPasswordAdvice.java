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
package com.square.synchro.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * AOP pour la création d'un espace client à la création d'une PP.
 * @author jgoncalves - SCUB
 */
@Aspect
public class ModifierPasswordAdvice {

    /** Logger. */
    private Logger logger = Logger.getLogger(ModifierPasswordAdvice.class);

//    private ZimbraService zimbraService = new ZimbraServiceImpl();
//
//    private UtilisateurService utilisateurService;

    /**
     * {@inheritDoc}
     */
    public Object modifierMotDePasse(ProceedingJoinPoint pjp) throws Throwable {
        logger.debug("AOP modifyPassword");
        // FIXME OpenSource
//        final ModifyPasswordDto modifyPasswordDto = (ModifyPasswordDto) pjp.getArgs()[0];
//
//        // on recupere l'email
//        final UtilisateurDto utilisateur = utilisateurService.getUtilisateurById(modifyPasswordDto.getIdUser());
//
        final Object retVal = pjp.proceed();
//        try {
//            modifierMotDePasseZimbra(utilisateur.getEmail(), modifyPasswordDto.getActualPassword(), modifyPasswordDto.getNewPassword());
//        } catch (Exception e) {
//            logger.fatal("Erreur Synchro MDP Zimbra,  poursuite du traitement : " + e.getMessage(), e);
//        }
        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    public Object reinitialiserMotDePasseUtilisateur(ProceedingJoinPoint pjp) throws Throwable {
        logger.debug("AOP reinitialiserMotDePasseUtilisateur");
     // FIXME OpenSource
//        final Long idUtilisateur = (Long) pjp.getArgs()[0];
//
//        // on recupere l'email et le mot de passe
//        final UtilisateurDto utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
//
        final Object retVal = pjp.proceed();
//        try {
//            final UtilisateurDto utilisateurRetour = (UtilisateurDto) retVal;
//            modifierMotDePasseZimbra(utilisateur.getEmail(), utilisateur.getPassword(), utilisateurRetour.getPassword());
//        } catch (Exception e) {
//            logger.fatal("Erreur Synchro MDP Zimbra,  poursuite du traitement : " + e.getMessage(), e);
//        }
        return retVal;
    }

    @SuppressWarnings("unused")
	private void modifierMotDePasseZimbra(String email, String ancienMotDePasse, String nouveauMotDePasse) {
//        logger.debug("Appel de l'API ZIMBRA authentifier : " + email + " / " + ancienMotDePasse);
//        final ZimbraAuthentificationDto authentificationDto = new ZimbraAuthentificationDto(email, ancienMotDePasse);
//        final String authToken = zimbraService.authentifier(authentificationDto);
//
//        logger.debug("Appel de l'API ZIMBRA modifierMotDePasse : " + email + " / " + ancienMotDePasse + " / " + nouveauMotDePasse);
//        final ZimbraModificationMotDePasseDto modificationMotDePasse = new ZimbraModificationMotDePasseDto(email, ancienMotDePasse, nouveauMotDePasse);
//        zimbraService.modifierMotDePasse(authToken, modificationMotDePasse);
    }
//
//    /**
//     * Set the value of utilisateurService.
//     * @param utilisateurService the utilisateurService to set
//     */
//    public void setUtilisateurService(UtilisateurService utilisateurService) {
//        this.utilisateurService = utilisateurService;
//    }

}
