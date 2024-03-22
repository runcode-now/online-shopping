/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerAdmin;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.AccountStatistic;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AdminDashboardServlet extends HttpServlet {

    AccountDAO acc_dao = new AccountDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminDashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashboardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AccountStatistic> accStatistic = new ArrayList<>();

        String view = request.getParameter("view");
        view = (view == null) ? "" : view;
        if (view.equals("month")) {
            accStatistic = acc_dao.getStatisticAccountByMonths();
        } else {
            accStatistic = acc_dao.getStatisticAccountBy7days();
        }

        List<AccountStatistic> roleStatistic = acc_dao.getStatisticRoleAccount();
        List<AccountStatistic> statusStatistic = acc_dao.getStatisticStatusAccount();
        List<Account> accTop5 = acc_dao.getTop5Account();

        request.setAttribute("accList", accStatistic);
        request.setAttribute("roleList", roleStatistic);
        request.setAttribute("statusList", statusStatistic);
        request.setAttribute("accTop5", accTop5);
        request.setAttribute("view", view);
        request.getRequestDispatcher("a_dashboard.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
