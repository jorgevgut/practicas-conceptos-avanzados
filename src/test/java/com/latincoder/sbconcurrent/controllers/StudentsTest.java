package com.latincoder.sbconcurrent.controllers;


import com.latincoder.sbconcurrent.component.StudentAdministration;
import com.latincoder.sbconcurrent.component.StudentAdministrationImpl;
import com.latincoder.sbconcurrent.model.ImmutableStudent;
import com.latincoder.sbconcurrent.model.Student;
import com.latincoder.sbconcurrent.model.StudentResponse;
import com.latincoder.sbconcurrent.model.exceptions.StudentUnderAgeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;


@RunWith(MockitoJUnitRunner.class)
public class StudentsTest {
    // The intended class to be tested
    private Students studentsController;

    // Mocks
    @Mock
    private StudentAdministration studentAdministrationComponent;

    // Test data and setup
    private static Student regularStudent = ImmutableStudent.builder()
                                            .age(StudentAdministrationImpl.MINIMUM_ACCEPTED_AGE)
                                            .name("John Doe")
                                            .build();
    private static Student underAgeStudent = ImmutableStudent.builder()
            .age(StudentAdministrationImpl.MINIMUM_ACCEPTED_AGE - 1)
            .name("John Doe")
            .build();

    @Before
    public  void setup() {
        studentsController = new Students(studentAdministrationComponent);
    }

    // Successfull/Hapycase tests
    @Test
    public void testThatStudentCanBeEnrolled() {
        StudentResponse studentResponse = studentsController
                .enrollStudent((ImmutableStudent)regularStudent);
        assertNotNull(studentResponse);
        assertEquals("OK", studentResponse.status());
    }

    // Tests for expected exceptions
    @Test(expected = StudentUnderAgeException.class)
    public void testThatUnderAgeStudentCannotBeEnrolled() {
        doThrow(StudentUnderAgeException.of(underAgeStudent))
                .when(studentAdministrationComponent)
                .signUp(underAgeStudent);

        studentsController.enrollStudent((ImmutableStudent)underAgeStudent);
    }
}
