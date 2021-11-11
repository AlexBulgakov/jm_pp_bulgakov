package webapplication.service;

import webapplication.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void removeUserById(long id);

    void updateUser(User updateUser, long id);

    List<User> getAllUsers();

    User getUserById(long id);

}
