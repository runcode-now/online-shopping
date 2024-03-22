/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCommon;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.model.Account;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

@WebServlet(urlPatterns = {"/save"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)     // 50 MB
public class ComUpdateAvatarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComUpdateAvatarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComUpdateAvatarServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = (Part) request.getPart("avatar"); // "image" là tên của input file

        InputStream fileContent = filePart.getInputStream();
        String avatar = encodeImageToBase64(fileContent);

        AccountDAO acc_dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        acc.setAccAvarUrl("data:image/png;base64," + avatar);
        acc_dao.update(acc);
        request.setAttribute("account", acc);
        if (acc.getRoleId() == 4) {
            request.getRequestDispatcher("cu_editprofile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("c_editprofile.jsp").forward(request, response);
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
