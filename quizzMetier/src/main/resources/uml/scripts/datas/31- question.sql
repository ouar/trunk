# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     quizz
# Server version:               5.5.12
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2013-08-13 14:27:36
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping data for table question: 3 rows
/*!40000 ALTER TABLE `question` DISABLE KEYS */;


INSERT INTO `question` (`Id`, `ref_type_sujet`, `lib_question`, `ref_niveau_question`, `dat_creation`, `int_duree_reflexion`, `bol_unique_reponse`, `url_image`, `isValid`) VALUES
	(10, 1, 'quel est la différence entre HQL et Critéria', 1, '2012-07-04', 120, 1, NULL, NULL),
	(11, 2, 'qu\'est ce qu\'un war', 1, '2012-07-04', 120, 0, NULL, NULL),
	(12, 3, 'quelle est la syntaxe correcte d\'une boucle', 1, '2013-08-12', 30, 1, NULL, NULL),
	(13, 2, 'test', 1, NULL, 222, 0, '20140216043837.jpg', 1);

/*!40000 ALTER TABLE `question` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
