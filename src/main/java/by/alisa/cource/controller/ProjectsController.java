package by.alisa.cource.controller;

import by.alisa.cource.entity.Project;
import by.alisa.cource.repository.ProjectsRepository;
import by.alisa.cource.service.ProjectsService;
import by.alisa.cource.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/projects")
@Controller
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    @Autowired
    ProjectsRepository projectsRepository;
    @Autowired
    UsersService usersService;

    @GetMapping
    public String projects(Model model) {
        model.addAttribute("projectForm", new Project());
        model.addAttribute("userID", usersService.getCurrentAuthenticatedUser().getId());
        model.addAttribute("projects", projectsRepository.findAll());
        return "projects";
    }

    @PostMapping
    public String addProject(@ModelAttribute("projectForm") @Valid Project projectForm,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {  // @Valid checks for limitations, like that provided from @Size, and puts to bindingResult
            return "projects";
        }
        projectForm.setUser(usersService.getCurrentAuthenticatedUser());
        if (!projectsService.saveProject(projectForm)){
            model.addAttribute("nameError", "Project with such a name already exists");
            return "projects";
        }
        return "redirect:projects";
    }

    @GetMapping("/{id}")
    String getProject(Model model, @PathVariable Long id) {
        model.addAttribute("project", projectsService.getById(id));
        model.addAttribute("current_user", usersService.getCurrentAuthenticatedUser());
        return "project";
    }

    @PostMapping("/{id}/delete")
    String deleteProject(@PathVariable Long id) {
        projectsService.deleteById(id);
        return "redirect:/projects";
    }

    @PostMapping("/{id}/takePart")
    String takePartProject(@PathVariable Long id) {
        projectsService.saveUserWannaTakePart(id, usersService.getCurrentAuthenticatedUser());
        return "redirect:/projects/{id}";
    }

    @PostMapping("/{id}/deleteTakePart")
    String deleteTakePartProject(@PathVariable Long id) {
        projectsService.deleteUserWannaTakePart(id, usersService.getCurrentAuthenticatedUser());
        return "redirect:/projects/{id}";
    }
}
