package edu.innotech;

import lombok.SneakyThrows;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Student {

    StudentRepo repo;
    private String name;
    private List grades = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public List getGrades() {
        return Collections.unmodifiableList(grades);
    }


    @SneakyThrows
    public void addGrade(int grade) {
        if (!repo.checkGrades(grade))
            grades.add(grade);
        else throw new IllegalArgumentException("not valid grade");
    }

    @SneakyThrows
    public int raiting() {
        return repo.getRaintingForGradeSum(
                grades.stream()
                        .mapToInt(x -> (int) x)
                        .sum()
        );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.grades);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.grades, other.grades);
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", marks=" + grades + '}';
    }


}

