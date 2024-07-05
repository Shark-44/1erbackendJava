package com.datajava.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Studient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStudient;

    private String name;
    private String firstname;
    private Date birthday;
    private String photo;

    // Getters and setters
    public int getIdStudient() {
        return idStudient;
    }

    public void setIdStudient(int idStudient) {
        this.idStudient = idStudient;
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
}
