package ru.jm.bulgakov.jm_pp_bulgakov.service;

import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
}
