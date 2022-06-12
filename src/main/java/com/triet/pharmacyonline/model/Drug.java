package com.triet.pharmacyonline.model;

import com.triet.pharmacyonline.utils.ValidationUtils;
import org.hibernate.validator.constraints.Range;


import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

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

    public Drug() throws ParseException {
        drugName = "";
        usage = "";
        productionDate = new SimpleDateFormat("MM/dd/yyyy").parse("00/00/0000").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        expirationDate = new SimpleDateFormat("MM/dd/yyyy").parse("00/00/0000").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        note = "";
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

    @NotNull(message = "Dosage form must NOT be null.")
    @Range(min = 1, max = 5, message = "Dosage form must be from 1 to 5.")
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

    @NotEmpty(message = "Drug name must NOT be empty.")
    @Pattern(regexp = ValidationUtils.DRUG_NAME_REGEX,
            message = "Drug Name must NOT contain DIGIT, SPECIAL CHARACTER or redundant WHITESPACE.")
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    @NotNull(message = "Drug content must NOT be null.")
    @Min(value = 0, message = "Drug content must be at least 0.")
    @Max(value = 1000000, message = "Drug content must not be higher than 1000000.")
    public double getDrugContent() {
        return drugContent;
    }

    public void setDrugContent(double drugContent) {
        this.drugContent = drugContent;
    }

    @NotNull(message = "Quantity must NOT be null.")
    @Min(value = 0, message = "Quantity must be at least 0.")
    @Max(value = 5000000, message = "Quantity must not be higher than 5000000.")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @NotNull(message = "Price must NOT be null.")
    @Min(value = 0, message = "Quantity must be at least 0.")
    @Max(value = 100000000, message = "Quantity must not be higher than 100000000.")
    public BigDecimal getPricePerPill() {
        return pricePerPill;
    }

    public void setPricePerPill(BigDecimal pricePerPill) {
        this.pricePerPill = pricePerPill;
    }

    @NotNull(message = "Production date must NOT be null.")
    @PastOrPresent(message = "Production date must be today or in the past.")
    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    @NotNull(message = "Expiration date must NOT be null.")
    @FutureOrPresent(message = "Expiration date must be in the future.")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Double.compare(drug.drugContent, drugContent) == 0 && Objects.equals(drugName, drug.drugName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugName, drugContent);
    }
}

