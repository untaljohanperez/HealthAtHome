package io.healthathome.dto.payu;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class AdditionalValues {
    @JsonProperty("TX_VALUE")
    private AdditionalValue TX_VALUE;
    @JsonProperty("TX_TAX")
    private AdditionalValue TX_TAX;
    @JsonProperty("TX_TAX_RETURN_BASE")
    private AdditionalValue TX_TAX_RETURN_BASE;

    @ApiModelProperty()
    public AdditionalValue getTX_VALUE() {
        return TX_VALUE;
    }

    public void setTX_VALUE(AdditionalValue TX_VALUE) {
        this.TX_VALUE = TX_VALUE;
    }

    @ApiModelProperty()
    public AdditionalValue getTX_TAX() {
        return TX_TAX;
    }

    public void setTX_TAX(AdditionalValue TX_TAX) {
        this.TX_TAX = TX_TAX;
    }

    @ApiModelProperty()
    public AdditionalValue getTX_TAX_RETURN_BASE() {
        return TX_TAX_RETURN_BASE;
    }

    public void setTX_TAX_RETURN_BASE(AdditionalValue TX_TAX_RETURN_BASE) {
        this.TX_TAX_RETURN_BASE = TX_TAX_RETURN_BASE;
    }
}
