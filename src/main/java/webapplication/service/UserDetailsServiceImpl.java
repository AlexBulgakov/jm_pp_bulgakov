package webapplication.service;

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

    @Override//dct jhr all good here
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        webapplication.model.User user = userService.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("GoodBye=)");
        } else {
            return new User(user.getName(), user.getPassword(), user.getAuthorities());
        }

    }
}
