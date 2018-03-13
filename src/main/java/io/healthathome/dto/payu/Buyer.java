package io.healthathome.dto.payu;

public class Buyer extends Person {
    private String merchantBuyerId;
    private Address shippingAddress;

    public Address getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public String getMerchantBuyerId() {
        return merchantBuyerId;
    }
    public void setMerchantBuyerId(String merchantBuyerId) {
        this.merchantBuyerId = merchantBuyerId;
    }
}
