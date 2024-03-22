/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.NewsDAO;
import com.team3.onlineshopping.model.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author MinhBD
 */
public class MktPostEditServlet extends HttpServlet {

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
            out.println("<title>Servlet MktPostEditServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktPostEditServlet at " + request.getContextPath() + "</h1>");
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
    public int id;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String getNewsId;
        getNewsId = request.getParameter("newsId");
        id = Integer.parseInt(getNewsId);
        NewsDAO nDao = new NewsDAO();
        News dNews = nDao.getById(id);
        request.setAttribute("dNews", dNews);
        request.getRequestDispatcher("m_postedit.jsp").forward(request, response);
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
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String value = request.getParameter("status");
        String image = request.getParameter("image");
        LocalDate currentDate = LocalDate.now();

        // Định dạng ngày theo năm-ngày-tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Định dạng lại ngày hiện tại
        String formattedDate = currentDate.format(formatter);

        NewsDAO newDao = new NewsDAO();
        News post = newDao.getById(id);
        post.setNewsDescription(content);
        post.setNewsImgUrl(image);
        post.setNewsTitle(title);
        post.setNewsUpdateDate(formattedDate);
        post.setNewsId(id);
        post.setNewsStatus(value);
        newDao.update(post);
        response.sendRedirect("mkt_postdetail?newsId=" + id);
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
