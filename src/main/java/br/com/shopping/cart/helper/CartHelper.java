package br.com.shopping.cart.helper;

import br.com.shopping.cart.dto.ItemDTO;
import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.model.Cart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartHelper {

    public Cart toDomain (ShoppingCartRequest request) {
        return new Cart();
    }

    public ShoppingCartResponse toResponse(Cart domain) {
        return new ShoppingCartResponse()
                .setId(domain.getId())
                .setUserId(domain.getUser().getId())
                .setUserName(domain.getUser().getName())
                .setItems(domain.getItems().stream().map(item -> new ItemDTO().setProductName(item.getProduct().getName())).collect(Collectors.toList()))
                .setTotalPrice(domain.getTotalPrice());
    }
}
