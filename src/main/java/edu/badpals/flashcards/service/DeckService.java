package edu.badpals.flashcards.service;

import edu.badpals.flashcards.dto.DeckDto;
import edu.badpals.flashcards.dto.DeckUserDto;
import edu.badpals.flashcards.model.*;
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

    public Deck save(DeckDto deckDto) {
        Optional<Teacher> user = teacherService.findById(deckDto.getOwner());
        if (user.isPresent()) {
            Deck deck = new Deck();
            deck.setOwner(user.get());
            deck.setName(deckDto.getName());
            deck.setLanguage(deckDto.getLanguage());
            return save(deck);
        }
        else
            return null;
    }

    public Deck update(DeckDto deckDto) {
        Optional<Deck> deckOptional = repository.findById(deckDto.getId());
        if (deckOptional.isPresent()) {
            Deck deck = deckOptional.get();
            deck.setName(deckDto.getName());
            deck.setLanguage(deckDto.getLanguage());
            return save(deck);
        }
        else
            return null;
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
                return  deckUserRepository.save(new DeckUser(deck,userNew, user.getLevel()));
            }
        }
        return null;
    }

    public boolean removeUser(DeckUserDto user) {
        Optional<DeckUser> deckUser = findDeckUser(user);
        if (deckUser.isPresent()){
            deckUserRepository.delete(deckUser.get());
            return true;
        }
        return false;
    }

    public DeckUser updateUser(DeckUserDto user) {
        Optional<DeckUser> deckUserOptional = findDeckUser(user);
        if (deckUserOptional.isPresent()){
            DeckUser deckUser = deckUserOptional.get();
            deckUser.setLevel(user.getLevel());
            return deckUserRepository.save(deckUser);
        }
        return null;
    }

    private Optional<DeckUser> findDeckUser(DeckUserDto user) {
        Optional<Deck> deckOptional = repository.findById(user.getDeckId());
        User userNew = userService.findByEmail(user.getUserEmail());
        if (deckOptional.isPresent() && userNew != null) {
            return deckUserRepository.findByDeckAndUser(deckOptional.get(), userNew);
        }
        return Optional.empty();
    }

    public List<DeckUser> getDeckUsers(long id) {
        Optional<Deck> deckOptional = repository.findById(id);
        if (deckOptional.isPresent()){
            return deckOptional.get().getUsers();
        }
        return null;
    }


    public boolean delete(Deck deck) {
        Optional<Deck> deckOptional = repository.findById(deck.getId());
        if (deckOptional.isPresent()){
            repository.delete(deckOptional.get());
            return true;
        }
        return false;
    }
}
