package com.latincoder.sbconcurrent.component;

import com.google.common.collect.ImmutableList;
import com.latincoder.sbconcurrent.model.ImmutableStudent;
import com.latincoder.sbconcurrent.model.Student;
import com.latincoder.sbconcurrent.model.exceptions.StudentUnderAgeException;
import org.springframework.stereotype.Component;

import java.util.List;

/** This StudentAdministrationImpl is initial implementation for
 * the {@link StudentAdministration} component
 * however this class is not actually a "working implementation"
 * this is just used to showcase practices an teaching
 */
@Component
public class StudentAdministrationImpl implements StudentAdministration {

    public final static int MINIMUM_ACCEPTED_AGE = 21;

    public StudentAdministrationImpl() {}

    @Override
    public void signUp(Student student) {
        // students are allowed only if they are considered adults
        if (student.age() < MINIMUM_ACCEPTED_AGE) {
            throw StudentUnderAgeException.of(student);
        }
        // code here...
    }

    @Override
    public List<Student> findByAge(Integer age) {
        // implementation here ..
        // maybe call some DB or something
        throw new NullPointerException("No students found");
        /*return ImmutableList.of(generateStudent("Yoda", 999),
                generateStudent("Jorge", 29),
                generateStudent("Mufasa", 3));

         */
    }

    private Student generateStudent(String name, Integer age) {
        return ImmutableStudent.builder()
                .name(name)
                .age(age)
                .build();
    }

}
