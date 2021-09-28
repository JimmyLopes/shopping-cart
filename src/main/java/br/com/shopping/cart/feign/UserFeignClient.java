package br.com.shopping.cart.feign;

import br.com.shopping.cart.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-INFORMATION-SERVICE")
public interface UserFeignClient {

    @GetMapping("/api/user/{id}")
    UserInfo findById(@PathVariable("id") Long id);

}