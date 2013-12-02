package com.square.mail.core.service.interfaces.email;

/**
 * Interface du service de mapping de l'application d'envoi de mail.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net)
 */
public interface EnvoiEmailMappingService {
//    /**
//     * Get the idModeleDemandeConfirmationAjoutPanier value.
//     * @return the idModeleDemandeConfirmationAjoutPanier
//     */
//    Long getIdModeleDemandeConfirmationAjoutPanier();
//
//    /**
//     * Get the idModeleDemandeDevisSantePrevoyance value.
//     * @return the idModeleDemandeDevisSantePrevoyance
//     */
//    Long getIdModeleDemandeDevisSantePrevoyance();
//
//    /**
//     * Get the idModeleDemandeDevisAssuranceAuto value.
//     * @return the idModeleDemandeDevisAssuranceAuto
//     */
//    Long getIdModeleDemandeDevisAssuranceAuto();
//
//    /**
//     * Get the idModeleDemandeDevisAssuranceHabitation value.
//     * @return the idModeleDemandeDevisAssuranceHabitation
//     */
//    Long getIdModeleDemandeDevisAssuranceHabitation();
//
//    /**
//     * Get the idModeleDemandeDevisPrevoyanceTeleassistance value.
//     * @return the idModeleDemandeDevisPrevoyanceTeleassistance
//     */
//    Long getIdModeleDemandeDevisPrevoyanceTeleassistance();
//
//    /**
//     * Get the idModeleDemandeDevisPrevoyanceAutre value.
//     * @return the idModeleDemandeDevisPrevoyanceAutre
//     */
//    Long getIdModeleDemandeDevisPrevoyanceAutre();
//
//    /**
//     * Get the idModeleConfirmationSouscriptionEnLigne value.
//     * @return the idModeleConfirmationSouscriptionEnLigne
//     */
//    Long getIdModeleConfirmationSouscriptionEnLigne();
//
//    /**
//     * Get the idModeleDemandeDossierPro value.
//     * @return the idModeleDemandeDossierPro
//     */
//    Long getIdModeleDemandeDossierPro();
//
//    /**
//     * Get the idModeleDemandeContact value.
//     * @return the idModeleDemandeContactProspect
//     */
//    Long getIdModeleDemandeContactProspect();
//
//    /**
//     * Get the idModeleDemandeContact value.
//     * @return the idModeleDemandeContactAdherent
//     */
//    Long getIdModeleDemandeContactAdherent();
//
//    /**
//     * Get the idModeleDemandeParrainage value.
//     * @return the idModeleDemandeParrainage
//     */
//    Long getIdModeleDemandeParrainage();

    /**
     * Get the idModeleDemandePriseEnCharge value.
     * @return the idModeleDemandePriseEnCharge
     */
    Long getIdModeleDemandePriseEnCharge();
//
//    /**
//     * Get the idModeleChangementMotDePasse value.
//     * @return the idModeleChangementMotDePasse
//     */
//    Long getIdModeleChangementMotDePasse();
//
//    /**
//     * Get the idModeleEnvoiMagazineMutuellement value.
//     * @return the idModeleEnvoiMagazineMutuellement
//     */
//    Long getIdModeleEnvoiMagazineMutuellement();

    /**
     * Get the idModeleEnvoiRelevePrestations value.
     * @return the idModeleEnvoiRelevePrestations
     */
    Long getIdModeleEnvoiRelevePrestations();

    /**
     * Get the idModeleMotDePasseOublie value.
     * @return the idModeleMotDePasseOublie
     */
    Long getIdModeleMotDePasseOublie();
//
//    /**
//     * Get the idModeleEnvoiRelance1 value.
//     * @return the idModeleEnvoiRelance1
//     */
//    Long getIdModeleEnvoiRelance1();
//
//    /**
//     * Get the idModeleEnvoiRelance2 value.
//     * @return the idModeleEnvoiRelance2
//     */
//    Long getIdModeleEnvoiRelance2();

    /**
     * Identifiant du modèle d'email pour l'envoi de documents en pièce jointe.
     * @return l'identifiant du modèle d'email.
     */
    Long getIdModeleEnvoiDocumentsPieceJointe();
//
//    /**
//     * Getter function.
//     * @return the idModelePropositionParrainage
//     */
//    Long getIdModelePropositionParrainage();
//
//    /**
//     * Getter function.
//     * @return the idModeleRelanceParrainage
//     */
//    Long getIdModeleRelanceParrainage();
//
//    /**
//     * Récupère l'identifiant du modèle de l'email de confirmation d'une adhésion en ligne payée par carte.
//     * @return l'identifiant du modèle de l'email.
//     */
//    Long getIdModeleConfirmationAdhesionEnLigneCarte();
//
//    /**
//     * Récupère l'identifiant du modèle de l'email de confirmation d'une adhésion en ligne payée par chèque.
//     * @return l'identifiant du modèle de l'email.
//     */
//    Long getIdModeleConfirmationAdhesionEnLigneCheque();

    /**
     * Récupère l'identifiant du modèle de l'email de confirmation de création de l'espace client.
     * @return l'identifiant du modèle de l'email.
     */
    Long getIdModeleConfirmationCreationEspaceClient();
}
