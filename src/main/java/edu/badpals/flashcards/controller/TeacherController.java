package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
}
