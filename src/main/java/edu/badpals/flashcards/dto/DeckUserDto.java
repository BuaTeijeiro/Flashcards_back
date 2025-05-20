package edu.badpals.flashcards.dto;

public class DeckUserDto {
    private String UserEmail;
    private long DeckId;
    private int level;

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public long getDeckId() {
        return DeckId;
    }

    public void setDeckId(long deckId) {
        DeckId = deckId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
