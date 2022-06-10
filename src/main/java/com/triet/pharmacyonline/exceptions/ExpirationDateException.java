package com.triet.pharmacyonline.exceptions;

public class ExpirationDateException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expiration Date must NOT be greater than 5 years from today!";
    }
}
