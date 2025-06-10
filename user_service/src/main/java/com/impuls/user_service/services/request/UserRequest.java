package com.impuls.user_service.services.request;

import lombok.Data;

@Data
public class UserRequest {

    private Long id;

    private String userNumber;

    private String email;

    private String role;

    private ProfileRequest profileRequest;
}
