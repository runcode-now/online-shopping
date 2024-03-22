//delete
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.CartDAO;
import com.team3.onlineshopping.dal.FeedbackDAO;
import com.team3.onlineshopping.dal.OrderDetailsDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.dal.ProductImageDAO;
import com.team3.onlineshopping.dal.ProductSizeDAO;
import com.team3.onlineshopping.model.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class MktDeleteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MktDeleteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktDeleteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        ProductImageDAO pid = new ProductImageDAO();
        ProductSizeDAO psd = new ProductSizeDAO();
        CartDAO cd = new CartDAO();
        OrderDetailsDAO od = new OrderDetailsDAO();
        FeedbackDAO fd = new FeedbackDAO();
        String getProductId = request.getParameter("productId");
        int productId;

        try {
            productId = Integer.parseInt(getProductId);

            if(cd.getByProId(productId) != null)
                cd.deleteByProId(productId);
            
            
            if(!od.getByProductId(productId).isEmpty())
                od.deleteByProId(productId);
            
            if(!fd.getByProId(productId).isEmpty())
                fd.deleteByProId(productId);
            
            pid.deleteByProId(productId);
            psd.deleteByProId(productId);
            pd.delete(productId);
            response.sendRedirect("mkt_productlist?statusPopDel=successDel");
        } catch (Exception e) {
        }
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

    }

}
