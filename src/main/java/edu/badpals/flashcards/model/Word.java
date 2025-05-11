package edu.badpals.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    private long id;
    private Deck deck;
    private String word;
    private String meaning;
    private Pattern pattern;

    public Category getCategory(){
        return getPattern().getCategory();
    }
}
