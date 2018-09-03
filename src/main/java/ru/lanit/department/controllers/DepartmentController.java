package ru.lanit.department.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lanit.department.RequestAnswer;
import ru.lanit.department.entities.Department;
import ru.lanit.department.exceptions.DepartmentNotFoundException;
import ru.lanit.department.services.DepartmentServiceImpl;

@RestController
@RequestMapping("/department/crud")
public class DepartmentController {

    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentServiceImpl service;

    @Autowired
    public DepartmentController(DepartmentServiceImpl service) {
        this.service = service;
    }


    @PostMapping(value = "/create")
    RequestAnswer newDepartment(@RequestBody Department department) {
        log.trace("Create request evaluate");
        return new RequestAnswer("Create complete", service.save(department));
    }

    @RequestMapping("/all")
    RequestAnswer getAll() {
        log.trace("Get All request evaluate");
        return new RequestAnswer("Get all departments complete", service.getAll());
    }

    @RequestMapping("/get/id/{id}")
    RequestAnswer getById(@PathVariable String id) throws DepartmentNotFoundException {
        log.trace("Get Department by id request evaluate");
        return new RequestAnswer("Get Department by id complete", service.getById(id));
    }

    @RequestMapping("/update/id/{id}")
    RequestAnswer updateDepartmentById(@RequestBody Department updateDepartment, @PathVariable String id) throws DepartmentNotFoundException {
        log.trace("Update Department By Id request evaluate");
        return new RequestAnswer("Update Department by id complete",
                service.updateById(updateDepartment, id));
    }

    @RequestMapping("/delete/{id}")
    RequestAnswer deleteDepartment(@PathVariable String id) throws DepartmentNotFoundException {
        log.trace("Delete Department request evaluate");
        return new RequestAnswer("Delete Department complete", service.delete(id));
    }
}
