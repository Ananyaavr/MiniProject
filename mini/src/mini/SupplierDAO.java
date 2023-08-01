package mini;
import java.util.List;

public interface SupplierDAO {
    void addSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(int id);
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(int id);
}
