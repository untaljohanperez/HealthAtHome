package io.healthathome.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "product")
public class Product {

    @Id
    private String _id;
    private String id;
    private String name;
    private String description;
    private String medicalCharacteristics;
    private String volume;
    private List<String> photos;
    private String platform;
    private Category category;
    private double eachPrice;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getMedicalCharacteristics() {
        return medicalCharacteristics;
    }
    public void setMedicalCharacteristics(String medicalCharacteristics) {
        this.medicalCharacteristics = medicalCharacteristics;
    }
    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }
    public List<String> getPhotos() {
        return photos;
    }
    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public double getEachPrice() {
        return eachPrice;
    }
    public void setEachPrice(double eachPrice) {
        this.eachPrice = eachPrice;
    }
}
