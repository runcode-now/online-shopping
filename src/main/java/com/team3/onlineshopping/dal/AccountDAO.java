/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.information.UserAccount;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.AccountStatistic;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext implements IDAO<Account> {

    @Override
    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Account> getAll(String status) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account \n"
                + "WHERE AccountStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Account t) {
        String sql = "INSERT INTO Account (AccountEmail, AccountPassword, AccountPhoneNumber, AccountName, AccountAvatarURL, AccountDoB, AccountCreatedDate, AccountStatus, RoleId)"
                + " VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getAccEmail());
            st.setString(2, t.getAccPass());
            st.setString(3, t.getAccPhone());
            st.setString(4, t.getAccName());
            st.setString(5, t.getAccAvarUrl());
            st.setString(6, t.getAccDoB());
            st.setString(7, "on");
            st.setInt(8, t.getRoleId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Account t) {
        String sql = "UPDATE Account\n"
                + "   SET AccountEmail = ? \n"
                + "      ,AccountPassword = ? \n"
                + "      ,AccountPhoneNumber = ? \n"
                + "      ,AccountName = ? \n"
                + "      ,AccountAvatarURL = ? \n"
                + "      ,AccountDoB = ? \n"
                + "      ,AccountStatus = ? \n"
                + "      ,RoleId = ? \n"
                + " WHERE AccountId = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getAccEmail());
            st.setString(2, t.getAccPass());
            st.setString(3, t.getAccPhone());
            st.setString(4, t.getAccName());
            st.setString(5, t.getAccAvarUrl());
            st.setString(6, t.getAccDoB());
            st.setString(7, t.getAccStatus());
            st.setInt(8, t.getRoleId());
            st.setInt(9, t.getAccId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Account \n"
                + " WHERE AccountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Account getById(int id) {
        String sql = "SELECT * FROM Account Where AccountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getById(int id, String status) {
        String sql = "SELECT * FROM Account Where AccountId = ?\n"
                + "AND AccountStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //---------------------------------------------------------------------------------------//
    //AnhPH    
    public Account getByEmailStatus(String email, String status) {
        String sql = "SELECT * FROM Account Where AccountEmail = ?";
        if (!status.isEmpty()) {
            sql += " AND AccountStatus = ?";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            if (!status.isEmpty()) {
                st.setString(2, status);
            }
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getByEmailPassStatus(String accEmail, String accPass, String status) {
        String query = "SELECT * FROM account\n"
                + "WHERE AccountEmail = ? AND AccountPassword = ? \n"
                + " AND AccountStatus = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, accEmail);
            st.setString(2, accPass);
            st.setString(3, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getTotalAccount() {
        String query = "select count(*) from Account";
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

    public int getTotalAccountByCondition(String text, String dateBegin,
            String dateEnd, String status, int roleId) {
        String sql = "SELECT count(*) FROM Account WHERE  1 = 1";
        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += " and (AccountEmail like ? or AccountPhoneNumber like ?"
                        + " or AccountName like ?)";
            }

            if (!dateBegin.isEmpty()) {
                sql += " and AccountCreatedDate >= ?";
            }

            if (!dateEnd.isEmpty()) {
                sql += " and AccountCreatedDate <= ?";
            }

            if (!status.isEmpty()) {
                sql += " and AccountStatus like ?";
            }

            if (roleId != 0) {
                sql += "and RoleId = ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
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

            if (roleId != 0) {
                st.setInt(index++, roleId);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Account> splitPageAccount(int quantity, int page, String text,
            String dateBegin, String dateEnd, String status, int roleId) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE  1 = 1";

        try {
            int index = 1; // Bắt đầu với chỉ mục của tham số là 1

            // check text (email || phone || name)
            if (!text.isEmpty()) {
                sql += " and (AccountEmail like ? or AccountPhoneNumber like ?"
                        + " or AccountName like ?)";
            }

            if (!dateBegin.isEmpty()) {
                sql += " and AccountCreatedDate >= ?";
            }

            if (!dateEnd.isEmpty()) {
                sql += " and AccountCreatedDate <= ?";
            }

            if (!status.isEmpty()) {
                sql += " and AccountStatus like ?";
            }

            if (roleId != 0) {
                sql += " and RoleId = ?";
            }

            if (quantity != 0) {
                sql += " order by AccountId "
                        + "LIMIT ? OFFSET ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);
            if (!text.isEmpty()) {
                st.setString(index++, "%" + text + "%");
                st.setString(index++, "%" + text + "%");
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

            if (roleId != 0) {
                st.setInt(index++, roleId);
            }

            if (quantity != 0) {
                st.setInt(index++, quantity);
                st.setInt(index++, (page - 1) * quantity);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
                list.add(a);
            }
        } catch (SQLException e) {
        }
        System.out.println(sql);
        return list;
    }

    public List<AccountStatistic> getStatisticAccountByMonths() {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            SELECT  m.MonthYear,
            		COALESCE(COUNT(a.AccountCreatedDate), 0) AS TotalAccounts
            FROM (SELECT DATE_FORMAT(CURDATE() - INTERVAL n MONTH, '%Y-%m') AS MonthYear
            	  FROM (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL 
                        SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL 
            			SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11
                        ) n
                  ) m	LEFT JOIN  account a ON DATE_FORMAT(a.AccountCreatedDate, '%Y-%m') = m.MonthYear
            GROUP BY m.MonthYear
            ORDER BY m.MonthYear;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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

    //-----------------------------------------------------------------------------
    //AnhTV
    public Account getAccByCusId(int id) {
        String sql = "SELECT * FROM account AS acc \n"
                + "JOIN customer AS cus ON acc.AccountId = cus.AccountId\n"
                + "WHERE cus.CustomerId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Account getAccountLatest() {
        String sql = "SELECT * FROM `Account`\n"
                + "ORDER BY AccountId DESC LIMIT 1";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            //loop until select the last object
            while (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //MinhBD
    public Account getAccByEmpId(int id) {
        String sql = "SELECT * FROM account AS acc \n"
                + "JOIN employee AS cus ON acc.AccountId = cus.AccountId\n"
                + "WHERE cus.EmployeeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //AnhPH
    public List<AccountStatistic> getStatisticAccountBy7days() {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            WITH DateRange AS (
                SELECT CURDATE() - INTERVAL a DAY AS date
                FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6) a
            )
            
            SELECT DateRange.date AS AccountCreatedDate, COUNT(account.AccountCreatedDate) AS TotalAccounts
            FROM DateRange LEFT JOIN account ON DateRange.date = account.AccountCreatedDate AND account.RoleId = 4
            WHERE DateRange.date BETWEEN CURDATE() - INTERVAL 7 DAY AND CURDATE()
            GROUP BY DateRange.date
            ORDER BY DateRange.date;
        """;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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

    public List<AccountStatistic> getStatisticRoleAccount() {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            SELECT RoleId, count(RoleId) 
            FROM account
            GROUP BY RoleId
        """;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
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

    public List<AccountStatistic> getStatisticStatusAccount() {
        List<AccountStatistic> list = new ArrayList<>();
        String sql = """
            SELECT AccountStatus, count(AccountStatus) 
            FROM account
            GROUP BY AccountStatus
        """;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
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

    public List<Account> getTop5Account() {
        List<Account> list = new ArrayList<>();
        String sql = """
            SELECT * FROM account
            ORDER BY AccountCreatedDate DESC
            LIMIT 5;
        """;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Account u = new Account(rs.getInt("AccountId"),
                        rs.getString("AccountEmail"),
                        rs.getString("AccountPassword"),
                        rs.getString("AccountPhoneNumber"),
                        rs.getString("AccountName"),
                        rs.getString("AccountAvatarURL"),
                        rs.getString("AccountDoB"),
                        rs.getString("AccountCreatedDate"),
                        rs.getString("AccountStatus"),
                        rs.getInt("RoleId"));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("==========");
        AccountDAO abc = new AccountDAO();
//        System.out.println(abc.getStatisticRoleAccount().size());
//        System.out.println(abc.getStatisticAccountByMonths().size());
        System.out.println(abc.getAccountLatest().toString());
    }
}
