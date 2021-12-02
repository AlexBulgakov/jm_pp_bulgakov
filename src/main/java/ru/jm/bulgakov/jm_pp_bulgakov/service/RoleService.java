package ru.jm.bulgakov.jm_pp_bulgakov.service;

import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    public Role getRole(int id);
    Set<Role> createRolesSet(int index);
}
