package com.square.adherent.noyau.plugin;

/**
 * Interface qui définit le branchement d'un service de constantes AIA.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public interface ConstantesAiaPlugin {
	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementBvr();

	/** Moyen de paiement. @return le moyen de paiement */
    String getMoyenPaiementCarteBancaire();

    /** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementCheque();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementPrelevement();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementEspeces();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementMandat();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementVirement();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementVirementEncaissement();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementInterne();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementCetip();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementPrelevementAccelere();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementTip();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementOrdrePermanent();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementOrdreMultiple();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementDSSContributions();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementDomiciliation();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementVCS();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementChequeAvecCoupon();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementPrecompte();

	/** Moyen de paiement. @return le moyen de paiement */
	String getMoyenPaiementChequeManuel();

	/** Statut prime. @return le statut de prime. */
	String getStatutPrimeAnnulante();

	/** Statut prime. @return le statut de prime. */
    String getStatutPrimeAnnulee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeBloquee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeDemandee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeEnInstance();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeRemboursee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeSoldee();

    /** Situation. @return la situation */
    String getSituationEncaissementEnInstance();

    /** Situation. @return la situation */
    String getSituationEncaissementSoldee();

    /** Statut prime. @return le statut de prime. */
	String getStatutPrimeLibelleAnnulante();

	/** Statut prime. @return le statut de prime. */
    String getStatutPrimeLibelleAnnulee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeLibelleBloquee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeLibelleDemandee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeLibelleEnInstance();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeLibelleRemboursee();

    /** Statut prime. @return le statut de prime. */
    String getStatutPrimeLibelleSoldee();

    /** Situation. @return la situation */
    String getSituationEncaissementLibelleEnInstance();

    /** Situation. @return la situation */
    String getSituationEncaissementLibelleSoldee();

}
