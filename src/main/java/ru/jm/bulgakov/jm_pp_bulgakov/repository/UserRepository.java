package ru.jm.bulgakov.jm_pp_bulgakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;

import java.security.Principal;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);


    default boolean isAllowed(Long id, Principal principal) {
        User user = findByName(principal.getName());
        return user.getId() == id || user.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().contains("ADMIN"));
    }
}
