/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCustomer;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.dal.AddressDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Address;
import com.team3.onlineshopping.model.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CusUpdateAddressServlet extends HttpServlet {

    AccountDAO acc_dao = new AccountDAO();
    CustomerDAO cus_dao = new CustomerDAO();
    AddressDAO add_dao = new AddressDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        Customer cus = cus_dao.getByAccountId(acc.getAccId());

        List<Address> addList = add_dao.getAllByCustomerId(cus.getCusId());

        request.setAttribute("addList", addList);
        request.getRequestDispatcher("cu_address.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String addId = request.getParameter("addId");
        AddressDAO add_dao = new AddressDAO();
        Address address = null;
        try {
            int add_id = Integer.parseInt(addId);
            address = add_dao.getById(add_id);
        } catch (Exception e) {
            
        }

        String[] arr = address.getAddName().split(",");
        String addDetail = "";
        for (int i = 0; i < arr.length - 3; i++) {
            if (i == arr.length - 4) {
                addDetail += arr[i].trim();
            } else {
                addDetail += arr[i].trim() + ",";
            }
        }

        request.setAttribute("city", arr[arr.length - 1]);
        request.setAttribute("district", arr[arr.length - 2]);
        request.setAttribute("ward", arr[arr.length - 3]);
        request.setAttribute("detail", addDetail);
        request.setAttribute("address", address);
        request.setAttribute("choice", "update");
        request.getRequestDispatcher("cu_addressdetails.jsp").forward(request, response);

    }

}
