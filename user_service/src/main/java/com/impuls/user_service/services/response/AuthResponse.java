package com.impuls.user_service.services.response;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private Boolean status;
}
