package com.datajava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "notation")
public class Notation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotation" )
    private int idNotation;

    @Column(name = "note")
    private Double note;

    @Column(name = "dateNote")
    private Date dateNote;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;

    @ManyToOne
    @JoinColumn(name = "langage_id")
    @JsonBackReference
    private Langage langage;

    // Getters and setters

    public int getIdNotation () {
        return idNotation;
    }

    public void setIdNotation(int idNotation) {
        this.idNotation = idNotation;
    }

    public Double getNote() {
        return note;
    }

    public void setNote (Double note) {
        this.note = note;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote (Date dateNote) {
        this.dateNote =dateNote;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Langage getLangage() {
        return langage;
    }

    public void setLangage(Langage langage) {
        this.langage = langage;
    }
}
