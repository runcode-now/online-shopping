/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Promotion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class PromotionDAO extends DBContext implements IDAO<Promotion> {

    @Override
    public List<Promotion> getAll() {
        List<Promotion> list = new ArrayList<>();
        String sql = "SELECT * FROM Promotion ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Promotion u = new Promotion(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Promotion t) {
        String sql = "INSERT INTO Promotion (PromotionName, PromotionDiscount, PromotionStartDate, PromotionEndDate, PromotionQuantity, ProductId)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getPromoName());
            st.setInt(2, t.getPromoDiscount());
            st.setString(3, t.getPromoStartDate());
            st.setString(4, t.getPromoEndDate());
            st.setInt(5, t.getPromoQuantity());
            st.setInt(6, t.getProId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void update(Promotion t) {
        String sql = "UPDATE Promotion\n"
                + "   SET PromotionName = ?\n"
                + "      ,PromotionDiscount = ?\n"
                + "      ,PromotionStartDate = ?\n"
                + "      ,PromotionEndDate = ?\n"
                + "      ,PromotionQuantity = ?\n"
                + "      ,ProductId = ?\n"
                + " WHERE PromotionId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getPromoName());
            st.setInt(2, t.getPromoDiscount());
            st.setString(3, t.getPromoStartDate());
            st.setString(4, t.getPromoEndDate());
            st.setInt(5, t.getPromoQuantity());
            st.setInt(6, t.getProId());
            st.setInt(7, t.getPromoId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Promotion \n"
                + " WHERE PromotionId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Promotion getById(int id) {
        String sql = "SELECT * FROM Promotion Where PromotionId = ?";
        // sau khi sửa nhớ bỏ s sau ProductImagesId
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Promotion u = new Promotion(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        PromotionDAO a = new PromotionDAO();
        System.out.println(a.getAll().size());

//        Promotion acc = new Promotion(4, "Tết", 20, "2024-02-01", "2024-02-29", 1000, 1);
//        a.update(acc);
//        Promotion acc = new Promotion(0, "aaa", 15, "2022-12-10", "2002-12-19", 2000, 4);
//        a.add(acc);
        System.out.println(a.getById(4).getPromoQuantity());
    }
}
