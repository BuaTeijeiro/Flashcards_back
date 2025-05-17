package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternRepository extends JpaRepository<Pattern, Long> {
}
