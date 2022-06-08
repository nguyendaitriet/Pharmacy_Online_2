package com.triet.pharmacyonline.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


public class Drug {
    private long id;
    private String drugName;
    private double drugContent;
    private int quantity;
    private int dosageForm;
    private String usage;
    private BigDecimal pricePerPill;
    private LocalDate productionDate;
    private LocalDate expirationDate;
    private String note;
    boolean deleted;

    public Drug() {
    }

    public Drug(long id, String drugName, double drugContent, int quantity, int dosageForm, String usage, BigDecimal pricePerPill, LocalDate productionDate, LocalDate expirationDate, String note) {
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

    public Drug(String drugName, double drugContent, int quantity, BigDecimal pricePerPill, int dosageForm, String usage, LocalDate productionDate, LocalDate expirationDate, String note) {
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

    public int getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(int dosageForm) {
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

    @NotEmpty
    @Length(min = 3, max = 10)
    public String getDrugName() {
        return drugName;
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

    @NotNull
    @Min(18)
    @Max(120)
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

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

