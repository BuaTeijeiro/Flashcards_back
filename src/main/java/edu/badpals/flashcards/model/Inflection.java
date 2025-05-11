package edu.badpals.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inflection {
    private long id;
    private String inflection;
    private String affix;
    private InflectionMode mode;
}
