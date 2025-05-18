package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patterns")
public class PatternController {

    @Autowired
    private PatternService patternService;

    @GetMapping("/detail/{id}")
    public Pattern findById(@PathVariable long id){
        return patternService.findById(id);
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<Pattern> addCategory(@PathVariable long id, @RequestBody Pattern pattern){
        Pattern newPattern = patternService.save(pattern,id);
        if (newPattern != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newPattern);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
