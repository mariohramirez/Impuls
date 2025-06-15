package com.impuls.business_service.services.response;

import com.impuls.business_service.model.City;
import lombok.Data;

@Data
public class AddressResponse {

    private String street;
    private String neighborhood;
    private String zipCode;
}
