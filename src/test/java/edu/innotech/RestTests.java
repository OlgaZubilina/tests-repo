package edu.innotech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RestTests {
    @DisplayName("Can create student without Id")
    @Test
    public void createNewStudent() throws JsonProcessingException {
        StudentData studentData = createStudentWithName("Vasya");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(studentData));
        System.out.println(studentData);
        RestAssured.given()
                .baseUri("http://localhost:8080/student/")
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(studentData))
                .when()
                .post()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(201).extract();
        // Assertions.assertEquals(studentData1.Id,studentData.Id);
    }

    public StudentData createStudentWithIdName(int Id, String name) {
        StudentData studentData = new StudentData();
        studentData.setId(Id);
        studentData.setName(name);
        return studentData;
    }

    public StudentData createStudentWithName(String name) {
        StudentData studentData = new StudentData();
        studentData.setName(name);
        return studentData;
    }


}
