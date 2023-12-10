package com.isi.categorisationproject.repository;

import com.isi.categorisationproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
