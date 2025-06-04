package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAllByDeck(Deck deck);
    List<Word> findAllByDeckIdAndCategoryId(long deckId, long categoryId);
    List<Word> findAllByDeckIdAndCategoryIdAndLevelLessThanEqual(long deckId, long categoryId, int leve);
    List<Word> findAllByDeckIdAndCategoryIdAndTag(long id, long id1, Tag tag);
    List<Word> findAllByDeckIdAndCategoryIdAndTagAndLevelLessThanEqual(long id, long id1, Tag tag, int level);
}
