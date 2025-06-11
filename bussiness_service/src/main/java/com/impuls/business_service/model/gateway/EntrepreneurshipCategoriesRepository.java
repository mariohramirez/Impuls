package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.EntrepreneurshipCategories;
import com.impuls.business_service.model.EntrepreneurshipCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepreneurshipCategoriesRepository extends JpaRepository<EntrepreneurshipCategories, EntrepreneurshipCategoryId> {
}
