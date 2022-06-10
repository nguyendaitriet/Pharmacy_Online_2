CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_remove_drug`(
	IN id BIGINT,
    OUT result BOOLEAN
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
	SET result = FALSE;
    
    START TRANSACTION;
    UPDATE drugs AS dr
	SET deleted = 1
	WHERE dr.id = id;
    COMMIT;
    
	SET result = TRUE;
END