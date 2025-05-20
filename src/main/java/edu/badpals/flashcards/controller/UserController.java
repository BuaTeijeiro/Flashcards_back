package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.User;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    private ResponseEntity<User> login(@RequestBody User user){
        User userAttempt = userService.login(user);
        if (userAttempt != null){
            return ResponseEntity.status(HttpStatus.OK).body(userAttempt);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/{id}/decks")
    private ResponseEntity<List<Map<String, String>>> getDecks(@PathVariable long id){
        List<Map<String, String>> data = userService.getDecksData(id);
        if (data!= null){
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
