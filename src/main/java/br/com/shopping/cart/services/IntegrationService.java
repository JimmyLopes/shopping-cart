package br.com.shopping.cart.services;

import br.com.shopping.cart.feign.ProductFeignClient;
import br.com.shopping.cart.feign.UserFeignClient;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Item;
import br.com.shopping.cart.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provide remote and mock implementations to others integration services,
 * like billing, delivery and review services.
 */
@Service
public class IntegrationService {

    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;

    public IntegrationService(UserFeignClient userFeignClient,
                              ProductFeignClient productFeignClient) {
        this.userFeignClient = userFeignClient;
        this.productFeignClient = productFeignClient;
    }


    public UserInfo getRemoteUserInfo(Long userId) {
        return userFeignClient.findById(userId);
    }

    public List<Item> getRemoteProductItemsInfo(List<Item> items) {
        items.forEach(item -> {
            var product = productFeignClient.findById(item.getProduct().getId());
            item.setProduct(product);
        });

        return items;
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
