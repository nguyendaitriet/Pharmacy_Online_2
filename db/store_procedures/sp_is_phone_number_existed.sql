CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_is_phone_number_existed`(
	IN phoneNumber VARCHAR(20),
    OUT result BOOLEAN
)
BEGIN
	SET result = FALSE;
    IF (SELECT COUNT(*) FROM users AS u WHERE u.phoneNumber = phoneNumber) > 0
	THEN 
		SET result = TRUE;
    END IF;
END