-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: adhound
-- ------------------------------------------------------
-- Server version	8.0.19-0ubuntu0.19.10.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contact_types`
--

DROP TABLE IF EXISTS `contact_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_types`
--

LOCK TABLES `contact_types` WRITE;
/*!40000 ALTER TABLE `contact_types` DISABLE KEYS */;
INSERT INTO `contact_types` VALUES (1,'Main Contact',NULL),(2,'Secondary Contact',NULL),(3,'Emergency Contact',NULL);
/*!40000 ALTER TABLE `contact_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_categories`
--

DROP TABLE IF EXISTS `location_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_categories`
--

LOCK TABLES `location_categories` WRITE;
/*!40000 ALTER TABLE `location_categories` DISABLE KEYS */;
INSERT INTO `location_categories` VALUES (1,'Bar',NULL),(2,'Resturant',NULL),(3,'Sport Complex',NULL);
/*!40000 ALTER TABLE `location_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_category`
--

DROP TABLE IF EXISTS `location_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_category` (
  `location_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`location_id`,`category_id`),
  KEY `category_fk` (`category_id`),
  CONSTRAINT `category_fk` FOREIGN KEY (`category_id`) REFERENCES `location_categories` (`id`),
  CONSTRAINT `category_location_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_category`
--

LOCK TABLES `location_category` WRITE;
/*!40000 ALTER TABLE `location_category` DISABLE KEYS */;
INSERT INTO `location_category` VALUES (1,1),(1,2),(2,2),(2,3);
/*!40000 ALTER TABLE `location_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_contact`
--

DROP TABLE IF EXISTS `location_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_contact` (
  `location_id` int NOT NULL,
  `contact_id` int NOT NULL,
  PRIMARY KEY (`location_id`,`contact_id`),
  KEY `contact_fk` (`contact_id`),
  CONSTRAINT `contact_fk` FOREIGN KEY (`contact_id`) REFERENCES `location_contacts` (`id`),
  CONSTRAINT `location_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_contact`
--

LOCK TABLES `location_contact` WRITE;
/*!40000 ALTER TABLE `location_contact` DISABLE KEYS */;
INSERT INTO `location_contact` VALUES (1,1),(2,1),(1,2),(2,2),(2,3);
/*!40000 ALTER TABLE `location_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_contacts`
--

DROP TABLE IF EXISTS `location_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_contacts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(24) NOT NULL,
  `last_name` varchar(24) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `city` varchar(24) NOT NULL,
  `state_id` int NOT NULL,
  `zipcode` varchar(11) NOT NULL,
  `type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contacts_state_fk` (`state_id`),
  KEY `contacts_type_fk` (`type_id`),
  CONSTRAINT `contacts_state_fk` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`),
  CONSTRAINT `contacts_type_fk` FOREIGN KEY (`type_id`) REFERENCES `contact_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_contacts`
--

LOCK TABLES `location_contacts` WRITE;
/*!40000 ALTER TABLE `location_contacts` DISABLE KEYS */;
INSERT INTO `location_contacts` VALUES (1,'Test 1','Last Name 1','(605) 456-8844','(605) 454-7844','test1@example.com',NULL,'Madison',16,'12345-67890',1),(2,'Test 2','Last Name  2','(705) 646-9743','(705) 345-7874','test2@example.com',NULL,'Markesan',20,'12345-67890',1),(3,'Test 3','Last Name  3','(805) 876-2244','(805) 777-7888','test3@example.com',NULL,'Fitchburg',33,'67890-12345',2);
/*!40000 ALTER TABLE `location_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_level`
--

DROP TABLE IF EXISTS `location_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_level` (
  `location_id` int NOT NULL,
  `level_id` int NOT NULL,
  PRIMARY KEY (`location_id`,`level_id`),
  KEY `level_fk` (`level_id`),
  CONSTRAINT `level_fk` FOREIGN KEY (`level_id`) REFERENCES `location_levels` (`id`),
  CONSTRAINT `locations_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_level`
--

LOCK TABLES `location_level` WRITE;
/*!40000 ALTER TABLE `location_level` DISABLE KEYS */;
INSERT INTO `location_level` VALUES (1,1),(2,1),(1,2),(2,2),(1,3);
/*!40000 ALTER TABLE `location_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_levels`
--

DROP TABLE IF EXISTS `location_levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_levels` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_levels`
--

LOCK TABLES `location_levels` WRITE;
/*!40000 ALTER TABLE `location_levels` DISABLE KEYS */;
INSERT INTO `location_levels` VALUES (1,'1st Floor'),(2,'2nd Floor'),(3,'3rd Floor');
/*!40000 ALTER TABLE `location_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_notes`
--

DROP TABLE IF EXISTS `location_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_notes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `note` varchar(1024) DEFAULT NULL,
  `location_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `notes_location_fk` (`location_id`),
  CONSTRAINT `notes_location_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_notes`
--

LOCK TABLES `location_notes` WRITE;
/*!40000 ALTER TABLE `location_notes` DISABLE KEYS */;
INSERT INTO `location_notes` VALUES (1,'Test Note 1',1),(2,'Test Note 2',1),(3,'Test Note 3',2),(4,'Test Note 4',2),(5,'Test Note 5',3);
/*!40000 ALTER TABLE `location_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_panel`
--

DROP TABLE IF EXISTS `location_panel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_panel` (
  `location_id` int NOT NULL,
  `level_id` int NOT NULL,
  `room_id` int NOT NULL,
  `wall_id` int NOT NULL,
  `panel_id` int NOT NULL,
  `height` float(5,3) NOT NULL,
  `width` float(5,3) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`location_id`,`level_id`,`room_id`,`wall_id`,`panel_id`),
  KEY `panel_fk` (`panel_id`),
  CONSTRAINT `location_panel_fk` FOREIGN KEY (`location_id`, `level_id`, `room_id`, `wall_id`) REFERENCES `location_wall` (`location_id`, `level_id`, `room_id`, `wall_id`) ON DELETE CASCADE,
  CONSTRAINT `panel_fk` FOREIGN KEY (`panel_id`) REFERENCES `location_panels` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_panel`
--

LOCK TABLES `location_panel` WRITE;
/*!40000 ALTER TABLE `location_panel` DISABLE KEYS */;
INSERT INTO `location_panel` VALUES (1,1,1,1,1,33.000,24.500,NULL),(1,1,1,1,2,24.750,18.000,NULL),(1,1,1,1,3,33.000,24.500,NULL),(1,1,1,1,4,48.000,20.000,NULL),(1,1,1,1,5,20.750,11.000,NULL),(1,1,1,1,6,30.000,24.500,NULL),(1,1,1,2,1,33.000,24.500,NULL),(1,1,1,2,2,24.750,24.500,NULL),(1,1,1,3,1,11.375,8.500,NULL),(1,1,1,3,2,11.375,8.500,NULL),(1,1,1,3,3,33.000,24.500,NULL),(2,2,1,1,1,11.375,8.500,NULL),(2,2,1,1,3,24.750,24.500,NULL),(2,2,1,2,1,11.375,8.500,NULL),(2,2,1,2,2,33.000,24.500,NULL),(2,2,1,2,3,24.750,24.500,NULL),(2,2,1,3,1,33.000,24.500,NULL),(2,2,1,3,2,24.750,24.500,NULL);
/*!40000 ALTER TABLE `location_panel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_panels`
--

DROP TABLE IF EXISTS `location_panels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_panels` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_panels`
--

LOCK TABLES `location_panels` WRITE;
/*!40000 ALTER TABLE `location_panels` DISABLE KEYS */;
INSERT INTO `location_panels` VALUES (1,'Panel A'),(2,'Panel B'),(3,'Panel C'),(4,'Panel D'),(5,'Panel E'),(6,'Panel F');
/*!40000 ALTER TABLE `location_panels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_regions`
--

DROP TABLE IF EXISTS `location_regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_regions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_regions`
--

LOCK TABLES `location_regions` WRITE;
/*!40000 ALTER TABLE `location_regions` DISABLE KEYS */;
INSERT INTO `location_regions` VALUES (1,'Test Region 1','Test Region 1 Description'),(2,'Test Region 2','Test Region 2 Description'),(3,'Test Region 3','Test Region 3 Description');
/*!40000 ALTER TABLE `location_regions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_room`
--

DROP TABLE IF EXISTS `location_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_room` (
  `location_id` int NOT NULL,
  `level_id` int NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`location_id`,`level_id`,`room_id`),
  KEY `room_fk` (`room_id`),
  CONSTRAINT `location_level_fk` FOREIGN KEY (`location_id`, `level_id`) REFERENCES `location_level` (`location_id`, `level_id`) ON DELETE CASCADE,
  CONSTRAINT `room_fk` FOREIGN KEY (`room_id`) REFERENCES `location_rooms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_room`
--

LOCK TABLES `location_room` WRITE;
/*!40000 ALTER TABLE `location_room` DISABLE KEYS */;
INSERT INTO `location_room` VALUES (1,1,1),(2,2,1),(1,2,2),(2,1,2),(1,1,3),(1,3,3);
/*!40000 ALTER TABLE `location_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_rooms`
--

DROP TABLE IF EXISTS `location_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_rooms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_rooms`
--

LOCK TABLES `location_rooms` WRITE;
/*!40000 ALTER TABLE `location_rooms` DISABLE KEYS */;
INSERT INTO `location_rooms` VALUES (1,'Men\'s Bathroom'),(2,'Entryway'),(3,'Women\'s Bathroom');
/*!40000 ALTER TABLE `location_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_wall`
--

DROP TABLE IF EXISTS `location_wall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_wall` (
  `location_id` int NOT NULL,
  `level_id` int NOT NULL,
  `room_id` int NOT NULL,
  `wall_id` int NOT NULL,
  PRIMARY KEY (`location_id`,`level_id`,`room_id`,`wall_id`),
  KEY `wall_fk` (`wall_id`),
  CONSTRAINT `location_wall_fk` FOREIGN KEY (`location_id`, `level_id`, `room_id`) REFERENCES `location_room` (`location_id`, `level_id`, `room_id`) ON DELETE CASCADE,
  CONSTRAINT `wall_fk` FOREIGN KEY (`wall_id`) REFERENCES `location_walls` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_wall`
--

LOCK TABLES `location_wall` WRITE;
/*!40000 ALTER TABLE `location_wall` DISABLE KEYS */;
INSERT INTO `location_wall` VALUES (1,1,1,1),(1,1,3,1),(2,2,1,1),(1,1,1,2),(1,1,3,2),(2,2,1,2),(1,1,1,3),(1,1,3,3),(2,2,1,3),(1,1,1,4),(1,1,3,4);
/*!40000 ALTER TABLE `location_wall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_walls`
--

DROP TABLE IF EXISTS `location_walls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_walls` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_walls`
--

LOCK TABLES `location_walls` WRITE;
/*!40000 ALTER TABLE `location_walls` DISABLE KEYS */;
INSERT INTO `location_walls` VALUES (1,'Wall A'),(2,'Wall B'),(3,'Wall C'),(4,'Wall D');
/*!40000 ALTER TABLE `location_walls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(48) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `city` varchar(24) NOT NULL,
  `state_id` int NOT NULL,
  `zipcode` varchar(11) NOT NULL,
  `region_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `locations_user_fk` (`user_id`),
  KEY `locations_state_fk` (`state_id`),
  KEY `locations_region_fk` (`region_id`),
  CONSTRAINT `locations_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `locations_region_fk` FOREIGN KEY (`region_id`) REFERENCES `location_regions` (`id`),
  CONSTRAINT `locations_state_fk` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1, 2,'BW-3s','(123) 456-7789','(123) 455-3890','123 Test Road','Madison',49,'12345-67890',1),(2, 2,'AJ Bombers','(445) 123-7890','(123) 444-7390','321 Test Road','Fitchburg',25,'67890-12345',2),(3, 2,'Great Dane','(664) 877-7890','(123) 444-7390','441 Test Road','Markesan',19,'67890-12345',3);
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `id` int NOT NULL AUTO_INCREMENT,
  `states_Abbreviation` varchar(2) NOT NULL,
  `states_Name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,'AL','Alabama'),(2,'AK','Alaska'),(3,'AZ','Arizona'),(4,'AR','Arkansas'),(5,'CA','California'),(6,'CO','Colorado'),(7,'CT','Connecticut'),(8,'DE','Delaware'),(9,'FL','Florida'),(10,'GA','Georgia'),(11,'HI','Hawaii'),(12,'ID','Idaho'),(13,'IL','Illinois'),(14,'IN','Indiana'),(15,'IA','Iowa'),(16,'KS','Kansas'),(17,'KY','Kentucky'),(18,'LA','Louisiana'),(19,'ME','Maine'),(20,'MD','Maryland'),(21,'MA','Massachusetts'),(22,'MI','Michigan'),(23,'MN','Minnesota'),(24,'MS','Mississippi'),(25,'MO','Missouri'),(26,'MT','Montana'),(27,'NE','Nebraska'),(28,'NV','Nevada'),(29,'NH','New Hampshire'),(30,'NJ','New Jersey'),(31,'NM','New Mexico'),(32,'NY','New York'),(33,'NC','North Carolina'),(34,'ND','North Dakota'),(35,'OH','Ohio'),(36,'OK','Oklahoma'),(37,'OR','Oregon'),(38,'PA','Pennsylvania'),(39,'RI','Rhode Island'),(40,'SC','South Carolina'),(41,'SD','South Dakota'),(42,'TN','Tennessee'),(43,'TX','Texas'),(44,'UT','Utah'),(45,'VT','Vermont'),(46,'VA','Virginia'),(47,'WA','Washington'),(48,'WV','West Virginia'),(49,'WI','Wisconsin'),(50,'WY','Wyoming');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL,
  `role_name` varchar(24) DEFAULT 'Distributor',
  PRIMARY KEY (`id`),
  KEY `username_fk` (`username`),
  CONSTRAINT `username_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'kkelm','Administrator'),(2,'distributor','Distributor'),(3,'runner','Runner');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL,
  `password` varchar(128) NOT NULL,
  `first_name` varchar(24) NOT NULL,
  `last_name` varchar(24) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `city` varchar(24) NOT NULL,
  `state_id` int NOT NULL,
  `zipcode` varchar(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `users_state_fk` (`state_id`),
  CONSTRAINT `users_state_fk` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'kkelm','testPassword','Kevin','Kelm','(123) 456-7890','(123) 789-4560','kkelm@outlook.com','123 Test Road','Fitchburg',49,'12345-67890'),(2,'distributor','test','Darth','Vader','(123) 456-7890','(123) 789-4560','test@gmail.com','123 Test Street','Madison',25,'12345-67890'),(3,'runner','123abc','Luke','Skywalker','(123) 456-7890','(123) 789-4560','test@yahoo.com','123 Test Circle','Markesan',33,'12345-67890');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17 16:00:22
