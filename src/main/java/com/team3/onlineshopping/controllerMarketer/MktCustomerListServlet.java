/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Admin
 */
public class MktCustomerListServlet extends HttpServlet {

    AccountDAO acc_dao = new AccountDAO();
    CustomerDAO cus_dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Account> accList = cus_dao.splitPageCustomer(10, 1, "", 0, 0, "");

        int numberPage = (int) Math.ceil((double) cus_dao.getTotalCustomerByCondition("", 0, 0, "")/ 10);

        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", 1);
        request.setAttribute("quantity", 10);
        request.setAttribute("accList", accList);
        request.getRequestDispatcher("m_customerlist.jsp").forward(request, response);
//        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String option = request.getParameter("option");
        String textSearch = request.getParameter("textSearch");
        String num_from = request.getParameter("numBegin");
        String num_end = request.getParameter("numEnd");
        String statusFilter = request.getParameter("statusFilter");
        String quantity = request.getParameter("quantityAccount");
        String pageNum = request.getParameter("page");

        option = (option == null) ? "" : option;
        textSearch = (textSearch == null) ? "" : textSearch;
        num_from = (num_from == null) ? "" : num_from;
        num_end = (num_end == null) ? "" : num_end;
        statusFilter = (statusFilter == null) ? "" : statusFilter;
        pageNum = (pageNum == null) ? "" : pageNum;
        quantity = (quantity == null) ? "" : quantity;

        int quantityAccount = Integer.parseInt(quantity);  // give the quantity account
        long numBegin = (num_from == "") ? 0 : Long.parseLong(num_from);
        long numEnd = (num_end == "") ? 0 : Long.parseLong(num_end);
        int totalAccount = cus_dao.getTotalCustomerByCondition(textSearch, numBegin, numEnd, statusFilter);
        int numberPage = 1;

        int page = 1;
        // user don't choose page
        if (!pageNum.isEmpty()) {
            page = Integer.parseInt(pageNum);
        }

        // give the quantity of page
        if (quantityAccount != 0) {
            numberPage = (int) Math.ceil((double) totalAccount / quantityAccount);
        }
        List<Account> accList = cus_dao.splitPageCustomer(quantityAccount, page, textSearch, numBegin, numEnd, statusFilter);

        // when user choose page
        if (option.equals("split")) {
            int number = quantityAccount * (page - 1) + 1;

            out.println("<table class=\"table-user\">");
            out.println("<tr class=\"table-user__header\">");
            out.println("    <th style=\"width: 5%;\">STT</th>");
            out.println("    <th style=\"width: 15%;\">Tên</th>");
            out.println("    <th style=\"width: 20%;\">Email</th>");
            out.println("    <th style=\"width: 15%;\">Số điện thoại</th>");
            out.println("    <th style=\"width: 18%;\">Trạng thái</th>");
            out.println("    <th style=\"width: 17%;\">Tổng tiền đã mua</th>");
            out.println("    <th style=\"width: 10%;\">Xem chi tiết</th>");
            out.println("</tr>");

            for (Account acc : accList) {
                createAccountHtml(acc, number, request, response);
                number++;
            }
            out.println("</table>");

            out.println("<div class=\"page\">");
            for (int i = 1; i <= numberPage; i++) {
                if (page == i) {
                    out.println("<a href=\"#\" class=\"page__number--chosen\" onclick=\"movePage(" + i + ")\"><p>" + i + "</p></a>");
                } else {
                    out.println("<a href=\"#\" class=\"page__number\" onclick=\"movePage(" + i + ")\"><p>" + i + "</p></a>");
                }
            }
            out.println("</div>");
            out.close();
        }

        // user just use filter
        if (option.isEmpty()) {
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("page", page);
            request.setAttribute("quantity", quantityAccount);
            request.setAttribute("accList", accList);
            request.getRequestDispatcher("m_customerlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("m_customerlist.jsp");
        }
    }

    public void createAccountHtml(Account acc, int number, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<tr class=\"table-user__body\" id=\"row_" + acc.getAccId() + "\">");
        out.println("    <td>" + number + "</td>");
        out.println("    <td>" + acc.getAccName() + "</td>");
        out.println("    <td>" + acc.getAccEmail() + "</td>");
        out.println("    <td>" + acc.getAccPhone() + "</td>");
        out.println("    <td>");
        if (acc.getAccStatus().equals("off")) {
            out.println("<p class=\"deactive\" style=\"margin: 0\">Dừng hoạt động</p>");
        } else {
            out.println("<p class=\"active\" style=\"margin: 0\">Đang hoạt động</p>");
        }
        out.println("    </td>");

        // format created date
        Customer cus = cus_dao.getByAccountId(acc.getAccId());
        double totalPurchase = cus.getCusTotalPurchase();

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        String formattedTotalPurchase = nf.format(totalPurchase);
        out.println("    <td>" + formattedTotalPurchase + "</td>");
        out.println("    <td class=\"edit1\">");
        out.println("        <a href=\"mkt_cusdetails?accId=" + acc.getAccId() + "\"><i class=\"far fa-eye fa-sm\" style=\"color: #b1a9a9;\"></i></a>");
        out.println("    </td>");
        out.println("</tr>");
    }

}
