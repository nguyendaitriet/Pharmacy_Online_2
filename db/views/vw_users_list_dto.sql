CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vw_users_list_dto` AS
    SELECT 
        `u`.`id` AS `id`,
        `u`.`username` AS `username`,
        `u`.`fullName` AS `fullName`,
        `u`.`address` AS `address`,
        `u`.`dateOfBirth` AS `dateOfBirth`,
        `u`.`email` AS `email`,
        `u`.`phoneNumber` AS `phoneNumber`,
        `u`.`creationDate` AS `creationDate`,
        `r`.`Code` AS `role`,
        `g`.`name` AS `gender`
    FROM
        ((`users` `u`
        JOIN `genders` `g` ON ((`u`.`gender` = `g`.`id`)))
        JOIN `roles` `r` ON ((`u`.`role` = `r`.`id`)))