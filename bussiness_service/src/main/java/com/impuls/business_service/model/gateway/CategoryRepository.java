package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
