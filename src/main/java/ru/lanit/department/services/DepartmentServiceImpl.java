package ru.lanit.department.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.department.entities.Department;
import ru.lanit.department.exceptions.DepartmentNotFoundException;
import ru.lanit.department.repository.DepartmentRepository;
import ru.lanit.department.repository.OfficeRepository;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }


    @Override
    public Department save(Department department) {
        log.trace("Save Department");
        return repository.save(department);
    }

    @Override
    public List<Department> getAll() {
        log.trace("Get All service evaluate");
        return (List<Department>) repository.findAll();
    }

    @Override
    public Department getById(String id) throws DepartmentNotFoundException {
        log.trace("Get Department by id service evaluate");
        return repository.findById(id).orElseThrow(() ->
                new DepartmentNotFoundException("Department can not found by Id: " + id));
    }

    @Override
    public Department delete(String id) throws DepartmentNotFoundException {
        log.trace("Delete Department service evaluate");
        Department department = repository.findById(id).orElseThrow(() ->
                new DepartmentNotFoundException("Department can not found by Id: " + id));
        repository.deleteById(id);
        return department;
    }

    @Override
    public Department updateById(Department updateDepartment, String id) throws DepartmentNotFoundException {
        log.trace("Update Department By Id service evaluate");
        return repository.findById(id)
                .map(department -> {
                    department.setName(updateDepartment.getName());
                    department.setOffices(updateDepartment.getOffices());
                    return repository.save(department);
                })
                .orElseThrow(() -> new DepartmentNotFoundException("Department can not found by Id: " + id));
    }
}
