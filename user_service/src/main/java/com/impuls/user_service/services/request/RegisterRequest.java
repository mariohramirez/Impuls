package com.impuls.user_service.services.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;

    private String password;

    private ProfileRequest profileRequest;
}
