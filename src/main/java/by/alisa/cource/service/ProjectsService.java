package by.alisa.cource.service;

import by.alisa.cource.entity.Project;
import by.alisa.cource.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository repository;


//    public Iterable<Project> findAllThatContainsSubstr(String substr) {
//        return repository.findAllByTextContainsIgnoreCase(substr);
//    }

    public boolean saveProject(Project project) {
        Project projectFromDB = repository.findByName(project.getName());
        if (projectFromDB != null) {
            return false;
        }
        repository.save(project);
        return true;
    }

    public Project getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
