package ru.jm.bulgakov.jm_pp_bulgakov.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;
import ru.jm.bulgakov.jm_pp_bulgakov.service.RoleService;
import ru.jm.bulgakov.jm_pp_bulgakov.service.UserService;


import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String start(){
        return "redirect:/login";
    }

    @GetMapping(value = "/user")
    public String viewUser(Model model, Principal principal) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        return "user";
    }

    @GetMapping("/admin")
    public String listUsers(Principal principal, Model model) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }


    @PostMapping(value = "/admin")
    public String addUser(@ModelAttribute User newUser, @RequestParam(value = "select-roles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : checkBoxRoles) {
            roleSet.add(roleService.findByName(role));
        }
        newUser.setRoles(roleSet);
        userService.addUser(newUser);

        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/{id}/edit")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "select-roles", required = false) String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : checkBoxRoles) {
            roleSet.add(roleService.findByName(role));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);

        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/{id}/delete")
    public String remove(@PathVariable("id") Long id) {
        userService.removeUserById(id);

        return "redirect:/admin";
    }

}
