package edu.badpals.flashcards.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "decks")
public class Deck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Teacher owner;

    @OneToMany(mappedBy = "deck", fetch = FetchType.EAGER)
    private List<Word> words;

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

    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher owner) {
        this.owner = owner;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deck{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", owner=").append(owner);
        sb.append('}');
        return sb.toString();
    }
}
