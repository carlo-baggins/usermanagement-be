package net.carlo.usermanagement_be.auth.dto;

public class PingMessage {
    private String message;

    public PingMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
