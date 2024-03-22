/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerMarketer;

import com.team3.onlineshopping.dal.CategoryProductDAO;
import com.team3.onlineshopping.model.CategoryProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import com.team3.onlineshopping.dal.CategoryProductDetailsDAO;
import com.team3.onlineshopping.dal.CategorySizeDAO;
import com.team3.onlineshopping.dal.EmployeeDAO;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.dal.ProductImageDAO;
import com.team3.onlineshopping.dal.ProductSizeDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.CategoryProductDetails;
import com.team3.onlineshopping.model.CategorySize;
import com.team3.onlineshopping.model.Employee;
import com.team3.onlineshopping.model.Product;
import com.team3.onlineshopping.model.ProductImage;
import com.team3.onlineshopping.model.ProductSize;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
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

public class MktAddProductServlet extends HttpServlet {

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
            out.println("<title>Servlet MktAddProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MktAddProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryProductDAO cd = new CategoryProductDAO();
        CategoryProductDetailsDAO cpdd = new CategoryProductDetailsDAO();
        CategorySizeDAO cs = new CategorySizeDAO();
        List<CategoryProduct> listCateProduct;
        listCateProduct = cd.getAll();
        int compareCateProId = 0;
        
        // check validate
        if (request.getParameter("errorInputPrice") != null) {
            String errorInputPrice = "Vui lòng nhập số!";
            request.setAttribute("errorInputPrice", errorInputPrice);
        } else if (request.getParameter("errorInputCost") != null) {
            String errorInputCost = "Vui lòng nhập số!";
            request.setAttribute("errorInputCost", errorInputCost);
        } else if (request.getParameter("errorInputSize") != null) {
            String errorInputSize = "Vui lòng nhập số!";
            request.setAttribute("errorInputSize", errorInputSize);
        }
        
        // check categories and set size
        if (request.getParameter("getCateProductId") != null) {
            compareCateProId = Integer.parseInt(request.getParameter("getCateProductId"));
            List<CategoryProductDetails> listCateProDetails = cpdd.getAllByCateProId(compareCateProId);
            if (compareCateProId >= 1 && compareCateProId <= 5) {
                String size = "fontsize";
                List<CategorySize> listProductSize = cs.getAllByCondition(size);
                request.setAttribute("listProductSize", listProductSize);
            } else if (compareCateProId == 6) {
                String size = "numbersize";
                List<CategorySize> listProductSize = cs.getAllByCondition(size);
                request.setAttribute("listProductSize", listProductSize);
            } else {
                String size = "other";
                List<CategorySize> listProductSize = cs.getAllByCondition(size);
                request.setAttribute("listProductSize", listProductSize);

            }
            
            // send data
            request.setAttribute("listCateProDetails", listCateProDetails);
            request.setAttribute("compareCateProId", compareCateProId);
            request.setAttribute("listCateProduct", listCateProduct);
            request.getRequestDispatcher("m_add-product.jsp").forward(request, response);
        } else {
            request.setAttribute("compareCateProId", compareCateProId);
            request.setAttribute("listCateProduct", listCateProduct);
            request.getRequestDispatcher("m_add-product.jsp").forward(request, response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initilize and get input
        ProductImageDAO pid = new ProductImageDAO();
        ProductDAO pd = new ProductDAO();
        ProductSizeDAO psd = new ProductSizeDAO();
        int proSold = 0, cateProDetailsId = 0;
        double proPrice = 0, proCost = 0, proRating = 0;
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        EmployeeDAO emp_dao = new EmployeeDAO();
        Employee emp = emp_dao.getByAccId(acc.getAccId());
        try {
            String getProName = request.getParameter("proName");
            String getProStatus = request.getParameter("proStatus");
            String getProDescription = request.getParameter("proDescription");
            String checkCateSize = request.getParameter("checkCateSize");
            String getCateProId = request.getParameter("cateProId");
            String cateProDetails = request.getParameter("cateProDetails");
            
            Part filePartD = (Part) request.getPart("proImgDefault");
            Part filePart1 = (Part) request.getPart("proImg1");
            Part filePart2 = (Part) request.getPart("proImg2");
            Part filePart3 = (Part) request.getPart("proImg3");
            
            getProDescription = getProDescription.replace("\n", "<br>");

            String getProImgDefault, getProImage1, getProImage2, getProImage3;

            String fmProImgDefault, fmProImage1, fmProImage2, fmProImage3;
            
            // get date
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String proCreateDate = currentDate.format(formatter);
            cateProDetailsId = Integer.parseInt(cateProDetails);
            String validate = "vali";
            String getProPrice = "null";
            String getProCost = "null";

            if (request.getParameter("proPrice") != null) {
                String getFormatNumProPrice = request.getParameter("proPrice");
                getProPrice = extractNumbers(getFormatNumProPrice);

            }
            if (request.getParameter("proCost") != null) {

                String getFormatNumProCost = request.getParameter("proCost");
                getProCost = extractNumbers(getFormatNumProCost);

            }

            String sendData = "&getProName=" + getProName + "&getProPrice=" + getProPrice + "&getProCost=" + getProCost + "&";
            if (validateTextInput(getProPrice) == false) {
                response.sendRedirect("mkt_addproduct?errorInputPrice=" + validate);

            }
            if (validateTextInput(getProCost) == false) {
                response.sendRedirect("mkt_addproduct?errorInputCost=" + validate);
            }
            proPrice = Double.parseDouble(getProPrice);
            proCost = Double.parseDouble(getProCost);

            
            // proImgDefault
            InputStream fileContentD = filePartD.getInputStream();
            getProImgDefault = encodeImageToBase64(fileContentD);
            fmProImgDefault = "data:image/png;base64," + getProImgDefault;
            // proImg1
            InputStream fileContent1 = filePart1.getInputStream();
            getProImage1 = encodeImageToBase64(fileContent1);
            fmProImage1 = "data:image/png;base64," + getProImage1;
            // proImg2
            InputStream fileContent2 = filePart2.getInputStream();
            getProImage2 = encodeImageToBase64(fileContent2);
            fmProImage2 = "data:image/png;base64," + getProImage2;
            // proImg3
            InputStream fileContent = filePart3.getInputStream();
            getProImage3 = encodeImageToBase64(fileContent);
            fmProImage3 = "data:image/png;base64," + getProImage3;

            if (checkCateSize.equalsIgnoreCase("fontsize")) {

                String getSizeS = request.getParameter("cateSizeId1");
                String getSizeM = request.getParameter("cateSizeId2");
                String getSizeL = request.getParameter("cateSizeId3");

                String getProSizeQuantityS = request.getParameter("proSizeQuantityS");
                String getProSizeQuantityM = request.getParameter("proSizeQuantityM");
                String getProSizeQuantityL = request.getParameter("proSizeQuantityL");
                int sizeS, sizeM, sizeL, proSizeQuantityS, proSizeQuantityM, proSizeQuantityL;
                // validate
                if (validateTextInputSize(getProSizeQuantityS) == false || validateTextInputSize(getProSizeQuantityM) == false || validateTextInputSize(getProSizeQuantityL) == false) {
                    response.sendRedirect("mkt_addproduct?errorInputSize=" + validate + "&");

                }
                // parseInt
                sizeS = Integer.parseInt(getSizeS);
                sizeM = Integer.parseInt(getSizeM);
                sizeL = Integer.parseInt(getSizeL);
                proSizeQuantityS = Integer.parseInt(getProSizeQuantityS);
                proSizeQuantityM = Integer.parseInt(getProSizeQuantityM);
                proSizeQuantityL = Integer.parseInt(getProSizeQuantityL);

                // convert to base 64 productdefault
                // Img Default
                // "image" là tên của input file
                Product newProduct = new Product(getProName, fmProImgDefault, proPrice, proCost, getProDescription, proRating, proSold, proCreateDate, getProStatus, emp.getEmId(), cateProDetailsId);
                pd.add(newProduct);
                // about product image
                int getInsertIdNewest = pd.getInsertIdNewest(); // dùng cho cả proImg và proSize
                // convert to base 64 productimage
                // proImg1
                ProductImage newProImgById1 = new ProductImage(fmProImage1, getInsertIdNewest);

                pid.add(newProImgById1);
                // proImg2
                ProductImage newProImgById2 = new ProductImage(fmProImage2, getInsertIdNewest);

                pid.add(newProImgById2);

                // proImg3
                ProductImage newProImgById3 = new ProductImage(fmProImage3, getInsertIdNewest);

                pid.add(newProImgById3);

                // about product size
                ProductSize newProductSizeByIdS = new ProductSize(getInsertIdNewest, sizeS, proSizeQuantityS);
                ProductSize newProductSizeByIdM = new ProductSize(getInsertIdNewest, sizeM, proSizeQuantityM);
                ProductSize newProductSizeByIdL = new ProductSize(getInsertIdNewest, sizeL, proSizeQuantityL);
                psd.add(newProductSizeByIdS);
                psd.add(newProductSizeByIdM);
                psd.add(newProductSizeByIdL);

            } else if (checkCateSize.equalsIgnoreCase("sizenumber")) {

                String getSize36 = request.getParameter("cateSizeId4");
                String getSize37 = request.getParameter("cateSizeId5");
                String getSize38 = request.getParameter("cateSizeId6");
                String getSize39 = request.getParameter("cateSizeId7");
                String getSize40 = request.getParameter("cateSizeId8");
                String getSize41 = request.getParameter("cateSizeId9");
                String getSize42 = request.getParameter("cateSizeId10");

                String getProSizeQuantity36 = request.getParameter("proSizeQuantity36");
                String getProSizeQuantity37 = request.getParameter("proSizeQuantity37");
                String getProSizeQuantity38 = request.getParameter("proSizeQuantity38");
                String getProSizeQuantity39 = request.getParameter("proSizeQuantity39");
                String getProSizeQuantity40 = request.getParameter("proSizeQuantity40");
                String getProSizeQuantity41 = request.getParameter("proSizeQuantity41");
                String getProSizeQuantity42 = request.getParameter("proSizeQuantity42");
                int size36, size37, size38, size39, size40, size41, size42, proSizeQuantity36, proSizeQuantity37, proSizeQuantity38, proSizeQuantity39, proSizeQuantity40, proSizeQuantity41, proSizeQuantity42;

                // validate
                if (validateTextInputSize(getProSizeQuantity37) == false || validateTextInputSize(getProSizeQuantity36) == false || validateTextInputSize(getProSizeQuantity38) == false
                        || validateTextInputSize(getProSizeQuantity39) == false || validateTextInputSize(getProSizeQuantity40) == false || validateTextInputSize(getProSizeQuantity41) == false
                        || validateTextInputSize(getProSizeQuantity42) == false) {
                    response.sendRedirect("mkt_addproduct?errorInputSize=" + validate);
                }
                // parseInt
                size36 = Integer.parseInt(getSize36);
                size37 = Integer.parseInt(getSize37);
                size38 = Integer.parseInt(getSize38);
                size39 = Integer.parseInt(getSize39);
                size40 = Integer.parseInt(getSize40);
                size41 = Integer.parseInt(getSize41);
                size42 = Integer.parseInt(getSize42);

                proSizeQuantity36 = Integer.parseInt(getProSizeQuantity36);
                proSizeQuantity37 = Integer.parseInt(getProSizeQuantity37);
                proSizeQuantity38 = Integer.parseInt(getProSizeQuantity38);
                proSizeQuantity39 = Integer.parseInt(getProSizeQuantity39);
                proSizeQuantity40 = Integer.parseInt(getProSizeQuantity40);
                proSizeQuantity41 = Integer.parseInt(getProSizeQuantity41);
                proSizeQuantity42 = Integer.parseInt(getProSizeQuantity42);

                // about product
                // convert to base 64 productdefault
                // Img Default
                Product newProduct = new Product(getProName, fmProImgDefault, proPrice, proCost, getProDescription, proRating, proSold, proCreateDate, getProStatus, 2, cateProDetailsId);

                pd.add(newProduct);
                // about product image
                int getInsertIdNewest = pd.getInsertIdNewest(); // dùng cho cả proImg và proSize
                // convert to base 64 productimage
                // proImg1
                ProductImage newProImgById1 = new ProductImage(fmProImage1, getInsertIdNewest);

                pid.add(newProImgById1);
                // proImg2
                ProductImage newProImgById2 = new ProductImage(fmProImage2, getInsertIdNewest);

                pid.add(newProImgById2);

                // proImg3
                ProductImage newProImgById3 = new ProductImage(fmProImage3, getInsertIdNewest);

                pid.add(newProImgById3);

                // about product size
                ProductSize newProductSizeById36 = new ProductSize(getInsertIdNewest, size36, proSizeQuantity36);
                ProductSize newProductSizeById37 = new ProductSize(getInsertIdNewest, size37, proSizeQuantity37);
                ProductSize newProductSizeById38 = new ProductSize(getInsertIdNewest, size38, proSizeQuantity38);
                ProductSize newProductSizeById39 = new ProductSize(getInsertIdNewest, size39, proSizeQuantity39);
                ProductSize newProductSizeById40 = new ProductSize(getInsertIdNewest, size40, proSizeQuantity40);
                ProductSize newProductSizeById41 = new ProductSize(getInsertIdNewest, size41, proSizeQuantity41);
                ProductSize newProductSizeById42 = new ProductSize(getInsertIdNewest, size42, proSizeQuantity42);

                psd.add(newProductSizeById36);
                psd.add(newProductSizeById37);
                psd.add(newProductSizeById38);
                psd.add(newProductSizeById39);
                psd.add(newProductSizeById40);
                psd.add(newProductSizeById41);
                psd.add(newProductSizeById42);

            } else if (checkCateSize.equalsIgnoreCase("none")) {
                String getSizeOver = request.getParameter("cateSizeId11");

                String getProSizeQuantityOver = request.getParameter("proSizeQuantityover");
                int sizeOver, proSizeQuantityOver;

                

                sizeOver = Integer.parseInt(getSizeOver);
                proSizeQuantityOver = Integer.parseInt(getProSizeQuantityOver);

                // about product
                // convert to base 64 productdefault
                // Img Default
                Product newProduct = new Product(getProName, fmProImgDefault, proPrice, proCost, getProDescription, proRating, proSold, proCreateDate, getProStatus, 2, cateProDetailsId);

                pd.add(newProduct);

                // about product image
                int getInsertIdNewest = pd.getInsertIdNewest(); // dùng cho cả proImg và proSize
                // convert to base 64 productimage
                // proImg1
                ProductImage newProImgById1 = new ProductImage(fmProImage1, getInsertIdNewest);

                pid.add(newProImgById1);
                // proImg2

                ProductImage newProImgById2 = new ProductImage(fmProImage2, getInsertIdNewest);

                pid.add(newProImgById2);

                // proImg3
                ProductImage newProImgById3 = new ProductImage(fmProImage3, getInsertIdNewest);

                pid.add(newProImgById3);

                ProductSize newProductSizeOver = new ProductSize(getInsertIdNewest, sizeOver, proSizeQuantityOver);

                psd.add(newProductSizeOver);
            }

            response.sendRedirect("mkt_productlist?statusPopAdd=successAdd");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // function validate input number
    private boolean validateTextInput(String value) {
        String regex = "^[0-9.,]+$"; // Chỉ chấp nhận các ký tự số
        if (!value.matches(regex)) {
            return false;
        }

        return true;
    }

    private boolean validateTextInputSize(String value) {
        String regex = "^[0-9]+$"; // Chỉ chấp nhận các ký tự số
        if (!value.matches(regex)) {
            return false;
        }

        return true;
    }

    // function convert url to base 64
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

    private String extractNumbers(String input) {
        // Sử dụng biểu thức chính quy để loại bỏ các ký tự không phải số
        String numberOnly = input.replaceAll("[^a-zA-Z0-9]", "");
        return numberOnly;
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
