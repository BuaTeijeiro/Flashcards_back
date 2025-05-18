package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {

    @Autowired
    private DeckRepository repository;
    @Autowired
    private TeacherService teacherService;

    public Deck save(Deck deck){
        return repository.save(deck);
    }

    public List<Deck> getDecksUser(Long id){
        Optional<Teacher> user = teacherService.findById(id);
        if (user.isPresent())
            return repository.findByOwner(user.get());
        else
            return null;
    }

    public Deck getDeck(Long id){
        Optional<Deck> deck = repository.findById(id);
        if (deck.isPresent()){
            return deck.get();
        } else {
            return null;
        }
    }

    public Deck findById(long deckId) {
        Optional<Deck> categoryOptional = repository.findById(deckId);
        return categoryOptional.isPresent()? categoryOptional.get() : null;
    }
}
