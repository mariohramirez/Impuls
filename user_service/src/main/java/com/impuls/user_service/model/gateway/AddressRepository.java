package com.impuls.user_service.model.gateway;

import com.impuls.user_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long> {
}
