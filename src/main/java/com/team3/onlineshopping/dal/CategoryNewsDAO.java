/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.CategoryNews;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryNewsDAO extends DBContext implements IDAO<CategoryNews> {

    @Override
    public List<CategoryNews> getAll() {
        List<CategoryNews> list = new ArrayList<>();
        String sql = "SELECT * FROM CategoryNews";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                CategoryNews u = new CategoryNews(rs.getInt(1), rs.getString(2));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(CategoryNews t) {
        String sql = "INSERT INTO CategoryNews (CategoryNewsName) VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateNewsName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(CategoryNews t) {
        String sql = "UPDATE CategoryNews\n"
                + "SET CategoryNewsName = ?\n"
                + " WHERE CategoryNewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCateNewsName());
            st.setInt(2, t.getCateNewsId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CategoryNews\n"
                + " WHERE CategoryNewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CategoryNews getById(int id) {
        String sql = "SELECT * FROM CategoryNews Where CategoryNewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                CategoryNews u = new CategoryNews(rs.getInt(1),
                        rs.getString(2));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CategoryNewsDAO a = new CategoryNewsDAO();
        System.out.println(a.getAll().size());

//        CategoryNews test = new CategoryNews(5, "Slider");
//        a.update(test);
//        CategoryNews acc = new CategoryNews(0, "dsadas");
//        a.add(acc);
//        System.out.println(a.getById(2).getCateNewsName());
    }
}
