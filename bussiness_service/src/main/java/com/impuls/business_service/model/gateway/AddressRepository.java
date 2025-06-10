package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
