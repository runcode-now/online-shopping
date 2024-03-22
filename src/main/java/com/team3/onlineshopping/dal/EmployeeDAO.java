/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class EmployeeDAO extends DBContext implements IDAO<Employee> {

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Employee u = new Employee(rs.getInt(1),
                        rs.getLong(2),
                        rs.getInt(3),
                        rs.getInt(4));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Employee t) {
        String sql = "INSERT INTO Employee (EmployeeSalary, AccountId, RoleId)"
                + " VALUES (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setLong(1, t.getEmSalary());
            st.setInt(2, t.getAccId());
            st.setInt(3, t.getRoleId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Employee t) {
        String sql = "UPDATE Employee\n"
                + "   SET EmployeeSalary = ?\n"
                + "      ,AccountId = ?\n"
                + "      ,RoleId = ?\n"
                + " WHERE EmployeeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, t.getEmSalary());
            st.setInt(2, t.getAccId());
            st.setInt(3, t.getRoleId());
            st.setInt(4, t.getEmId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Employee\n"
                + " WHERE EmployeeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM Employee Where EmployeeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Employee u = new Employee(rs.getInt(1),
                        rs.getLong(2),
                        rs.getInt(3),
                        rs.getInt(4));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //ThaiNH
    public Employee getByAccId(int id) {
        String sql = "SELECT * FROM Employee Where AccountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Employee u = new Employee(rs.getInt(1),
                        rs.getLong(2),
                        rs.getInt(3),
                        rs.getInt(4));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeDAO a = new EmployeeDAO();
        System.out.println(a.getAll().size());

        Employee t = new Employee(0, 0, 81, 2);
//        a.update(t);
//        Employee acc = new Employee(0, 340000, "2022-12-01", null, 6, 3);
        a.add(t);
//        System.out.println(a.getById(2).getEmSalary());
    }

}
