package br.com.shopping.cart.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

    private String id;
    private UserInfo user;
    private List<Item> items;
    private BigDecimal totalPrice;

    public String getId() {
        return id;
    }

    public Cart setId(String id) {
        this.id = id;
        return this;
    }

    public UserInfo getUser() {
        return user;
    }

    public Cart setUser(UserInfo user) {
        this.user = user;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public Cart setItems(List<Item> items) {
        this.items = items;
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