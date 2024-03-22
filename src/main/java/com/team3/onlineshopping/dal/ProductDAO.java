/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.dal;

import com.team3.onlineshopping.model.Chart;
import com.team3.onlineshopping.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ProductDAO extends DBContext implements IDAO<Product> {

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }
            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement

        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAll(String status) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product \n"
                + "WHERE ProductStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void add(Product t) {
        String sql = "INSERT INTO Product (ProductName, ProductImageDefault, ProductPrice, ProductCost, ProductDescription, ProductRating, ProductSold,ProductCreatedDate, "
                + "ProductStatus, EmployeeId, CategoryProductDetailsId)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getProName());
            st.setString(2, t.getProImgDefault());
            st.setDouble(3, t.getProPrice());
            st.setDouble(4, t.getProCost());
            st.setString(5, t.getProDescription());
            st.setDouble(6, t.getProRating());
            st.setInt(7, t.getProSold());
            st.setString(8, t.getProCreatedDate());
            st.setString(9, t.getProStatus());
            st.setInt(10, t.getEmId());
            st.setInt(11, t.getCateProDeId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Product t) {
        String sql = "UPDATE Product\n"
                + "SET\n"
                + "ProductName = ?,\n"
                + "ProductImageDefault = ?,\n"
                + "ProductPrice = ?,\n"
                + "ProductCost = ?,\n"
                + "ProductDescription = ?,\n"
                + "ProductRating = ?,\n"
                + "ProductSold = ?,\n"
                + "ProductCreatedDate = ?,\n"
                + "ProductStatus = ?,\n"
                + "EmployeeId = ?,\n"
                + "CategoryProductDetailsId = ?\n"
                + "WHERE ProductId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getProName());
            st.setString(2, t.getProImgDefault());
            st.setDouble(3, t.getProPrice());
            st.setDouble(4, t.getProCost());
            st.setString(5, t.getProDescription());
            st.setDouble(6, t.getProRating());
            st.setInt(7, t.getProSold());
            st.setString(8, t.getProCreatedDate());
            st.setString(9, t.getProStatus());
            st.setInt(10, t.getEmId());
            st.setInt(11, t.getCateProDeId());
            st.setInt(12, t.getProId());

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Product\n"
                + " WHERE ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id, String status) {
        String sql = "UPDATE Product\n"
                + "SET ProductStatus = '" + status + "'"
                + " WHERE ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Product getById(int id) {
        String sql = "SELECT * FROM Product Where ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                return u;
            }

            rs.close(); // Đóng ResultSet
            st.close(); // Đóng PreparedStatement

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Product getById(int id, String status) {
        String sql = "SELECT * FROM Product Where ProductId = ?"
                + " AND ProductStatus = '" + status + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            if (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //AnhTV
    public void changeStatus(int id) {
        String sql = "UPDATE Product \n"
                + "   SET ProductStatus = ?\n"
                + " WHERE ProductId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (getById(id).getProStatus().equals("on")) {
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

    public double CalculateAVGRating(int id) {
        String sql = """
                     SELECT IFNULL(ROUND(AVG(f.FeedbackRating), 1), 0) AS AvgRating
                     FROM product p
                     LEFT JOIN feedback f ON p.ProductId = f.ProductId
                     WHERE p.ProductId = ?
                     GROUP BY p.ProductId """;
        double avgRating = 0.0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                avgRating = rs.getDouble("AvgRating");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return avgRating;
    }

    //ThaiNH
    // Hiển thị các sản phẩm ở trong thể loại áo(cùng thể loại áo-> áo sơ mi bất đối xứng,áo sơ mi dài tay phồng, ...)
    public List<Product> getAllProductByCateProDetailId(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Product AS p\n"
                + "JOIN CategoryProductDetails AS cpd ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cpd.CategoryProductDetailsId = ? AND p.ProductStatus LIKE 'on';";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductImageDefault"), rs.getDouble("ProductPrice"), rs.getDouble("ProductCost"), rs.getString("ProductDescription"), rs.getDouble("ProductRating"), rs.getInt("ProductSold"), rs.getString("ProductCreatedDate"), rs.getString("ProductStatus"), rs.getInt("EmployeeId"), rs.getInt("CategoryProductDetailsId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    // Hiển thị tất cả sản phẩm của thể loại(hiển thị ra tất cả các loại áo/ tất cả các loại quần)
    public List<Product> getAllProductOfCateId(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product AS p \n"
                + "JOIN CategoryProductDetails AS cpd ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cpd.CategoryProductId = ? AND p.ProductStatus LIKE 'on'\n"
                + "ORDER BY p.ProductCreatedDate DESC;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductImageDefault"), rs.getDouble("ProductPrice"), rs.getDouble("ProductCost"), rs.getString("ProductDescription"), rs.getDouble("ProductRating"), rs.getInt("ProductSold"), rs.getString("ProductCreatedDate"), rs.getString("ProductStatus"), rs.getInt("EmployeeId"), rs.getInt("CategoryProductDetailsId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    // Hiển thị tất cả sản phẩm của thể loại(hiển thị ra tất cả các loại áo/ tất cả các loại quần)-paging
    public List<Product> getAllProductOfCateIdPaging(int id, int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product AS p \n"
                + "JOIN CategoryProductDetails AS cpd ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cpd.CategoryProductId = ? AND p.ProductStatus LIKE 'on'\n"
                + "ORDER BY p.ProductCreatedDate DESC LIMIT 16 OFFSET ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, (indexPage - 1) * 16);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductImageDefault"), rs.getDouble("ProductPrice"), rs.getDouble("ProductCost"), rs.getString("ProductDescription"), rs.getDouble("ProductRating"), rs.getInt("ProductSold"), rs.getString("ProductCreatedDate"), rs.getString("ProductStatus"), rs.getInt("EmployeeId"), rs.getInt("CategoryProductDetailsId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    // Hiển thị tất cả sản phẩm theo từng thể loại chi tiết(-> tất cả áo sơ mi/tất cả quần....)
    public List<Product> getAllProductByCateId(int CategoryProductId, int CategoryProductDetailsId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Product AS p\n"
                + "JOIN CategoryProductDetails AS cpd ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cpd.CategoryProductId = ? AND p.ProductStatus LIKE 'on' AND cpd.CategoryProductDetailsId = ?\n"
                + "ORDER BY p.ProductCreatedDate DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CategoryProductId);
            st.setInt(2, CategoryProductDetailsId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductImageDefault"), rs.getDouble("ProductPrice"), rs.getDouble("ProductCost"), rs.getString("ProductDescription"), rs.getDouble("ProductRating"), rs.getInt("ProductSold"), rs.getString("ProductCreatedDate"), rs.getString("ProductStatus"), rs.getInt("EmployeeId"), rs.getInt("CategoryProductDetailsId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    //  Hiển thị tất cả sản phẩm theo từng thể loại chi tiết(-> tất cả áo sơ mi/tất cả quần....)-Paging
    public List<Product> getAllProductByCateIdPaging(int CategoryProductId, int CategoryProductDetailsId, int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Product AS p\n"
                + "JOIN CategoryProductDetails AS cpd ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cpd.CategoryProductId = ? AND p.ProductStatus LIKE 'on' AND cpd.CategoryProductDetailsId = ? AND p.ProductStatus LIKE 'on'\n"
                + "ORDER BY p.ProductCreatedDate DESC LIMIT 16 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CategoryProductId);
            st.setInt(2, CategoryProductDetailsId);
            st.setInt(3, (indexPage - 1) * 16);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductImageDefault"), rs.getDouble("ProductPrice"), rs.getDouble("ProductCost"), rs.getString("ProductDescription"), rs.getDouble("ProductRating"), rs.getInt("ProductSold"), rs.getString("ProductCreatedDate"), rs.getString("ProductStatus"), rs.getInt("EmployeeId"), rs.getInt("CategoryProductDetailsId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    // Tìm kiếm sản phẩm theo tên sản phẩm
    public List<Product> searchProductByProName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE ProductName LIKE '%" + name + "%' AND ProductStatus LIKE '%on%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Tìm kiếm sản phẩm theo tên sản phẩm-paging
    public List<Product> searchProductByProNamePaging(String name, int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE ProductName LIKE '%" + name + "%' AND ProductStatus LIKE '%on%'\n"
                + "ORDER BY ProductCreatedDate DESC LIMIT 16 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (indexPage - 1) * 16);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Lọc các sản phẩm theo giá và size
    public List<Product> filterProductByProNameAndSize(String price, int cateProId, int cateProDeId) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product as p\n"
                + "join categoryProductDetails as cpd on p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cpd.CategoryProductId = ? AND p.ProductStatus LIKE 'on'";

        if (cateProDeId != 0) {
            sql += " AND cpd.CategoryProductDetailsId = " + cateProDeId;
        }
        if (!price.equalsIgnoreCase("")) {
            sql += " AND p.productprice BETWEEN " + price + "";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cateProId);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Lọc các sản phẩm theo giá và size-Paging
    public List<Product> filterProductByProNameAndSizePaging(String price, int indexPage, int cateProId, int cateProDeId) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product as p\n"
                + "join categoryProductDetails as cpd on p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cpd.CategoryProductId = ? AND p.ProductStatus LIKE 'on'";

        if (cateProDeId != 0) {
            sql += " AND cpd.CategoryProductDetailsId = " + cateProDeId;
        }
        if (!price.equalsIgnoreCase("")) {
            sql += " AND p.productprice BETWEEN " + price + "";
        }
        sql += " ORDER BY p.ProductCreatedDate DESC LIMIT 16 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cateProId);
            st.setInt(2, (indexPage - 1) * 16);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Lọc các sản phẩm dược tìm kiếm theo giá và size
    public List<Product> filterSearchProductByProNameAndSize(String price, String searched) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product\n"
                + "WHERE productname LIKE '%" + searched + "%' AND productStatus LIKE 'on'";

        if (!price.equalsIgnoreCase("")) {
            sql += " AND productprice BETWEEN " + price + "";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Product> filterSearchProductByProNameAndSizePaging(String price, String searched, int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product\n"
                + "WHERE productname LIKE '%" + searched + "%' AND productStatus LIKE 'on'";
        if (!price.equalsIgnoreCase("")) {
            sql += " AND productprice BETWEEN " + price + "";
        }
        sql += " ORDER BY ProductCreatedDate DESC LIMIT 16 OFFSET ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, searched);
            st.setInt(1, (indexPage - 1) * 16);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //MinhBD
    public List<Product> getAllNew(String searchKeyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product "
                + "WHERE ProductStatus = 'ON' AND (ProductName LIKE ? "
                + "OR ProductName LIKE ? "
                + "OR ProductName LIKE ? "
                + "OR ProductName = ?)"
                + "ORDER BY ProductCreatedDate DESC\n"
                + "LIMIT 16;";
        // Sử dụng tham số thay vì chuỗi cố định

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Gán giá trị tham số và thêm % vào cả hai bên để tìm kiếm chính xác hơn
            st.setString(1, searchKeyword + " %");
            st.setString(2, "% " + searchKeyword + " %");
            st.setString(3, "% " + searchKeyword);
            st.setString(4, searchKeyword);

            ResultSet rs = st.executeQuery();

            // Loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Product> getAllHot() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product "
                + "ORDER BY ProductSold DESC\n"
                + "LIMIT 9;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int getCountProduct(String searchKeyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT COUNT(*) AS CountOfProducts\n"
                + "FROM product"
                + " WHERE ProductName LIKE ? \n"
                + "OR ProductName LIKE ? \n"
                + "OR ProductName LIKE ? \n"
                + "OR ProductName = ?;";
        // Sử dụng tham số thay vì chuỗi cố định
        int count = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Gán giá trị tham số và thêm % vào cả hai bên để tìm kiếm chính xác hơn
            st.setString(1, searchKeyword + " %");
            st.setString(2, "% " + searchKeyword + " %");
            st.setString(3, "% " + searchKeyword);
            st.setString(4, searchKeyword);

            ResultSet rs = st.executeQuery();

            // Loop until select the last object
            if (rs.next()) {
                count = rs.getInt("CountOfProducts");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    //ThaiNH
    public List<Product> getAllProductPaging(int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY Productid DESC LIMIT 16 OFFSET ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (indexPage - 1) * 16);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;

    }

    public List<Product> getAllProductOfCateIdPagingMar(int id, int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM categoryproduct AS cd\n"
                + "JOIN CategoryProductDetails AS cpd ON cd.CategoryProductId = cpd.CategoryProductId\n"
                + "JOIN Product AS p ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cd.CategoryProductId = ?\n"
                + "ORDER BY p.productid DESC LIMIT 16 OFFSET ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, (indexPage - 1) * 16);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductImageDefault"), rs.getDouble("ProductPrice"), rs.getDouble("ProductCost"), rs.getString("ProductDescription"), rs.getDouble("ProductRating"), rs.getInt("ProductSold"), rs.getString("ProductCreatedDate"), rs.getString("ProductStatus"), rs.getInt("EmployeeId"), rs.getInt("CategoryProductDetailsId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    // lấy ra tất cả sản phẩm theo thể loại
    public List<Product> getAllProductOfCateIdMar(int id, int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM categoryproduct AS cd\n"
                + "JOIN CategoryProductDetails AS cpd ON cd.CategoryProductId = cpd.CategoryProductId\n"
                + "JOIN Product AS p ON p.CategoryProductDetailsId = cpd.CategoryProductDetailsId\n"
                + "WHERE cd.CategoryProductId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductImageDefault"), rs.getDouble("ProductPrice"), rs.getDouble("ProductCost"), rs.getString("ProductDescription"), rs.getDouble("ProductRating"), rs.getInt("ProductSold"), rs.getString("ProductCreatedDate"), rs.getString("ProductStatus"), rs.getInt("EmployeeId"), rs.getInt("CategoryProductDetailsId"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    // Lấy ra id mới nhất vừa mới insert
    public int getInsertIdNewest() {
        int id = 0;
        String sql = "SELECT MAX(productid) FROM product;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            // Loop until select the last object
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public List<Product> filterProductMkt(String status, int cateProId, String sortRank, String searching) {
        List<Product> list = new ArrayList<>();
        String sql = "";
        if (searching != null) {
            sql = "SELECT * FROM Product WHERE ProductName LIKE '%" + searching + "%'";
            if (status != null) {
                sql += " AND productstatus like '%" + status + "%'";

            }
            if (sortRank != null) {
                sql += " ORDER BY PRODUCTRATING DESC";
            }
        } else {

            sql = "SELECT * FROM product AS p join categoryproductdetails\n"
                    + "AS cpd ON p.categoryproductdetailsid = cpd.categoryproductdetailsid\n"
                    + "where 1 = 1";
            if (cateProId != -1) {
                sql += " and cpd.categoryproductid = " + cateProId;
            }
            if (status != null) {
                sql += " AND p.productstatus like '%" + status + "%'";

            }
            if (sortRank != null) {
                sql += " ORDER BY P.PRODUCTRATING DESC";
            }
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Product> filterProductMktPaging(String status, int cateProId, String sortRank, String searching, int indexPage) {
        List<Product> list = new ArrayList<>();
        String sql = "";
        if (searching != null) {
            sql = "SELECT * FROM Product WHERE ProductName LIKE '%" + searching + "%'";
            if (status != null) {
                sql += " AND productstatus like '%" + status + "%'";

            }
            if (sortRank != null) {
                sql += " ORDER BY PRODUCTRATING DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            } else {
                sql += " ORDER BY ProductCreatedDate DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            }
        } else {

            sql = "SELECT * FROM product AS p join categoryproductdetails\n"
                    + "AS cpd ON p.categoryproductdetailsid = cpd.categoryproductdetailsid\n"
                    + "where 1 = 1";
            if (cateProId != -1) {
                sql += " and cpd.categoryproductid = " + cateProId;
            }
            if (status != null) {
                sql += " AND p.productstatus like '%" + status + "%'";
            }
            if (sortRank != null) {
                sql += " ORDER BY P.PRODUCTRATING DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            } else {
                sql += " ORDER BY P.ProductCreatedDate DESC LIMIT 16 OFFSET " + (indexPage - 1) * 16;
            }
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Product u = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    //AnhPh
    public List<Chart> getRatingByCategoryProductDetailsId(int cateProDetailsId) {
        List<Chart> list = new ArrayList<>();
        String sql = 
        """
            SELECT p.ProductId, p.ProductRating
            FROM product p LEFT JOIN CategoryProductDetails c ON c.CategoryProductDetailsId = p.CategoryProductDetailsId
            WHERE c.CategoryProductDetailsId = ?
            GROUP BY p.ProductId
        """;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cateProDetailsId);
            ResultSet rs = st.executeQuery();
            // loop until select the last object
            while (rs.next()) {
                Chart u = new Chart(rs.getInt(1), rs.getDouble(2));
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO a = new ProductDAO();
//        System.out.println("PRoduct = == = = " + a.getAll().size());
//        System.out.println("PRoduct = == = = " + a.getAll().size());
//        System.out.println("PRoduct = == = = " + a.getAll().size());
//        System.out.println("PRoduct = == = = " + a.getAll().size());
//        System.out.println("PRoduct = == = = " + a.getAll().size());
//        System.out.println("PRoduct = == = = " + a.getAll().size());
//            System.out.println(a.filterProductByProNameAndSize("100000 AND 200000", 1, 0).size());
//        Product acc = new Product(35, "Áo Sơ Mi Bất Đối Xứng", 120000, 160000, "Mô hình: Đồng bằng#Phong cách: Thanh lịch#Cộng với kích thước: Không#Chất liệu: Polyester#Thành phần: 95% Polyester + 5% Spandex#Loại phù hợp: Lỏng lẻo#Trong suốt: Không#Căng: Không giãn#Xuất xứ: Việt Nam", 4, 200, "on", 3, 1);
//        a.update(acc);
//        a.delete(35);
//        Product acc = new Product(0, "aaaa", 333330, 333330, "aaaaa", 3, 140, "on", 3, 1);
//        a.add(acc);
//        System.out.println(a.getById(3).getProDescription());
//        System.out.println(a.getAllProductByCateId(1).size());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getProName());
//        String[] l = {"M"};
//        List<Product> listq = a.filterProductByProNameAndSize("", l, 1, 1);
//        List<Product> list = a.filterProductByProNameAndSizePaging("", l, 1, 1, 1);
////        System.out.println(list.size());
//        System.out.println(list.size());
//        System.out.println(listq.size());
//
//        System.out.println(l.length);
//        List<Product> listq = a.filterSearchProductByProNameAndSize("200000 AND 300000", null, "Áo");
//        List<Product> list = a.filterSearchProductByProNameAndSizePaging("200000 AND 300000", null, "Áo", 1);
//        System.out.println(listq.size());
//        System.out.println(list.size());
//        System.out.println(a.getByIdStatusOn(1).getProName());
//        List<Product> list = a.getAllNew("áo");
//        System.out.println(list.toString());
        System.out.println(a.CalculateAVGRating(1));
    }
}
