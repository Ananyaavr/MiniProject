package mini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    private Connection connection;

    public SupplierDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addSupplier(Supplier supplier) {
        try {
            String query = "INSERT INTO suppliers (supplier_name, contact_info) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getContactInfo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        try {
            String query = "UPDATE suppliers SET supplier_name=?, contact_info=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getContactInfo());
            preparedStatement.setInt(3, supplier.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSupplier(int id) {
        try {
            String query = "DELETE FROM suppliers WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            String query = "SELECT * FROM suppliers";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String supplierName = resultSet.getString("supplier_name");
                String contactInfo = resultSet.getString("contact_info");

                Supplier supplier = new Supplier(id, supplierName, contactInfo);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    @Override
    public Supplier getSupplierById(int id) {
        try {
            String query = "SELECT * FROM suppliers WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String supplierName = resultSet.getString("supplier_name");
                String contactInfo = resultSet.getString("contact_info");

                return new Supplier(id, supplierName, contactInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

