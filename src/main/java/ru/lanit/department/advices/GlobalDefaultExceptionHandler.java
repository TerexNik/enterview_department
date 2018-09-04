package ru.lanit.department.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.lanit.department.RequestAnswer;
import ru.lanit.department.exceptions.DepartmentNotFoundException;
import ru.lanit.department.exceptions.OfficeNotFoundException;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = DepartmentNotFoundException.class)
    @ResponseBody
    public RequestAnswer DepartmentNotFoundErrorHandler(DepartmentNotFoundException e) {
        return new RequestAnswer(HttpStatus.CONFLICT, e.getMessage(), e.getCause());
    }

    @ExceptionHandler(value = OfficeNotFoundException.class)
    @ResponseBody
    public RequestAnswer OfficeNotFoundErrorHandler(OfficeNotFoundException e) {
        return new RequestAnswer(HttpStatus.CONFLICT, e.getMessage(), e.getCause());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RequestAnswer defaultErrorHandler(Exception e) {
        return new RequestAnswer(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
    }
}
