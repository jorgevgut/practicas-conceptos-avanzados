package com.latincoder.sbconcurrent.model.exceptions;

import org.springframework.http.HttpStatus;

public enum SupportedException {
    STUDENT_UNDER_AGE(StudentUnderAgeException.class, HttpStatus.UNPROCESSABLE_ENTITY),
    STUDENT_NOT_FOUND(NullPointerException.class, HttpStatus.NOT_FOUND);
    // By creating Class<? extends Throwable> enforces type on SupportedExceptions
    // Uncomment the following code line to see how the code won't compile, since object is not throwable
    //INVALID_SUPPORTED_EXCEPTION(Object.class, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);


    /**
     * in Spring, it depends on the application's use case how exceptions should be handle to return a response
     * which is why as developers we are responsible of this.
     * This approach to exception handling, forces all supported exceptions to share similar properties, even if
     * this is a very simple example, we can defined supported exceptions to provide an {@link HttpStatus}, thus
     * enabling future modifications to forcefully provide this important detail.
     */
    private  Class<? extends Throwable> exceptionClass;
    private HttpStatus status;

    /**
     * Notice how this constructor uses generics to accept any object that can be thrown as an exception.
     * This is essential so that new SupportedExceptions are in fact {@link Throwable} objects
     * And that can be catched by the exception handler.
     * For more details see {@link com.latincoder.sbconcurrent.configuration.RestExceptionInterceptor}
     *
     * @param exception
     * @param status
     */
    private SupportedException(Class<? extends Throwable> exception, HttpStatus status) {
        this.exceptionClass = exception;
        this.status = status;
    }

    public Class<? extends Throwable> getExceptionClass() {
        return this.exceptionClass;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
