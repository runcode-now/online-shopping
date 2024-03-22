//4
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.CategorySizeDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.CategorySize;
import com.team3.onlineshopping.model.Product;
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
 * @author admin
 */
public class PubSearchProductServlet extends HttpServlet {

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
            out.println("<title>Servlet Pub_SearchProductByProductName</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Pub_SearchProductByProductName at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        session.setAttribute("account", acc);
        ProductDAO pd = new ProductDAO();
        CategorySizeDAO csd = new CategorySizeDAO();
        int numOfProduct, endPage, indexPage;
        String searching = request.getParameter("searching");
        String index = request.getParameter("index");
        String checkPrice;
        List<CategorySize> listCateSize = csd.getAll();
        checkPrice = null;
        if (index == null) {
            index = "1";
        }

        try {
            indexPage = Integer.parseInt(index);
            numOfProduct = pd.searchProductByProName(searching).size();
            endPage = numOfProduct / 16;
            if (numOfProduct % 16 != 0) {
                endPage++;
            }
            List<Product> listProduct = pd.searchProductByProNamePaging(searching, indexPage);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("listCateSize", listCateSize);
            request.setAttribute("endPage", endPage);
            request.setAttribute("tag", index);
            request.setAttribute("searching", searching);
            request.setAttribute("checkPrice", checkPrice);
            if (listProduct.isEmpty()) {
                request.setAttribute("msgNotFound", "Không có sản phẩm này!");
            } else {
                request.setAttribute("msgFound", "Những sản phẩm liên quan: " + searching + "");
            }
            request.getRequestDispatcher("p_searchbyproductname.jsp").forward(request, response);

        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
