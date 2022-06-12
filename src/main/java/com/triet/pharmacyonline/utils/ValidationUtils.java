package com.triet.pharmacyonline.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ValidationUtils {
    public static final String FULL_NAME_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";
    public static final String DRUG_NAME_REGEX = "^([a-zA-Z]+( [a-zA-Z]+)*)$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static String validProductionDate;
    public static String validExpirationDate;
    public static String minDateOfBirthValid;
    public static String maxDateOfBirthValid;

    public static boolean checkProductionDate(LocalDate inputDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = currentDate.minusYears(1);
        validProductionDate = minDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isAfter(minDate);
    }

    public static boolean checkExpirationDate(LocalDate inputDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate maxDate = currentDate.plusYears(3);
        validExpirationDate = maxDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isBefore(maxDate);
    }

    public static boolean checkDateOfBirth(Date inputDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minDateOfBirth = currentDate.minusYears(100);
        LocalDate maxDateOfBirth = currentDate.minusYears(10);
        minDateOfBirthValid = minDateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        maxDateOfBirthValid = maxDateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dateOfBirth = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return dateOfBirth.isAfter(minDateOfBirth) && dateOfBirth.isBefore(maxDateOfBirth);
    }

}
