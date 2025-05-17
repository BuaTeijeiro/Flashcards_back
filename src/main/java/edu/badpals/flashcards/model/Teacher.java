package edu.badpals.flashcards.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Entity
public class Teacher{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner")
    private List<Deck> decks;


    public List<Deck> getDecks() {
        return decks;
    }


    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }
}
