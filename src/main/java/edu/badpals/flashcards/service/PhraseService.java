package edu.badpals.flashcards.service;


import edu.badpals.flashcards.model.*;
import edu.badpals.flashcards.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhraseService {

    @Autowired
    private PhraseRepository repository;
    @Autowired
    private WordService service;
    @Autowired
    private WordService wordService;
    @Autowired
    private SubstitutionRuleService substitutionRuleService;

    public Phrase save(Phrase phrase){
        return repository.save(phrase);
    }

    public void delete(Phrase phrase){
        repository.delete(phrase);
    }

    public Optional<Phrase> getById(Long id){
        return repository.findById(id);
    }

    private String substitute(String phrase, String oldWord, String newWord){
        return phrase.replaceFirst(oldWord, newWord);
    }

    private List<String> substituteAll(Phrase phrase, SubstitutionRule substitutionRule){
        List<String> phrases = new ArrayList<>();
        String text = phrase.getPhrase();
        String targetWord = substitutionRule.getWord();
        Inflection inflection = substitutionRule.getInflection();
        List<Word> words = wordService.findAllByDeck(phrase.getDeck());
        for (Word word: words){
            String inflectedWord = wordService.inflect(word, inflection);
            phrases.add(substitute(text, targetWord, inflectedWord));
        }

        return phrases;

    }

    public List<String> substituteAll(Long id, Long idRule){
        return substituteAll(getById(id).get(), substitutionRuleService.findById(idRule).get());
    }
}
