CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_block_or_unblock_user`(
	IN id BIGINT,
    IN `block` BOOLEAN
)
BEGIN
		IF (`block`) THEN
			UPDATE users AS u
            SET blocked = 1
            WHERE u.id = id;
		ELSE 
			UPDATE users AS u
            SET blocked = 0
            WHERE u.id = id;
		END IF;
END