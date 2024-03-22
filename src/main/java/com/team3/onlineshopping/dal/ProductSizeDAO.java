/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.ProductSize;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ProductSizeDAO extends DBContext implements IDAO<ProductSize> {

    @Override
    public List<ProductSize> getAll() {
        List<ProductSize> list = new ArrayList<>();
        String sql = "SELECT * FROM ProductSize ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                ProductSize u = new ProductSize(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(ProductSize t) {
        String sql = "INSERT INTO ProductSize (ProductId, CategorySizeId, ProductSizeQuantity)"
                + " VALUES (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getProId());
            st.setInt(2, t.getCateSizeId());
            st.setInt(3, t.getProSizeQuantity());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void update(ProductSize t) {
        String sql = "UPDATE ProductSize\n"
                + "   SET ProductSizeQuantity = ?\n"
                + " WHERE ProductId = ? AND CategorySizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getProSizeQuantity());
            st.setInt(2, t.getProId());
            st.setInt(3, t.getCateSizeId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM ProductSize \n"
                + " WHERE ProductSizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteByProId(int id) {
        String sql = "DELETE FROM ProductSize \n"
                + " WHERE productid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ProductSize getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<ProductSize> getByProductId(int proId) {
        List<ProductSize> list = new ArrayList<>();
        String sql = "SELECT * FROM ProductSize "
                + "WHERE ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                ProductSize u = new ProductSize(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<ProductSize> getByCateSizeId(int id) {
        List<ProductSize> list = new ArrayList<>();
        String sql = "SELECT * FROM ProductSize "
                + "WHERE CategorySizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                ProductSize u = new ProductSize(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ProductSize getByProCateSizeId(int proId, int cateSizeId) {
        String sql = "SELECT * FROM ProductSize "
                + "WHERE ProductId = ? AND CategorySizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            st.setInt(2, cateSizeId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ProductSize u = new ProductSize(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //ThaiNH
    public int getTotalQuantity(int proId) {
        int total = 0;
        String sql = "SELECT SUM(ps.productsizequantity) FROM product as p join productsize as ps ON p.ProductId = ps.ProductId\n"
                + "JOIN categorysize cs ON ps.categorysizeid = cs.categorysizeid WHERE p.ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    public List<ProductSize> getAllProSizeById(int proId) {
        List<ProductSize> list = new ArrayList<>();
        String sql = "SELECT * FROM productsize AS ps JOIN product AS p ON ps.ProductId = p.ProductId\n"
                + "WHERE p.ProductId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                ProductSize u = new ProductSize(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        ProductSizeDAO a = new ProductSizeDAO();
        System.out.println(a.getAll().size());

//        ProductSize acc = new ProductSize(4,2,100);
//        a.update(acc);
//        ProductSize acc = new ProductSize(4, 2, 210);
//        a.add(acc);
//        System.out.println(a.getByCateSizeId(2).size());
//        System.out.println(a.getByProductId(1).size());
        System.out.println(a.getByProCateSizeId(1, 1).getProSizeQuantity());
    }
}
