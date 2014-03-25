select 'Desactivation des contraintes de foreign keys' as '-------------------------------';
SET FOREIGN_KEY_CHECKS = 0;
select 'Import de la structure des tables' as '-------------------------------';
--   -------------------------------------------------- 
--   Generated by Enterprise Architect Version 7.5.848
--   Created On : mardi, 18 mars, 2014 
--   DBMS       : MySql 
--   -------------------------------------------------- 

USE quizz
;

SET FOREIGN_KEY_CHECKS=0;


--  Drop Tables, Stored Procedures and Views 
DROP TABLE IF EXISTS langage
;
DROP TABLE IF EXISTS niveau_question
;
DROP TABLE IF EXISTS question
;
DROP TABLE IF EXISTS quizz
;
DROP TABLE IF EXISTS quizz_question
;
DROP TABLE IF EXISTS quizz_sujet
;
DROP TABLE IF EXISTS reponse
;
DROP TABLE IF EXISTS reponse_candidat
;
DROP TABLE IF EXISTS role
;
DROP TABLE IF EXISTS type_sujet
;
DROP TABLE IF EXISTS user
;
DROP TABLE IF EXISTS user_roles
;

--  Create Tables 
CREATE TABLE langage
(
	id INTEGER NOT NULL AUTO_INCREMENT COMMENT 'id unique de la table langage',
	libelle VARCHAR(50) NOT NULL COMMENT 'libelle du langage (exemple jee,java,...)',
	PRIMARY KEY (id)
)  COMMENT='Table qui recense les diff�rents langage telle que java,UMl,C'
;


CREATE TABLE niveau_question
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	lib_niveau VARCHAR(50) COMMENT 'niveau du quizz',
	PRIMARY KEY (Id)
) 
;


CREATE TABLE question
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	ref_type_sujet INTEGER NOT NULL COMMENT 'cl� etrang�re vers la table des types de sujet',
	lib_question TEXT COMMENT 'libell� de la question pos�e',
	ref_niveau_question INTEGER NOT NULL COMMENT 'difficult� de la question',
	dat_creation DATE COMMENT 'date de cr�ation de la question',
	int_duree_reflexion INTEGER COMMENT 'dur�e estim�e pour r�pondre � une question',
	bol_unique_reponse BOOL COMMENT 'indique si une question a une ou plusieurs r�ponse(s) possible(s) 0->une reponse 1->plusieurs reponses',
	url_image VARCHAR(50) COMMENT 'url d''une �ventuelle image utilis�e pour la question',
	isValid BOOL COMMENT 'indique si une question est toujours valide c''est � dire utilisable par un quizz',
	PRIMARY KEY (Id),
	KEY (ref_niveau_question),
	KEY (ref_type_sujet)
)  COMMENT='recence des questions pour chaque type de sujet'
;


CREATE TABLE quizz
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	int_duree INTEGER,
	prenom_candidat VARCHAR(50),
	nom_candidat VARCHAR(50),
	lib_commentaire TEXT,
	dat_quizz DATETIME COMMENT 'date du passage du quizz par le candidat',
	ref_user INTEGER NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE UQ_quizz_Id(Id),
	KEY (ref_user)
)  COMMENT='recense des quizz'
;


CREATE TABLE quizz_question
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	ref_quizz INTEGER,
	ref_question INTEGER,
	PRIMARY KEY (Id),
	UNIQUE UQ_quizz_question_Id(Id),
	KEY (ref_question),
	KEY (ref_quizz)
)  COMMENT='table de correspondance entre le quizz et les diff�rentes questions pos�es'
;


CREATE TABLE quizz_sujet
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	ref_quizz INTEGER,
	ref_type_sujet INTEGER,
	ref_niveau_question INTEGER NOT NULL,
	PRIMARY KEY (Id),
	KEY (ref_niveau_question),
	KEY (ref_quizz),
	KEY (ref_type_sujet)
)  COMMENT='table de correspondance entre un quizz et les diff�rents types de sujets. '
;


CREATE TABLE reponse
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	ref_question INTEGER NOT NULL,
	lib_reponse TEXT COMMENT 'r�ponse d''une question',
	bol_type_reponse BOOL COMMENT 'indique s''il s''agit d''une bonne  r�ponse ou pas: 1->une bonne  reponse 0-> fausse reponse',
	PRIMARY KEY (id),
	KEY (ref_question)
)  COMMENT='recense les choix de r�ponses possibles aux questions'
;


CREATE TABLE reponse_candidat
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	ref_question INTEGER NOT NULL,
	ref_reponse INTEGER COMMENT 'r�f�rence de la r�ponse du candidat',
	ref_quizz INTEGER,
	PRIMARY KEY (Id),
	KEY (ref_question),
	KEY (ref_quizz),
	KEY (ref_reponse)
)  COMMENT='recense les r�ponses des candidats'
;


CREATE TABLE role
(
	id INTEGER NOT NULL,
	lib_role VARCHAR(50) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_roles_lib_role(lib_role)
)  COMMENT='cette table r�f�rentiel sert � resencer les diff�rents roles possible que puisse poss�der un utlisateurs'
;


CREATE TABLE type_sujet
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	libelle VARCHAR(50) NOT NULL COMMENT 'libell� du type (ex: Hibernate ou J2E)',
	ref_langage INTEGER NOT NULL COMMENT 'cl� �trang�re vers la table langage',
	PRIMARY KEY (Id),
	UNIQUE UQ_type_sujet_libelle(libelle),
	KEY (ref_langage)
)  COMMENT='recense les types de technologies des questions pos�es'
;


CREATE TABLE user
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	nom VARCHAR(50),
	prenom VARCHAR(50),
	login VARCHAR(20),
	password VARCHAR(20),
	PRIMARY KEY (Id)
)  COMMENT='recense tous les utlisateurs'
;


CREATE TABLE user_roles
(
	user_role_id INTEGER NOT NULL AUTO_INCREMENT,
	user_id INTEGER,
	role_id INTEGER,
	PRIMARY KEY (user_role_id),
	KEY (role_id),
	KEY (user_id)
) 
;



SET FOREIGN_KEY_CHECKS=1;


--  Create Foreign Key Constraints 
ALTER TABLE question ADD CONSTRAINT FK_question_niveau_question 
	FOREIGN KEY (ref_niveau_question) REFERENCES niveau_question (Id)
;

ALTER TABLE question ADD CONSTRAINT FK_question_type_sujet 
	FOREIGN KEY (ref_type_sujet) REFERENCES type_sujet (Id)
;

ALTER TABLE quizz ADD CONSTRAINT FK_quizz_user 
	FOREIGN KEY (ref_user) REFERENCES user (Id)
;

ALTER TABLE quizz_question ADD CONSTRAINT FK_quizz_question_question 
	FOREIGN KEY (ref_question) REFERENCES question (Id)
;

ALTER TABLE quizz_question ADD CONSTRAINT FK_quizz_question_quizz 
	FOREIGN KEY (ref_quizz) REFERENCES quizz (Id)
;

ALTER TABLE quizz_sujet ADD CONSTRAINT FK_quizz_sujet_niveau_question 
	FOREIGN KEY (ref_niveau_question) REFERENCES niveau_question (Id)
;

ALTER TABLE quizz_sujet ADD CONSTRAINT FK_quizz_sujet_quizz 
	FOREIGN KEY (ref_quizz) REFERENCES quizz (Id)
;

ALTER TABLE quizz_sujet ADD CONSTRAINT FK_quizz_sujet_type_sujet 
	FOREIGN KEY (ref_type_sujet) REFERENCES type_sujet (Id)
;

ALTER TABLE reponse ADD CONSTRAINT FK_reponse_question 
	FOREIGN KEY (ref_question) REFERENCES question (Id)
;

ALTER TABLE reponse_candidat ADD CONSTRAINT FK_reponse_candidat_question 
	FOREIGN KEY (ref_question) REFERENCES question (Id)
;

ALTER TABLE reponse_candidat ADD CONSTRAINT FK_reponse_candidat_quizz 
	FOREIGN KEY (ref_quizz) REFERENCES quizz (Id)
;

ALTER TABLE reponse_candidat ADD CONSTRAINT FK_reponse_candidat_reponse 
	FOREIGN KEY (ref_reponse) REFERENCES reponse (id)
;

ALTER TABLE type_sujet ADD CONSTRAINT FK_type_sujet_langage 
	FOREIGN KEY (ref_langage) REFERENCES langage (id)
;

ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_role 
	FOREIGN KEY (role_id) REFERENCES role (id)
;

ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_user 
	FOREIGN KEY (user_id) REFERENCES user (Id)
;
select 'Import des structures des tables termine' as '-------------------------------';
select 'Activation des contraintes de foreign keys' as '-------------------------------';
SET FOREIGN_KEY_CHECKS = 1;
select 'import des donnees en table' as '-------------------------------';
select 'traitement du fichier 00 - niveau_question.sql' as '-------------------------------';

INSERT INTO `niveau_question` (`Id`, `lib_niveau`) VALUES
	(1, 'facile'),
	(2, 'moyen'),
	(3, 'difficile');
select 'traitement du fichier 01 - langage.sql' as '-------------------------------';

INSERT INTO `langage` (`id`, `libelle`) VALUES
	(1, 'JAVA'),
	(3, 'C#');
select 'traitement du fichier 10 - type_sujet.sql' as '-------------------------------';
INSERT INTO `type_sujet` (`Id`, `libelle`, `ref_langage`) VALUES
	(1, 'hibernate', 1),
	(2, 'Java/J2EE', 1),
	(3, 'C natif', 3);
select 'traitement du fichier 20 - role.sql' as '-------------------------------';
INSERT INTO `role` (`id`, `lib_role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
select 'traitement du fichier 30 - user.sql' as '-------------------------------';
INSERT INTO `user` (`Id`, `nom`, `prenom`, `login`, `password`) VALUES
	(1, 'SECLY', 'Pierre', 'psecly', 'psecly'),
	(2, 'ATKIN', 'Philippe', 'patkin', 'patkin'),
	(3, 'OUAR', 'SALAH EDDINE', 'souar', 'pouar');
select 'traitement du fichier 31 - question.sql' as '-------------------------------';
INSERT INTO `question` (`Id`, `ref_type_sujet`, `lib_question`, `ref_niveau_question`, `dat_creation`, `int_duree_reflexion`, `bol_unique_reponse`, `url_image`, `isValid`) VALUES
	(10, 1, 'quelle est la différence entre HQL et Critéria ????????', 1, '2012-07-04', 120, 1, NULL, NULL),
	(11, 2, 'qu\'est ce qu\'un war', 1, '2012-07-04', 120, 0, NULL, NULL),
	(12, 3, 'quelle est la syntaxe correcte d\'une boucle', 1, '2013-08-12', 30, 1, NULL, NULL),
	(13, 2, 'test', 1, NULL, 222, 0, '20140216043837.jpg', 1);
select 'traitement du fichier 32 - reponse.sql' as '-------------------------------';
INSERT INTO `reponse` (`id`, `ref_question`, `lib_reponse`, `bol_type_reponse`) VALUES
	(30, 10, 'pas de différence', 0),
	(31, 10, 'un utilise les objets, l\'autre le SQL', 1),
	(32, 10, 'l\'un est transactionnel, l\'autre non', 0),
	(33, 10, 'aucune réponse précedement citée', 0),
	(34, 11, 'un zip', 1),
	(35, 11, 'une release pour un conteneur', 1),
	(36, 11, 'une image', 0),
	(37, 11, 'un éxécutable', 0),
	(38, 12, 'for(int i;i<50;i++){...}', 1),
	(39, 12, 'loop until( i<50){...}', 0),
	(40, 12, 'while(i<50){...}', 0),
	(41, 12, 'boucle(i<50){...}', 0),
	(42, 13, 'test1', 1);
select 'traitement du fichier 33 - userrole.sql' as '-------------------------------';
INSERT INTO `user_roles` (`USER_ROLE_ID`, `USER_ID`, `ROLE_ID`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 2, 1),
	(4, 3, 2);
select 'fin des traitements' as '-------------------------------';
