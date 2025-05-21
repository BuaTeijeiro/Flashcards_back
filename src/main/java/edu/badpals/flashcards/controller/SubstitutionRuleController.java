package edu.badpals.flashcards.controller;

import edu.badpals.flashcards.dto.SubstitutionRuleDto;
import edu.badpals.flashcards.model.SubstitutionRule;
import edu.badpals.flashcards.service.CategoryService;
import edu.badpals.flashcards.service.SubstitutionRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/substitution-rules")
public class SubstitutionRuleController {
    @Autowired
    private SubstitutionRuleService substitutionRuleService;

    @PostMapping("/new")
    public ResponseEntity<SubstitutionRule> addSubstitutionRule(@RequestBody SubstitutionRuleDto substitutionRule){
        SubstitutionRule newSubstitutionRule = substitutionRuleService.save(substitutionRule);
        if (newSubstitutionRule != null){
            return ResponseEntity.status(HttpStatus.OK).body(newSubstitutionRule);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SubstitutionRule> updateWord(@RequestBody SubstitutionRuleDto substitutionRuleDto){
        SubstitutionRule updatedSubstitutionRule = substitutionRuleService.update(substitutionRuleDto);
        if (updatedSubstitutionRule != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedSubstitutionRule);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<SubstitutionRule> deleteSubstitutionRule(@RequestBody SubstitutionRuleDto substitutionRuleDto){
        if (substitutionRuleService.delete(substitutionRuleDto))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/alt/{id}")
    public List<String> getAlternativeSentences(@PathVariable long id){
        return substitutionRuleService.substituteAll(id);
    }
}
