package mini;
public class Supplier {
    private int id;
    private String supplierName;
    private String contactInfo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public Supplier(int id, String supplierName, String contactInfo) {
		super();
		this.id = id;
		this.supplierName = supplierName;
		this.contactInfo = contactInfo;
	}
}
