package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public Optional<Teacher> findById(long id){
        return repository.findById(id);
    }

    public Teacher save(Teacher teacher){
        return repository.save(teacher);
    }

    public void delete(Teacher teacher){
        repository.delete(teacher);
    }
}
