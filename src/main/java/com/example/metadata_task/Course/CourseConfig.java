package com.example.metadata_task.Course;

import com.example.metadata_task.Student.Student;
import com.example.metadata_task.Student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner commandLineRunnerr(CourseRepository repository){
        return args -> {
            Course algorithm = new Course(
                    "algorithm",
                    "2021",
                    "block a",
                    "john",
                    LocalDate.of(2021, Month.JANUARY, 5)
            );
            Course math = new Course(
                    "math",
                    "2021",
                    "block c",
                    "jane",
                    LocalDate.of(2021, Month.JANUARY, 5)
            );
            Course sociology = new Course(
                    "sociology",
                    "2021",
                    "block d",
                    "johnny",
                    LocalDate.of(2021, Month.JANUARY, 5)
            );
            repository.saveAll(
                    List.of(algorithm, math, sociology)
            );
        };
    }
}
