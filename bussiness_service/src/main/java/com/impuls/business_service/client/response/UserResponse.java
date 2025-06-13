package com.impuls.business_service.client.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String email;

    private ProfileResponse profileResponse;
}
