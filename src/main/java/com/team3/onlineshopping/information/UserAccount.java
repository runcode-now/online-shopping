/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.information;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import java.security.MessageDigest;
import java.util.Base64;

public class UserAccount {

    public static String encodeToSHA1(String str) {
        String secure = "wodmtmpa@eiknsiovichdaa";
        str += secure;

        String result = "";

        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            result = Hex.encodeHexString(md.digest(dataBytes));
            result = Base64.getEncoder().encodeToString(md.digest(dataBytes));
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public static void movePage(HttpServletRequest request, HttpServletResponse response,
            String page1, String page2) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc.getRoleId() == 4) {
            request.getRequestDispatcher(page1).forward(request, response);
        } else {
            request.getRequestDispatcher(page2).forward(request, response);
        }
    }

    public static String checkFullName(String name) {
        if (name.matches("^[a-zA-Z]+$")) {
            return "Tên chỉ bao gồm những chữ cái.";
        }
        return "";
    }

    public static String checkEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+$")) {
            return "Sai định dạng email.";
        }

        AccountDAO acc_dao = new AccountDAO();
        // check duplicate email in database
        if (acc_dao.getByEmailStatus(email, "") != null) {
            return "Email đã tồn tại trong hệ thống.";
        }

        return "";
    }

    public static String checkPhone(String phone) {
        if (!phone.trim().isEmpty() && !phone.matches("^0[35789]{1}\\d{8}$")) {
            return "Số điện thoại phải gồm 10 chữ số và số 0 ở đầu.";
        }
        return "";
    }

    public static String checkPassword(String password) {
        if (password.length() < 6 || !password.matches(".*[a-zA-Z]+.*") || !password.matches(".*\\d.*")) {
            return "Mật khẩu phải có ít nhất 6 ký tự và bao gồm cả chữ và số";
        }
        return "";
    }

    public static String checkConfirmPassword(String password, String cfpass) {
        if (!password.equals(cfpass)) {
            return "Mật khẩu xác nhận không trùng khớp.";
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(encodeToSHA1("sfasf"));
    }
}
