package br.com.shopping.cart.dto.request;

import br.com.shopping.cart.dto.ItemDTO;

import java.util.List;

public class ShoppingCartRequest {

    private Long userId;
    private List<ItemDTO> items;

    public Long getUserId() {
        return userId;
    }

    public ShoppingCartRequest setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public ShoppingCartRequest setItems(List<ItemDTO> items) {
        this.items = items;
        return this;
    }
}
