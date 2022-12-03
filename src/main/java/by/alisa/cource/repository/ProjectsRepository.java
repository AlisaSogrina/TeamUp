package by.alisa.cource.repository;

import by.alisa.cource.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository <Project, Long> {
    Project findByName(String projectName);

    Iterable<Project> findAllByTextContainsIgnoreCase(String value);
}
