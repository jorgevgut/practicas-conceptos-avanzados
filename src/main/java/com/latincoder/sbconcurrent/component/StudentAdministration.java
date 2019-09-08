package com.latincoder.sbconcurrent.component;

import com.latincoder.sbconcurrent.model.Student;
import com.latincoder.sbconcurrent.model.exceptions.StudentUnderAgeException;

import java.util.List;

public interface StudentAdministration {
    public void signUp(Student student);
    public List<Student> findByAge(Integer age);
}
