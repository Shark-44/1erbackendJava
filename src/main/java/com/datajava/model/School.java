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
}
