CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_is_drug_id_existed`(
	IN id BIGINT,
    OUT result BOOLEAN
)
BEGIN
	SET result = FALSE;
    IF (SELECT COUNT(*) FROM drugs AS dr WHERE dr.id = id) > 0
	THEN 
		SET result = TRUE;
    END IF;
END