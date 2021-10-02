package br.com.shopping.cart.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long cartId;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;

    public Item() {}

    public Item(ProductOverview product, int quantity, Long cartId) {
        this.cartId = cartId;
        this.productId = product.getId();
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCartId() {
        return cartId;
    }

    public Item setCartId(Long cartId) {
        this.cartId = cartId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public Item setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Item setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Item setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
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
