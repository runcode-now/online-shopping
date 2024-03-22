/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.CategoryProductDAO;
import com.team3.onlineshopping.dal.CategoryProductDetailsDAO;
import com.team3.onlineshopping.dal.CategorySizeDAO;
import com.team3.onlineshopping.dal.FeedbackDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.dal.ProductImageDAO;
import com.team3.onlineshopping.dal.ProductSizeDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.CategoryProduct;
import com.team3.onlineshopping.model.CategoryProductDetails;
import com.team3.onlineshopping.model.CategorySize;
import com.team3.onlineshopping.model.Feedback;
import com.team3.onlineshopping.model.Product;
import com.team3.onlineshopping.model.ProductImage;
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
 * @author MinhBD
 */
public class MktFeedBackDeltaiServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

FeedbackDAO fd = new FeedbackDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Mkt_FeedBackDeltaiServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Mkt_FeedBackDeltaiServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ProductDAO pd = new ProductDAO();
        ProductImageDAO pid = new ProductImageDAO();
        ProductSizeDAO psd = new ProductSizeDAO();
        CategorySizeDAO csd = new CategorySizeDAO();
        CategoryProductDAO cpd = new CategoryProductDAO();
        CategoryProductDetailsDAO cpdd = new CategoryProductDetailsDAO();
        List<ProductImage> productImage;
        List<ProductSize> listProductSize;
        List<CategorySize> listCateSize = csd.getAll();
        List<CategoryProduct> listCateProduct;
        listCateProduct = cpd.getAll();
        Product product;
        String getProductId;
        int countImg = 0;
        getProductId = request.getParameter("proId");
        

        int productId, totalQuantity;
        String checkCateSize = "";

        try {
            productId = Integer.parseInt(getProductId);
            product = pd.getById(productId);
            totalQuantity = psd.getTotalQuantity(productId);
            productImage = pid.getAllByProId(productId);
            listProductSize = psd.getAllProSizeById(productId);
            
            // thêm
            CategoryProductDetails CateProDeId = cpdd.getByProId(productId);
            int compareCateProId = CateProDeId.getCateProId();

            if (request.getParameter("getCateProductId") != null) {
                compareCateProId = Integer.parseInt(request.getParameter("getCateProductId"));
            }
            List<CategoryProductDetails> listCateProDetails = cpdd.getAllByCateProId(compareCateProId);
            int cateProDetails = CateProDeId.getCateProDeId();
            if (request.getParameter("cateProDetails") != null) {
                cateProDetails = Integer.parseInt(request.getParameter("cateProDetails"));
            }
            // check xem size dạng gì (S-M-L or 35,36...)
            if (listProductSize.size() > 1 && listProductSize.size() <= 3) {
                checkCateSize = "fontsize";
            } else if (listProductSize.size() >= 4 && listProductSize.size() <= 10) {
                checkCateSize = "sizenumber";
            } else {
                checkCateSize = "none";
            }
            List<Feedback> feed = fd.getByProId(productId);
            product.setProDescription(product.getProDescription().replace("<br>", "\n"));
            request.setAttribute("cateProDetails", cateProDetails);
            request.setAttribute("compareCateProId", compareCateProId);
            request.setAttribute("listCateProDetails", listCateProDetails);
            request.setAttribute("listCateProduct", listCateProduct);
            request.setAttribute("productid", getProductId);
            request.setAttribute("countImg", countImg);
            request.setAttribute("product", product);
            request.setAttribute("totalQuantity", totalQuantity);
            request.setAttribute("productImage", productImage);
            request.setAttribute("listCateSize", listCateSize);
            request.setAttribute("listProductSize", listProductSize);
            request.setAttribute("checkCateSize", checkCateSize);
            request.setAttribute("feedback", feed);
            request.getRequestDispatcher("m_feedbackdeltai.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

   

}
