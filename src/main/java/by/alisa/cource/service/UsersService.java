package by.alisa.cource.service;

import by.alisa.cource.entity.Project;
import by.alisa.cource.entity.Role;
import by.alisa.cource.entity.User;
import by.alisa.cource.repository.RolesRepository;
import by.alisa.cource.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    UsersRepository repository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getCurrentAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean saveUser(User user) {
        return saveUser(user, "ROLE_USER");
    }

    public boolean saveUser(User user, String roleName) {
        User userFromDB = repository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        Role usersRole = rolesRepository.findByName(roleName);
        if (usersRole == null) {
            return false;
        }

        user.setRoles(Collections.singleton(usersRole));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
        return true;
    }

    public User getById(Long id) {
        return repository.findById(id).get();
    }

//    public void saveProjectForUser(Long id, Project project) {
//        User userInDB = repository.findById(id).get();
//        Set<Project> projects = userInDB.getProjects();
//        projects.add(project);
//        userInDB.setProjects(projects);
////        repository.deleteById(id);
//        repository.save(userInDB);
//    }

//    public void saveProjectWannaTakePart(Long userId, Project project) {
//        User userInDB = repository.findById(userId).get();
//        Set<Project> projects = userInDB.getProjectsWannaTakePart();
//        projects.add(project);
//        userInDB.setProjectsWannaTakePart(projects);
////        repository.deleteById(userId);
//        repository.save(userInDB);
//    }
//
//    public void deleteProjectWannaTakePart(Long userId, Project project) {
//        User userInDB = repository.findById(userId).get();
//        Set<Project> projects = userInDB.getProjectsWannaTakePart();
//        projects.remove(project);
//        userInDB.setProjectsWannaTakePart(projects);
////        repository.deleteById(userId);
//        repository.save(userInDB);
//    }


}
