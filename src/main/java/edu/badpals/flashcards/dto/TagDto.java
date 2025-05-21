package edu.badpals.flashcards.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.badpals.flashcards.model.Teacher;
import jakarta.persistence.*;

public class TagDto {
    private long id;
    private String tag;
    private long ownerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
