CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_drug`(
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
	UPDATE drugs AS dr
	SET
		drugName = drugName,
        drugContent = drugContent,
        quantity = quantity,
        pricePerPill = pricePerPill,
        `usage` = `usage`,
        dosageForm = dosageForm,
        productionDate = productionDate,
        expirationDate = expirationDate,
        note = note
    WHERE dr.id = id;
    COMMIT;
    SET `error` = TRUE;
END