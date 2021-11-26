package ru.jm.bulgakov.jm_pp_bulgakov.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;
import ru.jm.bulgakov.jm_pp_bulgakov.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.jm.bulgakov.jm_pp_bulgakov.model.User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("GoodBye=)");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthorities());
        }

    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User updateUser) {
        userRepository.save(updateUser);
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

}
