package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.LegalForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalFormRepository extends JpaRepository<LegalForm, Integer> {
}
