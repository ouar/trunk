USE quizz
;


DROP TABLE IF EXISTS reponse_candidat
;
DROP TABLE IF EXISTS reponse
;
DROP TABLE IF EXISTS quizz_sujet
;
DROP TABLE IF EXISTS quizz_question
;
DROP TABLE IF EXISTS type_sujet
;
DROP TABLE IF EXISTS quizz
;
DROP TABLE IF EXISTS question
;
DROP TABLE IF EXISTS niveau_question
;
DROP TABLE IF EXISTS langage
;
DROP TABLE IF EXISTS admin
;



CREATE TABLE reponse_candidat
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	ref_question INTEGER NOT NULL,
	ref_reponse INTEGER,
	ref_quizz INTEGER,
	PRIMARY KEY (Id),
	KEY (ref_question),
	KEY (ref_quizz),
	KEY (ref_reponse)
) 
;


CREATE TABLE reponse
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	ref_question INTEGER NOT NULL,
	lib_reponse TEXT,
	bol_type_reponse BOOL,
	PRIMARY KEY (id),
	KEY (ref_question)
) 
;


CREATE TABLE quizz_sujet
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	ref_quizz INTEGER,
	ref_type_sujet INTEGER,
	PRIMARY KEY (Id),
	KEY (ref_quizz),
	KEY (ref_type_sujet)
) 
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
) 
;


CREATE TABLE type_sujet
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	libelle VARCHAR(50) NOT NULL,
	ref_langage INTEGER NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE UQ_type_sujet_libelle(libelle),
	KEY (ref_langage)
) 
;


CREATE TABLE quizz
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	int_duree INTEGER,
	lib_nom_candidat VARCHAR(50),
	lib_commentaire TEXT,
	dat_quizz DATETIME,
	ref_admin INTEGER NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE UQ_quizz_Id(Id),
	KEY (ref_admin)
) 
;


CREATE TABLE question
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	ref_type_sujet INTEGER NOT NULL,
	lib_question TEXT,
	ref_niveau_question INTEGER NOT NULL,
	dat_creation DATE,
	int_duree_reflexion INTEGER,
	bol_unique_reponse BOOL,
	url_image VARCHAR(50),
	isValid BOOL,
	PRIMARY KEY (Id),
	KEY (ref_niveau_question),
	KEY (ref_type_sujet)
) 
;


CREATE TABLE niveau_question
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	lib_niveau VARCHAR(50),
	PRIMARY KEY (Id)
) 
;


CREATE TABLE langage
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	libelle VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
) 
;


CREATE TABLE admin
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	lib_nom VARCHAR(50),
	lib_prenom VARCHAR(50),
	lib_password VARCHAR(50),
	PRIMARY KEY (Id)
) 
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

ALTER TABLE reponse ADD CONSTRAINT FK_reponse_question 
	FOREIGN KEY (ref_question) REFERENCES question (Id)
;

ALTER TABLE quizz_sujet ADD CONSTRAINT FK_quizz_sujet_quizz 
	FOREIGN KEY (ref_quizz) REFERENCES quizz (Id)
;

ALTER TABLE quizz_sujet ADD CONSTRAINT FK_quizz_sujet_type_sujet 
	FOREIGN KEY (ref_type_sujet) REFERENCES type_sujet (Id)
;

ALTER TABLE quizz_question ADD CONSTRAINT FK_quizz_question_question 
	FOREIGN KEY (ref_question) REFERENCES question (Id)
;

ALTER TABLE quizz_question ADD CONSTRAINT FK_quizz_question_quizz 
	FOREIGN KEY (ref_quizz) REFERENCES quizz (Id)
;

ALTER TABLE type_sujet ADD CONSTRAINT FK_type_sujet_langage 
	FOREIGN KEY (ref_langage) REFERENCES langage (id)
;

ALTER TABLE quizz ADD CONSTRAINT FK_quizz_admin 
	FOREIGN KEY (ref_admin) REFERENCES admin (Id)
;

ALTER TABLE question ADD CONSTRAINT FK_question_niveau_question 
	FOREIGN KEY (ref_niveau_question) REFERENCES niveau_question (Id)
;

ALTER TABLE question ADD CONSTRAINT FK_question_type_sujet 
	FOREIGN KEY (ref_type_sujet) REFERENCES type_sujet (Id)
;
