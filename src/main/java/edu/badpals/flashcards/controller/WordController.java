package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.dto.WordDto;
import edu.badpals.flashcards.model.Word;
import edu.badpals.flashcards.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping("/new")
    public Word save(@RequestBody WordDto word){
        return wordService.save(word);
    }

    @GetMapping("/detail/{id}")
    public Word getById(@PathVariable Long id){
        Word word = wordService.findById(id).get();
        return word;
    }

    @PutMapping("/update")
    public ResponseEntity<Word> updateWord(@RequestBody WordDto word){
        Word updatedWord = wordService.update(word);
        if (updatedWord != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedWord);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Word> deleteWord(@RequestBody WordDto word){
        if ( wordService.delete(word)){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/full-inflected/{id}")
    public ResponseEntity<Map<String, String>> inflectWord(@PathVariable Long id){
        Map<String, String> inflections = wordService.fullyInflect(id);
        if (inflections == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(inflections);
        }
    }
}
