package com.triet.pharmacyonline.controller;

import com.triet.pharmacyonline.dto.DrugDTO;
import com.triet.pharmacyonline.exceptions.ExpirationDateException;
import com.triet.pharmacyonline.exceptions.ProductionDateException;
import com.triet.pharmacyonline.model.DosageForm;
import com.triet.pharmacyonline.model.Drug;
import com.triet.pharmacyonline.service.MedicineService;
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
//                    removeDrug(request,response);
                    break;
            }
        } catch (ParseException | SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void addDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String anotherError;
        Drug newDrug = new Drug();
        try {
            newDrug = getNewDrug(request, newDrug);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Drug>> constraintViolations = validator.validate(newDrug);

            if (constraintViolations.isEmpty()) {
                if (medicineService.save(newDrug)) {
                    request.setAttribute("successfully", "Add new drug successfully!");
                } else {
                    request.setAttribute("failed", "Failed to add new drug. Please contact to manager!");
                }
//                List<DosageForm> dosageFormList = medicineService.getDosageForms();
//                request.setAttribute("dosageFormList", dosageFormList);
//
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/add.jsp");
//                dispatcher.forward(request, response);
//                return;
            }
//            request.setAttribute("newDrug", newDrug);

            request.setAttribute("errors", constraintViolations);

//            List<DosageForm> dosageFormList = medicineService.getDosageForms();
//            request.setAttribute("dosageFormList", dosageFormList);
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/add.jsp");
//            dispatcher.forward(request, response);
        }
        catch (ParseException ex){
            anotherError = "Invalid Date";
            request.setAttribute("anotherError", anotherError);
        }
        catch (NumberFormatException ex)
        {
            anotherError = "Invalid Number";
            request.setAttribute("anotherError", anotherError);
        }
        catch (ProductionDateException | ExpirationDateException p) {
            anotherError = p.getMessage();

//            request.setAttribute("newDrug", newDrug);

            request.setAttribute("anotherError", anotherError);
        }
        finally {

            request.setAttribute("newDrug", newDrug);

            List<DosageForm> dosageFormList = medicineService.getDosageForms();
            request.setAttribute("dosageFormList", dosageFormList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/add.jsp");
            dispatcher.forward(request, response);
        }
    }

    public Drug getNewDrug(HttpServletRequest request, Drug drug) throws ParseException, NumberFormatException{
//        String drugName = request.getParameter("drugName");
//        double drugContent = Double.parseDouble(request.getParameter("drugContent"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        long price = Long.parseLong(request.getParameter("price").replace(",", ""));
//        BigDecimal pricePerPill = BigDecimal.valueOf(price);
//        String usage = request.getParameter("usage");
//        String note = request.getParameter("note");

//        String productionDateGot = request.getParameter("productionDate");
//        String expirationDateGot = request.getParameter("expirationDate");
//        int dosageForm = Integer.parseInt(request.getParameter("dosageForm"));
//        LocalDate productionDate = new SimpleDateFormat("MM/dd/yyyy").parse(productionDateGot).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate expirationDate = new SimpleDateFormat("MM/dd/yyyy").parse(expirationDateGot).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        if (!ValidationUtils.checkProductionDate(productionDate)) {
//            throw new ProductionDateException();
//        }
//        if (!ValidationUtils.checkExpirationDate(expirationDate)) {
//            throw new ExpirationDateException();
//        }
//        return new Drug(drugName, drugContent, quantity, pricePerPill, dosageForm, usage, productionDate, expirationDate, note);

        drug.setDrugName(request.getParameter("drugName"));
        drug.setDrugContent(Double.parseDouble(request.getParameter("drugContent")));
        drug.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        long price = Long.parseLong(request.getParameter("price").replace(",", ""));
        drug.setPricePerPill(BigDecimal.valueOf(price));
        drug.setUsage(request.getParameter("usage"));
        drug.setNote(request.getParameter("note"));

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
        drug.setDosageForm(Integer.parseInt(request.getParameter("dosageForm")));

        return drug;
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

    private void showRemoveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        long id = Long.parseLong(request.getParameter("id"));
        Drug drug = medicineService.findById(id);
        request.setAttribute("drug", drug);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/drug-management/list.jsp");
        dispatcher.forward(request, response);
    }
}
