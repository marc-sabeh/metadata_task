package com.example.metadata_task.Course;

import com.example.metadata_task.Student.Student;
import com.example.metadata_task.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourse(){
        return courseService.getCouses();
    }

    @PostMapping
    public void createCourse(@RequestBody Course course){
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(@PathVariable("courseId") Long courseId, @RequestBody Course course){
        courseService.updateCourse(courseId, course);
    }
}
