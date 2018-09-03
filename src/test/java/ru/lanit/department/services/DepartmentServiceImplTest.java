package ru.lanit.department.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lanit.department.entities.Department;
import ru.lanit.department.entities.Office;
import ru.lanit.department.exceptions.DepartmentNotFoundException;
import ru.lanit.department.repository.DepartmentRepository;
import ru.lanit.department.repository.OfficeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl service;

    @Mock
    private DepartmentRepository repository;

    @Before
    public void setUp() throws DepartmentNotFoundException {
        // Entities section
        HashSet<Office> offices = new HashSet<>();
        Office office = new Office();
        office.setId("1");
        office.setCity("City");
        office.setAddress("Address");
        office.setCategory("A");
        office.setValue(100.0);
        office.setPropertyType(0L);
        offices.add(office);

        Department department = new Department();
        department.setName("Test");
        department.setId("1");
        department.setOffices(offices);

        //When section
        when(repository.findById("1"))
                .thenReturn(Optional.of(department));
        when(repository.save(department))
                .thenReturn(department);
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void TestGetByIdThrowsException() throws DepartmentNotFoundException {
        service.getById("qwe");
    }

    @Test
    public void TestGetById() throws Exception {
        // Testing
        Department department = service.getById("1");

        // Verify
        verify(repository).findById("1");
        assertEquals("Test", department.getName());
        assertEquals("1", department.getId());
    }

    @Test
    public void TestDepartmentSave() {
        // Prepare
        HashSet<Office> offices = new HashSet<>();
        Office office = new Office();
        office.setId("1");
        office.setCity("City");
        office.setAddress("Address");
        office.setCategory("A");
        office.setValue(100.0);
        office.setPropertyType(0L);
        offices.add(office);

        Department department = new Department();
        department.setName("Test");
        department.setId("1");
        department.setOffices(offices);

        // Testing
        Department result = service.save(department);

        // Verify
        verify(repository).save(department);

        assertEquals("Test",    result.getName());
        assertEquals("1",       result.getId());
        assertEquals("Address", result.getOffices().iterator().next().getAddress());
        assertEquals("City",    result.getOffices().iterator().next().getCity());
        assertEquals("A",       result.getOffices().iterator().next().getCategory());
        assertEquals("1",       result.getOffices().iterator().next().getId());
    }

    @Test
    public void TestUpdateById() throws DepartmentNotFoundException {
        // Prepare
        Department departmentMock = new Department();
        departmentMock.setId("1");
        Set<Office> officesMock = new HashSet<>();
        officesMock.add(new Office());
        departmentMock.setOffices(officesMock);

        HashSet<Office> offices = new HashSet<>();
        Office office = new Office();
        office.setId("1");
        office.setCity("City");
        office.setAddress("Address");
        office.setCategory("A");
        office.setValue(100.0);
        office.setPropertyType(0L);
        offices.add(office);

        Department department = new Department();
        department.setName("Test");
        department.setId("1");
        department.setOffices(offices);

        // When
        when(repository.save(departmentMock)).thenReturn(department);

        // Testing
        Department result = service.updateById(departmentMock, "1");

        // Validate
        verify(repository).save(departmentMock);

        assertEquals("Test",    result.getName());
        assertEquals("1",       result.getId());
        assertEquals("Address", result.getOffices().iterator().next().getAddress());
        assertEquals("City",    result.getOffices().iterator().next().getCity());
        assertEquals("A",       result.getOffices().iterator().next().getCategory());
        assertEquals("1",       result.getOffices().iterator().next().getId());
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void TestUpdateByIdException() throws DepartmentNotFoundException {
        service.updateById(new Department(), "123");
    }
}