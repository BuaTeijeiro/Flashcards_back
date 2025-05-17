package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatternService {

    @Autowired
    private PatternRepository repository;

    public Pattern save(Pattern pattern){
        return repository.save(pattern);
    }

    public void delete(Pattern pattern){
        repository.delete(pattern);
    }

}
