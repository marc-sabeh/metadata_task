package com.example.metadata_task.Course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCouses(){
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        courseRepository.save(course);
        System.out.println(course);
    }

    public void deleteCourse(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if (!exists) {
            throw new IllegalStateException("Course with id  " + courseId + " does not exist");
        }

        courseRepository.deleteById(courseId);
    }
    @Transactional
    public void updateCourse(Long courseId, Course course) {
        Course course1 = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "Course with id  " + courseId + " does not exist"
                ));
        if (course.getTitle() != null && course.getTitle().length() > 0 && !Objects.equals(course1.getTitle(), course.getTitle())){
            course1.setTitle(course.getTitle());
        }

        if (course.getSemester() != null && course.getSemester().length() > 0 && !Objects.equals(course1.getSemester(), course.getSemester())){
            course1.setSemester(course.getSemester());
        }

        if (course.getLocation() != null && course.getLocation().length() > 0 && !Objects.equals(course1.getLocation(), course.getLocation())){
            course1.setLocation(course.getLocation());
        }

        if (course.getInstructor() != null  && !Objects.equals(course1.getInstructor(), course.getInstructor())){
            course1.setInstructor(course.getInstructor());
        }

        courseRepository.save(course1);

    }
}
