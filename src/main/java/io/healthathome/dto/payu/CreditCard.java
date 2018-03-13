package io.healthathome.dto.payu;

public class CreditCard {

    private String number;
    private String securityCode;
    private String expirationDate;
    private String name;

    public CreditCard() {}

    public CreditCard(String number, String securityCode, String expirationDate, String name) {
        this.number = number;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getName() {
        return name;
    }
}
