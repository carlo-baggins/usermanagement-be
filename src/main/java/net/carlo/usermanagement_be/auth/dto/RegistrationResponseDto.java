package net.carlo.usermanagement_be.auth.dto;

public class RegistrationResponseDto {

    private String message;

    public RegistrationResponseDto(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
