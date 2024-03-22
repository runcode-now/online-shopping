/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.CategorySize;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CategorySizeDAO extends DBContext implements IDAO<CategorySize> {

    @Override
    public List<CategorySize> getAll() {
        List<CategorySize> list = new ArrayList<>();
        String sql = "SELECT * FROM CategorySize ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                CategorySize u = new CategorySize(rs.getInt(1), rs.getString(2));
                list.add(u);
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return list;
    }

    @Override
    public void add(CategorySize t) {
        String sql = "INSERT INTO CategorySize (CategorySizename) VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateSizeName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(CategorySize t) {
        String sql = "UPDATE CategorySize\n"
                + "SET CategorySizename = ?\n"
                + "WHERE CategorySizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateSizeName());
            st.setInt(2, t.getCateSizeId());
            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CategorySize\n"
                + " WHERE CategorySizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CategorySize getById(int id) {
        String sql = "SELECT * FROM CategorySize Where CategorySizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                CategorySize u = new CategorySize(rs.getInt(1),
                        rs.getString(2));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public CategorySize getByName(String name) {
        String sql = "SELECT * FROM CategorySize Where CategorySizeName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                CategorySize u = new CategorySize(rs.getInt(1),
                        rs.getString(2));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //ThaiNV
    public List<CategorySize> getAllByCondition(String condition) {
        List<CategorySize> list = new ArrayList<>();
        String sql = "SELECT * FROM CategorySize ";
        if (condition.equalsIgnoreCase("fontsize")) {
            sql += " WHERE CategorySizeId <= 3";
        } else if (condition.equalsIgnoreCase("numbersize")) {
            sql += " WHERE CategorySizeId >= 4 AND CategorySizeId <= 10";
        } else {
            sql += " WHERE CategorySizeId >= 11";

        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                CategorySize u = new CategorySize(rs.getInt(1), rs.getString(2));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        CategorySizeDAO a = new CategorySizeDAO();
//        System.out.println(a.getAll().size());
//
//        CategorySize t = new CategorySize(7, "S");
//        a.update(t);
//        //        CategorySize acc = new CategorySize(0, "dsadas");
////        a.add(acc);
//        System.out.println(a.getById(2).toString());
//        CategorySize acc = new CategorySize(0, "dsadas");
//        a.add(acc);
//        System.out.println(a.getById(2).getCateSizeName());

        List<CategorySize> acc = a.getAll();
        for (CategorySize categorySize : acc) {
            System.out.println(categorySize.getCateSizeName());
        }
    }

}
