//ServletaddCOl

package com.team3.onlineshopping.controllerMarketer;
import com.team3.onlineshopping.dal.CollectionDAO;
import com.team3.onlineshopping.dal.EmployeeDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Collection;
import com.team3.onlineshopping.model.Employee;
import com.team3.onlineshopping.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class MktAddCollectionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktAddCollectionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktAddCollectionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String getListSelectedProduct = "";


        if (request.getParameter("listSelectedProduct") == null || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
            getListSelectedProduct = null;
        }


        if (request.getParameter("newsId") != null) {
            request.setAttribute("newsId", request.getParameter("newsId"));
        }
        if (request.getParameter("collectionTitle") != null) {
            request.setAttribute("collectionTitle", request.getParameter("collectionTitle"));
        }
        if (request.getParameter("collectionStatus") != null) {
            request.setAttribute("collectionStatus", request.getParameter("collectionStatus"));
        }
        if (getListSelectedProduct != null) {
            request.setAttribute("listSelectedProduct", request.getParameter("listSelectedProduct"));

        }

        request.getRequestDispatcher("m_add-collection.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle slider or product selection
        ProductDAO pd = new ProductDAO();
        CollectionDAO cd = new CollectionDAO();
        String typeCollection = request.getParameter("typeCollection");
        String collectionTitle = request.getParameter("collectionTitle");
        String collectionStatus = request.getParameter("collectionStatus");
        String newsId = request.getParameter("newsId");
        String getCollectionTitle = "";
        String getListSelectedProduct = "";
        String getNewsId = "";
        String collectionId = "";

        // Kiểm tra đầu vào 
        if (request.getParameter("collectionTitle") == null || request.getParameter("collectionTitle").equalsIgnoreCase("")) {
            getCollectionTitle = null;
        }
        if (request.getParameter("listSelectedProduct") == null || request.getParameter("listSelectedProduct").equalsIgnoreCase("")) {
            getListSelectedProduct = null;
        }
        if (newsId == null || newsId.equalsIgnoreCase("")) {
            getNewsId = null;
        }

        if (typeCollection.equalsIgnoreCase("chooseSlider")) {

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

            response.sendRedirect("mkt_sliderlist?cateNewsId=2&typeCollection=" + typeCollection + "&collectionTitle=" + collectionTitle + "&collectionStatus=" + collectionStatus + "&newsId=" + newsId + "&listSelectedProduct=" + encodedList + "&collectionId=" + collectionId);
        } else if (typeCollection.equalsIgnoreCase("chooseProduct")) {
            response.sendRedirect("mkt_productlist?collectionTitle=" + collectionTitle + "&collectionStatus=" + collectionStatus + "&typeCollection=" + typeCollection + "&newsId=" + newsId + "&collectionId=" + collectionId);
        } else if (typeCollection.equalsIgnoreCase("sendListSelPro")) {

            Set<Integer> listSeleted = new HashSet<>();
            String oldListSelected = request.getParameter("listSelectedProduct");

            String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
            // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
            if (elements.length > 0 && elements[0].isEmpty()) {
                elements = Arrays.copyOfRange(elements, 1, elements.length);
            }
            //Thêm từng phần tử vào danh sách
            for (String element : elements) {
                listSeleted.add(Integer.parseInt(element.trim()));
            }
            request.setAttribute("typeCollection", typeCollection);
            request.setAttribute("collectionTitle", collectionTitle);
            request.setAttribute("collectionStatus", collectionStatus);
            request.setAttribute("newsId", newsId);

            request.setAttribute("listSelectedProduct", listSeleted);

            request.getRequestDispatcher("m_add-collection.jsp").forward(request, response);
        } else if (typeCollection.equalsIgnoreCase("reviewListSel")) {
            collectionId = "null";
            Set<Integer> listSeleted = new HashSet<>();
            String oldListSelected = request.getParameter("listSelectedProduct");

            String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
            // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
            if (elements.length > 0 && elements[0].isEmpty()) {
                elements = Arrays.copyOfRange(elements, 1, elements.length);
            }
            //Thêm từng phần tử vào danh sách
            for (String element : elements) {
                listSeleted.add(Integer.parseInt(element.trim()));
            }
            List<Product> listAllProduct = pd.getAll();
            List<Product> listProduct = new ArrayList<>();
            for (Product product : listAllProduct) {
                for (Integer listPro : listSeleted) {
                    if (product.getProId() == listPro) {
                        listProduct.add(product);
                    }
                }
            }

            // gửi dữ liệu
            request.setAttribute("typeCollection", "chooseProduct");
            request.setAttribute("collectionTitle", collectionTitle);
            request.setAttribute("collectionStatus", collectionStatus);
            request.setAttribute("newsId", newsId);
            request.setAttribute("collectionId", collectionId);

            request.setAttribute("listProduct", listProduct);
            request.setAttribute("listSelectedProduct", listSeleted);
            request.getRequestDispatcher("m_handleselected.jsp").forward(request, response);

        } else if (typeCollection.equalsIgnoreCase("submitAdd")) {
            if (getNewsId == null || getListSelectedProduct == null || getCollectionTitle == null) {
                Set<Integer> listSeleted = new HashSet<>();
                if (getListSelectedProduct != null) {
                    String oldListSelected = request.getParameter("listSelectedProduct");

                    String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
                    // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
                    if (elements.length > 0 && elements[0].isEmpty()) {
                        elements = Arrays.copyOfRange(elements, 1, elements.length);
                    }
                    //Thêm từng phần tử vào danh sách
                    for (String element : elements) {
                        listSeleted.add(Integer.parseInt(element.trim()));
                    }
                }
                if (listSeleted.isEmpty()) {
                    listSeleted = null;
                }
//                request.setAttribute("collectionTitle", collectionTitle);
                request.setAttribute("collectionStatus", collectionStatus);
                request.setAttribute("newsId", newsId);
                request.setAttribute("listSelectedProduct", listSeleted);
                if (getNewsId == null) {
                    request.setAttribute("errorSlider", "Vui lòng thêm Slider!");
                } else if (getListSelectedProduct == null) {
                    request.setAttribute("errorProduct", "Vui lòng thêm sản phẩm!");
                } else {
                    request.setAttribute("errorTitle", "Vui lòng nhập tên bộ sưu tập!");

                }

                request.getRequestDispatcher("m_add-collection.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("account");
                EmployeeDAO emp_dao = new EmployeeDAO();
                Employee emp = emp_dao.getByAccId(acc.getAccId());
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String collectionCreateDate = currentDate.format(formatter);
                String collectionProId = "";
                Set<Integer> listSeleted = new HashSet<>();
                if (getListSelectedProduct != null) {
                    String oldListSelected = request.getParameter("listSelectedProduct");

                    String[] elements = oldListSelected.replaceAll("[^0-9,]", "").split(",");
                    // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
                    if (elements.length > 0 && elements[0].isEmpty()) {
                        elements = Arrays.copyOfRange(elements, 1, elements.length);
                    }
                    collectionProId = String.join(", ", elements);

                }
                Collection newCollection = new Collection(1, collectionTitle, collectionProId, collectionCreateDate, collectionStatus, Integer.parseInt(newsId), emp.getEmId());
                cd.add(newCollection);
                response.sendRedirect("mkt_collectionlist?statusPopAdd=successAdd");
            }
        }

    }

    // function check if user input string empty
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
