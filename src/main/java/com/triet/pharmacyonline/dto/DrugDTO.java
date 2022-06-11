package com.triet.pharmacyonline.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DrugDTO {
    private long id;
    private String drugName;
    private double drugContent;
    private int quantity;
    private String dosageForm;
    private String usage;
    private BigDecimal pricePerPill;
    private Date productionDate;
    private Date expirationDate;
    private String note;

    public DrugDTO() {
    }

    public DrugDTO(long id, String drugName, double drugContent, int quantity, String dosageForm, String usage, BigDecimal pricePerPill, Date productionDate, Date expirationDate, String note) {
        this.id = id;
        this.drugName = drugName;
        this.drugContent = drugContent;
        this.quantity = quantity;
        this.dosageForm = dosageForm;
        this.usage = usage;
        this.pricePerPill = pricePerPill;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.note = note;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDrugName() {
        String[] words = drugName.split(" ");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getDrugContent() {
        return drugContent;
    }

    public void setDrugContent(double drugContent) {
        this.drugContent = drugContent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerPill() {
        return pricePerPill;
    }

    public void setPricePerPill(BigDecimal pricePerPill) {
        this.pricePerPill = pricePerPill;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}

