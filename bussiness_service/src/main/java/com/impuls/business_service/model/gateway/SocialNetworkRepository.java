package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {
}
