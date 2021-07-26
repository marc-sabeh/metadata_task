package com.example.metadata_task.Course;

import com.example.metadata_task.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByTitle(String title);
}
