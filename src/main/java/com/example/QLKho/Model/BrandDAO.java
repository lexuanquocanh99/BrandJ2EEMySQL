package com.example.QLKho.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    private String url = "jdbc:mysql://localhost:3306/quanlykho?useSSL=false";
    private String username = "root";
    private String password = "";
    Connection connection = null;

    private static final String Insert_Brand_Query = "INSERT INTO brand (name, is_enabled, description) VALUES (?, ?, ?);";
    private static final String Select_Brand_By_Id_Query = "SELECT id, name, is_enabled, description FROM brand WHERE id = ?;";
    private static final String Select_All_Brands_Query = "SELECT * FROM brand;";
    private static final String Update_Brand_Query = "UPDATE brand SET name = ?, is_enabled = ?, description = ? WHERE id = ?;";
    private static final String Delete_Brand_Query = "DELETE FROM brand WHERE id = ?;";

    public BrandDAO() {}

    public void openConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

   public boolean insertBrand(Brand brand) throws SQLException {
        openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Insert_Brand_Query);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setInt(2, brand.getIs_enabled());
        preparedStatement.setString(3, brand.getDescription());
        boolean insertRow = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return insertRow;
   }

   public List< Brand > getAllBrands() throws SQLException {
        openConnection();
        List brandList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Select_All_Brands_Query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString(2);
            int is_enabled = resultSet.getInt(3);
            String description = resultSet.getString(4);
            Brand brand = new Brand(id, name, is_enabled, description);
            brandList.add(brand);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return brandList;
   }

   public boolean deleteBrand(Brand brand) throws SQLException {
        openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Delete_Brand_Query);
        preparedStatement.setInt(1, brand.getId());
        boolean is_deleted = preparedStatement.execute();
        preparedStatement.close();
        connection.close();
        return is_deleted;
   }

   public boolean updateBrand(Brand brand) throws SQLException {
        openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Update_Brand_Query);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setInt(2, brand.getIs_enabled());
        preparedStatement.setString(3, brand.getDescription());
        preparedStatement.setInt(4, brand.getId());
        boolean is_updated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return is_updated;
   }

   public Brand getBrandById(int id) throws SQLException {
        openConnection();
        Brand brand = null;
        PreparedStatement preparedStatement = connection.prepareStatement(Select_Brand_By_Id_Query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(2);
            int is_enabled = resultSet.getInt(3);
            String description = resultSet.getString(4);
            brand = new Brand(id, name, is_enabled, description);
        }
        return brand;
   }

}
