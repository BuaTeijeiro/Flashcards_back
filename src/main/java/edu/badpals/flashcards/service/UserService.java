package edu.badpals.flashcards.service;


import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.model.User;
import edu.badpals.flashcards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> findUserById(Long id){
        return repository.findById(id);
    }

    public User save(User user){
        return repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }
}
