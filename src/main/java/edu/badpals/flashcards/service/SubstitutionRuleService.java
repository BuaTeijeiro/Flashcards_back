package edu.badpals.flashcards.service;

import edu.badpals.flashcards.dto.SubstitutionRuleDto;
import edu.badpals.flashcards.model.*;
import edu.badpals.flashcards.repository.SubstitutionRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubstitutionRuleService {

    @Autowired
    private SubstitutionRuleRepository repository;
    @Autowired
    private PhraseService phraseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private WordService wordService;
    @Autowired
    private TagService tagService;
    

    public Optional<SubstitutionRule> findById(Long id){
        return repository.findById(id);
    }

    public SubstitutionRule save(SubstitutionRuleDto substitutionRuleDto){
        Phrase phrase = phraseService.findById(substitutionRuleDto.getPhraseId());
        Category category = categoryService.findById(substitutionRuleDto.getCategoryId());
        if (phrase != null && category != null && category.getInflectionsNames().contains(substitutionRuleDto.getInflectionName())) {
            SubstitutionRule substitutionRule = new SubstitutionRule();
            substitutionRule.setPhrase(phrase);
            substitutionRule.setWord(substitutionRuleDto.getWord());
            substitutionRule.setInflectionName(substitutionRuleDto.getInflectionName());
            substitutionRule.setCategory(category);
            substitutionRule.setTag(tagService.findById(substitutionRuleDto.getTagId()));
            return repository.save(substitutionRule);
        } else {
            return null;
        }
    }

    public SubstitutionRule update(SubstitutionRuleDto substitutionRuleDto) {
        Optional<SubstitutionRule> substitutionRuleOptional = repository.findById(substitutionRuleDto.getId());
        Category category = categoryService.findById(substitutionRuleDto.getCategoryId());
        if (substitutionRuleOptional.isPresent() && category != null && category.getInflectionsNames().contains(substitutionRuleDto.getInflectionName())) {
            SubstitutionRule substitutionRule = substitutionRuleOptional.get();
            substitutionRule.setWord(substitutionRuleDto.getWord());
            substitutionRule.setInflectionName(substitutionRule.getInflectionName());
            substitutionRule.setCategory(substitutionRule.getCategory());
            substitutionRule.setTag(tagService.findById(substitutionRuleDto.getTagId()));
            return repository.save(substitutionRule);
        } else {
            return null;
        }
    }

    public boolean delete(SubstitutionRuleDto substitutionRuleDto){
        Optional<SubstitutionRule> substitutionRuleOptional = repository.findById(substitutionRuleDto.getId());
        if (substitutionRuleOptional.isPresent()) {
            repository.delete(substitutionRuleOptional.get());
            return true;
        } else {
            return false;
        }

    }

    private String substitute(String phrase, String oldWord, String newWord){
        return phrase.replaceFirst(oldWord, newWord);
    }

    private List<String> substituteAll(Phrase phrase, SubstitutionRule substitutionRule, int level){
        List<String> phrases = new ArrayList<>();
        String text = phrase.getPhrase().toLowerCase();
        String targetWord = substitutionRule.getWord().toLowerCase();
        Tag tag = substitutionRule.getTag();
        List<Word> words;
        if (level == -1 && tag == null) {
            words = wordService.findAllByDeckAndCategory(phrase.getDeck(), substitutionRule.getCategory());
        } else if (level == -1) {
            words = wordService.findAllByDeckIdAndCategoryIdAndTag(phrase.getDeck(), substitutionRule.getCategory(), tag);
        } else if (tag == null) {
            words = wordService.findAllByDeckIdAndCategoryIdAndLevelLessThanEqual(phrase.getDeck(), substitutionRule.getCategory(), level);
        } else {
                words = wordService.findAllByDeckIdAndCategoryIdAndTagAndLevelLessThanEqual(phrase.getDeck(), substitutionRule.getCategory(), tag, level);
        }

        for (Word word: words){
            Inflection inflection = word.getPattern().getInflectionByName(substitutionRule.getInflectionName());
            String inflectedWord = wordService.inflect(word, inflection).toLowerCase();
            phrases.add(substitute(text, targetWord, inflectedWord));
        }
        return phrases;
    }


    public List<String> substituteAll(Long id){
        Optional<Phrase> phraseOptional = phraseService.getById(id);
        List<String> phrases = new ArrayList<>();
        if (phraseOptional.isPresent()){
            Phrase phrase = phraseOptional.get();
            for (SubstitutionRule rule : phrase.getSubstitutionRules()){
                phrases.addAll(substituteAll(phrase, rule, -1));
            }

        }
        return phrases;
    }

    public List<String> substituteByLevel(Long id, int level){
        Optional<Phrase> phraseOptional = phraseService.getById(id);
        List<String> phrases = new ArrayList<>();
        if (phraseOptional.isPresent()){
            Phrase phrase = phraseOptional.get();
            for (SubstitutionRule rule : phrase.getSubstitutionRules()){
                phrases.addAll(substituteAll(phrase, rule, level));
            }

        }
        return phrases;
    }

}
