package by.alisa.cource.service;

import by.alisa.cource.entity.Project;
import by.alisa.cource.entity.Role;
import by.alisa.cource.entity.User;
import by.alisa.cource.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository repository;

    @Autowired
    private UsersService usersService;

    public Iterable<Project> findAllThatContainsSubstr(String substr) {
        return repository.findAllByTextContainsIgnoreCase(substr);
    }

    public boolean saveProject(Project project) {
        Project projectFromDB = repository.findByProjectName(project.getProjectName());
        if (projectFromDB != null) {
            return false;
        }

        project.setProjectText(project.getProjectText());
        repository.save(project);

        return true;
    }

    public Project getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
