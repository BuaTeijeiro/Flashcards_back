package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.DeckUser;
import edu.badpals.flashcards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeckUserRepository extends JpaRepository<DeckUser, Long> {
    Optional<DeckUser> findByDeckAndUser(Deck deck, User user);
}
