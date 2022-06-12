CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_user_by_id`(
	IN id BIGINT
)
BEGIN
	SELECT
		u.id,
        u.username,
        u.`password`,
        u.fullName,
        u.address,
        u.dateOfBirth,
        u.email,
        u.phoneNumber,
        u.creationDate,
        u.`role`,
        u.blocked,
        u.gender
	FROM users AS u
    WHERE u.id = id;
END