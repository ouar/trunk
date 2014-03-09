# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     quizz
# Server version:               5.5.12
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2013-08-13 14:27:54
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping data for table type_sujet: 4 rows
/*!40000 ALTER TABLE `type_sujet` DISABLE KEYS */;
INSERT INTO `type_sujet` (`Id`, `libelle`, `ref_langage`) VALUES
	(1, 'hibernate', 1),
	(2, 'Java/J2EE', 1),
	(3, 'C natif', 3);
/*!40000 ALTER TABLE `type_sujet` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
