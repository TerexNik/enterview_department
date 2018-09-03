package ru.lanit.department.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lanit.department.entities.Department;
import ru.lanit.department.exceptions.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, String> {
}
