package com.vincent.beauty_shop.service.category;

import com.vincent.beauty_shop.entity.Category;
import com.vincent.beauty_shop.exception.DeleteForeignKeyException;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.repository.CategoryRepository;
import com.vincent.beauty_shop.request.CategoryCreateRequest;
import com.vincent.beauty_shop.request.CategoryUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(CategoryCreateRequest categoryRequest) {
        Category categoryParent = null;

        if(categoryRequest.getParentId() != null) {
            categoryParent = categoryRepository.findById(categoryRequest.getParentId()).orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
        }

        Category category = Category.builder().title(categoryRequest.getTitle()).parent(categoryParent).build();
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }

    @Override
    public Category updateCategory(Long id, CategoryUpdateRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        category.setTitle(categoryRequest.getTitle());

        if (categoryRequest.getParentId() != null) {
            Category parent = categoryRepository.findById(categoryRequest.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        // Nếu cần validate children/parent
        if (!category.getChildren().isEmpty()) {
            throw new DeleteForeignKeyException("Cannot delete category because it has child categories.");
        }

        categoryRepository.delete(category);
    }
}
