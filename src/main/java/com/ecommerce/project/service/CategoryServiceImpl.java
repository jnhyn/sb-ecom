package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repository.CategoryRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CategoryResponse getAllCategories() {
    List<Category> categoryList = categoryRepository.findAll();

    if (categoryList.isEmpty()) {
      throw new ResourceNotFoundException("No category created till now");
    }

    List<CategoryDTO> categoryDtoList = categoryList.stream()
        .map(category -> modelMapper.map(category, CategoryDTO.class))
        .toList();

    CategoryResponse response = new CategoryResponse();
    response.setContent(categoryDtoList);

    return response;
  }

  @Override
  public CategoryDTO createCategory(CategoryDTO categoryDto) {
    Category categoryToCreate = modelMapper.map(categoryDto, Category.class);

    Category existingCategory = categoryRepository.findByCategoryName(
        categoryToCreate.getCategoryName());
    if (existingCategory != null) {
      throw new APIException(
          "Category with the name " + categoryToCreate.getCategoryName() + " already exists !!!");
    }

    Category createdCategory = categoryRepository.save(categoryToCreate);

    return modelMapper.map(createdCategory, CategoryDTO.class);
  }

  @Override
  public String deleteCategory(Long categoryId) {
    categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

    categoryRepository.deleteById(categoryId);
    return "Category with categoryId: " + categoryId + " deleted successfully !!";
  }

  @Override
  public CategoryDTO updateCategory(CategoryDTO categoryDto, Long categoryId) {
    Category existingCategory = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

    existingCategory.setCategoryName(categoryDto.getCategoryName());

    Category updatedCategory = categoryRepository.save(existingCategory);
    return modelMapper.map(updatedCategory, CategoryDTO.class);
  }

}
