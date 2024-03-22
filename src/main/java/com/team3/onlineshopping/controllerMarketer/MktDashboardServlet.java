/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.CategoryProductDAO;
import com.team3.onlineshopping.dal.CategoryProductDetailsDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.CategoryProduct;
import com.team3.onlineshopping.model.CategoryProductDetails;
import com.team3.onlineshopping.model.Chart;
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
 * @author PC
 */
public class MktDashboardServlet extends HttpServlet {
    CategoryProductDetailsDAO cateProDe_dao = new CategoryProductDetailsDAO();
    CategoryProductDAO catePro_dao = new CategoryProductDAO();
    ProductDAO pro_dao = new ProductDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryProduct> cateProList = catePro_dao.getAll();       // list all category product
        String cateproid = request.getParameter("cateproid");
        
        // assign default value when access the first time
        if(cateproid == null) cateproid = "1";
        int cateProId = Integer.parseInt(cateproid);
        List<CategoryProductDetails> cateProDeList = cateProDe_dao.getAllByCateProId(cateProId);

        int cateProDeId = cateProDeList.get(0).getCateProDeId();    // get the first category product detail
        List<Chart> chartList = pro_dao.getRatingByCategoryProductDetailsId(cateProDeId);

        request.setAttribute("cateproid", cateProId);           // send chosen value
        request.setAttribute("cateprodeid", cateProDeId);       // send chosen value
        
        request.setAttribute("cateProList", cateProList);       // send list category product
        request.setAttribute("cateProDeList", cateProDeList);       // send list category product details
        
        request.setAttribute("chartList", chartList);       // send chart 

        request.getRequestDispatcher("m_dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryProduct> cateProList = catePro_dao.getAll();       // list all category product
        
        String cateproid = request.getParameter("cateproid");
        String cateprodeid = request.getParameter("cateprodeid");
        
        int cateProId = Integer.parseInt(cateproid);
        List<CategoryProductDetails> cateProDeList = cateProDe_dao.getAllByCateProId(cateProId);

        int cateProDeId = Integer.parseInt(cateprodeid);
        List<Chart> chartList = pro_dao.getRatingByCategoryProductDetailsId(cateProDeId);

        request.setAttribute("cateproid", cateproid);           // send chosen value
        request.setAttribute("cateprodeid", cateprodeid);       // send chosen value
        
        request.setAttribute("cateProList", cateProList);       // send list category product
        request.setAttribute("cateProDeList", cateProDeList);       // send list category product details
        
        request.setAttribute("chartList", chartList);       // send chart 
        request.getRequestDispatcher("m_dashboard.jsp").forward(request, response);
    }
}
