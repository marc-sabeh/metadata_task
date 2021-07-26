package com.example.metadata_task.Course;

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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CourseControllerTest {
    @Autowired
    private CourseService courseService;
    @MockBean
    private CourseRepository courseRepository;

    Course course = new Course(
            "math",
            "2021",
            "block c",
            "jane",
            LocalDate.of(2021, Month.JANUARY, 5)
    );

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseRepository);
    }


    @Test
    void getCourse() {
        courseService.getCouses();

        verify(courseRepository).findAll();
    }

    @Test
    void createCourse() {
        courseService.addNewCourse(course);

        ArgumentCaptor<Course> courseArgumentCaptor =
                ArgumentCaptor.forClass(Course.class);

        verify(courseRepository).save(courseArgumentCaptor.capture());

        Course captureCourse = courseArgumentCaptor.getValue();

        assertThat(captureCourse).isEqualTo(course);
    }

    @Test
    void deleteCourse() {
        when( courseRepository.existsById( course.getId() ) ).thenReturn(true);

        courseService.deleteCourse(course.getId());

        verify(courseRepository).deleteById(course.getId());

    }

    @Test
    void willThrowWhenIdNotFound() {
        given(courseRepository.existsById(course.getId()))
                .willReturn(false);

        assertThatThrownBy(() ->  courseService.deleteCourse(course.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course with id  " + course.getId() + " does not exist");

        verify(courseRepository, never()).deleteById(any());

    }

    @Test
    void updateCourse() {
        Long id = 1L;
        when( courseRepository.findById( id ) ).thenReturn(Optional.of(course));

        courseService.updateCourse(id, course);
        course.setTitle(course.getTitle());
        course.setSemester(course.getSemester());
        course.setInstructor(course.getInstructor());
        course.setLocation(course.getLocation());

        verify(courseRepository).save(course);

    }
}