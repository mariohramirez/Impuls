package com.impuls.user_service.services.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;

}
