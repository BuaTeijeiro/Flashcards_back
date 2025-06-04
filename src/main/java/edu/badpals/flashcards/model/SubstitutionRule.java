package edu.badpals.flashcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "substitution_rules")
public class SubstitutionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Phrase phrase;

    @Column
    private String word;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tag tag;

    @ManyToOne
    private Category category;

    @Column(name = "inflection_name")
    private String inflectionName;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getInflectionName() {
        return inflectionName;
    }

    public void setInflectionName(String inflectionName) {
        this.inflectionName = inflectionName;
    }
}
