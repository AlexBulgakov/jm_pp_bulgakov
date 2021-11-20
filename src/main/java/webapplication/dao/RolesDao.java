package webapplication.dao;

import webapplication.model.Role;

import java.util.List;

public interface RolesDao {
    void addRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(Long id);

}
