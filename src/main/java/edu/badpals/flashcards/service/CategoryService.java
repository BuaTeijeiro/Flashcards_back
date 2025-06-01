package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.CategoryRepository;
import edu.badpals.flashcards.repository.InflectionRepository;
import edu.badpals.flashcards.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private InflectionService inflectionService;
    @Autowired
    private PatternRepository patternRepository;
    @Autowired
    private InflectionRepository inflectionRepository;


    public Category save(Category category, long id){
        Optional<Teacher> teacherOptional = teacherService.findById(id);
        if (teacherOptional.isPresent()) {
            category.setOwner(teacherOptional.get());
            return repository.save(category);
        } else {
            return null;
        }
    }

    public Category update(Category category) {
        Category oldCategory = findById(category.getId());
        if (oldCategory != null){
            oldCategory.setName(category.getName());
            oldCategory.setLanguage(category.getLanguage());
            return repository.save(oldCategory);
        } else
            return null;
    }

    public boolean delete(Category category){
        Optional<Category> categoryOptional = repository.findById(category.getId());
        if (categoryOptional.isPresent()){
            repository.delete(categoryOptional.get());
            return true;
        }
        return false;
    }

    public void getCategoriesTeacher(Teacher teacher){
        repository.findAllByOwner(teacher);
    }

    public List<Category> findAllByTeacher(long id) {
        Optional<Teacher> teacherOptional = teacherService.findById(id);
        if (teacherOptional.isPresent())
            return repository.findAllByOwner(teacherOptional.get());
        else
            return null;
    }

    public List<Category> findAllByTeacherAndLanguage(long id, String language) {
        Optional<Teacher> teacherOptional = teacherService.findById(id);
        if (teacherOptional.isPresent())
            return repository.findAllByOwnerAndLanguage(teacherOptional.get(), language);
        else
            return null;
    }

    public Category findById(long id) {
        Optional<Category> categoryOptional = repository.findById(id);
        return categoryOptional.isPresent()? categoryOptional.get() : null;
    }

    public Category addInflection(long id, String name) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.addInflectionName(name);
            for (Pattern pattern : category.getPatterns()){
                pattern.addInflection(inflectionService.save(new Inflection(name, pattern)));
            }
            return repository.save(category);
        } else {
            return null;
        }
    }

    public Category deleteInflection(long id, String name) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.deleteInflectionName(name);
            for (Pattern pattern : category.getPatterns()){
                for (Inflection inflection : pattern.getInflections()){
                    if (inflection.getInflection().equals(name)){
                        inflectionRepository.delete(inflection);
                    }
                }
            }
            return repository.save(category);
        } else {
            return null;
        }
    }


}
