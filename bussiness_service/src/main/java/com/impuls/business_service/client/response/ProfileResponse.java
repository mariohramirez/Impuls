package com.impuls.business_service.client.response;

import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;
}
