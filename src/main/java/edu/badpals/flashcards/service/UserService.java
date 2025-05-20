package edu.badpals.flashcards.service;


import edu.badpals.flashcards.model.DeckUser;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.model.User;
import edu.badpals.flashcards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private TeacherService teacherService;

    public Optional<User> findUserById(Long id){
        return repository.findById(id);
    }

    public User save(User user){
        return repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public User login(User userAttempt) {
        Optional<User> user = repository.findByEmail(userAttempt.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(userAttempt.getPassword())){
            if (user.get().getUser_type() == 1){
                return teacherService.findByEmail(userAttempt.getEmail()).get();
            } else
                return user.get();
        } else{
            return null;
        }
    }

    public User findByEmail(String userEmail) {
        Optional<User> userOptional = repository.findByEmail(userEmail);
        if (userOptional.isPresent()){
            return userOptional.get();
        } else {
            return null;
        }
    }

    public List<Map<String,String>> getDecksData(long id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()){
            List<Map<String,String>> decksData = new ArrayList<>();
            List<DeckUser> decks =  userOptional.get().getDecks();
            for (DeckUser deck : decks){
                Map<String, String> data = new HashMap<>();
                data.put("name", deck.getDeck().getName());
                data.put("words", String.valueOf(deck.getDeck().getWords().stream().filter(o -> o.getLevel() <= deck.getLevel()).toList().size()) );
                decksData.add(data);
            }
            return decksData;
        } else {
            return null;
        }
    }
}
