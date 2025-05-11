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
public class Pattern {
    private long id;
    private String pattern;
    private Category category;
    private List<Inflection> inflections;
}
