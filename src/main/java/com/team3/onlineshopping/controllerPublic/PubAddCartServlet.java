/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerPublic;

import com.team3.onlineshopping.dal.AddressDAO;
import com.team3.onlineshopping.dal.CartDAO;
import com.team3.onlineshopping.dal.CategorySizeDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.dal.ProductSizeDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Address;
import com.team3.onlineshopping.model.Cart;
import com.team3.onlineshopping.model.CategorySize;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.Product;
import com.team3.onlineshopping.model.ProductSize;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author PC
 */
public class PubAddCartServlet extends HttpServlet {

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
            out.println("<title>Servlet PubAddCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PubAddCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String proId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        String proSize = request.getParameter("size");
        String action = request.getParameter("action");
        Account acc = (Account) session.getAttribute("account");

        CustomerDAO cus_dao = new CustomerDAO();
        ProductSizeDAO proSize_dao = new ProductSizeDAO();
        CartDAO cd = new CartDAO();
        CategorySizeDAO csd = new CategorySizeDAO();
        AddressDAO addr_dao = new AddressDAO();
        ProductDAO pro_dao = new ProductDAO();

        Customer cus = cus_dao.getByAccountId(acc.getAccId());

        if (action.equals("addCart")) {
            if (proSize == null) {
                String proSizeError = "Vui lòng chọn kích thước sản phẩm";
                request.setAttribute("sizeError", proSizeError);
            } else if (quantity == null || quantity.isEmpty() || quantity.equals("")) {
                String proSizeError = "Vui lòng chọn số lượng sản phẩm";
                request.setAttribute("sizeError", proSizeError);
            } else {
                CategorySize s = csd.getByName(proSize);
                int productId = Integer.parseInt(proId);
                int productQuantity = Integer.parseInt(quantity);
                Cart t = new Cart(0, productId, cus.getCusId(), s.getCateSizeId(), productQuantity, "", "on");

                System.out.println("======================");
                System.out.println(proSize);
                System.out.println(cus.getCusId());
                System.out.println(s.getCateSizeId());
                System.out.println(productQuantity);

                ProductSize ps = proSize_dao.getByProCateSizeId(productId, s.getCateSizeId());
                if (ps.getProSizeQuantity() > 0) {
                    if (productQuantity > ps.getProSizeQuantity()) {
                        String proSizeError = "Số lượng còn lại không đủ";
                        request.setAttribute("sizeError", proSizeError);
                    } else {
                        //Check product exist in Cart
                        if (cd.getByProSizeId(productId, s.getCateSizeId(), cus.getCusId()) != null) {
                            System.out.println("chay vao update");
                            cd.updateQuantity(cd.getByProSizeId(productId, s.getCateSizeId(), cus.getCusId()), productQuantity);
                        } else {
                            System.out.println("chay vao day");
                            cd.add(t);
                        }
                        request.setAttribute("status", "success");
                    }
                } else {
                    String proSizeError = "Hiện tại đã hết hàng";
                    request.setAttribute("sizeError", proSizeError);
                }
            }
            request.setAttribute("productId", proId);
            request.getRequestDispatcher("pub_productdetails").forward(request, response);
        } else if (action.equals("pay")) {
            request.setAttribute("productId", proId);
            if (proSize == null) {
                String proSizeError = "Vui lòng chọn kích thước sản phẩm";
                request.setAttribute("sizeError", proSizeError);
                request.getRequestDispatcher("pub_productdetails").forward(request, response);
            } else if (quantity == null || quantity.isEmpty() || quantity.equals("")) {
                String proSizeError = "Vui lòng chọn số lượng sản phẩm";
                request.setAttribute("sizeError", proSizeError);
            } else {
                CategorySize s = csd.getByName(proSize);
                int productId = Integer.parseInt(proId);
                int productQuantity = Integer.parseInt(quantity);

                ProductSize ps = proSize_dao.getByProCateSizeId(productId, s.getCateSizeId());
                if (ps.getProSizeQuantity() > 0) {
                    if (productQuantity > ps.getProSizeQuantity()) {
                        String proSizeError = "Số lượng còn lại không đủ";
                        request.setAttribute("sizeError", proSizeError);
                    } else {
                        List<Address> cusAddress = addr_dao.getAllByCustomerId(cus.getCusId());
                        Product pro = pro_dao.getById(productId);

                        request.setAttribute("selectedValues", proId);
                        request.setAttribute("quantity", productQuantity);
                        request.setAttribute("cateSizeId", s.getCateSizeId());
                        request.setAttribute("products", pro);
                        request.setAttribute("totalPrice", pro.getProPrice() * productQuantity + 30000);
                        request.setAttribute("cusAddress", cusAddress);
                        request.setAttribute("buynow", "buynow");
                        request.getRequestDispatcher("cu_cartcontact.jsp").forward(request, response);
                    }
                } else {
                    String proSizeError = "Hiện tại đã hết hàng";
                    request.setAttribute("sizeError", proSizeError);
                }
            }
            request.setAttribute("productId", proId);
            request.getRequestDispatcher("pub_productdetails").forward(request, response);
        }
    }

}
