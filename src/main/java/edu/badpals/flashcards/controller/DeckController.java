package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Deck;
import edu.badpals.flashcards.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
