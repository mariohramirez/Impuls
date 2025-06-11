package com.impuls.business_service.services.interfaces;

import com.impuls.business_service.model.Category;
import com.impuls.business_service.services.request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public Category createCategory(CategoryRequest request);

    public Category updateCategory(Integer id, CategoryRequest request) throws Exception;

    public void deleteCategory(Integer id)throws Exception;

    public List<Category> getAllCategories();

    public Category  findCategoryById(Integer id) throws Exception;

}
