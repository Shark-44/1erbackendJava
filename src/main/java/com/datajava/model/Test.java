package com.datajava.model;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtest")
    private int idTest;
    
    @Column(name = "testcol")
    private String testCol;

    // Getters and setters
    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getTestCol() {
        return testCol;
    }

    public void setTestCol(String testCol) {
        this.testCol = testCol;
    }
}
