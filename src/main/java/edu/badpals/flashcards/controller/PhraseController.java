package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Phrase;
import edu.badpals.flashcards.model.Word;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phrases")
public class PhraseController {
    @Autowired
    private PhraseService phraseService;

    @GetMapping("/alt/{id}/{idRule}")
    public List<String> getAlternativeSentences(@PathVariable long id, @PathVariable long idRule){
        return phraseService.substituteAll(id, idRule);
    }

    @GetMapping("/detail/{id}")
    public Phrase getById(@PathVariable Long id){
        return phraseService.findById(id);
    }
}
