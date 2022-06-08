package com.triet.pharmacyonline.service;

import java.sql.SQLException;
import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();

    T findById(long id) throws SQLException;

    boolean save(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean remove(long id) throws SQLException;
}
