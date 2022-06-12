CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_is_email_existed`(
	IN email VARCHAR(100),
    OUT result BOOLEAN
)
BEGIN
	SET result = FALSE;
    IF (SELECT COUNT(*) FROM users AS u WHERE u.email = email) > 0
	THEN 
		SET result = TRUE;
    END IF;
END