/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.FeedbackDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.Feedback;
import com.team3.onlineshopping.model.Product;
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
public class MktFeedBackListServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktFeedBackListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktFeedBackListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Feedback> lFeedBack = fDao.splitPageFeedback(16, 1, "", "", "", 0);
        int numberPage = (int) Math.ceil((double) fDao.getTotalFeedBack() / 16);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", 1);
        request.setAttribute("quantity", 0);
        request.setAttribute("lFeedBack", lFeedBack);
        request.getRequestDispatcher("m_feedbacklist.jsp").forward(request, response);
    }

    AccountDAO aDao = new AccountDAO();
    FeedbackDAO fDao = new FeedbackDAO();
    CustomerDAO cus_dao = new CustomerDAO();
    ProductDAO pro_dao = new ProductDAO();

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

        option = (option == null) ? "" : option;
        textSearch = (textSearch == null) ? "" : textSearch;
        dateBegin = (dateBegin == null) ? "" : dateBegin;
        dateEnd = (dateEnd == null) ? "" : dateEnd;
        pageNum = (pageNum == null) ? "" : pageNum;
        quantity = (quantity == null) ? "" : quantity;
        int quantityAccount = Integer.parseInt(quantity);  // give the quantity account
        int totalAccount = fDao.getTotalFeedbackByCondition(textSearch, dateBegin, dateEnd, quantityAccount);
        int numberPage = 1;
        int page = 1;
        // user don't choose page
        if (!pageNum.isEmpty()) {
            page = Integer.parseInt(pageNum);
        }

        // give the quantity of page
        
            numberPage = (int) Math.ceil((double) totalAccount / 16);
        
        List<Feedback> feedbList = fDao.splitPageFeedback(16, page, textSearch, dateBegin, dateEnd, quantityAccount);

        // when user choose page
        if (option.equals("split")) {
            int number = 16 * (page - 1) + 1;

            out.println("<table class=\"table table-hover w-100 table-red thead-primary\">");
            out.println("<div class=\"tab-pane\" id=\"tabs-2\" role=\"tabpanel\">");
            out.println("<h5>Đánh giá sản phẩm</h5>");
            for (Feedback fb : feedbList) {
                createFeedbackHtml(fb, number, request, response);
                number++;
            }
            out.println("</div> ");
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
        // </editor-fold>

    }

    public void createFeedbackHtml(Feedback fb, int number, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Customer cus = cus_dao.getById(fb.getCusId());
        Account accCustomer = aDao.getById(cus.getAccId());
        Product pro = pro_dao.getById(fb.getProId());
        out.println("<div class=\"row d-flex py-3 w-100 relative\">");
        out.println("<div class=\"col-md-1 text-center\">");
        out.println("<img class=\"feedback__img\" src=\""+accCustomer.getAccAvarUrl()+"\" alt=\"alt\"/>");
        out.println("</div>");
        out.println("<div class=\"col-md-11\">");
        out.println("<div class=\"mb-1 d-flex justify-content-between\">");
        out.println("<div> ");
        for (int i = 0; i < fb.getFeedRating(); i++){
        out.println("<i class=\"fa-solid fa-star fa-xs\" style=\"color: #FFD43B;\"></i>");
        }
        out.println("</div> ");
        out.println("<p class=\"font-italic\" style=\"font-size: 12px\">"+fb.getFeedCreatedDate()+"</p>");
        out.println("</div> ");
        out.println("<h6 class=\"mb-2\">"+accCustomer.getAccName()+"</h6>");
        out.println("<p class=\"text-justify\" style=\"font-size: 13px\">"+fb.getFeedContent()+"</p>");
        out.println("<a href=\"mkt_feedbackdeltai?proId="+ fb.getProId()+"\"  class=\"feedback__product\"\">");
        out.println("<h6 class=\"pro_name\">"+pro.getProName()+"</h6>");
        out.println("</a>");
        out.println("</div> ");
        out.println("</div> ");

    }
}
