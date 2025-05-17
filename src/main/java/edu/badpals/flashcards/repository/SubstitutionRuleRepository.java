package edu.badpals.flashcards.repository;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.SubstitutionRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubstitutionRuleRepository extends JpaRepository<SubstitutionRule, Long> {
}
