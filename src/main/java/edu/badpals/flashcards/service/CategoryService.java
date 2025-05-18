package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Tag;
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

    public Category save(Category category){
        return repository.save(category);
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
}
