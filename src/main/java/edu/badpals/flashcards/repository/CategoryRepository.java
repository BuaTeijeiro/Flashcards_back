package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOwner(Teacher teacher);
}
