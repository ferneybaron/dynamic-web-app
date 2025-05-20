package com.fbaron.dao;

import com.fbaron.model.ProductModel;
import com.fbaron.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ferney Estupinan Baron
 */
public class ProductDAOImpl implements ProductDAO {

    @Override
    public void insertProduct(ProductModel model) {
    String  sql = "INSERT INTO product (name, description, interestRate) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, model.getName());
                statement.setString(2, model.getDescription());
                statement.setBigDecimal(3, model.getInterestRate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("ProductDAOImpl failed to insert product: " + e.getMessage());
        }
    }

    @Override
    public ProductModel getProductById(long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    ProductModel productModel = new ProductModel();
                    productModel.setId(resultSet.getLong("id"));
                    productModel.setName(resultSet.getString("name"));
                    productModel.setDescription(resultSet.getString("description"));
                    productModel.setInterestRate(resultSet.getBigDecimal("interestRate"));
                    return productModel;
                }
            }
        } catch (SQLException e) {
            System.err.println("ProductDAOImpl failed to select product by id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ProductModel> getAllProducts() {
        String sql = "SELECT * FROM product";
        try (Connection connection = ConnectionManager.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                List<ProductModel> products = new ArrayList<>();
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ProductModel productModel = new ProductModel();
                    productModel.setId(resultSet.getLong("id"));
                    productModel.setName(resultSet.getString("name"));
                    productModel.setDescription(resultSet.getString("description"));
                    productModel.setInterestRate(resultSet.getBigDecimal("interestRate"));
                    products.add(productModel);
                }

                return products;
            }
        } catch (SQLException e) {
            System.err.println("ProductDAOImpl failed to select all products: " + e.getMessage());
        }
        return List.of();
    }

}
