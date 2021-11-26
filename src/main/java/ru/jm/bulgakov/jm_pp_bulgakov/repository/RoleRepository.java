package ru.jm.bulgakov.jm_pp_bulgakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
