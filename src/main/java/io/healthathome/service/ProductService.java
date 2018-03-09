package io.healthathome.service;

import io.healthathome.models.Product;
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
        return map(repository.findFirstByIdProduct(id));
    }

    public io.healthathome.dto.Product getProductByName(String name) {
        return map(repository.findByName(name));
    }

    public io.healthathome.dto.Product insertOrUpdate(io.healthathome.dto.Product product) {
        return map(repository.insert(map(product)));
    }

    public io.healthathome.dto.Product update(io.healthathome.dto.Product product) {
        Product productStore = repository.findFirstByIdProduct(product.getId());
        Product productDto = map(product);
        productStore.setName(productDto.getName());
        productStore.setDescription(productDto.getDescription());
        productStore.setMedicalCharacteristics(productDto.getMedicalCharacteristics());
        productStore.setVolume(productDto.getVolume());
        productStore.setPhotos(productDto.getPhotos());
        productStore.setPlatform(productDto.getPlatform());
        productStore.setCategory(productDto.getCategory());
        return map(repository.save(map(product)));
    }

    public void delete(String id) {
        repository.delete(repository.findFirstByIdProduct(id));
    }

    public List<io.healthathome.dto.Product> getProductByCategoryId(String id) {
        return repository.getProductByCategoryId(id).stream().map(x -> map(x)).collect(Collectors.toList());
    }

    private Product map(io.healthathome.dto.Product product) {
        return mapper.map(product, Product.class);
    }

    private io.healthathome.dto.Product map(Product product) {
        return mapper.map(product, io.healthathome.dto.Product.class);
    }
}
