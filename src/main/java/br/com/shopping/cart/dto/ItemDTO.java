package br.com.shopping.cart.dto;

import java.math.BigDecimal;

public class ItemDTO {

    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantity;

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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public ItemDTO setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ItemDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}