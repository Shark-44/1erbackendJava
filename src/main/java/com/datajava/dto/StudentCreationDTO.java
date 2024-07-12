package com.datajava.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentCreationDTO {
    private String name;
    private String firstname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String photo;

    // Getters and setters
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