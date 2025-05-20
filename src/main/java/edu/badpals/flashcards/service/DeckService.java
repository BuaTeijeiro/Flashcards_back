package edu.badpals.flashcards.service;

import edu.badpals.flashcards.dto.DeckUserDto;
import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.DeckUser;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.model.User;
import edu.badpals.flashcards.repository.DeckRepository;
import edu.badpals.flashcards.repository.DeckUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {

    @Autowired
    private DeckRepository repository;
    @Autowired
    private DeckUserRepository deckUserRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;

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

    public DeckUser addUser(DeckUserDto user) {
        Optional<Deck> deckOptional = repository.findById(user.getDeckId());
        if (deckOptional.isPresent()){
            Deck deck = deckOptional.get();
            User userNew = userService.findByEmail(user.getUserEmail());
            if (userNew != null){
                return  deckUserRepository.save(new DeckUser(deck,userNew, 1));
            }
        }
        return null;
    }

    public List<DeckUser> getDeckUsers(long id) {
        Optional<Deck> deckOptional = repository.findById(id);
        if (deckOptional.isPresent()){
            return deckOptional.get().getUsers();
        }
        return null;
    }
}
