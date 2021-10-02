package br.com.shopping.cart.services;

import br.com.shopping.cart.dto.ItemDTO;
import br.com.shopping.cart.feign.ProductFeignClient;
import br.com.shopping.cart.feign.UserFeignClient;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Item;
import br.com.shopping.cart.model.ProductOverview;
import br.com.shopping.cart.model.UserInfo;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide remote and mock implementations to others integration services,
 * like billing, delivery and review services.
 */
@Service
public class IntegrationService {

    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final ItemService itemService;

    public IntegrationService(UserFeignClient userFeignClient,
                              ProductFeignClient productFeignClient,
                              CircuitBreakerFactory circuitBreakerFactory,
                              ItemService itemService) {
        this.userFeignClient = userFeignClient;
        this.productFeignClient = productFeignClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.itemService = itemService;
    }


    public UserInfo getRemoteUserInfo(Long userId) {
        return createCircuitBreaker().run(() -> userFeignClient.findById(userId), throwable -> this.findUserByIdFallBack(userId));
    }

    private UserInfo findUserByIdFallBack(Long userId) {
        return new UserInfo(userId, "name info unavailable");
    }

    public ProductOverview getRemoteProduct(Long productId) {
        return createCircuitBreaker().run(() -> productFeignClient.findById(productId), throwable -> this.findProductByIdFallBack(productId));
    }

    public ProductOverview findProductByIdFallBack(Long productId) {
        return new ProductOverview(productId, "product name unavailable");
    }

    private CircuitBreaker createCircuitBreaker() {
        return circuitBreakerFactory.create("circuitbreaker");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Item> getRemoteProductItems(List<ItemDTO> items, Long shoppingCartId) {
        List<Item> purcharseItems = new ArrayList<>();
        items.forEach(item -> {
            var product = getRemoteProduct(item.getProductId());
            purcharseItems.add(new Item(product, item.getQuantity(), shoppingCartId));
        });

        itemService.saveItems(purcharseItems);

        return itemService.getCartItems(shoppingCartId);
    }



    public void submitToBilling(Cart shoppingCart) {
        // Pretend to submit to Billing Service
    }

    public void notifyToDelivery(Cart shoppingCart) {
        // Pretend to submit to Delivery Service
    }

    public void askForUserReview(Cart shoppingCart) {
        // Pretend to submit to Review Service
    }
}
