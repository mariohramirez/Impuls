package com.impuls.user_service.services.request;

import com.impuls.user_service.model.DocumentType;
import com.impuls.user_service.model.Gender;
import lombok.Data;

import java.util.List;

@Data
public class ProfileRequest {

    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;

    private Gender gender ;
    private DocumentType documentType ;

    private List<SocialNetworkRequest> socialNetworkRequests ;

    private List<AddressRequest> addressRequests;
}
