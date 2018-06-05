package io.healthathome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class ProductInput {

    @JsonProperty("id")
    private String id;
    @JsonProperty("eachPrice")
    private Double eachPrice;

    @ApiModelProperty()
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty()
    @NotNull
    public Double getEachPrice() {
        return eachPrice;
    }
    public void setEachPrice(Double eachPrice) {
        this.eachPrice = eachPrice;
    }
}
