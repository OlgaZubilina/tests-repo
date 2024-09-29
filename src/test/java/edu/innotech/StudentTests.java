package edu.innotech;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class StudentTests {

    @DisplayName("Correct get name")
    @Test
    public void canGetName() {
        Student student = new Student("Vasya");
        Assertions.assertEquals(student.getName(), "Vasya");
    }

    @DisplayName("Correct set name")
    @Test
    public void canSetName() {
        Student student = new Student("Vasya");
        student.setName("Vova");
        Assertions.assertEquals(student.getName(), "Vova");
    }

    @RepeatedTest(value = 4, name = "Adding correct grades to list")
    public void marksInRange(RepetitionInfo repetitionInfo) {
        Student student = new Student("Vasya");
        int num = repetitionInfo.getCurrentRepetition() + 1;
        student.addGrade(num);
        Assertions.assertEquals(student.getGrades().get(0), num);
    }

    @ParameterizedTest(name = "Adding uncorrect grades throws exeption")
    @MethodSource("edu.innotech.MarksGenerator#ints")
    public void marksNotInRange(int x) {
        Student student = new Student("Vasya");
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.addGrade(x));
    }

    @ParameterizedTest(name = "Unsupported grades's adding throws exeption")
    @MethodSource("edu.innotech.MarksGenerator#ints")
    public void marksIncorrectAdding(int x) {
        Student student = new Student("Vasya");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> student.getGrades().add(x));
    }

    @DisplayName("CorrectEquals")
    @Test
    public void equalsStudent() {
        Student student = new Student("Vasya");
        student.addGrade(5);
        student.addGrade(4);
        Student student1 = new Student("Vova");
        student1.setName("Vasya");
        student1.addGrade(5);
        student1.addGrade(4);
        Assertions.assertEquals(student1, student);
    }

    @DisplayName("CorrectToString")
    @Test
    public void correctToString() {
        Student student = new Student("Vasya");
        student.addGrade(5);
        student.addGrade(4);
        Assertions.assertEquals("Student{name=Vasya, marks=[5, 4]}", student.toString());
    }

    @DisplayName("Correct hashCode")
    @Test
    public void correctHashCode() {
        Student student = new Student("Vasya");
        student.addGrade(5);
        student.addGrade(4);
        int i = student.getName().hashCode();
        int i1 = student.getGrades().hashCode();
        int hash = (13 * 7 + i) * 13 + i1;
        Assertions.assertEquals(student.hashCode(), hash);
    }
}