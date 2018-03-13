package io.healthathome.models;

public class Item {
    private String product;
    private Integer quantity;
    private double eachPrice;

    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public double getEachPrice() {
        return eachPrice;
    }
    public void setEachPrice(double eachPrice) {
        this.eachPrice = eachPrice;
    }
}
