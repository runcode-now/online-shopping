/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.CartDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.ProductSizeDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Cart;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.ProductSize;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author PC
 */
public class PubCartServlet extends HttpServlet {

    ProductSizeDAO proSize_dao = new ProductSizeDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CusCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CusCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartDAO cart_dao = new CartDAO();
        CustomerDAO cus_dao = new CustomerDAO();
        HttpSession session = request.getSession();

        Account acc = (Account) session.getAttribute("account");
        String error = (String) session.getAttribute("error");

        if (acc != null) {
            Customer cus = cus_dao.getByAccountId(acc.getAccId());
            List<Cart> list = cart_dao.getByCusId(cus.getCusId());
            request.setAttribute("cartProduct", list);
            request.setAttribute("numberCart", list.size());

            if(error != null){
                request.setAttribute("errorQuantity", "errorQuantity");
            }
            
            request.getRequestDispatcher("p_cart.jsp").forward(request, response);
        } else {
            response.sendRedirect("c_login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] cart = request.getParameterValues("cartId");
        HttpSession session = request.getSession();

        CartDAO cd = new CartDAO();
        Cart c = new Cart();

        try {
            for (String carts : cart) {
                String increase = request.getParameter("increase");
                String decrease = request.getParameter("decrease");
                if (increase == null) {
                    increase = "0";
                } else {
                    String[] increaseParts = increase.split("_");
                    String cartIdInc_raw = increaseParts[1];

                    if (cartIdInc_raw.equals(carts)) {
                        int cartId = Integer.parseInt(cartIdInc_raw);
                        c = cd.getById(cartId);

                        ProductSize proSize = proSize_dao.getByProCateSizeId(c.getProId(), c.getCateSizeId());
                        System.out.println(proSize.getProSizeQuantity());
                        System.out.println(c.getCartQuantity());
                        if (proSize.getProSizeQuantity() > c.getCartQuantity()) {
                            if (increaseParts.length == 2) {
                                cd.increaseQuantity(c);
                            }
                        } else {
                            session.setAttribute("error", "max_quantity");
                        }
                    }
                }
                //----------------------------------------
                if (decrease == null) {
                    decrease = "0";
                } else {
                    String[] decreaseParts = decrease.split("_");
                    String cartIdDec_raw = decreaseParts[1];
                    if (cartIdDec_raw.equals(carts)) {
                        int cartId = Integer.parseInt(cartIdDec_raw);
                        c = cd.getById(cartId);
                        if (decreaseParts.length == 2) {
                            cd.decreaseQuantity(c);
                        }
                    }
                }
            }
            response.sendRedirect("pub_cart");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

}
