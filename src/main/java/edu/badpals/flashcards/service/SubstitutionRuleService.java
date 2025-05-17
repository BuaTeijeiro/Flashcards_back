package edu.badpals.flashcards.service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.SubstitutionRule;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.repository.CategoryRepository;
import edu.badpals.flashcards.repository.SubstitutionRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubstitutionRuleService {

    @Autowired
    private SubstitutionRuleRepository repository;

    public SubstitutionRule save(SubstitutionRule substitutionRule){
        return repository.save(substitutionRule);
    }

    public void delete(SubstitutionRule substitutionRule){
        repository.delete(substitutionRule);
    }

    public Optional<SubstitutionRule> findById(Long id){
        return repository.findById(id);
    }


}
