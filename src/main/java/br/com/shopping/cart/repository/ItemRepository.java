package br.com.shopping.cart.repository;

import br.com.shopping.cart.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends JpaRepository<Item, Long> {  }