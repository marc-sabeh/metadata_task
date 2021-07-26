package com.example.metadata_task.StudentCourse;

import javax.persistence.*;

@Entity
@Table
public class StudentCourse {
    @Id
    @SequenceGenerator(
            name = "student_course_sequence",
            sequenceName = "student_course_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_course_sequence"
    )
    private long id;
    private long studentId;
    private long courseId;

    public StudentCourse() {
    }

    public StudentCourse(long id, long studentId, long courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public StudentCourse(long studentId, long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
