package io.healthathome.service;

import io.healthathome.dto.Product;
import io.healthathome.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper mapper;

    public io.healthathome.dto.Product getProductById(String id) {
        return map(repository.findByIdProduct(id));
    }

    public io.healthathome.dto.Product getProductByName(String name) {
        return map(repository.findByName(name));
    }

    public io.healthathome.dto.Product insertOrUpdate(Product product) {
        return map(repository.insert(map(product)));
    }

    public io.healthathome.dto.Product update(Product product) {
        return map(repository.save(map(product)));
    }

    public void delete(String id) {
        repository.delete(repository.findByIdProduct(id));
    }

    public List<io.healthathome.dto.Product> getProductByCategoryId(String id) {
        return repository.getProductByCategoryId(id).stream().map(x -> map(x)).collect(Collectors.toList());
    }

    private Product map(io.healthathome.models.Product product) {
        return mapper.map(product, Product.class);
    }

    private io.healthathome.models.Product map(Product product) {
        return mapper.map(product, io.healthathome.models.Product.class);
    }
}
