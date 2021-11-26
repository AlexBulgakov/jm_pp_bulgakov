package ru.jm.bulgakov.jm_pp_bulgakov.service;


import ru.jm.bulgakov.jm_pp_bulgakov.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User findByName(String name);

    void addUser(User user);

    void updateUser(User updateUser);

    void removeUserById(Long id);

}
