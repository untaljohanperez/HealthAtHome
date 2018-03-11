package io.healthathome.service;

import io.healthathome.models.Product;
import io.healthathome.repository.ProductRepository;
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

    public List<io.healthathome.dto.Product> getAllProducts() {
        return repository.findAll().stream().map(x -> Mapper.map(x)).collect(Collectors.toList());
    }

    public io.healthathome.dto.Product getProductById(String id) {
        return Mapper.map(repository.findFirstByIdProduct(id));
    }

    public io.healthathome.dto.Product getProductByName(String name) {
        return Mapper.map(repository.findByName(name));
    }

    public void insert(io.healthathome.dto.Product product) {
        repository.insert(Mapper.map(product));
    }

    public void update(io.healthathome.dto.Product product) {
        Product productStore = repository.findFirstByIdProduct(product.getId());
        Product productDto = Mapper.map(product);
        productStore.setName(productDto.getName());
        productStore.setDescription(productDto.getDescription());
        productStore.setMedicalCharacteristics(productDto.getMedicalCharacteristics());
        productStore.setVolume(productDto.getVolume());
        productStore.setPhotos(productDto.getPhotos());
        productStore.setPlatform(productDto.getPlatform());
        productStore.setCategory(productDto.getCategory());
        repository.save(Mapper.map(product));
    }

    public void delete(String id) {
        repository.delete(repository.findFirstByIdProduct(id));
    }

    public List<io.healthathome.dto.Product> getProductByCategoryId(String id) {
        return repository.getProductByCategoryId(id).stream().map(x -> Mapper.map(x)).collect(Collectors.toList());
    }


}
