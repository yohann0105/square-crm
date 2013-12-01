package com.square.adherent.noyau.service.mock;

import java.util.ArrayList;

import com.square.adherent.noyau.plugin.CotisationsPlugin;
import com.square.adherent.noyau.plugin.dto.cotisation.CotisationPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.CotisationsCriteresPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.ErreurRetourPluginDto;
import com.square.adherent.noyau.plugin.dto.cotisation.RetourCotisationsPluginDto;

/**
 * Mock pour le service des cotisations.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class CotisationsPluginMock implements CotisationsPlugin {

	@Override
	public RetourCotisationsPluginDto recupererCotisations(String login,
			CotisationsCriteresPluginDto criteresCotisations) {
		final RetourCotisationsPluginDto retour = new RetourCotisationsPluginDto();
		retour.setListeCotisations(new ArrayList<CotisationPluginDto>());
		retour.setErreurs(new ArrayList<ErreurRetourPluginDto>());
		retour.setSolde(0f);
		return retour;
	}

}
