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
package com.square.adherent.noyau.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Classe utilitaire pour les mots de passe de l'espace client.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class PasswordUtil {

    /**
     * Encrypteur des mots de passes.
     */
    private StandardPBEStringEncryptor encryptor;

    /**
     * Constructeur.
     * @param encryptorPassword le mot de passe à utiliser pour l'encrypteur
     * @param encryptorAlgorithm l'algorithm d'encryption
     */
    public PasswordUtil(String encryptorPassword, String encryptorAlgorithm) {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setAlgorithm(encryptorAlgorithm);
        encryptor.setPassword(encryptorPassword);
    }

    /**
     * Encrypte le mot de passe passé en paramètre.
     * @param plainPassword mot de passe en clair
     * @return le mot de passe encrypté
     */
    public String encrypt(String plainPassword) {
        return encryptor.encrypt(plainPassword);
    }

    /**
     * Décrypte le mot de passe passé en paramètre.
     * @param encryptedPassword mot de passe encrypté
     * @return le mot de passe décrypté
     */
    public String decrypt(String encryptedPassword) {
        return encryptor.decrypt(encryptedPassword);
    }
}
