/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerAdmin;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.EmployeeDAO;
import com.team3.onlineshopping.information.UserAccount;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminRegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminRegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("a_register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String pass = request.getParameter("pass");
        String cfpass = request.getParameter("cfpass");

        fullname = (fullname == null) ? "" : fullname.trim();
        job = (job == null) ? "" : job.trim();
        email = (email == null) ? "" : email.trim();
        phone = (phone == null) ? "" : phone.trim();
        pass = (pass == null) ? "" : pass.trim();
        cfpass = (cfpass == null) ? "" : cfpass.trim();

        String nameErr = UserAccount.checkFullName(fullname);
        int jobId = Integer.parseInt(job);
        String emailErr = UserAccount.checkEmail(email);
        String phoneErr = UserAccount.checkPhone(phone);
        String passwordErr = UserAccount.checkPassword(pass);
        String passAgainErr = UserAccount.checkConfirmPassword(pass, cfpass);

        // register success
        if (nameErr.isEmpty() && emailErr.isEmpty() && phoneErr.isEmpty()
                && passwordErr.isEmpty() && passAgainErr.isEmpty()) {
            AccountDAO acc_dao = new AccountDAO();
            EmployeeDAO emp_dao = new EmployeeDAO();
            acc_dao.add(new Account(0, email, UserAccount.encodeToSHA1(pass), phone, fullname, "Image/Avatar/Avatar_Default.png", null, "", "on", jobId));
            System.out.println(acc_dao.getAccountLatest().getAccId());
            emp_dao.add(new Employee(0, 0, acc_dao.getAccountLatest().getAccId(), jobId));
            request.setAttribute("status", "success");
        }

        request.setAttribute("errFullName", nameErr);
        request.setAttribute("errEmail", emailErr);
        request.setAttribute("errPhone", phoneErr);
        request.setAttribute("errPass", passwordErr);
        request.setAttribute("errCfPass", passAgainErr);

        request.getRequestDispatcher("a_register.jsp").forward(request, response);
    }

}
