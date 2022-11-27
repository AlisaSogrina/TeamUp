package by.alisa.cource.controller;

import by.alisa.cource.entity.User;
import by.alisa.cource.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StartPageController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "start_page";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {  // @Valid checks for limitations, like that provided from @Size, and puts to bindingResult
            return "start_page";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Passwords are not the same");
            return "start_page";
        }
        if (!usersService.saveUser(userForm)){
            model.addAttribute("usernameError", "User with such a name already exists");
            return "start_page";
        }
        return "redirect:login";
    }
}
