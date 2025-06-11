package com.impuls.business_service.services.request;

import com.impuls.business_service.model.LegalForm;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EntrepreneurshipRequest {

    private Long ownerId;
    private String name;
    private String email;
    private String phone;
    private String nit;
    private String logoUrl;
    private String companySize;
    private String companyName;
    private String websiteUrl;
    private String shortDescription;
    private String bannerUrl;
    private String description;

    private Boolean isVerified;
    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LegalForm legalForm;

    private LocalDateTime registerDate;
    private LocalDateTime startDate;
    private LocalDateTime incorporationDate;
    private Boolean formalized;

    private List<AddressRequest> addressRequests;
    private List<SocialNetworkRequest> socialNetworkRequests;
    private List<CategoryRequest> categoryRequests;
    private List<ServiceRequest> serviceRequests;
}
