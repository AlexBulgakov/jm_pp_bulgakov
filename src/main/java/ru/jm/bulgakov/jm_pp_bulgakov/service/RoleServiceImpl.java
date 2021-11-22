package ru.jm.bulgakov.jm_pp_bulgakov.service;

import org.springframework.stereotype.Service;
import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;
import ru.jm.bulgakov.jm_pp_bulgakov.repository.RoleRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = null;
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            role = roleOptional.get();
        }
        return role;
    }

}
