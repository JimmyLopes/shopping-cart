package br.com.shopping.cart.services;

import br.com.shopping.cart.feign.ProductFeignClient;
import br.com.shopping.cart.feign.UserFeignClient;
import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Item;
import br.com.shopping.cart.model.ProductOverview;
import br.com.shopping.cart.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Provide remote and mock implementations to others integration services,
 * like billing, delivery and review services.
 */
@Service
public class IntegrationService {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;

    private final String USER_SERVICE_NAME = "USER-INFORMATION-SERVICE";
    private final String PRODUCT_SERVICE_NAME = "PRODUCT-CATALOG-SERVICE";

    public IntegrationService(RestTemplate restTemplate,
                              WebClient.Builder webClientBuilder,
                              UserFeignClient userFeignClient,
                              ProductFeignClient productFeignClient) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
        this.userFeignClient = userFeignClient;
        this.productFeignClient = productFeignClient;
    }


    public UserInfo getRemoteUserInfo(Long userId) {

        // IMPLEMENTATION 01: using RestTemplate
        // return fetchDataWithRestTemplate("http://" + USER_SERVICE_NAME + "/api/user/", userId, UserInfo.class);

        // IMPLEMENTATION 02: using WebClient
        // return fetchDataWithWebClient("http://" + USER_SERVICE_NAME + "/api/user/", userId, UserInfo.class);

        // IMPLEMENTATION 03: using Feign
        return userFeignClient.findById(userId);
    }

    public List<Item> getRemoteProductItemsInfo(List<Item> items) {
        items.forEach(item -> {

            // IMPLEMENTATION 01: using RestTemplate
            // var product = fetchDataWithRestTemplate("http://" + PRODUCT_SERVICE_NAME + "/api/product", item.getProduct().getId(), ProductOverview.class);

            // IMPLEMENTATION 02: using WebClient
            // var product = fetchDataWithWebClient("http://" + PRODUCT_SERVICE_NAME  + "/api/product", item.getProduct().getId(), ProductOverview.class);

            // IMPLEMENTATION 03: using Feign
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


    // Generic implementation for Rest Template call
    private <T> T fetchDataWithRestTemplate(String url, Long id, Class<T> clazz) {
        return restTemplate.getForObject(url + id, clazz);
    }

    // Generic implementation for WebClient
    private <T> T fetchDataWithWebClient(String url, Long id, Class<T> clazz) {
        return webClientBuilder.build().get()
                .uri(url + id).retrieve()
                .bodyToMono(clazz)
                .block(); // makes sync
    }

    @Deprecated   // replaced by generic method
    private UserInfo fetchUserWithRestTemplate(Long userId) {
        return restTemplate.getForObject("http://" + USER_SERVICE_NAME + "/api/user/" + userId, UserInfo.class);
    }

    @Deprecated   // replaced by generic method
    private ProductOverview fetchProductWithRestTemplate(Long productId) {
        return restTemplate.getForObject("http://" + PRODUCT_SERVICE_NAME + "/api/product/" + productId, ProductOverview.class);
    }
}
