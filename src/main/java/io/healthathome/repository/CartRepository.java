package io.healthathome.repository;

import io.healthathome.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

    Cart getByUserAndStateIsTrue(String user);

    Cart getByCartId(String cartId);


}
