package br.com.shopping.cart.helper;

import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartHelper {

    public Cart toDomain (ShoppingCartRequest request) {
        return new Cart();
    }

    public ShoppingCartResponse toResponse(Cart domain) {
        return new ShoppingCartResponse()
                .setId(domain.getId())
                .setUserId(domain.getUserId())
                .setTotalPrice(domain.getTotalPrice());
    }
}
