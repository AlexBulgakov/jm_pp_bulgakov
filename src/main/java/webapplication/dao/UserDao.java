package webapplication.dao;

import webapplication.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void removeUserById(long id);

    void updateUser(User updateUser, long id);

    List<User> getAllUsers();

    User getUserById(long id);

}
