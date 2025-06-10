package com.impuls.user_service.model.gateway;

import com.impuls.user_service.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
