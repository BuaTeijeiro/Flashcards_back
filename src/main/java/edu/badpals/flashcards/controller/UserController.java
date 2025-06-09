package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.dto.DeckForStudyDto;
import edu.badpals.flashcards.dto.DeckSummaryDto;
import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.User;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.SubstitutionRuleService;
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
    @Autowired
    private SubstitutionRuleService substitutionRuleService;

    @PostMapping("/login")
    private ResponseEntity<User> login(@RequestBody User user){
        User userAttempt = userService.login(user);
        if (userAttempt != null){
            return ResponseEntity.status(HttpStatus.OK).body(userAttempt);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User user){
        User userAttempt = userService.register(user);
        if (userAttempt != null){
            return ResponseEntity.status(HttpStatus.OK).body(userAttempt);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/{id}/decks")
    private ResponseEntity<List<DeckSummaryDto>> getDecks(@PathVariable long id){
        List<DeckSummaryDto> data = userService.getDecksData(id);
        if (data!= null){
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/deckData/{deckId}")
    private ResponseEntity<DeckForStudyDto> getDeckData(@PathVariable long id, @PathVariable long deckId){
        DeckForStudyDto deck = userService.getDecksAccordingToLevel(id, deckId);
        if (deck!= null){
            return ResponseEntity.ok(deck);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/alt/{level}")
    private List<String>  getAlternativePhraseByLevel(@PathVariable long id, @PathVariable int level){
        return substitutionRuleService.substituteByLevel(id, level);
    }


}
