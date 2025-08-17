package com.vincent.beauty_shop.service.category;

import com.vincent.beauty_shop.entity.Category;
import com.vincent.beauty_shop.request.CategoryCreateRequest;
import com.vincent.beauty_shop.request.CategoryUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    Category createCategory(CategoryCreateRequest categoryRequest);

    Category getCategoryById(Long id);

    Category updateCategory(Long id, CategoryUpdateRequest categoryRequest);

    void deleteCategory(Long id);
}
