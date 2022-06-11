package com.triet.pharmacyonline.exceptions;

import com.triet.pharmacyonline.utils.ParsingValidationUtils;
import com.triet.pharmacyonline.utils.ValidationUtils;

public class ProductionDateException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Production Date must NOT be less than " + ValidationUtils.validProductionDate + ".";
    }
}
