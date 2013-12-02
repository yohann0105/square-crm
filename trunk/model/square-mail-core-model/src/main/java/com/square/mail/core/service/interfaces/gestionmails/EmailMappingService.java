package com.square.mail.core.service.interfaces.gestionmails;


/**
 * Interfaces des services de mapping de l'application.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public interface EmailMappingService {

    /**
     * Retourne la propriété "Numéro adhérent".
     * @return la propriété "Numéro adhérent".
     */
    String getProprieteNumeroAdherent();

    /**
     * Retourne la propriété "Nom".
     * @return la propriété "Nom".
     */
    String getProprieteNom();

    /**
     * Retourne la propriété "Prénom".
     * @return la propriété "Prénom".
     */
    String getProprietePrenom();

    /**
     * Retourne la propriété "Date envoi".
     * @return la propriété "Date envoi".
     */
    String getProprieteDateEnvoi();

    /**
     * Retourne la propriété "Sujet".
     * @return la propriété "Sujet".
     */
    String getProprieteSujet();

    /**
     * Retourne la propriété "Etat".
     * @return la propriété "Etat".
     */
    String getProprieteEtat();

    /**
     * Retourne la propriété "Utilisateur".
     * @return la propriété "Utilisateur".
     */
    String getProprieteUtilisateur();

    /**
     * Retourne la propriété "Service".
     * @return la propriété "Service".
     */
    String getProprieteService();

    /**
     * Identifiant de l'état "Verrouille".
     * @return l'identifiant de l'état "Verrouille".
     */
    Long getIdEtatVerrouille();

    /**
     * Identifiant de l'état "Archive".
     * @return l'identifiant de l'état "Archive".
     */
    Long getIdEtatArchive();

    /**
     * Identifiant de l'état "Nouveau".
     * @return l'identifiant de l'état "Nouveau".
     */
    Long getIdEtatNouveau();
//
//    /**
//     * Renvoie la taille maximale autorisée pour un fichier en pièce jointe à enregistrer.
//     * @return la taille maximale en octets
//     */
//    Long getTailleMaxFichier();
//
//    /**
//     * Renvoie la liste des types de fichiers autorisés à être enregistrés.
//     * @return les types de fichiers autorisés
//     */
//    List < String > getListeTypeFichiersAutorises();
//
//    /**
//     * Retourne le chemin du répertoire temporaire où sont enregistrés les fichiers en pièce jointes aux emails.
//     * @return le chemin
//     */
//    String getRepertoireFichierPiecesJointesTemp();
//
//    /**
//     * Retourne le chemin du répertoire où sont enregistrés les fichiers en pièce jointes aux emails.
//     * @return le chemin
//     */
//    String getRepertoireFichierPiecesJointes();
//
//    /**
//     * Retourne le nom de la propriété "References" dans l'entete d'un email.
//     * @return le nom
//     */
//    String getNameEmailHeaderReferences();
//
//    /**
//     * Retourne le nom de la propriété NumAdherent dans l'entete d'un email.
//     * @return le nom
//     */
//    String getNameEmailHeaderNumAdherent();
//
//    /**
//     * Récupère l'adresse de l'expéditeur de mail Smatis.
//     * @return l'adresse
//     */
//    String getExpediteurEmailSmatis();

}
