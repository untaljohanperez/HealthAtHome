package io.healthathome.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "productChangeLog")
public class ProductChangeLog {

    private String _id;
    private String product;
    private String user;
    private List<ChangedProperty> changedProperties;
    private LocalDate changeDate = LocalDate.now();

    public ProductChangeLog(String product, String user, List<ChangedProperty> changedProperties) {
        this.product = product;
        this.user = user;
        this.changedProperties = changedProperties;
    }

    public String getProduct() {
        return product;
    }

    public String getUser() {
        return user;
    }

    public List<ChangedProperty> getChangedProperties() {
        return changedProperties;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }
}
