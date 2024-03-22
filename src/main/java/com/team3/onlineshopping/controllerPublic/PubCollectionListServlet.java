//collectionlist
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.CollectionDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.model.Collection;
import com.team3.onlineshopping.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PubCollectionListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PubCollectionListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PubCollectionListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CollectionDAO cd = new CollectionDAO();
        ProductDAO pd = new ProductDAO();
        String getCollectionId = request.getParameter("collectionId");
        Collection getCollection = cd.getById(Integer.parseInt(getCollectionId));
        String[] elements = getCollection.getCollectionProId().replaceAll("[^0-9,]", "").split(",");
        // Loại bỏ dấu phẩy ở đầu chuỗi nếu có 
        if (elements.length > 0 && elements[0].isEmpty()) {
            elements = Arrays.copyOfRange(elements, 1, elements.length);
        }

        List<Product> listAllProduct = pd.getAll("on");
        List<Product> listProduct = new ArrayList<>();
        for (Product product : listAllProduct) {
            for (String element : elements) {
                if (product.getProId() == Integer.parseInt(element.trim())) {
                    listProduct.add(product);
                }
            }
        }

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("collectionId", getCollectionId);
        

        request.setAttribute("collectionTitle", getCollection.getCollectionTitle());

        request.getRequestDispatcher("p_collectionlist.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
