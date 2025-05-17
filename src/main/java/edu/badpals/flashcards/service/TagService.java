package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagRepository repository;

    public Tag save(Tag tag){
        return repository.save(tag);
    }

    public void delete(Tag tag){
        repository.delete(tag);
    }
}
