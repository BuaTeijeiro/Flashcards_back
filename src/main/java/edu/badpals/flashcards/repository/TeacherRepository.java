package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
