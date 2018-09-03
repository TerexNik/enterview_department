package ru.lanit.department.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.lanit.department.RequestAnswer;
import ru.lanit.department.exceptions.DepartmentNotFoundException;
import ru.lanit.department.exceptions.OfficeNotFoundException;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public RequestAnswer defaultErrorHandler(Exception e) {
        return new RequestAnswer(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }

    @ExceptionHandler(value = DepartmentNotFoundException.class)
    public RequestAnswer DepartmentNotFoundErrorHandler(DepartmentNotFoundException e) {
        return new RequestAnswer(HttpStatus.CONFLICT, e.getMessage(), e);
    }

    @ExceptionHandler(value = OfficeNotFoundException.class)
    public RequestAnswer OfficeNotFoundErrorHandler(OfficeNotFoundException e) {
        return new RequestAnswer(HttpStatus.CONFLICT, e.getMessage(), e);
    }
}
