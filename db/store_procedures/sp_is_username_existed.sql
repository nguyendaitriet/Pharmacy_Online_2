CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_is_username_existed`(
	IN username VARCHAR(50),
    OUT result BOOLEAN
)
BEGIN
	SET result = FALSE;
    IF (SELECT COUNT(*) FROM users AS u WHERE u.username = username) > 0
	THEN 
		SET result = TRUE;
    END IF;
END