DELIMITER \\

CREATE PROCEDURE `sp_find_drug_by_id`(
	IN id BIGINT
)
BEGIN
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
	FROM drugs AS dr
    WHERE dr.id = id;
END \\

DELIMITER ;