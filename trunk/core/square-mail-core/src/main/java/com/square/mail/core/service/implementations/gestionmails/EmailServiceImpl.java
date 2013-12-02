package com.square.mail.core.service.implementations.gestionmails;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.core.model.dto.SearchUtilDto;

import com.square.mail.core.dto.gestionmails.CritereRechercheNombreEmailDto;
import com.square.mail.core.dto.gestionmails.EmailDestinataireDto;
import com.square.mail.core.dto.gestionmails.EmailDto;
import com.square.mail.core.dto.gestionmails.GroupeEmailDto;
import com.square.mail.core.dto.gestionmails.RechercheDestinataireQueryDto;
import com.square.mail.core.dto.gestionmails.RechercheDestinataireResultatsDto;
import com.square.mail.core.dto.gestionmails.RechercheEmailRequeteDto;
import com.square.mail.core.dto.gestionmails.RechercheEmailResultatDto;
import com.square.mail.core.service.interfaces.gestionmails.EmailService;

/**
 * Implémentation du service des emails.
 * @author "Arnaud Brochain (arnaud.brochain@scub.net)" 7 sept. 2012
 *
 */
@SuppressWarnings("deprecation")
public class EmailServiceImpl implements EmailService {

	@Override
	public void changerEtatEmail(Long idEmail, Long idEtat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changerEtatEmailsByGroupeEmail(Long idGroupeEmail, Long idEtat) {
		// TODO Auto-generated method stub

	}

	@Override
	public EmailDto envoyerEmail(EmailDto email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdentifiantLibelleDto> getFiltresServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdentifiantLibelleDto> getFiltresUtilisateurs(Long idService) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupeEmailDto getGroupeEmailParEmail(Long idEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNombreEmailsByCriteres(
			CritereRechercheNombreEmailDto criteresDto) {
		return 0;
	}

	@Override
	public Integer getNombreTotalEmails() {
		return 0;
	}

	@Override
	public RechercheDestinataireResultatsDto rechercherEmailsByNomOrEmail(
			RechercheDestinataireQueryDto criteres) {
		RechercheDestinataireResultatsDto resultat = new RechercheDestinataireResultatsDto();
		resultat.setListeResultats(new ArrayList<EmailDestinataireDto>());
		return resultat;
	}

	@Override
	public SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto> rechercherEmailsParCriteres(
			SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto> criteres) {
		SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto> resultat = new SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto>();
		resultat.setTotalSearchResult(0);
		resultat.setResults(new ArrayList<RechercheEmailResultatDto>());
		return resultat;
	}

	@Override
	public void transfererGroupe(List<Long> listeGroupes,
			Long idUtilisateurDestination) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verrouillerEmailsByGroupeEmail(Long idGroupeEmail,
			Boolean aVerrouiller) {
		// TODO Auto-generated method stub

	}

}
