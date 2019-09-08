package com.latincoder.sbconcurrent.model.exceptions;

import com.latincoder.sbconcurrent.model.Student;

public class StudentUnderAgeException extends RuntimeException {

    public static StudentUnderAgeException of(Student student) {
        return new StudentUnderAgeException(student);
    }

    public StudentUnderAgeException(String customMessage) {
        super(customMessage);
    }

    /**
     * Notice I have made this method 'private', this forces developer to use static constructor 'of' above
     * so code will be more 'idiomatic' when instantiating this object.
     *
     * The difference looks like the following:
     * StudentUnderAgeException exception = StudentUnderAgeException.of(someStudent);
     * vs
     * StudentUnderAgeException exception = new StudentUnderAgeException(someStudent);
     *
     * Note: in this particular case, this idiom falls heavily on 'debate', depending of coding styles.
     * @param student
     */
    private StudentUnderAgeException(Student student) {
        super(String.format("Student %s with age %d is considered under adult age.",
                student.name(),
                student.age()));
    }

}
