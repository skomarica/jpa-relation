package io.github.skomarica.practice.rest;

import io.github.skomarica.practice.dto.RoleCreateUpdateDto;
import io.github.skomarica.practice.model.Role;
import io.github.skomarica.practice.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sinisa Komarica
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role findOne(@PathVariable("id") Long id) {
        return roleService.find(id);
    }

    @PostMapping
    public void create(@RequestBody RoleCreateUpdateDto dto) {
        roleService.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody RoleCreateUpdateDto dto) {
        roleService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleService.delete(id);
    }
}
