package com.triet.pharmacyonline.controller;

import com.triet.pharmacyonline.dto.DrugDTO;
import com.triet.pharmacyonline.exceptions.ExpirationDateException;
import com.triet.pharmacyonline.exceptions.ProductionDateException;
import com.triet.pharmacyonline.model.DosageForm;
import com.triet.pharmacyonline.model.Drug;
import com.triet.pharmacyonline.service.MedicineService;
import com.triet.pharmacyonline.utils.ParsingValidationUtils;
import com.triet.pharmacyonline.utils.ValidationUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                showEditForm(request,response);
                    break;
                case "remove":
                showRemoveForm(request,response);
                    break;
                default:
                    showDrugsList(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    addDrug(request, response);
                    break;
                case "edit":
//                    editDrug(request, response);
                    break;
                case "remove":
                    removeDrug(request,response);
                    break;
            }
        } catch (ParseException | SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void showDrugsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DrugDTO> drugDTOList = medicineService.findAllDTO();
        request.setAttribute("drugDTOList", drugDTOList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<DosageForm> dosageFormList = medicineService.getDosageForms();
        request.setAttribute("dosageFormList", dosageFormList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/add.jsp");
        dispatcher.forward(request, response);
    }



    private void addDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String anotherError;
        Drug inputDrug = new Drug();
        try {
            inputDrug = getNewDrug(request, inputDrug);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Drug>> constraintViolations = validator.validate(inputDrug);

            if (constraintViolations.isEmpty()) {
                if (!medicineService.isExisted(inputDrug)) {
                    if (medicineService.save(inputDrug)) {
                        request.setAttribute("successfully", "Successful operation!");
                        inputDrug = new Drug();
                    } else {
                        request.setAttribute("failed", "Failed operation. Please contact to the manager!");
                    }
                } else {
                    request.setAttribute("isDrugExist", "Drug existed! Please enter another Drug Name or Drug Content.");
                }
            }

            request.setAttribute("productionDate", inputDrug.getProductionDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            request.setAttribute("expirationDate", inputDrug.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            request.setAttribute("errors", constraintViolations);
        } catch (ParseException ex) {
            anotherError = "Invalid Date. Please enter with format mm/dd/yyyy";
            request.setAttribute("anotherError", anotherError);
        } catch (NumberFormatException ex) {
            anotherError = "Invalid Number";
            request.setAttribute("anotherError", anotherError);
        } catch (ProductionDateException | ExpirationDateException p) {
            request.setAttribute("productionDate", inputDrug.getProductionDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            request.setAttribute("expirationDate", inputDrug.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            anotherError = p.getMessage();
            request.setAttribute("anotherError", anotherError);
        } finally {
            request.setAttribute("newDrug", inputDrug);

            List<DosageForm> dosageFormList = medicineService.getDosageForms();
            request.setAttribute("dosageFormList", dosageFormList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/add.jsp");
            dispatcher.forward(request, response);
        }
    }

    public Drug getNewDrug(HttpServletRequest request, Drug drug) throws ParseException, NumberFormatException {
        drug.setDrugName(request.getParameter("drugName"));
        drug.setDrugContent(Double.parseDouble(request.getParameter("drugContent")));
        drug.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        long price = Long.parseLong(request.getParameter("price").replace(",", ""));
        drug.setPricePerPill(BigDecimal.valueOf(price));
        drug.setUsage(request.getParameter("usage"));
        drug.setNote(request.getParameter("note"));
        drug.setDosageForm(Integer.parseInt(request.getParameter("dosageForm")));

        String productionDateGot = request.getParameter("productionDate");
        String expirationDateGot = request.getParameter("expirationDate");
        LocalDate productionDate = new SimpleDateFormat("MM/dd/yyyy").parse(productionDateGot).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate expirationDate = new SimpleDateFormat("MM/dd/yyyy").parse(expirationDateGot).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        drug.setProductionDate(productionDate);
        drug.setExpirationDate(expirationDate);
        if (!ValidationUtils.checkProductionDate(productionDate)) {
            throw new ProductionDateException();
        }
        if (!ValidationUtils.checkExpirationDate(expirationDate)) {
            throw new ExpirationDateException();
        }

        return drug;
    }

    private void showRemoveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String path = "/admin/drug-management/remove.jsp";
        dispatchInvalidId(path, request,response);
    }

    private void dispatchInvalidId (String path, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        if (ParsingValidationUtils.isNumberParsing(id)) {
            long validId = Long.parseLong(id);
            if (medicineService.isIdExisted(validId)) {
                Drug drug = medicineService.findById(validId);
                request.setAttribute("drug", drug);
                request.setAttribute("productionDate", drug.getProductionDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                request.setAttribute("expirationDate", drug.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                List<DosageForm> dosageFormList = medicineService.getDosageForms();
                request.setAttribute("dosageFormList", dosageFormList);
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
                return;
            }
        }
        request.setAttribute("invalidID","Drug ID doesn't exist. Try again!");
        showDrugsList(request, response);
    }

    private void removeDrug(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        if (ParsingValidationUtils.isNumberParsing(id)) {
            long validId = Long.parseLong(id);
            if (medicineService.isIdExisted(validId)) {
                if (medicineService.remove(validId)) {
                    request.setAttribute("successfully", "Successful operation!");
                } else {
                    request.setAttribute("failed", "Failed operation. Please contact to the manager!");
                }
                showDrugsList(request,response);
                return;
            }
        }
        showRemoveForm(request,response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        String path = "/admin/drug-management/edit.jsp";
        dispatchInvalidId(path, request,response);
    }
}
