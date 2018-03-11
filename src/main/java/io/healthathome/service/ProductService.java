package io.healthathome.service;

import io.healthathome.models.ChangedProperty;
import io.healthathome.models.Product;
import io.healthathome.models.ProductChangeLog;
import io.healthathome.repository.ProductChangeLogRepository;
import io.healthathome.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductChangeLogRepository productChangeLogRepository;

    public List<io.healthathome.dto.Product> getAllProducts() {
        return productRepository.findAll().stream().map(x -> Mapper.map(x)).collect(Collectors.toList());
    }

    public io.healthathome.dto.Product getProductById(String id) {
        return Mapper.map(productRepository.findFirstByIdProduct(id));
    }

    public io.healthathome.dto.Product getProductByName(String name) {
        return Mapper.map(productRepository.findByName(name));
    }

    public void insert(io.healthathome.dto.Product product) {
        productRepository.insert(Mapper.map(product));
    }

    public void update(io.healthathome.dto.Product product, String user) {
        Product productStored = productRepository.findFirstByIdProduct(product.getId());

        if(productStored == null)
            return;

        List<ChangedProperty> changedProperties = getChangedProperties(productStored, product);
        if(changedProperties.isEmpty())
            return;

        setNewProperties(product, productStored);
        ProductChangeLog productChangeLog = new ProductChangeLog(product.getId(), user, changedProperties);
        productRepository.save(productStored);
        productChangeLogRepository.insert(productChangeLog);
    }

    private void setNewProperties(io.healthathome.dto.Product product, Product productStored) {
        Product productDto = Mapper.map(product);
        productStored.setName(productDto.getName());
        productStored.setDescription(productDto.getDescription());
        productStored.setMedicalCharacteristics(productDto.getMedicalCharacteristics());
        productStored.setVolume(productDto.getVolume());
        productStored.setPhotos(productDto.getPhotos());
        productStored.setPlatform(productDto.getPlatform());
        productStored.setCategory(productDto.getCategory());
    }

    private List<ChangedProperty> getChangedProperties(Product productStored, io.healthathome.dto.Product productChanged) {
        List<ChangedProperty> changedProperties = new ArrayList<>();
        if(valueHasChanged(productStored.getName(), productChanged.getName()))
            changedProperties.add(new ChangedProperty("name", productStored.getName(), productChanged.getName()));
        if(valueHasChanged(productStored.getDescription(), productChanged.getDescription()))
            changedProperties.add(new ChangedProperty("description", productStored.getDescription(), productChanged.getDescription()));
        if(valueHasChanged(productStored.getMedicalCharacteristics(), productChanged.getMedicalCharacteristics()))
            changedProperties.add(new ChangedProperty("medicalCharacteristics", productStored.getMedicalCharacteristics(), productChanged.getMedicalCharacteristics()));
        if(valueHasChanged(productStored.getVolume(), productChanged.getVolume()))
            changedProperties.add(new ChangedProperty("volume", productStored.getVolume(), productChanged.getVolume()));
        if(valueHasChanged(productStored.getPlatform(), productChanged.getPlatform()))
            changedProperties.add(new ChangedProperty("platform", productStored.getPlatform(), productChanged.getPlatform()));
        if(valueHasChanged(productStored.getCategory().getName(), productChanged.getCategory().getName()))
            changedProperties.add(new ChangedProperty("category", productStored.getCategory().getName(), productChanged.getCategory().getName()));
        return changedProperties;
    }

    private boolean valueHasChanged(String oldValue, String newValue) {
        return (StringUtils.isEmpty(oldValue) && !StringUtils.isEmpty(newValue) ||
                !StringUtils.isEmpty(oldValue) && StringUtils.isEmpty(newValue) ||
                oldValue != null && !oldValue.equals(newValue));
    }

    public void delete(String id) {
        productRepository.delete(productRepository.findFirstByIdProduct(id));
    }

    public List<io.healthathome.dto.Product> getProductByCategoryId(String id) {
        return productRepository.getProductByCategoryId(id).stream().map(x -> Mapper.map(x)).collect(Collectors.toList());
    }

}
