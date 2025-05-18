package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findByOwner(Teacher teacher);
}

