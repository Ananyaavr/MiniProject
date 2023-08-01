package mini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesDAOImpl implements SalesDAO {
    private Connection connection;

    public SalesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addSaleItem(SaleItem item) {
        try {
            String query = "INSERT INTO sales (sale_date, total_amount) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDate(1, java.sql.Date.valueOf(item.getSaleDate()));
            preparedStatement.setDouble(2, item.getTotalAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SaleItem> getAllSaleItems() {
        List<SaleItem> saleItems = new ArrayList<>();

        try {
            String query = "SELECT * FROM sales";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocalDate saleDate = resultSet.getDate("sale_date").toLocalDate();
                double totalAmount = resultSet.getDouble("total_amount");

                SaleItem saleItem = new SaleItem(saleDate, totalAmount);
                saleItems.add(saleItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saleItems;
    }

    @Override
    public List<SaleItem> getSaleItemsByDate(LocalDate date) {
        List<SaleItem> saleItems = new ArrayList<>();

        try {
            String query = "SELECT * FROM sales WHERE sale_date=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                double totalAmount = resultSet.getDouble("total_amount");

                SaleItem saleItem = new SaleItem(date, totalAmount);
                saleItems.add(saleItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saleItems;
    }
}
