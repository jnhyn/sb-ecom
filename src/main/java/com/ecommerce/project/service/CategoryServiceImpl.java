package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  // 생성자 주입
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public void createCategory(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public String deleteCategory(Long categoryId) {
    Category savedCategory = categoryRepository
        .findById(categoryId)
        .orElseThrow(
            () -> new ResourceNotFoundException("Category", "categoryId", categoryId)
        );

    categoryRepository.deleteById(categoryId);
    return "Category with categoryId: " + categoryId + " deleted successfully !!";
  }

  @Override
  public Category updateCategory(Category category, Long categoryId) {
    categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

    category.setCategoryId(categoryId);

    return categoryRepository.save(category);
  }


}
