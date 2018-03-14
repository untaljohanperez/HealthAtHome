package io.healthathome.dto.payu;

public class TransactionResponse {

    private String orderId;
    private String transactionId;
    private String state;
    private String paymentNetworkResponseCode;
    private String paymentNetworkResponseErrorMessage;
    private String trazabilityCode;
    private String authorizationCode;
    private String pendingReason;
    private String responseCode;
    private String errorCode;
    private String responseMessage;
    private String transactionDate;
    private String transactionTime;
    private String operationDate;
    private String extraParameters;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPaymentNetworkResponseCode() {
        return paymentNetworkResponseCode;
    }
    public void setPaymentNetworkResponseCode(String paymentNetworkResponseCode) {
        this.paymentNetworkResponseCode = paymentNetworkResponseCode;
    }
    public String getPaymentNetworkResponseErrorMessage() {
        return paymentNetworkResponseErrorMessage;
    }

    public void setPaymentNetworkResponseErrorMessage(String paymentNetworkResponseErrorMessage) {
        this.paymentNetworkResponseErrorMessage = paymentNetworkResponseErrorMessage;
    }

    public String getTrazabilityCode() {
        return trazabilityCode;
    }

    public void setTrazabilityCode(String trazabilityCode) {
        this.trazabilityCode = trazabilityCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getPendingReason() {
        return pendingReason;
    }

    public void setPendingReason(String pendingReason) {
        this.pendingReason = pendingReason;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public String getExtraParameters() {
        return extraParameters;
    }

    public void setExtraParameters(String extraParameters) {
        this.extraParameters = extraParameters;
    }
}
