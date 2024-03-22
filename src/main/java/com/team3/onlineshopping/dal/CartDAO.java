/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Cart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CartDAO extends DBContext implements IDAO<Cart> {

    @Override
    public List<Cart> getAll() {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT * FROM Cart "
                + "WHERE CartStatus = 'on'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Cart u = new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Cart t) {
        String sql = "INSERT INTO Cart (ProductId, CustomerId, CategorySizeId, CartQuantity, cartCreatedDate , CartStatus)"
                + " VALUES (?, ?, ?, ?, NOW(), ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getProId());
            st.setInt(2, t.getCusId());
            st.setInt(3, t.getCateSizeId());
            st.setInt(4, t.getCartQuantity());
            st.setString(5, "on");
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Cart t) {
        String sql = "UPDATE Cart\n"
                + "   SET ProductId = ?\n"
                + "      ,CustomerId = ?\n"
                + "      ,CategorySizeId = ?\n"
                + "      ,CartQuantity = ?\n"
                + "      ,cartCreatedDate = NOW()\n"
                + "      ,CartStatus = ?\n"
                + " WHERE CartId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getProId());
            st.setInt(2, t.getCusId());
            st.setInt(3, t.getCateSizeId());
            st.setInt(4, t.getCartQuantity());
            st.setString(5, t.getCartStatus());
            st.setInt(6, t.getCartId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Cart\n"
                + " WHERE CartId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Cart getById(int id) {
        String sql = "SELECT * FROM Cart Where CartId = ?\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Cart u = new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //AnhTV
    public Cart getByIdStatusOn(int id) {
        String sql = "SELECT * FROM Cart Where CartId = ?\n"
                + " AND CartStatus = 'on'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Cart u = new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void changeStatus(int id) {
        String sql = "UPDATE Cart \n"
                + "   SET CartStatus = ?\n"
                + " WHERE CartId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (getById(id).getCartStatus().equals("on")) {
                st.setString(1, "off");
            } else {
                st.setString(1, "on");
            }
            st.setInt(2, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cart> getByCusId(int id) {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT * FROM Cart Where CustomerId = ?\n"
                + " AND CartStatus = 'on'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Cart u = new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Cart getByProSizeId(int proId, int ProSizeId, int cusId) {
        String sql = "SELECT * FROM Cart \n"
                + "Where ProductId = ? AND CategorySizeId = ? AND CustomerId = ?\n"
                + "AND CartStatus = 'on'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            st.setInt(2, ProSizeId);
            st.setInt(3, cusId);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Cart u = new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateQuantity(Cart t, int quantity) {
        String sql = "UPDATE Cart SET CartQuantity = CartQuantity + " + quantity + " WHERE CartId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getCartId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void increaseQuantity(Cart t) {
        String sql = "UPDATE Cart";
        if (t.getCartQuantity() < 10000) {
            sql += " SET CartQuantity = CartQuantity + 1 \n";
        } else {
            sql += " SET CartQuantity = 9999\n";
        }
        sql += "WHERE CartId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getCartId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void decreaseQuantity(Cart t) {
        String sql = "UPDATE Cart";
        if (t.getCartQuantity() > 1) {
            sql += " SET CartQuantity = CartQuantity - 1 \n";
        } else {
            sql += " SET CartQuantity = 1\n";
        }
        sql += "WHERE CartId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getCartId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Thai iter3
    public void deleteByProId(int id) {
        String sql = "DELETE FROM Cart\n"
                + " WHERE Productid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Cart getByProId(int id) {
        String sql = "SELECT * FROM Cart Where ProductId = ?\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Cart u = new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CartDAO a = new CartDAO();
        System.out.println(a.getAll().size());

//        Cart acc = new Cart(12, 1, 1, 2, 1, "2024-01-18", "on");
//        a.updateQuantity(acc);
        Cart acc = new Cart(1, 1, 1, 1, 23, "2024-1-16", "on");
        a.add(acc);
//        Cart t = a.getById(3);
//        a.decreaseQuantity(t);
//    a.delete(1);
//        Cart abc = a.getByProSizeId(2,3);
//        System.out.println(a.getByProSizeId(2, 3));
//        a.changeStatus(48);
    }

}
