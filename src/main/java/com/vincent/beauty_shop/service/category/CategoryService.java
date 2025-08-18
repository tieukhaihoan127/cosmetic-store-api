package com.vincent.beauty_shop.service.category;

import com.vincent.beauty_shop.entity.Category;
import com.vincent.beauty_shop.request.category.CategoryCreateRequest;
import com.vincent.beauty_shop.request.category.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category createCategory(CategoryCreateRequest categoryRequest);

    Category getCategoryById(Long id);

    Category updateCategory(Long id, CategoryUpdateRequest categoryRequest);

    void deleteCategory(Long id);
}
