package com.latincoder.sbconcurrent.controllers;

import com.latincoder.sbconcurrent.component.StudentAdministration;
import com.latincoder.sbconcurrent.model.ImmutableStudent;
import com.latincoder.sbconcurrent.model.ImmutableStudentResponse;
import com.latincoder.sbconcurrent.model.Student;
import com.latincoder.sbconcurrent.model.StudentResponse;
import com.latincoder.sbconcurrent.model.exceptions.StudentUnderAgeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class Students {

    private StudentAdministration studentAdministrationComponent;

    /**
     * A constructor for Students rest controller, this autowires (dependency injection)
     * the {@link StudentAdministration} component, so we can explicitly define this dependency
     * and ease testing. This way when a new developer looks at this code, she or he will immediatly
     * recognize these dependencies
     *
     * @param studentAdministrationComponent
     */
    @Autowired
    public Students(StudentAdministration studentAdministrationComponent) {
        this.studentAdministrationComponent = studentAdministrationComponent;
    }

    // TODO: needs to be implemented
    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudentByName(@PathVariable String name) {
        return ImmutableStudent.builder()
                .age(10)
                .name(name)
                .build();
    }

    // TODO: needs to be unit tested properly
    @GetMapping(path = "searchByAge/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> findStudentsByAge(@PathVariable("age") Integer age) {
        return studentAdministrationComponent.findByAge(age);
    }

    @PostMapping(path = "/enroll")
    public StudentResponse enrollStudent(@RequestBody ImmutableStudent student) {
        this.studentAdministrationComponent.signUp(student);
        return ImmutableStudentResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .build();
    }
}
