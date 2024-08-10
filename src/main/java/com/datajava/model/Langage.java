package com.datajava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "langage")
public class Langage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLangage")
    private int idLangage;

    @Column(name = "nameLangage")
    private String nameLangage;

    @ManyToMany(mappedBy = "langage")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();
    
    @ManyToMany(mappedBy = "langage")
    @JsonBackReference
    private Set<School> schools = new HashSet<>();

    @OneToMany(mappedBy = "langage")
    @JsonManagedReference
    private Set<Notation> notations= new HashSet<>();


    // Getters and setters
    public int getIdLangage() {
        return idLangage;
    }

    public void setIdLangage(int idLangage) {
        this.idLangage = idLangage;
    }

    public String getNameLangage() {
        return nameLangage;
    }

    public void setNameLangage(String nameLangage) {
        this.nameLangage = nameLangage;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<School> getSchools() {
        return schools;
    }

    public void setSchools(Set<School> schools) {
        this.schools = schools;
    }

    public Set<Notation> getNotations() {
        return notations;
    }

    public void setNotations(Set<Notation> notations) {
        this.notations = notations;
    }

}
