package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.EntrepreneurshipServiceId;
import com.impuls.business_service.model.ServiceEntrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepreneurshipServicesRepository extends JpaRepository<ServiceEntrepreneurship, EntrepreneurshipServiceId> {
}
