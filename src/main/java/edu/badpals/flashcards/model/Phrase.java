package edu.badpals.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phrase {
    private long id;
    private Deck deck;
    private String phrase;
    private List<SubstitutionRule> substitutionRules;
}
