package mini;
import java.time.LocalDate;
import java.util.List;

public interface SalesDAO {
    void addSaleItem(SaleItem item);
    List<SaleItem> getAllSaleItems();
    List<SaleItem> getSaleItemsByDate(LocalDate date);
}
