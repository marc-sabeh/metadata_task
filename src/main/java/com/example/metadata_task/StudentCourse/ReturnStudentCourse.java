package com.example.metadata_task.StudentCourse;

public class ReturnStudentCourse {
    String name;
    String title;
    String email;
    String instructor;

//    public ReturnStudentCourse(String name, String title, String email, String instructor) {
//        this.name = name;
//        this.title = title;
//        this.email = email;
//        this.instructor = instructor;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getInstructor() {
//        return instructor;
//    }
//
//    public void setInstructor(String instructor) {
//        this.instructor = instructor;
//    }


    @Override
    public String toString() {
        return "ReturnStudentCourse{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}
