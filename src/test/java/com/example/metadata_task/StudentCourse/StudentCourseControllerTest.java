package com.example.metadata_task.StudentCourse;

import com.example.metadata_task.Course.Course;
import com.example.metadata_task.Course.CourseRepository;
import com.example.metadata_task.Student.Student;
import com.example.metadata_task.Student.StudentRepository;
import com.example.metadata_task.Student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentCourseControllerTest {
    @Autowired
    private StudentCourseService studentCourseService;

    @MockBean
    private StudentCourseRepository studentCourseRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        studentCourseService = new StudentCourseService(studentCourseRepository , studentRepository, courseRepository);
    }

    StudentCourse studentCourse = new StudentCourse(
            1,
            1
    );

    Student student = new Student(
            1,
            "john",
            "john@gmail.com",
            "72638292",
            "France",
            LocalDate.of(2005, Month.JANUARY, 5)
    );

    Course course = new Course(
            1,
            "math",
            "2021",
            "block c",
            "jane",
            LocalDate.of(2021, Month.JANUARY, 5)
    );

    @Test
    void getStudentCourse() {
        studentCourseService.getStudentCouses();

        verify(studentCourseRepository).findAll();
    }

    @Test
    void addStudentCourse() {

        when( studentRepository.findStudentByName( student.getName() ) ).thenReturn(Optional.of(student));
        when( courseRepository.findCourseByTitle( course.getTitle() ) ).thenReturn(Optional.of(course));


        studentCourseService.addStudentCourse(studentCourse, student, course);


        ArgumentCaptor<StudentCourse> studentCourseArgumentCaptor =
                ArgumentCaptor.forClass(StudentCourse.class);

        verify(studentCourseRepository).save(studentCourseArgumentCaptor.capture());

        StudentCourse captureStudentCourse = studentCourseArgumentCaptor.getValue();

        assertThat(captureStudentCourse).isEqualTo(studentCourse);
    }
    @Test
    void getStudentCourseReport() {
        String fieldName = "";
        String fieldValue = "";
        String Null = "";
        studentCourseService.getStudentCoursesReport(fieldName, fieldValue, Null);

        verify(studentCourseRepository).getReport();
    }

    @Test
    void getStudentCourseReportStudentName() {
        String fieldName = "studentName";
        String fieldValue = "1";
        String Null = "";
        studentCourseService.getStudentCoursesReport(fieldName, fieldValue, Null);


        verify(studentCourseRepository).getReportFilterByStudent(fieldValue);


    }
    @Test
    void getStudentCourseReportCourseName() {
        String fieldName = "courseTitle";
        String fieldValue = "1";
        String Null = "";
        studentCourseService.getStudentCoursesReport(fieldName, fieldValue, Null);


        verify(studentCourseRepository).getReportFilterByCourse(fieldValue);

    }
    @Test
    void getStudentCourseReportCourseNull() {
        String fieldName = "";
        String fieldValue = "";
        String Null = "course";
        studentCourseService.getStudentCoursesReport(fieldName, fieldValue, Null);

        verify(studentCourseRepository).getReportFilterByNullCourse();
    }
    @Test
    void getStudentCourseReportStudentNull() {
        String fieldName = "";
        String fieldValue = "";
        String Null = "student";
        studentCourseService.getStudentCoursesReport(fieldName, fieldValue, Null);

        verify(studentCourseRepository).getReportFilterByNullStudent();
    }


}