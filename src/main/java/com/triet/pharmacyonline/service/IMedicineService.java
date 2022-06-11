package com.triet.pharmacyonline.service;

import com.triet.pharmacyonline.model.Drug;
import java.sql.SQLException;
import java.util.List;

public interface IMedicineService extends IGeneralService<Drug>{
    @Override
    List<Drug> findAll();

    @Override
    Drug findById(long id) throws SQLException;

    @Override
    boolean save(Drug drug) throws SQLException;

    @Override
    boolean update(long id, Drug drug) throws SQLException;

    @Override
    boolean remove(long id) throws SQLException;

    boolean isExisted(Drug newDrug) throws SQLException;

    @Override
    boolean isIdExisted(long id) throws SQLException;
}
