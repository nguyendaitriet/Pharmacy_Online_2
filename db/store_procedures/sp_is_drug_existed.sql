CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_is_drug_existed`(
	IN drugName VARCHAR(100),
    IN drugContent DOUBLE,
    IN price DECIMAL(10,0),
    IN dosageForm INT,
    IN productionDate DATE,
    IN expirationDate DATE,
    OUT result BOOLEAN
)
BEGIN
	SET result = FALSE;
    IF (
		SELECT COUNT(*) 
        FROM drugs AS dr 
        WHERE  dr.drugName = drugName 
			AND dr.drugContent = drugContent
            AND dr.pricePerPill = price
            AND dr.dosageForm = dosageForm
			AND dr.productionDate = productionDate
			AND dr.expirationDate = expirationDate
            AND dr.deleted = 0
        ) > 0
	THEN 
		SET result = TRUE;
    END IF;
END