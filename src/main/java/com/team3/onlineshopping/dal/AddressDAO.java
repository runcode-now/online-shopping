/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Address;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class AddressDAO extends DBContext implements IDAO<Address> {

    @Override
    public List<Address> getAll() {
        List<Address> list = new ArrayList<>();
        String sql = "SELECT * FROM Address ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Address u = new Address(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Address> getAll(String status) {
        List<Address> list = new ArrayList<>();
        String sql = "SELECT * FROM Address "
                + "WHERE AddressStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Address u = new Address(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Address t) {
        String sql = "INSERT INTO Address (AddressReceiverName, AddressReceiverPhone, AddressName, AddressDefault, AddressStatus,CustomerId) "
                + "\n VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getAddRecName());
            st.setString(2, t.getAddRecPhone());
            st.setString(3, t.getAddName());
            st.setString(4, t.getAddDefault());
            st.setString(5, "on");
            st.setInt(6, t.getCusId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Address t) {
        String sql = "UPDATE Address\n"
                + "   SET AddressReceiverName = ?\n"
                + "      ,AddressReceiverPhone = ?\n"
                + "      ,AddressName = ?\n"
                + "      ,AddressDefault = ?\n"
                + "      ,AddressStatus = ?\n"
                + "      ,CustomerId = ?\n"
                + " WHERE AddressId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getAddRecName());
            st.setString(2, t.getAddRecPhone());
            st.setString(3, t.getAddName());
            st.setString(4, t.getAddDefault());
            st.setString(5, t.getAddStatus());
            st.setInt(6, t.getCusId());
            st.setInt(7, t.getAddId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Address\n"
                + " WHERE AddressId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id, String status) {
        String sql = "UPDATE Address\n"
                + "SET AddressStatus = '" + status + "'\n"
                + " WHERE AddressId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Address getById(int id) {
        String sql = "SELECT * FROM Address Where AddressId = ?\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Address u = new Address(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Address getById(int id, String status) {
        String sql = "SELECT * FROM Address Where AddressId = ?\n"
                + "AND AddressStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Address u = new Address(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void changeStatus(int id) {
        String sql = "UPDATE Address \n"
                + "   SET AddressStatus = ?\n"
                + " WHERE AddressId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (getById(id).getAddStatus().equals("on")) {
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

    public List<Address> getAllByCustomerId(int id) {
        List<Address> list = new ArrayList<>();
        String sql = "SELECT * FROM Address "
                + "WHERE AddressStatus = 'on' && CustomerId = " + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Address u = new Address(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Address getAddByOrderId(int id) {
        String sql = "SELECT * FROM address AS addr\n"
                + "JOIN `order` AS ord ON ord.AddressId = addr.AddressId\n"
                + "WHERE ord.OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Address u = new Address(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //MinhBD
    public void deleteNull() {
        String sql = "DELETE FROM `address` WHERE `AddressReceiverName` IS NULL;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        AddressDAO add_dao = new AddressDAO();
//        List<Address> addList = a.getAllByCustomerId(2);
//        for (Address address : addList) {
//            System.out.println(address.toString());
//        }
//        System.out.println(add_dao.getAll().size());
//            int add_id = 8;
//            Address addre = add_dao.getById(add_id);
//            addre.setAddStatus("off");
//            add_dao.update(addre);

//        add_dao.changeStatus(11);
//        System.out.println(add_dao.getById(1, "on").getAddName());
        System.out.println(add_dao.getAddByOrderId(114).getAddId());
    }
}
