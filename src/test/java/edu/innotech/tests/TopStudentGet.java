package edu.innotech.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.innotech.helpers.RestHelper;
import edu.innotech.sources.StudentData;
import edu.innotech.sources.Students;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class TopStudentGet {

    @DisplayName("Get some top students with the same raiting")
    @Test
    public void checkRaitingSameStuds() throws JsonProcessingException {
        List<StudentData> students = Students.sameStudents();
        System.out.println("Список студентов " + students);
        getTopStudentsCheck(students);
        massDelete(students);
    }

    @DisplayName("Get top with random students")
    @Test
    public void checkRaitingRandomStuds() throws JsonProcessingException {
        List<StudentData> students = Students.fullStudents();
        System.out.println("Список студентов " + students);
        getTopStudentsCheck(students);
        massDelete(students);
    }

    @DisplayName("Students without marks: get empty list")
    @Test
    public void checkRaitingWitoutMarks() throws JsonProcessingException {
        List<StudentData> students = Students.studentsWithoutMarks();
        System.out.println("Список студентов " + students);
        getEmptyTopStudentsCheck(students);
        massDelete(students);
    }

    private static void getTopStudentsCheck(List<StudentData> students) throws JsonProcessingException {
        ArrayList<StudentData> raiting = StudentData.raiting(students);
        Set<StudentData> sortedRaiting = raiting.stream().collect(Collectors.toSet());
        System.out.println("Ожидаемый результат: " + sortedRaiting);
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < students.size(); i++) {
            RestHelper.studentPost(mapper, students.get(i), 201);
        }
        String result = RestHelper.GetTopStudent(200);
        Set<StudentData> resultStuds = Set.of(mapper.readValue(result, StudentData[].class));
        System.out.println("Фактический результат: " + resultStuds);
        Assertions.assertEquals(sortedRaiting, resultStuds);
    }

    private static void getEmptyTopStudentsCheck(List<StudentData> students) throws JsonProcessingException {
        ArrayList<StudentData> raiting = StudentData.raiting(students);

        System.out.println("Ожидаемый результат: ");
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < students.size(); i++) {
            RestHelper.studentPost(mapper, students.get(i), 201);
        }
        String result = RestHelper.GetTopStudentWithoutMarks(200);
        System.out.println("Фактический результат: " + result);
        Assertions.assertEquals("", result);
    }

    private static void massDelete(List<StudentData> students) throws JsonProcessingException {
        for (int i = 0; i < students.size(); i++) {
            RestHelper.studentDeleteWithoutCheck(students.get(i));
        }
    }
}
