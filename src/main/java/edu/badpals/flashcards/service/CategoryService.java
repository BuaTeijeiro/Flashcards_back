package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Category save(Category category, long id){
        Optional<Teacher> teacherOptional = teacherService.findById(id);
        if (teacherOptional.isPresent()) {
            category.setOwner(teacherOptional.get());
            return repository.save(category);
        } else {
            return null;
        }
    }

    public void delete(Category category){
        repository.delete(category);
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
            }
            return repository.save(category);
        } else {
            return null;
        }
    }
}
