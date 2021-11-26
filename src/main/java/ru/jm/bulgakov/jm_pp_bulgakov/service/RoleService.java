package ru.jm.bulgakov.jm_pp_bulgakov.service;

import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findByName(String role);
}
