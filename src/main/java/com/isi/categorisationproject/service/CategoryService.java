package com.isi.categorisationproject.service;

import com.isi.categorisationproject.model.Category;
import com.isi.categorisationproject.model.CategoryDTO;
import com.isi.categorisationproject.model.CategoryMapper;
import com.isi.categorisationproject.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO, CategoryDTO parentDTO) {
        Category newCategory = categoryMapper.categoryDTOToCategory(categoryDTO);
        Category parent = categoryMapper.categoryDTOToCategory(parentDTO);
        if (parent != null) {
            int maxRight = categoryRepository.findMaxRightValue().orElse(0);
            newCategory.setLeft(maxRight + 1);
            newCategory.setRight(maxRight + 2);

        } else {
            categoryRepository.incrementRightValues(parent.getRight());
            categoryRepository.incrementLeftValues(parent.getLeft());
        }

        newCategory.setLeft(parent.getRight());
        newCategory.setRight(parent.getRight() + 1);
        newCategory.setParent(parent);

        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.categoryToCategoryDTO(savedCategory);
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return categoryMapper.categoryToCategoryDTO(category);
    }

    public Page<CategoryDTO> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::categoryToCategoryDTO);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Assuming the mapper handles null properties correctly
        categoryMapper.updateCategoryFromDTO(categoryDTO, category);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDTO(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

}
