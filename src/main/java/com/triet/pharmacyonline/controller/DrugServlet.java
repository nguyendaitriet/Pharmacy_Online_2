package com.triet.pharmacyonline.controller;

import com.triet.pharmacyonline.dto.DrugDTO;
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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(name = "DrugServlet", value = "/drugs")
public class DrugServlet extends HttpServlet {
    private static final MedicineService medicineService = new MedicineService();

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
                    showEditForm(request, response);
                    break;
                case "remove":
                    showRemoveForm(request, response);
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
                    editDrug(request, response);
                    break;
                case "remove":
                    removeDrug(request, response);
                    break;
                default:
                    showDrugsList(request, response);
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
        String path = "/admin/drug-management/add.jsp";
        addOrUpdateDrug(request, response, path, 1);
    }

    private void addOrUpdateDrug(HttpServletRequest request, HttpServletResponse response, String path, int action) throws ServletException, IOException, ParseException, SQLException {
        Drug inputDrug = new Drug();
        ArrayList<String> parsingErrors = getNewDrug(request, inputDrug);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Drug>> constraintViolations = validator.validate(inputDrug);

        String id = request.getParameter("id");
        if (ParsingValidationUtils.isLongParsing(id)) {
            long validId = Long.parseLong(id);
            if (medicineService.isIdExisted(validId)) {
                if (constraintViolations.isEmpty() && parsingErrors.isEmpty()) {
                    switch (action) {
                        //add new drug
                        case 1: {
                            if (!medicineService.isExisted(inputDrug)) {
                                if (medicineService.save(inputDrug)) {
                                    request.setAttribute("successfully", "Successful operation!");
                                    inputDrug = new Drug();
                                } else {
                                    request.setAttribute("failed", "Failed operation. Please contact to the manager!");
                                }
                            } else {
                                request.setAttribute("anotherError", "Drug existed! Please enter another Drug Name or Drug Content.");
                            }
                            break;
                        }
                        //update drug
                        case 2: {
                            if (medicineService.update(inputDrug)) {
                                request.setAttribute("successfully", "Successful operation!");
                            } else {
                                request.setAttribute("failed", "Failed operation. Please contact to the manager!");
                            }
                            break;
                        }
                    }
                }
            } else parsingErrors.add("Drug ID doesn't exist. Try again!");
        } else parsingErrors.add("Drug ID doesn't exist. Try again!");

        request.setAttribute("productionDate", inputDrug.getProductionDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        request.setAttribute("expirationDate", inputDrug.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        request.setAttribute("errors", constraintViolations);
        request.setAttribute("invalidInput", parsingErrors);
        request.setAttribute("newDrug", inputDrug);

        List<DosageForm> dosageFormList = medicineService.getDosageForms();
        request.setAttribute("dosageFormList", dosageFormList);

        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);

    }

    public ArrayList<String> getNewDrug(HttpServletRequest request, Drug drug) throws ParseException, NumberFormatException {
        ArrayList<String> parsingErrors = new ArrayList<>();

        String action = request.getParameter("action");

        if (action.equals("edit")) {
            String drugId = request.getParameter("id");

            if (ParsingValidationUtils.isIntParsing(drugId)) {
                drug.setId(Integer.parseInt(drugId));
            } else parsingErrors.add("Invalid ID!");
        }

        drug.setDrugName(request.getParameter("drugName").toLowerCase());

        String drugContent = request.getParameter("drugContent");

        if (ParsingValidationUtils.isDecimalParsing(drugContent)) {
            drug.setDrugContent(Double.parseDouble(drugContent));
        } else parsingErrors.add("Invalid value of Drug Content!");

        String quantity = request.getParameter("quantity");

        if (ParsingValidationUtils.isIntParsing(quantity)) {
            drug.setQuantity(Integer.parseInt(quantity));
        } else parsingErrors.add("Invalid value of Quantity!");

        String price = request.getParameter("price");

        if (ParsingValidationUtils.isLongParsing(price)) {
            drug.setPricePerPill(BigDecimal.valueOf(Long.parseLong(price)));
        } else parsingErrors.add("Invalid value of Price!");

        drug.setUsage(request.getParameter("usage").trim());
        drug.setNote(request.getParameter("note").trim());

        String dosageForm = request.getParameter("dosageForm");

        if (ParsingValidationUtils.isIntParsing(dosageForm)) {
            drug.setDosageForm(Integer.parseInt(dosageForm));
        } else parsingErrors.add("Invalid value of Dosage Form!");

        String productionDate = request.getParameter("productionDate");

        if (ParsingValidationUtils.isDateParsingType1(productionDate)) {
            drug.setProductionDate(new SimpleDateFormat("MM/dd/yyyy").parse(productionDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if (ValidationUtils.checkProductionDate(drug.getProductionDate())) {
                drug.setProductionDate(drug.getProductionDate());
            } else
                parsingErrors.add("Production Date must NOT be less than " + ValidationUtils.validProductionDate + ".");
        } else parsingErrors.add("Invalid value of Production Date!");

        String expirationDate = request.getParameter("expirationDate");

        if (ParsingValidationUtils.isDateParsingType1(expirationDate)) {
            drug.setExpirationDate(new SimpleDateFormat("MM/dd/yyyy").parse(expirationDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if (ValidationUtils.checkExpirationDate(drug.getExpirationDate())) {
                drug.setExpirationDate(drug.getExpirationDate());
            } else
                parsingErrors.add("Expiration Date must NOT be greater than " + ValidationUtils.validExpirationDate + ".");
        } else parsingErrors.add("Invalid value of Expiration Date!");

        return parsingErrors;
    }

    private void showRemoveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String path = "/admin/drug-management/remove.jsp";
        dispatchInvalidId(path, request, response);
    }

    private void dispatchInvalidId(String path, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        if (ParsingValidationUtils.isLongParsing(id)) {
            long validId = Long.parseLong(id);
            if (medicineService.isIdExisted(validId)) {
                Drug drug = medicineService.findById(validId);
                request.setAttribute("newDrug", drug);
                request.setAttribute("productionDate", drug.getProductionDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                request.setAttribute("expirationDate", drug.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                List<DosageForm> dosageFormList = medicineService.getDosageForms();
                request.setAttribute("dosageFormList", dosageFormList);
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
                return;
            }
        }
        request.setAttribute("invalidID", "Drug ID doesn't exist. Try again!");
        showDrugsList(request, response);
    }

    private void removeDrug(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        if (ParsingValidationUtils.isLongParsing(id)) {
            long validId = Long.parseLong(id);
            if (medicineService.isIdExisted(validId)) {
                if (medicineService.remove(validId)) {
                    request.setAttribute("successfully", "Successful operation!");
                } else {
                    request.setAttribute("failed", "Failed operation. Please contact to the manager!");
                }
                showDrugsList(request, response);
                return;
            }
        }
        showRemoveForm(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        String path = "/admin/drug-management/edit.jsp";
        dispatchInvalidId(path, request, response);
    }

    private void editDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException, ParseException {
        String path = "/admin/drug-management/edit.jsp";
        addOrUpdateDrug(request, response, path, 2);
    }
}