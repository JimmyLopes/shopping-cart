package br.com.shopping.cart.helper;

import br.com.shopping.cart.dto.ItemDTO;
import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartHelper {

    public Cart toDomain (ShoppingCartRequest request) {
        return new Cart();
    }

    public ShoppingCartResponse toResponse(Cart domain, List<Item> cartsItems) {
        return new ShoppingCartResponse()
                .setId(domain.getId())
                .setUserId(domain.getUserId()) //todo ver distinct
                .setItems(cartsItems.stream().distinct().map(item -> new ItemDTO().setProductId(item.getProductId())
                                                                        .setProductName(item.getProductName())
                                                                        .setQuantity(item.getQuantity())
                                                                        .setProductPrice(item.getProductPrice()))
                                                                        .collect(Collectors.toList()))
                .setTotalPrice(domain.getTotalPrice());
    }
}
