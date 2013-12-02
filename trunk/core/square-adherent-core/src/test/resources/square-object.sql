CREATE TABLE data_personne_physique (
    personne_date_deces timestamp ,
    personne_date_naissance timestamp ,
    personne_deces boolean,
    personne_nb_enfants integer,
    personne_nom character varying(255),
    personne_nom_jf character varying(255),
    personne_prenom character varying(255),
    personne_refuse_etre_contacte boolean,
    personne_uid bigint NOT NULL,
    personne_civilite_uid bigint,
    personne_csp_uid bigint,
    personne_p_nature_uid bigint,
    personne_profession_uid bigint,
    reseau_uid bigint,
    personne_segment_uid bigint,
    personne_sitfam_uid bigint,
    personne_statut_uid bigint,
    personne_refus_offre_commerciale boolean DEFAULT false,
    personne_info_sante_uid bigint,
    personne_autres_dossier boolean,
    personne_autres_cotisation_annuelle_sup_m boolean,
    personne_autres_cotisation_unique_sup_dmcc boolean,
    personne_autres_domiciliation_tiers boolean,
    personne_autres_prestation_tiers boolean,
    personne_autres_cotisation_tiers boolean,
    personne_autres_bp boolean
);

CREATE TABLE data_personnes_emails (
    personnes_emails_eid character varying(255),
    personnes_emails_date_creation timestamp ,
    personnes_emails_date_suppression timestamp ,
    personnes_emails_supprime boolean,
    personne_uid bigint,
    email_uid bigint
);

CREATE TABLE data_email (
    email_uid bigint NOT NULL,
    email_version integer,
    email_eid character varying(255),
    email_date_creation timestamp NOT NULL,
    email_date_modification timestamp ,
    email_date_suppression timestamp ,
    email_supprime boolean NOT NULL,
    email_adresse character varying(255),
    email_nature_uid bigint NOT NULL,
    personne_uid bigint
);

INSERT INTO DATA_PERSONNE_PHYSIQUE (personne_uid,personne_nom,personne_prenom,personne_civilite_uid,personne_date_naissance) Values (1,'nom1','prenom1',1,'1987-09-30');
INSERT INTO DATA_PERSONNE_PHYSIQUE (personne_uid,personne_nom,personne_prenom,personne_civilite_uid,personne_date_naissance) Values (2,'nom2','prenom2',1,'1987-09-30');

INSERT INTO DATA_EMAIL (email_uid,email_date_creation,email_supprime,email_nature_uid) VALUES (1,'2008-10-25','false',1);
INSERT INTO DATA_EMAIL (email_uid,email_date_creation,email_supprime,email_nature_uid) VALUES (2,'2008-10-30','false',1);

INSERT INTO DATA_PERSONNES_EMAILS (personnes_emails_eid,personnes_emails_date_creation,personnes_emails_date_suppression,personnes_emails_supprime,personne_uid,email_uid) VALUES (1,'2008-10-25','2001-10-25','false',3,1);
INSERT INTO DATA_PERSONNES_EMAILS (personnes_emails_eid,personnes_emails_date_creation,personnes_emails_date_suppression,personnes_emails_supprime,personne_uid,email_uid) VALUES (1,'2008-10-30','2001-10-25','false',4,2);