package com.impuls.user_service.model.gateway;

import com.impuls.user_service.model.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {
}
