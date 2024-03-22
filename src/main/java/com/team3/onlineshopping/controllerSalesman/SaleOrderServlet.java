/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerSalesman;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.EmployeeDAO;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author PC
 */
public class SaleOrderServlet extends HttpServlet {

    OrderDAO or_dao = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null || type.isEmpty()) {
            doGetAll(request, response);
        } else if (type.equalsIgnoreCase("delivered")) {
            doGetDelivered(request, response);
        } else if (type.equalsIgnoreCase("cancelled")) {
            doGetCancelled(request, response);
        } else if (type.equalsIgnoreCase("pending")) {
            doGetPending(request, response);
        } else if (type.equalsIgnoreCase("delivering")) {
            doGetDelivering(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String status = request.getParameter("status");
        Account account = (Account) session.getAttribute("account");

        CustomerDAO cus_dao = new CustomerDAO();
        AccountDAO acc_dao = new AccountDAO();
        OrderDetailsDAO orde_dao = new OrderDetailsDAO();
        ProductDAO pro_dao = new ProductDAO();
        ProductSizeDAO prosize_dao = new ProductSizeDAO();
        EmployeeDAO emp_dao = new EmployeeDAO();
        PaymentHistoryDAO pay_dao = new PaymentHistoryDAO();
        ProductSize proSize;
        Product pro;

        or_dao.changeStatus(orderId, status);

        Customer cus = cus_dao.getById(or_dao.getById(orderId).getCusId());
        Account acc = acc_dao.getAccByCusId(cus.getCusId());
        Order or = or_dao.getById(orderId);
        if (or.getEmId() == 1) {
            or.setEmId(emp_dao.getByAccId(account.getAccId()).getEmId());
            or_dao.update(or);
        }

        if (status.equalsIgnoreCase("delivered")) {

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDateTime = currentTime.format(formatter);

            PaymentHistory payment = new PaymentHistory(0, formattedDateTime, "direct", orderId, or_dao.getById(orderId).getCusId());
            pay_dao.add(payment);
            cus.setCusTotalPurchase(cus.getCusTotalPurchase() + or_dao.getById(orderId).getOrTotalPrice() + 30000.0);
            cus_dao.update(cus);

            //send email
            String content = HtmlContent.orderNotification("delivered", "");
            Email.sendEmail(acc.getAccEmail(), acc.getAccId(), 3, "Đơn hàng đã được giao thành công", content);
        } else if (status.equalsIgnoreCase("cancelled")) {
            List<OrderDetails> orde = orde_dao.getByOrderId(orderId);
            for (OrderDetails orderDetails : orde) {
                //set quantity of product size quantity
                proSize = prosize_dao.getByProCateSizeId(orderDetails.getProId(), orderDetails.getCateSizeId());
                proSize.setProSizeQuantity(proSize.getProSizeQuantity() + orderDetails.getOrDeQuantity());
                //set quantity of product sold
                pro = pro_dao.getById(orderDetails.getProId());
                pro.setProSold(pro.getProSold() - orderDetails.getOrDeQuantity());
                //update product and product size
                prosize_dao.update(proSize);
                pro_dao.update(pro);
            }

            PaymentHistory payHis = pay_dao.getByOrderId(orderId);
            String content = HtmlContent.orderNotification("cancelled", payHis.getPayMethod());
            Email.sendEmail(acc.getAccEmail(), acc.getAccId(), 3, "Đơn hàng của bạn đã được hủy", content);
        }

//        response.sendRedirect("sale_order");
    }

    protected void doGetAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String index = request.getParameter("index");
        String status = request.getParameter("status");
        String tilte = request.getParameter("tilteOrder");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String statusOrder = request.getParameter("statusOrder");
        String beginPrice = request.getParameter("beginPrice");
        String endPrice = request.getParameter("endPrice");
        int indexPage = index != null ? Integer.parseInt(index) : 1;

        //-------validate -------
        beginPrice = (beginPrice != null && !beginPrice.isEmpty()) ? beginPrice : null;
        endPrice = (endPrice != null && !endPrice.isEmpty()) ? endPrice : null;
        beginDate = (beginDate != null && !beginDate.isEmpty()) ? beginDate : null;
        endDate = (endDate != null && !endDate.isEmpty()) ? endDate : null;
        statusOrder = (statusOrder != null && !statusOrder.isEmpty()) ? statusOrder : null;
        tilte = (tilte != null && !tilte.isEmpty()) ? tilte : null;
        int startIndex = (indexPage - 1) * 10;
        //---------------------------
        if (tilte != null || beginDate != null || endDate != null || statusOrder != null || beginPrice != null || endPrice != null) {

            int countPage = or_dao.getTotalOrder(tilte, statusOrder, beginDate, endDate, beginPrice, endPrice);
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);
            List<Order> list = or_dao.FilterOrder(tilte, statusOrder, beginDate, endDate, beginPrice, endPrice, indexPage);

            request.setAttribute("orderList", list);
            request.setAttribute("tilteOrder", tilte);
            request.setAttribute("beginDate", beginDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("statusOrder", statusOrder);
            request.setAttribute("beginPrice", beginPrice);
            request.setAttribute("endPrice", endPrice);
            request.setAttribute("endPage", endPage);
            request.setAttribute("filter", "FilterOn");
        } else {
            int countPage = or_dao.getTotalOrder();
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);
            List<Order> order = or_dao.getAll(indexPage);

            request.setAttribute("orderList", order);
            request.setAttribute("endPage", endPage);
            request.setAttribute("status", status);
        }
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("pageNumber", indexPage);
        request.getRequestDispatcher("s_order.jsp").forward(request, response);
    }

    protected void doGetDelivered(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String index = request.getParameter("index");
        String tilte = request.getParameter("tilteOrder");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String beginPrice = request.getParameter("beginPrice");
        String endPrice = request.getParameter("endPrice");
        int indexPage = index != null ? Integer.parseInt(index) : 1;

        //-------validate -------
        beginPrice = (beginPrice != null && !beginPrice.isEmpty()) ? beginPrice : null;
        endPrice = (endPrice != null && !endPrice.isEmpty()) ? endPrice : null;
        beginDate = (beginDate != null && !beginDate.isEmpty()) ? beginDate : null;
        endDate = (endDate != null && !endDate.isEmpty()) ? endDate : null;
        tilte = (tilte != null && !tilte.isEmpty()) ? tilte : null;
        //---------------------------
        int startIndex = (indexPage - 1) * 10;

        if (tilte != null || beginDate != null || endDate != null || beginPrice != null || endPrice != null) {
            int countPage = or_dao.getTotalOrder(tilte, "delivered", beginDate, endDate, beginPrice, endPrice);
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);
            List<Order> list = or_dao.FilterOrder(tilte, "delivered", beginDate, endDate, beginPrice, endPrice, indexPage);

            request.setAttribute("orderList", list);
            request.setAttribute("tilteOrder", tilte);
            request.setAttribute("beginDate", beginDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("beginPrice", beginPrice);
            request.setAttribute("endPrice", endPrice);
            request.setAttribute("endPage", endPage);
            request.setAttribute("filter", "FilterOn");
        } else {
            int countPage = or_dao.getTotalOrder("delivered");
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);

            List<Order> order = or_dao.getAll("delivered", indexPage);

            request.setAttribute("orderList", order);
            request.setAttribute("endPage", endPage);
        }
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("pageNumber", indexPage);
        request.getRequestDispatcher("s_orderdelivered.jsp").forward(request, response);
    }

    protected void doGetCancelled(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String index = request.getParameter("index");
        String tilte = request.getParameter("tilteOrder");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String beginPrice = request.getParameter("beginPrice");
        String endPrice = request.getParameter("endPrice");
        int indexPage = index != null ? Integer.parseInt(index) : 1;

        //-------validate -------
        beginPrice = (beginPrice != null && !beginPrice.isEmpty()) ? beginPrice : null;
        endPrice = (endPrice != null && !endPrice.isEmpty()) ? endPrice : null;
        beginDate = (beginDate != null && !beginDate.isEmpty()) ? beginDate : null;
        endDate = (endDate != null && !endDate.isEmpty()) ? endDate : null;
        tilte = (tilte != null && !tilte.isEmpty()) ? tilte : null;
        //---------------------------
        int startIndex = (indexPage - 1) * 10;

        if (tilte != null || beginDate != null || endDate != null || beginPrice != null || endPrice != null) {
            int countPage = or_dao.getTotalOrder(tilte, "cancelled", beginDate, endDate, beginPrice, endPrice);
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);
            List<Order> list = or_dao.FilterOrder(tilte, "cancelled", beginDate, endDate, beginPrice, endPrice, indexPage);

            request.setAttribute("orderList", list);
            request.setAttribute("tilteOrder", tilte);
            request.setAttribute("beginDate", beginDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("beginPrice", beginPrice);
            request.setAttribute("endPrice", endPrice);
            request.setAttribute("endPage", endPage);
            request.setAttribute("filter", "FilterOn");
        } else {
            int countPage = or_dao.getTotalOrder("cancelled");
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);

            List<Order> order = or_dao.getAll("cancelled", indexPage);

            request.setAttribute("orderList", order);
            request.setAttribute("endPage", endPage);
        }
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("pageNumber", indexPage);
        request.getRequestDispatcher("s_orderdelivered.jsp").forward(request, response);
    }

    protected void doGetPending(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String index = request.getParameter("index");
        String tilte = request.getParameter("tilteOrder");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String beginPrice = request.getParameter("beginPrice");
        String endPrice = request.getParameter("endPrice");
        int indexPage = index != null ? Integer.parseInt(index) : 1;

        //-------validate -------
        beginPrice = (beginPrice != null && !beginPrice.isEmpty()) ? beginPrice : null;
        endPrice = (endPrice != null && !endPrice.isEmpty()) ? endPrice : null;
        beginDate = (beginDate != null && !beginDate.isEmpty()) ? beginDate : null;
        endDate = (endDate != null && !endDate.isEmpty()) ? endDate : null;
        tilte = (tilte != null && !tilte.isEmpty()) ? tilte : null;
        //---------------------------
        int startIndex = (indexPage - 1) * 10;

        if (tilte != null || beginDate != null || endDate != null || beginPrice != null || endPrice != null) {
            int countPage = or_dao.getTotalOrder(tilte, "pending", beginDate, endDate, beginPrice, endPrice);
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);
            List<Order> list = or_dao.FilterOrder(tilte, "pending", beginDate, endDate, beginPrice, endPrice, indexPage);

            request.setAttribute("orderList", list);
            request.setAttribute("tilteOrder", tilte);
            request.setAttribute("beginDate", beginDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("beginPrice", beginPrice);
            request.setAttribute("endPrice", endPrice);
            request.setAttribute("endPage", endPage);
            request.setAttribute("filter", "FilterOn");
        } else {
            int countPage = or_dao.getTotalOrder("pending");
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);

            List<Order> order = or_dao.getAll("pending", indexPage);

            request.setAttribute("orderList", order);
            request.setAttribute("endPage", endPage);
        }
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("pageNumber", indexPage);
        request.getRequestDispatcher("s_orderdelivered.jsp").forward(request, response);
    }

    protected void doGetDelivering(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String index = request.getParameter("index");
        String tilte = request.getParameter("tilteOrder");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String beginPrice = request.getParameter("beginPrice");
        String endPrice = request.getParameter("endPrice");
        int indexPage = index != null ? Integer.parseInt(index) : 1;

        //-------validate -------
        beginPrice = (beginPrice != null && !beginPrice.isEmpty()) ? beginPrice : null;
        endPrice = (endPrice != null && !endPrice.isEmpty()) ? endPrice : null;
        beginDate = (beginDate != null && !beginDate.isEmpty()) ? beginDate : null;
        endDate = (endDate != null && !endDate.isEmpty()) ? endDate : null;
        tilte = (tilte != null && !tilte.isEmpty()) ? tilte : null;
        //---------------------------
        int startIndex = (indexPage - 1) * 10;

        if (tilte != null || beginDate != null || endDate != null || beginPrice != null || endPrice != null) {
            int countPage = or_dao.getTotalOrder(tilte, "delivering", beginDate, endDate, beginPrice, endPrice);
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);
            List<Order> list = or_dao.FilterOrder(tilte, "delivering", beginDate, endDate, beginPrice, endPrice, indexPage);

            request.setAttribute("orderList", list);
            request.setAttribute("tilteOrder", tilte);
            request.setAttribute("beginDate", beginDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("beginPrice", beginPrice);
            request.setAttribute("endPrice", endPrice);
            request.setAttribute("endPage", endPage);
            request.setAttribute("filter", "FilterOn");
        } else {
            int countPage = or_dao.getTotalOrder("delivering");
            int endPage = countPage / 10 + (countPage % 10 != 0 ? 1 : 0);

            List<Order> order = or_dao.getAll("delivering", indexPage);

            request.setAttribute("orderList", order);
            request.setAttribute("endPage", endPage);
        }
        request.setAttribute("startIndex", startIndex);
        request.setAttribute("pageNumber", indexPage);
        request.getRequestDispatcher("s_orderdelivered.jsp").forward(request, response);
    }

}
