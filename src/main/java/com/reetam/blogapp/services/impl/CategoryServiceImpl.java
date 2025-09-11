package com.reetam.blogapp.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reetam.blogapp.domain.entities.Category;
import com.reetam.blogapp.repositories.CategoryRepository;
import com.reetam.blogapp.services.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService{
  private final CategoryRepository categoryRepository;
  
  @Override
  public List<Category> listCategories() {
    return categoryRepository.findAllWithPostCount();
  }

  @Override
  @Transactional
  public Category createCategory(Category category) {
    String categoryName = category.getName();
    if(categoryRepository.existsByNameIgnoreCase(categoryName)) {
      throw new IllegalArgumentException("Category already exists with name: " + categoryName);
    }
    return categoryRepository.save(category);
    
  }

}
