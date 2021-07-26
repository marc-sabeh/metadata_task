package com.example.metadata_task.StudentCourse;

import com.example.metadata_task.Course.Course;
import com.example.metadata_task.Course.CourseRepository;
import com.example.metadata_task.Student.Student;
import com.example.metadata_task.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public StudentCourseService(StudentCourseRepository studentCourseRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudentCourse> getStudentCouses(){
        return studentCourseRepository.findAll();
    }

    @Transactional
    public void addStudentCourse(StudentCourse studentCourse, Student student, Course course) {

        Optional<Student> student1 = studentRepository.findStudentByName(student.getName());
        Optional<Course> course1 = courseRepository.findCourseByTitle(course.getTitle());
        int StudentsInCourse = studentCourseRepository.countStudentsInCourse(course1.get().getId());
        int CoursesOfStudent = studentCourseRepository.countCoursesOfStudent(student1.get().getId());
        System.out.println(StudentsInCourse);
        System.out.println(CoursesOfStudent);

        //Make sure a Course has max 50 students and this student who is registering has less then 5 courses
        if(StudentsInCourse <= 50 && CoursesOfStudent <= 5) {
            studentCourse.setStudentId(student1.get().getId());
            studentCourse.setCourseId(course1.get().getId());
            studentCourseRepository.save(studentCourse);
            System.out.println(studentCourse);
        }
    }


    public List <NameTitle> getStudentCoursesReport(String fieldName, String fieldValue, String Null) {
        if (fieldName != null && fieldName.length() > 0 && fieldValue != null && fieldValue.length() > 0)
        {
            if(fieldName.equals("studentName")) {
                return studentCourseRepository.getReportFilterByStudent(fieldValue);
            }
            else  if(fieldName.equals("courseTitle")) {
                return studentCourseRepository.getReportFilterByCourse(fieldValue);
            }
        }
        else if (Null != null && Null.length() > 0) {
            if (Null.equals("student")) {
                return studentCourseRepository.getReportFilterByNullStudent();
            }
            else  if (Null.equals("course")) {
                return studentCourseRepository.getReportFilterByNullCourse();
            }
        }
            return  studentCourseRepository.getReport();
    }
}
