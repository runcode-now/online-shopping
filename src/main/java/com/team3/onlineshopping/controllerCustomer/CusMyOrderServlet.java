/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCustomer;

import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.OrderDAO;
import com.team3.onlineshopping.dal.OrderDetailsDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CusMyOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CusMyOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CusMyOrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerDAO cus_dao = new CustomerDAO();
        OrderDetailsDAO orde_dao = new OrderDetailsDAO();
        OrderDAO or_dao = new OrderDAO();

        String type = request.getParameter("type");

        System.out.println("Vao day 1");

        Account acc = (Account) session.getAttribute("account");
        Customer cus = cus_dao.getByAccountId(acc.getAccId());
        List<Order> order = new ArrayList<>();

        System.out.println("Vao day 2");

        if (type == null || type.equalsIgnoreCase("all")) {
            order = (or_dao.getByCusId(cus.getCusId()));
            type = "all";
        } else if (type.equalsIgnoreCase("delivered")) {
            order = (or_dao.getByCusId(cus.getCusId(), "delivered"));
        } else if (type.equalsIgnoreCase("delivering")) {
            order = (or_dao.getByCusId(cus.getCusId(), "delivering"));
        } else if (type.equalsIgnoreCase("cancelled")) {
            order = (or_dao.getByCusId(cus.getCusId(), "cancelled"));
        } else if (type.equalsIgnoreCase("pending")) {
            order = (or_dao.getByCusId(cus.getCusId(), "pending"));
        }

        request.setAttribute("type", type);
        request.setAttribute("orders", order);
        request.getRequestDispatcher("cu_myorder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
