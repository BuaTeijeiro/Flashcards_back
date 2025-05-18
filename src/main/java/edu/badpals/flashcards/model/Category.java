package edu.badpals.flashcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "inflectionNames",
            joinColumns = @JoinColumn(name = "category_id")
    )
    @Column(name = "inflection_name")
    private List<String> inflectionsNames;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Pattern> patterns;

    @ManyToOne
    @JsonIgnore
    private Teacher owner;

    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher teacher) {
        this.owner = teacher;
    }

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

    public List<String> getInflectionsNames() {
        return inflectionsNames;
    }

    public void setInflectionsNames(List<String> inflectionsNames) {
        this.inflectionsNames = inflectionsNames;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public void addInflectionName(String name){
        inflectionsNames.add(name);
    }
}
