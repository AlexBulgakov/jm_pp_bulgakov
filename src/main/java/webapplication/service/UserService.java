package webapplication.service;

import webapplication.model.User;

import java.util.List;

public interface UserService {
    User getUserByName(String name);

    void addUser(User user);

    void removeUserById(Long id);

    void updateUser(User updateUser, Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

}
