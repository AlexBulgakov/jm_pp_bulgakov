package ru.jm.bulgakov.jm_pp_bulgakov.service;


import ru.jm.bulgakov.jm_pp_bulgakov.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User findByName(String name);

    User findById(Long id);

    void addUser(User user);

    void updateUser(User updateUser);

    void removeUserById(Long id);

    boolean isAllowed(Long id, Principal principal);

}
