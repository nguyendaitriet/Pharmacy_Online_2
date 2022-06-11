package com.triet.pharmacyonline.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;


public class ParsingValidationUtils {
    public static boolean isLongParsing(String number) {
        try {
            Long.parseLong(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isIntParsing(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDecimalParsing(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDateParsing(String date) {
        try {
            new SimpleDateFormat("MM/dd/yyyy").parse(date);
            return true;
        } catch (final ParseException e) {
            return false;
        }
    }
}
