package ru.jm.bulgakov.jm_pp_bulgakov.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.jm.bulgakov.jm_pp_bulgakov.model.User user = userService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("GoodBye=)");
        } else {
            return new User(user.getName(), user.getPassword(), user.getAuthorities());
        }

    }
}
