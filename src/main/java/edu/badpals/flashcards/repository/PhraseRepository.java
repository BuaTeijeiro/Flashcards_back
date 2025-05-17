package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {
}
