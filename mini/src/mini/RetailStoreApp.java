package mini;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class RetailStoreApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            return;
        }

        InventoryDAO inventoryDAO = new InventoryDAOImpl(connection);
        SalesDAO salesDAO=new SalesDAOImpl(connection);
        SupplierDAO supplierDAO = new SupplierDAOImpl(connection);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Inventory and Sales Management App =====");
            System.out.println("1. Add new item to inventory");
            System.out.println("2. Update item quantity");
            System.out.println("3. View all items in inventory");
            System.out.println("4. Delete items from inventory");
            System.out.println("5. Add new sale record");
            System.out.println("6. View all sale records");
            System.out.println("7. Add new supplier");
            System.out.println("8. Update supplier");
            System.out.println("9. Delete supplier");
            System.out.println("10. View all suppliers");
            System.out.println("11. Terminate");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addNewItemToInventory(scanner, inventoryDAO);
                    break;
                case 2:
                    updateItemQuantity(scanner, inventoryDAO);
                    break;
                case 3:
                    viewAllItemsInInventory(inventoryDAO);
                    break;
                case 4:
                	deleteItemFromInventory(scanner,inventoryDAO);
                	break;
                case 5:
                    addNewSaleRecord(scanner, salesDAO);
                    break;
                case 6:
                    viewAllSaleRecords(salesDAO);
                    break;
                case 7:
                    addNewSupplier(scanner, supplierDAO);
                    break;
                case 8:
                    updateSupplier(scanner, supplierDAO);
                    break;
                case 9:
                    deleteSupplier(scanner, supplierDAO);
                    break;
                case 10:
                    viewAllSuppliers(supplierDAO);
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewItemToInventory(Scanner scanner, InventoryDAO inventoryDAO) {
        System.out.println("\n===== Add New Item to Inventory =====");
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 

        InventoryItem newItem = new InventoryItem(quantity, productName, quantity, price);
        inventoryDAO.addInventoryItem(newItem);

        System.out.println("Item added successfully to the inventory.");
    }
    
    private static void addNewSaleRecord(Scanner scanner, SalesDAO salesDAO) {
        System.out.println("\n===== Add New Sale Record =====");
        System.out.print("Enter sale date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter total amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        try {
            LocalDate saleDate = LocalDate.parse(dateStr);
            SaleItem newSale = new SaleItem(saleDate, totalAmount);
            salesDAO.addSaleItem(newSale);
            System.out.println("Sale record added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid date format. Sale record not added.");
        }
    }
    

    private static void viewAllSaleRecords(SalesDAO salesDAO) {
        System.out.println("\n===== View All Sale Records =====");
        System.out.println("Sale Date\tTotal Amount");

        for (SaleItem saleItem : salesDAO.getAllSaleItems()) {
            System.out.println(saleItem.getSaleDate() + "\t" + saleItem.getTotalAmount());
        }
    }
    
    private static void addNewSupplier(Scanner scanner, SupplierDAO supplierDAO) {
        System.out.println("\n===== Add New Supplier =====");
        System.out.print("Enter supplier name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contactInfo = scanner.nextLine();

        Supplier newSupplier = new Supplier(0, supplierName, contactInfo);
        supplierDAO.addSupplier(newSupplier);

        System.out.println("Supplier added successfully.");
    }
    
    private static void updateSupplier(Scanner scanner, SupplierDAO supplierDAO) {
        System.out.println("\n===== Update Supplier =====");
        System.out.print("Enter the ID of the supplier you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Supplier supplierToUpdate = supplierDAO.getSupplierById(id);
        if (supplierToUpdate == null) {
            System.out.println("Supplier with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter new supplier name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter new contact info: ");
        String contactInfo = scanner.nextLine();

        supplierToUpdate.setSupplierName(supplierName);
        supplierToUpdate.setContactInfo(contactInfo);
        supplierDAO.updateSupplier(supplierToUpdate);

        System.out.println("Supplier updated successfully.");
    }

    private static void deleteSupplier(Scanner scanner, SupplierDAO supplierDAO) {
        System.out.println("\n===== Delete Supplier =====");
        System.out.print("Enter the ID of the supplier you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        supplierDAO.deleteSupplier(id);

        System.out.println("Supplier deleted successfully.");
    }

    private static void viewAllSuppliers(SupplierDAO supplierDAO) {
        System.out.println("\n===== View All Suppliers =====");
        System.out.println("ID\tSupplier Name\tContact Info");

        for (Supplier supplier : supplierDAO.getAllSuppliers()) {
            System.out.println(supplier.getId() + "\t" + supplier.getSupplierName() + "\t" +
                    supplier.getContactInfo());
        }
    }
    
    private static void updateItemQuantity(Scanner scanner, InventoryDAO inventoryDAO) {
        System.out.println("\n===== Update Item Quantity =====");
        System.out.print("Enter the ID of the item you want to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter the new quantity: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine(); 

        InventoryItem itemToUpdate = inventoryDAO.getInventoryItemById(id);
        if (itemToUpdate == null) {
            System.out.println("Item with ID " + id + " not found in the inventory.");
            return;
        }

        itemToUpdate.setQuantity(newQuantity);
        inventoryDAO.updateInventoryItem(itemToUpdate);

        System.out.println("Item quantity updated successfully.");
    }
    
    private static void deleteItemFromInventory(Scanner scanner, InventoryDAO inventoryDAO) {
        System.out.println("\n===== Delete Item from Inventory =====");
        System.out.print("Enter the ID of the item you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        inventoryDAO.deleteInventoryItem(id);

        System.out.println("Item deleted successfully from the inventory.");
        
    }

    private static void viewAllItemsInInventory(InventoryDAO inventoryDAO) {
        System.out.println("\n===== View All Items in Inventory =====");
        System.out.println("ID\tProduct Name\tQuantity\tPrice");

        for (InventoryItem item : inventoryDAO.getAllInventoryItems()) {
            System.out.println(item.getId() + "\t" + item.getProductName() + "\t\t" +
                    item.getQuantity() + "\t\t" + item.getPrice());
        }
    }
}
