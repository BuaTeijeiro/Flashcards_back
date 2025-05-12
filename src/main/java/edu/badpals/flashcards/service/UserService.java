package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.User;
import edu.badpals.flashcards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
}
