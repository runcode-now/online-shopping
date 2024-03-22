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
import java.io.PrintWriter;
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
public class CusUpdateAddressDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("cu_addressdetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String choice = request.getParameter("choice");
        AccountDAO acc_dao = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        AddressDAO add_dao = new AddressDAO();
        CustomerDAO cus_dao = new CustomerDAO();
        Customer cus = cus_dao.getByAccountId(acc.getAccId());

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String detail = request.getParameter("detail");
        String addId = request.getParameter("addId");

        String newAdd = detail + ", " + ward + ", " + district + ", " + city;
        // check option (choice || add new)
        if (choice.equals("update")) {
            Address address = add_dao.getById(Integer.parseInt(addId));
            address.setAddRecName(name);
            address.setAddRecPhone(phone);
            address.setAddName(newAdd);
            add_dao.update(address);
        } else {  //add new address
            Address add = new Address(0, name, phone, newAdd, "", "on", cus.getCusId());

            List search = add_dao.getAllByCustomerId(cus.getCusId());
            if (search.size() != 0) {
                add.setAddDefault("custom");
            } else {
                add.setAddDefault("default");
            }

            add_dao.add(add);
        }

        request.getRequestDispatcher("cus_upaddress").forward(request, response);
    }

}
