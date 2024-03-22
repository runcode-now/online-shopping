// mktColleclist
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.CollectionDAO;
import com.team3.onlineshopping.dal.NewsDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.Collection;
import com.team3.onlineshopping.model.News;
import com.team3.onlineshopping.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MktCollectionListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktCollectionListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktCollectionListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // Page display list of collection
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initilize
        CollectionDAO cd = new CollectionDAO();
        NewsDAO nd = new NewsDAO();
        String searching = request.getParameter("searching");
        String collectionStatus = request.getParameter("collectionStatus");
        String index = request.getParameter("numberPage");
        int numOfProduct, indexPage = 0, endPage, startNumOr;
        List<Collection> listCollection;
        String check = "";
        String statusPopAdd = request.getParameter("statusPopAdd");
        String statusPopDel = request.getParameter("statusPopDel");
        if (check.equalsIgnoreCase(collectionStatus)) {
            collectionStatus = null;
        }
        if (check.equalsIgnoreCase(searching)) {
            searching = null;
        }

        // Check index page
        if (index == null) {
            index = "1";
        }

        try {

            indexPage = Integer.parseInt(index);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // paging
        numOfProduct = cd.getListCol(searching, collectionStatus).size();
        startNumOr = (indexPage - 1) * 8;
        endPage = numOfProduct / 8;
        if (numOfProduct % 8 != 0) {
            endPage++;
        }

        // Get list collection
        listCollection = cd.getListColPaging(searching, collectionStatus, indexPage);

        // check searching
        if (searching != null) {
            if (listCollection.isEmpty()) {
                request.setAttribute("msgNotFound", "Không có bộ sưu tập này!");
            } else {
                request.setAttribute("msgFound", "Những bộ sưu tập liên quan: " + searching + "");
            }
            request.setAttribute("back", "Trở về danh sách bộ sưu tập");
        }

        // Check status
        if (collectionStatus != null) {
            if (collectionStatus.equalsIgnoreCase("on")) {
                request.setAttribute("msgOn", "Đang hoạt động");
            } else {
                request.setAttribute("msgOff", "Đã dừng");
            }
            request.setAttribute("back", "Trở về danh sách bộ sưu tập");
        }
        // Send data
        request.setAttribute("listCollection", listCollection);

        request.setAttribute("numberPage", indexPage);
        request.setAttribute("startNumOr", startNumOr);
        request.setAttribute("endPage", endPage);

        request.setAttribute("searching", searching);
        request.setAttribute("collectionStatus", collectionStatus);

        // Gửi thông báo thành công 
        if (statusPopDel != null) {
            request.setAttribute("status", statusPopDel);
        } else if (statusPopAdd != null) {
            request.setAttribute("status", statusPopAdd);
        }
        // forward to another page
        request.getRequestDispatcher("m_collection-list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String handleColDe = request.getParameter("handleColDe");

        if (handleColDe.equalsIgnoreCase("viewColDe")) {
            viewCollectionDetails(request, response);
        } else if (handleColDe.equalsIgnoreCase("reviewListSel")) {
            viewSelectedProduct(request, response);
        } else if (handleColDe.equalsIgnoreCase("chooseSlider")) {
            chooseNewSlider(request, response);
        } else if (handleColDe.equalsIgnoreCase("sendListSelPro")) {
            sendListSelPro(request, response);
        } else if (handleColDe.equalsIgnoreCase("chooseProduct")) {
            chooseNewListProduct(request, response);
        } else if (handleColDe.equalsIgnoreCase("update")) {
            updateCollection(request, response);
        } else if (handleColDe.equalsIgnoreCase("delete")) {
            deleteCollection(request, response);
        }

    }

    public void viewCollectionDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Khởi tạo và lấy giá trị
        CollectionDAO cd = new CollectionDAO();
        String collectionId = request.getParameter("collectionId");
//try (PrintWriter out = response.getWriter()) {
//
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MktCollectionListServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet MktCollectionListServlet at " + collectionId + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        // Tìm kiếm Bộ sưu tập bởi Id
        Collection getCollection = cd.getById(Integer.parseInt(collectionId));

        String listSelectedProduct = getCollection.getCollectionProId();
        int newsId = getCollection.getNewsId();

        // Gửi dữ liệu
        request.setAttribute("collection", getCollection);
        request.setAttribute("listSelectedProduct", listSelectedProduct);
        request.setAttribute("newsId", newsId);

        request.getRequestDispatcher("m_collectiondetails.jsp").forward(request, response);

    }

    public void viewSelectedProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        Set<Integer> listSeleted = new HashSet<>();
        String collectionId = request.getParameter("collectionId");
        String newsId = request.getParameter("newsId");
        String listSelectedProduct = "";
        String oldListSelected = request.getParameter("listSelectedProduct");

        String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
        // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
        if (elements.length > 0 && elements[0].isEmpty()) {
            elements = Arrays.copyOfRange(elements, 1, elements.length);
        }

        listSelectedProduct = String.join(", ", elements);
        List<Product> listAllProduct = pd.getAll();
        List<Product> listProduct = new ArrayList<>();
        for (Product product : listAllProduct) {
            for (String element : elements) {
                if (product.getProId() == Integer.parseInt(element.trim())) {
                    listProduct.add(product);
                }
            }
        }

        // gửi dữ liệu
        request.setAttribute("typeCollection", "chooseProduct");
        request.setAttribute("handleColDe", "chooseProduct");
        request.setAttribute("newsId", newsId);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("collectionId", collectionId);
        request.setAttribute("listSelectedProduct", listSelectedProduct);
        request.getRequestDispatcher("m_handleselected.jsp").forward(request, response);
    }

    public void chooseNewSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String collectionId = request.getParameter("collectionId");
        String newsId = request.getParameter("newsId");
        String getListSelectedProduct = "";

        // Kiểm tra đầu vào 
        if (request.getParameter("listSelectedProduct") == null || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
            getListSelectedProduct = null;
        }
        String encodedList = "";
        if (getListSelectedProduct != null) {
            Set<Integer> listSelectedProduct = new HashSet<>();
            String oldListSelected = request.getParameter("listSelectedProduct");
            String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
            // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
            if (elements.length > 0 && elements[0].isEmpty()) {
                elements = Arrays.copyOfRange(elements, 1, elements.length);
            }
            //Thêm từng phần tử vào danh sách
            for (String element : elements) {
                listSelectedProduct.add(Integer.parseInt(element.trim()));
            }

            encodedList = URLEncoder.encode(listSelectedProduct.toString(), "UTF-8");
        }

        response.sendRedirect("mkt_sliderlist?cateNewsId=2&typeCollection=chooseSlider&newsId=" + newsId + "&listSelectedProduct=" + encodedList + "&collectionId=" + collectionId);
    }

    public void sendListSelPro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CollectionDAO cd = new CollectionDAO();
        String collectionId = request.getParameter("collectionId");
        String newsId = request.getParameter("newsId");
        String listSelectedProduct = request.getParameter("listSelectedProduct");

        Collection getCollection = cd.getById(Integer.parseInt(collectionId));
        request.setAttribute("collection", getCollection);
        request.setAttribute("newsId", newsId);
        request.setAttribute("listSelectedProduct", listSelectedProduct);

        request.getRequestDispatcher("m_collectiondetails.jsp").forward(request, response);
    }

    public void chooseNewListProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String collectionId = request.getParameter("collectionId");
        String newsId = request.getParameter("newsId");

        response.sendRedirect("mkt_productlist?typeCollection=chooseProduct&newsId=" + newsId + "&handleColDe=chooseProduct" + "&collectionId=" + collectionId);
    }

    public void updateCollection(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CollectionDAO cd = new CollectionDAO();
        String collectionId = request.getParameter("collectionId");
        String collectionTitle = request.getParameter("collectionTitle");
        String collectionStatus = request.getParameter("collectionStatus");
        String newsId = request.getParameter("newsId");
        String collectionProId;

        String oldListSelected = request.getParameter("listSelectedProduct");
        String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
        // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
        if (elements.length > 0 && elements[0].isEmpty()) {
            elements = Arrays.copyOfRange(elements, 1, elements.length);
        }
        collectionProId = String.join(", ", elements);
        Collection collection = cd.getById(Integer.parseInt(collectionId));
        collection.setCollectionTitle(collectionTitle);
        collection.setCollectionStatus(collectionStatus);
        collection.setNewsId(Integer.parseInt(newsId));
        collection.setCollectionProId(collectionProId);

        cd.update(collection);

        request.setAttribute("status", "success");
        request.setAttribute("collection", collection);
        request.setAttribute("newsId", collection.getNewsId());
        request.setAttribute("listSelectedProduct", collection.getCollectionProId());

        request.getRequestDispatcher("m_collectiondetails.jsp").forward(request, response);

    }

    public void deleteCollection(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CollectionDAO cd = new CollectionDAO();
        String collectionId = request.getParameter("collectionId");
        cd.delete(Integer.parseInt(collectionId));
        response.sendRedirect("mkt_collectionlist?statusPopAdd=successDel");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
