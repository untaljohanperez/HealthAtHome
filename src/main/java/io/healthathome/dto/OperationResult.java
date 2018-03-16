package io.healthathome.dto;

public class OperationResult {

    public OperationResult() {
    }

    public OperationResult(String message, boolean hasError) {
        this.message = message;
        this.hasError = hasError;
    }

    private String message;
    private boolean hasError = false;

    public static OperationResult newFailedOperationResponse(String message) {
        return new OperationResult(message, true);
    }
    public static OperationResult newSuccessOperationResult(String message) {
        return new OperationResult(message, false);
    }
    public String getMessage() {
        return message;
    }
    public boolean isHasError() {
        return hasError;
    }
}
