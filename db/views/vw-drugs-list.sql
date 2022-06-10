CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vw_drugs_list` AS
    SELECT 
        `dr`.`id` AS `id`,
        `dr`.`drugName` AS `drugName`,
        `dr`.`drugContent` AS `drugContent`,
        `dr`.`quantity` AS `quantity`,
        `dr`.`pricePerPill` AS `pricePerPill`,
        `dr`.`dosageForm` AS `dosageForm`,
        `dr`.`usage` AS `usage`,
        `dr`.`productionDate` AS `productionDate`,
        `dr`.`expirationDate` AS `expirationDate`,
        `dr`.`note` AS `note`
    FROM
        `drugs` `dr`
    WHERE
        (`dr`.`deleted` = 0)
    ORDER BY `dr`.`id`