/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.NewsDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.News;
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
 * @author MinhBD
 */
public class MktPostListServlet extends HttpServlet {

    NewsDAO nDao = new NewsDAO();
    AccountDAO aDao = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<News> lNews = nDao.splitPagePost(5, 1, "", "", "", "");
        int numberPage = (int) Math.ceil((double) nDao.getTotalPost() / 5);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", 1);
        request.setAttribute("quantity", 1);
        request.setAttribute("lNews", lNews);
        request.getRequestDispatcher("m_postlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String option = request.getParameter("option");
        String textSearch = request.getParameter("textSearch");
        String dateBegin = request.getParameter("dateBegin");
        String dateEnd = request.getParameter("dateEnd");
        String pageNum = request.getParameter("page");
        String quantity = request.getParameter("quantityPost");
        String statusFilter = request.getParameter("statusFilter");

        option = (option == null) ? "" : option;
        textSearch = (textSearch == null) ? "" : textSearch;
        dateBegin = (dateBegin == null) ? "" : dateBegin;
        dateEnd = (dateEnd == null) ? "" : dateEnd;
        pageNum = (pageNum == null) ? "" : pageNum;
        quantity = (quantity == null) ? "" : quantity;

        int quantityAccount = Integer.parseInt(quantity);
        int totalAccount = nDao.getTotalPostByCondition(textSearch, dateBegin, dateEnd, statusFilter);
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
        List<News> lNews = nDao.splitPagePost(quantityAccount, page, textSearch, dateBegin, dateEnd, statusFilter);

        // when user choose page
        if (option.equals("split")) {
            int number = quantityAccount * (page - 1) + 1;

            out.println("<table class=\"table table-hover w-100 table-red thead-primary\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th style=\"width: 5%;\" scope=\"col\">STT</th>");
            out.println("<th style=\"width: 33%;\" scope=\"col\">Tiêu đề</th>");
            out.println("<th style=\"width: 10%;\" scope=\"col\">Ngày đăng</th>");
            out.println("<th style=\"width: 10%;\" scope=\"col\">Cập nhật</th>");
            out.println("<th style=\"width: 9%;\" scope=\"col\">Lượt xem</th>");
            out.println("<th style=\"width: 15%;\" scope=\"col\">Người đăng</th>");
            out.println("<th style=\"width: 10%;\" scope=\"col\">Trạng thái</th>");
            out.println("<th style=\"width: 8%;\" scope=\"col\">Chi tiết</th>");
            out.println("</tr>");
            out.println("    <thead>");
            out.println("    <tbody>");
            out.println("    <div>");
            out.println("    </div>");
            for (News news : lNews) {
                createAccountHtml(news, number, request, response);
                number++;
            }
            out.println("<tbody>");
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
            request.setAttribute("lNews", lNews);
            request.getRequestDispatcher("m_postlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("m_postlist.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public void createAccountHtml(News news, int number, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("    <tr class=\"my-5\">");
        out.println("    <td class=\"long-text\">" + number + "</td>");
        out.println("    <td class=\"long-text\">" + news.getNewsTitle() + "</td>");
        out.println("    <td class=\"long-text\">" + news.getNewsCreatedDate() + "</td>");
        out.println("    <td class=\"long-text\">" + news.getNewsUpdateDate() + "</td>");
        out.println("    <td class=\"long-text\">" + news.getNewsView() + "</td>");
        out.println("    <td class=\"long-text\">" + aDao.getAccByEmpId(news.getEmId()).getAccName() + "</td>");
        out.println("<td class=\"long-text\">");
        if (news.getNewsStatus().equals("on")) {
            out.println("<p class=\"text-success bold\">Đang hiện </p>");
        } else {
            out.println("<p class=\"text-danger bold\">Đã ẩn</p>");
        }
        out.println("</td>");
        out.println("<td><a class=\"d-flex justify-content-center\" href=\"mkt_postdetail?newsId=" + news.getNewsId() + "\"><i class=\"far fa-eye fa-sm\" style=\"color: #b1a9a9;\"></i></a></td>");
        out.println("</tr>");
    }

}
