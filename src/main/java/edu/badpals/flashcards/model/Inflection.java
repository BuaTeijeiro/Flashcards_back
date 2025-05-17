package edu.badpals.flashcards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "inflections")
public class Inflection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "")
    private String inflection;

    @Column(name = "affix")
    private String affix;

    @ManyToOne
    private Pattern pattern;

    @Enumerated(EnumType.STRING)
    @Column(name="mode")
    private InflectionMode mode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInflection() {
        return inflection;
    }

    public void setInflection(String inflection) {
        this.inflection = inflection;
    }

    public String getAffix() {
        return affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public InflectionMode getMode() {
        return mode;
    }

    public void setMode(InflectionMode mode) {
        this.mode = mode;
    }
}
