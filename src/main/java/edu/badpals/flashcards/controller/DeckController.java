package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.dto.DeckDto;
import edu.badpals.flashcards.dto.DeckUserDto;
import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.model.DeckUser;
import edu.badpals.flashcards.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/decks/")
public class DeckController {
    @Autowired
    private DeckService deckService;

    @GetMapping("/all/{id}")
    public List<Deck> getDecks(@PathVariable Long id) {
        return deckService.getDecksUser(id);
    }

    @GetMapping("/data/{id}")
    public Deck getDeck(@PathVariable Long id) {
        Deck deck = deckService.getDeck(id);
        return deckService.getDeck(id);
    }

    @PostMapping("/new")
    public ResponseEntity<Deck> newDeck(@RequestBody DeckDto deckDto){
        Deck deck = deckService.save(deckDto);
        if (deck != null){
            return ResponseEntity.status(HttpStatus.OK).body(deck);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Deck> updateDeck(@RequestBody DeckDto deckDto){
        Deck deck = deckService.update(deckDto);
        if (deck != null){
            return ResponseEntity.status(HttpStatus.OK).body(deck);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<DeckUser>> getUsersDeck(@PathVariable long id){
        List<DeckUser> users = deckService.getDeckUsers(id);
        if (users != null){
            return ResponseEntity.ok(users);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping("/users/add")
    public ResponseEntity<DeckUser> addUserToDeck(@RequestBody DeckUserDto user){
        DeckUser deckUser = deckService.addUser(user);
        if (deckUser != null){
            return ResponseEntity.ok(deckUser);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/users/update")
    public ResponseEntity<DeckUser> updateUser(@RequestBody DeckUserDto user){
        DeckUser deckUser = deckService.updateUser(user);
        if (deckUser != null){
            return ResponseEntity.ok(deckUser);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/users/remove")
    public ResponseEntity<DeckUser> removeUserFromDeck(@RequestBody DeckUserDto user){
        if (deckService.removeUser(user)){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
