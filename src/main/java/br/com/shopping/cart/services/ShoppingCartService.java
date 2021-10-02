package br.com.shopping.cart.services;

import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.helper.CartHelper;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    private final IntegrationService integrationService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartHelper cartHelper;

    public ShoppingCartService(IntegrationService integrationService, ShoppingCartRepository shoppingCartRepository, CartHelper cartHelper) {
        this.integrationService = integrationService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartHelper = cartHelper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ShoppingCartResponse purchase(ShoppingCartRequest request) {
        var shoppingCart = shoppingCartRepository.save(new Cart());
        shoppingCart.setUserId(integrationService.getRemoteUserInfo(request.getUserId()).getId());
        shoppingCart.setTotalPrice(integrationService.getRemoteProductItemsInfo(request.getItems(), shoppingCart.getId()));
        integrationService.submitToBilling(shoppingCart);
        integrationService.notifyToDelivery(shoppingCart);
        integrationService.askForUserReview(shoppingCart);
        return cartHelper.toResponse(shoppingCart);
    }
}
