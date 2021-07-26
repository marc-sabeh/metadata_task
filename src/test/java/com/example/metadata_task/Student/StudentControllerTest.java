package com.example.metadata_task.Student;

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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {


    @Autowired
    private StudentService studentService;
    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void getStudents() {
        studentService.getStudents();

        verify(studentRepository).findAll();
    }

    @Test
    void registerNewStudent() {
        Student student = new Student(
                "alex",
                "alex@gmail.com",
                "72638292",
                "France",
                LocalDate.of(2005, Month.JANUARY, 5)
        );
        studentService.addNewStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailTaken() {
        Student student = new Student(
                "john",
                "john@gmail.com",
                "72638292",
                "France",
                LocalDate.of(2005, Month.JANUARY, 5)
        );

        given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.of(student));

        assertThatThrownBy(() ->  studentService.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");

        verify(studentRepository, never()).save(any());

    }

    @Test
    void deleteStudent() {
        Student student = new Student(
                1L,
                "john",
                "john@gmail.com",
                "72638292",
                "France",
                LocalDate.of(2005, Month.JANUARY, 5)
        );
        when( studentRepository.existsById( student.getId() ) ).thenReturn(true);

        studentService.deleteStudent(student.getId());

        verify(studentRepository).deleteById(student.getId());

    }

    @Test
    void willThrowWhenIdNotFound() {
        Student student = new Student(
                1L,
                "john",
                "john@gmail.com",
                "72638292",
                "France",
                LocalDate.of(2005, Month.JANUARY, 5)
        );

        given(studentRepository.existsById(student.getId()))
                .willReturn(false);

        assertThatThrownBy(() ->  studentService.deleteStudent(student.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id  " + student.getId() + " does not exist");

        verify(studentRepository, never()).deleteById(any());

    }

    @Test
    void updateStudent() {
        Long id = 1L;
        Student student = new Student(
                1L,
                "john",
                "john@gmail.com",
                "72638292",
                "France",
                LocalDate.of(2005, Month.JANUARY, 5)
        );
        when( studentRepository.findById( id ) ).thenReturn(Optional.of(student));

        studentService.updateStudent(id, student);
        student.setName(student.getName());
        student.setEmail(student.getEmail());
        student.setPhone(student.getPhone());
        student.setAddress(student.getAddress());

        verify(studentRepository).save(student);

    }
}