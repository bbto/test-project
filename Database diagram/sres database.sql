-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-0ubuntu6


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sres
--

CREATE DATABASE IF NOT EXISTS sres;
USE sres;

--
-- Definition of table `sres`.`activities`
--

DROP TABLE IF EXISTS `sres`.`activities`;
CREATE TABLE  `sres`.`activities` (
  `id` int(11) NOT NULL auto_increment,
  `subject_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `link` tinytext NOT NULL,
  `scribd_id` int(11) NOT NULL,
  `scribd_key` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `subject_id_index2` (`subject_id`),
  CONSTRAINT `subject_id_fk2` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sres`.`activities`
--

/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
LOCK TABLES `activities` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;


--
-- Definition of table `sres`.`bulletins`
--

DROP TABLE IF EXISTS `sres`.`bulletins`;
CREATE TABLE  `sres`.`bulletins` (
  `id` int(11) NOT NULL auto_increment,
  `subject_id` int(11) NOT NULL,
  `context` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `subject_id_index` (`subject_id`),
  CONSTRAINT `subject_id_fk` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sres`.`bulletins`
--

/*!40000 ALTER TABLE `bulletins` DISABLE KEYS */;
LOCK TABLES `bulletins` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `bulletins` ENABLE KEYS */;


--
-- Definition of table `sres`.`competitions`
--

DROP TABLE IF EXISTS `sres`.`competitions`;
CREATE TABLE  `sres`.`competitions` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sres`.`competitions`
--

/*!40000 ALTER TABLE `competitions` DISABLE KEYS */;
LOCK TABLES `competitions` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `competitions` ENABLE KEYS */;


--
-- Definition of table `sres`.`rates`
--

DROP TABLE IF EXISTS `sres`.`rates`;
CREATE TABLE  `sres`.`rates` (
  `id` int(11) NOT NULL auto_increment,
  `student_subject_id` int(11) NOT NULL,
  `cuantification` double NOT NULL,
  `qualification` text NOT NULL,
  `activity_id` int(11) NOT NULL,
  `answer` text NOT NULL,
  `type` int(11) NOT NULL,
  `link` tinytext NOT NULL,
  `scribd_id` int(11) NOT NULL,
  `scribd_key` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `student_subject_id_index` (`student_subject_id`),
  KEY `activity_id_index` (`activity_id`),
  CONSTRAINT `student_subject_id_fk` FOREIGN KEY (`student_subject_id`) REFERENCES `student_subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_id_fk` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sres`.`rates`
--

/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
LOCK TABLES `rates` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;


--
-- Definition of table `sres`.`student_subjects`
--

DROP TABLE IF EXISTS `sres`.`student_subjects`;
CREATE TABLE  `sres`.`student_subjects` (
  `id` int(11) NOT NULL auto_increment,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `final_grade` double NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `student_id_index1` (`student_id`),
  KEY `subject_id_index1` (`subject_id`),
  CONSTRAINT `student_id_fk1` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subject_id_fk1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sres`.`student_subjects`
--

/*!40000 ALTER TABLE `student_subjects` DISABLE KEYS */;
LOCK TABLES `student_subjects` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `student_subjects` ENABLE KEYS */;


--
-- Definition of table `sres`.`subjects`
--

DROP TABLE IF EXISTS `sres`.`subjects`;
CREATE TABLE  `sres`.`subjects` (
  `id` int(11) NOT NULL auto_increment,
  `competition_id` int(11) NOT NULL,
  `creation_date` datetime NOT NULL,
  `professor_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `professor_id_index` (`professor_id`),
  KEY `competition_id_index` (`competition_id`),
  CONSTRAINT `competition_id_fk` FOREIGN KEY (`competition_id`) REFERENCES `competitions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `professor_id_fk` FOREIGN KEY (`professor_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sres`.`subjects`
--

/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
LOCK TABLES `subjects` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;


--
-- Definition of table `sres`.`users`
--

DROP TABLE IF EXISTS `sres`.`users`;
CREATE TABLE  `sres`.`users` (
  `id` int(11) NOT NULL auto_increment,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `role` int(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sres`.`users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
LOCK TABLES `users` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
