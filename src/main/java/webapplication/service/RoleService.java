package webapplication.service;

import webapplication.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
}
