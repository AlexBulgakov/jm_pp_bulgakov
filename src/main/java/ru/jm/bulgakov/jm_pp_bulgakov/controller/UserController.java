package ru.jm.bulgakov.jm_pp_bulgakov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;
import ru.jm.bulgakov.jm_pp_bulgakov.service.RoleService;
import ru.jm.bulgakov.jm_pp_bulgakov.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/user")
    public String userPage(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("user", userService.getUserByName(auth.getName()));

        return "user";
    }

    @GetMapping("/admin")
    public String listUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/{name}")
    public String findByName(@PathVariable("name") String name, Model model) {
        model.addAttribute("user", userService.findByName(name));

        return "getuser";
    }

    @GetMapping("/admin/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/getuser";
    }

    @GetMapping("/admin/add")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());

        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("newUser") User newUser) {
        userService.addUser(newUser);

        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/update")
    public String updateUserById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("updateUser", userService.getUserById(id));

        return "/update";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User updatedUser) {
        userService.updateUser(updatedUser);

        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/{id}")
    public String remove(@PathVariable("id") Long id) {
        userService.removeUserById(id);

        return "redirect:/admin";
    }

}
