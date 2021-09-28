package br.com.shopping.cart.services;

import br.com.shopping.cart.model.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartService {

    public Cart purchase(Cart shoopingCart) {
//        var uuid = UUID.randomUUID().toString();
//        shoppingCart.setId(uuid);
//        var user = integrationService.getRemoteUserInfo(shoppingCart.getUser().getId());
//        shoppingCart.setUser(user);
//        var items = integrationService.getRemoteProductItemsInfo(shoppingCart.getItems());
//        shoppingCart.setItems(items);
//        integrationService.submitToBilling(shoppingCart);
//        integrationService.notifyToDelivery(shoppingCart);
//        integrationService.askForUserReview(shoppingCart);
        return null;
    }

    private BigDecimal calculatedTotalPrice(Cart cart) {
        if (!cart.getItems().isEmpty()) {
            return cart.getItems().stream()
                    .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } return BigDecimal.ONE;
    }
}
