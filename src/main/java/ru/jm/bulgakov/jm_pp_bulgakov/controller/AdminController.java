package ru.jm.bulgakov.jm_pp_bulgakov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;
import ru.jm.bulgakov.jm_pp_bulgakov.service.UserService;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", service.getAllUsers());
        model.addAttribute("thisUser", service.findByName(principal.getName()));
        model.addAttribute("newUser", new User());
        return "/admin";
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal) {
        if (!service.isAllowed(id, principal)) {
            id = service.findByName(principal.getName()).getId();
            model.addAttribute(service.findByName(principal.getName()));
        } else {
            model.addAttribute(service.findById(id));
        }
        return "/user";
    }

    @GetMapping("/user")
    public void userInfo(Principal principal, Model model) {
        User user = service.findByName(principal.getName());
        model.addAttribute(user);
        show(user.getId(), model, principal);
    }

    @GetMapping("/admin/new_user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/new_user";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", service.findById(id));
        return "/admin/update_user";
    }

    @PatchMapping("/user/update")
    public String update(@ModelAttribute("user") User newUser) {
        service.updateUser(newUser);
        return "redirect:/admin";
    }

    @DeleteMapping("/user/delete")
    public String delete(@ModelAttribute("user") User user) {
        service.removeUserById(user.getId());
        return "redirect:/admin";
    }

}
