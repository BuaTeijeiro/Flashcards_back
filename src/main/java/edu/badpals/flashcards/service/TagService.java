package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository repository;
    @Autowired
    private TeacherService teacherService;

    public List<Tag> findAllByTeacher(Teacher teacher){
        return repository.findAllByTeacher(teacher);
    }

    public List<Tag> findAllByTeacher(Long id){
        Optional<Teacher> teacherOptional = teacherService.findById(id);
        if (teacherOptional.isPresent())
            return repository.findAllByTeacher(teacherOptional.get());
        else
            return null;
    }

    public Tag save(Tag tag, long id){
        Optional<Teacher> teacherOptional = teacherService.findById(id);
        if (teacherOptional.isPresent()) {
            tag.setTeacher(teacherOptional.get());
            return repository.save(tag);
        } else {
            return null;
        }
    }

    public void delete(Tag tag){
        repository.delete(tag);
    }
}
