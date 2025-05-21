package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAllByDeck(Deck deck);
    List<Word> findAllByDeckAndTags(Deck deck, Tag tag);
    List<Word> findAllByDeckIdAndCategoryId(long deckId, long categoryId);
}
