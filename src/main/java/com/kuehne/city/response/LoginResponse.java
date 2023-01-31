package com.kuehne.city.response;

import org.springframework.lang.NonNull;

public class LoginResponse {

    @NonNull
     String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
    }
}
