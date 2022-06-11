package com.triet.pharmacyonline.exceptions;

import com.triet.pharmacyonline.utils.ValidationUtils;

public class ExpirationDateException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expiration Date must NOT be greater than " + ValidationUtils.validExpirationDate + ".";
    }
}
