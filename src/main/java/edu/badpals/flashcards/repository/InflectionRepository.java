package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InflectionRepository extends JpaRepository<Inflection, Long> {
}
