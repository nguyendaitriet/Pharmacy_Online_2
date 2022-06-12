CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_new_drug`(
	IN drugName VARCHAR(100),
    IN drugContent DOUBLE,
    IN quantity INT,
    IN pricePerPill DECIMAL(10,0),
    IN `usage` VARCHAR(100),
    IN dosageForm INT,
    IN productionDate DATE,
    IN expirationDate DATE,
    IN note VARCHAR(200),
    OUT `error` BOOLEAN,
    IN id BIGINT
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
	START TRANSACTION;
    SET `error` = FALSE;
	INSERT INTO drugs (
		drugName,
        drugContent,
        quantity,
        pricePerPill,
        `usage`,
        dosageForm,
        productionDate,
        expirationDate,
        note,
        deleted
    )
    VALUES (
		drugName,
        drugContent,
        quantity,
        pricePerPill,
        `usage`,
        dosageForm,
        productionDate,
        expirationDate,
        note,
        0
    );
    COMMIT;
    SET `error` = TRUE;
END