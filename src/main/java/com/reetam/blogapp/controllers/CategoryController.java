package com.reetam.blogapp.controllers;

import java.util.List;
import java.util.UUID;

import com.reetam.blogapp.mappers.CategoryMapper;
import com.reetam.blogapp.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reetam.blogapp.domain.dtos.CategoryDto;
import com.reetam.blogapp.domain.dtos.CreateCategoryRequest;
import com.reetam.blogapp.domain.entities.Category;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  private final CategoryMapper categoryMapper;

  @GetMapping
  public ResponseEntity<List<CategoryDto>> listCategories() {
    // todo
    List<CategoryDto> categories = categoryService.listCategories().stream().map(category -> categoryMapper.tDto(category)).toList();

    return ResponseEntity.ok(categories);
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(
    @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
    Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);

    Category savedCategory = categoryService.createCategory(categoryToCreate);

    return new ResponseEntity<>(
      categoryMapper.tDto(savedCategory),
      HttpStatus.CREATED
    );
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
    categoryService.deleteCategory(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
