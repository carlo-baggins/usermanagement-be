package net.carlo.usermanagement_be.auth.dto;

import net.carlo.usermanagement_be.dto.AccountDto;

public class AuthResponse {
    private String token;
    private int status;
    private String message;
    private AccountDto user;

    public AuthResponse(String token, int status, String message, AccountDto user) {
        this.token = token;
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public AccountDto getUser() {
        return user;
    }
}
