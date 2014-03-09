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


-- Export de la structure de table cmgquizz. role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL,
  `lib_role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_roles_lib_role` (`lib_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='cette table référentiel sert à resencer les différents roles possible que puisse posséder un utlisateurs';

-- Export de données de la table cmgquizz.role: ~2 rows (environ)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `lib_role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
