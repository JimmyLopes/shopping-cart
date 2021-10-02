package br.com.shopping.cart.repository;


import br.com.shopping.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cart", path = "cart")
public interface ShoppingCartRepository extends JpaRepository<Cart, Long> {  }

