package edu.badpals.flashcards.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.badpals.flashcards.model.Phrase;
import edu.badpals.flashcards.model.Teacher;
import edu.badpals.flashcards.model.Word;
import jakarta.persistence.*;

import java.util.List;

public class DeckForStudyDto {
    private long id;
    private String name;
    private List<Word> words;
    private List<Phrase> phrases;
    private int level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
