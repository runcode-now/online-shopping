/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCustomer;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.information.GoogleUtils;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.GooglePojo;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author MinhBD
 */
public class CusLoginGoogleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String code = request.getParameter("code");

            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            AccountDAO dao = new AccountDAO();
            Account loginAccount = dao.getByEmailStatus(googlePojo.getEmail(), "on");
            LocalDate currentDate = LocalDate.now();
            // Định dạng ngày theo năm-ngày-tháng
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            CustomerDAO cus_dao = new CustomerDAO();
            if (loginAccount == null) {
                Account newAccount = new Account(0, googlePojo.getEmail(), "", "", googlePojo.getEmail(), "Image/Avatar/Avatar_Default.png", null, formattedDate, "on", 4);
                dao.add(newAccount);

                loginAccount = dao.getByEmailStatus(googlePojo.getEmail(), "on");
                Customer cus = new Customer(0, formattedDate, 0, dao.getByEmailStatus(googlePojo.getEmail(), "on").getAccId(), 4);
                cus_dao.add(cus);
            }
            HttpSession session = request.getSession();
            session.setAttribute("account", loginAccount);

            request.getRequestDispatcher("pub_home").forward(request, response);
            //response.sendRedirect("pub_home");

        } catch (Exception e) {
            log("Error at LoginGoogleHandler: " + e.toString());
            // Handle error scenario, redirect to an error page, etc.
            response.sendRedirect("c_login.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
