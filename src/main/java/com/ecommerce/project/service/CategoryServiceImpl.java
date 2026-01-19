package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
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
    List<Category> categories = categoryRepository.findAll();

    if (categories.isEmpty()) {
      throw new ResourceNotFoundException("No category created till now");
    }

    return categories;
  }

  @Override
  public void createCategory(Category category) {
    Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
    if (savedCategory != null) {
      throw new APIException(
          "Category with the name " + category.getCategoryName() + " already exists !!!");
    }

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
