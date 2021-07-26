package com.example.metadata_task.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam@gmail.com",
                    "7165282",
                    "Lebanon",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student alex = new Student(
                    "john",
                    "john@gmail.com",
                    "72638292",
                    "France",
                    LocalDate.of(2005, Month.JANUARY, 5)
            );
            Student marc = new Student(
                    "marc",
                    "marc@gmail.com",
                    "73839227",
                    "Lebanon",
                    LocalDate.of(1999, Month.JANUARY, 5)
            );
            repository.saveAll(
                    List.of(mariam, alex, marc)
            );
        };
    }
}
