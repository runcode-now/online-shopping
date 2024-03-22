/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.CategoryProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CategoryProductDAO extends DBContext implements IDAO<CategoryProduct> {

    @Override
    public List<CategoryProduct> getAll() {
        List<CategoryProduct> list = new ArrayList<>();
        String sql = "SELECT * FROM CategoryProduct ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                CategoryProduct u = new CategoryProduct(rs.getInt(1), rs.getString(2));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(CategoryProduct t) {
        String sql = "INSERT INTO CategoryProduct (CategoryProductName) VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateProName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(CategoryProduct t) {
        String sql = "UPDATE CategoryProduct \n"
                + "   SET CategoryProductName = ?\n"
                + " WHERE CategoryProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateProName());
            st.setInt(2, t.getCateProId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CategoryProduct\n"
                + " WHERE CategoryProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();
            st.close(); // Đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CategoryProduct getById(int id) {
        String sql = "SELECT * FROM CategoryProduct Where CategoryProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                CategoryProduct u = new CategoryProduct(rs.getInt(1),
                        rs.getString(2));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CategoryProductDAO a = new CategoryProductDAO();
        System.out.println(a.getAll().size());

//        CategoryProduct t = new CategoryProduct(11, "Áo");
//        a.update(t);
//        CategoryProduct acc = new CategoryProduct(0, "dsadas");
//        a.add(acc);
//        System.out.println(a.getById(2).getCateProName());
    }

}
