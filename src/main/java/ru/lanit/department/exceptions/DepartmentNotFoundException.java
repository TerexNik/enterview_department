package ru.lanit.department.exceptions;

import javassist.NotFoundException;

public class DepartmentNotFoundException extends NotFoundException {

    public DepartmentNotFoundException() {
        super("Department not found");
    }

    public DepartmentNotFoundException(String msg) {
        super(msg);
    }

    public DepartmentNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
