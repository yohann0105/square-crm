package com.square.mail.core.service.interfaces.gestionmails;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.core.model.dto.SearchUtilDto;

import com.square.mail.core.dto.gestionmails.CritereRechercheNombreEmailDto;
import com.square.mail.core.dto.gestionmails.EmailDto;
import com.square.mail.core.dto.gestionmails.GroupeEmailDto;
import com.square.mail.core.dto.gestionmails.RechercheDestinataireQueryDto;
import com.square.mail.core.dto.gestionmails.RechercheDestinataireResultatsDto;
import com.square.mail.core.dto.gestionmails.RechercheEmailRequeteDto;
import com.square.mail.core.dto.gestionmails.RechercheEmailResultatDto;


/**
 * Interface des services liés aux mails.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
@SuppressWarnings("deprecation")
public interface EmailService {

    /**
     * Récupère les mails correspondants aux critères de recherche.
     * @param criteres les critères de recherche
     * @return le DTO contenant la liste des mails recherchés
     */
    SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto> rechercherEmailsParCriteres(
        SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto> criteres);

    /**
     * Récupère le groupe d'emails dont fait partie l'email demandé.
     * @param idEmail l'identifiant de l'email
     * @return le groupe d'emails
     */
    GroupeEmailDto getGroupeEmailParEmail(Long idEmail);

    /**
     * Récupère la liste des services accessibles pour l'utilisateur connecté.
     * @return la liste des services
     */
    List<IdentifiantLibelleDto> getFiltresServices();

    /**
     * Récupère la liste des utilisateurs accessible pour l'utilisateur connecté et le service demandé.
     * @param idService l'identifiant du service
     * @return la liste des utilisateurs
     */
    List<IdentifiantLibelleDto> getFiltresUtilisateurs(Long idService);
//
//    /**
//     * Relance l'Indexation sur un lot d'emails.
//     * @param debutTraitement détermine ou doit commencer le traitement
//     * @param tailleLot détermine le nombre d'emails à indexer
//     * @return le nombre de mails traités
//     */
//    int reIndexerEmailsParLot(int debutTraitement, int tailleLot);
//
//    /**
//     * Enregistre un fichier en pièce jointe à un mail (enregistrement dans un dossier temporaire).
//     * @param fichier le fichier en pièce jointe à enregistrer
//     * @return les informations relatives au fichier enregistré sur le serveur
//     */
//    PieceJointeEmailDto enregistrerPieceJointe(FichierPieceJointeDto fichier);
//
//    /**
//     * Récupère le fichier enregistré auparavant.
//     * @param idPieceJointe identifiant du fichier pièce jointe
//     * @return le fichier correspondant à l'identifiant passé en paramètre
//     */
//    FichierPieceJointeDto getPieceJointeById(Long idPieceJointe);
//
//    /**
//     * Supprime des fichiers pièces jointes selon les critères passés en paramètres (suppression dans le dossier temporaire).
//     * @param dateLimite la date limite : on supprime les fichiers antérieurs à cette date
//     */
//    void supprimerFichiersPiecesJointes(Calendar dateLimite);
//
//    /**
//     * Enregistre un mail.
//     * @param email le mail à enregistrer
//     * @return le mail enregistré
//     */
//    EmailDto enregistrerEmail(EmailDto email);

    /**
     * Change l'état d'un email.
     * @param idEmail id de l'email
     * @param idEtat l'id de l'état
     */
    void changerEtatEmail(Long idEmail, Long idEtat);
//
//    /**
//     * Verrouille ou déverrouille un email.
//     * @param idEmail id de l'email
//     * @param aVerrouiller boolean pour verrouiller ou deverrouiller le email
//     */
//    void verrouillerEmail(Long idEmail, Boolean aVerrouiller);

    /**
     * Change l'état de tous les emails d'un groupe.
     * @param idGroupeEmail id de l'email
     * @param idEtat l'id de l'état
     */
    void changerEtatEmailsByGroupeEmail(Long idGroupeEmail, Long idEtat);

    /**
     * Verrouille ou déverrouille tous les emails d'un groupe.
     * @param idGroupeEmail id de l'email
     * @param aVerrouiller boolean pour verrouiller ou deverrouiller le mail
     */
    void verrouillerEmailsByGroupeEmail(Long idGroupeEmail, Boolean aVerrouiller);

    /**
     * Envoie un email.
     * @param email l'email
     * @return l'email
     */
    EmailDto envoyerEmail(EmailDto email);

    /**
     * Récupère le nombre total de mails en base.
     * @return le nombre total de mails en base
     */
    Integer getNombreTotalEmails();

    /**
     * Compte le nombre d'emails par critères.
     * @param criteresDto les critères de recherche.
     * @return le nombre d'emails.
     */
    int getNombreEmailsByCriteres(CritereRechercheNombreEmailDto criteresDto);

    /**
     * Transfère une liste de groupe de discussion à un utilisateur (transfert de responsabilité).
     * @param listeGroupes la liste des identifiants des groupes de discussion.
     * @param idUtilisateurDestination l'identifiant du destinataire.
     */
    void transfererGroupe(List<Long> listeGroupes, Long idUtilisateurDestination);
//
//    /**
//     * Envoie un accusé de réception à une personne.
//     * @param adresseEmailDestinataire l'adresse email de la personne.
//     * @param dateEnvoi la date d'envoi de la demande.
//     */
//    void envoyerAccuseReception(String adresseEmailDestinataire, String dateEnvoi);
//
//    /**
//     * Service permettant de notifier une réception de mails.
//     * @param nbEmailsRecus nombre de mails reçus
//     */
//    void notifierReceptionEmails(Integer nbEmailsRecus);
//
//    /**
//     * Récupère l'identifiant du service associé à une adresse mail.
//     * @param adresseMail l'adresse mail.
//     * @return l'identifiant du service trouvé.
//     */
//    Long getIdServiceByAdresseEmail(String adresseMail);

    /**
     * Recherche des adresses mails correspondant aux critères de recherche passés en paramètre.
     * @param criteres les critères de recherche.
     * @return le résultat des destinataires.
     */
    RechercheDestinataireResultatsDto rechercherEmailsByNomOrEmail(RechercheDestinataireQueryDto criteres);
//
//    /**
//     * Récupère les infos des rôles de l'utilisateur courant.
//     * @return les infos
//     */
//    InfosRolesUtilisateurDto getInfosRolesUtilisateurCourant();
//
//    /**
//     * Récupère un mail par son identifiant.
//     * @param idEmail l'identifiant du mail
//     * @return
//     */
//    EmailDto getEmailById(Long idEmail);
}
