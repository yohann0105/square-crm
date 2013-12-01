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
package com.square.notificateur.core.dao.implementations;


import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.scub.foundation.framework.core.dao.implementations.HibernateDaoBaseImplementation;

import com.square.notificateur.core.dao.interfaces.MessageDao;
import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.model.Message;


/**
 * Implémentation de l'interface MessageDao.
 * @author KsouriMohamedAli - SCUB
 */
public class MessageDaoImplementation extends HibernateDaoBaseImplementation implements MessageDao {

	@Override
	public void creerMessage(Message message) {
		save(message);

	}

	@Override
	public Message getMessageById(Long idMessage) {
		return load(idMessage, Message.class);
	}


	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Message> rechercherMessageParCriteres(MessageCriteresDto criteres) {
		final Long idUtilisateur = criteres.getIdUtilisateur();
		final Calendar datePublication = criteres.getDatePublication();
		final Boolean inclureAquites = criteres.getInclureAquites();
		final Criteria criteria = createCriteria(Message.class);
		if (idUtilisateur != null) {
			criteria.add(Restrictions.eq("idUtilisateur", idUtilisateur));
		}
		if (datePublication != null) {
			criteria.add(Expression.le("datePublication", datePublication));
		}
		if (inclureAquites != null && inclureAquites.equals(false)) {
			criteria.add(Restrictions.isNull("dateReception"));
		}

		// Tri croissant par le nom des Messages trouvés suivant la date de publication
		criteria.addOrder(Order.asc("datePublication"));
		return (List<Message>) criteria.list();
	}


}
