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
-- Temporary view structure for view `vw_drugs_list`
--

DROP TABLE IF EXISTS `vw_drugs_list`;
/*!50001 DROP VIEW IF EXISTS `vw_drugs_list`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_drugs_list` AS SELECT 
 1 AS `id`,
 1 AS `drugName`,
 1 AS `drugContent`,
 1 AS `quantity`,
 1 AS `pricePerPill`,
 1 AS `dosageForm`,
 1 AS `usage`,
 1 AS `productionDate`,
 1 AS `expirationDate`,
 1 AS `note`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_users_list_dto`
--

DROP TABLE IF EXISTS `vw_users_list_dto`;
/*!50001 DROP VIEW IF EXISTS `vw_users_list_dto`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_users_list_dto` AS SELECT 
 1 AS `id`,
 1 AS `username`,
 1 AS `fullName`,
 1 AS `address`,
 1 AS `dateOfBirth`,
 1 AS `email`,
 1 AS `phoneNumber`,
 1 AS `creationDate`,
 1 AS `role`,
 1 AS `gender`,
 1 AS `blocked`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_drugs_list_dto`
--

DROP TABLE IF EXISTS `vw_drugs_list_dto`;
/*!50001 DROP VIEW IF EXISTS `vw_drugs_list_dto`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_drugs_list_dto` AS SELECT 
 1 AS `id`,
 1 AS `drugName`,
 1 AS `drugContent`,
 1 AS `quantity`,
 1 AS `pricePerPill`,
 1 AS `dosageForm`,
 1 AS `usage`,
 1 AS `productionDate`,
 1 AS `expirationDate`,
 1 AS `note`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vw_drugs_list`
--

/*!50001 DROP VIEW IF EXISTS `vw_drugs_list`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_drugs_list` AS select `dr`.`id` AS `id`,`dr`.`drugName` AS `drugName`,`dr`.`drugContent` AS `drugContent`,`dr`.`quantity` AS `quantity`,`dr`.`pricePerPill` AS `pricePerPill`,`dr`.`dosageForm` AS `dosageForm`,`dr`.`usage` AS `usage`,`dr`.`productionDate` AS `productionDate`,`dr`.`expirationDate` AS `expirationDate`,`dr`.`note` AS `note` from `drugs` `dr` where (`dr`.`deleted` = 0) order by `dr`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_users_list_dto`
--

/*!50001 DROP VIEW IF EXISTS `vw_users_list_dto`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_users_list_dto` AS select `u`.`id` AS `id`,`u`.`username` AS `username`,`u`.`fullName` AS `fullName`,`u`.`address` AS `address`,`u`.`dateOfBirth` AS `dateOfBirth`,`u`.`email` AS `email`,`u`.`phoneNumber` AS `phoneNumber`,`u`.`creationDate` AS `creationDate`,`r`.`Code` AS `role`,`g`.`name` AS `gender`,`u`.`blocked` AS `blocked` from ((`users` `u` join `genders` `g` on((`u`.`gender` = `g`.`id`))) join `roles` `r` on((`u`.`role` = `r`.`id`))) order by `u`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_drugs_list_dto`
--

/*!50001 DROP VIEW IF EXISTS `vw_drugs_list_dto`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_drugs_list_dto` AS select `dr`.`id` AS `id`,`dr`.`drugName` AS `drugName`,`dr`.`drugContent` AS `drugContent`,`dr`.`quantity` AS `quantity`,`dr`.`pricePerPill` AS `pricePerPill`,`df`.`name` AS `dosageForm`,`dr`.`usage` AS `usage`,`dr`.`productionDate` AS `productionDate`,`dr`.`expirationDate` AS `expirationDate`,`dr`.`note` AS `note` from (`drugs` `dr` join `dosage_forms` `df` on((`dr`.`dosageForm` = `df`.`id`))) where (`dr`.`deleted` = 0) order by `dr`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-15  9:20:27
