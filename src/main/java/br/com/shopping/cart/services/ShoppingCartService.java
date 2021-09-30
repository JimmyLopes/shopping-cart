package br.com.shopping.cart.services;

import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.helper.CartHelper;
import br.com.shopping.cart.model.Cart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ShoppingCartService {

    private final IntegrationService integrationService;
    private final CartHelper cartHelper;

    public ShoppingCartService(IntegrationService integrationService, CartHelper cartHelper) {
        this.integrationService = integrationService;
        this.cartHelper = cartHelper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ShoppingCartResponse purchase(ShoppingCartRequest request) {
        var shoppingCart = new Cart();
        shoppingCart.setUser(integrationService.getRemoteUserInfo(request.getUserId()));
        shoppingCart.setItems(integrationService.getRemoteProductItemsInfo(shoppingCart.getItems()));
        integrationService.submitToBilling(shoppingCart);
        integrationService.notifyToDelivery(shoppingCart);
        integrationService.askForUserReview(shoppingCart);
        return cartHelper.toResponse(shoppingCart);
    }

    private BigDecimal calculatedTotalPrice(Cart cart) {
        if (!cart.getItems().isEmpty()) {
            return cart.getItems().stream()
                    .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } return BigDecimal.ONE;
    }
}
