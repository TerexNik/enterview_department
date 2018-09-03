package ru.lanit.department.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lanit.department.entities.Office;

public interface OfficeRepository extends CrudRepository<Office, String> {
}
