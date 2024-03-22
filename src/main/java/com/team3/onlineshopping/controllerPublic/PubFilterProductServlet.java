//5
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.CategoryProductDAO;
import com.team3.onlineshopping.dal.CategoryProductDetailsDAO;
import com.team3.onlineshopping.dal.CategorySizeDAO;
import com.team3.onlineshopping.dal.ProductDAO;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class PubFilterProductServlet extends HttpServlet {

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
            out.println("<title>Servlet Pub_FilterPriceAndSizeProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Pub_FilterPriceAndSizeProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    // Filter searchProductList
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO pd = new ProductDAO();
        List<Product> listProduct;
        String price = request.getParameter("filterPrice");
        int endPage, numOfProduct, indexPage;
        String index = request.getParameter("index");
        String searching = request.getParameter("searching");
        int number_product = 16;
        if (index == null) {
            index = "1";
        }
        if (price == null) {
            price = "";
        }

        try {
            indexPage = Integer.parseInt(index);
            numOfProduct = pd.filterSearchProductByProNameAndSize(price, searching).size();
            endPage = numOfProduct / number_product;
            if (numOfProduct % number_product != 0) {
                endPage++;
            }
            listProduct = pd.filterSearchProductByProNameAndSizePaging(price, searching, indexPage);
            
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("endPage", endPage);
            request.setAttribute("tag", indexPage);
            request.setAttribute("checkPrice", price);
            request.setAttribute("searching", searching);
            if (listProduct.isEmpty()) {
                request.setAttribute("msgNotFound", "Không có sản phẩm này!");
            } else {
                request.setAttribute("msgFound", "Những sản phẩm liên quan: " + searching + "");
            }
            request.getRequestDispatcher("p_searchbyproductname.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // Filter viewProductList
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        CategoryProductDetailsDAO cpdd = new CategoryProductDetailsDAO();
        CategoryProductDAO cpd = new CategoryProductDAO();
        CategorySizeDAO csd = new CategorySizeDAO();

        List<Product> listProduct;
        List<CategoryProductDetails> listCateProDetails;
        String price = request.getParameter("filterPrice");
        int endPage, numOfProduct, indexPage, CategoryProductDetailsId, CategoryProductId;
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        if (price == null) {
            price = "";
        }


        int number_product = 16;
        try {
            CategoryProductId = Integer.parseInt(request.getParameter("CategoryProductId"));
            CategoryProductDetailsId = Integer.parseInt(request.getParameter("CategoryProductDetailsId"));
            indexPage = Integer.parseInt(index);
            numOfProduct = pd.filterProductByProNameAndSize(price, CategoryProductId, CategoryProductDetailsId).size();
            endPage = numOfProduct / number_product;
            if (numOfProduct % number_product != 0) {
                endPage++;
            }
            
            CategoryProduct cp = cpd.getById(CategoryProductId);
            listProduct = pd.filterProductByProNameAndSizePaging(price, indexPage, CategoryProductId, CategoryProductDetailsId);
            listCateProDetails = cpdd.getallCateProDeIdByCateProId(CategoryProductId);
            CategoryProduct catePro = cpd.getById(CategoryProductId);
            CategoryProductDetails cateProDe = cpdd.getById(CategoryProductDetailsId);

            request.setAttribute("listProduct", listProduct);
            request.setAttribute("listCateProDetails", listCateProDetails);
            request.setAttribute("endPage", endPage);
            request.setAttribute("tag", index);
            request.setAttribute("cateProDe", cateProDe);
            request.setAttribute("catePro", catePro);
            request.setAttribute("cateProduct", cp);
            request.setAttribute("categoryProductDetailsId", CategoryProductDetailsId);
            request.setAttribute("categoryProductId", CategoryProductId);
            request.setAttribute("checkPrice", price);

            request.getRequestDispatcher("p_product-list.jsp").forward(request, response);
        } catch (Exception e) {
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
