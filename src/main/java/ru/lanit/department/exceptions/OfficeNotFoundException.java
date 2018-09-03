package ru.lanit.department.exceptions;

import javassist.NotFoundException;

public class OfficeNotFoundException extends NotFoundException {

    public OfficeNotFoundException() {
        super("Office not found");
    }

    public OfficeNotFoundException(String msg) {
        super(msg);
    }

    public OfficeNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
