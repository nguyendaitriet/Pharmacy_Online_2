package com.triet.pharmacyonline.controller;

import com.triet.pharmacyonline.model.Gender;
import com.triet.pharmacyonline.model.User;
import com.triet.pharmacyonline.service.UserService;
import com.triet.pharmacyonline.utils.ParsingValidationUtils;
import com.triet.pharmacyonline.utils.ValidationUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private static final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Gender> gendersList = userService.getGenders();
        request.setAttribute("genders", gendersList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register/form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User inputUser = new User();
        ArrayList<String> parsingErrors;
        try {
            parsingErrors = getNewUser(request, inputUser);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(inputUser);

            if (constraintViolations.isEmpty() && parsingErrors.isEmpty()) {
                if (userService.isEmailExisted(inputUser)) {
                    parsingErrors.add("Email existed. Try another email address.");
                }
                if (userService.isPhoneNumberExisted(inputUser)) {
                    parsingErrors.add("Phone number existed. Try another phone number.");
                }
                if (userService.isUsernameExisted(inputUser)) {
                    parsingErrors.add("Username existed. Try another username.");
                }
                if (!userService.isEmailExisted(inputUser) && !userService.isPhoneNumberExisted(inputUser) && !userService.isUsernameExisted(inputUser))
                    if (userService.save(inputUser)) {
                        request.setAttribute("successfully", "Successful operation!");
                    } else {
                        request.setAttribute("failed", "Failed operation. Please contact to the manager!");
                    }
            }

            request.setAttribute("errors", constraintViolations);
            request.setAttribute("invalidInput", parsingErrors);
            request.setAttribute("user", inputUser);
            request.setAttribute("dateOfBirth", new SimpleDateFormat("yyyy-MM-dd").format(inputUser.getDateOfBirth()));
            List<Gender> gendersList = userService.getGenders();
            request.setAttribute("genders", gendersList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/register/form.jsp");
            dispatcher.forward(request, response);
        } catch (ParseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getNewUser(HttpServletRequest request, User user) throws ParseException, NumberFormatException {
        ArrayList<String> parsingErrors = new ArrayList<>();

        user.setFullName(request.getParameter("fullName").trim());

        String gender = request.getParameter("gender");
        if (ParsingValidationUtils.isIntParsing(gender)) {
            user.setGender(Integer.parseInt(gender));
        } else parsingErrors.add("Invalid value of Gender!");

        user.setAddress(request.getParameter("address").trim());
        user.setEmail(request.getParameter("email").trim());
        user.setPhoneNumber(request.getParameter("phoneNumber").trim());
        user.setUsername(request.getParameter("username").trim());
        user.setPassword(request.getParameter("password").trim());

        String dateOfBirth = request.getParameter("dateOfBirth");
        if (ParsingValidationUtils.isDateParsingType2(dateOfBirth)) {
            user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth));
            if (ValidationUtils.checkDateOfBirth(user.getDateOfBirth())) {
                user.setDateOfBirth(user.getDateOfBirth());
            } else
                parsingErrors.add("Date of Birth must NOT be less than " + ValidationUtils.minDateOfBirthValid +
                        " or greater than " + ValidationUtils.maxDateOfBirthValid + ".");
        } else parsingErrors.add("Invalid value of Date!");

        return parsingErrors;
    }
}
