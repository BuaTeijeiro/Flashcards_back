package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatternService {

    @Autowired
    private PatternRepository repository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InflectionService inflectionService;

    public Pattern save(Pattern pattern, long id){
        Category category = categoryService.findById(id);
        if (category != null) {
            pattern.setCategory(category);
            Pattern newPattern = repository.save(pattern);
            for (String inflection: category.getInflectionsNames()){
                inflectionService.save(new Inflection(inflection, pattern));
            }
            return newPattern;
        } else {
            return null;
        }
    }

    public Pattern update(Pattern pattern) {
        Pattern oldPattern = findById(pattern.getId());
        if (oldPattern != null){
            oldPattern.setName(pattern.getName());
            oldPattern.setPattern(pattern.getPattern());
            return repository.save(oldPattern);
        } else
            return null;
    }

    public boolean delete(Pattern pattern){
        Optional<Pattern> patternOptional = repository.findById(pattern.getId());
        if (patternOptional.isPresent()){
            repository.delete(patternOptional.get());
            return true;
        }
        return false;
    }

    public Pattern findById(long id) {
        Optional<Pattern> categoryOptional = repository.findById(id);
        return categoryOptional.isPresent()? categoryOptional.get() : null;
    }


}
