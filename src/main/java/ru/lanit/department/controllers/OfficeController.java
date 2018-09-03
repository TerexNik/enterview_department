package ru.lanit.department.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lanit.department.RequestAnswer;
import ru.lanit.department.entities.Office;
import ru.lanit.department.exceptions.OfficeNotFoundException;
import ru.lanit.department.services.OfficeServiceImpl;

@RestController
@RequestMapping("/office/crud")
public class OfficeController {

    private final Logger log = LoggerFactory.getLogger(OfficeController.class);
    private final OfficeServiceImpl service;

    @Autowired
    public OfficeController(OfficeServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(value = "/create", consumes = "application/json", method = RequestMethod.POST)
    RequestAnswer newOffice(@RequestBody Office office) {
        log.trace("Create request evaluate");
        return new RequestAnswer("Create complete", service.save(office));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    RequestAnswer getAll() {
        log.trace("Get All request evaluate");
        return new RequestAnswer("Get all offices complete", service.getAll());
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    RequestAnswer getById(@PathVariable String id) throws OfficeNotFoundException {
        log.trace("Get Office by id evaluate");
        return new RequestAnswer("Get Office by id complete", service.getById(id));
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT, consumes = "application/json")
    RequestAnswer updateOfficeById(@RequestBody Office updateOffice, @PathVariable String id)
            throws OfficeNotFoundException {
        log.trace("Update Office By Id evaluate");
        return new RequestAnswer("Update Office by id complete", service.getById(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    RequestAnswer deleteOffice(@PathVariable String id) throws OfficeNotFoundException {
        log.trace("Delete Office evaluate");
        return new RequestAnswer("Delete Office complete", service.delete(id));
    }
}
