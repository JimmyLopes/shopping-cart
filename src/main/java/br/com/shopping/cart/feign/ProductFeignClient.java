package br.com.shopping.cart.feign;

import br.com.shopping.cart.model.ProductOverview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCT-CATALOG-SERVICE")
public interface ProductFeignClient {

    @GetMapping("/api/product/{id}")
    ProductOverview findById(@PathVariable("id") Long id);

}
