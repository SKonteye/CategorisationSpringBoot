package com.isi.categorisationproject.service;

import com.isi.categorisationproject.model.Category;
import com.isi.categorisationproject.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new category
    @Transactional
    public Category createCategory(Category category) {
        List<Category> categoryList = categoryRepository.findAll().stream()
                .filter(x -> x.getParentId() == category.getId())
                .collect(Collectors.toList());
        Set<Category> categories = new HashSet<>(categoryList); // Convert List to Set

        category.setChildren(categories);
        Category savedCategory = categoryRepository.save(category);
        updateCategoryLevels(savedCategory);
        return savedCategory;
    }


    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get a single category by id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Update a category
    @Transactional
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        category.setParentId(categoryDetails.getParentId());
        category.setLevel(categoryDetails.getLevel()); // Updating level
        category.setDescription(categoryDetails.getDescription()); // Updating description
        var test = categoryRepository.findAll().stream().filter(x->x.getParentId()==categoryDetails.getId()).collect(Collectors.toList());
        List<Category> categories= new ArrayList<>();
        for (Category item:test
             ) {
            categories.add(item);
        }
        category.setChildren((Set<Category>) categories);
         Category updatedCategory = categoryRepository.save(category);
         updateCategoryLevels(updatedCategory);
        return updatedCategory;
    }

    // Delete a category
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    @Transactional
    public void updateCategoryLevels(Category affectedCategory) {
        Category rootCategory = findRootCategory(affectedCategory);
        setCategoryLevel(rootCategory, 0);
    }

    private Category findRootCategory(Category category) {
        Category current = category;
        while (current.getParentId() != null) {
            current = current.getParent();
        }
        return current;
    }

    private void setCategoryLevel(Category category, int level) {
        category.setLevel(level);
        categoryRepository.save(category);
        for (Category child : category.getChildren()) {
            setCategoryLevel(child, level + 1);
        }
    }

}