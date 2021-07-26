package com.example.metadata_task.StudentCourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StudentCourseRepository  extends JpaRepository<StudentCourse, Long> {
    @Query("select count(sc) from StudentCourse sc where sc.courseId = :courseId")
    int countStudentsInCourse(Long courseId);

    @Query("select count(sc) from StudentCourse sc where sc.studentId = :studentId")
    int countCoursesOfStudent(Long studentId);

    @Query("Select s.name as studentName, s.email as studentEmail, c.title as courseTitle, c.instructor as courseInstructor\n" +
            "from Student s , Course c,  StudentCourse sc\n" +
            "where sc.studentId = s.id\n" +
            "and sc.courseId = c.id")
    List <NameTitle> getReport();

    @Query("Select s.name as studentName, s.email as studentEmail, c.title as courseTitle, c.instructor as courseInstructor\n" +
            "from Student s , Course c,  StudentCourse sc\n" +
            "where sc.studentId = s.id\n" +
            "and sc.courseId = c.id\n" +
            "and s.name = :fieldValue")
    List <NameTitle> getReportFilterByStudent(String fieldValue);

    @Query("Select s.name as studentName, s.email as studentEmail, c.title as courseTitle, c.instructor as courseInstructor\n" +
            "from Student s , Course c,  StudentCourse sc\n" +
            "where sc.studentId = s.id\n" +
            "and sc.courseId = c.id\n" +
            "and c.title = :fieldValue")
    List <NameTitle> getReportFilterByCourse(String fieldValue);

    @Query("Select s.name as studentName, s.email as studentEmail \n" +
            "From Student s\n" +
            "left JOIN StudentCourse sc on sc.studentId = s.id\n" +
            "where sc.id IS NULL")
    List <NameTitle> getReportFilterByNullStudent();

    @Query("Select c.title as courseTitle, c.instructor as courseInstructor \n" +
            "From Course c\n" +
            "left JOIN StudentCourse sc on sc.courseId = c.id\n" +
            "where sc.id IS NULL")
    List <NameTitle> getReportFilterByNullCourse();

}
