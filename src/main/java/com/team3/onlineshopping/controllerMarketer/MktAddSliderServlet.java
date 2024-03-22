// addsliderser
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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 *
 * @author admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)
public class MktAddSliderServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktAddSliderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktAddSliderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cateNewsId = Integer.parseInt(request.getParameter("cateNewsId"));
        request.setAttribute("cateNewsId", cateNewsId);
        request.getRequestDispatcher("m_add-slider.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO nd = new NewsDAO();
        try {
            String sliderTitle = request.getParameter("sliderTitle");
            String sliderStatus = request.getParameter("sliderStatus");
            String sliderDescription = request.getParameter("sliderDescription");
            int sliderView = Integer.parseInt(request.getParameter("sliderView"));
            String getProImgBanner, proImgBanner;
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String proCreateDate = currentDate.format(formatter);
            Part filePart = (Part) request.getPart("proImgBanner"); 
            InputStream fileContent = filePart.getInputStream();
            getProImgBanner = encodeImageToBase64(fileContent);
            proImgBanner = "data:image/png;base64," + getProImgBanner;
            int cateNewsId = Integer.parseInt(request.getParameter("cateNewsId"));

            News news = new News(1, sliderTitle, proImgBanner, sliderDescription, proCreateDate, null , sliderStatus, sliderView, cateNewsId, 2);
            nd.add(news);
            response.sendRedirect("mkt_sliderlist?cateNewsId=2&statusPopAdd=successAdd");
        } catch (Exception e) {
        }
    }

    private String encodeImageToBase64(InputStream imageInputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = imageInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }



}
