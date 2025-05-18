package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.InflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inflections")
public class InflectionController {

    @Autowired
    private InflectionService inflectionService;

    @PutMapping("/update/{id}")
    public ResponseEntity<Inflection> updateInflection(@PathVariable long id, @RequestBody Inflection inflection){
        Inflection updatedInflection = inflectionService.update(id, inflection);
        if (updatedInflection != null)
            return ResponseEntity.status(HttpStatus.OK).body(updatedInflection);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
