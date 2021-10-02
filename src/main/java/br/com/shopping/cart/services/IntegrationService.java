package br.com.shopping.cart.services;

import br.com.shopping.cart.dto.ItemDTO;
import br.com.shopping.cart.feign.ProductFeignClient;
import br.com.shopping.cart.feign.UserFeignClient;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Item;
import br.com.shopping.cart.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private final ItemService itemService;

    public IntegrationService(UserFeignClient userFeignClient,
                              ProductFeignClient productFeignClient,
                              ItemService itemService) {
        this.userFeignClient = userFeignClient;
        this.productFeignClient = productFeignClient;
        this.itemService = itemService;
    }


    public UserInfo getRemoteUserInfo(Long userId) {
        return userFeignClient.findById(userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BigDecimal getRemoteProductItemsInfo(List<ItemDTO> items, Long shoppingCartId) {
        List<Item> purcharseItems = new ArrayList<>();
        items.forEach(item -> {
            var product = productFeignClient.findById(item.getProductId());
            purcharseItems.add(new Item(product, item.getQuantity(),shoppingCartId));
        });

        itemService.saveItems(purcharseItems);

        return calculatedTotalPrice(purcharseItems);
    }

    private BigDecimal calculatedTotalPrice(List<Item> items) {
        if (!items.isEmpty()) {
            return items.stream()
                    .map(item -> item.getProductPrice().multiply(new BigDecimal(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } return BigDecimal.ZERO;
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
