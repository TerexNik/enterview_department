package ru.lanit.department.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lanit.department.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {
}
