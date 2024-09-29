package edu.innotech;

public interface StudentRepo {
    int getRaintingForGradeSum(int sum);
    Boolean checkGrades (int grades);
    long count();
    void delete(Student entity);
    void deleteAll(Iterable<Student> entities);
    Iterable<Student> findAll();
    Student save(Student entity);
    Iterable<Student> saveAll(Iterable<Student> entities);
}
