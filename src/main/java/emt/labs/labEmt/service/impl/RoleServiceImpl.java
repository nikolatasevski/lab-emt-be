package emt.labs.labEmt.service.impl;

import emt.labs.labEmt.model.Role;
import emt.labs.labEmt.model.dto.AddRoleDto;
import emt.labs.labEmt.repository.RoleRepository;
import emt.labs.labEmt.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role find(Long id) {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Role with id: %s not found", String.valueOf(id))));
    }

    @Override
    public Role save(AddRoleDto addRoleDto) {
        Role role = new Role();
        role.setName(addRoleDto.getName());
        role.setUsers(new HashSet<>());

        return this.roleRepository.save(role);
    }

    @Override
    public Role delete(Long id) {
        Role deletedRole = this.find(id);

        this.roleRepository.delete(deletedRole);

        return deletedRole;
    }
}
