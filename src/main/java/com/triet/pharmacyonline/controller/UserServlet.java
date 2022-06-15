package com.triet.pharmacyonline.controller;

import com.triet.pharmacyonline.dto.UserDTO;
import com.triet.pharmacyonline.service.UserService;
import com.triet.pharmacyonline.utils.ParsingValidationUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name="UserServlet", value = "/users")
public class UserServlet  extends HttpServlet {

    private static final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "block":
                    blockOrUnblockUser(request, response, true);
                    break;
                case "unblock":
                    blockOrUnblockUser(request, response, false);
                    break;
                case "detail":
                    showUserDetail(request,response);
                    break;
                default:
                    showUsersList(request,response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void blockOrUnblockUser(HttpServletRequest request, HttpServletResponse response, boolean choice) throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        if (ParsingValidationUtils.isLongParsing(id)) {
            long validId = Long.parseLong(id);
            if (userService.isIdExisted(validId)) {
                UserDTO currentUserDTO = userService.findByIdDTO(validId);
                    if (currentUserDTO.isBlocked() == choice) {
                        request.setAttribute("error","This operation cannot be done!");
                    } else {
                        userService.blockOrUnblockUser(currentUserDTO, validId, choice);
                        request.setAttribute("successfully","Successful operation!");
                    }

                    request.setAttribute("user", currentUserDTO);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user-management/detail.jsp");
                    dispatcher.forward(request,response);
                    return;

            }
        }
        request.setAttribute("invalidID", "User ID doesn't exist. Try again!");
        showUsersList(request, response);
    }

    private void showUsersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userDTOList",userService.findAllDTO());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user-management/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showUserDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        if (ParsingValidationUtils.isLongParsing(id)) {
            long validId = Long.parseLong(id);
            if (userService.isIdExisted(validId)) {
                request.setAttribute("user", userService.findByIdDTO(validId));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user-management/detail.jsp");
                dispatcher.forward(request,response);
                return;
            }
        }
        request.setAttribute("invalidID", "User ID doesn't exist. Try again!");
        showUsersList(request, response);
    }

}
