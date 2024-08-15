package com.datajava.service;

import java.util.Objects;

public class StudentLangageKey {
    private final int studentId;
    private final String studentName;
    private final int langageId;
    private final String langageName;

    public StudentLangageKey(int studentId, String studentName, int langageId, String langageName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.langageId = langageId;
        this.langageName = langageName;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getLangageId() {
        return langageId;
    }

    public String getLangageName() {
        return langageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentLangageKey that = (StudentLangageKey) o;
        return studentId == that.studentId &&
               langageId == that.langageId &&
               studentName.equals(that.studentName) &&
               langageName.equals(that.langageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, langageId, langageName);
    }

}
