# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     quizz
# Server version:               5.5.12
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2013-08-13 14:28:20
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping data for table quizz.reponse: 12 rows
/*!40000 ALTER TABLE `reponse` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `reponse` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
