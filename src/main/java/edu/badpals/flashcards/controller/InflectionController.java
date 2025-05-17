package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.InflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inflections")
public class InflectionController {

    @Autowired
    private InflectionService inflectionService;
}
