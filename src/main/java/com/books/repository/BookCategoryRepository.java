package com.books.repository;

import com.books.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    @Query("SELECT c FROM BookCategory c WHERE c.id = :id")
    BookCategory findByCategoryId(@Param("id") Long id);
}
