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

-- Export de la structure de la base pour cmgquizz
CREATE DATABASE IF NOT EXISTS `cmgquizz` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cmgquizz`;


-- Export de la structure de table cmgquizz. user_roles
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

-- Export de données de la table cmgquizz.user_roles: ~4 rows (environ)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`USER_ROLE_ID`, `USER_ID`, `ROLE_ID`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 2, 1),
	(4, 3, 2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
