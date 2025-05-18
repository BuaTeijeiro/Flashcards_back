package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all/{id}")
    public List<Category> findAllByTeacher(@PathVariable long id){
        return categoryService.findAllByTeacher(id);
    }

    @GetMapping("/detail/{id}")
    public Category findById(@PathVariable long id){
        return categoryService.findById(id);
    }


}
