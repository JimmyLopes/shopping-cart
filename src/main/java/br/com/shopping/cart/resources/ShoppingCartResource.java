package br.com.shopping.cart.resources;

import br.com.shopping.cart.dto.request.ShoppingCartRequest;
import br.com.shopping.cart.dto.response.ShoppingCartResponse;
import br.com.shopping.cart.services.ShoppingCartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartResource {
    private ShoppingCartService shoppingCartService;

    public ShoppingCartResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ShoppingCartResponse submit(@RequestBody ShoppingCartRequest requestDTO) {
        return shoppingCartService.purchase(requestDTO);
    }
}
