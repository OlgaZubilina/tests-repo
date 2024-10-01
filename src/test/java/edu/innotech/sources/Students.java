package edu.innotech.sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Students {
    public static StudentData createStudentWithIdName() {
        int Id = new Random().nextInt(100);
        String name = "Vasya" + Id;
        StudentData studentData = new StudentData(Id, name, List.of());
        return studentData;
    }

    public static StudentData createStudentWithoutId() {
        String name = "Vasya" + new Random().nextInt(100);
        StudentData studentData = new StudentData(0, name, List.of());
        return studentData;
    }

    public static List<StudentData> studsList() {
        List<StudentData> randomStuds = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            StudentData stud = createStudentWithIdName();
            randomStuds.add(stud);
        }
        return randomStuds;
    }

    public static List<StudentData> studsListWithoutId() {
        List<StudentData> randomStuds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StudentData stud = createStudentWithoutId();
            randomStuds.add(stud);
        }
        return randomStuds;
    }

    public static List<StudentData> studsListWithoutName() {
        List<StudentData> randomStuds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StudentData stud = createStudentWithId();
            randomStuds.add(stud);
        }
        return randomStuds;
    }

    public static StudentData createFullStudent() {
        String name = "Vasya" + new Random().nextInt(100);
        ArrayList<Integer> marks = marksList();
        StudentData studentData = new StudentData();
        studentData.setName(name);
        studentData.setMarks(marks);
        return studentData;
    }

    public static StudentData createStudentWithId() {
        int Id = new Random().nextInt(100);
        StudentData studentData = new StudentData();
        studentData.setId(Id);
        return studentData;
    }

    public static ArrayList<Integer> marksList() {
        ArrayList<Integer> marksList = new ArrayList<>();
        int rnd = new Random().nextInt(20);
        for (int i = 0; i < rnd; i++) {
            int mark = new Random().nextInt(3) + 2;
            ;
            marksList.add(mark);
        }
        return marksList;
    }

    public static List<StudentData> studentsWithoutMarks() {
        List<StudentData> studentsWithoutMarks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StudentData studentData = createStudentWithIdName();
            studentsWithoutMarks.add(studentData);
        }
        return studentsWithoutMarks;
    }

    public static List<StudentData> fullStudents() {
        List<StudentData> fullStudents = new ArrayList<>();
        fullStudents.add(createFullStudent());
        fullStudents.get(0).setId(1);
        for (int i = 1; i < 20; i++) {
            fullStudents.add(createFullStudent());
            fullStudents.get(i).setId(i + 1);
        }
        return fullStudents;
    }

    public static List<StudentData> sameStudents() {
        List<StudentData> sameStudents = new ArrayList<>();
        StudentData st1 = new StudentData(25, "Pete", List.of(5, 4, 3, 4, 3, 2));
        sameStudents.add(st1);
        StudentData st2 = new StudentData(26, "Vova", List.of(5, 5, 5, 5, 4, 2));
        sameStudents.add(st2);
        StudentData st3 = new StudentData(27, "Pavel", List.of(5, 4, 5, 4, 3, 5, 5, 5, 5, 5));
        sameStudents.add(st3);
        StudentData st4 = new StudentData(28, "Pete", List.of(5, 4, 4, 5, 3, 5, 5, 5, 5, 5));
        sameStudents.add(st4);
        StudentData st5 = new StudentData(28, "Sasha", List.of(5, 2, 5, 4, 3, 5));
        return sameStudents;
    }
}