package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category save(Category category){
        return repository.save(category);
    }

    public void delete(Category category){
        repository.delete(category);
    }

    public void getCategoriesTeacher(Teacher teacher){
        repository.findAllByOwner(teacher);
    }

}
