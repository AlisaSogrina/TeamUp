package by.alisa.cource.controller;

import by.alisa.cource.entity.Project;
import by.alisa.cource.repository.UsersRepository;
import by.alisa.cource.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UsersController {
    @Autowired
    UsersRepository repository;

    @Autowired
    UsersService usersService;

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", repository.findAll());
        return "users";
    }

    @GetMapping("/{id}")
    String getUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getById(id));
        model.addAttribute("current_user", usersService.getCurrentAuthenticatedUser());
        return "user";
    }
}
