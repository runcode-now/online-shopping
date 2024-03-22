/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.AccountStatistic;
import com.team3.onlineshopping.model.ExcelStatistic;
import com.team3.onlineshopping.model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class OrderDAO extends DBContext implements IDAO<Order> {

    @Override
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `Order` ";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Order orders = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                list.add(orders);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Order t) {
        String sql = "INSERT INTO `Order` (OrderTitle, OrderDate, OrderTotalPrice, OrderStatus, EmployeeId, CustomerId,AddressId)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getOrTitle());
            st.setString(2, t.getOrDate());
            st.setDouble(3, t.getOrTotalPrice());
            st.setString(4, t.getOrStatus());
            st.setInt(5, t.getEmId());
            st.setInt(6, t.getCusId());
            st.setInt(7, t.getAddId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Order t) {
        String sql = "UPDATE `Order`\n"
                + "   SET OrderTitle = ?\n"
                + "      ,OrderDate = ?\n"
                + "      ,OrderTotalPrice = ?\n"
                + "      ,OrderStatus = ?\n"
                + "      ,EmployeeId = ?\n"
                + "      ,CustomerId = ?\n"
                + "      ,AddressId = ?\n"
                + " WHERE OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getOrTitle());
            st.setString(2, t.getOrDate());
            st.setDouble(3, t.getOrTotalPrice());
            st.setString(4, t.getOrStatus());
            st.setInt(5, t.getEmId());
            st.setInt(6, t.getCusId());
            st.setInt(7, t.getAddId());
            st.setInt(8, t.getOrId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `Order`\n"
                + " WHERE OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Order getById(int id) {
        String sql = "SELECT * FROM `Order` Where OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Order u = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                return u;
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void changeStatus(int id, String status) {
        String sql = "UPDATE `Order` \n"
                + "   SET OrderStatus = ?\n"
                + " WHERE OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //AnhTV
    public int getTotalOrder() {
        String sql = "SELECT COUNT(*) FROM `order`";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int getTotalOrder(String status) {
        String sql = "SELECT COUNT(*) FROM `order`"
                + "WHERE OrderStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int getTotalOrder(String tilte, String status, String beginDate, String endDate, String beginPrice, String endPrice) {
        String sql = "SELECT COUNT(*) FROM `order` AS ord\n"
                + "INNER JOIN Customer AS cus ON cus.CustomerId = ord.CustomerId\n"
                + "INNER JOIN Account AS acc ON acc.AccountId = cus.AccountId\n"
                + "INNER JOIN Address AS ad ON ad.AddressId = ord.AddressId\n";
        if (tilte != null) {
            sql += "WHERE (OrderTitle LIKE '%" + tilte + "%' "
                    + "OR AccountName LIKE '%" + tilte + "%'\n"
                    + "OR AddressName LIKE '%" + tilte + "%')\n";
        }
        if (status != null && !"all".equals(status)) {
            sql += "AND OrderStatus LIKE '%" + status + "%'\n";
        }
        if (beginDate != null && endDate != null) {
            sql += "AND OrderDate BETWEEN '" + beginDate + "' AND '" + endDate + "'\n";
        }
        if (beginPrice != null && endPrice != null) {
            sql += "AND OrderTotalPrice BETWEEN " + beginPrice + " AND " + endPrice + "\n";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<Order> getAll(int index) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `order`\n"
                + "ORDER BY OrderDate DESC, OrderId DESC \n"
                + "LIMIT 10 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Order orders = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                list.add(orders);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Order getOrderLatest() {
        String sql = "SELECT * FROM `order`\n"
                + "ORDER BY OrderId DESC LIMIT 1";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Order orders = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                return orders;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Order> getAll(String status, int index) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `order`\n"
                + "WHERE OrderStatus = ?"
                + "ORDER BY OrderDate DESC, OrderId DESC\n"
                + "LIMIT 10 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, status);
            st.setInt(2, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Order orders = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                list.add(orders);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> FilterOrder(String tilte, String status, String beginDate, String endDate, String beginPrice, String endPrice, int index) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT distinct * FROM `order` AS ord\n"
                + "INNER JOIN Customer AS cus ON cus.CustomerId = ord.CustomerId\n"
                + "INNER JOIN Account AS acc ON acc.AccountId = cus.AccountId\n"
                + "INNER JOIN Address AS ad ON ad.AddressId = ord.AddressId\n";
        if (tilte != null) {
            sql += " WHERE (OrderTitle LIKE '%" + tilte + "%' \n"
                    + " OR AccountName LIKE '%" + tilte + "%'\n"
                    + " OR AddressName LIKE '%" + tilte + "%')\n";
        }
        if (status != null && !"all".equals(status)) {
            sql += " AND OrderStatus LIKE '%" + status + "%'\n";
        }
        if (beginDate != null && endDate != null) {
            sql += " AND OrderDate BETWEEN '" + beginDate + "' AND '" + endDate + "'\n";
        }
        if (beginPrice != null && endPrice != null) {
            sql += " AND OrderTotalPrice BETWEEN " + beginPrice + " AND " + endPrice + "\n";
        }
        sql += " ORDER BY OrderDate DESC, OrderId DESC LIMIT 10 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Order orders = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                list.add(orders);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> getByCusId(int id) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `order`\n"
                + "where CustomerId = ?\n"
                + "order by OrderDate DESC, OrderId DESC";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Order orders = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                list.add(orders);
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> getByCusId(int id, String status) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `Order` "
                + "WHERE CustomerId = ? AND OrderStatus = ? \n"
                + "order by OrderDate DESC, OrderId DESC";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, id);
            st.setString(2, status);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Order orders = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
                list.add(orders);
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //AnhPH
    public List<AccountStatistic> getStatisticOrderBy7days(String status) {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            WITH DateRange AS (
                SELECT CURDATE() - INTERVAL a DAY AS date
                FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6) a
            )
            
            SELECT DateRange.date AS OrderDate, COUNT(`order`.OrderDate) AS TotalOrders
            FROM DateRange 
            LEFT JOIN `order` ON DateRange.date = `order`.OrderDate AND `order`.OrderStatus = ?
            WHERE DateRange.date BETWEEN CURDATE() - INTERVAL 7 DAY AND CURDATE()
            GROUP BY DateRange.date
            ORDER BY DateRange.date;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                AccountStatistic u = new AccountStatistic(rs.getString(1),
                        rs.getInt(2));
                u.setName(u.getName().replace("-", "/"));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<AccountStatistic> getStatisticOrderByMonths(String status) {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            SELECT m.MonthYear,
                   COALESCE(COUNT(o.OrderDate), 0) AS TotalOrders
            FROM (
                SELECT DATE_FORMAT(CURDATE() - INTERVAL n MONTH, '%Y-%m') AS MonthYear
                FROM (
                    SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL 
                    SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL 
                    SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11
                ) n
            ) m
            LEFT JOIN `order` o ON DATE_FORMAT(o.OrderDate, '%Y-%m') = m.MonthYear AND o.OrderStatus = ?
            GROUP BY m.MonthYear
            ORDER BY m.MonthYear;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                AccountStatistic u = new AccountStatistic(rs.getString(1),
                        rs.getInt(2));
                u.setName(u.getName().replace("-", "/"));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<AccountStatistic> getStatisticTop5(String status, String time) {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            SELECT od.ProductId, SUM(od.OrderDetailsQuantity) AS Quantity
            FROM `order` o
            INNER JOIN `orderdetails` od ON o.OrderId = od.OrderId
            WHERE o.OrderStatus = ? AND o.OrderDate >= CURDATE() - INTERVAL ? DAY AND o.OrderDate <= CURDATE()
            GROUP BY od.ProductId
            ORDER BY SUM(od.OrderDetailsQuantity) DESC
            LIMIT 5;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            if (time.equals("day")) {
                st.setInt(2, 7);
            } else {
                st.setInt(2, 365);
            }

            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                AccountStatistic u = new AccountStatistic(rs.getString(1),
                        rs.getInt(2));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<AccountStatistic> getStatisticPriceBy7days(String status) {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            SELECT DateRange.date AS OrderDate, COALESCE(SUM(order.OrderTotalPrice), 0) AS TotalPrice
            FROM (
                SELECT CURDATE() - INTERVAL n DAY AS date
                FROM (
                    SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL 
                    SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
                ) n
            ) DateRange
            LEFT JOIN `order` ON DateRange.date = order.OrderDate AND order.OrderStatus = ?
            WHERE DateRange.date BETWEEN CURDATE() - INTERVAL 6 DAY AND CURDATE()
            GROUP BY DateRange.date
            ORDER BY DateRange.date;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                AccountStatistic u = new AccountStatistic(rs.getString(1),
                        rs.getInt(2));
                u.setName(u.getName().replace("-", "/"));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<AccountStatistic> getStatisticPriceByMonths(String status) {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            SELECT m.MonthYear,
                   COALESCE(SUM(o.OrderTotalPrice), 0) AS TotalOrderTotalPrice
            FROM (
                SELECT DATE_FORMAT(CURDATE() - INTERVAL n MONTH, '%Y-%m') AS MonthYear
                FROM (
                    SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL 
                    SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL 
                    SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11
                ) n
            ) m
            LEFT JOIN `order` o ON DATE_FORMAT(o.OrderDate, '%Y-%m') = m.MonthYear AND o.OrderStatus = ?
            GROUP BY m.MonthYear
            ORDER BY m.MonthYear;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                AccountStatistic u = new AccountStatistic(rs.getString(1),
                        rs.getInt(2));
                u.setName(u.getName().replace("-", "/"));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<ExcelStatistic> getStatisticExcel() {
        List<ExcelStatistic> list = new ArrayList<>();
        String sql = """
            WITH DateRange AS (
                SELECT (SELECT MIN(OrderDate) FROM `order`) + INTERVAL a.a DAY AS date
                FROM (
                    SELECT (a.a + (10 * b.a) + (100 * c.a)) AS a
                    FROM (
                        SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
                    ) a
                    CROSS JOIN (
                        SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
                    ) b
                    CROSS JOIN (
                        SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
                    ) c
                ) a
                WHERE (SELECT MIN(OrderDate) FROM `order`) + INTERVAL a.a DAY <= CURDATE()
            )
            SELECT 
                DateRange.date AS OrderDate,
                COALESCE(SUM(o.OrderTotalPrice), 0) AS TotalPrice,
                COALESCE(COUNT(o.OrderID), 0) AS OrderCount,
                COALESCE(ROUND(SUM(o.OrderTotalPrice) / COUNT(o.OrderID), 0), 0) AS AveragePrice,
                o.OrderStatus
            FROM DateRange
            LEFT JOIN  `order` o ON DateRange.date = DATE(o.OrderDate)
            GROUP BY DateRange.date, o.OrderStatus
            ORDER BY DateRange.date DESC;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                ExcelStatistic u = new ExcelStatistic(rs.getString(1),
                        rs.getDouble(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5));
                if (u.getOrderStatus() != null) {

                    if (u.getOrderStatus().equals("delivered")) {
                        u.setOrderStatus("Đã giao");
                    }
                    if (u.getOrderStatus().equals("cancelled")) {
                        u.setOrderStatus("Đã hủy");
                    }
                    if (u.getOrderStatus().equals("delivering")) {
                        u.setOrderStatus("Ðang giao");
                    }
                    if (u.getOrderStatus().equals("pending")) {
                        u.setOrderStatus("Chờ xử lý");
                    }

                }
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDAO a = new OrderDAO();
//        System.out.println(a.getAll(1).size());
        a.delete(1);
        List<ExcelStatistic> list = a.getStatisticExcel();
//        list.add("a");
//        list.add("b");
//        Order t = new Order(10, "Đơn Hàng Của Thái", "2024-01-09", 350000, "on", 5, 1, 5);
//        a.update(t);
//        a.delete(10);
//        Order acc = new Order(0, "aaaa", "2022-12-10", 300000, "off", 5, 1, 5);
//        a.add(acc);
//        System.out.println(a.getById(6).getOrTotalPrice());
//        a.changeStatus(1,"pending");
//        System.out.println(a.getTotalOrder("delivered"));
//        List<Order> list = a.getByCusId(4);
//        for (ExcelStatistic order : list) {
//            System.out.println(order.toString());
//        }
//        System.out.println(a.getTotalOrder(null, null, "all", null, null, null, null, null));
//        System.out.println(a.FilterOrder("hien", "cancelled", null, null, null, null, 1));
//        System.out.println(a.getTotalOrder("hang", "delivering", null, null, null, null));
    }

}
