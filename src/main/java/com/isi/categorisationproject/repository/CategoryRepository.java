package com.isi.categorisationproject.repository;

import com.isi.categorisationproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Query("UPDATE Category c SET c.right = c.right + 2 WHERE c.right > :right")
    void incrementRightValues(int right);

    @Modifying
    @Query("UPDATE Category c SET c.left = c.left + 2 WHERE c.left > :right")
    void incrementLeftValues(int right);
    @Modifying
    @Query("UPDATE Category c SET c.right = c.right - 2 WHERE c.right > :right")
    void decrementRightValues(int right);

    @Modifying
    @Query("UPDATE Category c SET c.left = c.left - 2 WHERE c.left > :left")
    void decrementLeftValues(int left);

    @Query("SELECT MAX(c.right) FROM Category c")
    Optional<Integer> findMaxRightValue();
}
