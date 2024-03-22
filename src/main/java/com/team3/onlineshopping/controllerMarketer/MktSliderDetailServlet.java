//mktsliderde
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

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)
public class MktSliderDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktSliderDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktSliderDetailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO nd = new NewsDAO();
        int newsId;

        String status = request.getParameter("statusPop");

        if (request.getParameter("typeCollection") != null) {
            request.setAttribute("typeCollection", request.getParameter("typeCollection"));
            request.setAttribute("collectionStatus", request.getParameter("collectionStatus"));
            request.setAttribute("collectionTitle", request.getParameter("collectionTitle"));
            request.setAttribute("listSelectedProduct", request.getParameter("listSelectedProduct"));

            // Xử lý nếu người dùng update sản phẩm
            String collectionId = "";

            if (request.getParameter("collectionId") == null || request.getParameter("collectionId").equalsIgnoreCase("")) {
                collectionId = "null";
            } else {
                collectionId = request.getParameter("collectionId");

            }

            request.setAttribute("collectionId", collectionId);

        }

        try {
            newsId = Integer.parseInt(request.getParameter("newsId"));
            News news = nd.getById(newsId);
            request.setAttribute("newsId", newsId);
            request.setAttribute("news", news);
            request.setAttribute("status", status);
            request.getRequestDispatcher("m_slider-detail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO nd = new NewsDAO();
        String newsTitle = request.getParameter("newsTitle");
        String sliderStatus = request.getParameter("sliderStatus");
        String sliderDescription = request.getParameter("sliderDescription");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sliderCreateDate = currentDate.format(formatter);

        int newsId;
        String proImgBanner;
        try {
            newsId = Integer.parseInt(request.getParameter("newsId"));

            News news = nd.getById(newsId);

            if (request.getPart("NewProImgBanner") != null && request.getPart("NewProImgBanner").getSize() > 0) {
                Part filePart = (Part) request.getPart("NewProImgBanner"); // "image" là tên của input file
                InputStream fileContent = filePart.getInputStream();
                proImgBanner = encodeImageToBase64(fileContent);
                news.setNewsImgUrl("data:image/png;base64," + proImgBanner);
            }

            news.setNewsTitle(newsTitle);
            news.setNewsStatus(sliderStatus);
            news.setNewsDescription(sliderDescription);
            news.setNewsUpdateDate(sliderCreateDate);
            nd.update(news);
            response.sendRedirect("mkt_sliderdetail?newsId=" + newsId + "&statusPop=success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
