package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.DeckUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckUserRepository extends JpaRepository<DeckUser, Long> {
}
