package mini;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public  class InventoryDAOImpl implements InventoryDAO  {
    private Connection connection;

    public InventoryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addInventoryItem(InventoryItem item) {
        try {
            String query = "INSERT INTO inventory (product_name, quantity, price) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, item.getProductName());
            preparedStatement.setInt(2, item.getQuantity());
            preparedStatement.setDouble(3, item.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInventoryItem(InventoryItem item) {
        try {
            String query = "UPDATE inventory SET product_name=?, quantity=?, price=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, item.getProductName());
            preparedStatement.setInt(2, item.getQuantity());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setInt(4, item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteInventoryItem(int id) {
        try {
            String query = "DELETE FROM inventory WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> items = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM inventory";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("product_name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                InventoryItem item = new InventoryItem(id, productName, quantity, price);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    
    @Override
    public InventoryItem getInventoryItemById(int id) {
        try {
            String query = "SELECT * FROM inventory WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                return new InventoryItem(id, productName, quantity, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

