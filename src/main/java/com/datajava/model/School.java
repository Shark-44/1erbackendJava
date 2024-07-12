package com.datajava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// pour essais 
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchool")
    private int idSchool;
    
    @Column(name = "nameSchool")
    private String nameSchool;
    
    @Column(name = "photoSchool")
    private String photoSchool;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private Set<Student> students = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "school_has_langage",
        joinColumns = @JoinColumn(name = "idSchool"),
        inverseJoinColumns = @JoinColumn(name = "idLangage")
    )
    @JsonManagedReference
    private Set<Langage> langages = new HashSet<>();

    // Getters and setters
    public int getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(int idSchool) {
        this.idSchool = idSchool;
    }

    public String getNameSchool() {
        return nameSchool;
    }

    public void setNameSchool(String nameSchool) {
        this.nameSchool = nameSchool;
    }

    public String getPhotoSchool() {
        return photoSchool;
    }

    public void setPhotoSchool(String photoSchool) {
        this.photoSchool = photoSchool;
    }

    public Set<Langage> getLangages() {
        return langages;
    }

    public void setLangages(Set<Langage> langages) {
        this.langages = langages;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
