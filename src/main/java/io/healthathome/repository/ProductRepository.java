package io.healthathome.repository;

import io.healthathome.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);

    @Query("{'id':'?0'}")
    Product findByIdProduct(String idProduct);


    @Query("{'category.id':'?0'}")
    List<Product> getProductByCategoryId(String id);
}
