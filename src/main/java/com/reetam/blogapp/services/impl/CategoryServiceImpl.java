package com.reetam.blogapp.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reetam.blogapp.domain.entities.Category;
import com.reetam.blogapp.repositories.CategoryRepository;
import com.reetam.blogapp.services.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService{
  private final CategoryRepository categoryRepository;
  
  @Override
  public List<Category> listCategories() {
    return categoryRepository.findAll();
  }

}
