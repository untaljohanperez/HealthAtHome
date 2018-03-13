package io.healthathome.dto.payu;

public class Payer extends Person {

    private String merchantPayerId;
    private Address billingAddress;

    public Address getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
    public String getMerchantPayerId() {
        return merchantPayerId;
    }
    public void setMerchantPayerId(String merchantPayerId) {
        this.merchantPayerId = merchantPayerId;
    }
}
