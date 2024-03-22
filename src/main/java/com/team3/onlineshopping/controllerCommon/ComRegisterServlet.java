package com.team3.onlineshopping.controllerCommon;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.information.UserAccount;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ComRegisterServlet extends HttpServlet {

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        request.getRequestDispatcher("c_register.jsp").forward(request, response);
//    } 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("c_register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.addUser(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameUser = request.getParameter("username");
        String emailUser = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("pass");
        String passwordAgain = request.getParameter("passAgain");
        String date = request.getParameter("currentDate");

        boolean result = checkUser(request, response);

        if (date != null && date.length() > 10) {
            date = date.substring(0, 10);
        }

        if (result) {
            // add new account in db
            AccountDAO acc_dao = new AccountDAO();
            CustomerDAO cus_dao = new CustomerDAO();

            Account u = new Account(0, emailUser, UserAccount.encodeToSHA1(password), phone, nameUser, "Image/Avatar/Avatar_Default.png", null, null, "on", 4);
//            Account u = new Account(0, emailUser, password, phone, nameUser, null, null, null, "on", 4);
            acc_dao.add(u);
            Customer cus = new Customer(0, date, 0, acc_dao.getByEmailStatus(emailUser, "on").getAccId(), 4);
            cus_dao.add(cus);

            response.sendRedirect("c_login.jsp");
        }
    }

    private boolean checkUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameUser = request.getParameter("username");
        String emailUser = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("pass");
        String passwordAgain = request.getParameter("passAgain");

        String nameErr = "";
        String emailErr = "";
        String phoneErr = "";
        String passAgainErr = "";
        String passwordErr = "";

        // check format emailUserErr input
        if (!emailUser.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+$")) {
            emailErr = "Sai định dạng email.";
            request.setAttribute("emailUserErr", emailErr);
        }

        // check format phone input
        if (!phone.trim().isEmpty() && !phone.matches("^0[35789]{1}\\d{8}$")) {
            phoneErr = "Số điện thoại phải gồm 10 chữ số và số 0 ở đầu.";
            request.setAttribute("phoneErr", phoneErr);
        }

        // check format password input
        if (!passwordAgain.equals(password)) {
            passAgainErr = "Mật khẩu xác nhận phải giống mật khẩu trước đó.";
            request.setAttribute("passwordAgainErr", passAgainErr);
        }

        // check duplicate email
        if (emailErr.isEmpty()) {
            AccountDAO user = new AccountDAO();
            Account choice = user.getByEmailStatus(emailUser, "on");
            // email is exist
            if (choice != null) {
                emailErr = "Email đã tồn tại. Vui lòng nhập email khác!";
                request.setAttribute("emailUserErr", emailErr);
            }
        }

        //check pass.length>=6 
        if (password.length() < 6 || !password.matches(".*[a-zA-Z]+.*") || !password.matches(".*\\d.*")) {
            passwordErr = "Mật khẩu phải có ít nhất 6 ký tự và bao gồm cả chữ và số";
            request.setAttribute("passwordErr", passwordErr);
        }

        // move page
        if (!nameErr.isEmpty() || !emailErr.isEmpty()
                || !phoneErr.isEmpty() || !passAgainErr.isEmpty()) {
            request.getRequestDispatcher("c_register.jsp").forward(request, response);
            return false;
        }
        return true;
    }

}
