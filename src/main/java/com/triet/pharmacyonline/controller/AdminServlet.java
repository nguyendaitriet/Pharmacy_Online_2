package com.triet.pharmacyonline.controller;

import com.triet.pharmacyonline.dto.UserDTO;
import com.triet.pharmacyonline.model.Drug;
import com.triet.pharmacyonline.model.User;
import com.triet.pharmacyonline.service.MedicineService;
import com.triet.pharmacyonline.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    private static final UserService userService = new UserService();
    private static final MedicineService medicineService = new MedicineService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDTO> userList = userService.findAllDTO();
        int activeUsers = 0;
        int blockedUsers = 0;
        int activeAdmins = 0;
        int blockedAdmins = 0;
        for (UserDTO user : userList) {
            if (!user.isBlocked() && user.getRole().equals("User")) activeUsers++;
            if (user.isBlocked() && user.getRole().equals("User")) blockedUsers++;
            if (!user.isBlocked() && user.getRole().equals("Admin")) activeAdmins++;
            if (user.isBlocked() && user.getRole().equals("User")) blockedAdmins++;
        }
        request.setAttribute("totalAccounts",userList.size());
        request.setAttribute("activeUsers",activeUsers);
        request.setAttribute("blockedUsers",blockedUsers);
        request.setAttribute("activeAdmins",activeAdmins);
        request.setAttribute("blockedAdmins",blockedAdmins);
        List<Drug> drugList = medicineService.findAll();
        request.setAttribute("totalDrugs",drugList.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
        dispatcher.forward(request, response);
    }


}
