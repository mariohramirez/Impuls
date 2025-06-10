package com.impuls.user_service.services.response;

import com.impuls.user_service.model.DocumentType;
import com.impuls.user_service.model.Gender;
import com.impuls.user_service.services.request.AddressRequest;
import com.impuls.user_service.services.request.SocialNetworkRequest;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;

    private Gender gender ;
    private DocumentType documentType ;

    private List<SocialNetworkRequest> socialNetworkRequests ;

    private List<AddressRequest> addressRequests;
}
