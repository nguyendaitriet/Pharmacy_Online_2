package com.triet.pharmacyonline.service;

import com.triet.pharmacyonline.model.DosageForm;
import com.triet.pharmacyonline.model.Drug;
import com.triet.pharmacyonline.dto.DrugDTO;
import com.triet.pharmacyonline.utils.MySQLConnUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineService implements IMedicineService {
    private static final String DRUGS_LIST = "SELECT * FROM vw_drugs_list;";
    private static final String DRUGS_LIST_DTO = "SELECT * FROM vw_drugs_list_dto;";
    private static final String DOSAGE_FORMS_LIST = "SELECT " +
                                                        "ds.id," +
                                                        "ds.name " +
                                                    "FROM dosage_forms AS ds;";
    private static final String NEW_DRUG_ADD_SP = "CALL pharmacy_online.sp_add_new_drug(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_DRUG_BY_ID = "CALL pharmacy_online.sp_find_drug_by_id(?)";
    private static final String IS_DRUG_EXISTED = "CALL pharmacy_online.sp_is_drug_existed(?, ?, ?, ?, ?, ?, ?)";
    private static final String IS_DRUG_ID_EXISTED = "CALL pharmacy_online.sp_is_drug_id_existed(?, ?)";
    private static final String REMOVE_DRUG_EXISTED = "CALL pharmacy_online.sp_remove_drug(?, ?)";
    private static final String UPDATE_DRUG_EXISTED = "CALL pharmacy_online.sp_update_drug(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public List<DrugDTO> findAllDTO() {
        List<DrugDTO> drugsDTOList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getSqlConnection();
            PreparedStatement statement = connection.prepareStatement(DRUGS_LIST_DTO);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                drugsDTOList.add(getDrugDTO(rs));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return drugsDTOList;
    }

    public List<DosageForm> getDosageForms() {
        List<DosageForm> dosageForms = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getSqlConnection();
            PreparedStatement statement = connection.prepareStatement(DOSAGE_FORMS_LIST);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dosageForms.add(getDosageForm(rs));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return dosageForms;
    }

    private DosageForm getDosageForm(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        return new DosageForm(id, name);
    }

    public DrugDTO getDrugDTO(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String drugName = rs.getString("drugName");
        double drugContent = rs.getDouble("drugContent");
        int quantity = rs.getInt("quantity");
        String dosageForm = rs.getString("dosageForm");
        String usage = rs.getString("usage");
        BigDecimal pricePerPill = rs.getBigDecimal("pricePerPill");
        Date productionDate = rs.getDate("productionDate");
        Date expirationDate = rs.getDate("expirationDate");
        String note = rs.getString("note");
        return new DrugDTO(id, drugName, drugContent, quantity, dosageForm, usage,
                pricePerPill, productionDate, expirationDate, note);
    }

    @Override
    public List<Drug> findAll() {
        List<Drug> drugsList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getSqlConnection();
            PreparedStatement statement = connection.prepareStatement(DRUGS_LIST);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                drugsList.add(getDrug(rs));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return drugsList;
    }

    @Override
    public Drug findById(long id) throws SQLException {
        Drug currentDrug = null;
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(GET_DRUG_BY_ID);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            currentDrug = getDrug(rs);
        }
        return currentDrug;
    }

    public Drug getDrug(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String drugName = rs.getString("drugName");
        double drugContent = rs.getDouble("drugContent");
        int quantity = rs.getInt("quantity");
        int dosageForm = rs.getInt("dosageForm");
        String usage = rs.getString("usage");
        BigDecimal pricePerPill = rs.getBigDecimal("pricePerPill");
        LocalDate productionDate = rs.getDate("productionDate").toLocalDate();
        LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
        String note = rs.getString("note");
        return new Drug(id, drugName, drugContent, quantity, dosageForm, usage,
                pricePerPill, productionDate, expirationDate, note);
    }

    @Override
    public boolean save(Drug drug) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(NEW_DRUG_ADD_SP);
        return setParameters(statement, drug);
    }

    @Override
    public boolean update(long id, Drug drug) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(UPDATE_DRUG_EXISTED);
        return setParameters(statement, drug);
    }

    private boolean setParameters(CallableStatement statement, Drug drug) throws SQLException {
        statement.setString(1, drug.getDrugName());
        statement.setDouble(2, drug.getDrugContent());
        statement.setInt(3, drug.getQuantity());
        statement.setBigDecimal(4, drug.getPricePerPill());
        statement.setString(5, drug.getUsage());
        statement.setInt(6, drug.getDosageForm());
        statement.setDate(7, java.sql.Date.valueOf(drug.getProductionDate()));
        statement.setDate(8, java.sql.Date.valueOf(drug.getExpirationDate()));
        statement.setString(9,drug.getNote());
        statement.setLong(11,drug.getId());
        statement.executeQuery();
        return statement.getBoolean(10);
    }

    @Override
    public boolean remove(long id) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(REMOVE_DRUG_EXISTED);
        statement.setLong(1, id);
        statement.execute();
        return statement.getBoolean(2);
    }

    @Override
    public boolean isExisted(Drug newDrug) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_DRUG_EXISTED);
        statement.setString(1, newDrug.getDrugName());
        statement.setDouble(2, newDrug.getDrugContent());
        statement.setBigDecimal(3,newDrug.getPricePerPill());
        statement.setInt(4,newDrug.getDosageForm());
        statement.setDate(5,java.sql.Date.valueOf(newDrug.getProductionDate()));
        statement.setDate(6,java.sql.Date.valueOf(newDrug.getExpirationDate()));
        statement.execute();
        return statement.getBoolean(7);
    }

    @Override
    public boolean isIdExisted(long id) throws SQLException {
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall(IS_DRUG_ID_EXISTED);
        statement.setLong(1, id);
        statement.execute();
        return statement.getBoolean(2);
    }
}
