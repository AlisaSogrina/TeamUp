package by.alisa.cource.service;

import by.alisa.cource.entity.Project;
import by.alisa.cource.entity.User;
import by.alisa.cource.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository repository;

//    @Autowired
//    private UsersRepository usersRepository;


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

    public void saveUserWannaTakePart(Long projectId, User currentAuthenticatedUser) {
        Project projectInDB = repository.findById(projectId).get();
        Set<User> usersForProjectInDB = projectInDB.getUsersWannaTakePart();
        usersForProjectInDB.add(currentAuthenticatedUser);
        projectInDB.setUsersWannaTakePart(usersForProjectInDB);
        repository.save(projectInDB);
    }

    public void deleteUserWannaTakePart(Long projectId, User currentAuthenticatedUser) {
        Project projectInDB = repository.findById(projectId).get();
        Set<User> usersForProjectInDB = projectInDB.getUsersWannaTakePart();
        usersForProjectInDB.remove(currentAuthenticatedUser);
        projectInDB.setUsersWannaTakePart(usersForProjectInDB);
        repository.save(projectInDB);
    }

    public void changeById(Long id, Project project) {
        Project projectInDB = repository.findById(id).get();
        projectInDB.setName(project.getName());
        projectInDB.setText(project.getText());
        repository.save(projectInDB);
    }

    public void saveUserTakePart(Long id, User user) {
        Project projectInDB = repository.findById(id).get();
        Set<User> usersForProjectInDB = projectInDB.getUsersTakePart();
        usersForProjectInDB.add(user);
        projectInDB.setUsersTakePart(usersForProjectInDB);
        repository.save(projectInDB);
    }

    public void deleteUserTakePart(Long id, User user) {
        Project projectInDB = repository.findById(id).get();
        Set<User> usersForProjectInDB = projectInDB.getUsersTakePart();
        usersForProjectInDB.remove(user);
        projectInDB.setUsersTakePart(usersForProjectInDB);
        repository.save(projectInDB);
    }
}
