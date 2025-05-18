package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.TagService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/all/{id}")
    public List<Tag> findAllByTeacher(@PathVariable long id){
        return tagService.findAllByTeacher(id);
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<Tag> addTag(@PathVariable long id, @RequestBody Tag tag){
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.save(tag,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Tag> deleteTag(@PathVariable long id){
        if (tagService.delete(id))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
