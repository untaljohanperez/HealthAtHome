package io.healthathome.service;

import io.healthathome.models.ChangedProperty;
import io.healthathome.models.Product;
import io.healthathome.models.ProductChangeLog;
import io.healthathome.repository.ProductChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductChangeLogService {

    @Autowired
    private ProductChangeLogRepository productChangeLogRepository;

    public List<ChangedProperty> getChangedProperties(Product productStored, io.healthathome.dto.Product productChanged) {
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

    public void insert(ProductChangeLog productChangeLog) {
        productChangeLogRepository.insert(productChangeLog);
    }

    private boolean valueHasChanged(String oldValue, String newValue) {
        return (StringUtils.isEmpty(oldValue) && !StringUtils.isEmpty(newValue) ||
                !StringUtils.isEmpty(oldValue) && StringUtils.isEmpty(newValue) ||
                oldValue != null && !oldValue.equals(newValue));
    }



}
