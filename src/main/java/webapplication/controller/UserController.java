package webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapplication.model.User;
import webapplication.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService user;

    public UserController(UserService user) {
        this.user = user;
    }

    @GetMapping()
    public String listUsers(Model model) {
        model.addAttribute("users", user.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.getUserById(id));
        return "getuser";
    }

    @GetMapping("/add")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "add";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("newUser") User newUser) {
        user.addUser(newUser);
        return "users";
    }

    @GetMapping("/{id}/update")
    public String updateUserById(Model model, @PathVariable("id") long id) {
        model.addAttribute("updateUser", user.getUserById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User updatedUser, @PathVariable("id") long id) {
        user.updateUser(updatedUser, id);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}")
    public String remove(@PathVariable("id") long id) {
        user.removeUserById(id);
        return "redirect:/users";
    }

}
