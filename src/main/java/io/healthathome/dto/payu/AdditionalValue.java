package io.healthathome.dto.payu;

public class AdditionalValue {

    private double value;
    private String currency;

    public AdditionalValue(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}
