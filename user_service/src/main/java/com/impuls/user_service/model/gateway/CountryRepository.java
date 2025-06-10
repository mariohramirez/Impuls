package com.impuls.user_service.model.gateway;

import com.impuls.user_service.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
