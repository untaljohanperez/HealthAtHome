package io.healthathome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product   {

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("medical_characteristics")
  private String medicalCharacteristics = null;

  @JsonProperty("volume")
  private String volume = null;

  @JsonProperty("photos")
  private List<String> photos = null;

  @JsonProperty("platform")
  private String platform = null;

  @JsonProperty("category")
  private Category category = null;

  @JsonProperty("eachPrice")
  private double eachPrice;

  public Product id(String id) {
    this.id = id;
    return this;
  }

  @ApiModelProperty()
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Product name(String name) {
    this.name = name;
    return this;
  }

  @ApiModelProperty(required = true)
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Product description(String description) {
    this.description = description;
    return this;
  }

  @ApiModelProperty()
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Product medicalCharacteristics(String medicalCharacteristics) {
    this.medicalCharacteristics = medicalCharacteristics;
    return this;
  }

  @ApiModelProperty()
  public String getMedicalCharacteristics() {
    return medicalCharacteristics;
  }

  public void setMedicalCharacteristics(String medicalCharacteristics) {
    this.medicalCharacteristics = medicalCharacteristics;
  }

  public Product volume(String volume) {
    this.volume = volume;
    return this;
  }

  @ApiModelProperty()
  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public Product photos(List<String> photos) {
    this.photos = photos;
    return this;
  }

  public Product addPhotosItem(String photosItem) {
    if (this.photos == null) {
      this.photos = new ArrayList<String>();
    }
    this.photos.add(photosItem);
    return this;
  }

  @ApiModelProperty()
  public List<String> getPhotos() {
    return photos;
  }

  public void setPhotos(List<String> photos) {
    this.photos = photos;
  }

  public Product platform(String platform) {
    this.platform = platform;
    return this;
  }

  @ApiModelProperty()
  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public Product category(Category category) {
    this.category = category;
    return this;
  }

  @ApiModelProperty()
  @Valid
  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @ApiModelProperty()
  public double getEachPrice() {
    return eachPrice;
  }
  public void setEachPrice(double eachPrice) {
    this.eachPrice = eachPrice;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.id, product.id) &&
        Objects.equals(this.name, product.name) &&
        Objects.equals(this.description, product.description) &&
        Objects.equals(this.medicalCharacteristics, product.medicalCharacteristics) &&
        Objects.equals(this.volume, product.volume) &&
        Objects.equals(this.photos, product.photos) &&
        Objects.equals(this.platform, product.platform) &&
        Objects.equals(this.category, product.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, medicalCharacteristics, volume, photos, platform, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    medicalCharacteristics: ").append(toIndentedString(medicalCharacteristics)).append("\n");
    sb.append("    volume: ").append(toIndentedString(volume)).append("\n");
    sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
    sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

