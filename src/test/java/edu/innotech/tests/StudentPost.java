package edu.innotech.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.innotech.helpers.RestHelper;
import edu.innotech.sources.StudentData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static edu.innotech.helpers.RestHelper.studentGet;

public class StudentPost {

    @ParameterizedTest(name = "Can create student")
    @MethodSource("edu.innotech.sources.Students#studsList")
    public void createNewStudent(StudentData studentData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RestHelper.studentPost(mapper, studentData, 201);
        RestHelper.studentDeleteWithoutCheck(studentData);
    }

    @ParameterizedTest(name = "Can create student without Id")
    @MethodSource("edu.innotech.sources.Students#studsListWithoutId")
    public void createNewStudentWithoutId(StudentData studentData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = RestHelper.studentPostWithoutId(mapper, studentData, 201);
        System.out.println(str);
        int responce = Integer.parseInt(str);
        studentData.setId(responce);
        StudentData studentResponce = studentGet(mapper, studentData, 200);
        Assertions.assertEquals(studentData, studentResponce);
        RestHelper.studentDeleteWithoutCheck(studentData);
    }

    @ParameterizedTest(name = "Can update student")
    @MethodSource("edu.innotech.sources.Students#fullStudents")
    public void updateStudent(StudentData studentData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RestHelper.studentPost(mapper, studentData, 201);
        System.out.println("Существующий студент:" + studentGet(mapper, studentData, 200));
        studentData.setName("NewName");
        studentData.setMarks(List.of(4,3,5,2));
        RestHelper.studentPost(mapper, studentData, 201);
        StudentData studentResponce = studentGet(mapper, studentData, 200);
        System.out.println("Измененный студент:" + studentResponce);
        Assertions.assertEquals("NewName", studentResponce.name);
        RestHelper.studentDeleteWithoutCheck(studentData);
    }

    @ParameterizedTest(name = "Negative creation: test without name")
    @MethodSource("edu.innotech.sources.Students#studsListWithoutName")
    public void cantCreateStudent(StudentData studentData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RestHelper.studentPost(mapper, studentData, 400);
        RestHelper.studentDeleteWithoutCheck(studentData);
    }
}
