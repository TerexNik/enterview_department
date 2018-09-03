package ru.lanit.department.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lanit.department.entities.Department;
import ru.lanit.department.entities.Office;
import ru.lanit.department.exceptions.DepartmentNotFoundException;
import ru.lanit.department.exceptions.OfficeNotFoundException;
import ru.lanit.department.repository.DepartmentRepository;
import ru.lanit.department.repository.OfficeRepository;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OfficeServiceImplTest {

    @InjectMocks
    private OfficeServiceImpl service;

    @Mock
    private OfficeRepository repository;
    @Mock
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() {
        // Entities section
        Set<Department> departments = new HashSet<>();
        Department department = new Department();
        department.setName("Test");
        department.setId("1");
        departments.add(department);

        Office office = new Office();
        office.setValue(100.0);
        office.setId("1");
        office.setCategory("A");
        office.setCity("City");
        office.setAddress("Address");
        office.setDepartments(departments);

        List<Office> list = new ArrayList<>();
        list.add(office);

        // When section
        when(repository.findAll())
                .thenReturn(list);
        when(repository.findById("1"))
                .thenReturn(Optional.of(office));
    }


    @Test
    public void TestThatOfficeValuesUpdateByOnePercent() {
        // Testing
        service.updateValue();

        //Verify
        assertEquals(new Double(99.0), repository.findAll().iterator().next().getValue());
    }

    @Test(expected = OfficeNotFoundException.class)
    public void TestGetByIdThrowsException() throws OfficeNotFoundException {
        service.getById("qwe");
    }

    @Test
    public void TestGetById() throws Exception {
        // Testing
        Office office = service.getById("1");

        // Verify
        verify(repository).findById("1");
        assertEquals("Address", office.getAddress());
        assertEquals("City", office.getCity());
        assertEquals("A", office.getCategory());
        assertEquals("1", office.getId());
        assertEquals("1", office.getDepartments().iterator().next().getId());
        assertEquals("Test", office.getDepartments().iterator().next().getName());
    }

    @Test
    public void TestOfficesSave() {
        // Prepare
        HashSet<Department> departments = new HashSet<>();
        Department department = new Department();
        department.setName("Test");
        department.setId("1");
        departments.add(department);

        Office office = new Office();
        office.setId("1");
        office.setCity("City");
        office.setAddress("Address");
        office.setCategory("A");
        office.setValue(100.0);
        office.setPropertyType(0L);
        office.setDepartments(departments);

        // Testing
        service.save(office);

        // Verify
        verify(repository).save(office);
        verify(departmentRepository).saveAll(departments);

    }

    @Test
    public void TestUpdateById() throws OfficeNotFoundException {
        // Prepare
        Office mock = new Office();
        mock.setId("1");
        Set<Department> departmentsMock = new HashSet<>();
        departmentsMock.add(new Department());
        mock.setDepartments(departmentsMock);

        HashSet<Department> departments = new HashSet<>();
        Department department = new Department();
        department.setName("Test");
        department.setId("1");
        departments.add(department);

        Office office = new Office();
        office.setId("1");
        office.setCity("City");
        office.setAddress("Address");
        office.setCategory("A");
        office.setValue(100.0);
        office.setPropertyType(0L);
        office.setDepartments(departments);

        // When
        when(repository.save(mock)).thenReturn(office);
        when(departmentRepository.saveAll(departmentsMock)).thenReturn(departments);

        // Testing
        Office result = service.updateById(mock, "1");

        // Validate
        verify(repository).save(mock);
        verify(departmentRepository).saveAll(departmentsMock);

        assertEquals("Address", result.getAddress());
        assertEquals("City",    result.getCity());
        assertEquals("A",       result.getCategory());
        assertEquals("1",       result.getId());
        assertEquals("1",       result.getDepartments().iterator().next().getId());
        assertEquals("Test",    result.getDepartments().iterator().next().getName());
    }

    @Test(expected = OfficeNotFoundException.class)
    public void TestUpdateByIdException() throws OfficeNotFoundException {
        service.updateById(new Office(), "123");
    }
}