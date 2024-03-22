/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCustomer;

import com.team3.onlineshopping.dal.AddressDAO;
import com.team3.onlineshopping.dal.OrderDAO;
import com.team3.onlineshopping.dal.OrderDetailsDAO;
import com.team3.onlineshopping.model.Order;
import com.team3.onlineshopping.model.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author PC
 */
public class CusOrderDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CusOrderDetailsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CusOrderDetailsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String type = request.getParameter("type");

        OrderDAO or_dao = new OrderDAO();
        OrderDetailsDAO orde_dao = new OrderDetailsDAO();

        
        if (orderId != null) {
            Order or = or_dao.getById(Integer.parseInt(orderId));
            List<OrderDetails> orde = orde_dao.getByOrderId(or.getOrId());

            request.setAttribute("order", or);
            request.setAttribute("orderdetails", orde);
        }
        request.setAttribute("type", type);
        request.getRequestDispatcher("cu_myorderdetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
