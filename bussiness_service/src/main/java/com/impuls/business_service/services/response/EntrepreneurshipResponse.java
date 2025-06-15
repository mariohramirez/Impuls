package com.impuls.business_service.services.response;

import com.impuls.business_service.services.request.AddressRequest;
import com.impuls.business_service.services.request.CategoryRequest;
import com.impuls.business_service.services.request.ServiceRequest;
import com.impuls.business_service.services.request.SocialNetworkRequest;
import lombok.Data;

import java.util.List;

@Data
public class EntrepreneurshipResponse {

    private Long ownerId;
    private String name;
    private String email;
    private String phone;
    private String logoUrl;
    private String websiteUrl;
    private String shortDescription;
    private String bannerUrl;
    private String description;

    private List<AddressResponse> addressResponses;
    private List<SocialNetworkResponse> socialNetworkResponses;
    private List<CategoryResponse> categoryResponses;
    private List<ServiceResponse> serviceResponses;

}
