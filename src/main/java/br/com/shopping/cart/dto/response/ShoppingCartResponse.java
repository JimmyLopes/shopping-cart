package br.com.shopping.cart.dto.response;

import br.com.shopping.cart.dto.ItemDTO;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartResponse {

    private Long id;
    private Long userId;
    private String userName;
    private List<ItemDTO> items;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public ShoppingCartResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ShoppingCartResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ShoppingCartResponse setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public ShoppingCartResponse setItems(List<ItemDTO> items) {
        this.items = items;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public ShoppingCartResponse setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}