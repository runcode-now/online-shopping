/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Collection;
import com.team3.onlineshopping.model.News;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CollectionDAO extends DBContext implements IDAO<Collection> {

    @Override
    public List<Collection> getAll() {
        List<Collection> list = new ArrayList<>();
        String sql = "SELECT * FROM Collection ";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Collection orders = new Collection(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));
                list.add(orders);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Collection t) {
        String sql = """
                     INSERT INTO `collection`
                     (`CollectionTitle`,`CollectionProId`,`CollectionCreatedDate`,`CollectionStatus`,`NewsId`,`EmployeeId`)
                     VALUES (?,?,?,?,?,?)
                     """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getCollectionTitle());
            st.setString(2, t.getCollectionProId());
            st.setString(3, t.getCollectionCreatedDate());
            st.setString(4, t.getCollectionStatus());
            st.setInt(5, t.getNewsId());
            st.setInt(6, t.getEmployeeId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Collection t) {
        String sql = "UPDATE `collection`\n"
                + "SET\n"
                + "`CollectionId` = ?,\n"
                + "`CollectionTitle` = ?,\n"
                + "`CollectionProId` = ?,\n"
                + "`CollectionCreatedDate` = ?,\n"
                + "`CollectionStatus` = ?,\n"
                + "`NewsId` = ?,\n"
                + "`EmployeeId` = ?\n"
                + "WHERE `CollectionId` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getCollectionId());
            st.setString(2, t.getCollectionTitle());
            st.setString(3, t.getCollectionProId());
            st.setString(4, t.getCollectionCreatedDate());
            st.setString(5, t.getCollectionStatus());
            st.setInt(6, t.getNewsId());
            st.setInt(7, t.getEmployeeId());
            st.setInt(8, t.getCollectionId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `collection`\n"
                + "WHERE CollectionId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Collection getById(int id) {
        String sql = "SELECT * FROM `collection` Where collectionId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Collection u = new Collection(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
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

    // ThaiNH iter3
    // lay ra danh sach collection
    public List<Collection> getListCol(String search, String status) {
        List<Collection> list = new ArrayList<>();
        String sql = "SELECT * FROM Collection WHERE 1 = 1";
        if (search != null) {
            sql += " AND CollectionTitle LIKE '%" + search + "%'";
        }
        if (status != null) {
            sql += " AND CollectionStatus LIKE '%" + status + "%'";
        }
        sql += " ORDER BY CollectionCreatedDate DESC";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Collection col = new Collection(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));

                list.add(col);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // lay ra danh sach collection duoc phan trang
    public List<Collection> getListColPaging(String search, String status, int indexPage) {
        List<Collection> list = new ArrayList<>();
        String sql = "SELECT * FROM Collection WHERE 1 = 1";
        if (search != null) {
            sql += " AND CollectionTitle LIKE '%" + search + "%'";
        }
        if (status != null) {
            sql += " AND CollectionStatus LIKE '%" + status + "%'";
        }
        sql += " ORDER BY CollectionCreatedDate DESC LIMIT 8 OFFSET " + (indexPage - 1) * 8;
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Collection col = new Collection(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));
                list.add(col);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Collection> get5SliderNewest() {
        List<Collection> list = new ArrayList<>();
        String sql = "SELECT * FROM collection WHERE CollectionStatus LIKE '%on%' ORDER BY CollectionCreatedDate DESC LIMIT 5;";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Collection orders = new Collection(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));
                list.add(orders);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        CollectionDAO a = new CollectionDAO();
//        System.out.println(a.getAll().size());
        System.out.println(a.getListCol(null, null).size());
        System.out.println(a.getListColPaging(null, null, 1).size());
        Collection c = new Collection("bộ", "1, 2", "2024-02-23", "on", 32, 2);
        a.add(c);

    }
}
