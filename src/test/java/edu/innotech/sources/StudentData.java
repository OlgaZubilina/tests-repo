package edu.innotech.sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentData {
    public int Id;
    public String name;
    public List marks = new ArrayList<>();

    public StudentData(int id, String name, List marks) {
        Id = id;
        this.name = name;
        this.marks = marks;
    }

    public StudentData() {
    }

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

    public List getMarks() {
        return marks;
    }

    public void setMarks(List marks) {
        this.marks = marks;
    }

    public static double averageMark(List<Integer> marks) {
        double sum = 0;
        for (int i = 0; i < marks.size(); i++) {
            sum = sum + marks.get(i);
        }
        return sum / marks.size();
    }

    public static ArrayList<StudentData> raiting(List<StudentData> students) {
        ArrayList<StudentData> raiting = new ArrayList<>();
        double max = 1;
        int marksCount = 1;
        for (int i = 0; i < students.size(); i++) {
            if (averageMark(students.get(i).getMarks()) >= max && students.get(i).getMarks().size() >= marksCount) {
                max = averageMark(students.get(i).getMarks());
                raiting.add(students.get(i));
                break;
            }
        }
        for (int i = 0; i < students.size(); i++) {
            if (averageMark(students.get(i).getMarks()) > max) {
                max = averageMark(students.get(i).getMarks());
                marksCount = students.get(i).getMarks().size();
                raiting.set(0, students.get(i));
            }
        }
        for (int i = 0; i < students.size(); i++) {
            if (averageMark(students.get(i).getMarks()) == max && students.get(i).getMarks().size() > marksCount) {
                marksCount = students.get(i).getMarks().size();
                ;
                raiting.set(0, students.get(i));
            }
        }
        for (int i = 0; i < students.size(); i++) {
            if (averageMark(students.get(i).getMarks()) == max
                    && students.get(i).getMarks().size() == marksCount
                    && !(students.get(i).equals(raiting.get(0)))) {
                marksCount = students.get(i).getMarks().size();
                ;
                raiting.add(0, students.get(i));
            }
        }
        return raiting;

    }


    @Override
    public String toString() {
        return "{" +
                "\"id\":" + Id +
                ",\"name\":\"" + name +
                "\",\"marks\":" + marks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentData that = (StudentData) o;
        return Id == that.Id && Objects.equals(name, that.name) && Objects.equals(marks, that.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, marks);
    }
}
