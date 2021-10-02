package br.com.shopping.cart.services;

import br.com.shopping.cart.model.Item;
import br.com.shopping.cart.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItems(List<Item> items) {
        itemRepository.saveAll(items);
    }

    public List<Item> getCartItems(Long cartId) {
        return itemRepository.findAllByCartId(cartId);
    }
}
