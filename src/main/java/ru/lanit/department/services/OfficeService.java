package ru.lanit.department.services;

import ru.lanit.department.entities.Office;
import ru.lanit.department.exceptions.OfficeNotFoundException;

import java.util.List;

public interface OfficeService {
    List<Office> getAll();
    Office getById(String id) throws OfficeNotFoundException;
    Office save(Office office);
    Office delete(String id) throws OfficeNotFoundException;
    Office updateById(Office updateOffice, String id) throws OfficeNotFoundException;

    void updateValue();
}
