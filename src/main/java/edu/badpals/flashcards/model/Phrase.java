package edu.badpals.flashcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "phrases")
public class Phrase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    private Deck deck;

    @Column(name = "phrase")
    private String phrase;

    @Column(name = "level")
    private int level;

    @Column(name = "meaning")
    private String meaning;

    @OneToMany(mappedBy = "phrase", fetch = FetchType.EAGER)
    private List<SubstitutionRule> substitutionRules;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public List<SubstitutionRule> getSubstitutionRules() {
        return substitutionRules;
    }

    public void setSubstitutionRules(List<SubstitutionRule> substitutionRules) {
        this.substitutionRules = substitutionRules;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addSubstitutionRule(SubstitutionRule substitutionRule){
        getSubstitutionRules().add(substitutionRule);
    }

    public void removeSubstitutionRule(SubstitutionRule substitutionRule){
        getSubstitutionRules().remove(substitutionRule);
    }
}
