package io.healthathome.service;

import com.google.common.base.Strings;
import io.healthathome.models.ChangedProperty;
import io.healthathome.models.Product;
import io.healthathome.models.ProductChangeLog;
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
    private ProductRepository productRepository;
    @Autowired
    private ProductChangeLogService productChangeLogService;

    public List<io.healthathome.dto.Product> getAllProducts() {
        return productRepository.findAll().stream().map(x -> Mapper.map(x)).collect(Collectors.toList());
    }

    public io.healthathome.dto.Product getProductById(String id) {
        return Mapper.map(productRepository.findFirstByIdProduct(id));
    }

    public io.healthathome.dto.Product getProductByName(String name) {
        return Mapper.map(productRepository.findFirstByName(name));
    }

    public String insert(io.healthathome.dto.Product productDto) {
        Product productModel = Mapper.map(productDto);
        String id = Strings.padStart(String.valueOf(productRepository.count()), 3, '0');
        productModel.setId(id);
        productRepository.insert(productModel);
        return id;
    }

    public void delete(String id) {
        productRepository.delete(productRepository.findFirstByIdProduct(id));
    }

    public List<io.healthathome.dto.Product> getProductByCategoryId(String id) {
        return productRepository.getProductByCategoryId(id).stream().map(x -> Mapper.map(x)).collect(Collectors.toList());
    }

    public void update(io.healthathome.dto.Product product, String user) {
        Product productStored = productRepository.findFirstByIdProduct(product.getId());

        if(productStored == null)
            return;

        List<ChangedProperty> changedProperties = productChangeLogService.getChangedProperties(productStored, product);
        if(changedProperties.isEmpty())
            return;

        setNewProperties(product, productStored);
        ProductChangeLog productChangeLog = new ProductChangeLog(product.getId(), user, changedProperties);
        productRepository.save(productStored);
        productChangeLogService.insert(productChangeLog);
    }

    private void setNewProperties(io.healthathome.dto.Product product, Product productStored) {
        Product productDto = Mapper.map(product);
        productStored.setName(productDto.getName());
        productStored.setDescription(productDto.getDescription());
        productStored.setMedicalCharacteristics(productDto.getMedicalCharacteristics());
        productStored.setVolume(productDto.getVolume());
        productStored.setPlatform(productDto.getPlatform());
        productStored.setCategory(productDto.getCategory());
    }
}
