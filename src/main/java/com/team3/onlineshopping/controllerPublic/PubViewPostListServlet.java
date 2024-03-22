/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.NewsDAO;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author MinhBD
 */
public class PubViewPostListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    NewsDAO nDao = new NewsDAO();
    AccountDAO aDao = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<News> lNews = nDao.splitPagePost(8, 1, "", "", "", "on");
        int numberPage = (int) Math.ceil((double) nDao.getTotalPost("on") / 8);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", 1);
        request.setAttribute("quantity", 1);
        request.setAttribute("lNews", lNews);
        request.getRequestDispatcher("p_viewpostlist.jsp").forward(request, response);

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
        String statusFilter = "on";

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
            out.println("<div class=\"row property__gallery\">");
            for (News news : lNews) {
                createAccountHtml(news, number, request, response);
                number++;
            }
            out.println("</div>");
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
            request.getRequestDispatcher("p_viewpostlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("p_viewpostlist.jsp");
        }
    }

    public void createAccountHtml(News news, int number, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<div class=\"col-md-12 mb-4\">");
        out.println("    <a class=\"d-flex gap-3\" href=\"pub_postdetail?newsId=" + news.getNewsId() + "\">");
        out.println("        <div class=\"product__item__pic\">" + news.getNewsImgUrl() + "</div>");
        out.println("        <div class=\"product__item__text text-left\" style=\"padding: 0; max-width: 75%\">");
        out.println("            <h6 class=\"mt-2\" style=\"font-size:23px; font-family: sans-serif;\">" + news.getNewsTitle() + "</h6>");
        out.println("            <p>" + aDao.getAccByEmpId(news.getEmId()).getAccName() + " • <span class=\"font-italic\">" + news.getNewsCreatedDate() + "</span></p>");
        out.println("        </div>");
        out.println("    </a>");
        out.println("</div>");

    }

}
