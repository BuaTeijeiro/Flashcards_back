package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
