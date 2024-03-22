/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCustomer;

import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.FeedbackDAO;
import com.team3.onlineshopping.dal.OrderDetailsDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.Feedback;
import com.team3.onlineshopping.model.OrderDetails;
import com.team3.onlineshopping.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class CusFeedbackServlet extends HttpServlet {

    private static final OrderDetailsDAO orde_dao = new OrderDetailsDAO();
    private static final FeedbackDAO fee_dao = new FeedbackDAO();
    private static final CustomerDAO cus_dao = new CustomerDAO();
    private static final ProductDAO pro_dao = new ProductDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CusFeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CusFeedbackServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc != null && acc.getRoleId() == 4) {
            int proId = Integer.parseInt(request.getParameter("proId"));
            int orderId = Integer.parseInt(request.getParameter("order"));
            String type = request.getParameter("type");

            Feedback f = fee_dao.getByProCusOrId(proId, cus_dao.getByAccountId(acc.getAccId()).getCusId(), orderId);
            if (f != null) {
                response.sendRedirect("cus_myorder");
            } else {
                Product pro = pro_dao.getById(proId);
                OrderDetails orde = orde_dao.getByProCateSizeId(proId, orderId);

                request.setAttribute("pro", pro);
                request.setAttribute("orde", orde);
                request.setAttribute("type", type);
                request.getRequestDispatcher("cu_feedback.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("com_login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc != null) {
            String feedContent = request.getParameter("feedContent");
            String star = request.getParameter("rate");
            int proId = Integer.parseInt(request.getParameter("proId"));
            int orderId = Integer.parseInt(request.getParameter("order"));
            String type = request.getParameter("type");

            Product pro = pro_dao.getById(proId);
            OrderDetails orde = orde_dao.getByProCateSizeId(proId, orderId);
            request.setAttribute("pro", pro);
            request.setAttribute("orde", orde);
            request.setAttribute("type", type);

            if (feedContent == null || feedContent.trim().isEmpty()
                    || star == null || star.trim().isEmpty()) {
                request.setAttribute("error", "Vui lòng chọn số sao và viết đánh giá.");
                request.getRequestDispatcher("cu_feedback.jsp").forward(request, response);
            } else {
                Customer cus = cus_dao.getByAccountId(acc.getAccId());
                int stars = Integer.parseInt(star);
                Feedback feed = new Feedback(0, feedContent, "", stars, proId, cus.getCusId(), orderId);
                fee_dao.add(feed);

                double avgPro = pro_dao.CalculateAVGRating(proId);
                System.out.println(avgPro);
                pro.setProRating(avgPro);
                pro_dao.update(pro);

                response.sendRedirect("cus_orderdetails?orderId=" + orderId + "&type=" + type);
            }
        } else {
            response.sendRedirect("com_login");
        }
    }

}
