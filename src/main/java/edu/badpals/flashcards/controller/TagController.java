package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.dto.TagDto;
import edu.badpals.flashcards.dto.WordDto;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.model.Word;
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

    @PostMapping("/new")
    public ResponseEntity<Tag> addTag(@RequestBody TagDto tag){
        Tag newTag = tagService.save(tag);
        if (newTag != null){
            return ResponseEntity.status(HttpStatus.OK).body(newTag);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Tag> updateWord(@RequestBody TagDto tagDto){
        Tag updatedTag = tagService.update(tagDto);
        if (updatedTag != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedTag);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Tag> deleteTag(@RequestBody TagDto tagDto){
        if (tagService.delete(tagDto))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
