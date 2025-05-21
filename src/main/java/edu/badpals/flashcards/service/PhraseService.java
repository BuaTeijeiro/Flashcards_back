package edu.badpals.flashcards.service;


import edu.badpals.flashcards.dto.PhraseDto;
import edu.badpals.flashcards.dto.PhraseDto;
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
    private DeckService deckService;


    public Phrase save(PhraseDto phraseDto){
        Deck deck = deckService.findById(phraseDto.getDeckId());
        if (deck != null) {
            Phrase phrase = new Phrase();
            phrase.setDeck(deck);
            phrase.setPhrase(phraseDto.getPhrase());
            phrase.setMeaning(phraseDto.getMeaning());
            phrase.setLevel(phraseDto.getLevel());
            return repository.save(phrase);
        } else {
            return null;
        }
    }

    public Phrase update(PhraseDto phraseDto) {
        Optional<Phrase> phraseOptional = repository.findById(phraseDto.getId());
        if (phraseOptional.isPresent()) {
            Phrase oldPhrase = phraseOptional.get();
            oldPhrase.setPhrase(phraseDto.getPhrase());
            oldPhrase.setMeaning(phraseDto.getMeaning());
            oldPhrase.setLevel(phraseDto.getLevel());
            return repository.save(oldPhrase);
        } else {
            return null;
        }
    }

    public boolean delete(PhraseDto phraseDto){
        Optional<Phrase> phraseOptional = repository.findById(phraseDto.getId());
        if (phraseOptional.isPresent()) {
            repository.delete(phraseOptional.get());
            return true;
        } else {
            return false;
        }

    }

    public Optional<Phrase> getById(Long id){
        return repository.findById(id);
    }



    public Phrase findById(Long id) {
        Optional<Phrase> phraseOptional= repository.findById(id);
        return phraseOptional.isPresent()? phraseOptional.get():null;
    }

    
}
