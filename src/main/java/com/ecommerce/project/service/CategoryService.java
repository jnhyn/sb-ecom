package com.ecommerce.project.service;

import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;

public interface CategoryService {

  CategoryResponse getAllCategories();

  CategoryDTO createCategory(CategoryDTO categoryDTO);

  String deleteCategory(Long categoryId);

  CategoryDTO updateCategory(CategoryDTO category, Long categoryId);
}
