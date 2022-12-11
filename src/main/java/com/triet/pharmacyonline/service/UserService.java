package com.triet.pharmacyonline.service;

import com.triet.pharmacyonline.dto.UserDTO;
import com.triet.pharmacyonline.model.*;
import com.triet.pharmacyonline.utils.MySQLConnUtils;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserService implements IUserService {
    private static final String USERS_LIST_DTO = "SELECT * FROM vw_users_list_dto;";
    private static final String IS_USER_ID_EXISTED = "CALL sp_is_user_id_existed(?, ?)";
    private static final String IS_PHONE_NUMBER_EXISTED = "CALL sp_is_phone_number_existed(?, ?)";
    private static final String IS_EMAIL_EXISTED = "CALL sp_is_email_existed(?, ?)";
    private static final String IS_USERNAME_EXISTED = "CALL sp_is_username_existed(?, ?)";
    private static final String GET_USER_BY_ID = "CALL sp_find_user_by_id(?)";
    private static final String GET_USER_BY_ID_DTO = "CALL sp_find_user_by_id_dto(?)";
    private static final String ROLES_LIST = "SELECT r.id,r.code,r.role FROM roles AS r;";
    private static final String GENDERS_LIST = "SELECT g.id,g.name FROM genders AS g;";
    private static final String BLOCK_UNBLOCK_USER = "CALL sp_block_or_unblock_user(?, ?)";
    private static final String ADD_NEW_USER = "CALL sp_add_new_user(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<User> findAll() {
        return null;
    }

    public List<UserDTO> findAllDTO() {

        List<UserDTO> usersDTOList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getSqlConnection();
            PreparedStatement statement = connection.prepareStatement(USERS_LIST_DTO);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                usersDTOList.add(getUserDTO(rs));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return usersDTOList;
    }

    public UserDTO getUserDTO(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String fullName = rs.getString("fullName");
        String gender = rs.getString("gender");
        String phoneNumber = rs.getString("phoneNumber");
        String address = rs.getString("address");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String role = rs.getString("role");
        Date dateOfBirth = rs.getDate("dateOfBirth");
        LocalDateTime rawCreationDate = rs.getTimestamp("creationDate").toLocalDateTime();
        String creationDate = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' hh:mm:ss").format(rawCreationDate);

        boolean blocked = rs.getBoolean("blocked");

        return new UserDTO(id, fullName, gender, phoneNumber, email, address, dateOfBirth, creationDate, role, username,blocked);
    }

    public UserDTO findByIdDTO(long id) throws SQLException {
        UserDTO currentUserDTO = new UserDTO();
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(GET_USER_BY_ID_DTO);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            currentUserDTO = getUserDTO(rs);
        }
        return currentUserDTO;
    }


    @Override
    public User findById(long id) throws SQLException {
        User currentUser = new User();
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(GET_USER_BY_ID);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            currentUser = getUser(rs);
        }
        return currentUser;
    }

    private User getUser(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String password = rs.getString("password");
        String username = rs.getString("username");
        String fullName = rs.getString("fullName");
        String address = rs.getString("address");
        String phoneNumber = rs.getString("phoneNumber");
        String email = rs.getString("email");
        Date dateOfBirth = rs.getDate("dateOfBirth");
        String creationDate = rs.getString("creationDate");
        int role = rs.getInt("role");
        int gender = rs.getInt("gender");
        boolean blocked = rs.getBoolean("blocked");
        return new User(id, fullName, gender, phoneNumber, email, address, dateOfBirth, creationDate, role, blocked, username, password);

    }

    @Override
    public boolean save(User user) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(ADD_NEW_USER);
        return setParameters(statement, user);
    }

    private boolean setParameters(CallableStatement statement, User user) throws SQLException {
        statement.setLong(1, user.getId());
        statement.setString(2, user.getFullName());
        statement.setInt(3, user.getGender());
        statement.setString(4, user.getPhoneNumber());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getAddress());
        statement.setDate(7, new java.sql.Date(user.getDateOfBirth().getTime()));
        statement.setString(8, user.getUsername());
        statement.setString(9,user.getPassword());
        statement.executeQuery();
        return statement.getBoolean(10);
    }

    @Override
    public boolean update(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(long id) throws SQLException {
        return false;
    }

    public void blockOrUnblockUser(UserDTO currentUserDTO, long id, boolean block) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        PreparedStatement statement = connection.prepareStatement(BLOCK_UNBLOCK_USER);
        statement.setLong(1, id);
        statement.setBoolean(2, block);
        currentUserDTO.setBlocked(block);
        statement.execute();
    }

    @Override
    public boolean isIdExisted(long id) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_USER_ID_EXISTED);
        statement.setLong(1, id);
        statement.execute();
        return statement.getBoolean(2);
    }

    @Override
    public boolean isExisted(User user) throws SQLException {
        return false;
    }

    public boolean isEmailExisted(User user) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_EMAIL_EXISTED);
        statement.setString(1, user.getEmail());
        statement.execute();
        return statement.getBoolean(2);
    }

    public boolean isUsernameExisted(User user) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_USERNAME_EXISTED);
        statement.setString(1, user.getUsername());
        statement.execute();
        return statement.getBoolean(2);
    }

    public boolean isPhoneNumberExisted(User user) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_PHONE_NUMBER_EXISTED);
        statement.setString(1, user.getPhoneNumber());
        statement.execute();
        return statement.getBoolean(2);
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getSqlConnection();
            PreparedStatement statement = connection.prepareStatement(ROLES_LIST);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("code");
                String role = rs.getString("role");
                roles.add(new Role(id, name,role));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }

        return roles;
    }

    public List<Gender> getGenders() {
        List<Gender> genders = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getSqlConnection();
            PreparedStatement statement = connection.prepareStatement(GENDERS_LIST);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                genders.add(new Gender(id, name));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return genders;
    }


}
