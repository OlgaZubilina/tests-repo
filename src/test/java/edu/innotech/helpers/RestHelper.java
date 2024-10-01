package edu.innotech.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.innotech.sources.StudentData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestHelper {
    public static void studentPost(ObjectMapper mapper, StudentData studentData, int statusCode) throws JsonProcessingException {
        RestAssured.given()
                .baseUri("http://localhost:8080/student/")
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(studentData))
                .when()
                .post()
                .then()
                .statusCode(statusCode);
    }

    public static String studentPostWithoutId(ObjectMapper mapper, StudentData studentData, int statusCode) throws JsonProcessingException {
        return RestAssured.given()
                .baseUri("http://localhost:8080/student/")
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(studentData))
                .when()
                .post()
                .then()
                .contentType(ContentType.JSON).statusCode(statusCode).extract().response().asString();
    }

    public static StudentData studentGet(ObjectMapper mapper, StudentData studentData, int statusCode) throws JsonProcessingException {
        return RestAssured.given()
                .baseUri("http://localhost:8080/student/" + studentData.getId())
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON).extract().response().as(StudentData.class);
    }

    public static void cannotGetStudent(StudentData studentData, int statusCode) throws JsonProcessingException {
        RestAssured.given()
                .baseUri("http://localhost:8080/student/" + studentData.getId())
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(statusCode);
    }

    public static void studentDelete(StudentData studentData, int statusCode) throws JsonProcessingException {
        RestAssured.given()
                .baseUri("http://localhost:8080/student/" + studentData.Id)
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .statusCode(statusCode).extract().statusCode();
    }

    public static void studentDeleteWithoutCheck(StudentData studentData) throws JsonProcessingException {
        RestAssured.given()
                .baseUri("http://localhost:8080/student/" + studentData.Id)
                .contentType(ContentType.JSON)
                .when()
                .delete();
    }

    public static String GetTopStudent(int statusCode) throws JsonProcessingException {
        return RestAssured.given()
                .baseUri("http://localhost:8080/topStudent/")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract()
                .response().asString();
    }

    public static String GetTopStudentWithoutMarks(int statusCode) throws JsonProcessingException {
        return RestAssured.given()
                .baseUri("http://localhost:8080/topStudent/")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200).extract().response().asString();
    }
}
