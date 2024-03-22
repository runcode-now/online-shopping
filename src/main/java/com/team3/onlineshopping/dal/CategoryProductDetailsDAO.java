/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.CategoryProductDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CategoryProductDetailsDAO extends DBContext implements IDAO<CategoryProductDetails> {

    @Override
    public List<CategoryProductDetails> getAll() {
        List<CategoryProductDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM CategoryProductDetails ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                CategoryProductDetails u = new CategoryProductDetails(rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(CategoryProductDetails t) {
        String sql = "INSERT INTO CategoryProductDetails (CategoryProductDetailsName, CategoryProductId)"
                + " VALUES (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateProDeName());
            st.setInt(2, t.getCateProId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(CategoryProductDetails t) {
        String sql = "UPDATE CategoryProductDetails\n"
                + "SET CategoryProductDetailsName = ?\n"
                + ",CategoryProductId = ?\n"
                + "WHERE CategoryProductDetailsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateProDeName());
            st.setInt(2, t.getCateProId());
            st.setInt(3, t.getCateProDeId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CategoryProductDetails\n"
                + " WHERE CategoryProductDetailsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CategoryProductDetails getById(int id) {
        String sql = "SELECT * FROM CategoryProductDetails Where CategoryProductDetailsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                CategoryProductDetails u = new CategoryProductDetails(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //ThaiNV
    // Hiển thị tất cả các loại sản phẩm trong từng thể loại (Áo sơ mi, áo Polo..)
    public List<CategoryProductDetails> getallCateProDeIdByCateProId(int id) {
        List<CategoryProductDetails> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM CategoryProductDetails AS cpd\n"
                + "JOIN CategoryProduct AS cp ON cpd.CategoryProductId = cp.CategoryProductId\n"
                + "WHERE cpd.CategoryProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoryProductDetails cpd = new CategoryProductDetails(rs.getInt("CategoryProductDetailsId"), rs.getString("CategoryProductDetailsName"), rs.getInt("CategoryProductId"));
                list.add(cpd);
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return list;
    }

    public CategoryProductDetails getByProId(int id) {
        String sql = "SELECT * FROM product AS p JOIN categoryproductdetails AS cpd \n"
                + "ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId WHERE p.productid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                CategoryProductDetails u = new CategoryProductDetails(rs.getInt("CategoryProductDetailsId"),
                        rs.getString("CategoryProductDetailsName"),
                        rs.getInt("CategoryProductId"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<CategoryProductDetails> getAllByCateProId(int id) {
        List<CategoryProductDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM CategoryProductDetails Where CategoryProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                CategoryProductDetails u = new CategoryProductDetails(rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //AnhTV
    public CategoryProductDetails getCateDeByProId(int id) {
        String sql = "SELECT * FROM categoryproductdetails AS cateDe\n"
                + "JOIN Product AS pro ON pro.CategoryProductDetailsId = cateDe.CategoryProductDetailsId\n"
                + "WHERE pro.ProductId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                CategoryProductDetails u = new CategoryProductDetails(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CategoryProductDetailsDAO a = new CategoryProductDetailsDAO();
        System.out.println(a.getAll().size());
//
//        CategoryProductDetails t = new CategoryProductDetails(29, "Đầm", 1);
//        a.update(t);
//        CategoryProductDetails acc = new CategoryProductDetails(0, "dsadas", 2);
//        a.add(acc);
//        System.out.println(a.getById(2).getCateProDeName());
    }

}
