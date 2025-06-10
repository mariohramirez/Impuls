package com.impuls.user_service.services.response;

import lombok.Data;

@Data
public class UserResponse {

    private String email;

    private String role;

    private ProfileResponse profileResponse;
}
