package com.example.metadata_task.StudentCourse;

import com.example.metadata_task.Course.Course;
import com.example.metadata_task.Course.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentCourseConfig {
    @Bean
    CommandLineRunner commandLineRunnerrr(StudentCourseRepository repository){
        return args -> {
            StudentCourse a = new StudentCourse(
                   1,
                    1
            );
            StudentCourse b = new StudentCourse(
                    1,
                    2
            );
            StudentCourse c = new StudentCourse(
                    2,
                    2
            );
            repository.saveAll(
                    List.of(a, b, c)
            );
        };
    }
}
