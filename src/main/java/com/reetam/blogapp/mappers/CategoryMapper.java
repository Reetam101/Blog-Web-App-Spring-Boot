package com.reetam.blogapp.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.reetam.blogapp.domain.PostStatus;
import com.reetam.blogapp.domain.dtos.CategoryDto;
import com.reetam.blogapp.domain.dtos.CreateCategoryRequest;
import com.reetam.blogapp.domain.entities.Category;
import com.reetam.blogapp.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
  CategoryDto tDto(Category category);

  Category toEntity(CreateCategoryRequest createCategoryRequest);
  
  @Named("calculatePostCount")
  default long calculatePostCount(List<Post> posts) {
    if(posts == null) {
      return 0;
    }

    return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
  }


}
