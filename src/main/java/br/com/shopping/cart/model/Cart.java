package br.com.shopping.cart.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public Cart setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Cart setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Cart setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}