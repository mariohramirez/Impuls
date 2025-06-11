package com.impuls.business_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entrepreneurship_services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntrepreneurship {

    @EmbeddedId
    private EntrepreneurshipServiceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entrepreneurshipId")
    @JoinColumn(name = "entrepreneurship_id")
    private Entrepreneurship entrepreneurship;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private Service service;
}
