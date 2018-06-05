package io.healthathome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class ItemInput {

  @JsonProperty("product")
  private ProductInput product = null;

  @JsonProperty("quantity")
  private Integer quantity;

  public ItemInput(ProductInput product, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public ItemInput() {

  }

  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public ProductInput getProduct() {
    return product;
  }

  public void setProduct(ProductInput product) {
    this.product = product;
  }

  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

}

