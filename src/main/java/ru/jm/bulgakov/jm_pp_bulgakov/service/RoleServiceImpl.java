package ru.jm.bulgakov.jm_pp_bulgakov.service;

import org.springframework.stereotype.Service;
import ru.jm.bulgakov.jm_pp_bulgakov.model.Role;
import ru.jm.bulgakov.jm_pp_bulgakov.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    public Role getRole(int id) {
        return roleRepository.findById(id);
    }


    @Override
    public Set<Role> createRolesSet(int index) {
        Set<Role> rolesSet = new HashSet<>();
        switch (index) {
            case 1:
                rolesSet.add(getRole(1));
                break;
            case 2:
                rolesSet.add(getRole(2));
                break;
            case 3:
                rolesSet.add(getRole(1));
                rolesSet.add(getRole(2));
                break;
            default:
        }
        return rolesSet;
    }
}
