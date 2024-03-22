/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CustomerDAO extends DBContext implements IDAO<Customer> {

    @Override
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Customer u = new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Customer t) {
        String sql = "INSERT INTO Customer (CustomerCreatedDate, CustomerTotalPurchase, AccountId, RoleId)"
                + " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCusCreatedDate());
            st.setDouble(2, t.getCusTotalPurchase());
            st.setInt(3, t.getAccId());
            st.setInt(4, t.getRoleId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Customer t) {
        String sql = "UPDATE Customer\n"
                + "   SET CustomerCreatedDate = ?\n"
                + "      ,CustomerTotalPurchase = ?\n"
                + "      ,AccountId = ?\n"
                + "      ,RoleId = ?\n"
                + " WHERE CustomerId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCusCreatedDate());
            st.setDouble(2, t.getCusTotalPurchase());
            st.setInt(3, t.getAccId());
            st.setInt(4, t.getRoleId());
            st.setInt(5, t.getCusId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Customer\n"
                + " WHERE CustomerId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Customer getById(int id) {
        String sql = "SELECT * FROM Customer Where CustomerId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Customer u = new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Customer getByAccountId(int id) {
        String sql = "SELECT * FROM Customer Where AccountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Customer u = new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5));
                return u;
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //AnhPH
    public int getTotalCustomer() {
        String query = "select count(*) from Account where RoleId = 4";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalCustomerByCondition(String text, long numBegin,
            long numEnd, String status) {
        String sql = "SELECT count(*) FROM Account a join customer c on a.AccountId = c.AccountId "
                + " WHERE  1 = 1";
        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += " and (a.AccountEmail like ? or a.AccountPhoneNumber like ?"
                        + " or a.AccountName like ?)";
            }

            if (numBegin != 0) {
                sql += " and c.CustomerTotalPurchase >= ?";
            }

            if (numEnd != 0) {
                sql += " and c.CustomerTotalPurchase <= ?";
            }

            if (!status.isEmpty()) {
                sql += " and a.AccountStatus like ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
            }

            if (numBegin != 0) {
                st.setLong(index++, numBegin);
            }

            if (numEnd != 0) {
                st.setLong(index++, numEnd);
            }

            if (!status.isEmpty()) {
                st.setString(index++, status);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Account> splitPageCustomer(int quantity, int page, String text,
            long numBegin, long numEnd, String status) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account a join customer c on a.AccountId = c.AccountId "
                + " WHERE  1 = 1 ";
        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += " and (a.AccountEmail like ? or a.AccountPhoneNumber like ?"
                        + " or a.AccountName like ?)";
            }

            if (numBegin != 0) {
                sql += " and c.CustomerTotalPurchase >= ?";
            }

            if (numEnd != 0) {
                sql += " and c.CustomerTotalPurchase <= ?";
            }

            if (!status.isEmpty()) {
                sql += " and a.AccountStatus like ?";
            }

            if (quantity != 0) {
                sql += " order by a.AccountId "
                        + "LIMIT ? OFFSET ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
            }

            if (numBegin != 0) {
                st.setLong(index++, numBegin);
            }

            if (numEnd != 0) {
                st.setLong(index++, numEnd);
            }

            if (!status.isEmpty()) {
                st.setString(index++, status);
            }

            if (quantity != 0) {
                st.setInt(index++, quantity);
                st.setInt(index++, (page - 1) * quantity);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
                list.add(a);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        CustomerDAO a = new CustomerDAO();
        System.out.println(a.getAll().size());

//        Customer t = new Customer(7, "2023-12-01", 1500000, 6, 4);
//        a.update(t);
//        Customer acc = new Customer(0, "2022-12-01", 330000, 6, 4);
//        a.add(acc);
//        System.out.println(a.getById(3).getCusCreatedDate());
    }

}
