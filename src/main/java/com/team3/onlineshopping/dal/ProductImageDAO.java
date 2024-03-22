/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.ProductImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ProductImageDAO extends DBContext implements IDAO<ProductImage> {

    @Override
    public List<ProductImage> getAll() {
        List<ProductImage> list = new ArrayList<>();
        String sql = "SELECT * FROM ProductImage ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                ProductImage u = new ProductImage(rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(ProductImage t) {
        String sql = "INSERT INTO ProductImage (ProductImageURL, ProductId) "
                + " VALUES (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getProImgUrl());
            st.setInt(2, t.getProId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(ProductImage t) {
        String sql = "UPDATE ProductImage\n"
                + "   SET ProductImageURL =  ?\n"
                + "      ,ProductId = ?\n"
                + " WHERE ProductImageId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getProImgUrl());
            st.setInt(2, t.getProId());
            st.setInt(3, t.getProImgId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM ProductImage \n"
                + " WHERE ProductImageId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteByProId(int id) {
        String sql = "DELETE FROM ProductImage \n"
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
    public ProductImage getById(int id) {
        String sql = "SELECT * FROM ProductImage WHERE ProductImageId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                ProductImage u = new ProductImage(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<ProductImage> getAllByProId(int id) {
        List<ProductImage> list = new ArrayList<>();
        String sql = "SELECT * FROM ProductImage WHERE ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                ProductImage u = new ProductImage(rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        ProductImageDAO a = new ProductImageDAO();
        System.out.println(a.getAll().size());

//        ProductImage acc = new ProductImage(10, "01_ao_so_mi_nu_bat_doi_xung_01.jpd", 1);
//        a.update(acc);
//        ProductImage acc = new ProductImage(0, "aaaa", 3);
//        a.add(acc);
//        System.out.println(a.getById(4).getProImgUrl());
        a.deleteByProId(215);

    }
}
