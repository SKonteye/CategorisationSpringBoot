package com.isi.categorisationproject.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Existing mapping methods
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mapping(target = "left", ignore = true)
    @Mapping(target = "right", ignore = true)
    Category categoryDTOToCategory(CategoryDTO dto);

    // Method to update an existing Category entity from a DTO
    void updateCategoryFromDTO(CategoryDTO dto, @MappingTarget Category entity);
}


