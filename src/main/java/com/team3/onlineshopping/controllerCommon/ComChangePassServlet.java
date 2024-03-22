package com.team3.onlineshopping.controllerCommon;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.model.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ComChangePassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc.getRoleId() == 4) {
            req.getRequestDispatcher("cu_changepass.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("c_changepass.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO acc_dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String cfpass = request.getParameter("cfpass");

        String errOldPass = "";
        String errNewPass = "";
        String errCfPass = "";
        String status = "";

        oldpass = (oldpass == null) ? "" : oldpass;
        newpass = (newpass == null) ? "" : newpass;
        cfpass = (cfpass == null) ? "" : cfpass;

        if (!checkCorrectPass(oldpass, acc)) {
            errOldPass = "Mật khẩu không chính xác";
        }

        if (!checkFormatPass(newpass)) {
            errNewPass = "Mật khẩu phải gồm ít nhất 6 kí tự chứa cả chữ và số";
        }

        if (!newpass.equals(cfpass)) {
            errCfPass = "Mật khẩu xác nhận phải trùng mật khẩu mới";
        }

        if (checkCorrectPass(oldpass, acc) && checkFormatPass(newpass) && newpass.equals(cfpass)) {
            status = "success";
            acc.setAccPass(newpass);
            acc_dao.update(acc);
            request.setAttribute("status", "success");
        }

        request.setAttribute("errOldPass", errOldPass);
        request.setAttribute("errNewPass", errNewPass);
        request.setAttribute("errCfPass", errCfPass);
        if (acc.getRoleId() == 4) {
            request.getRequestDispatcher("cu_changepass.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("c_changepass.jsp").forward(request, response);
        }
//        request.getRequestDispatcher("test.jsp").forward(request, response);

    }

    private boolean checkCorrectPass(String oldpass, Account acc) {
        return oldpass.equals(acc.getAccPass());
    }

    private boolean checkFormatPass(String newpass) {
        if (newpass.length() >= 6 && newpass.matches(".*[0-9]+.*") && newpass.matches(".*[a-z]+.*")) {
            return true;
        }
        return false;
    }

}
