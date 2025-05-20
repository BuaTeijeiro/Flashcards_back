package edu.badpals.flashcards.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DeckUserId implements Serializable {
    private Long deckId;
    private Long userId;
}
