DROP VIEW IF EXISTS `vw_drugs_list`;
CREATE VIEW `vw_drugs_list` AS
    SELECT 
        `dr`.`id` AS `id`,
        `dr`.`drugName` AS `drugName`,
        `dr`.`drugContent` AS `drugContent`,
        `dr`.`quantity` AS `quantity`,
        `dr`.`pricePerPill` AS `pricePerPill`,
        `df`.`name` AS `dosageForm`,
        `dr`.`usage` AS `usage`,
        `dr`.`productionDate` AS `productionDate`,
        `dr`.`expirationDate` AS `expirationDate`,
        `dr`.`note` AS `note`
    FROM `drugs` AS`dr`
    JOIN `dosage_forms` `df` 
    ON `dr`.`dosageForm` = `df`.`id`
    ORDER BY `dr`.`id`;