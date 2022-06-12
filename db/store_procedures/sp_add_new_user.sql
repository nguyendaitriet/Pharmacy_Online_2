CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_new_user`(
    IN id BIGINT,
    IN fullName VARCHAR(100),
    IN gender INT,
    IN phoneNumber VARCHAR(20),
    IN email VARCHAR(100),
	IN address VARCHAR(200),
    IN dateOfBirth DATE,
    IN username VARCHAR(45),
	IN `password` VARCHAR(45),
    OUT `result` BOOLEAN
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
	START TRANSACTION;
    SET `result` = FALSE;
	INSERT INTO users (
		fullName,
        gender,
        phoneNumber,
        email,
        address,
        dateOfBirth,
        username,
        `password`
    )
    VALUES (
		fullName,
        gender,
        phoneNumber,
        email,
        address,
        dateOfBirth,
        username,
        `password`
    );
    COMMIT;
    SET `result` = TRUE;
END