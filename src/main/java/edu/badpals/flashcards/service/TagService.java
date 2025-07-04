package edu.badpals.flashcards.service;

import edu.badpals.flashcards.dto.TagDto;
import edu.badpals.flashcards.model.Tag;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public Tag save(TagDto tagDto){
        Optional<Teacher> teacherOptional = teacherService.findById(tagDto.getOwnerId());
        if (teacherOptional.isPresent()) {
            Tag tag = new Tag();
            tag.setTeacher(teacherOptional.get());
            tag.setTag(tagDto.getTag());
            return repository.save(tag);
        } else {
            return null;
        }
    }

    public Tag update(TagDto tagDto) {
        Optional<Tag> tagOptional = repository.findById(tagDto.getId());
        if (tagOptional.isPresent()) {
            Tag oldTag = tagOptional.get();
            oldTag.setTag(tagDto.getTag());
            return repository.save(oldTag);
        } else {
            return null;
        }
    }

    public boolean delete(TagDto tagDto){
        Optional<Tag> tagOptional = repository.findById(tagDto.getId());
        if (tagOptional.isPresent()) {
            repository.delete(tagOptional.get());
            return true;
        } else {
            return false;
        }

    }


    public Tag findById(long tagId) {
        Optional<Tag> tagOptional = repository.findById(tagId);
        if (tagOptional.isPresent()) {
            return tagOptional.get();
        } else {
            return null;
        }
    }
}
