
package io.healthathome.service;

import io.healthathome.models.Cart;
import io.healthathome.models.Item;
import io.healthathome.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository repository;
    @Autowired
    private ProductService productService;

    public io.healthathome.dto.Cart getCartByUser(String user) {
        Cart cartStored = repository.getByUserAndStateIsTrue(user);
        io.healthathome.dto.Cart cartDto = new io.healthathome.dto.Cart();
        cartDto.setUser(user);
        if (cartStored == null)
            return cartDto;
        cartDto.setItems(cartStored.getItems().stream()
                .map(x -> new io.healthathome.dto.Item(productService.getProductById(x.getProduct()), x.getQuantity()))
                .collect(Collectors.toList()));
        return cartDto;
    }

    public io.healthathome.dto.Cart addProduct(io.healthathome.dto.Item item, String idUser) {
        Cart cart = repository.getByUserAndStateIsTrue(idUser);
        if (cart == null)
            cart = new Cart();
        List items = cart.getItems();
        if (items == null)
            items = new ArrayList();
        Item itemModel = new Item();
        itemModel.setProduct(item.getProduct().getId());
        itemModel.setQuantity(item.getQuantity());
        items.add(itemModel);
        cart.setItems(items);
        cart.setState(true);
        cart.setUser(idUser);
        return Mapper.map(repository.save(cart));
    }



}
