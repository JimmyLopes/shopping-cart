package br.com.shopping.cart.model;


public class Item {
    private ProductOverview product;
    private int quantity;

    public ProductOverview getProduct() {
        return product;
    }

    public Item setProduct(ProductOverview product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
