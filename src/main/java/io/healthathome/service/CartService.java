
package io.healthathome.service;

import io.healthathome.models.Cart;
import io.healthathome.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository repository;
    @Autowired
    private ModelMapper mapper;

    public io.healthathome.dto.Cart getCartByUser(String user) {
        return map(repository.getCartByUser(user));
    }

    public io.healthathome.dto.Cart getCartByState(String state) {
        return map(repository.getCartByState(state));
    }

    private Cart map(io.healthathome.dto.Cart cart) {
        return mapper.map(cart, Cart.class);
    }

    private io.healthathome.dto.Cart map(Cart cart) {
        return mapper.map(cart, io.healthathome.dto.Cart.class);
    }


}
