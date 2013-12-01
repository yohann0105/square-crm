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
package com.square.adherent.noyau.service.implementations;

import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.adherent.noyau.dao.interfaces.MagazineDao;
import com.square.adherent.noyau.dto.magazine.MagazineDto;
import com.square.adherent.noyau.model.data.magazine.AdherentMagazine;
import com.square.adherent.noyau.model.data.magazine.Magazine;
import com.square.adherent.noyau.service.interfaces.MagazineService;

/**
 * Implémentation des services spécifiques aux adhérents Smatis.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class MagazineServiceImpl implements MagazineService {

    private MagazineDao magazineDao;

    private MapperDozerBean mapperDozerBean;

    @Override
    public void createMagazine(Long idMagazine) {
        final Magazine magazine = new Magazine();
        magazine.setId(idMagazine);
        magazine.setDateCreation(Calendar.getInstance());
        magazineDao.saveMagazine(magazine);
    }

    @Override
    public MagazineDto getMagazine(Long idMagazine) {
        return mapperDozerBean.map(magazineDao.getMagazine(idMagazine), MagazineDto.class);
    }

    @Override
    public void marquerMagazineCommeEnvoye(Long idMagazine) {
        final Magazine magazine = magazineDao.getMagazine(idMagazine);
        if (magazine != null) {
            magazine.setDateModification(Calendar.getInstance());
            magazine.setEnvoye(true);
        }
    }

    @Override
    public void ajouterMagazineAdherents(Long idMagazine, List<Long> idsAdherent) {
        final Magazine magazine = magazineDao.getMagazine(idMagazine);

        for (Long idAdherent : idsAdherent) {
            final AdherentMagazine adherentMagazine = new AdherentMagazine();
            adherentMagazine.setMagazine(magazine);
            adherentMagazine.setUidPersonne(idAdherent);
            adherentMagazine.setDateCreation(Calendar.getInstance());
            magazineDao.saveAdherentMagazine(adherentMagazine);
        }
    }

    /**
     * Set the magazineDao value.
     * @param magazineDao the magazineDao to set
     */
    public void setMagazineDao(MagazineDao magazineDao) {
        this.magazineDao = magazineDao;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }
}
