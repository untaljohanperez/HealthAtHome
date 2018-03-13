package io.healthathome.dto.payu;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class ExtraParameters {
    @JsonProperty("INSTALLMENTS_NUMBER")
    private int INSTALLMENTS_NUMBER;

    public ExtraParameters() {}

    public ExtraParameters(int INSTALLMENTS_NUMBER) {
        this.INSTALLMENTS_NUMBER = INSTALLMENTS_NUMBER;
    }

    @ApiModelProperty()
    public int getINSTALLMENTS_NUMBER() {
        return INSTALLMENTS_NUMBER;
    }
}
