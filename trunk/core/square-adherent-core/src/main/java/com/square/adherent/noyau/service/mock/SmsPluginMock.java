package com.square.adherent.noyau.service.mock;

import com.square.adherent.noyau.plugin.SmsPlugin;
import com.square.adherent.noyau.plugin.dto.sms.CreationSmsDto;
import com.square.adherent.noyau.plugin.dto.sms.SmsDto;

/**
 * Mock pour le plugin SMS.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class SmsPluginMock implements SmsPlugin {

	@Override
	public SmsDto creerSms(CreationSmsDto creationSms) {
		// FIXME OpenSource
		return null;
	}

	@Override
	public SmsDto envoyerSms(Long id) {
		// FIXME OpenSource
		return null;
	}

}
