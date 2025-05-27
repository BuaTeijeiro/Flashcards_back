package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.dto.PhraseDto;
import edu.badpals.flashcards.dto.TagDto;
import edu.badpals.flashcards.model.Phrase;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.model.Word;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phrases")
public class PhraseController {
    @Autowired
    private PhraseService phraseService;



    @GetMapping("/detail/{id}")
    public ResponseEntity<Phrase> getById(@PathVariable Long id){
        Phrase newPhrase = phraseService.findById(id);
        if (newPhrase != null){
            return ResponseEntity.status(HttpStatus.OK).body(newPhrase);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping("/new")
    public ResponseEntity<Phrase> addTag(@RequestBody PhraseDto phraseDto){
        Phrase newPhrase = phraseService.save(phraseDto);
        if (newPhrase != null){
            return ResponseEntity.status(HttpStatus.OK).body(newPhrase);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Phrase> updateWord(@RequestBody PhraseDto phraseDto){
        Phrase updatedPhrase = phraseService.update(phraseDto);
        if (updatedPhrase != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedPhrase);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Phrase> deleteTag(@RequestBody PhraseDto phraseDto){
        if (phraseService.delete(phraseDto))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
