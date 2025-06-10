package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
