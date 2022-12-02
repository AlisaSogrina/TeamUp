package by.alisa.cource.repository;

import by.alisa.cource.entity.Project;
import by.alisa.cource.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository <Project, Long> {
    Project findByProjectName(String projectName);

    Iterable<Project> findAllByTextContainsIgnoreCase(String value);
}
