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
package com.square.adherent.noyau.service.test.mock;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.CoreRunTimeException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.square.adherent.noyau.service.test.Messages;
import com.square.core.model.dto.RapportDto;
import com.square.mail.core.dto.emails.EmailAvecModeleDto;
import com.square.mail.core.dto.emails.MailDto;
import com.square.mail.core.dto.emails.PieceJointeFileDto;
import com.square.mail.core.service.interfaces.email.MailService;

/**
 * Mock pour les appels à MailService du noyau envoi-mail dans les tests unitaires.
 * @author nnadeau - SCUB
 */
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    /**
     * {@inheritDoc}
     */
    public String envoyerMail(final MailDto mail) {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    message.setFrom(mail.getExpediteur());
                    if (mail.getDestinataireInternetAdress() == null) { throw new BusinessException(Messages.getString("MailServiceImpl.1")); }
                    for (InternetAddress destinataire : mail.getDestinataireInternetAdress()) {
                        if (destinataire.getAddress() == null || destinataire.getAddress().equals("")) {
                            throw new BusinessException(Messages.getString("MailServiceImpl.3"));
                        }
                    }
                    message.setTo("scub.mail@gmail.com");
                    message.setSubject(mail.getTitre());
                    message.setText(mail.getTexte());
                    // Pièces jointes.
                    for (PieceJointeFileDto pieceJointeFile : mail.getListePiecesJointes()) {
                        try {
                            if (pieceJointeFile != null) {
                                message.addAttachment(pieceJointeFile.getNom(), new ByteArrayDataSource(pieceJointeFile.getData(),
                                        pieceJointeFile.getTypeMime()));
                            }
                        } catch (CoreRunTimeException e) {
                            throw new TechnicalException(e.getMessage());
                        }
                    }
                }
            };
            mailSender.send(preparator);
            return "messageId";
        } catch (MailException ex) {
            throw new TechnicalException(Messages.getString("MailServiceImpl.6") + ex.getMessage());
        }
    }

    /**
     * Set the mailSender value.
     * @param mailSender the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * {@inheritDoc}
     */
    public String envoyerMailDepuisModele(EmailAvecModeleDto emailAvecModeleDto) {
        return null;
    }

	@Override
	public Boolean verifierEmail(MailDto email, RapportDto rapportErreur) {
		return null;
	}

}
