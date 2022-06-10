package com.triet.pharmacyonline.exceptions;

public class ProductionDateException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Production Date must NOT be less than a year from today!";
    }
}
