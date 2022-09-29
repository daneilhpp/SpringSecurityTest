package com.carva.security.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController
{
    private static final List<Student> STUDENTS = Arrays.asList(
      new Student(1,"Joba Isreal"),
      new Student(2,"Gustav Ahr"),
      new Student(3,"Arthur Fleck")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId)
    {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst().orElseThrow(() -> new IllegalStateException("Student "+studentId+" does not exist"));
    }
}
