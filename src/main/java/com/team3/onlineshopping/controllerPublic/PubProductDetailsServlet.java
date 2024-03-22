/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

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
public class PubProductDetailsServlet extends HttpServlet {

    ProductDAO pd = new ProductDAO();
    ProductImageDAO pid = new ProductImageDAO();
    CategoryProductDAO cpd = new CategoryProductDAO();
    CategoryProductDetailsDAO cpdd = new CategoryProductDetailsDAO();
    CategorySizeDAO csd = new CategorySizeDAO();
    FeedbackDAO fd = new FeedbackDAO();
    ProductSizeDAO proSize_dao = new ProductSizeDAO();

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
        String proId_raw = "";
        int count = 0;

        try {
            //truyen productId qua lai giua 2 serlet
            if (request.getParameter("productId") == null && request.getParameter("proId") != null) {
                proId_raw = request.getParameter("proId");
            } else if (request.getParameter("productId") != null && request.getParameter("proId") == null) {
                proId_raw = request.getParameter("productId");
            }
            int proId = Integer.parseInt(proId_raw);

            List<ProductSize> proSize = proSize_dao.getByProductId(proId);
            for (ProductSize productSize : proSize) {
                count += productSize.getProSizeQuantity();
            }

            Product pro = pd.getById(proId, "on");
            CategoryProductDetails cateProDe = cpdd.getById(pro.getCateProDeId());
            CategoryProduct catePro = cpd.getById(cateProDe.getCateProId());
            List<CategorySize> cateSize = csd.getAll();
            List<ProductImage> listProImg = pid.getAllByProId(pro.getProId());
            String[] arr = pro.getProDescription().split("#");
            List<Feedback> feed = fd.getByProId(proId);
            List<Product> listPro = pd.getAllProductByCateProDetailId(cateProDe.getCateProDeId());

            request.setAttribute("catrgoryProduct", catePro);
            request.setAttribute("catrgoryProductDetails", cateProDe);
            request.setAttribute("product", pro);
            request.setAttribute("productImage", listProImg);
            request.setAttribute("productDescription", arr);
            request.setAttribute("cateProDe", listPro);
            request.setAttribute("cateSize", cateSize);
            request.setAttribute("feedback", feed);
            request.setAttribute("remaining", count);

            request.getRequestDispatcher("p_product-details.jsp").forward(request, response);
//            request.getRequestDispatcher("test.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
