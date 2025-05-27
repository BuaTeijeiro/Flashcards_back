package edu.badpals.flashcards.dto;

public class DeckSummaryDto {
    private long id;
    private String name;
    private long phrasesCount;
    private long wordsCount;

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

    public long getPhrasesCount() {
        return phrasesCount;
    }

    public void setPhrasesCount(long phrasesCount) {
        this.phrasesCount = phrasesCount;
    }

    public long getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(long wordsCount) {
        this.wordsCount = wordsCount;
    }
}
