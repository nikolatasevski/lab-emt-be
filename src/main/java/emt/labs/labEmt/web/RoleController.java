package emt.labs.labEmt.web;

import emt.labs.labEmt.model.Role;
import emt.labs.labEmt.model.dto.AddRoleDto;
import emt.labs.labEmt.service.RoleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(this.roleService.findAll());
    }

    @PostMapping
    public Role createRole(@RequestBody AddRoleDto addRoleDto){
        return this.roleService.save(addRoleDto);
    }

    @DeleteMapping("/{id}")
    public Role deleteRole(@PathVariable Long id){
        return this.roleService.delete(id);
    }
}