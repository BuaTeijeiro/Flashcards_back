package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all-by-language/{id}")
    public List<Category> findAllByTeacher(@PathVariable long id, @RequestParam String language){
        return categoryService.findAllByTeacherAndLanguage(id, language);
    }

    @GetMapping("/all/{id}")
    public List<Category> findAllByTeacher(@PathVariable long id){
        return categoryService.findAllByTeacher(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Category> deleteCategory(@RequestBody Category category){
        if (categoryService.delete(category)){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Category> findById(@PathVariable long id){
        Category category = categoryService.findById(id);
        if (category != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(category);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<Category> addCategory(@PathVariable long id, @RequestBody Category category){
        Category newCategory = categoryService.save(category,id);
        if (newCategory != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category updatedCategory = categoryService.update(category);
        if (updatedCategory != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedCategory);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/newInflection/{id}")
    public ResponseEntity<Category> addInflection(@PathVariable long id, @RequestParam String newInflection){
        Category category = categoryService.addInflection(id, newInflection);
        if (category != null)
            return ResponseEntity.status(HttpStatus.OK).body(category);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/deleteInflection/{id}")
    public ResponseEntity<Category> deleteInflection(@PathVariable long id, @RequestParam String deleteInflection){
        Category category = categoryService.deleteInflection(id, deleteInflection);
        if (category != null)
            return ResponseEntity.status(HttpStatus.OK).body(category);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
