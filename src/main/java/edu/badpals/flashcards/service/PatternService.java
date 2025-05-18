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

    public void delete(Pattern pattern){
        repository.delete(pattern);
    }

    public Pattern findById(long id) {
        Optional<Pattern> categoryOptional = repository.findById(id);
        return categoryOptional.isPresent()? categoryOptional.get() : null;
    }
}
