package com.impuls.business_service.services.request;

import com.impuls.business_service.model.City;
import lombok.Data;

@Data
public class AddressRequest {

    private String street;
    private City city;
    private String neighborhood;
    private String zipCode;
    private Boolean isPrimary;
}
