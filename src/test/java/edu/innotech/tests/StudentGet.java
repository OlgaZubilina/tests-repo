package edu.innotech.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.innotech.helpers.RestHelper;
import edu.innotech.sources.StudentData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.innotech.helpers.RestHelper.studentGet;

public class StudentGet {

    @ParameterizedTest(name ="Can get student" )
    @MethodSource ("edu.innotech.sources.Students#studsList")
    public void canGetStudent(StudentData studentData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RestHelper.studentPost(mapper,studentData,201);
        StudentData studentResponce = studentGet(mapper,studentData,200);
        Assertions.assertEquals(studentResponce,studentData);
        RestHelper.studentDeleteWithoutCheck(studentData);
    }

    @ParameterizedTest(name ="Can't get:student not found" )
    @MethodSource ("edu.innotech.sources.Students#studsList")
    public void cantGetStudent(StudentData studentData) throws JsonProcessingException {
        RestHelper.studentDeleteWithoutCheck(studentData);
        RestHelper.cannotGetStudent(studentData,404);

    }
}
