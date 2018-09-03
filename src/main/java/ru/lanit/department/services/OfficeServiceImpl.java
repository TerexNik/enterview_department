package ru.lanit.department.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.department.entities.Office;
import ru.lanit.department.exceptions.OfficeNotFoundException;
import ru.lanit.department.repository.DepartmentRepository;
import ru.lanit.department.repository.OfficeRepository;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final OfficeRepository repository;

    @Autowired
    public OfficeServiceImpl(OfficeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Office> getAll() {
        return (List<Office>) repository.findAll();
    }

    @Override
    public Office getById(String id) throws OfficeNotFoundException {
        log.trace("Get Office by id service evaluate");
        return repository.findById(id).orElseThrow(() ->
                new OfficeNotFoundException("Office can not found by id: " + id));
    }

    @Override
    public Office save(Office office) {
        log.trace("Save Office");
        return repository.save(office);
    }

    @Override
    public Office delete(String id) throws OfficeNotFoundException {
        log.trace("Delete Office service evaluate");
        Office office = repository.findById(id).orElseThrow(() ->
                new OfficeNotFoundException("Office can not found by id: " + id));
        repository.deleteById(id);
        return office;
    }

    @Override
    public Office updateById(Office updateOffice, String id) throws OfficeNotFoundException {
        log.trace("Update Office By Id service evaluate");
        return repository.findById(id)
                .map(office -> {
                    office.setCity(updateOffice.getCity());
                    office.setAddress(updateOffice.getAddress());
                    office.setCategory(updateOffice.getCategory());
                    office.setValue(updateOffice.getValue());
                    office.setPropertyType(updateOffice.getPropertyType());
                    office.setDepartments(updateOffice.getDepartments());
                    return repository.save(office);
                })
                .orElseThrow(() -> new OfficeNotFoundException("Office can not found by id: " + id));

    }

    @Override
    public void updateValue() {
        repository.findAll().forEach(office -> {
            office.setValue(office.getValue() - office.getValue()*0.01);
            repository.save(office);
        });
    }
}
