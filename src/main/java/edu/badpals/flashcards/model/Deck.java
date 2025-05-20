package edu.badpals.flashcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Teacher owner;

    @OneToMany(mappedBy = "deck", fetch = FetchType.EAGER)
    private List<Word> words;

    @OneToMany(mappedBy = "deck", fetch = FetchType.EAGER)
    private List<Phrase> phrases;

    @OneToMany(mappedBy = "deck", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<DeckUser> users;

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

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public List<DeckUser> getUsers() {
        return users;
    }

    public void setUsers(List<DeckUser> user) {
        this.users = user;
    }

    public void addUser(DeckUser user){
        getUsers().add(user);
    }

    public void removeUser(DeckUser user){
        getUsers().remove(user);
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
