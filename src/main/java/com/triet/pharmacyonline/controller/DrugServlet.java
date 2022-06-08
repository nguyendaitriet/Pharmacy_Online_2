package com.triet.pharmacyonline.controller;


import com.triet.pharmacyonline.dto.DrugDTO;
import com.triet.pharmacyonline.model.Drug;
import com.triet.pharmacyonline.service.MedicineService;

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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@WebServlet(name = "DrugServlet", value = "/drugs")
public class DrugServlet extends HttpServlet {
    MedicineService medicineService = new MedicineService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add1":
                    showAddForm1(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
//                showEditForm(request,response);
                    break;
                case "remove":
//                showRemoveForm(request,response);
                    break;
                default:
                    showDrugsList(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAddForm1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Drug drug = new Drug();
        request.setAttribute("drug", drug);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/drug-management/add1.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = req.getParameter("drug");
//        int quantity = Integer.parseInt(req.getParameter("quantity"));
//
//        try{
//
//        Drug drug = new Drug();
//        drug.setDrugName(name);
//        drug.setQuantity(quantity);
//
//
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Set<ConstraintViolation<Drug>> constraintViolations = validator.validate(drug);
//
//        if (!constraintViolations.isEmpty()) {
//            String errors = "<ul>";
//            for (ConstraintViolation<Drug> constraintViolation : constraintViolations) {
//                errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
//                        + "</li>";
//            }
//            errors += "</ul>";
//            req.setAttribute("drug", drug);
//            req.setAttribute("errors", errors);
//            req.getRequestDispatcher("/admin/drug-management/add1.jsp").forward(req, resp);
//        } else {
//            Drug drug1 = new Drug();
//            req.setAttribute("drug", drug1);
//            req.getRequestDispatcher("/admin/drug-management/add1.jsp").forward(req, resp);
//        }
//    } catch (Exception e) {
//        req.setAttribute("errors", e.getMessage());
//            req.getRequestDispatcher("/admin/drug-management/add1.jsp").forward(req, resp);
//    }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
//        try {
            switch (action) {
                case "add":
                    addDrug(request, response);
                    break;
                case "edit":
//                    editDrug(request, response);
                    break;
                case "remove":
//                    removeDrug(request,response);
                    break;
            }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }

    }

    private void addDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String drugName = request.getParameter("drugName");
        double drugContent = Double.parseDouble(request.getParameter("drugContent"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        long price = Long.parseLong(request.getParameter("price"));
        BigDecimal pricePerPill = BigDecimal.valueOf(price);
        int dosageForm = Integer.parseInt(request.getParameter("dosageForm"));
        String usage = request.getParameter("usage");
        LocalDate productionDate = LocalDate.parse(request.getParameter("productionDate"));
        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"));
        String note = request.getParameter("note");

        Drug newDrug = new Drug(drugName, drugContent, quantity, pricePerPill, dosageForm, usage, productionDate, expirationDate, note);
//        boolean customerSaved = customerService.save(newCustomer);
//        request.setAttribute("saveCustomer", customerSaved);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showDrugsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DrugDTO> drugDTOList = medicineService.findAllDTO();
        request.setAttribute("drugDTOList", drugDTOList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showRemoveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        long id = Long.parseLong(request.getParameter("id"));
        Drug drug = medicineService.findById(id);
        request.setAttribute("drug", drug);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/list.jsp");
        dispatcher.forward(request, response);
    }
}
