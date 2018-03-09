package io.healthathome.service;

import io.healthathome.model.Product;
import io.healthathome.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    public Product getProductById(String id) {
        return repository.findByIdProduct(id);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public Product insertOrUpdate(Product product) {
        return repository.insert(product);
    }

    public Product update(Product product) {
        int a = 0;
        return repository.save(product);
    }

    public void delete(String id) {
        repository.delete(repository.findByIdProduct(id));
    }

    public List<Product> getProductByCategoryId(String id) {
        return repository.getProductByCategoryId(id);
    }
}
