/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.CollectionDAO;
import com.team3.onlineshopping.dal.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.dal.ProductImageDAO;
import com.team3.onlineshopping.model.Collection;
import com.team3.onlineshopping.model.News;
import com.team3.onlineshopping.model.Product;
import com.team3.onlineshopping.model.ProductImage;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author minhb
 */
public class PubHomeServlet extends HttpServlet {

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
            out.println("<title>Servlet PublicHomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicHomeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO ProDao = new ProductDAO();
        ProductImageDAO ProIDao = new ProductImageDAO();
        NewsDAO nd = new NewsDAO();
        CollectionDAO cd = new CollectionDAO();
        //-----------------------------------------------------
        List<Product> ProListSet = ProDao.getAllNew("set");
        List<Product> ProListAo = ProDao.getAllNew("áo");
        List<Product> ProListQuan = ProDao.getAllNew("quần");
        List<Product> ProListVay = ProDao.getAllNew("váy");
        List<Product> ProListLot = ProDao.getAllNew("lót");
        List<Product> ProListHot = ProDao.getAllHot();
        //-----------------------------------------------------
        List<Collection> listColBanner = cd.get5SliderNewest();
        int countAo = ProDao.getCountProduct("áo");
        int countQuan = ProDao.getCountProduct("quần");
        int countVay = ProDao.getCountProduct("váy");
        int countLot = ProDao.getCountProduct("lót");
        request.setAttribute("countAo", countAo);
        request.setAttribute("countQuan", countQuan);
        request.setAttribute("countVay", countVay);
        request.setAttribute("countLot", countLot);
        //-----------------------------------------------------
        request.setAttribute("listColBanner", listColBanner);
        request.setAttribute("ProDaoSet", ProListSet);
        request.setAttribute("ProDaoAo", ProListAo);
        request.setAttribute("ProDaoQuan", ProListQuan);
        request.setAttribute("ProDaoVay", ProListVay);
        request.setAttribute("ProDaoLot", ProListLot);
        request.setAttribute("ProHot", ProListHot);
        //-----------------------------------------------------
        List<Collection> listCollection = cd.getAll();
        session.setAttribute("listCollection", listCollection);
        //-----------------------------------------------------

        request.getRequestDispatcher("p_home.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO ProDao = new ProductDAO();
        ProductImageDAO ProIDao = new ProductImageDAO();
        CollectionDAO cd = new CollectionDAO();
        //-----------------------------------------------------
        List<Product> ProListSet = ProDao.getAllNew("set");
        List<Product> ProListAo = ProDao.getAllNew("áo");
        List<Product> ProListQuan = ProDao.getAllNew("quần");
        List<Product> ProListVay = ProDao.getAllNew("váy");
        List<Product> ProListLot = ProDao.getAllNew("lót");
        List<Product> ProListHot = ProDao.getAllHot();
        //-----------------------------------------------------
        List<Collection> listColBanner = cd.get5SliderNewest();
        int countAo = ProDao.getCountProduct("áo");
        int countQuan = ProDao.getCountProduct("quần");
        int countVay = ProDao.getCountProduct("váy");
        int countLot = ProDao.getCountProduct("lót");
        request.setAttribute("countAo", countAo);
        request.setAttribute("countQuan", countQuan);
        request.setAttribute("countVay", countVay);
        request.setAttribute("countLot", countLot);
        //-----------------------------------------------------
        request.setAttribute("listColBanner", listColBanner);
        request.setAttribute("ProDaoSet", ProListSet);
        request.setAttribute("ProDaoAo", ProListAo);
        request.setAttribute("ProDaoQuan", ProListQuan);
        request.setAttribute("ProDaoVay", ProListVay);
        request.setAttribute("ProDaoLot", ProListLot);
        request.setAttribute("ProHot", ProListHot);
        //-----------------------------------------------------
        request.getRequestDispatcher("p_home.jsp").forward(request, response);
//        doGet(request, response);
    }

}
