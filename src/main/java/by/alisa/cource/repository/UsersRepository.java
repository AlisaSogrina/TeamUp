package by.alisa.cource.repository;

import by.alisa.cource.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
