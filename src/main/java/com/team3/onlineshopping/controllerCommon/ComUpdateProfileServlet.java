/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCommon;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.model.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ComUpdateProfileServlet extends HttpServlet {

    static AccountDAO acc_dao = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        AccountDAO acc_dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        request.setAttribute("account", acc);
        if (acc.getRoleId() == 4) {
            request.getRequestDispatcher("cu_editprofile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("c_editprofile.jsp").forward(request, response);
        }
//        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String phone = request.getParameter("phone").trim();
        String dob = request.getParameter("dob");

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        name = (name == null) ? "" : name;
        phone = (phone == null) ? "" : phone;
        dob = (dob == null) ? "" : dob;

        if (name.isEmpty()) {
            request.setAttribute("errorName", "Tên không được để trống");
        }

        // Validate phone 
        if (!checkFormatPhone(phone, acc)) {
            request.setAttribute("errPhone", "Số điện thoại phải gồm 10 chữ số và số 0 ở đầu.");
        }

        // Perform update
        if (checkFormatPhone(phone, acc) && !name.isEmpty()) {

            acc.setAccName(name);
            acc.setAccPhone(phone);
            acc.setAccDoB(dob);

            acc_dao.update(acc);
            request.setAttribute("status", "success");
//            request.setAttribute("acc", acc);
//            request.getRequestDispatcher("test.jsp").forward(request, response);
        }

        session.setAttribute("account", acc);
        doGet(request, response);
    }

    private boolean checkFormatPhone(String phone, Account acc) {
        // check format phone input
        if (!phone.trim().isEmpty() && phone.matches("^0[35789]{1}\\d{8}$")) {
            return true;
        }
        return false;
    }

}
