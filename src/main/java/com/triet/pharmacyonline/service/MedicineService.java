package com.triet.pharmacyonline.service;

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
    private static final String DRUGS_LIST = "SELECT * FROM vw_drugs_list AS dl;";

    public List<DrugDTO> findAllDTO() {
        List<DrugDTO> drugsDTOList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getSqlConnection();
            PreparedStatement statement = connection.prepareStatement(DRUGS_LIST);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                drugsDTOList.add(getDrugDTO(rs));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return drugsDTOList;
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
        return null;
    }

    @Override
    public Drug findById(long id) throws SQLException {
        Drug currentDrug = null;
        Connection connection = MySQLConnUtils.getSqlConnection();
        CallableStatement statement = connection.prepareCall("{CALL pharmacy_online.sp_find_drug_by_id(?)}");
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
        return false;
    }

    @Override
    public boolean update(Drug drug) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(long id) throws SQLException {
        return false;
    }
}
