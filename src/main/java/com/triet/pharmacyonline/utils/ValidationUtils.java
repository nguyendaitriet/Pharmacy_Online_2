package com.triet.pharmacyonline.utils;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.SimpleFormatter;

public class ValidationUtils {
    public static final String NAME_REGEX = "^[A-Z]{1}([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";
    public static final String DRUG_NAME_REGEX = "^([a-zA-Z]+( [a-zA-Z]+)*)$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static final String ADDRESS_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static final String DATE_STRING_REGEX = "(?<!\\d)(?:(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(?:(?:(?:0[13578]|1[02])31)|(?:(?:0[1,3-9]|1[0-2])(?:29|30)))|(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))0229)|(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(?:(?:0?[1-9])|(?:1[0-2]))(?:0?[1-9]|1\\d|2[0-8]))(?!\\d)";
    public static String validProductionDate;
    public static String validExpirationDate;

    public static boolean checkProductionDate(LocalDate inputDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus1Years = currentDate.minusYears(1);
        validProductionDate = currentDateMinus1Years.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isAfter(currentDateMinus1Years);
    }

    public static boolean checkExpirationDate(LocalDate inputDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlus3Years = currentDate.plusYears(3);
        validExpirationDate = currentDatePlus3Years.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isBefore(currentDatePlus3Years);
    }

}
