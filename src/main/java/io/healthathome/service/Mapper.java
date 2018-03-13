package io.healthathome.service;

import io.healthathome.models.Address;
import io.healthathome.models.Cart;
import io.healthathome.models.Product;
import io.healthathome.models.User;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Cart map(io.healthathome.dto.Cart cart) {
        return isNull(cart) ? null : mapper.map(cart, Cart.class);
    }
    public static io.healthathome.dto.Cart map(Cart cart) {
        return isNull(cart) ? null : mapper.map(cart, io.healthathome.dto.Cart.class);
    }
    public static User map(io.healthathome.dto.User user) {
        return isNull(user) ? null : mapper.map(user, User.class);
    }
    public static io.healthathome.dto.User map(User user) {
        return isNull(user) ? null : mapper.map(user, io.healthathome.dto.User.class);
    }
    public static Product map(io.healthathome.dto.Product product) {
        return isNull(product) ? null : mapper.map(product, Product.class);
    }
    public static io.healthathome.dto.Product map(Product product) {
        return isNull(product) ? null : mapper.map(product, io.healthathome.dto.Product.class);
    }
    public static Address map(io.healthathome.dto.payu.Address address) {
        return isNull(address) ? null : mapper.map(address, Address.class);
    }

    private static <T> boolean isNull(T object) {
        return object == null;
    }
}
