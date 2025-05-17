package edu.badpals.flashcards.service;

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

    public Word save(Word word){
        return repository.save(word);
    }

    public void delete(Word word){
        repository.delete(word);
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

    public String inflect(Word word, Inflection inflection){
        if (word.getPattern().getInflections().contains(inflection)){
            InflectionMode mode = inflection.getMode();
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
            for (Inflection inflection : word.getPattern().getInflections()) {
                inflections.put(inflection.getInflection(), inflect(word, inflection));
            }
            return inflections;
        } else {
            return null;
        }
    }
}
