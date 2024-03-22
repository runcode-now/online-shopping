// mkproductlist
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.CategoryProductDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.CategoryProduct;
import com.team3.onlineshopping.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MktProductListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktProductList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktProductList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // Xử lý việc hiển thị danh sách và chọn sản phẩm của bộ sưu tập
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Khởi tạo và lấy dữ liệu
        ProductDAO pd = new ProductDAO();
        CategoryProductDAO cd = new CategoryProductDAO();
        List<Product> listProduct;
        List<CategoryProduct> listCateProduct;
        int numOfProduct, indexPage, endPage, startNumOr, cateProId;
        String index = request.getParameter("numberPage");
        String paging = request.getParameter("paging");
        String proStatus = request.getParameter("proStatus");
        String sortByRank = request.getParameter("sortByRank");
        String check = "";
        String cateProIdd = request.getParameter("cateProId");
        String searching = request.getParameter("searching");

        // Xử lý bộ sưu tập bắt đầu
        // Nếu người dùng ấn vào xem chi tiết
        if (request.getParameter("productid") != null) {
            checkViewDetail(request, response);
        }

        // Kiểm tra typeCollection và xử lý việc chọn sản phẩm
        if (request.getParameter("typeCollection") != null) {
            checkTypeCollection(request, response);
            // Xử lý nếu người dùng update sản phẩm
            String handleColDe = "";
            String collectionId = "";

            if (request.getParameter("handleColDe") == null || request.getParameter("handleColDe").equalsIgnoreCase("")) {
                handleColDe = "null";
            } else {
                handleColDe = request.getParameter("handleColDe");

            }
            if (request.getParameter("collectionId") == null || request.getParameter("collectionId").equalsIgnoreCase("")) {
                collectionId = "null";
            } else {
                collectionId = request.getParameter("collectionId");

            }
            request.setAttribute("handleColDe", handleColDe);
            request.setAttribute("collectionId", collectionId);
            if (request.getParameter("error") != null) {
                request.setAttribute("error", "Vui lòng chọn sản phẩm!");
            }
        }

//        try (PrintWriter out = response.getWriter()) {
//
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MktProductList</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet MktProductList at " + handleColDe + "||" + collectionId + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        // Xử lý bộ sưu tập bắt đầu kết thúc
        // Xử lý hiển thị sản phẩm
        String statusPopDel = request.getParameter("statusPopDel");
        String statusPopAdd = request.getParameter("statusPopAdd");

        // kiểm tra đầu vào
        if (check.equalsIgnoreCase(cateProIdd)) {
            cateProIdd = null;
        }
        if (check.equalsIgnoreCase(proStatus)) {
            proStatus = null;
        }
        if (check.equalsIgnoreCase(searching)) {
            searching = null;
        }

        if (index == null) {
            index = "1";
            paging = "-1";
        }

        // Nếu người dùng filter hoặc search
        try {
            if (cateProIdd != null || proStatus != null || sortByRank != null || searching != null) {
                // lấy id thể loại sản phẩm
                if (cateProIdd == null) {
                    cateProId = -1;
                } else {
                    cateProId = Integer.parseInt(cateProIdd);

                }
                CategoryProduct cp;
                cp = cd.getById(cateProId);

                // phân trang
                indexPage = Integer.parseInt(index);
                numOfProduct = pd.filterProductMkt(proStatus, cateProId, sortByRank, searching).size();
                endPage = numOfProduct / 16;

                if (numOfProduct % 16 != 0) {
                    endPage++;
                }
                startNumOr = (indexPage - 1) * 16;
                listCateProduct = cd.getAll();
                listProduct = pd.filterProductMktPaging(proStatus, cateProId, sortByRank, searching, indexPage);

                // thông báo tìm kiếm
                if (searching != null) {
                    if (listProduct.isEmpty()) {
                        request.setAttribute("msgNotFound", "Không có sản phẩm này!");
                    } else {
                        request.setAttribute("msgFound", "Những sản phẩm liên quan: " + searching + "");
                    }
                }
                // gửi dữ liệu
                request.setAttribute("proStatus", proStatus);
                request.setAttribute("sortByRank", sortByRank);
                request.setAttribute("searching", searching);
                request.setAttribute("listProduct", listProduct);
                request.setAttribute("numberPage", indexPage);
                request.setAttribute("paging", paging);
                request.setAttribute("listCateProduct", listCateProduct);
                request.setAttribute("startNumOr", startNumOr);
                request.setAttribute("cateProId", cateProIdd);
                request.setAttribute("categoryProduct", cp);
                request.setAttribute("endPage", endPage);
                request.getRequestDispatcher("m_product-list.jsp").forward(request, response);

            } // Chỉ hiển thị danh sách sản phẩm 
            else {

                // phân trang
                indexPage = Integer.parseInt(index);
                numOfProduct = pd.getAll().size();
                startNumOr = (indexPage - 1) * 16;
                endPage = numOfProduct / 16;
                if (numOfProduct % 16 != 0) {
                    endPage++;
                }
                listCateProduct = cd.getAll();
                listProduct = pd.getAllProductPaging(indexPage);

                // thông báo sau khi thay thêm hoặc xóa
                if (statusPopDel != null) {
                    request.setAttribute("status", statusPopDel);
                } else if (statusPopAdd != null) {
                    request.setAttribute("status", statusPopAdd);
                }
                // gửi dữ liệu
                request.setAttribute("listProduct", listProduct);
                request.setAttribute("numberPage", indexPage);
                request.setAttribute("paging", paging);
                request.setAttribute("listCateProduct", listCateProduct);
                request.setAttribute("startNumOr", startNumOr);
                request.setAttribute("endPage", endPage);
                request.getRequestDispatcher("m_product-list.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // Xử lý sau khi chọn sản phẩm bên Collection
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Khởi tạo và lấy dữ liệu
        ProductDAO pd = new ProductDAO();
        String typeCollection = request.getParameter("typeCollection");
        String collectionTitle = request.getParameter("collectionTitle");
        String collectionStatus = request.getParameter("collectionStatus");
        String newsId = request.getParameter("newsId");
        String getSelList = request.getParameter("subListSelPro");
        String deletePro = request.getParameter("deleteSelectedPro");
        String getListSelectedProduct = "";
        String getSelectedProduct = "";
        Set<Integer> newList = new HashSet<>();

        if (request.getParameter("listSelectedProduct") == null || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
            getListSelectedProduct = null;
        }

        if (request.getParameter("selectedProduct") == null || request.getParameter("selectedProduct").equalsIgnoreCase("")) {
            getSelectedProduct = null;
        }

        // Xử lý nếu người dùng update sản phẩm
        String handleColDe = "";
        String collectionId = "";

        if (request.getParameter("handleColDe") == null || request.getParameter("handleColDe").equalsIgnoreCase("")) {
            handleColDe = "null";
        } else {
            handleColDe = request.getParameter("handleColDe");

        }
        if (request.getParameter("collectionId") == null || request.getParameter("collectionId").equalsIgnoreCase("")) {
            collectionId = "null";
        } else {
            collectionId = request.getParameter("collectionId");

        }
        request.setAttribute("handleColDe", handleColDe);
        request.setAttribute("collectionId", collectionId);
//        try (PrintWriter out = response.getWriter()) {
//
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MktProductList</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet MktProductList at " + deletePro + "||" + request.getParameter("listSelectedProduct") + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        // Sử lý người người dùng Thêm sản phẩm
        if (getSelList != null) {
            newList = checkListPro(request, response);
        }

        // Sử lý nếu người dùng xóa sản phẩm
        if (deletePro != null) {
            String oldListSelected = request.getParameter("listSelectedProduct");
            String productIdSel = request.getParameter("productIdSel");
            String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");

            // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
            if (elements.length > 0 && elements[0].isEmpty()) {
                elements = Arrays.copyOfRange(elements, 1, elements.length);
            }
            //Thêm từng phần tử vào danh sách
            for (String element : elements) {
                newList.add(Integer.parseInt(element.trim()));
            }
            Set<Integer> updatedSet = new HashSet<>();
            for (Integer element : newList) {
                if (element != Integer.parseInt(productIdSel)) {
                    updatedSet.add(element);
                }
            }
            newList = updatedSet;

            request.setAttribute("status", "success");
        }

        // So sánh tất cả sản phẩm với sản phẩm đã có để lấy ra danh sách cho người dùng
        List<Product> listProduct = new ArrayList<>();

        if (!newList.isEmpty()) {
            List<Product> listAllProduct = pd.getAll();
            for (Product product : listAllProduct) {
                for (Integer listPro : newList) {
                    if (product.getProId() == listPro) {
                        listProduct.add(product);
                    }
                }
            }
        } else {
            newList = null;
        }
        // gửi dữ liệu
        request.setAttribute("typeCollection", typeCollection);
        request.setAttribute("collectionTitle", collectionTitle);
        request.setAttribute("collectionStatus", collectionStatus);
        request.setAttribute("newsId", newsId);

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listSelectedProduct", newList);
        if (getListSelectedProduct != null || getSelectedProduct != null) {
            request.getRequestDispatcher("m_handleselected.jsp").forward(request, response);

        }

    }

    // Hàm xử lý listProduct
    public Set<Integer> checkListPro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Khởi tạo và lấy dữ liệu

        String typeCollection = request.getParameter("typeCollection");
        String collectionTitle = request.getParameter("collectionTitle");
        String collectionStatus = request.getParameter("collectionStatus");
        String newsId = request.getParameter("newsId");
        Set<Integer> newList = new HashSet<>();
        String getListSelectedProduct = "";
        String getSelectedProduct = "";
        String check = "[]";

        // Kiểm tra đầu vào 
        if (request.getParameter("listSelectedProduct") == null || check.equalsIgnoreCase(request.getParameter("listSelectedProduct")) || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
            getListSelectedProduct = null;
        }

        if (request.getParameter("selectedProduct") == null || request.getParameter("selectedProduct").equalsIgnoreCase("")) {
            getSelectedProduct = null;
        }

        if (getListSelectedProduct == null && getSelectedProduct == null) {

            List<String> listSelectedProduct = null;
            request.setAttribute("typeCollection", typeCollection);
            request.setAttribute("collectionTitle", collectionTitle);
            request.setAttribute("collectionStatus", collectionStatus);
            request.setAttribute("newsId", newsId);
            String error = "err";
            // Xử lý nếu người dùng update sản phẩm
            String handleColDe = "";
            String collectionId = "";

            if (request.getParameter("handleColDe") == null || request.getParameter("handleColDe").equalsIgnoreCase("")) {
                handleColDe = null;
            } else {
                handleColDe = request.getParameter("handleColDe");

            }
            if (request.getParameter("collectionId") == null || request.getParameter("collectionId").equalsIgnoreCase("")) {
                collectionId = null;
            } else {
                collectionId = request.getParameter("collectionId");

            }

            response.sendRedirect("mkt_productlist?collectionTitle=" + collectionTitle + "&collectionStatus=" + collectionStatus + "&typeCollection=" + typeCollection + "&newsId=" + newsId + "&error=" + error + "listSelectedProduct=" + listSelectedProduct + "&handleColDe=" + handleColDe + "&collectionId=" + collectionId);
        } else if (getListSelectedProduct != null) {
            String oldListSelected = request.getParameter("listSelectedProduct");
            String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
            // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
            if (elements.length > 0 && elements[0].isEmpty()) {
                elements = Arrays.copyOfRange(elements, 1, elements.length);
            }
            //Thêm từng phần tử vào danh sách
            for (String element : elements) {
                newList.add(Integer.parseInt(element.trim()));
            }

            if (getSelectedProduct != null) {
                String[] selectedProduct = request.getParameterValues("selectedProduct");

                for (String option : selectedProduct) {
                    String[] parts = option.split(",");
                    for (String part : parts) {
                        newList.add(Integer.parseInt(part.trim()));
                    }

                }
            }
        } else if (getSelectedProduct != null) {
            String[] selectedProduct = request.getParameterValues("selectedProduct");

            for (String option : selectedProduct) {
                String[] parts = option.split(",");
                for (String part : parts) {
                    newList.add(Integer.parseInt(part.trim()));
                }

            }
        }
        return newList;
    }

    // Hàm xử lý thể loại Colleciton
    public void checkTypeCollection(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String getListSelectedProduct = "";
        String getSelectedProduct = "";

        if (request.getParameter("listSelectedProduct") == null || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
            getListSelectedProduct = null;
        }

        if (request.getParameter("selectedProduct") == null || request.getParameter("selectedProduct").equalsIgnoreCase("")) {
            getSelectedProduct = null;
        }
        request.setAttribute("typeCollection", request.getParameter("typeCollection"));
        request.setAttribute("collectionTitle", request.getParameter("collectionTitle"));
        request.setAttribute("collectionStatus", request.getParameter("collectionStatus"));
        String getViewProDe = "";
        request.setAttribute("newsId", request.getParameter("newsId"));
        if (getViewProDe.equalsIgnoreCase("") || getViewProDe == null) {
            getViewProDe = null;
        }

        if (getListSelectedProduct != null && getViewProDe != null) {
            if (getSelectedProduct != null) {
                String[] selectedOption = request.getParameterValues("selectedProduct");
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
                // Loại bỏ các phần tử trùng lặp từ selectedOption
                for (String option : selectedOption) {
                    listSelectedProduct.add(Integer.parseInt(option.trim()));
                }

                request.setAttribute("listSelectedProduct", listSelectedProduct);
            } else {
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
        } // Nếu listSelectedProduct không tồn tại
        else if (getSelectedProduct != null && getListSelectedProduct == null) {
            String[] selectedOption = request.getParameterValues("selectedProduct");
            Set<Integer> listSelectedProduct = new HashSet<>();
            for (String option : selectedOption) {
                listSelectedProduct.add(Integer.parseInt(option.trim()));

            }
            request.setAttribute("listSelectedProduct", listSelectedProduct);
        } else if (getListSelectedProduct != null) {
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

            if (getSelectedProduct != null) {
                String[] selectedProduct = request.getParameterValues("selectedProduct");

                for (String option : selectedProduct) {
                    String[] parts = option.split(",");
                    for (String part : parts) {
                        listSelectedProduct.add(Integer.parseInt(part.trim()));
                    }

                }
            }
            request.setAttribute("listSelectedProduct", listSelectedProduct);
        }
    }

    // hàm kiểm tra nếu người dùng vào xem chi tiết sản phẩm
    public void checkViewDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String check = "";
        String cateProIdd = request.getParameter("cateProId");
        String searching = request.getParameter("searching");

        // Xử lý nếu người dùng update sản phẩm
        String handleColDe = "";
        String collectionId = "";

        if (request.getParameter("handleColDe") == null || request.getParameter("handleColDe").equalsIgnoreCase("")) {
            handleColDe = "null";
        } else {
            handleColDe = request.getParameter("handleColDe");

        }
        if (request.getParameter("collectionId") == null || request.getParameter("collectionId").equalsIgnoreCase("")) {
            collectionId = "null";
        } else {
            collectionId = request.getParameter("collectionId");

        }

        String getListSelectedProduct = "";
        String getSelectedProduct = "";

        if (request.getParameter("listSelectedProduct") == null || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
            getListSelectedProduct = null;
        }

        if (request.getParameter("selectedProduct") == null || request.getParameter("selectedProduct").equalsIgnoreCase("")) {
            getSelectedProduct = null;
        }
        String productid = request.getParameter("productid");
        String typeCollection = request.getParameter("typeCollection");
        String collectionTitle = request.getParameter("collectionTitle");
        String collectionStatus = request.getParameter("collectionStatus");
        String newsId = request.getParameter("newsId");

        String encodedList = "";

        if (getSelectedProduct != null && getListSelectedProduct == null) {
            String[] selectedOption = request.getParameterValues("selectedProduct");
            Set<Integer> listSelectedProduct = new HashSet<>();
            for (String option : selectedOption) {
                String[] parts = option.split(",");
                for (String part : parts) {
                    listSelectedProduct.add(Integer.parseInt(part.trim()));
                }
            }
            encodedList = URLEncoder.encode(Arrays.toString(listSelectedProduct.toArray()), "UTF-8");
            response.sendRedirect("mkt_productdetail?productid=" + productid + "&typeCollection=" + typeCollection + "&collectionTitle=" + collectionTitle + "&collectionStatus=" + collectionStatus + "&newsId=" + newsId + "&listSelectedProduct=" + encodedList + "&handleColDe=" + handleColDe + "&collectionId=" + collectionId);

        } else if (getListSelectedProduct != null) {
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

            if (getSelectedProduct != null) {
                String[] selectedProduct = request.getParameterValues("selectedProduct");

                for (String option : selectedProduct) {
                    String[] parts = option.split(",");
                    for (String part : parts) {
                        listSelectedProduct.add(Integer.parseInt(part.trim()));
                    }

                }
            }

            encodedList = URLEncoder.encode(listSelectedProduct.toString(), "UTF-8");
            response.sendRedirect("mkt_productdetail?productid=" + productid + "&typeCollection=" + typeCollection + "&collectionTitle=" + collectionTitle + "&collectionStatus=" + collectionStatus + "&newsId=" + newsId + "&listSelectedProduct=" + encodedList + "&handleColDe=" + handleColDe + "&collectionId=" + collectionId);

        } else {
            response.sendRedirect("mkt_productdetail?productid=" + productid + "&typeCollection=" + typeCollection + "&collectionTitle=" + collectionTitle + "&collectionStatus=" + collectionStatus + "&newsId=" + newsId + "&handleColDe=" + handleColDe + "&collectionId=" + collectionId);

        }
    }

}
