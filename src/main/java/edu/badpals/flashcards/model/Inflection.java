package edu.badpals.flashcards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "inflections")
public class Inflection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "inflection")
    private String inflection;

    @Column(name = "affix")
    private String affix;

    @ManyToOne
    private Pattern pattern;

    @Enumerated(EnumType.STRING)
    @Column(name="mode")
    private InflectionMode mode;

    public Inflection(){

    }

    public Inflection(String inflection, Pattern pattern) {
        this.inflection = inflection;
        this.pattern = pattern;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inflection that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
