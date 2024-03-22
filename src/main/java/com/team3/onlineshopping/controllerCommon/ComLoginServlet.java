/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCommon;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.information.UserAccount;
import com.team3.onlineshopping.model.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ComLoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("com_login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("c_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String accEmail = request.getParameter("email");
        String accPass = request.getParameter("pass");
        String rem = request.getParameter("rem");

//        request.setAttribute("email", accEmail);
//        request.setAttribute("accPass", accPass);
//        request.setAttribute("rem", rem);
//        request.getRequestDispatcher("test.jsp").forward(request, response);
        //tao 3 cookies: username, pass, remeber
        Cookie cu = new Cookie("cuser", accEmail);
        Cookie cp = new Cookie("cpass", accPass);
        Cookie cr = new Cookie("crem", rem);
        if (rem != null) {
            //co chon
            cu.setMaxAge(60 * 60 * 24 * 7);
            cp.setMaxAge(60 * 60 * 24 * 7);
            cr.setMaxAge(60 * 60 * 24 * 7);
        } else {
            //khong chon
            cu.setMaxAge(0);
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }
        //luu vao brower
        response.addCookie(cu);
        response.addCookie(cp);
        response.addCookie(cr);

        AccountDAO dao = new AccountDAO();
//        Account loginAccount = dao.getByEmailPassStatus(accEmail, UserAccount.encodeToSHA1(accPass), "on");
        Account loginAccount = dao.getByEmailPassStatus(accEmail, accPass, "on");

        if (loginAccount == null) {
            request.setAttribute("msg", "Sai tên đăng nhập/mật khẩu!");
            request.getRequestDispatcher("c_login.jsp").forward(request, response);

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("account", loginAccount);
//            request.getRequestDispatcher("pub_home").forward(request, response);
            switch (loginAccount.getRoleId()) {
                case 4:
                    request.getRequestDispatcher("pub_home").forward(request, response);
                    break;
                case 1:
                    request.getRequestDispatcher("ad_dashboard").forward(request, response);
                    break;
                case 2:
//                    request.getRequestDispatcher("sale_dashboard").forward(request, response);
                    response.sendRedirect("sale_dashboard");
                    break;
                case 3:
//                    request.getRequestDispatcher("m_dashboard.jsp").forward(request, response);
                    response.sendRedirect("mkt_dashboard");
                    break;
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
