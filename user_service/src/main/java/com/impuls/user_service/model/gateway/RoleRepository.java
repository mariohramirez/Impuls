package com.impuls.user_service.model.gateway;

import com.impuls.user_service.model.Role;
import com.impuls.user_service.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(RoleName roleName);
}
