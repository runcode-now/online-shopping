/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCommon;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.information.Token;
import com.team3.onlineshopping.model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ComNewPasswordServlet extends HttpServlet {
    private final String NOT_FOUNT_PAGE = "p_notfound.jsp";
    private final String NEW_PASSWORD_PAGE = "c_newpassword.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String token = (String) session.getAttribute("token");

        if (!Token.isTokenValid(token)) 
            request.getRequestDispatcher(NOT_FOUNT_PAGE).forward(request, response);

        request.getRequestDispatcher(NEW_PASSWORD_PAGE).forward(request, response);

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Integer id = (Integer) session.getAttribute("id");
        String token = (String) session.getAttribute("token");

        token = (token == null)?"":token;

        // check time exist token
        if (Token.isTokenValid(token)) {
            String pass = request.getParameter("new_pass");
            String cfpass = request.getParameter("cf_pass");
            String passErr = "";
            String cfpassErr = "";

            if (!checkFormatPass(pass))
                passErr = "Mật khẩu phải gồm ít nhất 6 kí tự chứa cả chữ và số";

            if (!pass.equals(cfpass))
                cfpassErr = "Mật khẩu xác nhận phải trùng mật khẩu mới";

            if (!passErr.isEmpty() || !cfpassErr.isEmpty()) {
                request.setAttribute("passErr", passErr);
                request.setAttribute("cfpassErr", cfpassErr);
                request.getRequestDispatcher(NEW_PASSWORD_PAGE).forward(request, response);
            }
            else{
                AccountDAO acc_dao = new AccountDAO();
                Account acc = acc_dao.getById(id.intValue());
                acc.setAccPass(pass);
                acc_dao.update(acc);

                // REMOVE session
                session.removeAttribute("id");
                session.removeAttribute("token");

                request.getRequestDispatcher("c_resetsuccess.jsp").forward(request, response);
            }

        } else
            request.getRequestDispatcher(NOT_FOUNT_PAGE).forward(request, response);

    }
    
    private boolean checkFormatPass(String newpass) {
        if (newpass.length() >= 6 && newpass.matches(".*[0-9]+.*") && newpass.matches(".*[a-z]+.*")) {
            return true;
        }
        return false;
    }

}
