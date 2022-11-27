package by.alisa.cource.repository;

import by.alisa.cource.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}

