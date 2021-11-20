package webapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.dao.UserDao;
import webapplication.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User updateUser, Long id) {
        userDao.updateUser(updateUser, id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
