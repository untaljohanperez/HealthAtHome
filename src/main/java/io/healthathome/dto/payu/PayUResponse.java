package io.healthathome.dto.payu;

public class PayUResponse {

    private String code;
    private String error;
    private TransactionResponse transactionResponse;
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
    public TransactionResponse getTransactionResponse() {
        return transactionResponse;
    }
    public void setTransactionResponse(TransactionResponse transactionResponse) {
        this.transactionResponse = transactionResponse;
    }
}
