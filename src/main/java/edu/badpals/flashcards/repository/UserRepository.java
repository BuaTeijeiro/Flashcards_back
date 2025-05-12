package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
