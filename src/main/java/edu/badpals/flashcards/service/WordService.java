package edu.badpals.flashcards.service;

import edu.badpals.flashcards.dto.WordDto;
import edu.badpals.flashcards.model.*;
import edu.badpals.flashcards.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository repository;
    @Autowired
    private DeckService deckService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PatternService patternService;
    @Autowired
    private TagService tagService;

    public Word save(WordDto wordDto){
        Word word = new Word();

        Deck deck = deckService.findById(wordDto.getDeckId());

        if (deck != null ){
            word.setMeaning(wordDto.getMeaning());
            word.setWord(wordDto.getWord());
            word.setDeck(deck);
            word.setLevel(wordDto.getLevel());
            word.setTag(tagService.findById(wordDto.getTagId()));
            word.setCategory(categoryService.findById(wordDto.getCategoryId()));
            word.setPattern(patternService.findById(wordDto.getPatternId()));
            return repository.save(word);
        } else
            return null;

    }

    public Word update(WordDto word) {
        Optional<Word> wordOptional = repository.findById(word.getId());
        if (wordOptional.isPresent()){
            Word oldWord = wordOptional.get();
            oldWord.setWord(word.getWord());
            oldWord.setMeaning(word.getMeaning());
            oldWord.setLevel(word.getLevel());
            oldWord.setTag(tagService.findById(word.getTagId()));
            oldWord.setCategory(categoryService.findById(word.getCategoryId()));
            oldWord.setPattern(patternService.findById(word.getPatternId()));
            return repository.save(oldWord);
        } else {
            return null;
        }
    }

    public boolean delete(WordDto word){
        Optional<Word> wordOptional = repository.findById(word.getId());
        if (wordOptional.isPresent()){
            repository.delete(wordOptional.get());
            return true;
        } else {
            return false;
        }

    }

    public Optional<Word> findById(Long id){
        return repository.findById(id);
    }


    public Word addWord(String word, String meaning, Long deckId){
        Word newWord = new Word();
        newWord.setWord(word);
        newWord.setMeaning(meaning);
        newWord.setDeck(deckService.getDeck(deckId));
        return repository.save(newWord);
    }

    public List<Word> findAllByDeck(Deck deck){
        return repository.findAllByDeck(deck);
    }

    public List<Word> findAllByDeckAndCategory(Deck deck, Category category){
        return repository.findAllByDeckIdAndCategoryId(deck.getId(), category.getId());
    }

    public List<Word> findAllByDeckIdAndCategoryIdAndLevelLessThanEqual(Deck deck, Category category, int level){
        return repository.findAllByDeckIdAndCategoryIdAndLevelLessThanEqual(deck.getId(), category.getId(), level);
    }

    public List<Word> findAllByDeckIdAndCategoryIdAndTag(Deck deck, Category category, Tag tag) {
        return repository.findAllByDeckIdAndCategoryIdAndTag(deck.getId(), category.getId(), tag);
    }

    public List<Word> findAllByDeckIdAndCategoryIdAndTagAndLevelLessThanEqual(Deck deck, Category category, Tag tag, int level) {
        return repository.findAllByDeckIdAndCategoryIdAndTagAndLevelLessThanEqual(deck.getId(), category.getId(), tag, level);
    }

    public String inflect(Word word, Inflection inflection){
        if (word.getPattern().getInflections().contains(inflection)){
            InflectionMode mode = inflection.getMode() != null ? inflection.getMode(): InflectionMode.NONE;
            String pattern = word.getPattern().getPattern();
            String inflectedWord = null;
            if (mode.equals(InflectionMode.START_APPEND)){
                inflectedWord = inflection.getAffix() + word.getWord();
            } else if (mode.equals(InflectionMode.END_APPEND)) {
                inflectedWord =  word.getWord() + inflection.getAffix();
            } else if (mode.equals(InflectionMode.START_SUBSTITUTE)){
                inflectedWord = inflection.getAffix() + word.getWord().substring(word.getWord().indexOf(pattern) + 1);
            } else if (mode.equals(InflectionMode.END_SUBSTITUTE)) {
                inflectedWord = word.getWord().substring(0, word.getWord().lastIndexOf(pattern)) + inflection.getAffix();
            } else {
                inflectedWord = word.getWord();
            }
            return inflectedWord;
        } else{
            return word.getWord();
        }
    }

    public Map<String,String> fullyInflect(Long id){

        Optional<Word> optionalWord = findById(id);

        if (optionalWord.isPresent()) {

            Word word = optionalWord.get();

            Map<String, String> inflections = new HashMap<>();

            if (word.getPattern()!= null) {

                for (Inflection inflection : word.getPattern().getInflections()) {
                    inflections.put(inflection.getInflection(), inflect(word, inflection));
                }
            }
            return inflections;
        } else {
            return null;
        }
    }



}
