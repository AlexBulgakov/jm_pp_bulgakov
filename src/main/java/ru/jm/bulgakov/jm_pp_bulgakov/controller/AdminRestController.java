package ru.jm.bulgakov.jm_pp_bulgakov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;
import ru.jm.bulgakov.jm_pp_bulgakov.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
public class AdminRestController {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getOneUser (@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(userService.findByName(user.getName()) , HttpStatus.OK);
    }

    @RequestMapping(value = "/users"
            , produces = MediaType.ALL_VALUE
            , method = RequestMethod.PUT)
    public ResponseEntity<User> changeUser (@RequestBody User user) {

        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> changeUser (@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
