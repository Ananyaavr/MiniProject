package mini;

import java.time.LocalDate;

public class SaleItem {
    private LocalDate saleDate;
    private double totalAmount;

    public SaleItem(LocalDate saleDate, double totalAmount) {
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
    }

    // Getters and setters for the attributes
    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
