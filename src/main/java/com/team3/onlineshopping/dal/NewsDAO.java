/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

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
public class NewsDAO extends DBContext implements IDAO<News> {

    @Override
    public List<News> getAll() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                News news = new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10));
                if (news.getNewsUpdateDate() == null) {
                    news.setNewsUpdateDate("");
                }
                list.add(news);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<News> getAll(String status) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News\n"
                + "WHERE NewsStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                News news = new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10));
                if (news.getNewsUpdateDate() == null) {
                    news.setNewsCreatedDate("");
                }
                list.add(news);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(News t) {
        String sql = "INSERT INTO News (NewsTitle, NewsImageURL, NewsDescription, NewsCreatedDate, NewsStatus, NewsView, CategoryNewsId, EmployeeId)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getNewsTitle());
            st.setString(2, t.getNewsImgUrl());
            st.setString(3, t.getNewsDescription());
            st.setString(4, t.getNewsCreatedDate());
            st.setString(5, t.getNewsStatus());
            st.setInt(6, t.getNewsView());
            st.setInt(7, t.getCateNewsId());
            st.setInt(8, t.getEmId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(News t) {
        String sql = "UPDATE News\n"
                + "   SET NewsTitle = ?\n"
                + "      ,NewsImageURL = ?\n"
                + "      ,NewsDescription = ?\n"
                + "      ,NewsCreatedDate = ?\n"
                + "      ,NewsUpdateDate = NOW()\n"
                + "      ,NewsStatus = ?\n"
                + "      ,NewsView = ?\n"
                + "      ,CategoryNewsId = ?\n"
                + "      ,EmployeeId = ?\n"
                + " WHERE NewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getNewsTitle());
            st.setString(2, t.getNewsImgUrl());
            st.setString(3, t.getNewsDescription());
            st.setString(4, t.getNewsCreatedDate());
            st.setString(5, t.getNewsStatus());
            st.setInt(6, t.getNewsView());
            st.setInt(7, t.getCateNewsId());
            st.setInt(8, t.getEmId());
            st.setInt(9, t.getNewsId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM News\n"
                + " WHERE NewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id, String status) {
        String sql = "UPDATE News\n"
                + "SET NewsStatus = '" + status + "'"
                + " WHERE NewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public News getById(int id) {
        String sql = "SELECT * FROM News Where NewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                News u = new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public News getByIdStatusOn(int id) {
        String sql = "SELECT * FROM News Where NewsId = ?\n"
                + "AND NewsStatus = 'on'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                News u = new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void changeStatus(int id) {
        String sql = "UPDATE News \n"
                + "   SET NewsStatus = ?\n"
                + " WHERE NewsId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (getById(id).getNewsStatus().equals("on")) {
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

    //MinhBD
    public int getTotalPostByCondition(String text, String dateBegin,
            String dateEnd, String status) {
        String sql = "SELECT count(*) FROM News WHERE  1 = 1";
        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += " and (NewsTitle like ? )";
            }

            if (!dateBegin.isEmpty()) {
                sql += " and NewsCreatedDate >= ?";
            }

            if (!dateEnd.isEmpty()) {
                sql += " and NewsCreatedDate <= ?";
            }
            if (!status.isEmpty()) {
                sql += " and NewsStatus like ?";
            }

            sql += " and CategoryNewsId =1";

            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");

            }

            if (!dateBegin.isEmpty()) {
                st.setString(index++, dateBegin);
            }

            if (!dateEnd.isEmpty()) {
                st.setString(index++, dateEnd);
            }
            if (!status.isEmpty()) {
                st.setString(index++, status);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalPost() {
        String query = "select count(*) from News WHERE CategoryNewsId= 1";
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

    public int getTotalPost(String status) {
        String query = "select count(*) from News"
                + " WHERE CategoryNewsId=1 and NewsStatus = '" + status + "'";
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

    public List<News> splitPagePost(int quantity, int page, String text,
            String dateBegin, String dateEnd, String status) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE  1 = 1 and CategoryNewsId=1";

        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += " and (NewsTitle like ? )";
            }

            if (!dateBegin.isEmpty()) {
                sql += " and NewsCreatedDate >= ?";
            }

            if (!dateEnd.isEmpty()) {
                sql += " and NewsCreatedDate <= ?";
            }

            if (!status.isEmpty()) {
                sql += " and NewsStatus like ?";
            }

            if (quantity != 0) {
                sql += " order by NewsId "
                        + "LIMIT ? OFFSET ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");

            }

            if (!dateBegin.isEmpty()) {
                st.setString(index++, dateBegin);
            }

            if (!dateEnd.isEmpty()) {
                st.setString(index++, dateEnd);
            }
            if (!status.isEmpty()) {
                st.setString(index++, status);
            }

            if (quantity != 0) {
                st.setInt(index++, quantity);
                st.setInt(index++, (page - 1) * quantity);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News a = new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10));
                if (a.getNewsUpdateDate() == null) {
                    a.setNewsUpdateDate("");
                }
                list.add(a);
            }
        } catch (SQLException e) {
        }
        System.out.println(sql);
        return list;
    }

    public List<News> getAllByCateNewsId(int cateNewsId, String searching, String status, String sortView) {
        List<News> list = new ArrayList<>();
        String sql = "";
        if (searching != null) {
            sql = "SELECT * FROM News\n"
                    + "WHERE CategoryNewsId = " + cateNewsId + " AND NewsTitle LIKE '%" + searching + "%'";
            if (status != null) {
                sql += " AND NewsStatus LIKE '%" + status + "%'";
            }
            if (sortView != null) {
                sql += " ORDER BY  NewsView DESC";
            }
        } else {
            sql = "SELECT * FROM News\n"
                    + "WHERE CategoryNewsId = " + cateNewsId;
            if (status != null) {
                sql += " AND NewsStatus LIKE '%" + status + "%'";
            }
            if (sortView != null) {
                sql += "  ORDER BY  NewsView DESC";
            }
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                News news = new News(rs.getInt("NewsId"),
                        rs.getString("NewsTitle"),
                        rs.getString("NewsImageURL"),
                        rs.getString("NewsDescription"),
                        rs.getString("NewsCreatedDate"),
                        rs.getString("NewsStatus"),
                        rs.getInt("NewsView"),
                        rs.getInt("CategoryNewsId"),
                        rs.getInt("EmployeeId"));
                list.add(news);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<News> getAllByCateNewsIdPaging(int cateNewsId, int indexPage, String searching, String status, String sortView) {
        List<News> list = new ArrayList<>();
        String sql = "";
        if (searching != null) {
            sql = "SELECT * FROM News\n"
                    + "WHERE CategoryNewsId = " + cateNewsId + " AND NewsTitle LIKE '%" + searching + "%'";
            if (status != null) {
                sql += " AND NewsStatus LIKE '%" + status + "%'";
            }
            if (sortView != null) {
                sql += " ORDER BY  NewsView DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            } else {
                sql += " ORDER BY  NewsCreatedDate DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            }
        } else {
            sql = "SELECT * FROM News\n"
                    + "WHERE CategoryNewsId = " + cateNewsId;
            if (status != null) {
                sql += " AND NewsStatus LIKE '%" + status + "%'";
            }
            if (sortView != null) {
                sql += "  ORDER BY  NewsView DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            } else {
                sql += "  ORDER BY  NewsCreatedDate DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            }
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                News news = new News(rs.getInt("NewsId"),
                        rs.getString("NewsTitle"),
                        rs.getString("NewsImageURL"),
                        rs.getString("NewsDescription"),
                        rs.getString("NewsCreatedDate"),
                        rs.getString("NewsStatus"),
                        rs.getInt("NewsView"),
                        rs.getInt("CategoryNewsId"),
                        rs.getInt("EmployeeId"));
                list.add(news);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    

    // Thai Iter 3
    // get list Collection by newsId
    public List<News> getListColByJoinNewsId() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News AS n \n"
                + "JOIN Collection AS c ON c.NewsId = n.NewsId";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                News news = new News(rs.getInt("NewsId"),
                        rs.getString("NewsTitle"),
                        rs.getString("NewsImageURL"),
                        rs.getString("NewsDescription"),
                        rs.getString("NewsCreatedDate"),
                        rs.getString("NewsStatus"),
                        rs.getInt("NewsView"),
                        rs.getInt("CategoryNewsId"),
                        rs.getInt("EmployeeId"));
                list.add(news);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public static void main(String[] args) {
        NewsDAO a = new NewsDAO();
        System.out.println(a.getAll().size());

//        News t = new News(6, "TƯNG BỪNG PHÁ KHO ĐÓN TẾT TO CHỈ 99K", null, "SALE Cực Mạnh Phá kho cuối năm - Tặng triệu ưu đãi Thời trang - Phụ kiện chỉ từ 99K cho nhà nhà thả ga sắm Tết! Siêu ưu đãi chỉ từ 8/1 – 9/2/2024, tới sắm ngay cho cả gia đình nhé!", "2024-08-01", "on", 1000, 1, 5);
//        a.update(t);
//        a.delete(6);
//        News acc = new News(0, "aaaa", null, "aaaaa", "2022-12-10", "on", 1000, 1, 5);
////        a.add(acc);
        News b = a.getById(12);
        b.setNewsTitle("bbbbbbbbbbbbbbbbbbbbbbbbbb");
        a.update(b);
//        a.changeStatus(1);
    }

}
