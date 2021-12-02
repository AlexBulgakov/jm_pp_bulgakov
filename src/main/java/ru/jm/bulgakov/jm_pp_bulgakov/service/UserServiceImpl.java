package ru.jm.bulgakov.jm_pp_bulgakov.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;
import ru.jm.bulgakov.jm_pp_bulgakov.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.jm.bulgakov.jm_pp_bulgakov.model.User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("GoodBye=)");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthorities());
        }

    }

    @Transactional
    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setRoles(roleService.createRolesSet(user.getRolesIndex()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User updateUser) {
        updateUser.setRoles(roleService.createRolesSet(updateUser.getRolesIndex()));
        userRepository.save(updateUser);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public boolean isAllowed(Long id, Principal principal) {
        return userRepository.isAllowed(id, principal);
    }

}
