package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
