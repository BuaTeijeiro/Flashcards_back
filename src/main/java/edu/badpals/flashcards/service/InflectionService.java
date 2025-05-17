package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.CategoryRepository;
import edu.badpals.flashcards.repository.InflectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InflectionService {

    @Autowired
    private InflectionRepository repository;

    public Inflection save(Inflection inflection){
        return repository.save(inflection);
    }

    public void delete(Inflection inflection){
        repository.delete(inflection);
    }

}
