package com.triet.pharmacyonline.service;

import com.triet.pharmacyonline.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService extends IGeneralService<User>{
    @Override
    List<User> findAll();

    @Override
    User findById(long id) throws SQLException;

    @Override
    boolean save(User user) throws SQLException;

    @Override
    boolean update(User user) throws SQLException;

    @Override
    boolean remove(long id) throws SQLException;

    @Override
    boolean isIdExisted(long id) throws SQLException;

    @Override
    boolean isExisted(User user) throws SQLException;
}
