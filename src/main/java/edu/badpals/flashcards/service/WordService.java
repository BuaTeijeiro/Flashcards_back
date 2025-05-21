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

    public Word save(WordDto wordDto){
        Word word = new Word();

        Deck deck = deckService.findById(wordDto.getDeckId());

        if (deck != null ){
            word.setMeaning(wordDto.getMeaning());
            word.setWord(wordDto.getWord());
            word.setDeck(deck);
            word.setLevel(wordDto.getLevel());
            Category category = categoryService.findById(wordDto.getCategoryId());
            if (category!= null){
                word.setCategory(category);
                Pattern pattern = patternService.findById(wordDto.getPatternId());
                if (pattern != null){
                    word.setPattern(pattern);
                }
            }
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
            Category category = categoryService.findById(word.getCategoryId());
            if (category!= null){
                oldWord.setCategory(category);
                Pattern pattern = patternService.findById(word.getPatternId());
                if (pattern != null){
                    oldWord.setPattern(pattern);
                }
            }
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
                inflectedWord = inflection.getAffix() + word.getWord().substring(word.getWord().indexOf(pattern));
            } else if (mode.equals(InflectionMode.END_SUBSTITUTE)) {
                inflectedWord = word.getWord().substring(0, word.getWord().indexOf(pattern)) + inflection.getAffix();
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
