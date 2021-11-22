package ru.jm.bulgakov.jm_pp_bulgakov.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.jm.bulgakov.jm_pp_bulgakov.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
