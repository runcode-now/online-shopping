//mktsliderlist
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.NewsDAO;
import com.team3.onlineshopping.model.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MktSliderListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktSliderListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktSliderListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO nd = new NewsDAO();
        List<News> listNews;
        int numOfProduct, indexPage, endPage, startNumOr, endNumOr, cateNewsId;
        String index = request.getParameter("numberPage");
        String paging = request.getParameter("paging");
        String sliderStatus = request.getParameter("sliderStatus");
        String sortByView = request.getParameter("sortByView");
        String searching = request.getParameter("searching");
        String statusPopAdd = request.getParameter("statusPopAdd");
        String statusPopDel = request.getParameter("statusPopDel");

        String check = "";
        if (check.equalsIgnoreCase(searching)) {
            searching = null;
        }
        if (check.equalsIgnoreCase(sliderStatus)) {
            sliderStatus = null;
        }
        if (index == null) {
            index = "1";
            paging = "-1";
        }
        
        // bo suu tap
        if (request.getParameter("typeCollection") != null) {
            request.setAttribute("typeCollection", request.getParameter("typeCollection"));
            request.setAttribute("collectionStatus", request.getParameter("collectionStatus"));
            request.setAttribute("collectionTitle", request.getParameter("collectionTitle"));
            // Xử lý nếu người dùng update Slider
            String collectionId = "";

            if (request.getParameter("collectionId") == null || request.getParameter("collectionId").equalsIgnoreCase("")) {
                collectionId = "null";
            } else {
                collectionId = request.getParameter("collectionId");

            }
            request.setAttribute("collectionId", collectionId);

            String getListSelectedProduct = "";

            if (request.getParameter("listSelectedProduct") == null || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
                getListSelectedProduct = null;
            }

            if (getListSelectedProduct != null) {
                String encodedList = request.getParameter("listSelectedProduct");
                String decodedList = URLDecoder.decode(encodedList, "UTF-8");

                // Xóa các ký tự `[` và `]` từ chuỗi
                decodedList = decodedList.replace("[", "").replace("]", "");

                // Tách các phần tử dựa trên dấu phẩy
                String[] elements = decodedList.split(",");
                if (elements.length > 0 && elements[0].isEmpty()) {
                    elements = Arrays.copyOfRange(elements, 1, elements.length);
                }
                Set<Integer> listSelectedProduct = new HashSet<>();
                for (String option : elements) {
                    String[] parts = option.split(",");
                    for (String part : parts) {
                        listSelectedProduct.add(Integer.parseInt(part.trim()));
                    }

                }

                request.setAttribute("listSelectedProduct", listSelectedProduct);
            }

        }

//        try {
        cateNewsId = Integer.parseInt(request.getParameter("cateNewsId"));
        indexPage = Integer.parseInt(index);
        numOfProduct = nd.getAllByCateNewsId(cateNewsId, searching, sliderStatus, sortByView).size();

        endPage = numOfProduct / 16;
        startNumOr = (indexPage - 1) * 16;

        if (numOfProduct % 16 != 0) {
            endPage++;
        }
        listNews = nd.getAllByCateNewsIdPaging(cateNewsId, indexPage, searching, sliderStatus, sortByView);
        request.setAttribute("listNews", listNews);
        request.setAttribute("numberPage", indexPage);
        request.setAttribute("paging", paging);
        request.setAttribute("startNumOr", startNumOr);
        request.setAttribute("endPage", endPage);
        request.setAttribute("sliderStatus", sliderStatus);
        request.setAttribute("sortByView", sortByView);
        request.setAttribute("searching", searching);
        
        if (statusPopDel != null) {
            request.setAttribute("status", statusPopDel);
        } else if (statusPopAdd != null) {
            request.setAttribute("status", statusPopAdd);
        }

        request.getRequestDispatcher("m_slider-list.jsp").forward(request, response);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }



}
