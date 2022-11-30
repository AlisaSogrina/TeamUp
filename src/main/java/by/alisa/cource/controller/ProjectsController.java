package by.alisa.cource.controller;

import by.alisa.cource.entity.Project;
import by.alisa.cource.entity.User;
import by.alisa.cource.service.ProjectsService;
import by.alisa.cource.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/projects")
@Controller
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    @Autowired
    UsersService usersService;

    @GetMapping
    public String projects(Model model) {
        model.addAttribute("projectForm", new Project());
        model.addAttribute("userID", usersService.getCurrentAuthenticatedUser().getId());
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
}
