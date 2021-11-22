package ru.jm.bulgakov.jm_pp_bulgakov.service;


import ru.jm.bulgakov.jm_pp_bulgakov.model.User;

import java.util.List;

public interface UserService {

    User findByName(String name);

    void addUser(User user);

    void removeUserById(Long id);

    void updateUser(User updateUser);

    List<User> getAllUsers();

    User getUserById(Long id);

}
