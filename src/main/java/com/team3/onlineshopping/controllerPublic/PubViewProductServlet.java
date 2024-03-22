//3
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.CategoryProductDAO;
import com.team3.onlineshopping.dal.CategoryProductDetailsDAO;
import com.team3.onlineshopping.dal.CategorySizeDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.CategoryProduct;
import com.team3.onlineshopping.model.CategoryProductDetails;
import com.team3.onlineshopping.model.CategorySize;
import com.team3.onlineshopping.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class PubViewProductServlet extends HttpServlet {

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
            out.println("<title>Servlet Pub_ViewProductListByCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Pub_ViewProductListByCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        session.setAttribute("account", acc);
        ProductDAO pd = new ProductDAO();

        CategoryProductDetailsDAO cpdd = new CategoryProductDetailsDAO();
        CategoryProductDAO cpd = new CategoryProductDAO();
        List<Product> listProduct;
        List<CategoryProductDetails> listCateProDetails;
        List<String> listCateSize = new ArrayList<>();
        int CategoryProductId, CategoryProductDetailsId, indexPage, numOfProduct, endPage;
        String index = request.getParameter("index");
        String checkPrice;
        checkPrice = null;
        // neu k an vao trang nao thi hien thi ra trang dau tien 
        if (index == null) {
            index = "1";
        }

        try {
            CategoryProductId = Integer.parseInt(request.getParameter("CategoryProductId"));
            CategoryProductDetailsId = Integer.parseInt(request.getParameter("CategoryProductDetailsId"));
            indexPage = Integer.parseInt(index);

            CategoryProduct cp = cpd.getById(CategoryProductId);

            if (CategoryProductDetailsId == 0) {
                // paging
                numOfProduct = pd.getAllProductOfCateId(CategoryProductId).size();
                endPage = numOfProduct / 16;
                if (numOfProduct % 16 != 0) {
                    endPage++;
                }
                listProduct = pd.getAllProductOfCateIdPaging(CategoryProductId, indexPage);
                listCateProDetails = cpdd.getallCateProDeIdByCateProId(CategoryProductId);
                CategoryProduct catePro = cpd.getById(CategoryProductId);
                CategoryProductDetails cateProDe = cpdd.getById(CategoryProductDetailsId);
                request.setAttribute("listCateProDetails", listCateProDetails);
                request.setAttribute("listProduct", listProduct);
                request.setAttribute("cateProduct", cp);
                request.setAttribute("endPage", endPage);
                request.setAttribute("tag", index);
                request.setAttribute("cateProDe", cateProDe);
                request.setAttribute("catePro", catePro);
                request.setAttribute("checkPrice", checkPrice);
                request.setAttribute("categoryProductDetailsId", CategoryProductDetailsId);
                request.setAttribute("categoryProductId", CategoryProductId);
            } else {
                // paging
                numOfProduct = pd.getAllProductByCateId(CategoryProductId, CategoryProductDetailsId).size();
                endPage = numOfProduct / 16;
                if (numOfProduct % 16 != 0) {
                    endPage++;
                }
                listProduct = pd.getAllProductByCateIdPaging(CategoryProductId, CategoryProductDetailsId, indexPage);
                listCateProDetails = cpdd.getallCateProDeIdByCateProId(CategoryProductId);
                CategoryProduct catePro = cpd.getById(CategoryProductId);
                CategoryProductDetails cateProDe = cpdd.getById(CategoryProductDetailsId);
                request.setAttribute("listProduct", listProduct);
                request.setAttribute("listCateProDetails", listCateProDetails);
                request.setAttribute("cateProduct", cp);
                request.setAttribute("endPage", endPage);
                request.setAttribute("cateProDe", cateProDe);
                request.setAttribute("catePro", catePro);
                request.setAttribute("categoryProductId", CategoryProductId);
                request.setAttribute("categoryProductDetailsId", CategoryProductDetailsId);
                request.setAttribute("checkPrice", checkPrice);
                request.setAttribute("tag", index);

            }

            request.getRequestDispatcher("p_product-list.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
