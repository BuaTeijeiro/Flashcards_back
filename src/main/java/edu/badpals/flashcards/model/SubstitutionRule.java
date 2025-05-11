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
public class SubstitutionRule {
    private long id;
    private Phrase phrase;
    private Word word;
    private List<Tag> tags;
    private Category category;
    private String inflection;
}
