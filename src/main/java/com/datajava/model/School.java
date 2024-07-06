package com.datajava.model;

import javax.persistence.*;

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
