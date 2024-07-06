package com.datajava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "langage")
public class Langage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLangage")
    private int idLangage;

    @Column(name = "NameLangage")
    private String nameLangage;

    @ManyToMany(mappedBy = "langages")
    @JsonBackReference

    private Set<Student> students;

    @ManyToMany(mappedBy = "langages")
    @JsonBackReference
    private Set<School> schools;
    
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
}
