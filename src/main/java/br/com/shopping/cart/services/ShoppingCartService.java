package br.com.shopping.cart.services;

import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.helper.CartHelper;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
        shoppingCart.setItems(integrationService.getRemoteProductItemsInfo(request.getItems()));
        shoppingCart.setTotalPrice(calculatedTotalPrice(shoppingCart.getItems()));
        integrationService.submitToBilling(shoppingCart);
        integrationService.notifyToDelivery(shoppingCart);
        integrationService.askForUserReview(shoppingCart);
        return cartHelper.toResponse(shoppingCart);
    }

    private BigDecimal calculatedTotalPrice(List<Item> items) {
        if (!items.isEmpty()) {
            return items.stream()
                    .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } return BigDecimal.ONE;
    }
}
