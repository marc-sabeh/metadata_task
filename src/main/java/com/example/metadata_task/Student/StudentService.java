package com.example.metadata_task.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Object addNewStudent(Student student) {
        Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        return studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);
       if (!exists) {
           throw new IllegalStateException("student with id  " + studentId + " does not exist");
       }

       studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student student) {
        Student student1 = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id  " + studentId + " does not exist"
                ));

        if (student.getName() != null && student.getName().length() > 0 && !Objects.equals(student1.getName(), student.getName())){
            student1.setName(student.getName());
        }

        if (student.getPhone() != null && student.getPhone().length() > 0 && !Objects.equals(student1.getPhone(), student.getPhone())){
            student1.setPhone(student.getPhone());
        }

        if (student.getAddress() != null && student.getAddress().length() > 0 && !Objects.equals(student1.getAddress(), student.getAddress())){
            student1.setAddress(student.getAddress());
        }

        if (student.getEmail() != null && student.getEmail().length() > 0 && !Objects.equals(student1.getEmail(), student.getEmail())){
            Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student1.setEmail(student.getEmail());
        }

        studentRepository.save(student1);
    }
}
