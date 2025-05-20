package edu.badpals.flashcards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "deck_users")
public class DeckUser {
    @EmbeddedId
    private DeckUserId id = new DeckUserId();

    @ManyToOne
    @MapsId("deckId")
    private Deck deck;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @Column(name = "level")
    private int level;

    public DeckUser() {
    }

    public DeckUser(Deck deck, User user, int level) {
        this.deck = deck;
        this.user = user;
        this.level = level;
    }

    public DeckUserId getId() {
        return id;
    }

    public void setId(DeckUserId id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public void setLeve(int leve) {
        this.level = leve;
    }
}
