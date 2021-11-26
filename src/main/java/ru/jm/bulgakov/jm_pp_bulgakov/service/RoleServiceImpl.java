package ru.jm.bulgakov.jm_pp_bulgakov.service;

import org.springframework.stereotype.Service;
import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;
import ru.jm.bulgakov.jm_pp_bulgakov.repository.RoleRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByName(String role) {
        return roleRepository.findByName(role);
    }

}
