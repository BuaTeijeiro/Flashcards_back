package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.TagService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Tag addTag(@PathVariable long id, @RequestParam Tag tag){
        return tagService.save(tag, id);
    }
}
