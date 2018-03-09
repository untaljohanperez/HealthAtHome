package io.healthathome.dto;

public class Message {
    private String message;

    private Message(String message) {
        this.message = message;
    }

    public static Message build(String message) {
        return new Message(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
