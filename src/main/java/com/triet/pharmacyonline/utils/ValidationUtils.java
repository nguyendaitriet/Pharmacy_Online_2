package com.triet.pharmacyonline.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ValidationUtils {
    public static final String FULL_NAME_REGEX = "[A-Za-z\\s]+";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static String validProductionDate;
    public static String validExpirationDate;
    public static String minDateOfBirthValid;
    public static String maxDateOfBirthValid;

    public static boolean checkProductionDate(LocalDate inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRange = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String minDateString = "01/01/" + (currentYear - validRange);
        LocalDate minDate = LocalDate.parse(minDateString, formatter);
        validProductionDate = minDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isAfter(minDate) || inputDate.isEqual(minDate);
    }

    public static boolean checkExpirationDate(LocalDate inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRange = 3;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String maxDateString = "31/12/" + (currentYear + validRange);
        LocalDate maxDate = LocalDate.parse(maxDateString, formatter);
        validExpirationDate = maxDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isBefore(maxDate) || inputDate.isEqual(maxDate);
    }

    public static boolean checkDateOfBirth(Date inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRangeMin = 90;
        int validRangeMax = 10;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String maxDateString = "31/12/" + (currentYear - validRangeMax);
        String minDateString = "01/01/" + (currentYear - validRangeMin);

        LocalDate minDateOfBirth = LocalDate.parse(minDateString,formatter);
        LocalDate maxDateOfBirth = LocalDate.parse(maxDateString,formatter);

        minDateOfBirthValid = minDateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        maxDateOfBirthValid = maxDateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dateOfBirth = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (dateOfBirth.isAfter(minDateOfBirth) || dateOfBirth.isEqual(minDateOfBirth)) &&
                (dateOfBirth.isBefore(maxDateOfBirth) ||dateOfBirth.isEqual(maxDateOfBirth));
    }

}
