package com.example.metadata_task.StudentCourse;

import com.example.metadata_task.Course.Course;
import com.example.metadata_task.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/studentcourse")
public class StudentCourseController {
    private final StudentCourseService studentCourseService;

    @Autowired
    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping
    public List<StudentCourse> getStudentCourse(){
        return studentCourseService.getStudentCouses();
    }

    @PostMapping
    public void addStudentCourse(StudentCourse studentCourse , @RequestBody Param param){
        studentCourseService.addStudentCourse(studentCourse, param.getStudent(), param.getCourse());
    }

    @GetMapping(path = "/report")
    public List <NameTitle> getStudentCourseReport(@RequestParam(required = false) String fieldName, @RequestParam(required = false) String fieldValue,@RequestParam(required = false)  String Null){
       return studentCourseService.getStudentCoursesReport(fieldName, fieldValue, Null);
    }


}
class Param {

    private Student student;
    private Course course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
