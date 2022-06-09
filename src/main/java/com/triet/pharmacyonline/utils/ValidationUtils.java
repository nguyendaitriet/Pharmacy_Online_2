package com.triet.pharmacyonline.utils;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationUtils {
    public static final String NAME_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static final String ADDRESS_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static final String DATE_STRING_REGEX = "(?<!\\d)(?:(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(?:(?:(?:0[13578]|1[02])31)|(?:(?:0[1,3-9]|1[0-2])(?:29|30)))|(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))0229)|(?:(?:1[6-9]|[2-9]\\d)?\\d{2})(?:(?:0?[1-9])|(?:1[0-2]))(?:0?[1-9]|1\\d|2[0-8]))(?!\\d)";

//    @AssertTrue(message = "Production date must NOT be less than a year from today!")
    public static boolean checkProductionDate(LocalDate inputDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus1Years = currentDate.minusYears(1);
        return inputDate.isAfter(currentDateMinus1Years);
    }

//    @AssertTrue(message = "Expiration date must NOT be greater than 3 years from today!")
    public static boolean checkExpirationDate(LocalDate inputDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlus5Years = currentDate.plusYears(3);
        return inputDate.isBefore(currentDatePlus5Years);
    }

    @Valid
    @AssertTrue(message = "Day entered is not valid!")
    public static boolean isDateValid(String dateStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        try {
            LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
