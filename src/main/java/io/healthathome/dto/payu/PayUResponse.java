package io.healthathome.dto.payu;

public class PayUResponse {

    private String code;
    private String error;
    private String transactionResponse;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getTransactionResponse() {
        return transactionResponse;
    }
    public void setTransactionResponse(String transactionResponse) {
        this.transactionResponse = transactionResponse;
    }
}
