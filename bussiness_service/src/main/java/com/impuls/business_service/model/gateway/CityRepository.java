package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
