package com.triet.pharmacyonline.utils;

public class ParsingValidationUtils {
    public static boolean isNumberParsing (String number) {
        try {
            Long.parseLong(number);
            return true;
        }catch (final NumberFormatException e) {
            return false;
        }
    }
}
