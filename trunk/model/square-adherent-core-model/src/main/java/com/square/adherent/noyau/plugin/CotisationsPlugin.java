package com.square.adherent.noyau.plugin;

import com.square.adherent.noyau.plugin.dto.cotisation.CotisationsCriteresPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.RetourCotisationsPluginDto;


/**
 * Interface qui définit le branchement d'un service de cotisations à square.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public interface CotisationsPlugin {

    /**
     * Récupère des cotisations.
     * @param login le login de l'utilisateur
     * @param criteresCotisations les criteres de cotisations
     * @return les cotisations
     */
    RetourCotisationsPluginDto recupererCotisations(final String login, final CotisationsCriteresPluginDto criteresCotisations);
}
