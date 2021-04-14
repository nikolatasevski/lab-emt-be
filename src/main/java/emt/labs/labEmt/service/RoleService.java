package emt.labs.labEmt.service;

import emt.labs.labEmt.model.Role;
import emt.labs.labEmt.model.dto.AddRoleDto;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role find(Long id);

    Role save(AddRoleDto addRoleDto);

    Role delete(Long id);
}
