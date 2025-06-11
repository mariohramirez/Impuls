package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
