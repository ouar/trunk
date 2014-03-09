-- --------------------------------------------------------
-- Hôte:                         ec2-50-19-213-178.compute-1.amazonaws.com
-- Version du serveur:           5.5.32-log - Source distribution
-- Serveur OS:                   Linux
-- HeidiSQL Version:             8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de table cmgquizz. langage
DROP TABLE IF EXISTS `langage`;
CREATE TABLE IF NOT EXISTS `langage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id unique de la table langage',
  `libelle` varchar(50) NOT NULL COMMENT 'libelle du langage (exemple jee,java,...)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table qui recense les différents langage telle que java,UMl,C';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. niveau_question
DROP TABLE IF EXISTS `niveau_question`;
CREATE TABLE IF NOT EXISTS `niveau_question` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `lib_niveau` varchar(50) DEFAULT NULL COMMENT 'niveau du quizz',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. question
DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_type_sujet` int(11) NOT NULL COMMENT 'clé etrangère vers la table des types de sujet',
  `lib_question` text COMMENT 'libellé de la question posée',
  `ref_niveau_question` int(11) NOT NULL COMMENT 'difficulté de la question',
  `dat_creation` date DEFAULT NULL COMMENT 'date de création de la question',
  `int_duree_reflexion` int(11) DEFAULT NULL COMMENT 'durée estimée pour répondre à une question',
  `bol_unique_reponse` tinyint(1) DEFAULT NULL COMMENT 'indique si une question a une ou plusieurs réponse(s) possible(s) 0->une reponse 1->plusieurs reponses',
  `url_image` varchar(50) DEFAULT NULL COMMENT 'url d''une éventuelle image utilisée pour la question',
  `isValid` tinyint(1) DEFAULT NULL COMMENT 'indique si une question est toujours valide c''est à dire utilisable par un quizz',
  PRIMARY KEY (`Id`),
  KEY `ref_niveau_question` (`ref_niveau_question`),
  KEY `ref_type_sujet` (`ref_type_sujet`),
  CONSTRAINT `FK_question_niveau_question` FOREIGN KEY (`ref_niveau_question`) REFERENCES `niveau_question` (`Id`),
  CONSTRAINT `FK_question_type_sujet` FOREIGN KEY (`ref_type_sujet`) REFERENCES `type_sujet` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='recence des questions pour chaque type de sujet';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. quizz
DROP TABLE IF EXISTS `quizz`;
CREATE TABLE IF NOT EXISTS `quizz` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `int_duree` int(11) DEFAULT NULL,
  `lib_nom_candidat` varchar(50) DEFAULT NULL,
  `lib_commentaire` text,
  `dat_quizz` datetime DEFAULT NULL COMMENT 'date du passage du quizz par le candidat',
  `ref_user` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UQ_quizz_Id` (`Id`),
  KEY `ref_user` (`ref_user`),
  CONSTRAINT `FK_quizz_user` FOREIGN KEY (`ref_user`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='recense des quizz';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. quizz_question
DROP TABLE IF EXISTS `quizz_question`;
CREATE TABLE IF NOT EXISTS `quizz_question` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_quizz` int(11) DEFAULT NULL,
  `ref_question` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UQ_quizz_question_Id` (`Id`),
  KEY `ref_question` (`ref_question`),
  KEY `ref_quizz` (`ref_quizz`),
  CONSTRAINT `FK_quizz_question_question` FOREIGN KEY (`ref_question`) REFERENCES `question` (`Id`),
  CONSTRAINT `FK_quizz_question_quizz` FOREIGN KEY (`ref_quizz`) REFERENCES `quizz` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table de correspondance entre le quizz et les différentes questions posées';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. quizz_sujet
DROP TABLE IF EXISTS `quizz_sujet`;
CREATE TABLE IF NOT EXISTS `quizz_sujet` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_quizz` int(11) DEFAULT NULL,
  `ref_type_sujet` int(11) DEFAULT NULL,
  `ref_niveau_question` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ref_niveau_question` (`ref_niveau_question`),
  KEY `ref_quizz` (`ref_quizz`),
  KEY `ref_type_sujet` (`ref_type_sujet`),
  CONSTRAINT `FK_quizz_sujet_niveau_question` FOREIGN KEY (`ref_niveau_question`) REFERENCES `niveau_question` (`Id`),
  CONSTRAINT `FK_quizz_sujet_quizz` FOREIGN KEY (`ref_quizz`) REFERENCES `quizz` (`Id`),
  CONSTRAINT `FK_quizz_sujet_type_sujet` FOREIGN KEY (`ref_type_sujet`) REFERENCES `type_sujet` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table de correspondance entre un quizz et les différents types de sujets. ';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. reponse
DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_question` int(11) NOT NULL,
  `lib_reponse` text COMMENT 'réponse d''une question',
  `bol_type_reponse` tinyint(1) DEFAULT NULL COMMENT 'indique s''il s''agit d''une bonne  réponse ou pas: 0->une bonne  reponse 1-> fausse reponses',
  PRIMARY KEY (`id`),
  KEY `ref_question` (`ref_question`),
  CONSTRAINT `FK_reponse_question` FOREIGN KEY (`ref_question`) REFERENCES `question` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='recense les choix de réponses possibles aux questions';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. reponse_candidat
DROP TABLE IF EXISTS `reponse_candidat`;
CREATE TABLE IF NOT EXISTS `reponse_candidat` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_question` int(11) NOT NULL,
  `ref_reponse` int(11) DEFAULT NULL COMMENT 'référence de la réponse du candidat',
  `ref_quizz` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ref_question` (`ref_question`),
  KEY `ref_quizz` (`ref_quizz`),
  KEY `ref_reponse` (`ref_reponse`),
  CONSTRAINT `FK_reponse_candidat_question` FOREIGN KEY (`ref_question`) REFERENCES `question` (`Id`),
  CONSTRAINT `FK_reponse_candidat_quizz` FOREIGN KEY (`ref_quizz`) REFERENCES `quizz` (`Id`),
  CONSTRAINT `FK_reponse_candidat_reponse` FOREIGN KEY (`ref_reponse`) REFERENCES `reponse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='recense les réponses des candidats';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL,
  `lib_role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_roles_lib_role` (`lib_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='cette table référentiel sert à resencer les différents roles possible que puisse posséder un utlisateurs';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. type_sujet
DROP TABLE IF EXISTS `type_sujet`;
CREATE TABLE IF NOT EXISTS `type_sujet` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL COMMENT 'libellé du type (ex: Hibernate ou J2E)',
  `ref_langage` int(11) NOT NULL COMMENT 'clé étrangére vers la table langage',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UQ_type_sujet_libelle` (`libelle`),
  KEY `ref_langage` (`ref_langage`),
  CONSTRAINT `FK_type_sujet_langage` FOREIGN KEY (`ref_langage`) REFERENCES `langage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='recense les types de technologies des questions posées';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `lib_nom` varchar(50) DEFAULT NULL,
  `lib_prenom` varchar(50) DEFAULT NULL,
  `lib_password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='recense tous les utlisateurs';

-- L'exportation de données n'été pas sélectionné.


-- Export de la structure de table cmgquizz. user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `USER_ROLE_ID` int(11) unsigned NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles_user` (`USER_ID`),
  KEY `FK_user_roles_role` (`ROLE_ID`),
  CONSTRAINT `FK_user_roles_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_user_roles_user` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- L'exportation de données n'été pas sélectionné.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
