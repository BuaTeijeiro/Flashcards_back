package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.dto.WordDto;
import edu.badpals.flashcards.model.Word;
import edu.badpals.flashcards.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/words/")
public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping("/new/")
    public Word save(@RequestBody WordDto word){
        return wordService.save(word);
    }

    @GetMapping("/detail/{id}")
    public Word getById(@PathVariable Long id){
        Word word = wordService.findById(id).get();
        return word;
    }

    @GetMapping("/full-inflected/{id}")
    public Map<String, String> inflectWord(@PathVariable Long id){
        return wordService.fullyInflect(id);
    }
}
