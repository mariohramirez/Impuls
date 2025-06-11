package com.impuls.business_service.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrepreneurshipServiceId {

    private Long entrepreneurshipId;
    private int serviceId;

}
