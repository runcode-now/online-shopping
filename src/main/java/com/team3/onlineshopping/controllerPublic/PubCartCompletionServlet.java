/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.CartDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.OrderDAO;
import com.team3.onlineshopping.dal.OrderDetailsDAO;
import com.team3.onlineshopping.dal.PaymentHistoryDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.dal.ProductSizeDAO;
import com.team3.onlineshopping.information.Email;
import com.team3.onlineshopping.information.HtmlContent;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.Order;
import com.team3.onlineshopping.model.OrderDetails;
import com.team3.onlineshopping.model.PaymentHistory;
import com.team3.onlineshopping.model.Product;
import com.team3.onlineshopping.model.ProductSize;
import java.io.IOException;
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
public class PubCartCompletionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderDAO or_dao = new OrderDAO();
        OrderDetailsDAO orde_dao = new OrderDetailsDAO();
        ProductDAO pro_dao = new ProductDAO();
        CartDAO cart_dao = new CartDAO();
        ProductSizeDAO proSize_dao = new ProductSizeDAO();
        PaymentHistoryDAO pay_dao = new PaymentHistoryDAO();
        CustomerDAO cus_dao = new CustomerDAO();
        AccountDAO acc_dao = new AccountDAO();

        Order order = (Order) session.getAttribute("order");
        List<Integer> quantityList = (List<Integer>) session.getAttribute("orderQuantity");
        List<Integer> sizeList = (List<Integer>) session.getAttribute("orderSize");
        List<ProductSize> productSizeList = (List<ProductSize>) session.getAttribute("proSize");
        List<Product> productList = (List<Product>) session.getAttribute("product");
        List<Integer> cartList = (List<Integer>) session.getAttribute("cart");
        String payment = (String) session.getAttribute("payment");
        double total = (double) session.getAttribute("total");

        if (order != null) {
            or_dao.add(order);
        }

        if (productSizeList != null) {
            for (int i = 0; i < productSizeList.size(); i++) {
                ProductSize proSize = productSizeList.get(i);
                proSize_dao.update(proSize);
            }
        }
        if (productList != null && sizeList != null) {
            for (int i = 0; i < productList.size(); i++) {
                Product pro = productList.get(i);
                int quantity = quantityList.get(i);
                int size = sizeList.get(i);
                pro_dao.update(pro);

                OrderDetails orDet = new OrderDetails(pro.getProId(), or_dao.getOrderLatest().getOrId(), quantity, size);
                orde_dao.add(orDet);
            }
        }
        if (cartList != null) {
            for (int i = 0; i < cartList.size(); i++) {
                Integer cart = cartList.get(i);
                cart_dao.delete(cart);
            }
        }

        if (payment.equalsIgnoreCase("VNPAY")) {
            PaymentHistory pay = new PaymentHistory(0, order.getOrDate(), payment, or_dao.getOrderLatest().getOrId(), order.getCusId());
            pay_dao.add(pay);
            Customer cus = cus_dao.getById(order.getCusId());
            cus.setCusTotalPurchase(cus.getCusTotalPurchase() + total);
            cus_dao.update(cus);
        } else {
            payment = "";
        }

        List<OrderDetails> orde = orde_dao.getByOrderId(or_dao.getOrderLatest().getOrId());

        //send email
        Customer cus = cus_dao.getById(order.getCusId());
        Account acc = acc_dao.getAccByCusId(cus.getCusId());
        String content = HtmlContent.orderNotification("pending", "");
        Email.sendEmail(acc.getAccEmail(), acc.getAccId(), 3, "Cảm ơn bạn đã đặt hàng", content);

        request.setAttribute("Order", or_dao.getOrderLatest());
        request.setAttribute("orderDetails", orde);
        request.setAttribute("paymentMethod", payment);

//        request.getRequestDispatcher("test.jsp").forward(request, response);
        request.getRequestDispatcher("p_cartcompletion.jsp").forward(request, response);

    }

}
