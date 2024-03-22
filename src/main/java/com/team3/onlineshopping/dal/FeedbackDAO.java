/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class FeedbackDAO extends DBContext implements IDAO<Feedback> {

    @Override
    public List<Feedback> getAll() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Feedback u = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Feedback t) {
        String sql = "INSERT INTO Feedback (FeedbackContent, FeedbackCreatedDate, FeedbackRating, ProductId, CustomerId, OrderId)"
                + "  VALUES (?, NOW(), ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getFeedContent());
            st.setInt(2, t.getFeedRating());
            st.setInt(3, t.getProId());
            st.setInt(4, t.getCusId());
            st.setInt(5, t.getOrId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Feedback t) {
        String sql = "UPDATE Feedback\n"
                + "   SET FeedbackContent = ?\n"
                + "      ,FeedbackCreatedDate = ?\n"
                + "      ,FeedbackRating = ?\n"
                + "      ,ProductId = ?\n"
                + "      ,CustomerId = ?\n"
                + " WHERE FeedbackId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getFeedContent());
            st.setString(2, t.getFeedCreatedDate());
            st.setInt(3, t.getFeedRating());
            st.setInt(4, t.getProId());
            st.setInt(5, t.getCusId());
            st.setInt(6, t.getFeedId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Feedback \n"
                + " WHERE FeedbackId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Feedback getById(int id) {
        String sql = "SELECT * FROM Feedback Where FeedbackId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Feedback u = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //AnhTV
    public Feedback getByProCusOrId(int proId, int cusId, int orId) {
        String sql = "SELECT * FROM Feedback Where ProductId = ? AND CustomerId = ? AND OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            st.setInt(2, cusId);
            st.setInt(3, orId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback u = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //MinhBD
    public Feedback getByProCusId(int proId, int cusId) {
        String sql = "SELECT * FROM Feedback Where ProductId = ? AND CustomerId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            st.setInt(2, cusId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback u = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Feedback> getByProId(int id) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback Where ProductId = ? ORDER BY FeedbackCreatedDate DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback u = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Feedback> splitPageFeedback(int quantity, int page, String text,
            String dateBegin, String dateEnd, int rate) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE 1=1 ";

        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += """
                      and CustomerId IN (
                           SELECT customer.CustomerId 
                           FROM customer 
                           JOIN `account` ON customer.AccountId = `account`.AccountId 
                           WHERE `account`.AccountName LIKE ? 
                       ) 
                       OR ProductId IN (
                           SELECT product.ProductId
                           FROM product 
                           WHERE product.ProductName LIKE ? 
                       )
                       """;
            }

            if (!dateBegin.isEmpty()) {
                sql += " and FeedbackCreatedDate >= ?";
            }

            if (!dateEnd.isEmpty()) {
                sql += " and FeedbackCreatedDate <= ?";
            }

            if (rate != 0) {
                sql += " and FeedbackRating = ?";
            }

            if (quantity != 0) {
                sql += " order by FeedbackCreatedDate DESC ,FeedbackId "
                        + "LIMIT ? OFFSET ? ";
            }
            ;
            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
            }

            if (!dateBegin.isEmpty()) {
                st.setString(index++, dateBegin);
            }

            if (!dateEnd.isEmpty()) {
                st.setString(index++, dateEnd);
            }
            if (rate != 0) {
                st.setInt(index++, rate);
            }

            if (quantity != 0) {
                st.setInt(index++, quantity);
                st.setInt(index++, (page - 1) * quantity);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback u = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                list.add(u);
            }
        } catch (SQLException e) {
        }
        System.out.println(sql);
        return list;
    }

    public int getTotalFeedBack() {
        String query = "select count(*) from Feedback";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalFeedbackByCondition(String text, String dateBegin,
            String dateEnd, int rate) {
        String sql = "SELECT count(*) FROM Feedback WHERE  1 = 1 ";
        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += """
                       and CustomerId IN (
                                                  SELECT customer.CustomerId 
                                                  FROM customer 
                                                  JOIN `account` ON customer.AccountId = `account`.AccountId 
                                                  WHERE `account`.AccountName LIKE ? 
                                              ) 
                                              OR ProductId IN (
                                                  SELECT product.ProductId
                                                  FROM product 
                                                  WHERE product.ProductName LIKE ? 
                                              )
                       """;
            }

            if (!dateBegin.isEmpty()) {
                sql += " and FeedbackCreatedDate >= ?";
            }

            if (!dateEnd.isEmpty()) {
                sql += " and FeedbackCreatedDate <= ?";
            }
            if (rate != 0) {
                sql += " and FeedbackRating = ?";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
            }

            if (!dateBegin.isEmpty()) {
                st.setString(index++, dateBegin);
            }

            if (!dateEnd.isEmpty()) {
                st.setString(index++, dateEnd);
            }
            if (rate != 0) {
                st.setInt(index++, rate);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    // Thai iter3
    public void deleteByProId(int id) {
        String sql = "DELETE FROM Feedback \n"
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
        FeedbackDAO a = new FeedbackDAO();
        System.out.println(a.getAll().size());

//        Feedback t = new Feedback(0, "aaaaaabbbbbbxxxxxx", "", 4, 1, 1);
//        a.update(t);
        Feedback acc = new Feedback(0, "Hay lam a!!!", "", 5, 88, 1);
        a.add(acc);
//        System.out.println(a.getByProCusId(82,4).getFeedContent());
    }
}
