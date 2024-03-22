package com.team3.onlineshopping.controllerAdmin;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.model.Account;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AdminUpdateUserServlet extends HttpServlet {

    AccountDAO acc_dao = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String choice = request.getParameter("choice");
        String accId = request.getParameter("accId");
        int id = Integer.parseInt(accId);
        Account acc = acc_dao.getById(id);

        if (choice.equals("view_detail")) {
            request.setAttribute("account", acc);
            request.getRequestDispatcher("a_userdetails.jsp").forward(request, response);
        } else if (choice.equals("update")) {
            String status = request.getParameter("status");
            String page = request.getParameter("page");

            acc.setAccStatus(status);
            acc_dao.update(acc);

            // check page ====== user details page
            if (page.equals("userdetails")) {
                request.setAttribute("account", acc);
                request.setAttribute("status", "success");
                request.getRequestDispatcher("a_userdetails.jsp").forward(request, response);
            } else {  /// user list page
                request.getRequestDispatcher("ad_userlist").forward(request, response);
            }
        } else {
            acc_dao.delete(id);
//            request.getRequestDispatcher("ad_userlist").forward(request, response);
            response.sendRedirect("ad_userlist");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
