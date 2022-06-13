-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: pharmacy_online
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `creationDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role` int NOT NULL DEFAULT '1',
  `blocked` tinyint NOT NULL DEFAULT '0',
  `gender` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `userName_UNIQUE` (`username`),
  UNIQUE KEY `phoneNumber_UNIQUE` (`phoneNumber`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `gender_idx` (`gender`),
  KEY `role_idx` (`role`),
  CONSTRAINT `gender` FOREIGN KEY (`gender`) REFERENCES `genders` (`id`),
  CONSTRAINT `role` FOREIGN KEY (`role`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'nguyenpham123','nguyen1234!','Pham Nguyen','Hue','2000-06-05','nguyen@gmail.com','0124327564','2022-06-05 00:00:00',1,0,1),(2,'nguyenpha123','nguyen1234!','Pham Nguyen','Hue','2000-06-05','guyen@gmail.com','0124323532','2022-06-05 00:00:00',1,0,1),(3,'nguyenm123','nguyen1234!','Pham Nguyen','Hue','2000-06-05','ngun@gmail.com','0325345564','2022-06-05 00:00:00',1,0,1),(4,'namnguyen','nam1122!','Nguyen Nam','Ha Noi','1998-07-05','nam@mail.com','0134354254','2022-06-11 15:25:07',1,0,2),(5,'hoale889','hoa3332!','Le Thi Hoa','Thanh Hoa','1996-12-04','lehoa@gmail.com','0674356322','2022-06-11 15:26:15',2,0,2),(6,'nhathung99','hung342!','Pham Nhat Hung','Lai Chau','1991-11-19','hung@gmail.com','0346554678','2022-06-12 14:57:01',1,0,1),(7,'tuan123','tuan123!','Nguyen Tuan','Hai Duong','1994-09-01','tuan213@gmail.com','0456847455','2022-06-12 15:03:37',1,0,3),(8,'qynyna123','Pa$$w0rd!','Rose Kirkland','Ipsam consequatur B','2000-01-30','dehenoly@mailinator.com','0783465748','2022-06-12 21:26:44',1,0,2);
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

-- Dump completed on 2022-06-13  8:03:13
