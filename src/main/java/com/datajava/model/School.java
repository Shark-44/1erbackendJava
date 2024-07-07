package com.datajava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchool")
    private int idSchool;
    
    @Column(name = "NameSchool")
    private String NameSchool;
    
    @Column(name = "PhotoSchool")
    private String PhotoSchool;

    @ManyToMany
    @JoinTable(
        name = "school_has_langage",
        joinColumns = @JoinColumn(name = "idSchool"),
        inverseJoinColumns = @JoinColumn(name = "idLangage")
    )
    private Set<Langage> langages;

    public Set<Langage> getLangages() {
        return langages;
    }

    public void setLangages(Set<Langage> langages) {
        this.langages = langages;
    }


    // Getters and setters
    public int getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(int idSchool) {
        this.idSchool = idSchool;
    }

    public String getNameSchool() {
        return NameSchool;
    }

    public void setNameSchool(String NameSchool) {
        this.NameSchool = NameSchool;
    }

    public String getPhotoSchool() {
        return PhotoSchool;
    }

    public void setPhotoSchool(String PhotoSchool) {
        this.PhotoSchool = PhotoSchool;
    }
}
