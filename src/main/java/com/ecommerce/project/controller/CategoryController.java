package com.ecommerce.project.controller;

import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/public/categories")
  public ResponseEntity<CategoryResponse> getAllCategories() {
    CategoryResponse categoryResponse = categoryService.getAllCategories();
    return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
  }

  @PostMapping("/public/categories")
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
    CategoryDTO categoryResponse = categoryService.createCategory(categoryDTO);
    return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
  }

  @DeleteMapping("/admin/categories/{categoryId}")
  public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
    String status = categoryService.deleteCategory(categoryId);
    return new ResponseEntity<>(status, HttpStatus.OK);
  }

  @PutMapping("/public/categories/{categoryId}")
  public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto,
      @PathVariable Long categoryId) {
    CategoryDTO updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

  }
}
