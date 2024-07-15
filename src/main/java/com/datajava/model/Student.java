package com.datajava.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStudent")
    private int idStudent;

    @Column(name = "name")
    private String name;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idSchool", nullable = true)
    @JsonBackReference
    private School school;
    
    @ManyToMany
    @JoinTable(
        name = "student_has_langage",
        joinColumns = @JoinColumn(name = "idStudent"),
        inverseJoinColumns = @JoinColumn(name = "idLangage")
    )
    @JsonManagedReference
    @JsonIgnoreProperties("students") // reference ciculaire
    private Set<Langage> langages = new HashSet<>();
    

    // Getters and setters
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Langage> getLangages() {
        return langages;
    }

    public void setLangages(Set<Langage> langages) {
        this.langages = langages;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
