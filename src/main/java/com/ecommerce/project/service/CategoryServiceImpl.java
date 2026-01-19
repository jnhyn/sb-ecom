package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found")
        );

    categoryRepository.deleteById(categoryId);
    return "Category with categoryId: " + categoryId + " deleted successfully !!";
  }

  @Override
  public Category updateCategory(Category category, Long categoryId) {
    Optional<Category> savedCategoriesOptional = categoryRepository.findById(categoryId);

    Category savedCategory = savedCategoriesOptional
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found")
        );

    category.setCategoryId(categoryId);

    savedCategory = categoryRepository.save(category);
    return savedCategory;
  }


}
