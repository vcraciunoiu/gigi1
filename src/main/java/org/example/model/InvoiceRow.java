package org.example.model;

public class InvoiceRow {

    private String description;
    private Double quantity = 1.0;
    private String unit = "h";
    private Double price = 1.0;

    public Double getRowtotal() {
        if (price == null || quantity == null) {
            return 0.0;
        } else {
            return price * quantity;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
