package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.CategoryRepository;
import edu.badpals.flashcards.repository.InflectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Inflection update(long id, Inflection inflection) {
        Optional<Inflection> inflectionOptional = repository.findById(id);
        if (inflectionOptional.isPresent()){
            Inflection oldInflection = inflectionOptional.get();
            oldInflection.setMode(inflection.getMode());
            oldInflection.setAffix(inflection.getAffix());
            return repository.save(oldInflection);
        } return null;
    }
}
