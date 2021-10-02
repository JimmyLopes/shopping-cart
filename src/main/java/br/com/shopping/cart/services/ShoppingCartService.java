package br.com.shopping.cart.services;

import br.com.shopping.cart.commons.shopping.cart.commons.exception.ServiceException;
import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.helper.CartHelper;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Item;
import br.com.shopping.cart.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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

    @Transactional(noRollbackFor = ServiceException.class)
    public ShoppingCartResponse purchase(ShoppingCartRequest request) {
        var shoppingCart = shoppingCartRepository.save(new Cart());
        shoppingCart.setUserId(integrationService.getRemoteUserInfo(request.getUserId()).getId());
        List<Item> cartsItems = integrationService.getRemoteProductItems(request.getItems(), shoppingCart.getId());
        shoppingCart.setTotalPrice(calculatedTotalPrice(cartsItems));
        integrationService.submitToBilling(shoppingCart);
        integrationService.notifyToDelivery(shoppingCart);
        integrationService.askForUserReview(shoppingCart);
        return cartHelper.toResponse(shoppingCart, cartsItems);
    }

    private BigDecimal calculatedTotalPrice(List<Item> items) {
        if (!items.isEmpty()) {
            return items.stream()
                    .map(item -> item.getProductPrice().multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } return BigDecimal.ZERO;
    }
}
