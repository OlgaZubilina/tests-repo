package edu.innotech;

import java.util.ArrayList;
import java.util.List;

public class StudentData {
    int Id;
    String name;
    List grades = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public List getGrades() {
        return grades;
    }

    public void setGrades(List grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "Id =" + Id +
                ", name='" + name + '\'' +
                ", grades=" + grades +
                '}';
    }
}
