package com.example.metadata_task.Course;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private long id;

    private String title;


    private String semester;
    private String location;
    private String instructor;
    private LocalDate start_date;

    public Course() {
    }

    public Course(long id, String title, String semester, String location, String instructor, LocalDate start_date) {
        this.id = id;
        this.title = title;
        this.semester = semester;
        this.location = location;
        this.instructor = instructor;
        this.start_date = start_date;
    }

    public Course(String title, String semester, String location, String instructor, LocalDate start_date) {
        this.title = title;
        this.semester = semester;
        this.location = location;
        this.instructor = instructor;
        this.start_date = start_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", semester='" + semester + '\'' +
                ", location='" + location + '\'' +
                ", instructor='" + instructor + '\'' +
                ", start_date=" + start_date +
                '}';
    }
}
