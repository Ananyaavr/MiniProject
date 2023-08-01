package mini;

import java.util.List;

public interface InventoryDAO {
    void addInventoryItem(InventoryItem item);
    void updateInventoryItem(InventoryItem item);
    void deleteInventoryItem(int id);
    List<InventoryItem> getAllInventoryItems();
    InventoryItem getInventoryItemById(int id);
}
