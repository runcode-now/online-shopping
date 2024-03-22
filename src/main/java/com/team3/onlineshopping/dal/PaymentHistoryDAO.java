/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.PaymentHistory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class PaymentHistoryDAO extends DBContext implements IDAO<PaymentHistory> {

    @Override
    public List<PaymentHistory> getAll() {
        List<PaymentHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM PaymentHistory";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                PaymentHistory u = new PaymentHistory(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
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
    public void add(PaymentHistory t) {
        String sql = "INSERT INTO PaymentHistory (PaymentDate, PaymentMethod, OrderId, CustomerId)"
                + " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getPayDate());
            st.setString(2, t.getPayMethod());
            st.setInt(3, t.getOrId());
            st.setInt(4, t.getCusId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(PaymentHistory t) {
        String sql = "UPDATE PaymentHistory\n"
                + "   SET PaymentDate = ?\n"
                + "      ,PaymentMethod = ?\n"
                + "      ,OrderId = ?\n"
                + "      ,CustomerId = ?\n"
                + " WHERE PaymentId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getPayDate());
            st.setString(2, t.getPayMethod());
            st.setInt(3, t.getOrId());
            st.setInt(4, t.getCusId());
            st.setInt(5, t.getPayId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM PaymentHistory \n"
                + " WHERE PaymentHistoryId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public PaymentHistory getById(int id) {
        String sql = "SELECT * FROM PaymentHistory Where PaymentId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                PaymentHistory u = new PaymentHistory(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //AnhTV
    public PaymentHistory getByOrderId(int id) {
        String sql = "SELECT * FROM PaymentHistory Where OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                PaymentHistory u = new PaymentHistory(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        PaymentHistoryDAO a = new PaymentHistoryDAO();
        System.out.println(a.getAll().size());

//        PaymentHistory t = new PaymentHistory(7, "2024-01-09", "VNPAY", 6, 4);
//        a.update(t);
//        PaymentHistory acc = new PaymentHistory(0, "2024-02-22", "VNPay", 102, 2);
//        a.add(acc);
        System.out.println(a.getByOrderId(168).getPayMethod());
    }

}
