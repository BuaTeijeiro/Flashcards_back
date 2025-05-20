package edu.badpals.flashcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="teachers")
@PrimaryKeyJoinColumn(name="id")
@DiscriminatorValue(value="1")
public class Teacher extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Deck> ownedDecks;


    public List<Deck> getOwnedDecks() {
        return ownedDecks;
    }


    public void setOwnedDecks(List<Deck> decks) {
        this.ownedDecks = decks;
    }
}
