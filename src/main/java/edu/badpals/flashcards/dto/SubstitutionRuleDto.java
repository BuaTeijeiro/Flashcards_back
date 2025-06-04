package edu.badpals.flashcards.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.model.Phrase;
import edu.badpals.flashcards.model.Tag;
import jakarta.persistence.*;

import java.util.List;

public class SubstitutionRuleDto {
    private long id;
    private long phraseId;
    private String word;
    private long tagId;
    private long categoryId;
    private String inflectionName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(long phraseId) {
        this.phraseId = phraseId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getInflectionName() {
        return inflectionName;
    }

    public void setInflectionName(String inflectionName) {
        this.inflectionName = inflectionName;
    }
}
