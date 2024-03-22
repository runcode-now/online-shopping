/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class OrderDetailsDAO extends DBContext implements IDAO<OrderDetails> {

    @Override
    public List<OrderDetails> getAll() {
        List<OrderDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                OrderDetails u = new OrderDetails(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(OrderDetails t) {
        String sql = "INSERT INTO OrderDetails (ProductId, OrderId, OrderDetailsQuantity, CategorySizeId)"
                + " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getProId());
            st.setInt(2, t.getOrId());
            st.setInt(3, t.getOrDeQuantity());
            st.setInt(4, t.getCateSizeId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(OrderDetails t) {
        String sql = "UPDATE OrderDetails\n"
                + "   SET OrderDetailsQuantity = ?,\n"
                + "   SET CategorySizeId = ?\n"
                + " WHERE OrderId = ? AND ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getOrDeQuantity());
            st.setInt(2, t.getCateSizeId());
            st.setInt(3, t.getOrId());
            st.setInt(4, t.getProId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM OrderDetails \n"
                + " WHERE OrderDetailsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public OrderDetails getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<OrderDetails> getByProductId(int proId) {
        List<OrderDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails "
                + "WHERE ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                OrderDetails u = new OrderDetails(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //AnhTV
    public List<OrderDetails> getByOrderId(int id) {
        List<OrderDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails "
                + "WHERE OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                OrderDetails u = new OrderDetails(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                list.add(u);
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public OrderDetails getByProCateSizeId(int proId, int orId) {
        String sql = "SELECT * FROM OrderDetails "
                + "WHERE ProductId = ? AND OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            st.setInt(2, orId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                OrderDetails u = new OrderDetails(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    // Thai iter3
    public void deleteByProId(int id) {
        String sql = "DELETE FROM OrderDetails \n"
                + " WHERE Productid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        OrderDetailsDAO a = new OrderDetailsDAO();
//        System.out.println(a.getAll().size());
//
//        OrderDetails t = new OrderDetails(2, 6, 1);
//        a.update(t);
//        OrderDetails acc = new OrderDetails(2, 6, 10);
//        a.add(acc);

//        System.out.println(a.getByProductId(1).size());
//        System.out.println(a.getByOrderId(6).size());
System.out.println(a.getByOrderId(154).get(0).getProId());
    }
}
