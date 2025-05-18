package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patterns")
public class PatternController {

    @Autowired
    private PatternService patternService;

    @GetMapping("/detail/{id}")
    public Pattern findById(@PathVariable long id){
        return patternService.findById(id);
    }
}
