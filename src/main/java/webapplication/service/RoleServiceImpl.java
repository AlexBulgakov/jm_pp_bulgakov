package webapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.dao.RolesDao;
import webapplication.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RolesDao rolesDao;

    public RoleServiceImpl(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        rolesDao.addRole(role);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return rolesDao.getAllRoles();
    }

    @Transactional
    @Override
    public Role getRoleById(Long id) {
        return rolesDao.getRoleById(id);
    }


}
