/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCommon;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.information.Email;
import com.team3.onlineshopping.information.HtmlContent;
import com.team3.onlineshopping.information.Token;
import com.team3.onlineshopping.model.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ComResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("c_resetpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO acc_dao = new AccountDAO();
        
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        email = (email == null) ? "" : email;

        Account acc = acc_dao.getByEmailStatus(email, "on");
        if (acc == null || email.isEmpty()) {
            request.setAttribute("emailErr", "Địa chỉ email không tồn tại! ");
            request.getRequestDispatcher("c_resetpassword.jsp").forward(request, response);
        } 
        
        String token = Token.generateToken(acc.getAccId(), acc.getAccEmail(), 1);

        // Create content email
        String content = HtmlContent.resetPassword();
        session.setAttribute("id", acc.getAccId());
        session.setAttribute("token", token);

        Email.sendEmail(acc.getAccEmail(), acc.getAccId(), 1, "Thiết lập lại mật khẩu", content);
            
        // when user click send again mail
        request.setAttribute("email", email);
        request.getRequestDispatcher("c_sendmail.jsp").forward(request, response);

    }
    
}
