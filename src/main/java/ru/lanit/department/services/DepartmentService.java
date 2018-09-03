package ru.lanit.department.services;

import org.springframework.stereotype.Service;
import ru.lanit.department.RequestAnswer;
import ru.lanit.department.entities.Department;
import ru.lanit.department.exceptions.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    Department save(Department department);
    List<Department> getAll();
    Department getById(String id) throws DepartmentNotFoundException;
    Department delete(String id) throws DepartmentNotFoundException;
    Department updateById(Department updateDepartment, String id) throws DepartmentNotFoundException;
}
