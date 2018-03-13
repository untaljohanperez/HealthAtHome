package io.healthathome.dto;

public class PayResponse {


    public PayResponse() {
    }

    public PayResponse(String message, boolean hasError) {
        this.message = message;
        this.hasError = hasError;
    }

    private String message;
    private boolean hasError = false;

    public static PayResponse newPayResponseFailed(String message) {
        return new PayResponse(message, true);
    }
    public static PayResponse newPayResponseSuccess(String message) {
        return new PayResponse(message, false);
    }
    public String getMessage() {
        return message;
    }
    public boolean isHasError() {
        return hasError;
    }
}
