package com.datajava.model;

import javax.persistence.*;

@Entity
@Table(name = "langage")
public class Langage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLangage")
    private int idLangage;

    @Column(name = "NameLangage")
    private String NameLangage;

    // Getters and setters
    public int getIdLangage() {
        return idLangage;
    }

    public void setIdLangage(int idLangage) {
        this.idLangage = idLangage;
    }

    public String getNameLangage() {
        return NameLangage;
    }

    public void setNameLangage(String NameLangage) {
        this.NameLangage = NameLangage;
    }
}
