package br.com.shopping.cart.dto;

public class ItemDTO {

    private Long productId;
    private String productName;
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public ItemDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ItemDTO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}